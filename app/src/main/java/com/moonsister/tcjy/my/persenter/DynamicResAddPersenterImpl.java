package com.moonsister.tcjy.my.persenter;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.BaseBean;
import com.moonsister.tcjy.center.widget.DynamicContentFragment;
import com.moonsister.tcjy.my.model.DynamicResAddModel;
import com.moonsister.tcjy.my.model.DynamicResAddModelImpl;
import com.moonsister.tcjy.my.widget.DynamicResAddView;
import com.moonsister.tcjy.utils.StringUtis;
import com.moonsister.tcjy.utils.UIUtils;

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
