package cn.jake.fitness.ui.adapter;

import android.content.Context;
import android.view.View;
import cn.jake.fitness.ui.widgt.PinnedHeaderListView.PinnedHeaderListener;

public abstract class BasePinnedHeaderAdapter<T> extends BaseListAdapter<T> implements PinnedHeaderListener {

	public BasePinnedHeaderAdapter(Context context) {
		super(context);
	}

	@Override
	public int getPinnedHeaderState(int position) {
		if (getCount() == 0 || position < 0) {
			return PinnedHeaderListener.PINNED_HEADER_GONE;
		}
		if (needChangeTitle(position + 1)) {
			return PinnedHeaderListener.PINNED_HEADER_PUSHED_UP;
		}
		return PinnedHeaderListener.PINNED_HEADER_VISIBLE;
	}

	protected abstract boolean needChangeTitle(int position);

	@Override
	public void changeTitlePinnedHeader(View headerView, int position) {
		changeTitle(headerView, position);
	}

	protected abstract void changeTitle(View headerView, int position);
}