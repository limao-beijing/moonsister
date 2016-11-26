package com.moonsister.tcjy.center.presenter;


import com.hickey.tool.base.BaseIPresenter;
import com.moonsister.tcjy.center.view.BuyDynamicRedPackketView;

/**
 * Created by jb on 2016/6/29.
 */
public interface BuyDynamicRedPackketPersenter extends BaseIPresenter<BuyDynamicRedPackketView> {
    void alipay(String id);

    void weixinPay(String id);

    void getPics(String id);

    void singBuy(String id);

    void loadVipRule();

}
