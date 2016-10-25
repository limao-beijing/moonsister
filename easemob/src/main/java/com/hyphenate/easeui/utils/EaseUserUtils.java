package com.hyphenate.easeui.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.easemob.easeui.R;
import com.hyphenate.easeui.controller.EaseUI;
import com.hyphenate.easeui.controller.EaseUI.EaseUserProfileProvider;
import com.hyphenate.easeui.db.HxUserDao;
import com.hyphenate.easeui.domain.EaseUser;

import java.util.HashMap;

public class EaseUserUtils {
    static HashMap<String, EaseUser> mUsers = new HashMap<String, EaseUser>();
    static EaseUserProfileProvider userProvider;

    static {
        userProvider = EaseUI.getInstance().getUserProfileProvider();
    }

    /**
     * get EaseUser according username
     *
     * @param username
     * @return
     */
    public static EaseUser getUserInfo(String username) {
        if (userProvider != null)
            return userProvider.getUser(username);

        return null;
    }

    public static void upUserInfo(String uid) {
        HxUserDao dao = new HxUserDao();
        EaseUser user = dao.getUser(uid);
        if (user != null && mUsers.containsKey(uid)) {
            EaseUser user1 = mUsers.get(uid);
            user1.setAvatar(user.getAvatar());
            user1.setNick(user.getNickname());
        }
    }

    /**
     * set user avatar
     *
     * @param username
     */
    public static void setUserAvatar(Context context, String username, ImageView imageView) {
        EaseUser user = null;
        if (mUsers.containsKey(username)) {
            user = mUsers.get(username);
            if (TextUtils.isEmpty(user.getNick())) {
                user = getUserInfo(username);
                mUsers.put(username, user);
            }
        } else {
            user = getUserInfo(username);
            mUsers.put(username, user);
        }

        if (user != null && user.getAvatar() != null) {
            try {
                int avatarResId = Integer.parseInt(user.getAvatar());
                Glide.with(context).load(avatarResId).into(imageView);
            } catch (Exception e) {
                //use default avatar
                Glide.with(context).load(user.getAvatar()).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.ease_default_avatar).into(imageView);
            }
        } else {
            Glide.with(context).load(R.drawable.ease_default_avatar).into(imageView);
        }
    }

    /**
     * set user's nickname
     */
    public static void setUserNick(String username, TextView textView) {
        if (textView != null) {
            EaseUser user = getUserInfo(username);
            if (user != null && user.getNick() != null) {
                textView.setText(user.getNick());
            } else {
                textView.setText(username);
            }
        }
    }

}
