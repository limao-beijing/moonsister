package com.moonsister.tcjy.my.persenter;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.InsertBaen;
import com.moonsister.tcjy.my.model.InsertActivityImpl;
import com.moonsister.tcjy.my.model.InsertActivityModel;
import com.moonsister.tcjy.my.view.InsertActivityView;
import com.moonsister.tcjy.utils.EnumConstant;

/**
 * Created by x on 2016/8/27.
 */
public class InsertActivityPersenterImpl implements InsertActivityPersenter, BaseIModel.onLoadDateSingleListener<InsertBaen> {
    private InsertActivityView view;
    private InsertActivityModel model;


    @Override
    public void LoadData(int tagid,String tagname,int img) {
        view.showLoading();
        model.loadData(tagid,tagname,img,this);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(InsertActivityView insertActivityView) {
        this.view = insertActivityView;
         model=new InsertActivityImpl();

    }


    @Override
    public void onSuccess(InsertBaen insertBaen, BaseIModel.DataType dataType) {

    }

    @Override
    public void onFailure(String msg) {

    }
}
