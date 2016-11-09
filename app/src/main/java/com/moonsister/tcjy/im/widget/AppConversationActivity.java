package com.moonsister.tcjy.im.widget;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.db.HxUserDao;
import com.hyphenate.easeui.domain.EaseUser;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.dialogFragment.BaseDialogFragment;
import com.moonsister.tcjy.dialogFragment.DialogMannager;
import com.moonsister.tcjy.dialogFragment.ImPermissionDialog;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.im.SendMsgForServiceHelper;
import com.moonsister.tcjy.permission.UserPermissionManager;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.EnumConstant;
import com.moonsister.tcjy.utils.StringUtis;
import com.moonsister.tcjy.utils.UIUtils;
import com.trello.rxlifecycle.ActivityEvent;


/**
 * Created by jb on 2016/6/18.
 */
public class AppConversationActivity extends BaseActivity {
    public final static String SYSTEM_PATH = "/conversation/system";

    private String mTargetId;
    private String toChatUsername;
    private com.hyphenate.easeui.ui.ChatFragment chatFragment;

    @Override
    protected View setRootContentView() {


        return UIUtils.inflateLayout(R.layout.appconversation);
    }

    @Override
    protected void initView() {
        TextView tv_title_right = (TextView) titleView.findViewById(R.id.tv_title_right);
        Drawable drawable = UIUtils.getResources().getDrawable(R.mipmap.im_userinfo);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        tv_title_right.setCompoundDrawables(drawable, null, null, null);
        tv_title_right.setPadding(10, 10, 10, 10);
        tv_title_right.setVisibility(View.VISIBLE);
        tv_title_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.startPersonalActivity(mTargetId);
            }
        });
        Intent intent = getIntent();

        mTargetId = getIntent().getExtras().getString(EaseConstant.EXTRA_USER_ID);
        getIntentDate(intent);
        getPermission();

    }

    private EnumConstant.PermissionReasult mReasult;
    private String mSex;
    private int mImCount;

    public void getPermission() {
        if (!StringUtis.equals(mTargetId, "10000"))
            UserPermissionManager.getInstance().checkIMPermission(mTargetId, new UserPermissionManager.PermissionCallback() {
                @Override
                public void onStatus(EnumConstant.PermissionReasult reasult, int count, String sex) {
                    mReasult = reasult;
                    mSex = sex;
                    mImCount = count;
                    if (mReasult != EnumConstant.PermissionReasult.HAVE_PERSSION) {
                        setRxbus();
                    }
                }
            });
        else {
            mReasult = EnumConstant.PermissionReasult.HAVE_PERSSION;
        }
    }

    private void setRxbus() {
        RxBus.with(this)
                .setEndEvent(ActivityEvent.DESTROY)
                .setEvent(Events.EventEnum.BUY_VIP_SUCCESS)
                .onNext(events -> {
                    mReasult = EnumConstant.PermissionReasult.HAVE_PERSSION;
                }).create();
    }


//        PersonInfoDetail memoryPersonInfoDetail = UserInfoManager.getInstance().getMemoryPersonInfoDetail();
//        if (memoryPersonInfoDetail.getVipStatus() == 1) {
//            return;
//        }
//        if (memoryPersonInfoDetail.getAttestation() != 3) {
//            return;
//        }
//        if (memoryPersonInfoDetail.getUserFriendList() != null && memoryPersonInfoDetail.getUserFriendList().contains(mTargetId)) {
//            return;
//        }
//        AlearDialog alearDialog = new AlearDialog(AlearDialog.DialogType.Certification_im_1002, this);
//        alearDialog.setListenter(new AlearDialog.onClickListenter() {
//            @Override
//            public void clickType(AlearDialog.clickType type) {
//                switch (type) {
//                    case cancel:
//                        finish();
//                        break;
//                    case confirm_vip:
//                        if (StringUtis.equals("1", sex))
//                            ActivityUtils.startBuyVipActivity();
//                        else
//                            ActivityUtils.startRenZhengThreeActivity();
//                        break;
//                    case confirm:
//                        ActivityUtils.startCertificationActivity();
//                        break;
//
//                }
//                alearDialog.dismiss();
//            }
//        });

//    }

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

        chatFragment = new com.hyphenate.easeui.ui.ChatFragment() {
            @Override
            public boolean isCanSendMessage(EMMessage message) {
                boolean isSend = false;
                switch (mReasult) {
                    case HAVE_PERSSION:
                        isSend = true;
                        break;
                    case NOT_PERSSION:
                        if (mImCount > 0) {
                            isSend = true;
                            mImCount--;
                        } else {
                            isSend = false;
                            showPermissionDialog();
                        }
                        break;
                    case NOT_NET:
                        isSend = false;
                        showToast(getString(R.string.request_failed));
                        break;
                }
                if (mHelper == null)
                    mHelper = new SendMsgForServiceHelper();
                if (mReasult != EnumConstant.PermissionReasult.HAVE_PERSSION)
                    mHelper.send(message);
                return isSend;
            }
        };
        //set arguments
        chatFragment.setArguments(extras);
        getSupportFragmentManager().beginTransaction().add(R.id.conversation, chatFragment).commit();

//
    }

    private SendMsgForServiceHelper mHelper;

    private void showPermissionDialog() {
        DialogMannager.getInstance().showImPermission(mSex, getSupportFragmentManager(), new ImPermissionDialog.OnCallBack() {
            @Override
            public void onStatus(BaseDialogFragment dialogFragment, EnumConstant.DialogCallBack statusCode) {
                if (statusCode == EnumConstant.DialogCallBack.CONFIRM) {
                    if (StringUtis.equals("1", mSex))
                        ActivityUtils.startBuyVipActivity();
                    else
                        ActivityUtils.startRenZhengThreeActivity();
                    dialogFragment.dismissDialogFragment();
                }

            }
        });
    }
}
