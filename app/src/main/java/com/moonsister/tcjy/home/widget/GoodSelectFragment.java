package com.moonsister.tcjy.home.widget;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.adapter.GoodSelectAdapter;
import com.moonsister.tcjy.base.BaseFragment;
import com.moonsister.tcjy.bean.GoodSelectBaen;
import com.moonsister.tcjy.home.presenetr.GoodSelectPresenter;
import com.moonsister.tcjy.home.presenetr.GoodSelectPresenterImpl;
import com.moonsister.tcjy.home.view.GoodSelectView;
import com.moonsister.tcjy.widget.XListView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.Bind;


/**
 * Created by pc on 2016/6/1.
 */
public class GoodSelectFragment extends BaseFragment implements GoodSelectView {
    @Bind(R.id.recyclerview)
    XListView recyclerview;
    @Bind(R.id.text_empty)
    TextView textEmpty;
    private int pageType;
    private GoodSelectPresenter goodSelectPresenter;
    public static final int GOOD_SELECT = 1;
    public static final int SAME_CITY = 2;

    public static GoodSelectFragment newInstance() {

        return new GoodSelectFragment();
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        goodSelectPresenter = new GoodSelectPresenterImpl(this);
        return inflater.inflate(R.layout.fragment_good_select, container, false);
    }

    public void setPegeType(int type) {
        pageType = type;

    }

    @Override
    protected void initData() {

        recyclerview.setVerticalGridLayoutManager(2);
        recyclerview.setEmptyView(textEmpty);

        recyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                isloadMore = false;
                goodSelectPresenter.uploadGoodSelectDateList(pageType);

            }

            @Override
            public void onLoadMore() {
                isloadMore = true;
                goodSelectPresenter.downloadGoodSelectDateList(pageType);

            }
        });
        recyclerview.setRefreshing(true);
    }

    private GoodSelectAdapter mAdapter;
    private boolean isloadMore = false;

    @Override
    public void addGoodSelectDate(List<GoodSelectBaen.Data> list) {

        if (mAdapter == null) {
            mAdapter = new GoodSelectAdapter(list);
            if (recyclerview != null)
                recyclerview.setAdapter(mAdapter);
        } else {
            if (!isloadMore)
                mAdapter.clean();
            mAdapter.addListData(list);
            mAdapter.onRefresh();

        }


    }

    private void loadMoreComplete() {
        if (recyclerview == null)
            return;
        recyclerview.loadMoreComplete();
        recyclerview.refreshComplete();

    }

    @Override
    public void showLoading() {
        showProgressDialog();
    }

    @Override
    public void hideLoading() {
        if (mAdapter != null) {
            loadMoreComplete();
        }
        hideProgressDialog();
    }

    @Override
    public void transfePageMsg(String msg) {
        showToast(msg);
    }
}
