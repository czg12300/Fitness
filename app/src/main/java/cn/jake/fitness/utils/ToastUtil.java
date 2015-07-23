package cn.jake.fitness.utils;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.Toast;
import cn.jake.fitness.config.FitnessApplication;

/**
 * toast
 * 
 * @author jake
 * 
 */
public class ToastUtil {
	private static Toast mToast;
	private static String mMsg;
	private static Handler mHandler = new Handler(Looper.getMainLooper()) {
		@Override
		public void handleMessage(android.os.Message msg) {
			String text = (String) msg.obj;
			if (!TextUtils.isEmpty(text)) {
				if (!TextUtils.equals(text, mMsg)) {
					mMsg = text;
					mToast = Toast.makeText(FitnessApplication.getContext(), text, Toast.LENGTH_SHORT);
				}
				mToast.show();
			}
		};
	};

	public static void showMsg(String msg) {
		mHandler.sendMessage(mHandler.obtainMessage(0, msg));
	}

	public static void showMsg(int resId) {
		showMsg(FitnessApplication.getContext().getString(resId));
	}
}
