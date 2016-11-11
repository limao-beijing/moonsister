package com.moonsister.tool.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jb on 2016/6/17.
 */
public class TimeUtils {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String YEAR_MONTH_DAY = "yyyyMMdd";
    private static final String HOUR_MINUTE_SECOND = "HHmmss";
    private static final String HOUR = "HH";

    /**
     * 格式当前时间
     *
     * @return
     */
    public static String formatCurrentDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        String format = simpleDateFormat.format(getDate());
        return format;
    }

    /**
     * 格式化当前的年月日
     *
     * @return
     */
    public static String getCurrentYearMonthDay() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(YEAR_MONTH_DAY);
        return simpleDateFormat.format(new Date());
    }

    /**
     * 获取当前的小时
     *
     * @return
     */
    public static String getCurrentHour() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(HOUR);
        return simpleDateFormat.format(new Date());
    }

    /**
     * 获取当前的时分秒
     *
     * @return
     */
    public static String getCurrentHourMinuteSecond() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(HOUR_MINUTE_SECOND);
        return simpleDateFormat.format(new Date());
    }

    /**
     * 获取当前的时间戳
     *
     * @return
     */
    public static long getCurrentTimestamp() {
        return System.currentTimeMillis();
    }

    private static Date getDate() {
        return new Date();
    }

    public static String format(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);

        return simpleDateFormat.format(new Date(time));
    }

    /**
     * 格式化动态时间
     */
    static StringBuffer result = new StringBuffer();

    public static String getDynamicTimeString(long milliseconds) {
        if (milliseconds < 1000)
            return "";

        result.delete(0, result.length());

        long time = System.currentTimeMillis() - (milliseconds * 1000);
        long mill = (long) Math.ceil(time / 1000);//秒前

        long minute = (long) Math.ceil(time / 60 / 1000.0f);// 分钟前

        long hour = (long) Math.ceil(time / 60 / 60 / 1000.0f);// 小时

        long day = (long) Math.ceil(time / 24 / 60 / 60 / 1000.0f);// 天前

        if (day - 1 > 0 && day - 1 < 30) {
            result.append(day + "天");
        } else if (day - 1 >= 30) {
            result.append(Math.round((day - 1) / 30) + "个月");
        } else if (hour - 1 > 0) {
            if (hour >= 24) {
                result.append("1天");
            } else {
                result.append(hour + "小时");
            }
        } else if (minute - 1 > 0) {
            if (minute == 60) {
                result.append("1小时");
            } else {
                result.append(minute + "分钟");
            }
        } else if (mill - 1 > 0) {
            if (mill == 60) {
                result.append("1分钟");
            } else {
                result.append(mill + "秒");
            }
        } else {
            result.append("刚刚");
        }
        if (!result.toString().equals("刚刚")) {
            result.append("前");
        }
        return result.toString();
    }

    /**
     * 格式化时间戳
     *
     * @param time
     * @return
     */
    public static long formatTimestamp(String time) {
        long ts = 0;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = simpleDateFormat.parse(time);
            ts = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ts;


    }
}
