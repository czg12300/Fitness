package cn.jake.fitness.ui.widgt.indicator;

import java.util.List;

import android.support.v4.view.PagerAdapter;

public interface IIndicator {
	List<String> getLabelList();

	PagerAdapter getAdapter();
}
