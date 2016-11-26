package com.moonsister.tcjy.main.model;


import com.hickey.tool.base.BaseIModel;
import com.hickey.tool.constant.EnumConstant;

/**
 * Created by jb on 2016/8/13.
 */
public interface BuyVipFragmentModel extends BaseIModel {
    void buyVIP(EnumConstant.PayType payType, int number, String phone, onLoadDateSingleListener<String> listener);


}
