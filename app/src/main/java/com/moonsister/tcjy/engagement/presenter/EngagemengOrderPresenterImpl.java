package com.moonsister.tcjy.engagement.presenter;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.BaseBean;
import com.moonsister.tcjy.engagement.model.EngagemengOrderModel;
import com.moonsister.tcjy.engagement.model.EngagemengOrderModelImpl;
import com.moonsister.tcjy.engagement.model.EngagemengOrderView;
import com.moonsister.tcjy.utils.EnumConstant;
import com.moonsister.tcjy.utils.StringUtis;
import com.moonsister.tcjy.utils.UIUtils;

/**
 * Created by jb on 2016/9/27.
 */
public class EngagemengOrderPresenterImpl implements EngagemengOrderPresenter, BaseIModel.onLoadDateSingleListener<BaseBean> {
    private EngagemengOrderView view;
    private EngagemengOrderModel model;

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(EngagemengOrderView view) {
        this.view = view;
        model = new EngagemengOrderModelImpl();

    }

    @Override
    public void loadData(EnumConstant.EngegamentType type, String uid, String money, String date, String message, String address) {
        view.showLoading();
        model.submitData(type, uid, money, date, message, address, this);
    }

    @Override
    public void onSuccess(BaseBean bean, BaseIModel.DataType dataType) {
        if (StringUtis.equals("1", bean.getCode())) {
            UIUtils.sendDelayedOneMillis(new Runnable() {
                @Override
                public void run() {
                    view.submitSuccess();
                }
            });

        }else if (StringUtis.equals("10",bean.getCode())){
            view.notLevel();
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
