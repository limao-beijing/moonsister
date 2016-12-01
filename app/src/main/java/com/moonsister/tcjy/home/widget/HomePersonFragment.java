package com.moonsister.tcjy.home.widget;

import com.hickey.network.bean.EngagemengRecommendBean;
import com.hickey.tool.base.BaseListFragment;
import com.moonsister.tcjy.adapter.EengegamentRecommendFragmentAdpter;
import com.moonsister.tcjy.home.presenetr.HomePersonFragmentPresenter;
import com.moonsister.tcjy.home.presenetr.HomePersonFragmentPresenterImpl;
import com.moonsister.tcjy.home.view.HomePersonFragmentView;

import java.util.List;

/**
 * Created by jb on 2016/11/29.
 */
public class HomePersonFragment extends BaseListFragment<EengegamentRecommendFragmentAdpter, EngagemengRecommendBean.DataBean> implements HomePersonFragmentView {
    private HomePersonFragmentPresenter presenter;
    private String type;

    @Override
    protected void initChildData() {
        type = getArguments().getString("type");
        presenter = new HomePersonFragmentPresenterImpl();
        presenter.attachView(this);
    }

    @Override
    public EengegamentRecommendFragmentAdpter setAdapter() {
        return new EengegamentRecommendFragmentAdpter(null);
    }

    @Override
    protected void onRefresh() {
        page = 1;
        presenter.loadInitData(page, type);
    }

    @Override
    protected void onLoadMore() {
        presenter.loadInitData(page, type);
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
    public void setInitData(List<EngagemengRecommendBean.DataBean> beens) {
        for (EngagemengRecommendBean.DataBean bean : beens) {

            bean.setHomeType(type);
        }
        addData(beens);
    }
}
