package com.moonsister.tcjy.home.widget;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hickey.network.bean.HomeTopItemBean;
import com.hickey.tool.constant.EnumConstant;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.adapter.HomeTopItemFragmentAdapter;
import com.moonsister.tcjy.base.BaseFragment;
import com.moonsister.tcjy.home.presenetr.HomeTopItemFragmentPersenter;
import com.moonsister.tcjy.home.presenetr.HomeTopItemFragmentPersenterImpl;
import com.moonsister.tcjy.home.view.HomeTopItemFragmentView;
import com.moonsister.tcjy.widget.XListView;

import java.util.List;

import butterknife.Bind;


/**
 * Created by jb on 2016/8/24.
 */
public class HomeTopItemFragment extends BaseFragment implements HomeTopItemFragmentView {
    @Bind(R.id.xlv)
    XListView xlv;
    private HomeTopItemFragmentPersenter persenter;
    private HomeTopItemFragmentAdapter itemFragmentAdapter;
    //标签
    private String tagId;
    private EnumConstant.HomeTopFragmentTop homeType;
    private boolean isRefresh = true;

    @Override

    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        persenter = new HomeTopItemFragmentPersenterImpl();
        persenter.attachView(this);
        return inflater.inflate(R.layout.fragment_home_top_item, container, false);
    }

    @Override
    protected void initData() {
        xlv.setVerticalLinearLayoutManager();
        xlv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                isRefresh = true;
                persenter.loadRefresh(isRefresh,tagId, homeType);
            }

            @Override
            public void onLoadMore() {
                isRefresh = false;
                persenter.loadRefresh(isRefresh, tagId, homeType);
            }
        });
        xlv.setRefreshing(true);
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

    public static HomeTopItemFragment newInstance() {
        return new HomeTopItemFragment();
    }

    public void setType(EnumConstant.HomeTopFragmentTop type) {
        this.homeType = type;
    }

    @Override
    public void setData(List<HomeTopItemBean.DataBean> data) {
        if (itemFragmentAdapter == null) {
            itemFragmentAdapter = new HomeTopItemFragmentAdapter(data);
            xlv.setAdapter(itemFragmentAdapter);
        } else {
            if (isRefresh)
                itemFragmentAdapter.clean();
            itemFragmentAdapter.addListData(data);
            itemFragmentAdapter.onRefresh();
        }
        if (xlv != null) {
            xlv.loadMoreComplete();
            xlv.refreshComplete();
        }

    }
}
