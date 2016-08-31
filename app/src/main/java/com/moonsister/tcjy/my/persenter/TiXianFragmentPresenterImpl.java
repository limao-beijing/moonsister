package com.moonsister.tcjy.my.persenter;

import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.InsertBaen;
import com.moonsister.tcjy.bean.TiXinrRecordBean;
import com.moonsister.tcjy.my.model.TiXianFragmentModel;
import com.moonsister.tcjy.my.model.TiXianFragmentModelImpl;
import com.moonsister.tcjy.my.view.TiXianFragmentView;
import com.moonsister.tcjy.utils.StringUtis;

/**
 * Created by jb on 2016/7/2.
 */
public class TiXianFragmentPresenterImpl implements TiXianFragmentPresenter, BaseIModel.onLoadDateSingleListener<TiXinrRecordBean> {
    private TiXianFragmentView view;
    private TiXianFragmentModel model;

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(TiXianFragmentView tiXianFragmentView) {
        this.view = tiXianFragmentView;
        model = new TiXianFragmentModelImpl();
    }

    @Override
    public void loadTixin() {
        view.showLoading();
        model.loadTixin(this);
    }

    @Override
    public void onSuccess(TiXinrRecordBean bean, BaseIModel.DataType dataType) {
        if (StringUtis.equals(bean.getCode(), AppConstant.code_request_success)) {
            view.setLoadData(bean);
        } else {
            view.transfePageMsg(bean.getMsg());
        }

        view.hideLoading();

    }

    @Override
    public void onFailure(String msg) {
        view.hideLoading();
        view.transfePageMsg(msg);
    }
}
