package com.moonsister.tcjy.login.widget;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.moonsister.tcjy.ImageServerApi;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.base.BaseFragment;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.login.presenter.LoginFragmentPersenter;
import com.moonsister.tcjy.login.presenter.LoginFragmentPersenterImpl;
import com.moonsister.tcjy.login.view.LoginFragmentView;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.StringUtis;
import com.moonsister.tcjy.utils.UIUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by pc on 2016/6/13.
 */
public class LoginFragment extends BaseFragment implements LoginFragmentView {
    @Bind(R.id.iv_user_icon)//用户头像
    ImageView iv_user_icon;
    @Bind(R.id.et_phone_number)
    EditText etPhoneNumber;
    @Bind(R.id.et_password)
    EditText etPassword;
    @Bind(R.id.tv_forget_password)
    TextView tvForgetPassword;
    @Bind(R.id.submit_login)
    TextView submitLogin;
    private LoginFragmentPersenter persenter;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        persenter = new LoginFragmentPersenterImpl();
        persenter.attachView(this);
//        return UIUtils.inflateLayout(R.layout.fragment_login, container);
        return UIUtils.inflateLayout(R.layout.login_page);
    }

    @Override
    protected void initData() {
        ImageServerApi.showURLSamllImage(iv_user_icon, UserInfoManager.getInstance().getAvater());
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        ((BaseActivity) getActivity()).hideSoftInput();
        return super.onTouch(v, event);
    }

    @OnClick({R.id.tv_forget_password, R.id.submit_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_forget_password:
                ActivityUtils.startFindPasswordActivity();
                break;
            case R.id.submit_login:
                login();
                break;
        }
    }

    private void login() {
        String password = etPassword.getText().toString().trim();
        String phone = etPhoneNumber.getText().toString().trim();
        if (StringUtis.isEmpty(password)) {
            showToast(resources.getString(R.string.input_password) + resources.getString(R.string.not_empty));
            return;
        }
        if (StringUtis.isEmpty(phone)) {
            showToast(resources.getString(R.string.input_phone_number) + resources.getString(R.string.not_empty));
            return;
        }
        persenter.loginSubmit(phone, password);

    }


    @Override
    public void showLoading() {
        showProgressDialog();
    }

    @Override
    public void hideLoading() {
        hideProgressDialog();
    }

    @Override
    public void transfePageMsg(String msg) {
        showToast(msg);
    }

    @Override
    public void loginSuccss() {
        showToast(resources.getString(R.string.str_login) + resources.getString(R.string.success));
        RxBus.getInstance().send(Events.EventEnum.LOGIN_SUCCSS, null);
        UIUtils.sendDelayed(new Runnable() {
            @Override
            public void run() {
                getActivity().finish();
            }
        }, 1000);
    }
}
