package com.moonsister.baidu;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;

import com.baidu.appx.BDBannerAd;


/**
 * Created by jb on 2016/7/28.
 */
public class BaiduManager {
    private static BaiduManager instance;
    private static String appkey;
    private static String appid;

    public static BaiduManager getInstance(Context context) {
        if (instance == null) {
            synchronized (BaiduManager.class) {
                if (instance == null) {
                    instance = new BaiduManager();
                    appkey = BaiduConfig.appKey(context);
                    appid = BaiduConfig.appID(context);
                }

            }
        }
        return instance;
    }

    public void show(Activity activity, ViewGroup viewGroup) {
        BDBannerAd bdBannerAd = new BDBannerAd(activity, appkey, appid);
        bdBannerAd.setAdSize(BDBannerAd.SIZE_FLEXIBLE);
        bdBannerAd.setAdListener(new BDBannerAd.BannerAdListener() {
            @Override
            public void onAdvertisementDataDidLoadSuccess() {

            }

            @Override
            public void onAdvertisementDataDidLoadFailure() {

            }

            @Override
            public void onAdvertisementViewDidShow() {

            }

            @Override
            public void onAdvertisementViewDidClick() {

            }

            @Override
            public void onAdvertisementViewWillStartNewIntent() {

            }
        });
        viewGroup.addView(bdBannerAd);


    }
}
