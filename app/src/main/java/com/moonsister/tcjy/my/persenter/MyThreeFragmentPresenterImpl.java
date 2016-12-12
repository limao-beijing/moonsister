package com.moonsister.tcjy.my.persenter;


import com.hickey.network.bean.BaseBean;
import com.hickey.network.bean.MyThreeFragmentBean;
import com.hickey.network.bean.StatusBean;
import com.hickey.network.bean.UserDetailBean;
import com.hickey.tool.base.BaseIModel;
import com.hickey.tool.lang.StringUtis;
import com.moonsister.tcjy.my.model.MyThreeFragmentModel;
import com.moonsister.tcjy.my.model.MyThreeFragmentModelImpl;
import com.moonsister.tcjy.my.view.MyThreeFragmentView;

/**
 * Created by jb on 2016/9/25.
 */
public class MyThreeFragmentPresenterImpl implements MyThreeFragmentPresenter, BaseIModel.onLoadDateSingleListener<BaseBean> {
    private MyThreeFragmentView view;
    private MyThreeFragmentModel model;

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(MyThreeFragmentView view) {
        this.view = view;
        model = new MyThreeFragmentModelImpl();
    }

    @Override
    public void loadData(String uid, int page, String type) {
        view.showLoading();
        model.loadData(uid, page, type, this);
    }

    @Override
    public void loadHeaderData(String uid) {
        model.loadHeaderData(uid, this);
    }

    @Override
    public void delectRes(String ResId) {
        view.showLoading();
        model.deleteRes(ResId, this);
    }

    @Override
    public void onSuccess(BaseBean bean, BaseIModel.DataType dataType) {
        switch (dataType) {
            case DATA_ZERO:
                if (bean instanceof MyThreeFragmentBean)
                    view.setData(((MyThreeFragmentBean) bean).getData());
                break;

            case DATA_ONE:
                if (bean instanceof UserDetailBean) {
                    view.setHeaderData((UserDetailBean) bean);
                }
                break;
            case DATA_TWO:
                if (StringUtis.equals(bean.getCode(), "1") && bean instanceof StatusBean) {

                    view.deleteSuccess(((StatusBean) bean).getId());
                }
                break;
        }
        view.hideLoading();
    }

    @Override
    public void onFailure(String msg) {
        view.transfePageMsg(msg);
        view.hideLoading();
    }
}
