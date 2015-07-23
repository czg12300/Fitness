package cn.jake.fitness.ui.widgt;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * 支持ListView置顶功能
 */
public class PinnedHeaderListView extends ListView implements OnScrollListener {

	private View mHeaderView;
	private int mMeasuredWidth;
	private int mMeasuredHeight;
	private boolean mDrawFlag = true;
	private PinnedHeaderListener mPinnedHeaderListener;

	public PinnedHeaderListView(Context context) {
		this(context, null);
	}

	public PinnedHeaderListView(Context context, AttributeSet attrs) {
		this(context, attrs, -1);
	}

	public PinnedHeaderListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setOnScrollListener(this);
		mGestureDetector = new GestureDetector(context, new YScrollDetector());
		canScroll = true;
	}

	/**
	 * 设置置顶的Header View
	 * 
	 * @param pHeader
	 */
	public void setPinnedHeader(View pHeader) {
		mHeaderView = pHeader;
		requestLayout();
	}

	@Override
	public void setAdapter(ListAdapter adapter) {
		super.setAdapter(adapter);
		mPinnedHeaderListener = (PinnedHeaderListener) adapter;
	}

	// 三个覆写方法负责在当前窗口显示inflate创建的Header View

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		if (null != mHeaderView) {
			measureChild(mHeaderView, widthMeasureSpec, heightMeasureSpec);
			mMeasuredWidth = mHeaderView.getMeasuredWidth();
			mMeasuredHeight = mHeaderView.getMeasuredHeight();
		}
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);

		if (null != mHeaderView) {
			mHeaderView.layout(0, 0, mMeasuredWidth, mMeasuredHeight);
			handlePinnedHeader(getFirstVisiblePosition());
		}
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);

		if (null != mHeaderView && mDrawFlag) {
			drawChild(canvas, mHeaderView, getDrawingTime());
		}
	}

	private boolean canScroll;

	private GestureDetector mGestureDetector;

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		if (ev.getAction() == MotionEvent.ACTION_UP) {
			canScroll = true;
		}
		return super.onInterceptTouchEvent(ev) && mGestureDetector.onTouchEvent(ev);
	}

	class YScrollDetector extends SimpleOnGestureListener {
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
			if (canScroll) {
				if (Math.abs(distanceY) >= Math.abs(distanceX)) {
					canScroll = true;
				} else {
					canScroll = false;
				}
			}
			return !canScroll;
		}
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

		if (view instanceof PinnedHeaderListView) {
			((PinnedHeaderListView) view).handlePinnedHeader(firstVisibleItem);
		}
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
	}

	/**
	 * HeaderView三种状态的具体处理
	 * 
	 * @param position
	 */
	public void handlePinnedHeader(int position) {
		if (null == mHeaderView) {
			return;
		}

		int pinnedHeaderState = mPinnedHeaderListener.getPinnedHeaderState(position);
		switch (pinnedHeaderState) {
		case PinnedHeaderListener.PINNED_HEADER_GONE:
			mDrawFlag = false;
			break;

		case PinnedHeaderListener.PINNED_HEADER_VISIBLE:
			mPinnedHeaderListener.changeTitlePinnedHeader(mHeaderView, position);
			mDrawFlag = true;
			mHeaderView.layout(0, 0, mMeasuredWidth, mMeasuredHeight);
			break;

		case PinnedHeaderListener.PINNED_HEADER_PUSHED_UP:
			mPinnedHeaderListener.changeTitlePinnedHeader(mHeaderView, position);
			mDrawFlag = true;
			// 移动位置
			View topItem = getChildAt(0);

			if (null != topItem) {
				int bottom = topItem.getBottom();
				int height = mHeaderView.getHeight();

				int y;
				if (bottom < height) {
					y = bottom - height;
				} else {
					y = 0;
				}

				if (mHeaderView.getTop() != y) {
					mHeaderView.layout(0, y, mMeasuredWidth, mMeasuredHeight + y);
				}

			}
			break;
		}

	}

	public interface PinnedHeaderListener {

		public static final int PINNED_HEADER_GONE = 0;

		public static final int PINNED_HEADER_VISIBLE = 1;

		
		public static final int PINNED_HEADER_PUSHED_UP = 2;

		int getPinnedHeaderState(int position);

		void changeTitlePinnedHeader(View headerView, int position);
	}

}
