package com.moonsister.tcjy.engagement.widget;

import android.view.View;

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
import com.trello.rxlifecycle.ActivityEvent;

import butterknife.OnClick;

/**
 * Created by jb on 2016/9/22.
 */
public class EngegamentPublishActivity extends BaseActivity {
    EnumConstant.EngegamentType type;

    @Override
    protected View setRootContentView() {
        return UIUtils.inflateLayout(R.layout.activity_engagment_publish);
    }

    @Override
    protected void initView() {

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
            case R.id.tv_other:
                type = EnumConstant.EngegamentType.other;
                break;
            case R.id.tv_more:
                type = EnumConstant.EngegamentType.All;
                break;

        }
        checkPermission();
    }

    private void checkPermission() {
        showProgressDialog();
        UserPermissionManager.getInstance().checkVip(EnumConstant.PermissionType.LATEST_PUB, new UserPermissionManager.PermissionCallback() {
            @Override
            public void onStatus(EnumConstant.PermissionReasult reasult, int imCount, String sex) {
                hideProgressDialog();
                if (reasult == EnumConstant.PermissionReasult.HAVE_PERSSION) {
                    ActivityUtils.startEengegamentRecommendActivity(type);
                    finish();
                } else if (reasult == EnumConstant.PermissionReasult.NOT_PERSSION) {
                    DialogMannager.getInstance().showEngagementPermission(sex, getSupportFragmentManager(), new ImPermissionDialog.OnCallBack() {
                        @Override
                        public void onStatus(BaseDialogFragment dialogFragment, EnumConstant.DialogCallBack statusCode) {
                            if (statusCode == EnumConstant.DialogCallBack.CANCEL) {
                                ActivityUtils.startEengegamentRecommendActivity(type);
                                finish();
                            } else {
                                if (StringUtis.equals(sex, "1")) {
                                    ActivityUtils.startBuyVipActivity();
                                    RxBus.with(EngegamentPublishActivity.this)
                                            .setEndEvent(ActivityEvent.DESTROY)
                                            .setEvent(Events.EventEnum.BUY_VIP_SUCCESS)
                                            .onNext(events ->
                                                    dialogFragment.dismissDialogFragment()
                                            )
                                            .create();
                                } else {
                                    ActivityUtils.startRenZhengThreeActivity();
                                    RxBus.with(EngegamentPublishActivity.this)
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
