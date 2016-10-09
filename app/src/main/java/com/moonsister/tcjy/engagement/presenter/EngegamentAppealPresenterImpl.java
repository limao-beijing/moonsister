package com.moonsister.tcjy.engagement.presenter;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.StatusBean;
import com.moonsister.tcjy.engagement.model.EngegamentAppealModel;
import com.moonsister.tcjy.engagement.model.EngegamentAppealModelImpl;
import com.moonsister.tcjy.engagement.view.EngegamentAppealView;
import com.moonsister.tcjy.utils.StringUtis;
import com.moonsister.tcjy.utils.UIUtils;

/**
 * Created by jb on 2016/9/29.
 */
public class EngegamentAppealPresenterImpl implements EngegamentAppealPresenter, BaseIModel.onLoadDateSingleListener<StatusBean> {
    private EngegamentAppealView view;
    private EngegamentAppealModel model;

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(EngegamentAppealView view) {
        this.view = view;
        model = new EngegamentAppealModelImpl();
    }

    @Override
    public void submitAppeal(String id, String content) {
        view.showLoading();
        model.submitAppeal(id, content, this);
    }

    @Override
    public void onSuccess(StatusBean bean, BaseIModel.DataType dataType) {
        if (bean != null && StringUtis.equals(bean.getCode(), "1")) {
            UIUtils.sendDelayedOneMillis(new Runnable() {
                @Override
                public void run() {
                    view.submitSuccess();
                }
            });

        }
        view.transfePageMsg(bean.getMsg());
        view.hideLoading();
    }

    @Override
    public void onFailure(String msg) {
        view.hideLoading();
        view.transfePageMsg(msg);
    }
}
