package cn.jake.fitness.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import cn.jake.fitness.R;
import cn.jake.fitness.ui.adapter.CommonFragmentPagerAdapter;
import cn.jake.fitness.ui.fragment.base.BaseFitnessFragment;
import cn.jake.fitness.ui.widgt.indicator.IIndicator;
import cn.jake.fitness.ui.widgt.indicator.IndicatorViewPager;

public class MachineFragment extends BaseFitnessFragment {
	public static Fragment newInstance() {
		return newInstance(null);
	}

	private ViewPager mParentViewPager;

	public static Fragment newInstance(ViewPager parent) {
		MachineFragment fragment = new MachineFragment();
		fragment.setParentViewParent(parent);
		return fragment;
	}

	public void setParentViewParent(ViewPager parent) {
		mParentViewPager = parent;
	}

	@Override
	public View initView() {
		IndicatorViewPager ivp = new IndicatorViewPager(getActivity());
		ivp.setTabHeight(getResources().getDimension(R.dimen.indicator_viewpager_height));
		ivp.setTabBackgroundColor(Color.WHITE);
		ivp.setTabTextColor(getResources().getColor(R.color.black_363636));
		ivp.setTabTextSize(getResources().getDimension(R.dimen.indicator_viewpager_tab_text_size));
		ivp.setTabLineHeight(getResources().getDimension(R.dimen.indicator_viewpager_tab_line_height));
		ivp.setTabLineColor(getResources().getColor(R.color.gray_cccccc));
		ivp.setTabSelectColor(getResources().getColor(R.color.blue_14b7f3));
		ivp.setTabChangeColor(true);
		ivp.setPagerParent(mParentViewPager);
		sendUiMessage(obtainUiMessage());
		return ivp;
	}

	@Override
	public void handleUiMessage(Message msg) {
		final List<String> labelList = new ArrayList<String>();
		labelList.add("哑铃");
		labelList.add("杠铃");
		labelList.add("拉力");
		labelList.add("综合");
		labelList.add("其他");
		final CommonFragmentPagerAdapter adapter = new CommonFragmentPagerAdapter(getActivity().getSupportFragmentManager());
		final ArrayList<Fragment> list = new ArrayList<Fragment>();
		list.add(NewsFragment.newInstance());
		list.add(NewsFragment.newInstance());
		list.add(NewsFragment.newInstance());
		list.add(NewsFragment.newInstance());
		list.add(NewsFragment.newInstance());
		adapter.setData(list);
		((IndicatorViewPager) convertView).setIndicator(new IIndicator() {

			@Override
			public List<String> getLabelList() {
				return labelList;
			}

			@Override
			public PagerAdapter getAdapter() {
				return adapter;
			}
		});
	}

}
