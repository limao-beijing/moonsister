package com.moonsister.tcjy.main.view;

import com.hickey.network.bean.DynamicItemBean;
import com.hickey.network.bean.UserInfoDetailBean;
import com.moonsister.tcjy.base.BaseIView;

import java.util.List;

/**
 * Created by pc on 2016/6/6.
 */
public interface DynamicView extends BaseIView {
    void loadUserinfo(List<DynamicItemBean> list);

    void switch2RewardActivity();

    void switch2SendMsgActivity();

    void switch2AppointmentActivity();

    void swicth2SendFlowersActivity();


    void setUserInfodetail(UserInfoDetailBean bean);

    void pageFinish();

    void deleteDynamic(String id);

    void upLoadDynamic();
}
