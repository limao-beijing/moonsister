package com.example.baidu;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

/**
 * Created by jb on 2016/7/28.
 */
public class BaiduConfig {
    public static String appKey(Context context) {
        return getStringData(context, "BDAPPKEY");
    }

    public static String appID(Context context) {
        return getIntegerData(context, "BDAPPID") + "";
    }

    private static String getStringData(Context context, String key) {
        String value = "";
        try {
            ApplicationInfo appInfo = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            value = appInfo.metaData.getString(key);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return value;
    }

    private static int getIntegerData(Context context, String key) {
        int value = 0;
        try {
            ApplicationInfo appInfo = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            value = appInfo.metaData.getInt(key);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return value;
    }


}
