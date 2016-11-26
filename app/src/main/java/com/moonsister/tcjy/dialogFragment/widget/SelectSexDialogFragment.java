package com.moonsister.tcjy.dialogFragment.widget;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hickey.network.bean.PersonInfoDetail;
import com.hickey.tool.base.BaseDialogFragment;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.login.widget.LoginMainActivity;
import com.moonsister.tcjy.main.presenter.ManorFrilActivityPresenter;
import com.moonsister.tcjy.main.presenter.ManorFrilActivityPresenterImpl;
import com.moonsister.tcjy.main.view.ManorGrilActivityView;
import com.moonsister.tcjy.main.widget.MainActivity;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.hickey.tool.ConfigUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by jb on 2016/11/2.
 */

public class SelectSexDialogFragment extends BaseDialogFragment implements ManorGrilActivityView {

    @Bind(R.id.imageview_man)//选择男图片
            ImageView imageview_man;
    @Bind(R.id.imageview_gril)//选择女图片
            ImageView imageview_gril;
    ManorFrilActivityPresenter persenter;
    @Bind(R.id.tv_boy)
    TextView mTvBoy;
    @Bind(R.id.tv_girls)
    TextView mTvGirls;


    @Override
    protected void initData() {
        persenter = new ManorFrilActivityPresenterImpl();//绑定
        persenter.attachView(this);
    }


    public static BaseDialogFragment newInstance() {
        return new SelectSexDialogFragment();
    }

    @Override
    protected int initViewId() {
        return R.layout.df_select_sex;
    }


    @Override
    public void getReg(String authcode) {
//        RxBus.getInstance().send(Events.EventEnum.BUY_VIP_SUCCESS, null);
        PersonInfoDetail memoryPersonInfoDetail = UserInfoManager.getInstance().getMemoryPersonInfoDetail();//获得对象
        memoryPersonInfoDetail.setAuthcode(authcode);//保存值
        memoryPersonInfoDetail.setLogin(true);
        UserInfoManager.getInstance().saveMemoryInstance(memoryPersonInfoDetail);
        RxBus.getInstance().send(Events.EventEnum.LOGIN_SUCCSS, null);
        dismissDialogFragment();
        Activity context = ConfigUtils.getInstance().getActivityContext();
        if (context instanceof MainActivity) {
            ((MainActivity) context).bindPhoneDialog();
        }

    }

    @OnClick({R.id.imageview_man, R.id.imageview_gril, R.id.textview_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageview_man:
                mTvBoy.setSelected(true);
                mTvGirls.setSelected(false);
                persenter.regOne(1);

                break;
            case R.id.imageview_gril:
                mTvBoy.setSelected(false);
                mTvGirls.setSelected(true);
                persenter.regOne(2);

                break;
            case R.id.textview_login:
                Intent i = new Intent(mActivity, LoginMainActivity.class);
                startActivity(i);
                dismissDialogFragment();
                break;
        }
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
