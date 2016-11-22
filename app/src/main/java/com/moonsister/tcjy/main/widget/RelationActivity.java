package com.moonsister.tcjy.main.widget;

import android.view.View;

import com.hickey.network.bean.FrientBaen;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.adapter.FrientAdapter;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.main.presenter.RelationActivityPresenter;
import com.moonsister.tcjy.main.presenter.RelationActivityPresenterImpl;
import com.moonsister.tcjy.main.view.RelationActivityView;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tcjy.widget.XListView;

import butterknife.Bind;

/**
 * Created by jb on 2016/7/22.
 */
public class RelationActivity extends BaseActivity implements RelationActivityView {
    private RelationActivityPresenter presenter;
    @Bind(R.id.xlv)
    XListView xlv;
    private RelationActivityPresenter presente;
    public static final int WACTH_PAGE = 1;
    public static final int FANS_PAGE = 2;
    private int type;
    private FrientAdapter adapter;
    private String uid;

    @Override
    protected View setRootContentView() {
        type = getIntent().getIntExtra("type", 0);
        uid = getIntent().getStringExtra("uid");
        presenter = new RelationActivityPresenterImpl();
        presenter.attachView(this);
        return UIUtils.inflateLayout(R.layout.activity_relation);
    }

    @Override
    protected String initTitleName() {
        if (type == WACTH_PAGE) {
            return UIUtils.getStringRes(R.string.wacth);
        }
        return UIUtils.getStringRes(R.string.str_fen);
    }

    @Override
    protected void initView() {
        xlv.setVerticalLinearLayoutManager();
        xlv.setPullRefreshEnabled(false);
        xlv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {
                presenter.loadData(type, uid);
            }
        });
        presenter.loadData(type, uid);

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
    public void setFrientData(FrientBaen frientBaen) {

        if (frientBaen == null) {
//            xlv.setNoMore();
        xlv.loadMoreComplete();
        xlv.refreshComplete();
        return;
    }
    if (frientBaen.getData() == null || frientBaen.getData().size() == 0) {
//            xlv.setNoMore();
        xlv.loadMoreComplete();
        xlv.refreshComplete();
        return;
    }
    if (adapter == null) {
        adapter = new FrientAdapter(frientBaen.getData(), this);
        adapter.setPageType(type);
        if (xlv != null)
            xlv.setAdapter(adapter);
    } else {
        adapter.addListData(frientBaen.getData());
        adapter.onRefresh();
    }
    xlv.loadMoreComplete();
    xlv.refreshComplete();


}
}
