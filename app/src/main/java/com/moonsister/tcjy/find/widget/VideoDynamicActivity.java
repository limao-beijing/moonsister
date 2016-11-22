package com.moonsister.tcjy.find.widget;

import android.view.View;

import com.hickey.network.bean.DynamicItemBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.adapter.HomePageFragmentAdapter;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.find.presenter.VideoDynamicActivityPresenter;
import com.moonsister.tcjy.find.presenter.VideoDynamicActivityPresenterImpl;
import com.moonsister.tcjy.find.view.VideoDynamicActivityView;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tcjy.widget.XListView;

import java.util.List;

import butterknife.Bind;

/**
 * Created by jb on 2016/9/14.
 */
public class VideoDynamicActivity extends BaseActivity implements VideoDynamicActivityView {
    @Bind(R.id.xlv)
    XListView mXListView;
    private VideoDynamicActivityPresenter presenter;
    private boolean isRefresh;
    private HomePageFragmentAdapter adapter;

    @Override
    protected View setRootContentView() {
        return UIUtils.inflateLayout(R.layout.activity_video_dynamic);
    }

    @Override
    protected String initTitleName() {
        return getString(R.string.video);
    }

    @Override
    protected void initView() {
        presenter = new VideoDynamicActivityPresenterImpl();
        presenter.attachView(this);
        mXListView.setVerticalLinearLayoutManager();
        mXListView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                isRefresh = true;
                presenter.loadRefresh();
            }

            @Override
            public void onLoadMore() {
                isRefresh = false;
                presenter.loadMore();
            }
        });
        mXListView.setRefreshing(true);
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
    public void setVideoDynamic(List<DynamicItemBean> list) {
        if (adapter == null) {
            adapter = new HomePageFragmentAdapter(list);
            adapter.setBaseView(this);
            mXListView.setAdapter(adapter);
        } else {
            if (isRefresh)
                adapter.clean();
            adapter.addListData(list);
            adapter.onRefresh();
        }
        if (mXListView != null) {
            mXListView.refreshComplete();
            mXListView.loadMoreComplete();
        }
    }

    @Override
    public void refreshData() {
        if (presenter != null) {
            isRefresh = true;
            presenter.loadRefresh();
        }
    }
}
