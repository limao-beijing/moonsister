package com.moonsister.tcjy.im.widget;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.google.android.gms.common.api.GoogleApiClient;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.bean.PersonInfoDetail;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.StringUtis;
import com.moonsister.tcjy.utils.UIUtils;
import com.trello.rxlifecycle.ActivityEvent;

import im.gouyin.com.progressdialog.AlearDialog;
import io.rong.imkit.RongyunManager;
import io.rong.imkit.fragment.ConversationFragment;
import io.rong.imkit.fragment.MessageListFragment;
import io.rong.imkit.fragment.UriFragment;
import io.rong.imlib.model.Conversation;

/**
 * Created by jb on 2016/6/18.
 */
public class AppConversationActivity extends BaseActivity {
    public final static String SYSTEM_PATH = "/conversation/system";

    private GoogleApiClient client;
    private String mTargetId;

    @Override
    protected View setRootContentView() {
        return UIUtils.inflateLayout(R.layout.appconversation);
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        mTargetId = getIntent().getData().getQueryParameter("targetId");
        getIntentDate(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!StringUtis.equals(getIntent().getData().getPath(), SYSTEM_PATH)) {
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
        if (StringUtis.equals(AppConstant.CHANNEL_ID, "1015")) {
            alearDialog = new AlearDialog(AlearDialog.DialogType.Certification_im_1015, this);
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

        String name = getIntent().getData().getQueryParameter("title");
        if (StringUtis.isEmpty(name)) {
            name = RongyunManager.getInstance().getUserName(mTargetId);
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
        String mTargetId = intent.getData().getQueryParameter("targetId");
        enterFragment(mTargetId);
    }

    /**
     * 加载会话页面 ConversationFragment
     *
     * @param
     * @param mTargetId
     */
    private void enterFragment(String mTargetId) {
        Conversation.ConversationType mConversationType;
        UriFragment fragment;
        if (!StringUtis.equals(getIntent().getData().getPath(), SYSTEM_PATH)) {
            mConversationType = Conversation.ConversationType.PRIVATE;
            fragment = new ConversationFragment();
        } else {
            mConversationType = Conversation.ConversationType.SYSTEM;
            fragment = new MessageListFragment();
        }

        Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                .appendPath("conversation").appendPath(mConversationType.getName().toLowerCase())
                .appendQueryParameter("targetId", mTargetId).build();

        fragment.setUri(uri);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //xxx 为你要加载的 id
        transaction.add(R.id.conversation, fragment);
        transaction.commit();
        RxBus.with(this)
                .setEndEvent(ActivityEvent.DESTROY)
                .setEvent(Events.EventEnum.CHAT_SEND_REDPACKET_SUCCESS)
                .onNext(events -> {
                    RongyunManager.getInstance().sendRedPacketMessage(mTargetId, (String) events.message);
                })
                .create();
    }

}
