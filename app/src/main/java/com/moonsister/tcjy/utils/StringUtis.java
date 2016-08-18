package com.moonsister.tcjy.utils;

import android.text.TextUtils;

/**
 * Created by jb on 2016/6/17.
 */
public class StringUtis {
    /**
     * 判断是字符串
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str == null || str.isEmpty())
            return true;
        return false;

    }

    /**
     * 比较两个字符串一致
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean equals(String a, String b) {
        return TextUtils.equals(a, b);
    }

    public static int string2Int(String string) {
        try {

            return Integer.parseInt(string);
        } catch (NumberFormatException e) {

        }
        return 0;
    }

    public static double string2Double(String string) {
        try {
            return Double.parseDouble(string);
        } catch (NumberFormatException e) {

        }
        return 0.00;
    }

    /**
     * 格式化接口
     *
     * @param arg
     */
    public static String formatAPI(String arg) {
        if (isEmpty(arg))
            return "";
        return arg;
    }
}
