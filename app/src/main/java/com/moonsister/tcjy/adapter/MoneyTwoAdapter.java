package com.moonsister.tcjy.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.hickey.network.bean.BalanceBean;
import com.hickey.tool.base.BaseIView;
import com.hickey.tool.base.BaseRecyclerViewAdapter;
import com.hickey.tool.base.BaseRecyclerViewHolder;
import com.hickey.tool.widget.UIUtils;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.viewholder.MoneyTwoHolder;

import java.util.List;

/**
 * Created by x on 2016/9/2.
 */
public class MoneyTwoAdapter extends BaseRecyclerViewAdapter<BalanceBean.DataBean> {
    private BaseIView view;
    private int pageType;
    private MoneyTwoHolder viewHoler;

    public MoneyTwoAdapter(List<BalanceBean.DataBean> data, BaseIView view) {
        super(data);
        this.view = view;
    }

    @Override
    protected View initRootView(ViewGroup parent, int viewType) {
        View view = UIUtils.inflateLayout(R.layout.onefragmentitem);
        viewHoler = new MoneyTwoHolder(view);
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
