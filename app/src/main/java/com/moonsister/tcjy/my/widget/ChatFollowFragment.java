package com.moonsister.tcjy.my.widget;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hickey.network.bean.FrientBaen;
import com.hickey.tool.base.BaseFragment;
import com.hickey.tool.widget.UIUtils;
import com.hickey.tool.widget.XListView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.adapter.FriendAdapter;
import com.moonsister.tcjy.main.presenter.RelationActivityPresenterImpl;
import com.moonsister.tcjy.main.view.RelationActivityView;

import butterknife.Bind;

/**
 * Created by x on 2016/8/24.
 */
public class ChatFollowFragment extends BaseFragment implements RelationActivityView {
    @Bind(R.id.listview)
    XListView xListView;

    private RelationActivityPresenterImpl presenter;
    private boolean isLoadMord;
    private int type;
    private String uid;
    FriendAdapter adapter;
    public static final int PAGE_WACTH = 1;
    public static final int PAGE_FEN = 2;
    public static final int PAGE_MAIN = 3;
    public static final int PAGE_FRIEND = 4;//好友状态
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        Intent intent=getActivity().getIntent();
//        type = getActivity().getIntent().getIntExtra("type", 0);
//        uid = getActivity().getIntent().getStringExtra("uid");
        type=1;
        presenter=new RelationActivityPresenterImpl();
        presenter.attachView(this);

        return UIUtils.inflateLayout(R.layout.chatfollowfragment);

    }

    @Override
    protected void initData() {
        xListView.setVerticalLinearLayoutManager();
        xListView.setPullRefreshEnabled(false);
        xListView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {
                presenter.loadData(type, uid);
            }
        });
        presenter.loadData(type,uid);

    }


    @Override
    public void setFrientData(FrientBaen frientBaen) {
//        adapter = new FriendAdapter(frientBaen.getData(), this);
//        adapter.setPageType(type);
        if (frientBaen == null) {
//            xlv.setNoMore();
            xListView.loadMoreComplete();
            xListView.refreshComplete();
            return;
        }
        if (frientBaen.getData() == null || frientBaen.getData().size() == 0) {
//            xlv.setNoMore();
            xListView.loadMoreComplete();
            xListView.refreshComplete();
            return;
        }
        if (adapter == null) {
            adapter = new FriendAdapter(frientBaen.getData(), this);
            adapter.setPageType(type);
            if (xListView != null)
                xListView.setAdapter(adapter);
        } else {
            adapter.addListData(frientBaen.getData());
            adapter.onRefresh();
        }
        xListView.loadMoreComplete();
        xListView.refreshComplete();



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


    public void setUid(String uid) {
        this.uid = uid;
    }
}
