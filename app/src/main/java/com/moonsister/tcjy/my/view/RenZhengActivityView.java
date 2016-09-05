package com.moonsister.tcjy.my.view;

import com.moonsister.tcjy.base.BaseIView;
import com.moonsister.tcjy.bean.BackTermsBean;

/**
 * Created by x on 2016/9/3.
 */
public interface RenZhengActivityView extends BaseIView {
    void success(BackTermsBean backTermsBean);
    void finishPage();

}
