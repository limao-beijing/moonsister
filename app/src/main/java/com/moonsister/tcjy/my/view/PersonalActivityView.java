package com.moonsister.tcjy.my.view;

import com.moonsister.tcjy.base.BaseIView;
import com.moonsister.tcjy.bean.PersonalMessageBean;

import org.json.JSONException;

/**
 * Created by x on 2016/9/2.
 */
public interface PersonalActivityView extends BaseIView {
    void success(PersonalMessageBean getPersonalBean);
    void person();
}
