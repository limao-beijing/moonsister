package com.moonsister.tcjy.main.presenter;

import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.base.BaseIModel.onLoadDateSingleListener;
import com.moonsister.tcjy.bean.DefaultDataBean;
import com.moonsister.tcjy.bean.DynamicItemBean;
import com.moonsister.tcjy.bean.UserInfoDetailBean;
import com.moonsister.tcjy.bean.UserInfoListBean;
import com.moonsister.tcjy.main.model.UserActionModelImpl;
import com.moonsister.tcjy.main.model.DynamicModel;
import com.moonsister.tcjy.main.model.DynamicModelImpl;
import com.moonsister.tcjy.main.view.DynamicView;
import com.moonsister.tcjy.utils.LogUtils;
import com.moonsister.tcjy.utils.StringUtis;
import com.moonsister.tcjy.utils.UIUtils;

import java.util.List;

/**
 * Created by pc on 2016/6/6.
 */
public class DynamicPresenterImpl implements DynamicPresenter, onLoadDateSingleListener<UserInfoDetailBean>, BaseIModel.onLoadListDateListener<DynamicItemBean> {
    private DynamicView mUserInfoView;
    private DynamicModel mUserInfoModel;
    private int page = 1;
    private String upID;

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(DynamicView userInfoView) {
        this.mUserInfoView = userInfoView;
        mUserInfoModel = new DynamicModelImpl();
    }

    @Override
    public void loadonRefreshData(String userId) {
        upID = null;
        mUserInfoView.showLoading();
        page = 1;
        mUserInfoModel.loadUserInfoData(userId, page, this);
    }

    @Override
    public void loadLoadMoreData(String userId) {
        mUserInfoView.showLoading();
        mUserInfoModel.loadUserInfoData(userId, page, this);
    }

    @Override
    public void switchNavigation(int id) {
        switch (id) {
            case R.id.tv_appointment:
                mUserInfoView.switch2AppointmentActivity();
                break;
            case R.id.tv_send_flowers:
                mUserInfoView.swicth2SendFlowersActivity();
                break;
            case R.id.single_send_msg:
            case R.id.tv_send_msg:
                mUserInfoView.switch2SendMsgActivity();
                break;
            case R.id.tv_reward:
                mUserInfoView.switch2RewardActivity();
                break;
            case R.id.action_back:
                mUserInfoView.pageFinish();
                break;
        }
    }

    @Override
    public void loadUserInfodetail(String uid) {
        mUserInfoView.showLoading();
        mUserInfoModel.loadUserInfodetail(uid, this);
    }

    @Override
    public void deleteDynamic(String id) {
        mUserInfoView.showLoading();
        UserActionModelImpl actionModel = new UserActionModelImpl();
        actionModel.deleteDynamic(id, new BaseIModel.onLoadDateSingleListener<DefaultDataBean>() {
            @Override
            public void onSuccess(DefaultDataBean bean, BaseIModel.DataType dataType) {
                if (StringUtis.equals(bean.getCode(), AppConstant.code_request_success)) {
                    mUserInfoView.deleteDynamic(id);
                }
                mUserInfoView.transfePageMsg(bean.getMsg());
                mUserInfoView.hideLoading();
            }

            @Override
            public void onFailure(String msg) {
                mUserInfoView.transfePageMsg(msg);
                mUserInfoView.hideLoading();
            }
        });
    }

    @Override
    public void upDynamic(String id) {
        mUserInfoView.showLoading();
        UserActionModelImpl actionModel = new UserActionModelImpl();
        actionModel.upDynamic("1", id, new BaseIModel.onLoadDateSingleListener<DefaultDataBean>() {
            @Override
            public void onSuccess(DefaultDataBean bean, BaseIModel.DataType dataType) {
                if (StringUtis.equals(bean.getCode(), AppConstant.code_request_success)) {
                    mUserInfoView.upLoadDynamic();
                }
                mUserInfoView.transfePageMsg(bean.getMsg());
                mUserInfoView.hideLoading();
            }

            @Override
            public void onFailure(String msg) {
                mUserInfoView.transfePageMsg(msg);
                mUserInfoView.hideLoading();
            }
        });
    }

    @Override
    public void delUpDynamic(String id) {
        mUserInfoView.showLoading();
        UserActionModelImpl actionModel = new UserActionModelImpl();
        actionModel.upDynamic("2", id, new BaseIModel.onLoadDateSingleListener<DefaultDataBean>() {
            @Override
            public void onSuccess(DefaultDataBean bean, BaseIModel.DataType dataType) {
                if (StringUtis.equals(bean.getCode(), AppConstant.code_request_success)) {
                    mUserInfoView.upLoadDynamic();
                }
                mUserInfoView.transfePageMsg(bean.getMsg());
                mUserInfoView.hideLoading();
            }

            @Override
            public void onFailure(String msg) {
                mUserInfoView.transfePageMsg(msg);
                mUserInfoView.hideLoading();
            }
        });
    }


    @Override
    public void onSuccess(UserInfoDetailBean bean, BaseIModel.DataType type) {
        mUserInfoView.setUserInfodetail(bean);
        mUserInfoView.hideLoading();

    }

    @Override
    public void onSuccess(List<DynamicItemBean> t, BaseIModel.DataType dataType) {

        if (t != null) {
            if (t.size() != 0) {
                if (page == 1) {

                    DynamicItemBean beanDataList = t.get(t.size() - 1);
                    if (StringUtis.equals(beanDataList.getIstop(), "1")) {
                        upID = beanDataList.getLatest_id();
                        t.remove(beanDataList);
                        DynamicItemBean beanDataList1 = null;
                        for (DynamicItemBean bean : t) {
                            if (StringUtis.equals(bean.getLatest_id(), upID)) {
                                beanDataList1 = bean;
                                break;
                            }
                        }
                        if (beanDataList1 != null)
                            t.remove(beanDataList1);
                        t.add(0, beanDataList);
                    }


                } else {
                    if (!StringUtis.isEmpty(upID)) {
                        DynamicItemBean beanDataList1 = null;
                        for (DynamicItemBean bean : t) {
                            if (StringUtis.equals(bean.getLatest_id(), upID)) {
                                beanDataList1 = bean;
                                break;
                            }
                        }
                        if (beanDataList1 != null)
                            t.remove(beanDataList1);
                    }
                }


            }
            page++;
        }
        mUserInfoView.loadUserinfo(t);
        mUserInfoView.hideLoading();

    }

    @Override
    public void onFailure(String msg) {
        LogUtils.e(this, msg);
        mUserInfoView.hideLoading();
        mUserInfoView.transfePageMsg(UIUtils.getStringRes(R.string.request_failed));

    }


}
