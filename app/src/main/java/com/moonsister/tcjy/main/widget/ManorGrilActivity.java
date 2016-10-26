package com.moonsister.tcjy.main.widget;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.bean.PersonInfoDetail;
import com.moonsister.tcjy.login.widget.LoginMainActivity;
import com.moonsister.tcjy.login.widget.RegActivity;
import com.moonsister.tcjy.main.presenter.ManorFrilActivityPresenter;
import com.moonsister.tcjy.main.presenter.ManorFrilActivityPresenterImpl;
import com.moonsister.tcjy.main.view.ManorGrilActivityView;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.my.widget.InsertActivity;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.UIUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by x on 2016/8/30.
 */
public class ManorGrilActivity extends BaseActivity implements ManorGrilActivityView {
    @Bind(R.id.imageview_man)//选择男图片
            ImageView imageview_man;
    @Bind(R.id.imageview_gril)//选择女图片
            ImageView imageview_gril;
    @Bind(R.id.textview_login)//直接登录
            TextView textview_login;
    ManorFrilActivityPresenter persenter;
    int sex;
    String apiVersion;

    @Override
    protected View setRootContentView() {
        SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
        boolean isFirst = sp.getBoolean("isFirst", true);
        if (isFirst == true) {
            SharedPreferences.Editor edit = sp.edit();
            edit.putBoolean("isFirst", false);
            //应用首次启动
            LayoutInflater.from(this).inflate(R.layout.manorgril_fen, null);
        } else {
            //应用非首次启动
            ActivityUtils.startLoginMainActivity();
        }
        return UIUtils.inflateLayout(R.layout.manorgril_fen);
    }

    @Override
    protected void initView() {
        persenter = new ManorFrilActivityPresenterImpl();//绑定
        persenter.attachView(this);

//        persenter.regOne(1);
    }

    @OnClick({R.id.imageview_man, R.id.imageview_gril, R.id.textview_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageview_man:

                persenter.regOne(1);

                break;
            case R.id.imageview_gril:

                persenter.regOne(2);

                break;
            case R.id.textview_login:
                Intent i = new Intent(ManorGrilActivity.this, LoginMainActivity.class);
                startActivity(i);
                finish();
                break;
        }
    }

    @Override
    public void getReg(String authcode) {
//        RxBus.getInstance().send(Events.EventEnum.BUY_VIP_SUCCESS, null);


        Intent intent = new Intent(ManorGrilActivity.this, RegActivity.class);
        startActivity(intent);
        this.finish();
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
}
