package com.moonsister.tcjy.main.view;


import com.hickey.tool.base.BaseIView;

/**
 * Created by jb on 2016/8/13.
 */
public interface BuyVipFragmentView extends BaseIView {
    void buySuccess();

    void typePay(int type, String phone);

}
