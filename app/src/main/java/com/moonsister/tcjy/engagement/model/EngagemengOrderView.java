package com.moonsister.tcjy.engagement.model;

import com.hickey.network.bean.EngagemengOrderBean;
import com.moonsister.tcjy.base.BaseIView;


/**
 * Created by jb on 2016/9/27.
 */
public interface EngagemengOrderView  extends BaseIView{
    void submitSuccess(String id);

    void notLevel();

    void setData(EngagemengOrderBean bean);

}
