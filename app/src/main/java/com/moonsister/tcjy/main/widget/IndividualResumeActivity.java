package com.moonsister.tcjy.main.widget;

import android.view.View;

import com.hickey.network.bean.resposen.IndividualResumeBean;
import com.hickey.tool.base.BaseActivity;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.main.presenter.IndividualResumePresenter;
import com.moonsister.tcjy.main.presenter.IndividualResumePresenterImpl;
import com.moonsister.tcjy.main.view.IndividualResumeView;
import com.moonsister.tcjy.viewholder.IndividualResumeViewHolder;
import com.trello.rxlifecycle.ActivityEvent;

/**
 * Created by jb on 2016/12/1.
 */

public class IndividualResumeActivity extends BaseActivity implements IndividualResumeView {

    private IndividualResumePresenter presenter;
    private String uid;
    private IndividualResumeViewHolder holder;
    private IndividualResumeBean bean;

    @Override
    protected View setRootContentView() {
        holder = new IndividualResumeViewHolder(this);
        return holder.getContentView();
    }

    @Override
    protected String initTitleName() {
        return "个人资料";
    }

    @Override
    protected void initView() {
        presenter = new IndividualResumePresenterImpl();
        presenter.attachView(this);
        uid = getIntent().getStringExtra("id");
        presenter.loadInitData(uid);
        RxBus.with(this)
                .setEndEvent(ActivityEvent.DESTROY)
                .setEvent(Events.EventEnum.BUY_VIP_SUCCESS)
                .onNext(events -> {
                    if (bean != null) {
                        presenter.loadInitData(uid);
                    }
                })
                .create();
    }


    @Override
    public void setIniData(IndividualResumeBean bean) {
        this.bean = bean;
        bean.setUid(uid);
        holder.refreshView(bean);
    }


}
