package com.moonsister.tcjy.main.presenter;


import android.support.annotation.IdRes;
import android.view.View;

import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.BaseBean;
import com.moonsister.tcjy.bean.CommentDataListBean;
import com.moonsister.tcjy.bean.DefaultDataBean;
import com.moonsister.tcjy.bean.DynamicDatailsBean;
import com.moonsister.tcjy.main.model.DynamincDatailsModel;
import com.moonsister.tcjy.main.model.DynamincDatailsModelImpl;
import com.moonsister.tcjy.main.model.UserActionModelImpl;
import com.moonsister.tcjy.main.view.DynamicDatailsView;
import com.moonsister.tcjy.main.widget.DynamicDatailsActivity;
import com.moonsister.tcjy.utils.StringUtis;
import com.moonsister.tcjy.utils.UIUtils;

import java.util.List;

/**
 * Created by pc on 2016/6/8.
 */
public class DynamincDatailsPresenterImpl implements DynamincDatailsPresenter, BaseIModel.onLoadListDateListener<CommentDataListBean.DataBean>,BaseIModel.onLoadDateSingleListener<BaseBean> {
    private DynamicDatailsView view;
    private DynamincDatailsModel dynamincDatailsModel;
    private int page = 1;

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(DynamicDatailsView dynamicDatailsView) {
        this.view = dynamicDatailsView;
        dynamincDatailsModel = new DynamincDatailsModelImpl();
    }

    @Override
    public void loadCommentListData(String commentID) {
        view.showLoading();
        dynamincDatailsModel.loadCommentListData(commentID, page, this);
        page++;
    }

    @Override
    public void sendComment(String id, String content, String pid) {
        view.showLoading();
        dynamincDatailsModel.sendComment(id, content, pid, this);
    }

    @Override
    public void deleteDynamic(String id) {
        view.showLoading();
        UserActionModelImpl actionModel = new UserActionModelImpl();
        actionModel.deleteDynamic(id, new BaseIModel.onLoadDateSingleListener<DefaultDataBean>() {
            @Override
            public void onSuccess(DefaultDataBean bean, BaseIModel.DataType dataType) {
                if (StringUtis.equals(bean.getCode(), AppConstant.code_request_success)) {
                    view.deleteDynamic(id);
                }
                view.transfePageMsg(bean.getMsg());
                view.hideLoading();
            }

            @Override
            public void onFailure(String msg) {
                view.transfePageMsg(msg);
                view.hideLoading();
            }
        });
    }

    @Override
    public void loadSingeDyamic(String latest_id) {
        dynamincDatailsModel.loadSingeDyamic(latest_id ,this);
    }

    @Override
    public void onSuccess(List<CommentDataListBean.DataBean> t, BaseIModel.DataType dataType) {
        view.hideLoading();
        view.loadData(t);

    }

    @Override
    public void onSuccess(BaseBean bean, BaseIModel.DataType dataType) {
        if (bean==null){
            view.hideLoading();
            return;
        }
        switch (dataType){
            case DATA_ZERO:
                if (StringUtis.equals(AppConstant.code_request_success, bean.getCode())) {
                    view.CommentSuccess();
                }
                view.transfePageMsg(bean.getMsg());
                break;
            case DATA_ONE:
                if (bean instanceof DynamicDatailsBean)
                view.setDynamicDatails((DynamicDatailsBean)bean);
                break;
        }

        view.hideLoading();
    }

    @Override
    public void onFailure(String msg) {
        view.hideLoading();
        view.transfePageMsg(msg);
    }

}
