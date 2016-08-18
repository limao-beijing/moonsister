//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.moonsister.tcjy.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;

public class CommonUtils {
    public CommonUtils() {
    }

    public static void saveNotificationQuietHours(Context mContext, String startTime, int spanMinutes) {
        SharedPreferences mPreferences = null;
        if (mContext != null) {
            mPreferences = mContext.getSharedPreferences("RONG_SDK", 0);
        }

        if (mPreferences != null) {
            Editor editor = mPreferences.edit();
            editor.putString("QUIET_HOURS_START_TIME", startTime);
            editor.putInt("QUIET_HOURS_SPAN_MINUTES", spanMinutes);
            editor.commit();
        }

    }

    public static String getNotificationQuietHoursForStartTime(Context mContext) {
        SharedPreferences mPreferences = null;
        if (mPreferences == null && mContext != null) {
            mPreferences = mContext.getSharedPreferences("RONG_SDK", 0);
        }

        return mPreferences != null ? mPreferences.getString("QUIET_HOURS_START_TIME", "") : "";
    }

    public static int getNotificationQuietHoursForSpanMinutes(Context mContext) {
        SharedPreferences mPreferences = null;
        if (mPreferences == null && mContext != null) {
            mPreferences = mContext.getSharedPreferences("RONG_SDK", 0);
        }

        return mPreferences != null ? mPreferences.getInt("QUIET_HOURS_SPAN_MINUTES", 0) : 0;
    }


    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5F);
    }

    public static String md5(Object object) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(toByteArray(object));
        } catch (NoSuchAlgorithmException var7) {
            throw new RuntimeException("Huh, MD5 should be supported?", var7);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        byte[] arr$ = hash;
        int len$ = hash.length;

        for (int i$ = 0; i$ < len$; ++i$) {
            byte b = arr$[i$];
            if ((b & 255) < 16) {
                hex.append("0");
            }

            hex.append(Integer.toHexString(b & 255));
        }

        return hex.toString();
    }

    public static byte[] toByteArray(Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            ObjectOutputStream ex = new ObjectOutputStream(bos);
            ex.writeObject(obj);
            ex.flush();
            bytes = bos.toByteArray();
            ex.close();
            bos.close();
        } catch (IOException var4) {
            var4.printStackTrace();
        }

        return bytes;
    }

    public static String getDataPath(Context context) {
        String path;
        if (isExistSDcard()) {
            path = Environment.getExternalStorageDirectory().getPath() + "/" + context.getPackageName() + "/img_cache";
        } else {
            path = context.getFilesDir().getPath() + "/" + context.getPackageName() + "/img_cache";
        }

        if (!path.endsWith("/")) {
            path = path + "/";
        }

        return path;
    }

    public static boolean isExistSDcard() {
        String state = Environment.getExternalStorageState();
        return state.equals("mounted");
    }
}
