package com.moonsister.tcjy.my.widget;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.adapter.MoneyAdapter;
import com.moonsister.tcjy.base.BaseFragment;
import com.moonsister.tcjy.bean.BalanceBean;
import com.moonsister.tcjy.my.persenter.MoneyActivityPersenter;
import com.moonsister.tcjy.my.persenter.MoneyActivityPersenterImpl;
import com.moonsister.tcjy.my.view.BalanceActivityView;
import com.moonsister.tcjy.utils.UIUtils;

import java.util.List;

import butterknife.Bind;

/**
 * Created by x on 2016/8/26.
 */
public class TwoFragment extends BaseFragment implements BalanceActivityView {
    @Bind(R.id.twofragment_listview)
    ListView onfragment_listview;
    private MoneyActivityPersenter mPresenter;
    private MoneyAdapter mAdapter;
    int type;
    int page;
    int pagesize;
    String uid;
    List<BalanceBean.DataBean> data;
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        type=2;
        mPresenter = new MoneyActivityPersenterImpl();
        mPresenter.attachView(this);
        return UIUtils.inflateLayout(R.layout.twofragment);
    }

    @Override
    protected void initData() {
        mPresenter.moneyba(type,page,pagesize);
    }

    @Override
    public void moneybalance(BalanceBean balanceBean) {
        data = balanceBean.getData();
        mAdapter=new MoneyAdapter(getActivity(),data);
        onfragment_listview.setAdapter(mAdapter);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void transfePageMsg(String msg) {
        showToast(msg);
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
}
