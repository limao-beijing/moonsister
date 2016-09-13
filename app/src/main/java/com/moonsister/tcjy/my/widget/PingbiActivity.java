package com.moonsister.tcjy.my.widget;

import android.view.View;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.bean.PingbiBean;
import com.moonsister.tcjy.my.persenter.PingbiActivityPersenter;
import com.moonsister.tcjy.my.persenter.PingbiActivityPersenterImpl;
import com.moonsister.tcjy.my.view.PingbiView;
import com.moonsister.tcjy.utils.UIUtils;

/**
 * Created by x on 2016/9/14.
 */
public class PingbiActivity extends BaseActivity implements PingbiView{
    PingbiActivityPersenter persenter;
    @Override
    protected View setRootContentView() {
        return UIUtils.inflateLayout(R.layout.pingbiactivity);
    }

    @Override
    protected void initView() {
        String page="1";
        persenter=new PingbiActivityPersenterImpl();
        persenter.attachView(this);
        persenter.submit(page);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void transfePageMsg(String msg) {

    }

    @Override
    public void success(PingbiBean pingbiBean) {

    }
}
