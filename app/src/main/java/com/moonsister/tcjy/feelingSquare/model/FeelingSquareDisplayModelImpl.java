package com.moonsister.tcjy.feelingSquare.model;

import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.ServerApi;
import com.moonsister.tcjy.bean.HomeTopItemBean;
import com.moonsister.tcjy.bean.RankBean;
import com.moonsister.tcjy.bean.model.FeelingSquareDisplayBean;
import com.moonsister.tcjy.manager.UserInfoManager;

import rx.Observable;

/**
 * Created by Administrator on 2016/9/20.
 */
public class FeelingSquareDisplayModelImpl implements FeelingSquareDisplayModel {

    @Override
    public void loadData(onLoadListDateListener<FeelingSquareDisplayBean> listener) {
       // Observable<FeelingSquareDisplayBean> observable = ServerApi.getAppAPI().getInfoDisplay();

    }
}
