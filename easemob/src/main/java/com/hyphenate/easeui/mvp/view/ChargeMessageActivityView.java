package com.hyphenate.easeui.mvp.view;

import com.hickey.network.bean.resposen.ChargeInitBean;
import com.hickey.network.bean.resposen.ChargeMessageBean;
import com.hickey.tool.base.BaseIView;

/**
 * Created by jb on 2016/11/26.
 */
public interface ChargeMessageActivityView  extends BaseIView{
    void setData(ChargeMessageBean bean);

    void setInitData(ChargeInitBean model);
}
