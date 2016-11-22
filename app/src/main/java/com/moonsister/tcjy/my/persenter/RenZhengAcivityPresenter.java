package com.moonsister.tcjy.my.persenter;

import com.moonsister.tcjy.base.BaseIPresenter;
import com.moonsister.tcjy.my.view.RenZhengActivityView;

/**
 * Created by x on 2016/9/3.
 */
public interface RenZhengAcivityPresenter  extends BaseIPresenter<RenZhengActivityView> {
    void LoadData();
//    void sendDynamic(EnumConstant.DynamicType dynamicType, String content, List<String> datas, String address);
    void submit(String address1, String address2,String text);
    void submitdata(String str,String order_id);
//    void sendRenZ;heng(EnumConstant.DynamicType dynamicType, String address);
}
