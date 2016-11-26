package com.moonsister.tcjy.my.widget;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hickey.network.bean.WithdRawDepositBean;
import com.hickey.tool.activity.FragmentUtils;
import com.hickey.tool.base.BaseActivity;
import com.hickey.tool.widget.UIUtils;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.my.persenter.WithdRawDepositPresenter;
import com.moonsister.tcjy.my.persenter.WithdRawDepositPresenterImpl;
import com.moonsister.tcjy.my.view.WithdRawDepositView;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.trello.rxlifecycle.ActivityEvent;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by x on 2016/8/26.
 */
public class MoneyActivity extends BaseActivity implements WithdRawDepositView {
    @Bind(R.id.talk)//说明
            TextView talk;
    @Bind(R.id.see_talk)//冻结状态，如何提取
            TextView see_talk;
    @Bind(R.id.balance)//余额金额
            TextView balance;
    @Bind(R.id.withdraw)//提现按钮
            Button withdraw;
    @Bind(R.id.recharge)//充值按钮
            Button recharge;

    @Bind(R.id.id_chat_tv)//收入
            TextView id_chat_tv;
    @Bind(R.id.id_friend_tv)//支出
            TextView id_friend_tv;
    @Bind(R.id.id_tab_line_iv)//游标
            ImageView id_tab_line_iv;
    @Bind(R.id.image_back)
    ImageView image_back;
    @Bind(R.id.id_tab_line_iv1)
    ImageView id_tab_line_iv1;
    WithdRawDepositPresenter presenter;
    @Bind(R.id.titile_money)//认证费
            TextView titile_money;
    private String withdraw_money;

    @Override
    protected View setRootContentView() {
        presenter = new WithdRawDepositPresenterImpl();
        presenter.attachView(this);
        return UIUtils.inflateLayout(R.layout.activity_money);
    }


    @Override
    protected void initView() {
        presenter.loadEnableMoney();
        setRx();
        FragmentUtils.swichReplaceFramgent(getSupportFragmentManager(), R.id.fragmentlayout, new OneFragment());

    }

    private void setRx() {
        RxBus.with(this)
                .setEndEvent(ActivityEvent.DESTROY)
                .setEvent(Events.EventEnum.MONEY_CHANGE)
                .onNext(events -> {
                    presenter.loadEnableMoney();
                })
                .create();
    }

    @OnClick({R.id.id_chat_tv, R.id.id_friend_tv, R.id.withdraw, R.id.recharge, R.id.image_back, R.id.see_talk, R.id.talk})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_chat_tv:
                FragmentUtils.swichReplaceFramgent(getSupportFragmentManager(), R.id.fragmentlayout, new OneFragment());
                id_chat_tv.setTextColor(getResources().getColor(R.color.text_follow_color));

                id_friend_tv.setTextColor(getResources().getColor(R.color.grid_line));
                id_tab_line_iv.setVisibility(View.VISIBLE);
                id_tab_line_iv1.setVisibility(View.INVISIBLE);
                break;
            case R.id.id_friend_tv:
                FragmentUtils.swichReplaceFramgent(getSupportFragmentManager(), R.id.fragmentlayout, new TwoFragment());
                id_chat_tv.setTextColor(getResources().getColor(R.color.grid_line));

                id_friend_tv.setTextColor(getResources().getColor(R.color.text_follow_color));
                id_tab_line_iv.setVisibility(View.INVISIBLE);
                id_tab_line_iv1.setVisibility(View.VISIBLE);
                break;
            case R.id.withdraw:
                ActivityUtils.startGetMoneyActivity(withdraw_money);
                break;
            case R.id.recharge:
                ActivityUtils.startRechargeActivity();
                break;
            case R.id.image_back:
                MoneyActivity.this.finish();
                break;
            case R.id.see_talk:
                ActivityUtils.startRuleActivity();
                break;
            case R.id.talk:
                Intent intent = new Intent(MoneyActivity.this, TalkActivity.class);
                startActivity(intent);
                break;

        }
    }

    @Override
    public void setloadEnableMoney(WithdRawDepositBean str) {
        withdraw_money = str.getData().getWithdraw_money();
        balance.setText(str.getData().getLast_money());
        titile_money.setText(str.getData().getFrozen_money());
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
