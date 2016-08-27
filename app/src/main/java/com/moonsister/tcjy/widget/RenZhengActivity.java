package com.moonsister.tcjy.widget;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.moonsister.tcjy.ImageServerApi;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.my.persenter.RZSecondPersenter;
import com.moonsister.tcjy.my.persenter.RZSecondPersenterImpl;
import com.moonsister.tcjy.my.view.RZSecondView;
import com.moonsister.tcjy.utils.UIUtils;

import butterknife.Bind;

/**
 * Created by x on 2016/8/25.
 */
public class RenZhengActivity extends BaseActivity implements RZSecondView{
    @Bind(R.id.riv_avater)//用户头像
    ImageView riv_avater;
    @Bind(R.id.vip_id)//会员ID
    TextView vip_id;
    @Bind(R.id.phone)//绑定手机号
    TextView phone;
    private RZSecondPersenter persenter;
    @Override
    protected View setRootContentView() {
        persenter = new RZSecondPersenterImpl();
        persenter.attachView(this);
        return UIUtils.inflateLayout(R.layout.renzhengactivity);
    }

    @Override
    protected void initView() {
        ImageServerApi.showURLSamllImage(riv_avater, UserInfoManager.getInstance().getAvater());
        vip_id.setText(UserInfoManager.getInstance().getUid());

//        phone.setText(UserInfoManager.getInstance().);
    }

    @Override
    public void success() {

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
}
