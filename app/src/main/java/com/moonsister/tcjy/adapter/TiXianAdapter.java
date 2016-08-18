package com.moonsister.tcjy.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseRecyclerViewAdapter;
import com.moonsister.tcjy.base.BaseRecyclerViewHolder;
import com.moonsister.tcjy.bean.TiXinrRecordBean;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tcjy.viewholder.TixianViewHolder;

import java.util.List;

/**
 * Created by jb on 2016/7/2.
 */
public class TiXianAdapter extends BaseRecyclerViewAdapter<TiXinrRecordBean.DataBean> {

    private TixianViewHolder tixianViewHolder;

    public TiXianAdapter(List<TiXinrRecordBean.DataBean> list) {
        super(list);
    }

    @Override
    protected View initRootView(ViewGroup parent, int viewType) {
        View view = UIUtils.inflateLayout(R.layout.item_tixian);
        tixianViewHolder = new TixianViewHolder(view);
        return tixianViewHolder.getRootView();
    }

    @Override
    protected BaseRecyclerViewHolder getBaseViewHolder(View v, int viewType) {
        return tixianViewHolder;
    }
}
