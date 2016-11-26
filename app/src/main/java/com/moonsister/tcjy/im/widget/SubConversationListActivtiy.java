package com.moonsister.tcjy.im.widget;

import android.content.Intent;
import android.view.View;

import com.hickey.tool.base.BaseActivity;
import com.hickey.tool.widget.UIUtils;
import com.moonsister.tcjy.R;

/**
 * Created by jb on 2016/7/20.
 */
public class SubConversationListActivtiy extends BaseActivity {
    /**
     * 目标 Id
     */
    private String mTargetId;

    /**
     * 会话类型
     */
//    private Conversation.ConversationType mConversationType = Conversation.ConversationType.PRIVATE;

    @Override
    protected View setRootContentView() {
        return UIUtils.inflateLayout(R.layout.appconversation);
    }


    @Override
    protected void initView() {
        Intent intent = getIntent();
//        getIntentDate(intent);

    }

    @Override
    protected String initTitleName() {
        String name = getIntent().getData().getQueryParameter("title");
        return name;
    }

    /**
     * 展示如何从 Intent 中得到 融云会话页面传递的 Uri
     */
//    private void getIntentDate(Intent intent) {
//        mTargetId = intent.getData().getQueryParameter("targetId");
//        enterFragment(mConversationType, mTargetId);
//    }

    /**
     * 加载会话页面 ConversationFragment
     *
     * @param mConversationType
     * @param mTargetId
     */
//    private void enterFragment(Conversation.ConversationType mConversationType, String mTargetId) {
//
//        MessageListFragment fragment = new MessageListFragment ();
//        Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
//                .appendPath("conversation").appendPath(mConversationType.getName().toLowerCase())
//                .appendQueryParameter("targetId", mTargetId).build();
//
//        fragment.setUri(uri);
//
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        //xxx 为你要加载的 id
//        transaction.add(R.id.conversation, fragment);
//        transaction.commit();
//
//    }
}
