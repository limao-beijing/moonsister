package com.moonsister.tcjy.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.base.BaseRecyclerViewAdapter;
import com.moonsister.tcjy.base.BaseRecyclerViewHolder;
import com.moonsister.tcjy.bean.DefaultDataBean;
import com.moonsister.tcjy.bean.InsertBaen;
import com.moonsister.tcjy.bean.SearchReasonBaen;
import com.moonsister.tcjy.home.widget.SearchReasonActivity;
import com.moonsister.tcjy.main.model.UserActionModelImpl;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.StringUtis;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tcjy.viewholder.SearchViewHolder;

import java.util.List;

/**
 * Created by jb on 2016/7/10.
 */
public class SearchAdapter extends BaseRecyclerViewAdapter<SearchReasonBaen.DataBean> {

    private SearchViewHolder holder;
    private SearchReasonActivity searchReasonActivityView;

    public SearchAdapter(List<SearchReasonBaen.DataBean> list) {
        super(list);
    }

    @Override
    protected View initRootView(ViewGroup parent, int viewType) {
        View view = UIUtils.inflateLayout(R.layout.item_search_reason, parent);
        holder = new SearchViewHolder(view);
        holder.setAdapter(this);
        return holder.getRootView();
    }

    @Override
    protected BaseRecyclerViewHolder getBaseViewHolder(View v, int viewType) {
        return holder;
    }

    public void setClick(Integer position) {
        if (datas.size() - 1 >= position) {
            SearchReasonBaen.DataBean dataBean = datas.get(position);
            if (StringUtis.equals(dataBean.getIsfollow(), "1")) {
                if (searchReasonActivityView != null)
                    searchReasonActivityView.transfePageMsg(UIUtils.getStringRes(R.string.already) + UIUtils.getStringRes(R.string.wacth));
                return;
            }
            searchReasonActivityView.showLoading();
            UserActionModelImpl userActionModel = new UserActionModelImpl();
            userActionModel.wacthAction(dataBean.getUid(), "1", new BaseIModel.onLoadDateSingleListener<DefaultDataBean>() {
                @Override
                public void onSuccess(DefaultDataBean bean, BaseIModel.DataType dataType) {
                    if (bean != null) {
                        if (StringUtis.equals(bean.getCode(), AppConstant.code_request_success)) {
                            dataBean.setIsfollow("1");
                            onRefresh();
                        }
                        searchReasonActivityView.transfePageMsg(bean.getMsg());
                    } else {
                        searchReasonActivityView.transfePageMsg(UIUtils.getStringRes(R.string.request_failed));
                    }
                    searchReasonActivityView.hideLoading();
                }

                @Override
                public void onFailure(String msg) {
                    searchReasonActivityView.hideLoading();
                    searchReasonActivityView.transfePageMsg(msg);
                }
            });

        }
    }

    @Override
    public void onItemclick(View view, int position) {
        ActivityUtils.startDynamicActivity(datas.get(position).getUid());
    }

    public void setSearchReasonActivityView(SearchReasonActivity searchReasonActivityView) {
        this.searchReasonActivityView = searchReasonActivityView;
    }
}
