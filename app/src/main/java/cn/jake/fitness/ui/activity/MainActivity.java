package cn.jake.fitness.ui.activity;

import java.util.ArrayList;

import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import cn.jake.fitness.R;
import cn.jake.fitness.ui.activity.base.BaseTitleActivity;
import cn.jake.fitness.ui.adapter.CommonFragmentPagerAdapter;
import cn.jake.fitness.ui.fragment.MachineFragment;
import cn.jake.fitness.ui.fragment.NewsFragment;

public class MainActivity extends BaseTitleActivity implements OnPageChangeListener, OnCheckedChangeListener {
	private CommonFragmentPagerAdapter mMainPageAdapter;
	private ViewPager mVpContent;
	private RadioGroup mRgMenu;
	private static final int UI_MESSAGE_INIT = 0x1f;
	private TextView mTvTitle;
	private static final int[] TITLE_IDS = { R.string.news_title, R.string.machine_title, R.string.prisoner_title, R.string.sport_title, R.string.mine_title };

	@Override
	protected void setTitle(String title) {
		mTvTitle.setText(title);
	}

	@Override
	protected void initView() {
		setContentView(R.layout.activity_main);
		setTitleLayout(R.layout.title_main);
		mTvTitle = (TextView) findViewById(R.id.tv_title);
		mVpContent = (ViewPager) findViewById(R.id.vp_content);
		mRgMenu = (RadioGroup) findViewById(R.id.rg_menu);
		mMainPageAdapter = new CommonFragmentPagerAdapter(getSupportFragmentManager());
		mVpContent.setAdapter(mMainPageAdapter);
		mVpContent.setOnPageChangeListener(this);
		mRgMenu.setOnCheckedChangeListener(this);
		mVpContent.setOffscreenPageLimit(4);
		setSwipeBackEnable(false);
		sendEmptyUiMessage(UI_MESSAGE_INIT);
	}

	@Override
	public void handleUiMessage(Message msg) {
		switch (msg.what) {
		case UI_MESSAGE_INIT:
			initMainData();
			break;

		default:
			break;
		}
	}

	private void initMainData() {
		ArrayList<Fragment> list = new ArrayList<Fragment>();
		list.add(NewsFragment.newInstance());
		list.add(MachineFragment.newInstance(mVpContent));
		list.add(NewsFragment.newInstance());
		list.add(NewsFragment.newInstance());
		list.add(NewsFragment.newInstance());
		mMainPageAdapter.setData(list);
		setTitle(TITLE_IDS[0]);
	}

	@Override
	public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
		int position = 0;
		switch (checkId) {
		case R.id.rb_menu_news:
			position = 0;
			break;
		case R.id.rb_menu_machine:
			position = 1;
			break;
		case R.id.rb_menu_prisoner:
			position = 2;
			break;
		case R.id.rb_menu_sport:
			position = 3;
			break;
		case R.id.rb_menu_mine:
			position = 4;
			break;
		}
		mVpContent.setCurrentItem(position, false);
		setTitle(TITLE_IDS[position]);
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	@Override
	public void onPageSelected(int position) {
		switch (position) {
		case 0:
			mRgMenu.check(R.id.rb_menu_news);
			break;
		case 1:
			mRgMenu.check(R.id.rb_menu_machine);
			break;
		case 2:
			mRgMenu.check(R.id.rb_menu_prisoner);
			break;
		case 3:
			mRgMenu.check(R.id.rb_menu_sport);
			break;
		case 4:
			mRgMenu.check(R.id.rb_menu_mine);
			break;
		}
		setTitle(TITLE_IDS[position]);
	}
}
