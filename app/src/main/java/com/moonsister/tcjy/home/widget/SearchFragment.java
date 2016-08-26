package com.moonsister.tcjy.home.widget;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseFragment;

/**
 * Created by jb on 2016/8/26.
 */
public class SearchFragment extends BaseFragment {

    private SearchHeaderFragment searchHeadFragment;
    private SearchContentFragment searchContentFragment;

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
        searchContentFragment = SearchContentFragment.newInstance();
        replaceFramgent(searchContentFragment, R.id.fl_search_content);
    }


}
