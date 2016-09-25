package com.moonsister.tcjy.home.widget;

import android.view.View;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.adapter.HomeThreeFragmentAdapter;
import com.moonsister.tcjy.base.BaseListFragment;
import com.moonsister.tcjy.bean.HomeParams;
import com.moonsister.tcjy.bean.HomeThreeFragmentBean;
import com.moonsister.tcjy.home.presenetr.HomeThreeFragmentPresenter;
import com.moonsister.tcjy.home.presenetr.HomeThreeFragmentPresenterImpl;
import com.moonsister.tcjy.home.view.HomeThreeFragmentView;
import com.moonsister.tcjy.viewholder.HomeThreeHeaderViewHolder;

import java.util.List;

/**
 * Created by jb on 2016/9/22.
 */
public class HomeThreeFragment extends BaseListFragment<HomeThreeFragmentAdapter, HomeThreeFragmentBean.DataBean> implements HomeThreeFragmentView {
    private HomeThreeHeaderViewHolder threeHeaderViewHolder;
    private HomeThreeFragmentPresenter presenter;
    private String type;
    private HomeParams mParams;

    @Override
    public HomeThreeFragmentAdapter setAdapter() {
        return new HomeThreeFragmentAdapter(null);
    }

    @Override
    protected View addListViewHead() {
        threeHeaderViewHolder = new HomeThreeHeaderViewHolder();
        return threeHeaderViewHolder.getContentView();
    }

    @Override
    protected void initChildData() {

        threeHeaderViewHolder.setActivity(getActivity());
        threeHeaderViewHolder.refreshView("");
        presenter = new HomeThreeFragmentPresenterImpl();
        presenter.attachView(this);
    }

    @Override
    public int getDecorationSize() {
        return (int) getResources().getDimension(R.dimen.y20);
    }

    @Override
    protected void onRefresh() {
        presenter.laodRefresh(page,type, mParams);
    }

    @Override
    protected void onLoadMore() {
        presenter.loadMore(page,type, mParams);
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

    @Override
    public void setData(List<HomeThreeFragmentBean.DataBean> data) {
        if (data != null) {
            addData(data);
        }
    }
}
