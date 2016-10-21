package com.moonsister.tcjy.home.widget;

import android.view.View;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.adapter.HomeThreeFragmentAdapter;
import com.moonsister.tcjy.base.BaseListFragment;
import com.moonsister.tcjy.bean.BannerBean;
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
    private String type = "1";
    private HomeParams mParams;
    // 0 搜索  1  推荐
    private int flag = 1;

    @Override
    public HomeThreeFragmentAdapter setAdapter() {
        return new HomeThreeFragmentAdapter(null);
    }

    @Override
    protected View addListViewHead() {
        threeHeaderViewHolder = new HomeThreeHeaderViewHolder();
        threeHeaderViewHolder.setPageType(type);
        return threeHeaderViewHolder.getContentView();
    }

    @Override
    protected void initChildData() {
//        IMManager.getInstance().loginHx("161830", "7f547d8a7b0afa0d2177361fb46cb52d");
        threeHeaderViewHolder.setActivity(getActivity());
        threeHeaderViewHolder.refreshView("");
        threeHeaderViewHolder.setFilterDoneListenter(new HomeThreeHeaderViewHolder.onFilterDoneListenter() {
            @Override
            public void onFilterDone(HomeParams params) {
                if (params != null) {
                    mParams = params;
                    if (mXListView != null) {
                        mXListView.setRefreshing(true);
                    }
                }
            }
        });
        presenter = new HomeThreeFragmentPresenterImpl();
        presenter.attachView(this);
        presenter.loadBannerData();
    }

    /**
     * 是否是推荐数据
     */
    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public int getDecorationSize() {
        return (int) getResources().getDimension(R.dimen.y20);
    }

    @Override
    protected void onRefresh() {
        presenter.laodRefresh(page, type, flag, mParams);
    }

    @Override
    protected void onLoadMore() {
        presenter.loadMore(page, type, flag, mParams);
    }

    @Override
    public void showLoading() {
        showProgressDialog();
    }

    @Override
    public void hideLoading() {

        hideProgressDialog();
        colorLoad();
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

    @Override
    public void setBanner(BannerBean bean) {
        threeHeaderViewHolder.setbanerDate(bean);
    }

    public void setShowSearchHeader(boolean showSearchHeader) {
        if (threeHeaderViewHolder != null)
            threeHeaderViewHolder.setSearchViewShow(true);
    }
}
