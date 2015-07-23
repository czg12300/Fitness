package cn.jake.fitness.ui.fragment.base;

import com.umeng.analytics.MobclickAgent;

public abstract class BaseFitnessFragment extends BaseWorkerFragment {
	@Override
	public void onResume() {
		super.onResume();
		MobclickAgent.onResume(getActivity());
	}

	@Override
	public void onPause() {
		super.onPause();
		MobclickAgent.onPause(getActivity());
	}
}
