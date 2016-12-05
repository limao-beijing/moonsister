package com.moonsister.tcjy.main.model;

import com.hickey.network.LogUtils;
import com.hickey.network.ModuleServerApi;
import com.hickey.tool.base.BaseIModel;
import com.hickey.tool.base.BaseResponse;
import com.hickey.tool.lang.StringUtis;
import com.hyphenate.easeui.mvp.model.ObservableMapUtils;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.manager.UserInfoManager;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by jb on 2016/12/3.
 */

public class ImageScanModelImpl implements BaseIModel {
    public void upImageScanCount(String lid) {
        if (StringUtis.isEmpty(lid))
            return;
        Observable<BaseResponse<Object>> observable = ModuleServerApi.getAppAPI().getUpImageScanCount(lid, UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
        ObservableMapUtils.getIOObservable(observable).subscribe(new Subscriber<BaseResponse<Object>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                LogUtils.e("ImageScanModelImpl", "  msg ： " + e.getMessage());
            }

            @Override
            public void onNext(BaseResponse<Object> response) {
                LogUtils.e("ImageScanModelImpl", "code : " + response.getCode() + "   msg ： " + response.getMsg());
            }
        });

    }
}
