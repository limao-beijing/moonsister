package com.moonsister.tcjy.my.view;

import com.hickey.network.bean.DynamicItemBean;
import com.hickey.network.bean.UserInfoDetailBean;
import com.hickey.tool.base.BaseIView;

import java.util.List;

/**
 * Created by jb on 2016/6/27.
 */
public interface MyFragmentView extends BaseIView {
    void swich2PersonRed();

    void swich2WithdRawDeposit();

    void swich2Appointment();

    void swich2PersonOrder();

    void swich2PersonSetting();

    void setListData(List<DynamicItemBean> t);

    void setUserInfo(UserInfoDetailBean bean);

    void setUserBackground(String obj);

    void deleteDynamic(String id);

    void upLoadDynamic();

}
