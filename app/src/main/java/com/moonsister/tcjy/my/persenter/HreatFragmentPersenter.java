package com.moonsister.tcjy.my.persenter;


import com.hickey.tool.base.BaseIPresenter;
import com.moonsister.tcjy.my.view.HreatFragmentView;

/**
 * Created by x on 2016/9/8.
 */
public interface HreatFragmentPersenter extends BaseIPresenter<HreatFragmentView> {
    void PaySubmit(String uid);
}
