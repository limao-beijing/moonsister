package com.moonsister.tcjy.center.widget;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hickey.network.bean.LableBean;
import com.hickey.tool.base.BaseFragment;
import com.hickey.tool.widget.UIUtils;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.center.presenter.LableFragmentPersenter;
import com.moonsister.tcjy.center.presenter.LableFragmentPersenterImpl;
import com.moonsister.tcjy.center.view.LableFragmentView;
import com.moonsister.tcjy.widget.FlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by jb on 2016/8/8.
 */
public class LableFragment extends BaseFragment implements View.OnClickListener, LableFragmentView {
    @Bind(R.id.fl_lable_selected)
    FlowLayout flLableSelected;
    @Bind(R.id.fl_lable_select)
    FlowLayout flLableSelect;
    @Bind(R.id.iv_refresh)
    ImageView iv_refresh;
    private ArrayList<View> selects = new ArrayList<View>();
    public boolean isMove;
    private LableFragmentPersenter persenter;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        persenter = new LableFragmentPersenterImpl();
        persenter.attachView(this);
        return inflater.inflate(R.layout.fragment_lable, container, false);
    }

    @Override
    protected void initData() {
        persenter.loadData();
    }

    @Override
    public void setLable(LableBean lableBean) {

        if (lableBean != null) {

            List<String> data = lableBean.getData();
            if (data.size() > 0) {
                flLableSelect.removeAllViews();
//                flLableSelected.removeAllViews();
            }
            for (String s : data) {
                TextView tv = (TextView) UIUtils.inflateLayout(
                        R.layout.activity_search_tv, flLableSelect);
                tv.setText(s);
                tv.setTextColor(getResources().getColor(R.color.home_navigation_text_red));
                setOnClickListener(tv);
                flLableSelect.addView(tv);
                selects.add(tv);
            }
        }

    }

    private void setOnClickListener(View v) {
        v.setOnClickListener(this);
    }

    public static LableFragment newInstance() {
        return new LableFragment();
    }


    @Override
    public void onClick(View v) {
        if (isMove)
            return;
        if (!selects.contains(v)) {
            moveView(flLableSelect, flLableSelected, v);
        } else {
            if (flLableSelected.getChildCount()>=6){
                showToast(UIUtils.getStringRes(R.string.lable_number_more_sex));
                return;
            }
            moveView(flLableSelected, flLableSelect, v);
        }
    }

    private void moveView(ViewGroup addLayout, ViewGroup removeLayout, View removeView) {
        final int[] startLocation = new int[2];
        removeView.getLocationInWindow(startLocation);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                try {
                    int[] endLocation = new int[2];
                    // 获取终点的坐标
                    int i = addLayout.getChildCount();
                    if (i < 1) {
                        addLayout.getLocationInWindow(endLocation);
                    } else {
                        addLayout.getChildAt(i - 1).getLocationInWindow(endLocation);
                    }

                    MoveAnim(removeView, startLocation, endLocation);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 50L);
    }

    /**
     * 点击ITEM移动动画
     *
     * @param moveView
     * @param startLocation
     * @param endLocation
     */
    private void MoveAnim(final View moveView, int[] startLocation,
                          int[] endLocation) {
        int[] initLocation = new int[2];
        // 获取传递过来的VIEW的坐标
        moveView.getLocationInWindow(initLocation);
        // 得到要移动的VIEW,并放入对应的容器中
        final FlowLayout moveViewGroup = getMoveViewGroup();
        ViewParent parent = moveView.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(moveView);
        }
        final View mMoveView = getMoveView(moveViewGroup, moveView,
                initLocation);
        // 创建移动动画
        TranslateAnimation moveAnimation = new TranslateAnimation(
                startLocation[0], endLocation[0], startLocation[1],
                endLocation[1]);
        moveAnimation.setDuration(300L);// 动画时间
        // 动画配置
        AnimationSet moveAnimationSet = new AnimationSet(true);
        moveAnimationSet.setFillAfter(false);// 动画效果执行完毕后，View对象不保留在终止的位置
        moveAnimationSet.addAnimation(moveAnimation);
        mMoveView.startAnimation(moveAnimationSet);
        moveAnimationSet.setAnimationListener(new Animation.AnimationListener() {


            @Override
            public void onAnimationStart(Animation animation) {

                isMove = true;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                if (selects.contains(moveView)) {
                    flLableSelected.addView(moveView);
                    selects.remove(moveView);
                } else {
                    flLableSelect.addView(moveView);
                    selects.add(moveView);
                }
                moveViewGroup.removeView(mMoveView);
                isMove = false;
            }
        });
    }

    /**
     * 获取点击的Item的对应View，
     *
     * @param view
     * @return
     */
    private ImageView getView(View view) {
        view.destroyDrawingCache();
        view.setDrawingCacheEnabled(true);
        Bitmap cache = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        ImageView iv = new ImageView(getActivity());
        iv.setImageBitmap(cache);
        return iv;
    }

    /**
     * 获取移动的VIEW，放入对应ViewGroup布局容器
     *
     * @param viewGroup
     * @param initLocation
     * @return
     */
    private View getMoveView(FlowLayout viewGroup, View views, int[] initLocation) {
        TextView tv = (TextView) UIUtils.inflateLayout(
                R.layout.activity_search_tv, viewGroup);
        tv.setText("itme  is  " + views.getId());
        tv.setTextColor(getResources().getColor(R.color.home_navigation_text_red));
        int x = initLocation[0];
        int y = initLocation[1];
        viewGroup.addView(tv);
        LinearLayout.LayoutParams mLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mLayoutParams.leftMargin = x;
        mLayoutParams.topMargin = y;
        tv.setLayoutParams(mLayoutParams);
        return tv;
    }

    /**
     * 创建移动的ITEM对应的ViewGroup布局容器
     */
    private FlowLayout getMoveViewGroup() {
        ViewGroup moveViewGroup = (ViewGroup) getActivity().getWindow().getDecorView();
        FlowLayout moveLinearLayout = new FlowLayout(getActivity());
        moveLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        moveViewGroup.addView(moveLinearLayout);
        return moveLinearLayout;
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
        transfePageMsg(msg);
    }

    @OnClick(R.id.iv_refresh)
    public void onClick() {
        if (persenter != null)
            persenter.loadData();
    }

    public List<String> getSelectLables() {
        ArrayList<String> lists = new ArrayList<String>();
        int childCount = flLableSelected.getChildCount();
        for (int i = 0; i < childCount; i++) {
            TextView child = (TextView) flLableSelected.getChildAt(i);
            lists.add(child.getText().toString());
        }
        return lists;
    }
}
