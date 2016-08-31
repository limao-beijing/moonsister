package com.moonsister.tcjy.my.persenter;

import com.moonsister.tcjy.base.BaseIPresenter;
import com.moonsister.tcjy.my.view.InsertActivityView;

/**
 * Created by x on 2016/8/27.
 */
public interface InsertActivityPersenter extends BaseIPresenter<InsertActivityView> {
     void LoadData(int tagid,String tagname,int img);
     void sendData(int tlist);
}
