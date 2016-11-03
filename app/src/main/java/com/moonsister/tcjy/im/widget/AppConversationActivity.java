package com.moonsister.tcjy.im.widget;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.db.HxUserDao;
import com.hyphenate.easeui.domain.EaseUser;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.bean.PersonInfoDetail;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.StringUtis;
import com.moonsister.tcjy.utils.UIUtils;

import java.util.Arrays;
import java.util.List;

import im.gouyin.com.progressdialog.AlearDialog;

/**
 * Created by jb on 2016/6/18.
 */
public class AppConversationActivity extends BaseActivity {
    public final static String SYSTEM_PATH = "/conversation/system";

    private String mTargetId;
    private String toChatUsername;

    @Override
    protected View setRootContentView() {
        return UIUtils.inflateLayout(R.layout.appconversation);
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        mTargetId = getIntent().getExtras().getString(EaseConstant.EXTRA_USER_ID);
        getIntentDate(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!StringUtis.equals(mTargetId, "10000")) {
            certificationStatus();
        }

    }

    public void certificationStatus() {
        PersonInfoDetail memoryPersonInfoDetail = UserInfoManager.getInstance().getMemoryPersonInfoDetail();
        if (memoryPersonInfoDetail.getVipStatus() == 1) {
            return;
        }
        if (memoryPersonInfoDetail.getAttestation() != 3) {
            return;
        }
        if (memoryPersonInfoDetail.getUserFriendList() != null && memoryPersonInfoDetail.getUserFriendList().contains(mTargetId)) {
            return;
        }
        AlearDialog alearDialog = null;
        String[] array = getResources().getStringArray(R.array.dynamic_channel_1002);
        List<String> strings = Arrays.asList(array);
        if (StringUtis.equals(AppConstant.CHANNEL_ID, "1015") || StringUtis.equals(AppConstant.CHANNEL_ID, "1009")) {

            alearDialog = new AlearDialog(AlearDialog.DialogType.Certification_im_1015, this);
        } else if (strings.contains(AppConstant.CHANNEL_ID)) {
            alearDialog = new AlearDialog(AlearDialog.DialogType.Certification_im_1002, this);
        } else {
            alearDialog = new AlearDialog(AlearDialog.DialogType.Certification_im, this);
        }
        final AlearDialog finalAlearDialog = alearDialog;
        alearDialog.setListenter(new AlearDialog.onClickListenter() {
            @Override
            public void clickType(AlearDialog.clickType type) {
                switch (type) {
                    case cancel:
                        finish();
                        break;
                    case confirm_vip:
                        ActivityUtils.startBuyVipActivity();
                        break;
                    case confirm:
                        ActivityUtils.startCertificationActivity();
                        break;
                }
                finalAlearDialog.dismiss();
            }
        });

    }


    @Override
    protected String initTitleName() {

        String name = getIntent().getExtras().getString(EaseConstant.EXTRA_USER_NIKE);
        if (StringUtis.isEmpty(name)) {
//            name = RongyunManager.getInstance().getUserName(mTargetId);
        }
        return name;
    }

    @Override
    public boolean isBaseonActivityResult() {
        return false;
    }

    /**
     * 展示如何从 Intent 中得到 融云会话页面传递的 Uri
     */
    private void getIntentDate(Intent intent) {
        String mTargetId = intent.getExtras().getString(EaseConstant.EXTRA_USER_ID);
        enterFragment(mTargetId);
    }

    /**
     * 加载会话页面 ConversationFragment
     *
     * @param
     * @param mTargetId
     */
    private void enterFragment(String mTargetId) {


        Bundle extras = getIntent().getExtras();
        toChatUsername = extras.getString(EaseConstant.EXTRA_USER_ID);

        HxUserDao dao = new HxUserDao();
        EaseUser user = new EaseUser(toChatUsername);
        user.setAvatar(extras.getString(EaseConstant.EXTRA_USER_AVATER));
        user.setNick(extras.getString(EaseConstant.EXTRA_USER_NIKE));
        dao.saveUser(user);
        com.hyphenate.easeui.ui.ChatFragment chatFragment = new com.hyphenate.easeui.ui.ChatFragment();
        //set arguments
        chatFragment.setArguments(extras);
        getSupportFragmentManager().beginTransaction().add(R.id.conversation, chatFragment).commit();
//        Conversation.ConversationType mConversationType;
//        UriFragment fragment;
//        if (!StringUtis.equals(getIntent().getData().getPath(), SYSTEM_PATH)) {
//            mConversationType = Conversation.ConversationType.PRIVATE;
//            fragment = new ConversationFragment();
//        } else {
//            mConversationType = Conversation.ConversationType.SYSTEM;
//            fragment = new MessageListFragment();
//        }
//
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
//        RxBus.with(this)
//                .setEndEvent(ActivityEvent.DESTROY)
//                .setEvent(Events.EventEnum.CHAT_SEND_REDPACKET_SUCCESS)
//                .onNext(events -> {
//                    RongyunManager.getInstance().sendRedPacketMessage(mTargetId, (String) events.message);
//                })
//                .create();
    }

}
