package com.moonsister.tcjy.my.view;

import com.moonsister.tcjy.base.BaseIPresenter;
import com.moonsister.tcjy.base.BaseIView;
import com.moonsister.tcjy.bean.BackInsertBean;
import com.moonsister.tcjy.bean.InsertBaen;

/**
 * Created by x on 2016/8/27.
 */
public interface InsertActivityView extends BaseIView{
        void setBasicInfo(InsertBaen getInsertBean);
        void success();
//        void setBasic(BackInsertBean getBackInsertBean);
}
