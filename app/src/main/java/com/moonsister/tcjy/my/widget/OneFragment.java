package com.moonsister.tcjy.my.widget;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hickey.network.bean.BalanceBean;
import com.hickey.tool.base.BaseFragment;
import com.hickey.tool.widget.UIUtils;
import com.hickey.tool.widget.XListView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.adapter.MoneyAdapter;
import com.moonsister.tcjy.my.persenter.MoneyActivityPersenter;
import com.moonsister.tcjy.my.persenter.MoneyActivityPersenterImpl;
import com.moonsister.tcjy.my.view.BalanceActivityView;

import java.util.List;

import butterknife.Bind;

/**
 * Created by x on 2016/8/26.
 */
public class OneFragment extends BaseFragment implements BalanceActivityView {
    @Bind(R.id.onfragment_xlistview)
    XListView onfragment_listview;
    private MoneyActivityPersenter mPresenter;
    public MoneyAdapter mAdapter;
    int type;
    int page;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        type = 1;
        page = 1;
        mPresenter = new MoneyActivityPersenterImpl();
        mPresenter.attachView(this);
        return UIUtils.inflateLayout(R.layout.onefragment);
    }

    @Override
    protected void initData() {

        onfragment_listview.setVerticalLinearLayoutManager();
        onfragment_listview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                mPresenter.moneyba(type, page, 10);
            }

            @Override
            public void onLoadMore() {
                mPresenter.moneyba(type, page, 10);
            }
        });
        mPresenter.moneyba(type, page, 10);
    }

    @Override
    public void showLoading() {
        showProgressDialog();
    }

    @Override
    public void hideLoading() {
        hideProgressDialog();
    }

    public void transfePageMsg(String msg) {
        showToast(msg);
    }

    @Override
    public void moneybalance(BalanceBean balanceBean) {
        List<BalanceBean.DataBean> data = balanceBean.getData();
        if (balanceBean.getData() == null || balanceBean.getData().size() == 0) {
//            xlv.setNoMore();
            onfragment_listview.loadMoreComplete();
            onfragment_listview.refreshComplete();
            return;
        }

        if (mAdapter == null) {
            mAdapter = new MoneyAdapter(balanceBean.getData(), this);
            mAdapter.setPageType(type);
            if (onfragment_listview != null)
                onfragment_listview.setAdapter(mAdapter);
        } else {
            if (page == 1)
                mAdapter.clean();
            mAdapter.addListData(balanceBean.getData());
            mAdapter.onRefresh();
        }
        onfragment_listview.loadMoreComplete();
        onfragment_listview.refreshComplete();
        page++;
    }

}
