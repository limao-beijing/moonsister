package com.moonsister.tcjy.manager;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.hickey.network.ServerApi;
import com.hickey.network.bean.DefaultDataBean;
import com.hickey.tool.file.PrefUtils;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.hickey.tool.ConfigUtils;
import com.moonsister.tcjy.utils.LogUtils;
import com.moonsister.tcjy.utils.ObservableUtils;

import java.util.ArrayList;

import rx.Observable;

/**
 * Created by jb on 2016/7/15.
 */
public class GaodeManager implements AMapLocationListener {
    private volatile static GaodeManager instance;

    private static AMapLocationClient locationClient = null;
    private static AMapLocationClientOption locationOption = null;

    public static GaodeManager getInstance() {
        if (instance == null) {
            synchronized (UserInfoManager.class) {
                if (instance == null) {
                    instance = new GaodeManager();
                    locationClient = new AMapLocationClient(ConfigUtils.getInstance().getApplicationContext());
                    locationOption = new AMapLocationClientOption();
                    // 设置定位模式为高精度模式
                    locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
                    // 设置定位监听
                }

            }
        }
        return instance;
    }

    public void getLocLocation() {
        LogUtils.e(this, "--------showTopBanner loclocation---------");
        initOption();
        locationClient.setLocationListener(this);
        locationClient.setLocationOption(locationOption);
        // 启动定位
        locationClient.startLocation();
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        LogUtils.e(this, "-------- finish loclocation---------");
        if (aMapLocation != null) {
            LogUtils.d(this, aMapLocation.toStr());
            PrefUtils.setString(ConfigUtils.getInstance().getApplicationContext(), GaodeManager.class.getName(), aMapLocation.toStr());
            if (UserInfoManager.getInstance().isLogin()) {
                Observable<DefaultDataBean> observable = ServerApi.getAppAPI().uploadLoation(aMapLocation.toStr(), UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
                ObservableUtils.parser(observable, null);

                RxBus.getInstance().send(Events.EventEnum.GET_LOCLOCATION, null);
            }
            for (onLocationFinishListenter listenter : listenters)
                listenter.onLocationListenter(aMapLocation);
        }
        locationClient.stopLocation();
        LogUtils.e(this, "-------- finish loclocation---------");
    }

    public String getStringAdress() {
        return PrefUtils.getString(ConfigUtils.getInstance().getApplicationContext(), GaodeManager.class.getName(), "");
    }

    // 根据控件的选择，重新设置定位参数
    private void initOption() {
        /**
         * 设置是否优先返回GPS定位结果，如果30秒内GPS没有返回定位结果则进行网络定位
         * 注意：只有在高精度模式下的单次定位有效，其他方式无效
         */
        locationOption.setGpsFirst(true);

        //设置是否返回地址信息（默认返回地址信息）
        locationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        locationOption.setOnceLocation(true);

        if (locationOption.isOnceLocationLatest()) {
            locationOption.setOnceLocationLatest(true);
            //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。
            //如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会。
        }

        //设置是否强制刷新WIFI，默认为强制刷新
        locationOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        locationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        locationOption.setInterval(2000);


    }

    private ArrayList<onLocationFinishListenter> listenters = new ArrayList<onLocationFinishListenter>();

    public void setLocationFinishListenter(onLocationFinishListenter listenter) {
        if (!listenters.contains(listenter)) {
            listenters.add(listenter);
        }
    }

    public void removeLocationFinishListenter(onLocationFinishListenter listenter) {
        if (listenters.contains(listenter))
            listenters.remove(listenter);
    }


    public interface onLocationFinishListenter {
        void onLocationListenter(AMapLocation aMapLocation);
    }
}
