package com.moonsister.tcjy.home.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseAdapter;
import com.moonsister.tcjy.base.BaseFragment;
import com.moonsister.tcjy.home.presenetr.SearchFragmentPersenter;
import com.moonsister.tcjy.home.presenetr.SearchFragmentPersenterImpl;
import com.moonsister.tcjy.home.view.SearchFragmentView;
import com.moonsister.tcjy.utils.StringUtis;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jb on 2016/8/26.
 */
public class SearchFragment extends BaseFragment implements SearchHeaderFragment.onSearchHeaderFragmentListener, SearchFragmentView {

    @Bind(R.id.fl_search_head)
    FrameLayout flSearchHead;
    @Bind(R.id.fl_search_content)
    FrameLayout flSearchContent;
    private SearchHeaderFragment searchHeadFragment;
    private SearchContentFragment searchContentFragment;
    private PopupWindow popupWindow;
    private ListView lv;
    private SearchFragmentPersenter persenter;
    private String key;

    public static Fragment newInstance() {
        return new SearchFragment();
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        persenter = new SearchFragmentPersenterImpl();
        persenter.attachView(this);
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    protected void initData() {
        searchHeadFragment = SearchHeaderFragment.newInstance();
        replaceFramgent(searchHeadFragment, R.id.fl_search_head);
        searchHeadFragment.setSearchHeaderFragmentListener(this);
        searchContentFragment = SearchContentFragment.newInstance();
        replaceFramgent(searchContentFragment, R.id.fl_search_content);

    }


    @Override
    public void onChange(String key) {
        this.key = key;
        if (StringUtis.isEmpty(key)) {
            if (popupWindow != null && popupWindow.isShowing()) {
                popupWindow.dismiss();
                popupWindow = null;
            }
        } else {
            if (popupWindow == null || !popupWindow.isShowing()) {
                initPopuptWindow();
            } else if (!popupWindow.isShowing()) {
                popupWindow.showAsDropDown(flSearchHead);
            }
            persenter.loadKeyMate(key);
        }
    }


    /**
     * 创建PopupWindow
     */
    protected void initPopuptWindow() {
        // TODO Auto-generated method stub
        // 获取自定义布局文件activity_popupwindow_left.xml的视图
        View popupWindow_view = getActivity().getLayoutInflater().inflate(R.layout.popupwindow_search, null,
                false);
        lv = (ListView) popupWindow_view.findViewById(R.id.lv);

        // 创建PopupWindow实例,200,LayoutParams.MATCH_PARENT分别是宽度和高度
        popupWindow = new PopupWindow(popupWindow_view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        // 设置动画效果
//        popupWindow.setAnimationStyle(R.style.AnimationFade);
        popupWindow.setFocusable(false);

        // 设置允许在外点击消失
        popupWindow.setOutsideTouchable(false);
        // 设置背景，这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //软键盘不会挡着popupwindow
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);

        //监听菜单的关闭事件
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
            }
        });
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
        // 这里是位置显示方式,在屏幕的左侧
        popupWindow.showAsDropDown(flSearchHead);
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
    public void setKeyMate(List<String> data) {
        if (data == null || data.size() == 0) {
            if (popupWindow != null) {
                popupWindow.dismiss();
                popupWindow = null;
            }
            return;
        }
        if (lv != null) {
            KeyMateAdapter keyMateAdapter = new KeyMateAdapter();
            keyMateAdapter.addCollection(data);
            lv.setAdapter(keyMateAdapter);
        }
    }

    public class KeyMateAdapter extends BaseAdapter<String> {


        @Override
        protected View newView(Context context, int position, ViewGroup parent) {
            TextView view = new TextView(context);
//            view.setTextSize(context.getResources().getDimension(R.dimen.text_size_13));
            view.setTextColor(context.getResources().getColor(R.color.text_gray_778998));
            view.setPadding(30, 15, 30, 15);
            return view;
        }

        @Override
        protected void bindView(View view, int position, String s) {
            if (!StringUtis.isEmpty(key)) {
                int color = view.getContext().getResources().getColor(R.color.yellow_ffd305);
                SpannableStringBuilder builder = new SpannableStringBuilder(s);
                builder.setSpan(new ForegroundColorSpan(color), 0, key.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                ((TextView) view).setText(builder);
            }
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }
    }
}
