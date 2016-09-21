package com.moonsister.tcjy.my.view;

import com.moonsister.tcjy.base.BaseIView;
import com.moonsister.tcjy.bean.PersonalMessageFenBean;

/**
 * Created by x on 2016/9/21.
 */
public interface PersonalActivityFenView extends BaseIView {
    void success(PersonalMessageFenBean getPersonalBean);
    void person();
}
