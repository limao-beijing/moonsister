package com.moonsister.tcjy.my.model;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.BackTermsBean;

/**
 * Created by x on 2016/9/3.
 */
public interface RenZhengActivityModel extends BaseIModel {
    void loadData(onLoadDateSingleListener<BackTermsBean> listener);
//    void upLoadIcon(String iconPath, onLoadDateSingleListener listener);
}
