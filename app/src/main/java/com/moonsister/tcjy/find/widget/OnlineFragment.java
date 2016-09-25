package com.moonsister.tcjy.find.widget;

import android.view.View;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.adapter.OnlineFragmentAdapter;
import com.moonsister.tcjy.base.BaseListFragment;
import com.moonsister.tcjy.bean.HomeThreeFragmentBean;
import com.moonsister.tcjy.home.presenetr.HomeThreeFragmentPresenter;
import com.moonsister.tcjy.home.presenetr.HomeThreeFragmentPresenterImpl;
import com.moonsister.tcjy.home.view.HomeThreeFragmentView;
import com.moonsister.tcjy.viewholder.HomeThreeHeaderViewHolder;

import java.util.List;

/**
 * Created by jb on 2016/9/22.
 */
public class OnlineFragment extends BaseListFragment<OnlineFragmentAdapter, HomeThreeFragmentBean.DataBean> implements HomeThreeFragmentView {
    HomeThreeHeaderViewHolder threeHeaderViewHolder;
    //    private OnlineFragmentPresenter presenter;
    private HomeThreeFragmentPresenter presenter;
    private String type = "2";

    @Override
    public OnlineFragmentAdapter setAdapter() {
        return new OnlineFragmentAdapter(null);
    }

    @Override
    protected View addListViewHead() {
        threeHeaderViewHolder = new HomeThreeHeaderViewHolder();
        threeHeaderViewHolder.setTvTitle(getString(R.string.online_user));
        threeHeaderViewHolder.showBanner(false);
        threeHeaderViewHolder.setActivity(getActivity());
        threeHeaderViewHolder.refreshView("");
        return threeHeaderViewHolder.getContentView();
    }

    @Override
    protected void initChildData() {
        presenter = new HomeThreeFragmentPresenterImpl();
        presenter.attachView(this);
//        presenter = new OnlineFragmentPresenterImpl();
//        presenter.attachView(this);
    }

    @Override
    public int getDecorationSize() {
        return (int) getResources().getDimension(R.dimen.y20);
    }

    @Override
    protected void onRefresh() {
//        presenter.loadData(page);
        presenter.laodRefresh(page, type, null);
    }

    @Override
    protected void onLoadMore() {
        presenter.laodRefresh(page, type, null);
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
