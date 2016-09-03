package com.moonsister.tcjy.main.model;

import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.ServerApi;
import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.DynamicItemBean;
import com.moonsister.tcjy.bean.UserInfoDetailBean;
import com.moonsister.tcjy.bean.UserInfoListBean;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.LogUtils;
import com.moonsister.tcjy.utils.UIUtils;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by pc on 2016/6/6.
 */
public class DynamicModelImpl implements DynamicModel {


    @Override
    public void loadUserInfoData(String userId, int page, BaseIModel.onLoadListDateListener<DynamicItemBean> listener) {
        String authcode = UserInfoManager.getInstance().getAuthcode();
        ServerApi.getAppAPI().getPersonDynamincList(userId, page, 0, authcode, AppConstant.CHANNEL_ID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UserInfoListBean>() {
                    @Override
                    public void onCompleted() {


                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onFailure(e.getMessage());
                    }

                    @Override
                    public void onNext(UserInfoListBean userInfoListBean) {
                        if (userInfoListBean != null) {
                            UserInfoListBean.UserInfoListBeanData data = userInfoListBean.getData();
                            if (data != null) {
                                List<DynamicItemBean> list = data.getList();
                                listener.onSuccess(list, BaseIModel.DataType.DATA_ZERO);
                            }
                        } else
                            listener.onFailure(UIUtils.getStringRes(R.string.request_failed));
                    }
                });

    }

    @Override
    public void loadUserInfodetail(String uid, BaseIModel.onLoadDateSingleListener listener) {
        String authcode = UserInfoManager.getInstance().getAuthcode();
        ServerApi.getAppAPI().getUserInfoDetail(uid, authcode, AppConstant.CHANNEL_ID).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<UserInfoDetailBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onFailure(e.getMessage());
                    }

                    @Override
                    public void onNext(UserInfoDetailBean userInfoDetailBean) {
                        listener.onSuccess(userInfoDetailBean, BaseIModel.DataType.DATA_ONE);
                        LogUtils.e(this, userInfoDetailBean.toString());
                    }
                });
    }


}
