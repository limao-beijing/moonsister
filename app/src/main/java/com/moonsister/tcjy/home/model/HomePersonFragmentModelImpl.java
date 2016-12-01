package com.moonsister.tcjy.home.model;

import com.hickey.network.ServerApi;
import com.hickey.network.bean.EngagemengRecommendBean;
import com.hickey.tool.base.BaseResponse;
import com.hyphenate.easeui.mvp.model.ObservableMapUtils;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.manager.UserInfoManager;

import java.util.List;

import rx.Observable;

/**
 * Created by jb on 2016/11/29.
 */
public class HomePersonFragmentModelImpl implements HomePersonFragmentModel {
    @Override
    public void loadInitData(int page, String type, onLoadDateSingleListener<List<EngagemengRecommendBean.DataBean>> listenter) {
        Observable<BaseResponse<List<EngagemengRecommendBean.DataBean>>> observable = ServerApi.getAppAPI().gethomePersonInitData(page, type, UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
        ObservableMapUtils.$_Map(observable, DataType.DATA_ZERO, listenter);
    }
}
