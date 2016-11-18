package com.moonsister.tcjy.engagement.model;

import com.moonsister.tcjy.base.BaseIView;
import com.moonsister.tcjy.bean.EngagemengOrderBean;

/**
 * Created by jb on 2016/9/27.
 */
public interface EngagemengOrderView  extends BaseIView{
    void submitSuccess(String id);

    void notLevel();

    void setData(EngagemengOrderBean bean);

}
