package com.moonsister.tcjy.home.model;

import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.ServerApi;
import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.GoodSelectBaen;

import com.moonsister.tcjy.home.widget.GoodSelectFragment;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ObservableUtils;
import com.moonsister.tcjy.utils.UIUtils;

import java.util.List;

import rx.Observable;


/**
 * Created by pc on 2016/6/3.
 */
public class GoodSelectModelImpl implements GoodSelectModel {

    @Override
    public void loadGoodSelectDate(int pageType, String type, int page, final BaseIModel.onLoadDateSingleListener<List<GoodSelectBaen.Data>> listener) {
        String authcode = UserInfoManager.getInstance().getAuthcode();
        Observable<GoodSelectBaen> observable = null;
        if (pageType == GoodSelectFragment.SAME_CITY) {
            observable = ServerApi.getAppAPI().getSameCity(type, page, authcode, AppConstant.CHANNEL_ID);
        } else if (GoodSelectFragment.GOOD_SELECT == pageType) {
            observable = ServerApi.getAppAPI().getGoodSelect(type, page, authcode, AppConstant.CHANNEL_ID);
        }
        if (observable == null) {
            listener.onFailure(UIUtils.getStringRes(R.string.request_failed));
            return;
        }
        ObservableUtils.parser(observable, new ObservableUtils.Callback<GoodSelectBaen>() {
            @Override
            public void onSuccess(GoodSelectBaen bean) {
                if (bean != null) {
                    if ("1".equals(bean.getCode()))
                        listener.onSuccess(bean.getData(), BaseIModel.DataType.DATA_ZERO);
                    else
                        listener.onFailure(bean.getMsg());
                } else
                    listener.onFailure(UIUtils.getStringRes(R.string.request_failed));
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });
    }

}
