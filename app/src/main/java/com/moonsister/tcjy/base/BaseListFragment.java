package com.moonsister.tcjy.base;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.bean.BaseDataBean;
import com.moonsister.tcjy.widget.XListView;

import java.util.List;

/**
 * Created by jb on 2016/9/22.
 */
public abstract class BaseListFragment<T extends BaseRecyclerViewAdapter, M extends BaseDataBean> extends BaseFragment {
    protected XListView mXListView;
    protected T mAdapter;
    private boolean isRefresh;
    protected int page = 1;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mXListView = new XListView(container.getContext());
        mXListView.setDecorationSize(getDecorationSize());
        mXListView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        if (isVerticalLinearLayoutManager())
            mXListView.setVerticalLinearLayoutManager();
        else
            mXListView.setHorizontalLinearLayoutManager();
        View rootView = null;
        View view = addListViewHead();
        if (view == null) {
            rootView = mXListView;
        } else {
            LinearLayout linearLayout = new LinearLayout(container.getContext());
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            linearLayout.addView(view);
            linearLayout.addView(mXListView);
            rootView = linearLayout;

        }

        return rootView;
    }

    /**
     * 加在listview 上面布局
     *
     * @return
     */
    protected View addListViewHead() {
        return null;
    }

    @Override
    protected void initData() {
        if (isRefreshStatus()) {
            mXListView.setLoadingListener(new XRecyclerView.LoadingListener() {
                @Override
                public void onRefresh() {
                    page = 1;
                    isRefresh = true;
                    BaseListFragment.this.onRefresh();
                }

                @Override
                public void onLoadMore() {
                    isRefresh = false;
                    BaseListFragment.this.onLoadMore();
                }
            });
        } else {
            mXListView.setPullRefreshEnabled(false);
            mXListView.setLoadingMoreEnabled(false);
        }

        mAdapter = setAdapter();
        if (mAdapter == null) {
            throw new RuntimeException("Adapter not is null ");
        }
        View headerView = addHeaderView();
        if (headerView != null) {
            mXListView.addHeaderView(headerView);
        }
        mXListView.setAdapter(mAdapter);
        initChildData();
        mXListView.setRefreshing(true);
    }

    protected abstract void initChildData();

    /**
     * 添加头布局
     *
     * @return
     */
    protected View addHeaderView() {
        return null;
    }

    public abstract T setAdapter();

    /**
     * 刷新数据
     */
    protected abstract void onRefresh();

    /**
     * 加载更多
     */
    protected abstract void onLoadMore();


    protected void addData(List<M> datas) {
        if (isRefresh) {
            mAdapter.clean();
        }
        if (datas == null || datas.size() == 0) {
            mXListView.loadMoreComplete();
            mXListView.refreshComplete();
            showToast(getResources().getString(R.string.not_more_data));
            return;
        }
        page++;
        mAdapter.addListData(datas);
        mAdapter.onRefresh();
        colorLoad();
    }

    /**
     * 关闭加载
     */
    protected void colorLoad() {
        if (mXListView != null) {
            mXListView.loadMoreComplete();
            mXListView.refreshComplete();
        }
    }

    /**
     * 横向
     *
     * @return
     */
    public boolean isVerticalLinearLayoutManager() {
        return true;
    }

    /**
     * 是否刷新
     *
     * @return
     */
    public boolean isRefreshStatus() {
        return true;
    }

    /**
     * 分割线
     *
     * @return
     */
    public int getDecorationSize() {
        return (int) getResources().getDimension(R.dimen.y1);
    }
}
