package com.moonsister.tcjy.main.widget;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.moonsister.tcjy.adapter.HomePageFragmentAdapter;
import com.moonsister.tcjy.base.BaseFragment;
import com.moonsister.tcjy.bean.DynamicItemBean;
import com.moonsister.tcjy.bean.UserInfoDetailBean;
import com.moonsister.tcjy.main.presenter.HomePageFragmentPresenter;
import com.moonsister.tcjy.main.presenter.HomePageFragmentPresenterImpl;
import com.moonsister.tcjy.main.view.HomePageFragmentView;
import com.moonsister.tcjy.utils.EnumConstant;
import com.moonsister.tcjy.widget.XListView;

import java.util.List;

/**
 * Created by jb on 2016/9/11.
 */
public class HomePageItemFragment extends BaseFragment implements HomePageFragmentView {
    private XListView xListView;
    private String userId;
    private boolean isRefresh;
    private HomePageFragmentPresenter presenter;
    private HomePageFragmentAdapter adapter;
    private EnumConstant.SearchType type = EnumConstant.SearchType.all;
    private View headerView;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        xListView = new XListView(container.getContext());
        xListView.setVerticalLinearLayoutManager();
        xListView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        userId = getActivity().getIntent().getStringExtra("id");
        presenter = new HomePageFragmentPresenterImpl();
        presenter.attachView(this);
        return xListView;
    }

    public void setSearchType(EnumConstant.SearchType type) {
        this.type = type;
    }

    @Override
    protected void initData() {
        xListView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                isRefresh = true;
                presenter.loadRefresh(userId, type);
            }

            @Override
            public void onLoadMore() {
                isRefresh = false;
                presenter.loadMore(userId, type);
            }
        });
        xListView.setRefreshing(true);
    }

    public void setHeader(View headerView) {
        this.headerView = headerView;

    }

    @Override
    public void onStart() {
        super.onStart();
        addHeader();
    }

    /**
     * 添加头布局
     *
     * @return
     */
    public void addHeader() {
        if (xListView != null && headerView != null) {
            if (headerView.getParent() == null) {
                xListView.addHeaderView(headerView);
            } else {
                if (headerView.getParent() instanceof ViewGroup) {
                    ((ViewGroup) headerView.getParent()).removeView(headerView);
                    xListView.addHeaderView(headerView);
                } else
                    xListView.addHeaderView(headerView);
            }
        }
    }

    @Override
    public void setDynamicData(List<DynamicItemBean> list) {
        if (adapter == null) {
            adapter = new HomePageFragmentAdapter(list);
//            adapter.setBaseView(this);
            xListView.setAdapter(adapter);
        } else {
            if (isRefresh)
                adapter.clean();
            adapter.addListData(list);
            adapter.onRefresh();
        }
        if (xListView != null) {
            xListView.refreshComplete();
            xListView.loadMoreComplete();
        }
    }

    @Override
    public void setHeaderData(UserInfoDetailBean bean) {

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

    public static HomePageItemFragment newInstance() {
        return new HomePageItemFragment();
    }
}
