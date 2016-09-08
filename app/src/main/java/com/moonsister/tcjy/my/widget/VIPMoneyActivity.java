package com.moonsister.tcjy.my.widget;

import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.moonsister.tcjy.ImageServerApi;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.bean.PersonInfoDetail;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.main.presenter.BuyVipFragmentPersenter;
import com.moonsister.tcjy.main.presenter.BuyVipFragmentPersenterImpl;
import com.moonsister.tcjy.main.view.BuyVipFragmentView;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tcjy.widget.RoundedImageView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by x on 2016/8/24.
 */
public class VIPMoneyActivity extends BaseActivity implements BuyVipFragmentView {
    @Bind(R.id.riv_avater)
    RoundedImageView rivAvater;
    @Bind(R.id.iv_add_v)
    ImageView ivAddV;
    @Bind(R.id.tv_user_name)
    TextView tvUserName;
    @Bind(R.id.vip_combo_12_text)
    TextView vipCombo12Text;
    @Bind(R.id.iv_select_12)
    ImageView ivSelect12;
    @Bind(R.id.vip_combo_3_text)
    TextView vipCombo3Text;
    @Bind(R.id.iv_select_3)
    ImageView ivSelect3;
    @Bind(R.id.vip_combo_1_text)
    TextView vipCombo1Text;
    @Bind(R.id.iv_select_1)
    ImageView ivSelect1;
    @Bind(R.id.tv_buy)
    TextView tv_bug;
    private BuyVipFragmentPersenter persenter;
    private int select;

    @Override
    protected View setRootContentView() {
        persenter = new BuyVipFragmentPersenterImpl();
        persenter.attachView(this);
        return UIUtils.inflateLayout(R.layout.activity_vipmoney);
    }

    @Override
    protected void initView() {
        ImageServerApi.showURLSamllImage(rivAvater, UserInfoManager.getInstance().getAvater());
        int certificationStatus = UserInfoManager.getInstance().getCertificationStatus();
        ivAddV.setVisibility(certificationStatus == 1 ? View.VISIBLE : View.INVISIBLE);
        tvUserName.setText(UserInfoManager.getInstance().getNickeName());
        String string = getResources().getString(R.string.combo_type);
        vipCombo12Text.setText(Html.fromHtml(String.format(string, "12", 300)));
        vipCombo3Text.setText(Html.fromHtml(String.format(string, "3", 120)));
        vipCombo1Text.setText(Html.fromHtml(String.format(string, "1", 50)));
        selectBuy(ivSelect12);
    }

    private void selectBuy(View view) {
        ivSelect12.setVisibility(view == ivSelect12 ? View.VISIBLE : View.INVISIBLE);
        ivSelect3.setVisibility(view == ivSelect3 ? View.VISIBLE : View.INVISIBLE);
        ivSelect1.setVisibility(view == ivSelect1 ? View.VISIBLE : View.INVISIBLE);
    }

    @OnClick({R.id.layout_combo_12, R.id.layout_combo_3, R.id.layout_combo_1, R.id.tv_buy})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.layout_combo_12:
                selectBuy(ivSelect12);
                select = 12;
                break;

            case R.id.layout_combo_3:
                selectBuy(ivSelect3);
                select = 3;
                break;
            case R.id.layout_combo_1:
                select = 1;
                selectBuy(ivSelect1);
                break;
            case R.id.tv_buy:
                persenter.buyVIP(select, "");
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

    @Override
    public void buySuccess() {
        RxBus.getInstance().send(Events.EventEnum.BUY_VIP_SUCCESS, null);
        PersonInfoDetail memoryPersonInfoDetail = UserInfoManager.getInstance().getMemoryPersonInfoDetail();
        memoryPersonInfoDetail.setVipStatus(1);
        UserInfoManager.getInstance().saveMemoryInstance(memoryPersonInfoDetail);
        this.finish();

    }

    @Override
    public void typePay(int type, String phone) {

    }
}
