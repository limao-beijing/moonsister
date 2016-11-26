package com.moonsister.tcjy.find.model;

import com.hickey.network.ServerApi;
import com.hickey.network.bean.NearbyBean;
import com.hickey.tool.file.PrefUtils;
import com.hickey.tool.lang.StringUtis;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.manager.GaodeManager;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.hickey.tool.ConfigUtils;
import com.moonsister.tcjy.utils.ObservableUtils;
import com.hickey.tool.widget.UIUtils;

import org.json.JSONException;
import org.json.JSONObject;

import rx.Observable;

/**
 * Created by jb on 2016/8/4.
 */
public class NearbyActivityModelImpl implements NearbyActivityModel {
    @Override
    public void loadData(String sex, int page, onLoadListDateListener<NearbyBean.DataBean> listener) {
        String location = PrefUtils.getString(ConfigUtils.getInstance().getApplicationContext(),GaodeManager.class.getName(), "");
        double latitude = 0;
        double longitude = 0;
        if (!StringUtis.isEmpty(location)) {
            {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(location);
                    longitude = jsonObject.getDouble("longitude");
                    latitude = jsonObject.getDouble("latitude");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        Observable<NearbyBean> observable = ServerApi.getAppAPI().getNearby(sex, page, longitude, latitude, UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<NearbyBean>() {
            @Override
            public void onSuccess(NearbyBean nearbyBean) {
                if (nearbyBean == null) {
                    listener.onFailure(UIUtils.getStringRes(R.string.request_failed));
                } else {
                    listener.onSuccess(nearbyBean.getData(), DataType.DATA_ZERO);
                }

            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });

    }

}