package com.moonsister.tcjy.my.view;

import com.hickey.network.bean.PersonalMessageBean;
import com.hickey.tool.base.BaseIView;


/**
 * Created by x on 2016/9/2.
 */
public interface PersonalActivityView extends BaseIView {
    void success(PersonalMessageBean getPersonalBean);
    void person();
}
