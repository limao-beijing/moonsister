package com.moonsister.tcjy.my.model;

import com.hickey.network.bean.BaseBean;
import com.hickey.tool.base.BaseIModel;


/**
 * Created by x on 2016/8/27.
 */
public interface InsertActivityModel extends BaseIModel {
//    void loadByIdData(int tagid, String tagname,int img, EnumConstant.HomeTopFragmentTop homeType,onLoadDateSingleListener<InsertBaen> listener);

    void loadData(int tagid, String tagname, int img, onLoadDateSingleListener<BaseBean> listener);

    void sendData(String tlist, onLoadDateSingleListener<BaseBean> listener);

}

