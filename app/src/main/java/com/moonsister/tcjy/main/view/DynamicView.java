package com.moonsister.tcjy.main.view;

import com.moonsister.tcjy.base.BaseIView;
import com.moonsister.tcjy.bean.UserInfoDetailBean;
import com.moonsister.tcjy.bean.UserInfoListBean;

import java.util.List;

/**
 * Created by pc on 2016/6/6.
 */
public interface DynamicView extends BaseIView {
    void loadUserinfo(List<UserInfoListBean.UserInfoListBeanData.UserInfoListBeanDataList> list);

    void switch2RewardActivity();

    void switch2SendMsgActivity();

    void switch2AppointmentActivity();

    void swicth2SendFlowersActivity();


    void setUserInfodetail(UserInfoDetailBean bean);

    void pageFinish();

    void deleteDynamic(String id);

    void upLoadDynamic();
}
