package com.moonsister.tcjy.my.model;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.BackInsertBean;
import com.moonsister.tcjy.bean.BaseBean;
import com.moonsister.tcjy.bean.InsertBaen;
import com.moonsister.tcjy.my.persenter.InsertActivityPersenterImpl;
import com.moonsister.tcjy.utils.EnumConstant;

/**
 * Created by x on 2016/8/27.
 */
public interface InsertActivityModel extends BaseIModel{
//    void loadByIdData(int tagid, String tagname,int img, EnumConstant.HomeTopFragmentTop homeType,onLoadDateSingleListener<InsertBaen> listener);

    void loadData(int tagid, String tagname, int img, onLoadDateSingleListener<BaseBean> listener);

    void sendData(String tlist, onLoadDateSingleListener<BaseBean> listener);

}

