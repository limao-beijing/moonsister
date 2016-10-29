package com.moonsister.tcjy.main.presenter;

import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.CertificationStatusBean;
import com.moonsister.tcjy.bean.PersonInfoDetail;
import com.moonsister.tcjy.bean.RongyunBean;
import com.moonsister.tcjy.bean.UserFriendListBean;
import com.moonsister.tcjy.bean.UserPermissionBean;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.main.model.MainActivityModel;
import com.moonsister.tcjy.main.model.MainActivityModelImpl;
import com.moonsister.tcjy.main.view.MainView;
import com.moonsister.tcjy.manager.IMManager;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.LogUtils;
import com.moonsister.tcjy.utils.StringUtis;
import com.moonsister.tcjy.utils.UIUtils;

import java.util.List;


/**
 * Created by pc on 2016/6/1.
 */
public class MainPresenterImpl implements MainPresenter, BaseIModel.onLoadDateSingleListener {
    private MainView view;
    private MainActivityModel model;

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(MainView mainView) {
        this.view = mainView;
        model = new MainActivityModelImpl();
    }

    @Override
    public void switchNavigation(int id) {
        switch (id) {
            case R.id.tv_home_page:
                view.switch2Home();
                break;
            case R.id.tv_im_page:
                view.switch2IM();
                break;
            case R.id.tv_center_page:
                view.switch2Center();
                break;
            case R.id.tv_find_page:
                view.switchFind();
                break;
            case R.id.tv_my_page:
                view.switch2My();
                break;
        }
    }

    @Override
    public void getRongyunKey() {
        model.getRongyunKey(this);

    }

    /**
     * 登录融云
     */
    @Override
    public void loginRongyun() {
        /**
         * 在另一个设备登录
         */

        IMManager.getInstance().setConnectionStatusListener(new IMManager.ConnectCallback() {
            @Override
            public void onSuccess(String s) {

            }

            @Override
            public void offline() {
                UIUtils.onRunMainThred(new Runnable() {
                    @Override
                    public void run() {
                        view.offline();
                    }
                });

            }

            @Override
            public void onTokenIncorrect() {
                UIUtils.onRunMainThred(new Runnable() {
                    @Override
                    public void run() {
                        view.offline();
                    }
                });
            }
        });
        String rongyunKey = UserInfoManager.getInstance().getIMServiceKey();
        if (StringUtis.isEmpty(rongyunKey)) {
            RxBus.getInstance().send(Events.EventEnum.GET_IM_SERVICE_KEY, null);
            return;
        }
        if (IMManager.getInstance().isConnected())
            return;
        IMManager.getInstance().loginIMService(UserInfoManager.getInstance().getUid(), rongyunKey, new IMManager.ConnectCallback() {
            @Override
            public void onSuccess(String s) {

            }

            @Override
            public void offline() {

            }

            @Override
            public void onTokenIncorrect() {
                UIUtils.onRunMainThred(new Runnable() {
                    @Override
                    public void run() {
                        view.offline();
                    }
                });
            }
        });

    }

    @Override
    public void getCertificationStatus() {
        int status = UserInfoManager.getInstance().getCertificationStatus();
        //在申请认证中 获取申请的进度
        if (status != 3) {
            model.getCertificationStatus(this);
        }

    }

    @Override
    public void getUserPermission() {
        model.getUserPermission(this);
    }

    @Override
    public void getUserFriendList() {
        model.getUserFriendList(this);
    }

    /**
     * @param object
     * @param dataType 0 融云key, 1 认证状态
     */

    @Override
    public void onSuccess(Object object, BaseIModel.DataType dataType) {
        if (object == null) {
            view.transfePageMsg(UIUtils.getStringRes(R.string.request_failed));
            return;
        }
        switch (dataType) {
            case DATA_ZERO:// 0 融云key
                if (object instanceof RongyunBean) {
                    RongyunBean bean = (RongyunBean) object;
                    if (StringUtis.equals(bean.getCode(), AppConstant.code_request_success)) {
                        PersonInfoDetail presonInfo = UserInfoManager.getInstance().getMemoryPersonInfoDetail();
                        presonInfo.setFace(bean.getData().getFace());
                        presonInfo.setNickname(bean.getData().getNickname());
                        presonInfo.setRongyunkey(bean.getData().getToken());
                        presonInfo.setSex(bean.getData().getSex());
                        presonInfo.setId(bean.getData().getUid());
                        presonInfo.setHxPwd(bean.getData().getPassword());
                        presonInfo.setAttestation(StringUtis.string2Int(bean.getData().getApply_status()));
                        UserInfoManager.getInstance().saveMemoryInstance(presonInfo);
                        loginRongyun();
                    }
                }

                break;
            case DATA_ONE://1 认证状态
                if (object instanceof CertificationStatusBean) {
                    CertificationStatusBean bean = (CertificationStatusBean) object;
                    if (StringUtis.equals(AppConstant.code_request_success, bean.getCode())) {
                        CertificationStatusBean.DataBean data = bean.getData();
                        if (data != null) {
                            //1 已审核   2审核中 3 未审核
                            String status = data.getStatus();
                            PersonInfoDetail memoryPersonInfoDetail = UserInfoManager.getInstance().getMemoryPersonInfoDetail();
                            if (StringUtis.equals(status, "1")) {

                                memoryPersonInfoDetail.setAttestation(1);
                            } else if (StringUtis.equals(status, "2")) {
                                memoryPersonInfoDetail.setAttestation(2);
                            } else if (StringUtis.equals(status, "3")) {
                                memoryPersonInfoDetail.setAttestation(3);
                            }
                            UserInfoManager.getInstance().saveMemoryInstance(memoryPersonInfoDetail);
                        }
                    }
                    LogUtils.e(this, " CertificationStatus : " + bean.getCode());

                }

                break;
            case DATA_TWO:
                if (object instanceof UserPermissionBean) {
                    UserPermissionBean permissionBean = (UserPermissionBean) object;
                    if (StringUtis.equals(permissionBean.getCode(), AppConstant.code_request_success)) {
                        UserPermissionBean.DataBean data = permissionBean.getData();
                        if (data != null) {
                            PersonInfoDetail memoryPersonInfoDetail = UserInfoManager.getInstance().getMemoryPersonInfoDetail();
                            memoryPersonInfoDetail.setAttestation(data.getIsauth());
                            memoryPersonInfoDetail.setVipStatus(data.getVip());
                            UserInfoManager.getInstance().saveMemoryInstance(memoryPersonInfoDetail);
                        }
                    }
                }
                break;
            case DATA_THREE://
                if (object instanceof UserFriendListBean) {
                    UserFriendListBean bean = (UserFriendListBean) object;
                    List<String> datas = bean.getData();
                    PersonInfoDetail personInfoDetail = UserInfoManager.getInstance().getMemoryPersonInfoDetail();
                    personInfoDetail.setUserFriendList(datas);
                    UserInfoManager.getInstance().saveMemoryInstance(personInfoDetail);
                }
                break;
        }


    }

    @Override
    public void onFailure(String msg) {
        view.transfePageMsg(msg);
        view.hideLoading();
    }
}
