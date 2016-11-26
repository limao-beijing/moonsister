package com.moonsister.tcjy.main.widget;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hickey.network.ImageServerApi;
import com.hickey.network.bean.PersonInfoDetail;
import com.hickey.tool.base.BaseFragment;
import com.hickey.tool.lang.StringUtis;
import com.hickey.tool.widget.UIUtils;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.main.presenter.BuyVipFragmentPersenter;
import com.moonsister.tcjy.main.presenter.BuyVipFragmentPersenterImpl;
import com.moonsister.tcjy.main.view.BuyVipFragmentView;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.widget.RoundedImageView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by jb on 2016/8/12.
 */
public class BuyVipFragment extends BaseFragment implements BuyVipFragmentView {
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

    @Bind(R.id.vip_12_low_money_rule)
    TextView vip_12_low_money_rule;
    @Bind(R.id.vip_3_low_money_rule)
    TextView vip_3_low_money_rule;
    @Bind(R.id.et_input_phone)
    EditText et_input_phone;
    private int select = 12;
    private BuyVipFragmentPersenter persenter;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        persenter = new BuyVipFragmentPersenterImpl();
        persenter.attachView(this);
        return UIUtils.inflateLayout(R.layout.fragme_buy_vip);
//        return UIUtils.inflateLayout(R.layout.activity_vipmoney);
    }

    @Override
    protected void initData() {
        ImageServerApi.showURLSamllImage(rivAvater, UserInfoManager.getInstance().getAvater());
        int certificationStatus = UserInfoManager.getInstance().getCertificationStatus();
        ivAddV.setVisibility(certificationStatus == 1 ? View.VISIBLE : View.INVISIBLE);
        tvUserName.setText(UserInfoManager.getInstance().getNickeName());
        String string = getResources().getString(R.string.combo_type);
        vipCombo12Text.setText(Html.fromHtml(String.format(string, "12", 229)));
        vipCombo3Text.setText(Html.fromHtml(String.format(string, "3", 119)));
        vipCombo1Text.setText(Html.fromHtml(String.format(string, "1", 50)));

        String vip_rule_type = getResources().getString(R.string.vip_rule_type);
        vip_12_low_money_rule.setText(Html.fromHtml(String.format(vip_rule_type, "[返200话费]", "免费查看全站异性会员私密视频，私密图片，私密语音，手机号，微信号等私密资料。")));
        vip_3_low_money_rule.setText(Html.fromHtml(String.format(vip_rule_type, "[返100话费]", "免费查看全站异性会员私密视频，私密图片，私密语音，手机号，微信号。")));
        selectBuy(ivSelect12);
    }

    public static Fragment newInstance() {
        return new BuyVipFragment();
    }

    private void selectBuy(View view) {
        ivSelect12.setVisibility(view == ivSelect12 ? View.VISIBLE : View.INVISIBLE);
        ivSelect3.setVisibility(view == ivSelect3 ? View.VISIBLE : View.INVISIBLE);
        ivSelect1.setVisibility(view == ivSelect1 ? View.VISIBLE : View.INVISIBLE);
    }

    @OnClick({R.id.layout_combo_12, R.id.layout_combo_3, R.id.layout_combo_1, R.id.tv_buy, R.id.vip_12_low_money_rule, R.id.vip_3_low_money_rule})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.vip_12_low_money_rule:
            case R.id.layout_combo_12:
                selectBuy(ivSelect12);
                select = 12;
                break;
            case R.id.vip_3_low_money_rule:
            case R.id.layout_combo_3:
                selectBuy(ivSelect3);
                select = 3;
                break;
            case R.id.layout_combo_1:
                select = 1;
                selectBuy(ivSelect1);
                break;
            case R.id.tv_buy:
                String phone = et_input_phone.getText().toString();
                if (StringUtis.isEmpty(phone)) {
                    showToast(UIUtils.getStringRes(R.string.input_phone_number));
                    return;
                }
                if (phone.length() < 11) {
                    showToast(UIUtils.getStringRes(R.string.input_phone_number) + UIUtils.getStringRes(R.string.error));
                    return;
                }
                persenter.buyVIP(select, phone);
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
        getActivity().finish();

    }

    @Override
    public void typePay(int type, String phone) {

    }
}
