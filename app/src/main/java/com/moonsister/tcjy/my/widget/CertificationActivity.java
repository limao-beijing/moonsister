package com.moonsister.tcjy.my.widget;

import android.view.View;

import com.hickey.network.ImageServerApi;
import com.hickey.tool.base.BaseActivity;
import com.hickey.tool.widget.UIUtils;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.widget.RoundedImageView;
import com.trello.rxlifecycle.ActivityEvent;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 认证
 * Created by jb on 2016/6/27.
 */
public class CertificationActivity extends BaseActivity {
    @Bind(R.id.iv_avater)
    RoundedImageView ivAvater;

    @Override
    protected View setRootContentView() {
        return UIUtils.inflateLayout(R.layout.activity_red_person);
    }

    @Override
    protected String initTitleName() {
        return UIUtils.getStringRes(R.string.red_person_renzheng);
    }

    @Override
    protected void initView() {
        RxBus.with(this)
                .setEndEvent(ActivityEvent.DESTROY)
                .setEvent(Events.EventEnum.CERTIFICATION_PAGE_FINISH)
                .onNext(events ->pageFinish())
                .create();
        ImageServerApi.showURLSamllImage(ivAvater, UserInfoManager.getInstance().getAvater());
    }

    private void pageFinish() {
        finish();
    }

    @OnClick(R.id.tv_apply)
    public void onClick() {
        ActivityUtils.startRZFirstActivity();
    }

}
