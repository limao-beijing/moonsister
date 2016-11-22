package com.moonsister.tcjy.my.widget;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hickey.network.bean.TiXinrRecordBean;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.adapter.TiXianAdapter;
import com.moonsister.tcjy.base.BaseFragment;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.my.persenter.TiXianFragmentPresenter;
import com.moonsister.tcjy.my.persenter.TiXianFragmentPresenterImpl;
import com.moonsister.tcjy.my.view.TiXianFragmentView;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tcjy.widget.XListView;
import com.trello.rxlifecycle.FragmentEvent;

import java.util.List;

import butterknife.Bind;

/**
 * Created by jb on 2016/6/27.
 */
public class MyAccountTiXianFragment extends BaseFragment implements TiXianFragmentView {
    @Bind(R.id.list)
    XListView xListView;
    private TiXianFragmentPresenter presenter;
    private TiXianAdapter adapter;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        presenter = new TiXianFragmentPresenterImpl();
        presenter.attachView(this);
        return UIUtils.inflateLayout(R.layout.fragment_my_account_recorder, container);
    }

    @Override
    protected void initData() {
        xListView.setVerticalLinearLayoutManager();
        xListView.setRefreshing(false);
        xListView.setLoadingMoreEnabled(false);
        presenter.loadTixin();
        RxBus.with(this)
                .setEndEvent(FragmentEvent.DESTROY)
                .setEvent(Events.EventEnum.MONEY_CHANGE)
                .onNext(events -> {
                    presenter.loadTixin();
                })
                .create();
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
    public void setLoadData(TiXinrRecordBean bean) {
        List<TiXinrRecordBean.DataBean> data = bean.getData();
        adapter = new TiXianAdapter(bean.getData());
        xListView.setAdapter(adapter);

    }
}
