package cn.jake.fitness.utils;

import android.util.Log;
import cn.jake.fitness.config.FitnessApplication;
import cn.jake.fitness.config.FitnessConfig;

public final class L {
	public static boolean isDebug = FitnessConfig.isDebug;
	public static String TAG = FitnessApplication.getContext().getPackageName();

	public static void i(String msg) {
		i(TAG, msg);
	}

	public static void i(String tag, String msg) {
		if (isDebug) {
			Log.i(tag, msg);
		}
	}

	public static void d(String msg) {
		d(TAG, msg);
	}

	public static void d(String tag, String msg) {
		if (isDebug) {
			Log.d(tag, msg);
		}
	}

	public static void w(String msg) {
		w(TAG, msg);
	}

	public static void w(String tag, String msg) {
		if (isDebug) {
			Log.w(tag, msg);
		}
	}

	public static void e(String msg) {
		e(TAG, msg);
	}

	public static void e(String tag, String msg) {
		if (isDebug) {
			Log.e(tag, msg);
		}
	}
}
