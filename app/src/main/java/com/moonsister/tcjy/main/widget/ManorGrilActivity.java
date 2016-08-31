package com.moonsister.tcjy.main.widget;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.bean.PersonInfoDetail;
import com.moonsister.tcjy.login.widget.LoginMainActivity;
import com.moonsister.tcjy.main.presenter.ManorFrilActivityPresenter;
import com.moonsister.tcjy.main.presenter.ManorFrilActivityPresenterImpl;
import com.moonsister.tcjy.main.view.ManorGrilActivityView;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.my.widget.InsertActivity;
import com.moonsister.tcjy.utils.UIUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by x on 2016/8/30.
 */
public class ManorGrilActivity extends BaseActivity implements ManorGrilActivityView{
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
        return UIUtils.inflateLayout(R.layout.manorgril);
    }

    @Override
    protected void initView() {
        persenter=new ManorFrilActivityPresenterImpl();
        persenter.attachView(this);
        persenter.regOne(1);
    }

   @OnClick({R.id.imageview_man,R.id.imageview_gril,R.id.textview_login})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageview_man:

                persenter.regOne(1);
                getReg("");
                break;
            case R.id.imageview_gril:
//                Intent in=new Intent(ManorGrilActivity.this, InsertActivity.class);
                persenter.regOne(2);
                getReg("");
                break;
            case R.id.textview_login:
                Intent i=new Intent(ManorGrilActivity.this, LoginMainActivity.class);
                startActivity(i);
                break;
        }
    }

    @Override
    public void getReg(String s) {
//        RxBus.getInstance().send(Events.EventEnum.BUY_VIP_SUCCESS, null);
        PersonInfoDetail memoryPersonInfoDetail = UserInfoManager.getInstance().getMemoryPersonInfoDetail();//获得对象
        memoryPersonInfoDetail.setAuthcode(s);//保存值
        UserInfoManager.getInstance().saveMemoryInstance(memoryPersonInfoDetail);

        Intent intent=new Intent(ManorGrilActivity.this, InsertActivity.class);
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
