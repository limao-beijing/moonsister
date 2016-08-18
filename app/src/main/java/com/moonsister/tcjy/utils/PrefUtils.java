package com.moonsister.tcjy.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharePreference封装
 * 
 * @author Kevin
 * 
 */
public class PrefUtils {
	public static final String PREF_NAME = "config";

	public static boolean getBoolean(Context ctx, String key,
			boolean defaultValue) {
		SharedPreferences sp = ctx.getSharedPreferences(PREF_NAME,
				Context.MODE_PRIVATE);
		return sp.getBoolean(key, defaultValue);
	}

	public static boolean getBoolean(String key, boolean defaultValue) {
		SharedPreferences sp = ConfigUtils.getInstance().getApplicationContext().getSharedPreferences(
				PREF_NAME, Context.MODE_PRIVATE);
		return sp.getBoolean(key, defaultValue);
	}

	public static int getInt(String key, int defaultValue) {
		SharedPreferences sp = ConfigUtils.getInstance().getApplicationContext().getSharedPreferences(
				PREF_NAME, Context.MODE_PRIVATE);
		return sp.getInt(key, defaultValue);
	}

	public static void setInt(String key, int value) {
		SharedPreferences sp = ConfigUtils.getInstance().getApplicationContext().getSharedPreferences(
				PREF_NAME, Context.MODE_PRIVATE);
		sp.edit().putInt(key, value).commit();
	}

	public static void setInt(Context ctx, String key, int value) {
		SharedPreferences sp = ctx.getSharedPreferences(PREF_NAME,
				Context.MODE_PRIVATE);
		sp.edit().putInt(key, value).commit();
	}

	public static void setBoolean(Context ctx, String key, boolean value) {
		SharedPreferences sp = ctx.getSharedPreferences(PREF_NAME,
				Context.MODE_PRIVATE);
		sp.edit().putBoolean(key, value).commit();
	}

	public static void setBoolean(String key, boolean value) {
		SharedPreferences sp = ConfigUtils.getInstance().getApplicationContext().getSharedPreferences(
				PREF_NAME, Context.MODE_PRIVATE);
		sp.edit().putBoolean(key, value).commit();
	}

	public static String getString(Context ctx, String key, String defaultValue) {
		SharedPreferences sp = ctx.getSharedPreferences(PREF_NAME,
				Context.MODE_PRIVATE);
		return sp.getString(key, defaultValue);
	}

	public static String getString(String key, String defaultValue) {
		SharedPreferences sp =ConfigUtils.getInstance().getApplicationContext().getSharedPreferences(
				PREF_NAME, Context.MODE_PRIVATE);
		return sp.getString(key, defaultValue);
	}

	public static long getLong(String key, long defaultValue) {
		SharedPreferences sp = ConfigUtils.getInstance().getApplicationContext().getSharedPreferences(
				PREF_NAME, Context.MODE_PRIVATE);
		return sp.getLong(key, defaultValue);
	}

	public static void setLong(Context ctx, String key, long value) {
		SharedPreferences sp = ctx.getSharedPreferences(PREF_NAME,
				Context.MODE_PRIVATE);
		sp.edit().putLong(key, value).commit();
	}

	public static void setString(Context ctx, String key, String value) {
		SharedPreferences sp = ctx.getSharedPreferences(PREF_NAME,
				Context.MODE_PRIVATE);
		sp.edit().putString(key, value).commit();
	}

	public static void setString(String key, String value) {
		SharedPreferences sp = ConfigUtils.getInstance().getApplicationContext().getSharedPreferences(
				PREF_NAME, Context.MODE_PRIVATE);
		sp.edit().putString(key, value).commit();
	}

	public static void clearSP(Context ctx) {
		SharedPreferences sp = ctx.getSharedPreferences(PREF_NAME,
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		editor.clear();
		editor.commit();
	}



}
