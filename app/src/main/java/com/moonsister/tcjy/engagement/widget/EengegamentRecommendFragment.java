package com.moonsister.tcjy.engagement.widget;


import android.content.Intent;
import android.support.v4.app.Fragment;

import com.moonsister.tcjy.adapter.EengegamentRecommendFragmentAdpter;
import com.moonsister.tcjy.base.BaseListFragment;
import com.moonsister.tcjy.bean.EngagemengRecommendBean;
import com.moonsister.tcjy.engagement.presenter.EengegamentRecommendPresenter;
import com.moonsister.tcjy.engagement.presenter.EengegamentRecommendPresenterImpl;
import com.moonsister.tcjy.engagement.view.EngagemengRecommendView;
import com.moonsister.tcjy.utils.EnumConstant;

import java.util.List;

/**
 * Created by jb on 2016/9/22.
 */
public class EengegamentRecommendFragment extends BaseListFragment<EengegamentRecommendFragmentAdpter, EngagemengRecommendBean.DataBean> implements EngagemengRecommendView {

    private EengegamentRecommendPresenter presenter;
    private EnumConstant.EngegamentType mEngegamentType = EnumConstant.EngegamentType.All;

    @Override
    protected void initChildData() {
        Intent intent = getActivity().getIntent();

        EnumConstant.EngegamentType type = (EnumConstant.EngegamentType) intent.getSerializableExtra("type");
        if (type != null) {
            mEngegamentType = type;

        }
        presenter = new EengegamentRecommendPresenterImpl();
        presenter.attachView(this);

    }

    @Override
    public EengegamentRecommendFragmentAdpter setAdapter() {
        return new EengegamentRecommendFragmentAdpter(null);
    }

    @Override
    protected void onLoadMore() {
        presenter.loadData(page, mEngegamentType);
    }

    @Override
    protected void onRefresh() {
        presenter.loadData(page, mEngegamentType);
    }

    public static Fragment newInstance() {
        return new EengegamentRecommendFragment();
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
    public void setRecommend(List<EngagemengRecommendBean.DataBean> datas) {
        for (EngagemengRecommendBean.DataBean bean : datas) {
            bean.setEngegamentType(mEngegamentType);
        }
        addData(datas);
    }
}
