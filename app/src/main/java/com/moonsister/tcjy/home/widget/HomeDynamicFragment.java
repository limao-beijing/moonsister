package com.moonsister.tcjy.home.widget;

import com.hickey.network.bean.DynamicItemBean;
import com.hickey.network.bean.PayRedPacketPicsBean;
import com.hickey.tool.base.BaseListFragment;
import com.moonsister.tcjy.adapter.HomePageFragmentAdapter;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.home.presenetr.HomeDynamicFragmentPresenter;
import com.moonsister.tcjy.home.presenetr.HomeDynamicFragmentPresenterImpl;
import com.moonsister.tcjy.home.view.HomeDynamicView;
import com.moonsister.tcjy.utils.LogUtils;
import com.trello.rxlifecycle.FragmentEvent;

import java.util.List;

/**
 * Created by jb on 2016/11/29.
 */
public class HomeDynamicFragment extends BaseListFragment<HomePageFragmentAdapter, DynamicItemBean> implements HomeDynamicView {
    private HomeDynamicFragmentPresenter presenter;

    @Override
    protected void initChildData() {
        presenter = new HomeDynamicFragmentPresenterImpl();
        presenter.attachView(this);
        setRx();
    }

    @Override
    public HomePageFragmentAdapter setAdapter() {
        return new HomePageFragmentAdapter(null);
    }

    @Override
    protected void onRefresh() {
        page = 1;
        presenter.loadInitData(page, "1");
    }

    @Override
    protected void onLoadMore() {
        presenter.loadInitData(page, "1");
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
    public void setInitData(List<DynamicItemBean> bean) {
        addData(bean);
    }

    private void setRx() {
        RxBus.with(this)
                .setEndEvent(FragmentEvent.DESTROY)
                .setEvent(Events.EventEnum.PAY_SUCCESS_GET_DATA)
                .onNext(events -> {
                    LogUtils.e("MyFragment", "PAY_SUCCESS_GET_DATA 数据");
                    if (events != null && mAdapter != null) {
                        Object message = events.message;
                        if (message instanceof PayRedPacketPicsBean) {
                            PayRedPacketPicsBean bean = (PayRedPacketPicsBean) message;
                            mAdapter.updataPayData(bean);

                        }
                    }
                })
                .create();
    }

}
