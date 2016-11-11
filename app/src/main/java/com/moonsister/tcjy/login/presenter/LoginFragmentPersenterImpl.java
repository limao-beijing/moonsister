package com.moonsister.tcjy.login.presenter;

import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.LoginBean;
import com.moonsister.tcjy.bean.PersonInfoDetail;
import com.moonsister.tcjy.login.model.LoginFragmentModel;
import com.moonsister.tcjy.login.model.LoginFragmentModelImpl;
import com.moonsister.tcjy.login.view.LoginFragmentView;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.LogUtils;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tool.lang.StringUtis;

/**
 * Created by jb on 2016/6/17.
 */
public class LoginFragmentPersenterImpl implements LoginFragmentPersenter, BaseIModel.onLoadDateSingleListener<LoginBean> {
    private LoginFragmentView loginView;
    private LoginFragmentModel loginFragmentModel;

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(LoginFragmentView loginFragmentView) {
        this.loginView = loginFragmentView;
        loginFragmentModel = new LoginFragmentModelImpl();
    }


    @Override
    public void loginSubmit(String phone, String password) {
        loginView.showLoading();
        loginFragmentModel.login(phone, password, this);

    }

    @Override
    public void onSuccess(LoginBean loginBean, BaseIModel.DataType dataType) {
        if (loginBean != null) {
            if (StringUtis.equals(loginBean.getCode(), AppConstant.code_request_success)) {
                PersonInfoDetail data = loginBean.getData();
                data.setLogin(true);
                if (data != null)
                    UserInfoManager.getInstance().saveMemoryInstance(data);
                loginView.loginSuccss();

            } else
                loginView.transfePageMsg(loginBean.getMsg());
        } else {
            loginView.transfePageMsg(UIUtils.getResources().getString(R.string.str_login) + UIUtils.getStringRes(R.string.failed));

        }
        loginView.hideLoading();


    }

    @Override
    public void onFailure(String msg) {

        loginView.transfePageMsg(msg);
        LogUtils.e(this, msg);
        loginView.hideLoading();
    }
}
