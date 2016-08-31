package com.moonsister.tcjy.login.presenter;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.BaseBean;
import com.moonsister.tcjy.bean.InsertBaen;
import com.moonsister.tcjy.login.model.RegiterDataFragmentModel;
import com.moonsister.tcjy.login.model.RegiterDataFragmentModelImpl;
import com.moonsister.tcjy.login.view.RegiterDataFragmentView;
import com.moonsister.tcjy.utils.ConfigUtils;
import com.moonsister.tcjy.utils.LogUtils;
import com.moonsister.tcjy.utils.UIUtils;

import java.util.Objects;

/**
 * Created by jb on 2016/6/15.
 */
public class RegiterDataFragmentPresenterImpl implements RegiterDataFragmentPresenter, BaseIModel.onLoadDateSingleListener {

    private RegiterDataFragmentView view;
    private RegiterDataFragmentModel model;

    @Override
    public void login(String face, String sex, String pwd, String authcode) {
        view.showLoading();
        LogUtils.e(this, "login  : " + face + " sex " +
                sex + " pwd" +
                pwd + " authcode " +
                authcode + " ");
        model.login(face, sex, pwd, authcode, this);

    }

    @Override
    public void upLoadIcon(String iconPath) {
        view.showLoading();
        model.upLoadIcon(iconPath, this);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(RegiterDataFragmentView regiterDataFragmentView) {
        this.view = regiterDataFragmentView;
        model = new RegiterDataFragmentModelImpl();
    }

    @Override
    public void onSuccess(Object o, BaseIModel.DataType type) {
        switch (type) {
            case DATA_ZERO:
                if (o != null && o instanceof BaseBean) {
                    BaseBean baseBean = (BaseBean) o;
                    LogUtils.e(this, "msg : " + baseBean.getMsg());
                    view.transfePageMsg(baseBean.getMsg());
                    if ("1".equals(baseBean.getCode())) {
                        UIUtils.sendDelayed(new Runnable() {
                            @Override
                            public void run() {
                                view.navigationNext();
                            }
                        }, 500);

                    }
                }
                break;
            case DATA_ONE:
                if (o != null && o instanceof String) {
                    view.uploadSuccess((String) o);
                    view.transfePageMsg(UIUtils.getResources().getString(R.string.upload) + UIUtils.getResources().getString(R.string.success));
                }
                break;
        }

        view.hideLoading();

    }

    @Override
    public void onFailure(String msg) {
        LogUtils.e(this, "Throwable : " + msg);
        view.transfePageMsg(ConfigUtils.getInstance().getResources().getString(R.string.net_Exception));
        view.hideLoading();
    }
}
