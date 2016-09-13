package com.moonsister.tcjy.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseIView;
import com.moonsister.tcjy.base.BaseRecyclerViewAdapter;
import com.moonsister.tcjy.base.BaseRecyclerViewHolder;
import com.moonsister.tcjy.bean.PingbiBean;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tcjy.viewholder.FriendViewHoler;
import com.moonsister.tcjy.viewholder.PingbiViewHolder;

import java.util.List;

/**
 * Created by x on 2016/9/14.
 */
public class PingbiAdapter extends BaseRecyclerViewAdapter<PingbiBean.DataBean> {
    private PingbiViewHolder viewHoler;
    private BaseIView view;
    private int pageType;

    public PingbiAdapter(List<PingbiBean.DataBean> list, BaseIView view) {
        super(list);
        this.view = view;
    }

    @Override
    protected View initRootView(ViewGroup parent, int viewType) {
        View view = UIUtils.inflateLayout(R.layout.pingbiactivityitem);
        viewHoler = new PingbiViewHolder(view);
        viewHoler.setAdapter(this);

        return viewHoler.getRootView();
    }

    @Override
    protected BaseRecyclerViewHolder getBaseViewHolder(View v, int viewType) {
        return viewHoler;
    }
    public void setPageType(int pageType) {
        this.pageType = pageType;
    }
}
