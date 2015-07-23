
package cn.jake.fitness.config;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Process;

import java.util.HashMap;

public class FitnessApplication extends Application {
    private static FitnessApplication mInstance;

    private static Context mContext;

    private HashMap<String, Activity> mActivityMap;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mContext = getApplicationContext();
        mActivityMap = new HashMap<String, Activity>();
    }

    public static Context getContext() {
        return mContext;
    }
    public static FitnessApplication getInstance() {
        return mInstance;
    }

    public void addActivity(Activity activity) {
        mActivityMap.put(activity.getClass().getSimpleName(), activity);
    }

    public void removeActivity(Activity activity) {
        mActivityMap.remove(activity.getClass().getSimpleName());
    }

    public void exitApp() {
        if (mActivityMap != null && mActivityMap.size() > 0) {
            for (String key : mActivityMap.keySet()) {
                if (mActivityMap.get(key) != null) {
                    mActivityMap.get(key).finish();
                }
            }
        }
        mActivityMap.clear();
        Process.killProcess(Process.myPid());
        System.exit(0);
    }
}
