package com.moonsister.tcjy.my.persenter;

import com.hickey.network.bean.DefaultDataBean;
import com.hickey.network.bean.PersonInfoDetail;
import com.hickey.tool.base.BaseIModel;
import com.hickey.tool.lang.StringUtis;
import com.hickey.tool.widget.UIUtils;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.my.model.RZSecondModel;
import com.moonsister.tcjy.my.model.RZSecondModelImpl;
import com.moonsister.tcjy.my.view.RZSecondView;

import java.util.ArrayList;

/**
 * Created by jb on 2016/6/30.
 */
public class RZSecondPersenterImpl implements RZSecondPersenter, BaseIModel.onLoadDateSingleListener {
    private RZSecondView view;
    private RZSecondModel model;

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(RZSecondView rzSecondView) {
        this.view = rzSecondView;
        model = new RZSecondModelImpl();
    }


    @Override
    public void submit(String address1, String address2, String height, String sexid, String nike, String avaterpath, ArrayList<String> pics) {
        view.showLoading();
        model.submit(address1, address2, height, sexid, nike, avaterpath, pics, this);
    }

    @Override
    public void onSuccess(Object o, BaseIModel.DataType dataType) {
        if (o != null && o instanceof DefaultDataBean) {
            DefaultDataBean bean = (DefaultDataBean) o;
            if (StringUtis.equals(AppConstant.code_request_success, bean.getCode())) {
                PersonInfoDetail memoryPersonInfoDetail = UserInfoManager.getInstance().getMemoryPersonInfoDetail();
                memoryPersonInfoDetail.setAttestation(2);
                UserInfoManager.getInstance().saveMemoryInstance(memoryPersonInfoDetail);
                UIUtils.sendDelayedOneMillis(new Runnable() {
                    @Override
                    public void run() {
                        view.success();
                    }
                });

            }
            view.transfePageMsg(bean.getMsg());
        } else
            view.transfePageMsg(UIUtils.getStringRes(R.string.request_failed));
        view.hideLoading();
    }

    @Override
    public void onFailure(String msg) {
        view.transfePageMsg(msg);
        view.hideLoading();
    }
}
