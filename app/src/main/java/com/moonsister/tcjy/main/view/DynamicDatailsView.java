package com.moonsister.tcjy.main.view;

import com.hickey.network.bean.CommentDataListBean;
import com.hickey.network.bean.DynamicDatailsBean;
import com.moonsister.tcjy.base.BaseIView;

import java.util.List;

/**
 * Created by jb on 2016/6/8.
 */
public interface DynamicDatailsView extends BaseIView {
    void loadData(List<CommentDataListBean.DataBean> datas);

    void CommentSuccess();

    void deleteDynamic(String id);

    void setDynamicDatails(DynamicDatailsBean bean);
}
