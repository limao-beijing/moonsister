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
import com.moonsister.tcjy.widget.XListView;

import java.util.List;

import butterknife.Bind;

/**
 * Created by x on 2016/8/26.
 */
public class OneFragment extends BaseFragment implements BalanceActivityView{
    @Bind(R.id.onfragment_listview)
    ListView onfragment_listview;
    private MoneyActivityPersenter mPresenter;
    private MoneyAdapter mAdapter;
    int type;
    int page;
    int pagesize;
    List<BalanceBean.DataBean> data;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mPresenter = new MoneyActivityPersenterImpl();
        mPresenter.attachView(this);
        type=1;
        mPresenter.moneyba(type,1,10);
        return UIUtils.inflateLayout(R.layout.onefragment);
    }

    @Override
    protected void initData() {

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

    @Override
    public void moneybalance(BalanceBean balanceBean) {
        data = balanceBean.getData();
        mAdapter=new MoneyAdapter(getActivity(),data);
        onfragment_listview.setAdapter(mAdapter);

    }


}
