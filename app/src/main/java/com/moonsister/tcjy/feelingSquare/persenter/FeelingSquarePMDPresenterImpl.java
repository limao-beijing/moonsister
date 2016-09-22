package com.moonsister.tcjy.feelingSquare.persenter;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.FeelingSquarePMDBean;
import com.moonsister.tcjy.feelingSquare.model.FeelingSquarePMDModel;
import com.moonsister.tcjy.feelingSquare.model.FeelingSquarePMDModelImpl;
import com.moonsister.tcjy.feelingSquare.view.FeelingSquarePMDView;
import com.moonsister.tcjy.find.model.NearbyActivityModel;
import com.moonsister.tcjy.find.model.NearbyActivityModelImpl;
import com.moonsister.tcjy.find.view.NearbyActivityView;

/**
 * Created by Administrator on 2016/9/21.
 */
public class FeelingSquarePMDPresenterImpl implements FeelingSquarePMDPresenter, BaseIModel.onLoadDateSingleListener<FeelingSquarePMDBean> {
    private FeelingSquarePMDView view;
    private FeelingSquarePMDModel model;


    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(FeelingSquarePMDView feelingSquarePMDView) {
        this.view=feelingSquarePMDView;
        model = new FeelingSquarePMDModelImpl();

        model.getCotent("",this);

    }


    @Override
    public void onSuccess(FeelingSquarePMDBean feelingSquarePMDBean, BaseIModel.DataType dataType) {
        view.setData(feelingSquarePMDBean.getData());
    }

    @Override
    public void onFailure(String msg) {
        view.transfePageMsg(msg);
    }
}
