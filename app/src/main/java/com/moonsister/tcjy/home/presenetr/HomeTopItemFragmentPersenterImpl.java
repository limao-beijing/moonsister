package com.moonsister.tcjy.home.presenetr;

import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.HomeTopItemBean;
import com.moonsister.tcjy.bean.InsertBaen;
import com.moonsister.tcjy.home.model.HomeTopItemFragmentModel;
import com.moonsister.tcjy.home.model.HomeTopItemFragmentModelImpl;
import com.moonsister.tcjy.home.view.HomeTopItemFragmentView;
import com.moonsister.tcjy.utils.EnumConstant;
import com.moonsister.tcjy.utils.StringUtis;
import com.moonsister.tcjy.utils.UIUtils;

import java.util.List;

/**
 * Created by jb on 2016/8/24.
 */
public class HomeTopItemFragmentPersenterImpl implements HomeTopItemFragmentPersenter, BaseIModel.onLoadDateSingleListener<HomeTopItemBean> {
    private HomeTopItemFragmentView view;
    private HomeTopItemFragmentModel model;
    private int page = 1;

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(HomeTopItemFragmentView homeTopItemFragmentView) {
        this.view = homeTopItemFragmentView;
        model = new HomeTopItemFragmentModelImpl();

    }

    @Override
    public void loadRefresh(boolean isRefresh, String tagId, EnumConstant.HomeTopFragmentTop homeType) {
        view.showLoading();
        if (isRefresh)
            page = 1;
        model.loadData(page, tagId, homeType, this);
    }

    @Override
    public void onSuccess(HomeTopItemBean bean, BaseIModel.DataType dataType) {
        if (bean == null) {
            view.transfePageMsg(UIUtils.getStringRes(R.string.request_failed));
        } else {
            String code = bean.getCode();
            if (StringUtis.equals(code, AppConstant.code_request_success)) {
                List<HomeTopItemBean.DataBean> data = bean.getData();
                if (data != null || data.size() != 0)
                    page++;
                view.setData(data);
            } else {
                view.transfePageMsg(bean.getMsg());
            }
        }
        view.hideLoading();
    }

    @Override
    public void onFailure(String msg) {
        view.hideLoading();
        view.transfePageMsg(msg);
    }
}
