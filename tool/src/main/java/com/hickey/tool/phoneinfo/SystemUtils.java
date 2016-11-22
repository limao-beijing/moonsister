package com.hickey.tool.phoneinfo;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;

/**
 * Created by jb on 2016/6/18.
 */
public class SystemUtils {
    /**
     * 进程名
     *
     * @param context
     * @return
     */
    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

    public static String getScreenWeith(Activity activity) {
        Activity activityContext = activity;
        DisplayMetrics dm = new DisplayMetrics();
        activityContext.getWindowManager().getDefaultDisplay().getMetrics(dm);
        DisplayMetrics dm2 = activityContext.getResources().getDisplayMetrics();
        //获取屏幕默认分辨率
        Display d = activityContext.getWindowManager().getDefaultDisplay();
        int heightPixels = dm.heightPixels;
        int widthPixels = dm.widthPixels;
        return widthPixels + "";
    }

    public static String getScreenHeight(Activity activity) {
        Activity activityContext = activity;
        DisplayMetrics dm = new DisplayMetrics();
        activityContext.getWindowManager().getDefaultDisplay().getMetrics(dm);
        DisplayMetrics dm2 = activityContext.getResources().getDisplayMetrics();
        //获取屏幕默认分辨率
        Display d = activityContext.getWindowManager().getDefaultDisplay();
        int heightPixels = dm.heightPixels;
        int widthPixels = dm.widthPixels;
        return heightPixels + "";
    }

    /**
     * 屏幕大小
     *
     * @param context
     * @return
     */
    public static String getDisplayMetrics(Activity context) {
        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        DisplayMetrics dm2 = context.getResources().getDisplayMetrics();
        //获取屏幕默认分辨率
        Display d = context.getWindowManager().getDefaultDisplay();
        int heightPixels = dm.heightPixels;
        int widthPixels = dm.widthPixels;
//        String dp = "屏幕宽度:" + widthPixels
//                + "\n屏幕高度:" + heightPixels
//                + "\n分辨率:" + widthPixels
//                + "*" + heightPixels;
        String dp = widthPixels + "*" + heightPixels;
        return dp;
    }

    /**
     * 内存大小
     *
     * @param context
     * @return
     */
    public static String getMemoryInfo(Context context) {
        ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
        ActivityManager manager = (ActivityManager) context.getSystemService(
                Context.ACTIVITY_SERVICE);
        manager.getMemoryInfo(info);
        long availMem = info.availMem / 1024 / 1024;
        String sdCardPath = MemoryManager.getPhoneInSDCardPath();
        long phoneSelfSize = MemoryManager.getPhoneSelfSize() / 1024 / 1024; // 1397428224 byte鍊?/1024 kb /1024 mb
//                long mb = phoneSelfSize / 1024 / 1024;
        long phoneSelfFreeSize = MemoryManager.getPhoneSelfFreeSize() / 1024 / 1024;
        float phoneSize = MemoryManager.getPhoneSelfSDCardSize() / 1024 / 1024; // 鎵嬫満鍐呴儴鍌ㄥ瓨 涓嶆槸SD鍗?                Log.d(TAG, "phoneSize: " + (phoneSize / 1024 / 1024 / 1024));  // 5204983808  绾︾瓑浜?G
        //  4963MB 4.847519GB
        float phoneFreeSize = MemoryManager.getPhoneSelfSDCardFreeSize() / 1024 / 1024; // 绾?00MB
        long phoneAllSize = MemoryManager.getPhoneAllSize() / 1024 / 1024;
        long phoneAllFreeSize = MemoryManager.getPhoneAllFreeSize() / 1024 / 1024;
        String s1 = "总内存:" + availMem
                + "\nSD卡路径:" + sdCardPath
                + "\n机身内存:" + phoneSelfSize
                + "\n" + phoneSelfFreeSize
                + "\n" + phoneSize
                + "\n手机可用内存" + phoneFreeSize
                + "\n剩余内存:" + phoneAllFreeSize
                + "\n" + phoneAllSize;
        return s1;
    }

    /**
     * 是否root
     *
     * @return
     */
    public static boolean isRoot() {
        boolean bool = false;
        try {
            if ((!new File("/system/bin/su").exists()) && (!new File("/system/xbin/su").exists())) {
                bool = false;
            } else {
                bool = true;
            }
        } catch (Exception e) {
        }
        return bool;
    }

    /**
     * 获取手机的cup数
     *
     * @return
     */
    public static int getCPUnumber() {
        File dir = new File("/sys/devices/system/cpu");
        File[] files = dir.listFiles(new CpuFilter());
        int length = files.length;
        return length;
    }


    private static class CpuFilter implements FileFilter {

        @Override
        public boolean accept(File pathname) {
            if (Pattern.matches("(cpu[0-9])", pathname.getName())) {
                return true;
            }
            return false;
        }
    }

    /**
     * 手机基本信息
     *
     * @param context
     * @return
     */
    public static String getTelephonInfo(Context context) {
        //从系统服务获取手机管理者
        TelephonyManager telManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        //获取网络类型
        int networkType = telManager.getNetworkType();
        String subscriberId = telManager.getSubscriberId();
        String deviceId = telManager.getDeviceId();
        String line1Number = telManager.getLine1Number();
        int dataState = telManager.getDataState();
        String simOperatorName = telManager.getSimOperatorName();
        int phoneType = telManager.getPhoneType();
        String radioVersion = Build.getRadioVersion();
        String simCountryIso = telManager.getSimCountryIso();
        //基带版本
        String baseOs = Build.VERSION.BASE_OS;
        int sdkInt = Build.VERSION.SDK_INT;
        String model = Build.MODEL;
        String release = Build.VERSION.RELEASE;
        String s = "手机IMEI:" + deviceId
                + "\n手机号码:" + line1Number
                + "\n网络类型:" + networkType
                + "\n数据状态:" + dataState
                + "\n基带版本:" + radioVersion
                + "\n操作系统:" + baseOs
                + "\nsdk:" + sdkInt
                + "\nSIM提供商的国家代码:" + simCountryIso
                + "\n设备运营商:" + simOperatorName
                + "\n移动终端的类型:" + phoneType
                + "\n手机IMSI:" + subscriberId
                + "\n系统版本号:" + release
                + "\nmodle" + model;

        return s;
    }

}
