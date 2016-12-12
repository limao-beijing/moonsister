package com.moonsister.tcjy.my.persenter;


import com.hickey.network.bean.BaseBean;
import com.hickey.tool.base.BaseIModel;
import com.hickey.tool.lang.StringUtis;
import com.hickey.tool.widget.UIUtils;
import com.moonsister.tcjy.center.widget.DynamicContentFragment;
import com.moonsister.tcjy.my.model.DynamicResAddModel;
import com.moonsister.tcjy.my.model.DynamicResAddModelImpl;
import com.moonsister.tcjy.my.view.DynamicResAddView;

import java.util.List;

/**
 * Created by jb on 2016/9/30.
 */
public class DynamicResAddPersenterImpl implements DynamicResAddPersenter, BaseIModel.onLoadDateSingleListener<BaseBean> {
    private DynamicResAddView view;
    private DynamicResAddModel model;

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(DynamicResAddView view) {
        this.view = view;
        model = new DynamicResAddModelImpl();
    }

    @Override
    public void submit(DynamicContentFragment.DynamicType type, List<String> contents) {
        view.showLoading();
        model.submit(type, contents, this);
    }

    @Override
    public void onSuccess(BaseBean bean, BaseIModel.DataType dataType) {
        if (StringUtis.equals(bean.getCode(), "1")) {
            UIUtils.sendDelayedOneMillis(new Runnable() {
                @Override
                public void run() {
                    view.finishPage();
                }
            });
        }
        view.transfePageMsg(bean.getMsg());
        view.hideLoading();
    }

    @Override
    public void onFailure(String msg) {
        view.transfePageMsg(msg);
        view.hideLoading();
    }
}
