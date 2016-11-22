package com.moonsister.tcjy.find.widget;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hickey.network.bean.RankBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.adapter.RankAdapter;
import com.moonsister.tcjy.base.BaseFragment;
import com.moonsister.tcjy.find.presenter.RankFragmentPresenter;
import com.moonsister.tcjy.find.presenter.RankFragmentPresenterImpl;
import com.moonsister.tcjy.find.view.RankFragmentView;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tcjy.widget.XListView;

import java.util.List;

import butterknife.Bind;

/**
 * Created by jb on 2016/8/3.
 */
public class RankFragment extends BaseFragment implements RankFragmentView {
    private RankFragmentPresenter presenter;
    @Bind(R.id.xlv)
    XListView xlv;
    private int type;
    private boolean isonLoadMore = true;
    private RankAdapter rankAdapter;

    public static RankFragment newInstance() {
        return new RankFragment();
    }

    public void setRankType(int type) {
        this.type = type;
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        presenter = new RankFragmentPresenterImpl();
        presenter.attachView(this);
        return UIUtils.inflateLayout(R.layout.fragme_rank);
    }

    @Override
    protected void initData() {
        xlv.setVerticalLinearLayoutManager();
        xlv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                isonLoadMore = false;
                presenter.refresh(type);
            }

            @Override
            public void onLoadMore() {
                isonLoadMore = true;
                presenter.loadMore(type);
            }
        });
        xlv.setRefreshing(true);
    }

    @Override
    public void setData(List<RankBean.DataBean> t) {
        if (t != null) {
            if (rankAdapter == null) {
                rankAdapter = new RankAdapter(t);
                rankAdapter.setRankFragmentView(this);
                xlv.setAdapter(rankAdapter);

            } else {
                if (!isonLoadMore)
                    rankAdapter.clean();
                rankAdapter.addListData(t);
            }
        }
    }

    @Override
    public void showLoading() {
        showProgressDialog();
    }

    @Override
    public void hideLoading() {
        if (xlv != null) {
            xlv.refreshComplete();
            xlv.loadMoreComplete();
        }
        hideProgressDialog();
    }

    @Override
    public void transfePageMsg(String msg) {
        showToast(msg);
    }
}
