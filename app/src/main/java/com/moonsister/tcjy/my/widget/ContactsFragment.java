package com.moonsister.tcjy.my.widget;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hickey.network.bean.FrientBaen;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.adapter.FriendlyAdapter;
import com.moonsister.tcjy.base.BaseFragment;
import com.moonsister.tcjy.main.presenter.RelationActivityPresenterImpl;
import com.moonsister.tcjy.main.view.RelationActivityView;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tcjy.widget.XListView;

import butterknife.Bind;

/**
 * Created by x on 2016/8/24.
 */
public class ContactsFragment extends BaseFragment implements RelationActivityView {
    @Bind(R.id.xlistview)
    XListView xListView;
//    @Bind(R.id.image_pingbi)
//    ImageView image_pingbi;
    private RelationActivityPresenterImpl presenter;
    private boolean isLoadMord;
    private int type;

    private FriendlyAdapter adapter;
    String uid;
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        type = getActivity().getIntent().getIntExtra("type", 0);
        uid = getActivity().getIntent().getStringExtra("uid");
        type=2;
        presenter=new RelationActivityPresenterImpl();
        presenter.attachView(this);

        return UIUtils.inflateLayout(R.layout.contactsfragment);
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
//        adapter = new FriendlyAdapter(frientBaen.getData(), this);
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
            adapter = new FriendlyAdapter(frientBaen.getData(), this);
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

//    @Override
//    public void setPingBiData(PingbiBean pingBiBaen) {
//
//    }

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

//    @OnClick(R.id.image_pingbi)
//    public void onClick(View view) {
//        Intent intent=new Intent(getActivity(),PingbiActivity.class);
//        startActivity(intent);
//    }
}
