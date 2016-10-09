package com.moonsister.tcjy.find.widget;

import android.view.View;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.adapter.OnlineFragmentAdapter;
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
public class OnlineFragment extends BaseListFragment<OnlineFragmentAdapter, HomeThreeFragmentBean.DataBean> implements HomeThreeFragmentView {
    HomeThreeHeaderViewHolder threeHeaderViewHolder;
    //    private OnlineFragmentPresenter presenter;
    private HomeThreeFragmentPresenter presenter;
    private String type = "2";
    public HomeParams mParams;
    // 0 搜索  1  推荐
    private int flag = 1;

    @Override
    public OnlineFragmentAdapter setAdapter() {
        return new OnlineFragmentAdapter(null);
    }

    @Override
    protected View addListViewHead() {
        threeHeaderViewHolder = new HomeThreeHeaderViewHolder();
        threeHeaderViewHolder.setPageType(type);
        threeHeaderViewHolder.setTvTitle(getString(R.string.online_user));
        threeHeaderViewHolder.showBanner(false);
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
        return threeHeaderViewHolder.getContentView();
    }

    /**
     * 是否是推荐数据
     */
    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    protected void initChildData() {
        presenter = new HomeThreeFragmentPresenterImpl();
        presenter.attachView(this);
    }

    @Override
    public int getDecorationSize() {
        return (int) getResources().getDimension(R.dimen.y20);
    }

    @Override
    protected void onRefresh() {
//        presenter.loadData(page);
        presenter.laodRefresh(page, type, flag, mParams);
    }

    @Override
    protected void onLoadMore() {
        presenter.laodRefresh(page, type, flag, mParams);
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

    @Override
    public void setBanner(BannerBean bean) {

    }
    public void setShowSearchHeader(boolean showSearchHeader) {
        if (threeHeaderViewHolder != null)
            threeHeaderViewHolder.setSearchViewShow(true);
    }

}
