package com.moonsister.tcjy.main.model;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.utils.EnumConstant;

/**
 * Created by jb on 2016/8/13.
 */
public interface BuyVipFragmentModel extends BaseIModel {
    void buyVIP(EnumConstant.PayType payType, int number, onLoadDateSingleListener<String> listener);


}
