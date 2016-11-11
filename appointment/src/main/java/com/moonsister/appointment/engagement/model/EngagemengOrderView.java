package com.moonsister.appointment.engagement.model;

import com.moonsister.tcjy.base.BaseIView;
import com.moonsister.tcjy.bean.EngagemengOrderBean;

/**
 * Created by jb on 2016/9/27.
 */
public interface EngagemengOrderView  extends BaseIView{
    void submitSuccess();

    void notLevel();

    void setData(EngagemengOrderBean bean);

}
