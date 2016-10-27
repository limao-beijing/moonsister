package com.hyphenate.easeui.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jb on 2016/10/21.
 */

public class DbOpenHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 6;
    private static DbOpenHelper instance;
    private static final String USERNAME_TABLE_CREATE = "CREATE TABLE "
            + HxUserDao.TABLE_NAME + " ("
            + HxUserDao.COLUMN_NAME_NICK + " TEXT, "
            + HxUserDao.COLUMN_NAME_AVATAR + " TEXT, "
            + HxUserDao.COLUMN_NAME_ID + " TEXT PRIMARY KEY);";

    public DbOpenHelper(Context context) {
        super(context, getUserDatabaseName(), null, DATABASE_VERSION);
    }

    public static DbOpenHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DbOpenHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(USERNAME_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static String getUserDatabaseName() {
        return "hx.db";
    }

    public void closeDB() {
        if (instance != null) {
            try {
                SQLiteDatabase db = instance.getWritableDatabase();
                db.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            instance = null;
        }
    }
}
