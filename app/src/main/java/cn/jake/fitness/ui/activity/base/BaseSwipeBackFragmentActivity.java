package cn.jake.fitness.ui.activity.base;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import cn.jake.fitness.ui.widgt.ChangeThemeUtils;
import cn.jake.fitness.ui.widgt.swipeback.SwipeBackActivityHelper;
import cn.jake.fitness.ui.widgt.swipeback.SwipeBackLayout;

public class BaseSwipeBackFragmentActivity extends BaseWorkerFragmentActivity {
	private SwipeBackActivityHelper mHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mHelper = new SwipeBackActivityHelper(this);
		mHelper.onActivityCreate();
		getSwipeBackLayout().setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
		DisplayMetrics metric = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metric);
		int width = metric.widthPixels;
		getSwipeBackLayout().setEdgeSize(width);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mHelper.onPostCreate();
	}

	@Override
	public View findViewById(int id) {
		View v = super.findViewById(id);
		if (v == null && mHelper != null) {
			return mHelper.findViewById(id);
		}
		return v;
	}

	public SwipeBackLayout getSwipeBackLayout() {
		return mHelper.getSwipeBackLayout();
	}

	public void setSwipeBackEnable(boolean enable) {
		getSwipeBackLayout().setEnableGesture(enable);
	}

	public void scrollToFinishActivity() {
		ChangeThemeUtils.convertActivityToTranslucent(this);
		getSwipeBackLayout().scrollToFinishActivity();
	}
}
