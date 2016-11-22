package com.moonsister.tcjy.main.presenter;

import com.hickey.network.bean.DynamicItemBean;
import com.hickey.network.bean.UserInfoDetailBean;
import com.hickey.tool.constant.EnumConstant;
import com.hickey.tool.lang.StringUtis;
import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.main.model.HomePageFragmentModel;
import com.moonsister.tcjy.main.model.HomePageFragmentModelImpl;
import com.moonsister.tcjy.main.view.HomePageFragmentView;

import java.util.List;

/**
 * Created by jb on 2016/9/1.
 */
public class HomePageFragmentPresenterImpl implements HomePageFragmentPresenter, BaseIModel.onLoadDateSingleListener {
    private HomePageFragmentView view;
    private HomePageFragmentModel model;
    private int page;
    private String upID;

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(HomePageFragmentView homePageFragmentView) {
        this.view = homePageFragmentView;
        model = new HomePageFragmentModelImpl();
    }

    @Override
    public void loadRefresh(String userId, EnumConstant.SearchType type) {
        view.showLoading();
        page = 1;
        model.loadDynamicData(userId, page, type, this);

    }

    @Override
    public void loadHeader(String userId) {
        model.loadheaderData(userId, this);
    }

    @Override
    public void loadMore(String userId, EnumConstant.SearchType type) {
        view.showLoading();
        model.loadDynamicData(userId, page, type, this);
    }

    @Override
    public void onSuccess(Object object, BaseIModel.DataType dataType) {
        if (object == null) {
            view.hideLoading();
            return;
        }
        switch (dataType) {
            case DATA_ZERO:
                if (object instanceof List) {
                    List<DynamicItemBean> list = (List<DynamicItemBean>) object;
                    dynamicSort(list);
                    view.setDynamicData(list);
                }
                view.hideLoading();
                break;

            case DATA_ONE:
                if (object instanceof UserInfoDetailBean) {
                    UserInfoDetailBean bean = (UserInfoDetailBean) object;
                    view.setHeaderData(bean);
                }
                break;
        }

    }

    private void dynamicSort(List<DynamicItemBean> t) {
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
        view.hideLoading();
    }

    @Override
    public void onFailure(String msg) {
        view.transfePageMsg(msg);
        view.hideLoading();
    }
}
