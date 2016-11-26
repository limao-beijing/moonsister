package com.moonsister.tcjy.login.widget;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hickey.network.ImageServerApi;
import com.hickey.tool.base.BaseFragment;
import com.hickey.tool.widget.UIUtils;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.login.presenter.RegiterDataFragmentPresenter;
import com.moonsister.tcjy.login.presenter.RegiterDataFragmentPresenterImpl;
import com.moonsister.tcjy.login.view.RegiterDataFragmentView;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.LogUtils;
import com.trello.rxlifecycle.FragmentEvent;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by pc on 2016/6/13.
 */
public class RegiterDataFragment extends BaseFragment implements RegiterDataFragmentView {
    @Bind(R.id.add_icom)
    ImageView addIcom;
    @Bind(R.id.tv_boy)
    TextView tvBoy;
    @Bind(R.id.girls)
    TextView girls;
    @Bind(R.id.tv_pwd)
    TextView tvPwd;
    @Bind(R.id.edit_password)
    EditText editPassword;
    private RegiterDataFragmentPresenter presenter;
    private String sex = "1";
    private String facePath;

    public static RegiterDataFragment newInstance() {
        return new RegiterDataFragment();
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        presenter = new RegiterDataFragmentPresenterImpl();
        presenter.attachView(this);
        return UIUtils.inflateLayout(R.layout.fragment_regiter_data, container);
    }

    @Override
    protected void initData() {
        tvBoy.setSelected(true);
    }

    @OnClick({R.id.add_icom, R.id.tv_boy, R.id.girls, R.id.id_tv_load})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_icom:

                ActivityUtils.startActivity(SelectPicPopupActivity.class);
                RxBus.with(this)
                        .setEvent(Events.EventEnum.GET_PHOTO)
                        .setEndEvent(FragmentEvent.DESTROY)
                        .onNext((events) -> {
                            String message = (String) events.message;
                            LogUtils.e(RegiterDataFragment.class, "pic_path : " + message);

                            ImageServerApi.showURLImage(addIcom, message);
                            presenter.upLoadIcon(message);
                        }).create();

                break;
            case R.id.tv_boy:
                tvBoy.setSelected(true);
                girls.setSelected(false);
                sex = "1";
                break;
            case R.id.girls:
                sex = "2";
                tvBoy.setSelected(false);
                girls.setSelected(true);
                break;
            case R.id.id_tv_load:
                String pwd = editPassword.getText().toString().trim();
                if (pwd == null || pwd.length() == 0) {
                    showToast(resources.getString(R.string.password) + resources.getString(R.string.not_empty));
                    return;
                }
                if (pwd.length() > 16 || pwd.length() < 0) {
                    showToast(resources.getString(R.string.password) + resources.getString(R.string.framat) + resources.getString(R.string.error));
                    return;
                }
                presenter.login(facePath, sex, pwd, ((LoginMainActivity) getActivity()).regiterCode);
                break;
        }
    }


    @Override
    public void navigationNext() {
        Activity activity = getActivity();
        if (activity instanceof LoginMainActivity)
            ((LoginMainActivity)activity).swicth2Login();
    }


    @Override
    public void uploadSuccess(String path) {
        this.facePath = path;
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
