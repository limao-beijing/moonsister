package com.moonsister.tcjy.my.widget;

import android.view.View;

import com.moonsister.tcjy.adapter.MyThreeFragmentAdapter;
import com.moonsister.tcjy.base.BaseListFragment;
import com.moonsister.tcjy.viewholder.MyThreeFragmentHeaderViewHoder;

/**
 * Created by jb on 2016/9/22.
 */
public class MyThreeFragment extends BaseListFragment<MyThreeFragmentAdapter> {
    private MyThreeFragmentHeaderViewHoder mHeaderViewHoder;

    @Override
    public MyThreeFragmentAdapter setAdapter() {

        return new MyThreeFragmentAdapter(null);
    }

    @Override
    protected void onRefresh() {

    }

    @Override
    protected void onLoadMore() {

    }

    @Override
    protected View addHeaderView() {
        mHeaderViewHoder = new MyThreeFragmentHeaderViewHoder();
        return mHeaderViewHoder.getContentView();
    }
}
