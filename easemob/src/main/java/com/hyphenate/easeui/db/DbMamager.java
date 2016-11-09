package com.hyphenate.easeui.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hyphenate.easeui.controller.EaseUI;
import com.hyphenate.easeui.domain.EaseUser;

/**
 * Created by jb on 2016/10/21.
 */

public class DbMamager {
    static private DbMamager dbMgr = new DbMamager();
    private DbOpenHelper dbHelper;

    private DbMamager() {
        dbHelper = DbOpenHelper.getInstance(EaseUI.getInstance().getContext());
    }

    public static synchronized DbMamager getInstance() {
        if (dbMgr == null) {
            dbMgr = new DbMamager();
        }
        return dbMgr;
    }

    synchronized public void saveContact(EaseUser user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(HxUserDao.COLUMN_NAME_AVATAR, user.getAvatar());
        values.put(HxUserDao.COLUMN_NAME_ID, user.getUsername());
        values.put(HxUserDao.COLUMN_NAME_NICK, user.getNick());
        if (db.isOpen())
            db.replace(HxUserDao.TABLE_NAME, null, values);

    }

    synchronized public EaseUser getContact(String uid) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        EaseUser user = null;
        if (db.isOpen()) {
//          String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having,String orderBy, String limit
            Cursor query = db.query(HxUserDao.TABLE_NAME, null, HxUserDao.COLUMN_NAME_ID + "=?" +
                    "", new String[]{uid}, null, null, null, null);
            if (query.moveToNext()) {
                if (query != null) {
                    user = new EaseUser(uid);
                    user.setAvatar(query.getString(query.getColumnIndex(HxUserDao.COLUMN_NAME_AVATAR)));
                    user.setNick(query.getString(query.getColumnIndex(HxUserDao.COLUMN_NAME_NICK)));
                }
            }
            query.close();
        }
        return user;


    }
}