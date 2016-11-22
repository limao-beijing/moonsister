package com.moonsister.tcjy.login.widget;

import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.base.BaseFragment;
import com.moonsister.tcjy.login.presenter.LoginMainPresenter;
import com.moonsister.tcjy.login.presenter.LoginMainPresenterImpl;
import com.moonsister.tcjy.login.view.LoginMainView;
import com.moonsister.tcjy.utils.FragmentUtils;
import com.moonsister.tcjy.utils.UIUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by pc on 2016/6/13.
 */
public class LoginMainActivity extends BaseActivity implements LoginMainView {

    @Bind(R.id.frameLyout_login_main_content)
    FrameLayout frameLyoutHomeContent;
    @Bind(R.id.tv_navigation_login)//登录按钮
    TextView tv_navigation_login;
    @Bind(R.id.tv_navigation_register)//注册按钮
    TextView tv_navigation_register;

    private LoginMainPresenter presenter;
    private FragmentManager fragmentManager;
    protected String regiterCode;

    @Override
    protected void initView() {
        fragmentManager = getSupportFragmentManager();
        presenter.swicthNavigation(R.id.tv_navigation_login);
    }

    @Override
    public boolean isAddActivity() {
        return false;
    }

    @Override
    protected View setRootContentView() {
        presenter = new LoginMainPresenterImpl(this);
        return UIUtils.inflateLayout(R.layout.activity_login_main);
//        return UIUtils.inflateLayout(R.layout.login_page);
    }

    private BaseFragment currentFragment, loginFragment, regiterFragment;
//    private BaseFragment currentFragment, loginFragment;

    @Override
    public void swicth2Login() {
        if (loginFragment == null) {
            loginFragment = LoginFragment.newInstance();
        }
        switchNatvigationSelect(loginFragment);
        FragmentUtils.swichReplaceFramgent(fragmentManager, R.id.frameLyout_login_main_content, loginFragment);
    }

    @Override
    public void swicth2Register() {
        if (regiterFragment == null) {
            regiterFragment = RegiterFragment.newInstance();
        }
        switchNatvigationSelect(regiterFragment);
        FragmentUtils.swichReplaceFramgent(fragmentManager, R.id.frameLyout_login_main_content, regiterFragment);
    }


    @OnClick({R.id.tv_navigation_login, R.id.tv_navigation_register})
    public void onClick(View view) {
        presenter.swicthNavigation(view.getId());
    }

    private void switchNatvigationSelect(BaseFragment fragment) {
        if (fragment == null)
            return;
        tv_navigation_login.setSelected(fragment == loginFragment);
        tv_navigation_register.setSelected(fragment == regiterFragment);
    }
}
