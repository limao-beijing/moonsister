package com.moonsister.tcjy.my.view;

import com.hickey.network.bean.InsertBaen;
import com.moonsister.tcjy.base.BaseIView;


/**
 * Created by x on 2016/8/27.
 */
public interface InsertActivityView extends BaseIView{
        void setBasicInfo(InsertBaen getInsertBean);
        void success();
//        void setBasic(BackInsertBean getBackInsertBean);
}
