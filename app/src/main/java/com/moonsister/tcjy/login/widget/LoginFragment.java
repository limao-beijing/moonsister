package com.moonsister.tcjy.login.widget;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hickey.network.ImageServerApi;
import com.hickey.tool.lang.StringUtis;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.base.BaseFragment;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.login.presenter.LoginFragmentPersenter;
import com.moonsister.tcjy.login.presenter.LoginFragmentPersenterImpl;
import com.moonsister.tcjy.login.view.LoginFragmentView;
import com.moonsister.tcjy.main.widget.MainActivity;
import com.moonsister.tcjy.manager.IMManager;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.ConfigUtils;
import com.moonsister.tcjy.utils.UIUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by pc on 2016/6/13.
 */
public class LoginFragment extends BaseFragment implements LoginFragmentView {
    @Bind(R.id.iv_user_icon)//用户头像
            ImageView iv_user_icon;
    @Bind(R.id.et_phone_number)//登录用的手机号
            EditText etPhoneNumber;
    @Bind(R.id.et_password)//登录用的密码
            EditText etPassword;
    @Bind(R.id.tv_forget_password)//忘记密码按钮
            TextView tvForgetPassword;
    @Bind(R.id.submit_login)//登录按钮
            TextView submitLogin;
    @Bind(R.id.reg_new_people)//注册新用户
            TextView reg_new_people;
    @Bind(R.id.login_page_yellow)//删除按钮
            ImageView login_page_yellow;
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

    @OnClick({R.id.tv_forget_password, R.id.submit_login, R.id.reg_new_people, R.id.login_page_yellow})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_forget_password://找回密码
                ActivityUtils.startFindPasswordActivity();
                break;
            case R.id.submit_login://登录
                login();
                break;
            case R.id.reg_new_people://注册新用户
//                Intent intent = new Intent(getActivity(), ManorGrilActivity.class);
//                startActivity(intent);

                getActivity().finish();
//                Activity context = ConfigUtils.getInstance().getActivityContext();
//                if (context instanceof MainActivity) {
//                    ((MainActivity) context).shwoSelectSexDialog();
//                }

                break;
            case R.id.login_page_yellow:
                String s = etPassword.getText().toString();
                if (s.length() == 0) {

                } else {
                    etPassword.setText(s.substring(0, s.length() - 1));//将字符取的地方减一位需要减2.“sss”.len
                }
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
        RxBus.getInstance().send(Events.EventEnum.LOGIN_SUCCSS, null);
        UIUtils.sendDelayed(new Runnable() {
            @Override
            public void run() {
                Activity context = ConfigUtils.getInstance().getActivityContext();
                if (context == null) {
                    ActivityUtils.startActivity(MainActivity.class);
                }
                getActivity().finish();
            }
        }, 1000);
        IMManager.getInstance().cleanAllChatMsg();
    }
}
