package com.moonsister.tcjy.dialogFragment.model;

import com.hickey.network.ModuleServerApi;
import com.hickey.network.bean.resposen.InterestBean;
import com.hickey.tool.base.BaseResponse;
import com.hyphenate.easeui.mvp.model.ObservableMapUtils;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.manager.UserInfoManager;

import java.util.List;

import rx.Observable;

/**
 * Created by jb on 2016/11/30.
 */
public class InterestDialogModelImpl implements InterestDialogModel {
    @Override
    public void loadInitData(onLoadDateSingleListener<Object> listener) {
        Observable<BaseResponse<List<InterestBean>>> observable = ModuleServerApi.getAppAPI().getInterstSelectInit(UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
        ObservableMapUtils.$_Map(observable, DataType.DATA_ZERO, listener);
    }


    @Override
    public void submitService(String content, onLoadDateSingleListener listener) {
        Observable<BaseResponse> observable = ModuleServerApi.getAppAPI().getInterstSubmit(content, UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
        ObservableMapUtils.$(observable, DataType.DATA_ONE, listener);
    }
}
