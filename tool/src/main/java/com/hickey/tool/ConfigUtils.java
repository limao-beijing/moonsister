package com.hickey.tool;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;

import java.util.ArrayList;

/**
 * Created by pc on 2016/6/3.
 */
public class ConfigUtils {
    private static Context mApplicationContext;
    private static Activity mActivityContext;
    private static Handler mHandler;
    private static final ConfigUtils instance = new ConfigUtils();
    private ArrayList<Activity> activities = new ArrayList<Activity>();
    private Resources resources;

    private ConfigUtils() {
    }

    public static ConfigUtils getInstance() {

        return instance;
    }

    /**
     * 获取全局上下文
     *
     * @return ApplicationContext
     */
    public Context getApplicationContext() {

        return mApplicationContext;
    }

    /**
     * 设置全局上下文
     *
     * @param applicationContext
     */
    public void setApplicationContext(Context applicationContext) {
        mApplicationContext = applicationContext;
        getMainHandler();
        getResources();
    }

    /**
     * 设置主acticity的上下文
     *
     * @param Activity
     */
    public void setActivityContext(Activity Activity) {
        mActivityContext = Activity;
    }

    /**
     * 获取Acticity上下文
     *
     * @return
     */
    public Activity getActivityContext() {
        return mActivityContext;
    }

    /**
     * 获取主线程的handler
     *
     * @return
     */
    public Handler getMainHandler() {
        if (mHandler == null)
            mHandler = new Handler(Looper.getMainLooper());
        return mHandler;
    }

    public Resources getResources() {
        if (resources == null)
            resources = mApplicationContext.getResources();
        return resources;
    }
    /**
     * 添加activity
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        if (!activities.contains(activity))
            activities.add(activity);
    }

    /**
     * 添加activity
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        if (activities.contains(activity))
            activities.remove(activity);
    }

    public void logout() {
        for (int i = 0; i < activities.size(); i++) {
            activities.get(i).finish();
        }
        activities.clear();
    }
}
