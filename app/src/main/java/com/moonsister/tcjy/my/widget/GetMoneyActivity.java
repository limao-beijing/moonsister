package com.moonsister.tcjy.my.widget;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.moonsister.tcjy.ImageServerApi;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.bean.GetMoneyBean;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.my.persenter.GetMoneyPersenter;
import com.moonsister.tcjy.my.persenter.GetMoneyPersenterImpl;
import com.moonsister.tcjy.my.view.GetMoneyView;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.StringUtis;
import com.moonsister.tcjy.utils.UIUtils;
import com.trello.rxlifecycle.ActivityEvent;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 提现到银行卡
 * Created by jb on 2016/6/27.
 */
public class GetMoneyActivity extends BaseActivity implements GetMoneyView {
    @Bind(R.id.iv_bank_logo)
    ImageView ivBankLogo;
    @Bind(R.id.tv_bank_name)
    TextView tvBankName;
    @Bind(R.id.tv_bank_number)
    TextView tvBankNumber;
    @Bind(R.id.et_input_money)
    EditText etInputMoney;
    @Bind(R.id.tv_add_card)
    TextView tv_add_card;
    @Bind(R.id.layout_swicth_card)
    RelativeLayout layout_swicth_card;
    private GetMoneyPersenter persenter;
    private String number;
    private String cardType;

    @Override
    protected View setRootContentView() {
        persenter = new GetMoneyPersenterImpl();
        persenter.attachView(this);
        return UIUtils.inflateLayout(R.layout.activity_ti_xian);
    }

    @Override
    protected String initTitleName() {
        return UIUtils.getStringRes(R.string.withdraw_deposit);
    }

    @Override
    protected void initView() {
        persenter.loadbasicInfo();
    }


    @OnClick({R.id.layout_swicth_card, R.id.tv_add_alipay, R.id.tv_add_bank_card, R.id.tv_sure})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_swicth_card:
                ActivityUtils.startSwitchCardActivity(number, cardType);
                setSwicthCardRxbus();
                break;
            case R.id.tv_add_alipay:
                //type卡类型 1银行卡2支付宝3微信
                ActivityUtils.startAddCardActivity(UIUtils.getStringRes(R.string.alipay), "2");
                break;
            case R.id.tv_add_bank_card:
                ActivityUtils.startAddCardActivity(UIUtils.getStringRes(R.string.winxinpay), "3");
                break;
            case R.id.tv_sure:
                if (StringUtis.isEmpty(number)) {
                    showToast(UIUtils.getStringRes(R.string.switch_card));
                    return;
                }
                String money = etInputMoney.getText().toString().trim();
                if (money.contains("\\.")) {
                    showToast(UIUtils.getStringRes(R.string.money_get));

                    return;
                }
                int i = StringUtis.string2Int(money);
                if (i < 200) {
                    showToast(UIUtils.getStringRes(R.string.money_number_more_200));
                    return;
                }
                persenter.PaySubmit(number, i);
                break;
        }
    }

    private void setSwicthCardRxbus() {
        RxBus.with(this)
                .setEndEvent(ActivityEvent.DESTROY)
                .setEvent(Events.EventEnum.CLICK_SWITCH_CARD_POSITION)
                .onNext(events -> {
                    if (events != null && events.message instanceof GetMoneyBean) {
                        setBasicInfo((GetMoneyBean) events.message);
                    }
                })
                .create();

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
    public void setBasicInfo(GetMoneyBean getMoneyBean) {
        GetMoneyBean.DataBean data = getMoneyBean.getData();
        if (data == null) {
            tv_add_card.setVisibility(View.VISIBLE);
            layout_swicth_card.setClickable(false);
            layout_swicth_card.setFocusable(false);
            setAddRx();
            return;
        }
        layout_swicth_card.setClickable(true);
        layout_swicth_card.setFocusable(true);
        tv_add_card.setVisibility(View.GONE);
        tvBankName.setText(data.getBank_name());
        number = data.getBank_no();
        cardType = data.getType();
        tvBankNumber.setText(number);
        ImageServerApi.showURLSamllImage(ivBankLogo, data.getLogo());
    }

    @Override
    public void pageFinish() {
        finish();
    }

    public void setAddRx() {
        RxBus.with(this)
                .setEndEvent(ActivityEvent.DESTROY)
                .setEvent(Events.EventEnum.CARD_CHANGE)
                .onNext(events -> {
                    persenter.loadbasicInfo();
                })
                .create();
    }
}
