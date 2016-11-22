package com.moonsister.tcjy.my.widget;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hickey.network.bean.BaseDataBean;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.adapter.MyOrderAdapter;
import com.moonsister.tcjy.base.BaseFragment;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tcjy.widget.XListView;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by jb on 2016/6/27.
 */
public class AllFragment extends BaseFragment {
    @Bind(R.id.list)
    XListView xRecyclerView;
    private static int mLayoutId;
    public enum  OrderType{
        all_order,

    }
    public static BaseFragment newInstance(@LayoutRes @NonNull int layoutId) {
        mLayoutId = layoutId;
        BaseFragment allFragment = new AllFragment();
        return allFragment;
    }
    private void swicthLayout(){
//        switch ()
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return UIUtils.inflateLayout(mLayoutId);
    }

    @Override
    protected void initData() {
        xRecyclerView.setVerticalLinearLayoutManager();
        ArrayList<BaseDataBean> objects = new ArrayList<BaseDataBean>();
        for (int i = 0; i < 10; i++)
            objects.add(new BaseDataBean());
        xRecyclerView.setAdapter(new MyOrderAdapter(objects));
    }
}
