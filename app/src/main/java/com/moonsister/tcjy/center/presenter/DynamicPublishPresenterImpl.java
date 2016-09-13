package com.moonsister.tcjy.center.presenter;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.BaseBean;
import com.moonsister.tcjy.bean.InsertBaen;
import com.moonsister.tcjy.center.model.DynamicPublishModel;
import com.moonsister.tcjy.center.model.DynamicPublishModelImpl;
import com.moonsister.tcjy.center.view.DefaultDynamicView;
import com.moonsister.tcjy.utils.EnumConstant;
import com.moonsister.tcjy.utils.UIUtils;

import java.util.List;
import java.util.Objects;

/**
 * Created by jb on 2016/6/23.
 */
public class DynamicPublishPresenterImpl implements DynamicPublishPresenter, BaseIModel.onLoadDateSingleListener {
    private DefaultDynamicView view;
    private DynamicPublishModel model;

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(DefaultDynamicView defaultDynamicView) {
        this.view = defaultDynamicView;
        model = new DynamicPublishModelImpl();

    }

    @Override
    public void sendDynamic(EnumConstant.DynamicType dynamicType, String content, List<String> datas, String tags, String address) {
        view.showLoading();
        model.sendDynamicPics(dynamicType, content, datas, tags, address, this);

    }


//    @Override
//    public void onSuccess(Objects o, BaseIModel.DataType dataType) {
//        view.hideLoading();
//        view.transfePageMsg(UIUtils.getStringRes(R.string.upload) + UIUtils.getStringRes(R.string.success));
//        view.finishPage();
//    }

    @Override
    public void onSuccess(Object o, BaseIModel.DataType dataType) {
        view.hideLoading();
        view.transfePageMsg(UIUtils.getStringRes(R.string.upload) + UIUtils.getStringRes(R.string.success));
        view.finishPage();
    }

    @Override
    public void onFailure(String msg) {
        view.hideLoading();
        view.transfePageMsg(UIUtils.getStringRes(R.string.request_failed));
    }
}
