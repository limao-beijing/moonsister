package com.hyphenate.easeui.db;

import com.hyphenate.easeui.domain.EaseUser;

/**
 * Created by jb on 2016/10/21.
 */
public class HxUserDao {
    public static final String TABLE_NAME = "user";
    public static final String COLUMN_NAME_NICK = "nick";
    public static final String COLUMN_NAME_AVATAR = "avater";
    public static final String COLUMN_NAME_ID = "id";

    public void saveUser(EaseUser user) {
        DbMamager.getInstance().saveContact(user);

    }

    public EaseUser getUser(String uid) {
        return DbMamager.getInstance().getContact(uid);
    }
}
