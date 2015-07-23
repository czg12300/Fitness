package cn.jake.fitness.ui.widgt;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class InnerViewPager extends ViewPager {

	public InnerViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		mViewPagerList = new ArrayList<ViewPager>();
	}

	public InnerViewPager(Context context) {
		this(context, null);
	}

	private List<ViewPager> mViewPagerList;

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		super.onLayout(changed, l, t, r, b);
//		getScrollChildView(this);
	}

	private void getScrollChildView(ViewGroup parent) {
		int childCount = parent.getChildCount();
		for (int i = 0; i < childCount; i++) {
			View child = parent.getChildAt(i);
			if (child instanceof ViewPager) {
				mViewPagerList.add((ViewPager) child);
			} else if (child instanceof ViewGroup) {
				getScrollChildView((ViewGroup) child);
			}
		}
	}

//	@Override
//	public boolean onInterceptTouchEvent(MotionEvent ev) {
//		ViewPager viewPager = getTouchViewPager(ev);
//		if (viewPager != null && viewPager.getCurrentItem() != 0 || viewPager.getCurrentItem() != viewPager.getChildCount() - 1) {
//			return super.onInterceptTouchEvent(ev);
//		}
//		return super.onInterceptTouchEvent(ev);
//	}

	private ViewPager getTouchViewPager(MotionEvent ev) {
		if (mViewPagerList.size() == 0) {
			return null;
		}
		for (ViewPager v : mViewPagerList) {
			if (isTouchOnChild(v, ev)) {
				return v;
			}
		}
		return null;
	}

	private boolean isTouchOnChild(View child, MotionEvent ev) {
		Rect mRect = new Rect();

		int[] location = new int[2];
		child.getLocationOnScreen(location);

		mRect.left = location[0];
		mRect.right = location[0] + child.getWidth();
		mRect.top = location[1];
		mRect.bottom = location[1] + child.getHeight();

		if (mRect.contains((int) ev.getRawX(), (int) ev.getRawY())) {
			return true;
		}
		return false;
	}

	//
	// // �������뼰����
	// private float xDistance, yDistance, xLast, yLast;
	//
	// @Override
	// public boolean onInterceptTouchEvent(MotionEvent ev) {
	// switch (ev.getAction()) {
	// case MotionEvent.ACTION_DOWN:
	// xDistance = yDistance = 0f;
	// xLast = ev.getX();
	// yLast = ev.getY();
	// break;
	// case MotionEvent.ACTION_MOVE:
	// final float curX = ev.getX();
	// final float curY = ev.getY();
	//
	// xDistance += Math.abs(curX - xLast);
	// yDistance += Math.abs(curY - yLast);
	// xLast = curX;
	// yLast = curY;
	//
	// if (xDistance < yDistance) {
	// return false; // ��ʾ���´����¼�
	// }
	// }
	//
	// return super.onInterceptTouchEvent(ev);
	// }
}
