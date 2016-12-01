package com.moonsister.tcjy.dialogFragment.view;

import com.hickey.network.bean.resposen.InterestBean;
import com.hickey.tool.base.BaseIView;

import java.util.List;

/**
 * Created by jb on 2016/11/30.
 */
public interface InterestDialogView extends BaseIView {
    void setInitData(List<InterestBean> bean);

    void submitSuccess();
}
