package com.moonsister.tcjy.my.view;

import com.hickey.network.bean.PersonalReviseMessageBean;
import com.hickey.tool.base.BaseIView;


/**
 * Created by x on 2016/9/10.
 */
public interface PersonalReviseActivityView extends BaseIView {
    void success(PersonalReviseMessageBean personalReviseMessageBean);

    void submitSuccess();
}
