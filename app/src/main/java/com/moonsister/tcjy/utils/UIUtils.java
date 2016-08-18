package com.moonsister.tcjy.utils;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by pc on 2016/6/3.
 */
public class UIUtils {
    /**
     * 填充布局
     *
     * @param resID
     * @return
     */
    public static View inflateLayout(int resID) {
        return LayoutInflater.from(ConfigUtils.getInstance().getApplicationContext()).inflate(resID, null);
    }

    /**
     * 填充布局
     *
     * @param resID
     * @return
     */
    public static View inflateLayout(int resID, ViewGroup root) {
        return LayoutInflater.from(ConfigUtils.getInstance().getApplicationContext()).inflate(resID, root, false);
    }


    /**
     * 获取字符串资源
     *
     * @param resid
     * @return
     */
    @NonNull
    public static String getStringRes(@StringRes int resid) {
        return getResources().getString(resid);

    }

    /**
     * 获取Resources
     *
     * @return
     */
    public static Resources getResources() {
        return ConfigUtils.getInstance().getResources();
    }

    /**
     * 运行在主线程
     *
     * @param r
     */
    public static void onRunMainThred(Runnable r) {
        ConfigUtils.getInstance().getMainHandler().post(r);
    }

    /**
     * 延迟执行
     *
     * @param r
     * @param time
     */
    public static void sendDelayed(Runnable r, long time) {
        ConfigUtils.getInstance().getMainHandler().postDelayed(r, time);
    }

    /**
     * 延迟1秒执行
     *
     * @param r
     * @param
     */
    public static void sendDelayedOneMillis(Runnable r) {
        ConfigUtils.getInstance().getMainHandler().postDelayed(r, 1000);
    }

    private static Toast toast;

    /**
     * 单列
     *
     * @param ctx
     * @param text
     */
    public static void showToast(Context ctx, String text) {
        if (ctx == null || StringUtis.isEmpty(text))
            return;
        if (toast == null) {
            toast = Toast.makeText(ctx, text, Toast.LENGTH_SHORT);
        } else {
            toast.setText(text);
        }
        toast.show();
    }

}
