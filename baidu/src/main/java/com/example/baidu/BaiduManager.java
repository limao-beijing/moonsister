package com.example.baidu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.baidu.appx.BDBannerAd;
import com.baidu.mobads.AdView;
import com.baidu.mobads.AdViewListener;
import com.baidu.mobads.AppActivity;
import com.baidu.mobads.AppActivity.ActionBarColorTheme;
import com.baidu.mobads.SplashAd;
import com.baidu.mobads.SplashAdListener;

import org.json.JSONObject;


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

    public void showBanner(Activity activity, ViewGroup viewGroup) {
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


    public void adSplashBanner(final Activity activity, ViewGroup viewGroup, final Class clz) {
//        AdView.setAppSid(activity.getApplicationContext(), "debug");
        AppActivity.setActionBarColorTheme(ActionBarColorTheme.ACTION_BAR_WHITE_THEME);
        // the observer of AD
        SplashAdListener listener = new SplashAdListener() {
            @Override
            public void onAdDismissed() {
                Log.i("RSplashActivity", "onAdDismissed");
                activity.startActivity(new Intent(activity, clz));
                activity.finish();
            }

            @Override
            public void onAdFailed(String arg0) {
                Log.e("RSplashActivity", arg0);
                activity.startActivity(new Intent(activity, clz));
                activity.finish();
            }

            @Override
            public void onAdPresent() {
                Log.i("RSplashActivity", "onAdPresent");
            }

            @Override
            public void onAdClick() {
                Log.i("RSplashActivity", "onAdClick");
                // 设置开屏可接受点击时，该回调可用
            }
        };
        String adPlaceId = "2927956"; // 重要：请填上您的广告位ID，代码位错误会导致无法请求到广告
        new SplashAd(activity, viewGroup, listener, adPlaceId, true);
    }

    /**
     * 添加banner
     *
     * @param activity
     * @param viewGroup
     */
    public void adBanner(final Activity activity, ViewGroup viewGroup) {

        // 代码设置AppSid，此函数必须在AdView实例化前调用final Activity activity, ViewGroup viewGroup
//        AdView.setAppSid(activity.getApplicationContext(), "debug");

        // 设置'广告着陆页'动作栏的颜色主题
        // 目前开放了七大主题：黑色、蓝色、咖啡色、绿色、藏青色、红色、白色(默认) 主题
        AppActivity.setActionBarColorTheme(ActionBarColorTheme.ACTION_BAR_WHITE_THEME);
//        另外，也可设置动作栏中单个元素的颜色, 颜色参数为四段制，0xFF(透明度, 一般填FF)DE(红)DA(绿)DB(蓝)
//        AppActivity.getActionBarColorTheme().set[Background|Title|Progress|Close]Color(0xFFDEDADB);

        // 创建广告View
        String adPlaceId = "2927797"; //  重要：请填上您的广告位ID，代码位错误会导致无法请求到广告
        AdView adView = new AdView(activity, adPlaceId);
        // 设置监听器
        adView.setListener(new AdViewListener() {
            public void onAdSwitch() {
                Log.w("BaiduManager", "onAdSwitch");
            }

            public void onAdShow(JSONObject info) {
                // 广告已经渲染出来
                Log.w("BaiduManager", "onAdShow " + info.toString());
            }

            public void onAdReady(AdView adView) {
                // 资源已经缓存完毕，还没有渲染出来
                Log.w("BaiduManager", "onAdReady " + adView);
            }

            public void onAdFailed(String reason) {
                Log.w("BaiduManager", "onAdFailed " + reason);
            }

            public void onAdClick(JSONObject info) {
                // Log.w("", "onAdClick " + info.toString());

            }

            @Override
            public void onAdClose(JSONObject arg0) {
                Log.w("BaiduManager", "onAdClose");
            }
        });
        // 将adView添加到父控件中(注：该父控件不一定为您的根控件，只要该控件能通过addView能添加广告视图即可)
        RelativeLayout.LayoutParams rllp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        rllp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        viewGroup.addView(adView, rllp);
    }

    public void addChuileiBanner(final Activity activity, ViewGroup viewGroup) {


    }
}
