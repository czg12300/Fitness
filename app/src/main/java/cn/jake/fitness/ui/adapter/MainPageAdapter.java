package cn.jake.fitness.ui.adapter;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MainPageAdapter extends FragmentPagerAdapter {
	private ArrayList<Fragment> mFragmentList;

	public MainPageAdapter(FragmentManager fm) {
		super(fm);
		mFragmentList = new ArrayList<Fragment>();
	}

	public void setFragmentList(ArrayList<Fragment> list) {
		if (list != null && list.size() > 0) {
			mFragmentList = list;
			notifyDataSetChanged();
		}
	}

	public void addFragmentList(ArrayList<Fragment> list) {
		if (list != null && list.size() > 0) {
			mFragmentList.addAll(list);
			notifyDataSetChanged();
		}
	}

	public void addFragment(Fragment fragment) {
		if (fragment != null) {
			mFragmentList.add(fragment);
			notifyDataSetChanged();
		}
	}

	@Override
	public Fragment getItem(int position) {
		return mFragmentList.get(position);
	}

	@Override
	public int getCount() {
		return mFragmentList.size();
	}

}
