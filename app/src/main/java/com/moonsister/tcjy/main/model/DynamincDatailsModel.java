package com.moonsister.tcjy.main.model;

import com.hickey.network.bean.BaseBean;
import com.hickey.network.bean.CommentDataListBean;
import com.moonsister.tcjy.base.BaseIModel;


/**
 * Created by pc on 2016/6/8.
 */
public interface DynamincDatailsModel extends BaseIModel {
    void loadCommentListData(String id, int page, onLoadListDateListener<CommentDataListBean.DataBean> listener);

    void sendComment(String id, String content, String pid, onLoadDateSingleListener<BaseBean> listenter);

    void loadSingeDyamic(String latest_id, onLoadDateSingleListener<BaseBean> listener);

}
