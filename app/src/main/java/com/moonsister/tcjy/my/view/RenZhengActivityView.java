package com.moonsister.tcjy.my.view;

import com.hickey.network.bean.BackTermsBean;
import com.moonsister.tcjy.base.BaseIView;


/**
 * Created by x on 2016/9/3.
 */
public interface RenZhengActivityView extends BaseIView {
    void success(BackTermsBean backTermsBean);
    void finishPage();

}
