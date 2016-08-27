package com.moonsister.tcjy.home.widget;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseFragment;

/**
 * Created by jb on 2016/8/26.
 */
public class SearchFragment extends BaseFragment implements SearchHeaderFragment.onSearchHeaderFragmentListener {

    private SearchHeaderFragment searchHeadFragment;
    private SearchContentFragment searchContentFragment;
    private PopupWindow popupWindow;

    public static Fragment newInstance() {
        return new SearchFragment();
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    protected void initData() {
        searchHeadFragment = SearchHeaderFragment.newInstance();
        replaceFramgent(searchHeadFragment, R.id.fl_search_head);
        searchHeadFragment.setSearchHeaderFragmentListener(this);
        searchContentFragment = SearchContentFragment.newInstance();
        replaceFramgent(searchContentFragment, R.id.fl_search_content);
        getPopupWindow();
    }


    @Override
    public void onChange(String key) {

    }

    /**
     * 创建PopupWindow
     */
    protected void initPopuptWindow() {
        // TODO Auto-generated method stub
        // 获取自定义布局文件activity_popupwindow_left.xml的视图
        View popupWindow_view = getActivity().getLayoutInflater().inflate(R.layout.popupwindow_search, null,
                false);
        // 创建PopupWindow实例,200,LayoutParams.MATCH_PARENT分别是宽度和高度
        popupWindow = new PopupWindow(popupWindow_view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        // 设置动画效果
//        popupWindow.setAnimationStyle(R.style.AnimationFade);
        // 点击其他地方消失
        popupWindow_view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                    popupWindow = null;
                }
                return false;
            }
        });
    }

    /***
     * 获取PopupWindow实例
     */
    private void getPopupWindow() {
        if (null != popupWindow) {
            popupWindow.dismiss();
            return;
        } else {
            initPopuptWindow();
        }
    }
}
