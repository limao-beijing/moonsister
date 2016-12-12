package com.moonsister.tcjy.main.model;

import com.hickey.network.ModuleServerApi;
import com.hickey.network.bean.resposen.IndividualResumeBean;
import com.hickey.tool.base.BaseResponse;
import com.hyphenate.easeui.mvp.model.ObservableMapUtils;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.manager.UserInfoManager;

import rx.Observable;

/**
 * Created by jb on 2016/12/1.
 */
public class IndividualResumeModelImpl implements IndividualResumeModel {
    @Override
    public void loadInitData(String uid, onLoadDateSingleListener<IndividualResumeBean> listenter) {
        Observable<BaseResponse<IndividualResumeBean>> observable = ModuleServerApi.getAppAPI().getIndividualResume(uid, "1", UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
        ObservableMapUtils.$_Map(observable, DataType.DATA_ZERO, listenter);
    }
}
