package com.moonsister.tcjy.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.moonsister.tcjy.BuildConfig;


/**
 * Created by jb on 2016/7/14.
 */
public class PackageUtils {
    /**
     * 获取软件版本号
     *
     * @param context
     * @return
     */
    public static int getVersionCode(Context context) {
//        int versionCode = 1;
//        try {
//            // 获取软件版本号，对应AndroidManifest.xml下android:versionCode
//            versionCode = context.getPackageManager().getPackageInfo(
//                    context.getPackageName(), 0).versionCode;
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
        return BuildConfig.VERSION_CODE;
    }

    /**
     * 获取版本名称
     *
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
//        String versionName = "";
//        try {
//            // 获取软件版本号，对应AndroidManifest.xml下android:versionCode
//            versionName = context.getPackageManager().getPackageInfo(
//                    context.getPackageName(), 0).versionName;
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
        return BuildConfig.VERSION_NAME;
    }

    /**
     * huqo
     *
     * @return
     */
    public static String getPackageName() {
        return BuildConfig.APPLICATION_ID;

    }

    public static ApplicationInfo getApplicationInfo(Context context, String packageName, int flags) {
        try {
            if (context == null)
                return null;
            return context.getPackageManager().getApplicationInfo(getPackageName(), flags);
        } catch (PackageManager.NameNotFoundException e) {

            e.printStackTrace();
        }
        return null;
    }

}
