package com.moonsister.tcjy.my.model;

import com.hickey.network.bean.BaseBean;
import com.hickey.tool.base.BaseIModel;


/**
 * Created by x on 2016/9/3.
 */
public interface RenZhengActivityModel extends BaseIModel {
    void loadData(onLoadDateSingleListener<BaseBean> listener);
//    void sendDynamicPics(EnumConstant.DynamicType dynamicType, String content, List<String> srcdatas, String address, onLoadDateSingleListener defaultDynamicPresenter);
    void submit(String address1, String address2, String text,onLoadDateSingleListener listener);

    void submittt(String address1, String address2, String text,String code,onLoadDateSingleListener listener);

    void submitdata(String str, String order_id, onLoadDateSingleListener listener);
}
