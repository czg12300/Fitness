package cn.jake.fitness.ui.widgt;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

public class NoConflictListView extends ListView {

	public NoConflictListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public NoConflictListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public NoConflictListView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	private float xDistance, yDistance, xLast, yLast;

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			xDistance = yDistance = 0f;
			xLast = ev.getX();
			yLast = ev.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			final float curX = ev.getX();
			final float curY = ev.getY();

			xDistance += Math.abs(curX - xLast);
			yDistance += Math.abs(curY - yLast);
			xLast = curX;
			yLast = curY;

			if (xDistance > yDistance) {
				return false;
			}
		}

		return super.onInterceptTouchEvent(ev);
	}
}
