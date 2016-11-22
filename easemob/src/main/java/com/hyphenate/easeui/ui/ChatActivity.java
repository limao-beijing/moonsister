package com.hyphenate.easeui.ui;

import android.content.Intent;
import android.os.Bundle;

import com.easemob.easeui.R;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.db.HxUserDao;
import com.hyphenate.easeui.domain.EaseUser;

public class ChatActivity extends EaseBaseActivity {
    public static ChatActivity activityInstance;
    private EaseChatFragment chatFragment;
    String toChatUsername;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_chat);
        activityInstance = this;
        //user or group id
        Bundle extras = getIntent().getExtras();
        toChatUsername = extras.getString(EaseConstant.EXTRA_USER_ID);

        HxUserDao dao = new HxUserDao();
        EaseUser user = new EaseUser(toChatUsername);
        user.setAvatar(extras.getString(EaseConstant.EXTRA_USER_AVATER));
        user.setNick(extras.getString(EaseConstant.EXTRA_USER_NIKE));
        dao.saveUser(user);
        chatFragment = new ChatFragment();

        //set arguments
        chatFragment.setArguments(extras);
        getSupportFragmentManager().beginTransaction().add(R.id.container, chatFragment).commit();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityInstance = null;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        // enter to chat activity when click notification bar, here make sure only one chat activiy
        String username = intent.getStringExtra("userId");
        if (toChatUsername.equals(username))
            super.onNewIntent(intent);
        else {

            startActivity(intent);
            finish();
        }

    }

    @Override
    public void onBackPressed() {
        chatFragment.onBackPressed();
    }

    public String getToChatUsername() {
        return toChatUsername;
    }
}
