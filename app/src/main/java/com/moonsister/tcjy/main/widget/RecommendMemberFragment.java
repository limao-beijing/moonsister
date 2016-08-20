package com.moonsister.tcjy.main.widget;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.adapter.CardAdapter;
import com.moonsister.tcjy.base.BaseFragment;
import com.moonsister.tcjy.bean.RecommendMemberFragmentBean;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tcjy.widget.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by jb on 2016/8/15.
 */
public class RecommendMemberFragment extends BaseFragment {
    @Bind(R.id.left)
    ImageView left;
    @Bind(R.id.right)
    ImageView right;
    @Bind(R.id.sfav)
    SwipeFlingAdapterView flingContainer;
    private CardAdapter adapter;

//    private RecommendMemberFragmentPresenter presenter;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        presenter = new RecommendMemberFragmentPresenterImpl();
//        presenter.attachView(this);
        return UIUtils.inflateLayout(R.layout.fragment_recommend_member);
    }

    @Override
    protected void initData() {
//        presenter.loadData();
    }

    public static RecommendMemberFragment newInstance() {
        return new RecommendMemberFragment();
    }


//    @Override
//    public void showLoading() {
//        showProgressDialog();
//    }
//
//    @Override
//    public void hideLoading() {
//        hideProgressDialog();
//    }
//
//    @Override
//    public void transfePageMsg(String msg) {
//        showToast(msg);
//    }

    //    @Override
    public void setData(ArrayList<RecommendMemberFragmentBean.DataBean> data) {
        adapter = new CardAdapter(this.getContext(), data);
        flingContainer.setAdapter(adapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                data.remove(0);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                showToast("不喜欢");
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                showToast("喜欢");
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                getActivity().finish();
            }

            @Override
            public void onScroll(float scrollProgressPercent) {
                View view = flingContainer.getSelectedView();
                view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
            }
        });

    }

    public void right() {
        flingContainer.getTopCardListener().selectRight();
    }

    public void left() {
        flingContainer.getTopCardListener().selectLeft();//调用动画实现不喜欢的效果。
    }

    @OnClick({R.id.left, R.id.right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.left:
                left();
                break;
            case R.id.right:
                right();
                break;
        }
    }
}
