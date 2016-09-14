package com.moonsister.tcjy.my.view;

import com.moonsister.tcjy.base.BaseIView;
import com.moonsister.tcjy.bean.PersonalReviseMessageBean;

/**
 * Created by x on 2016/9/10.
 */
public interface PersonalReviseActivityView extends BaseIView {
    void success(PersonalReviseMessageBean personalReviseMessageBean);

    void submitSuccess();
}
