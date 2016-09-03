package com.moonsister.tcjy.my.widget;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.bean.GetMoneyBean;
import com.moonsister.tcjy.bean.WithdRawDepositBean;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.my.persenter.GetMoneyPersenter;
import com.moonsister.tcjy.my.persenter.GetMoneyPersenterImpl;
import com.moonsister.tcjy.my.persenter.WithdRawDepositPresenter;
import com.moonsister.tcjy.my.persenter.WithdRawDepositPresenterImpl;
import com.moonsister.tcjy.my.view.GetMoneyView;
import com.moonsister.tcjy.my.view.WithdRawDepositView;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.FragmentUtils;
import com.moonsister.tcjy.utils.UIUtils;
import com.trello.rxlifecycle.ActivityEvent;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

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
    OneFragment oneFragment;
    TwoFragment twoFragment;
    Fragment currentFragment;
    WithdRawDepositPresenter presenter;

    @Override
    protected View setRootContentView() {
        presenter = new WithdRawDepositPresenterImpl();
        presenter.attachView(this);
        return UIUtils.inflateLayout(R.layout.activity_money);
    }


    @Override
    protected void initView() {

        oneFragment=new OneFragment();
        oneFragment.setUid(getIntent().getStringExtra("uid"));
        twoFragment=new TwoFragment();
        FragmentUtils.switchHideFragment(getSupportFragmentManager(),R.id.fragmentlayout,currentFragment,oneFragment);
        presenter.loadEnableMoney();
        setRx();
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

    @OnClick({R.id.id_chat_tv,R.id.id_friend_tv,R.id.withdraw,R.id.recharge,R.id.image_back})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.id_chat_tv:
                FragmentUtils.switchHideFragment(getSupportFragmentManager(),R.id.fragmentlayout,twoFragment,oneFragment);
                id_chat_tv.setTextColor(getResources().getColor(R.color.text_follow_color));

                id_friend_tv.setTextColor(getResources().getColor(R.color.grid_line));
                id_tab_line_iv.setVisibility(View.VISIBLE);
                id_tab_line_iv1.setVisibility(View.INVISIBLE);
                break;
            case R.id.id_friend_tv:
                FragmentUtils.switchHideFragment(getSupportFragmentManager(),R.id.fragmentlayout,oneFragment,twoFragment);
                id_chat_tv.setTextColor(getResources().getColor(R.color.grid_line));

                id_friend_tv.setTextColor(getResources().getColor(R.color.text_follow_color));
                id_tab_line_iv.setVisibility(View.INVISIBLE);
                id_tab_line_iv1.setVisibility(View.VISIBLE);
                break;
            case R.id.withdraw:
                ActivityUtils.startGetMoneyActivity();
                break;
            case R.id.recharge:
                ActivityUtils.startRechargeActivity();
                break;
            case R.id.image_back:
                MoneyActivity.this.finish();
                break;

        }
    }

    @Override
    public void setloadEnableMoney(WithdRawDepositBean str) {
//        String s=str.getData().getWithdraw_money();
//        if(s==null){
//            balance.setText("0");
//        }else{
//            balance.setText(s);
//        }
        balance.setText(str.getData().getWithdraw_money());

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
