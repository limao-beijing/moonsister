package com.moonsister.tcjy.main.model;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.CommentDataListBean;
import com.moonsister.tcjy.bean.DefaultDataBean;

/**
 * Created by pc on 2016/6/8.
 */
public interface DynamincDatailsModel extends BaseIModel {
    void loadCommentListData(String id, int page, BaseIModel.onLoadListDateListener<CommentDataListBean.DataBean> listener);

    void sendComment(String id, String content, String pid, onLoadDateSingleListener<DefaultDataBean> listenter);
}
