package com.moonsister.tcjy.my.widget;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.adapter.MoneyTwoAdapter;
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
public class TwoFragment extends BaseFragment implements BalanceActivityView {
    @Bind(R.id.twofragment_listview)
    XListView onfragment_listview;
    private MoneyActivityPersenter mPresenter;
    private MoneyTwoAdapter mAdapter;
    int type;
    int page;
    List<BalanceBean.DataBean> data;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        type = 2;
        page = 1;
        mPresenter = new MoneyActivityPersenterImpl();
        mPresenter.attachView(this);
        return UIUtils.inflateLayout(R.layout.twofragment);
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
    public void moneybalance(BalanceBean balanceBean) {
        List<BalanceBean.DataBean> data = balanceBean.getData();
        if (balanceBean == null) {
//            xlv.setNoMore();
            onfragment_listview.loadMoreComplete();
            onfragment_listview.refreshComplete();
            return;
        }
        if (balanceBean.getData() == null || balanceBean.getData().size() == 0) {
//            xlv.setNoMore();
            onfragment_listview.loadMoreComplete();
            onfragment_listview.refreshComplete();
            return;
        }

        if (mAdapter == null) {
            mAdapter = new MoneyTwoAdapter(balanceBean.getData(), this);
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
}
