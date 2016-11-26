package com.moonsister.tcjy.engagement.widget;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.hickey.network.ImageServerApi;
import com.hickey.tool.base.BaseActivity;
import com.hickey.tool.constant.EnumConstant;
import com.hickey.tool.lang.StringUtis;
import com.hickey.tool.widget.UIUtils;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.dialogFragment.DialogMannager;
import com.hickey.tool.base.BaseDialogFragment;
import com.moonsister.tcjy.dialogFragment.widget.ImPermissionDialog;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.permission.UserPermissionManager;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.widget.CircularImageView;
import com.trello.rxlifecycle.ActivityEvent;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by jb on 2016/9/21.
 */
public class EngagementTypeActivity extends BaseActivity {
    @Bind(R.id.civ_user_avater)
    CircularImageView mCivUserAvater;
    @Bind(R.id.tv_user_name)
    TextView mTvUserName;
    private String uid;
    private String avater;
    private String name;
    @Bind(R.id.tv_more)
    TextView tv_more;
    private EnumConstant.EngegamentType type;

    @Override
    protected View setRootContentView() {
        return UIUtils.inflateLayout(R.layout.activity_engagment);
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        uid = intent.getStringExtra("id");
        name = intent.getStringExtra("name");
        avater = intent.getStringExtra("avater");
        mTvUserName.setText(name);
        ImageServerApi.showURLSamllImage(mCivUserAvater, avater);
        tv_more.setVisibility(View.GONE);
    }

    @OnClick({R.id.tv_other, R.id.tv_fadai, R.id.tv_meal, R.id.tv_movie, R.id.tv_shop, R.id.tv_coffee, R.id.tv_travel, R.id.tv_more, R.id.iv_cancel})
    public void onClick(View view) {
        if (view.getId() == R.id.iv_cancel) {
            finish();
            return;
        }

        switch (view.getId()) {
            case R.id.tv_fadai:
                type = EnumConstant.EngegamentType.fadai;
                break;
            case R.id.tv_meal:
                type = EnumConstant.EngegamentType.meal;
                break;
            case R.id.tv_movie:
                type = EnumConstant.EngegamentType.movie;
                break;
            case R.id.tv_shop:
                type = EnumConstant.EngegamentType.shop;
                break;
            case R.id.tv_coffee:
                type = EnumConstant.EngegamentType.coffee;
                break;
            case R.id.tv_travel:
                type = EnumConstant.EngegamentType.travel;
                break;
            case R.id.tv_more:
                type = EnumConstant.EngegamentType.All;
                break;
            case R.id.tv_other:
                type = EnumConstant.EngegamentType.other;
                break;

        }
        checkPermission();

    }

    private void checkPermission() {
        UserPermissionManager.getInstance().checkVip(EnumConstant.PermissionType.LATEST_PUB, new UserPermissionManager.PermissionCallback() {
            @Override
            public void onStatus(EnumConstant.PermissionReasult reasult, int imCount, String sex) {
                if (reasult == EnumConstant.PermissionReasult.HAVE_PERSSION) {
                    ActivityUtils.startEngagemengOrderActivity(type, uid, name, avater);
                    finish();
                } else if (reasult == EnumConstant.PermissionReasult.NOT_PERSSION) {
                    DialogMannager.getInstance().showEngagementPermission(sex, getSupportFragmentManager(), new ImPermissionDialog.OnCallBack() {
                        @Override
                        public void onStatus(BaseDialogFragment dialogFragment, EnumConstant.DialogCallBack statusCode) {
                            if (statusCode == EnumConstant.DialogCallBack.CANCEL) {
                                ActivityUtils.startEngagemengOrderActivity(type, uid, name, avater);
                                finish();
                            } else {
                                if (StringUtis.equals(sex, "1")) {
                                    ActivityUtils.startBuyVipActivity();
                                    RxBus.with(EngagementTypeActivity.this)
                                            .setEndEvent(ActivityEvent.DESTROY)
                                            .setEvent(Events.EventEnum.BUY_VIP_SUCCESS)
                                            .onNext(events ->
                                                    dialogFragment.dismissDialogFragment()
                                            )
                                            .create();
                                } else {
                                    ActivityUtils.startRenZhengThreeActivity();
                                    RxBus.with(EngagementTypeActivity.this)
                                            .setEndEvent(ActivityEvent.DESTROY)
                                            .setEvent(Events.EventEnum.REN_ZHENG_SUCCESS)
                                            .onNext(events ->
                                                    dialogFragment.dismissDialogFragment()
                                            )
                                            .create();
                                }
                            }
                        }
                    });
                }
            }
        });

    }
}
