package com.moonsister.tcjy.main.presenter;

import com.hickey.network.bean.BaseBean;
import com.hickey.network.bean.PersonInfoDetail;
import com.hickey.tool.lang.StringUtis;
import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.center.widget.DynamicContentFragment;
import com.moonsister.tcjy.main.model.RenZhengThreeModel;
import com.moonsister.tcjy.main.model.RenZhengThreeModelImpl;
import com.moonsister.tcjy.main.view.RenZhengThreeView;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.UIUtils;

import java.util.List;

/**
 * Created by jb on 2016/10/10.
 */
public class RenZhengThreePresenterImpl implements RenZhengThreePresenter, BaseIModel.onLoadDateSingleListener<BaseBean> {
    private RenZhengThreeView view;
    private RenZhengThreeModel model;

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(RenZhengThreeView view) {
        this.view = view;
        model = new RenZhengThreeModelImpl();
    }

    @Override
    public void submit(List<String> content, DynamicContentFragment.DynamicType type) {
        view.showLoading();
        model.submit(content, type, this);
    }

    @Override
    public void onSuccess(BaseBean bean, BaseIModel.DataType dataType) {
        if (StringUtis.equals(bean.getCode(), "1")) {
            PersonInfoDetail detail = UserInfoManager.getInstance().getMemoryPersonInfoDetail();
            detail.setAttestation(2);
            UserInfoManager.getInstance().saveMemoryInstance(detail);
            UIUtils.sendDelayedOneMillis(new Runnable() {
                @Override
                public void run() {
                    view.pageFinish();
                }
            });

        }
        view.hideLoading();
        view.transfePageMsg(bean.getMsg());
    }

    @Override
    public void onFailure(String msg) {
        view.hideLoading();
        view.transfePageMsg(msg);
    }
}
