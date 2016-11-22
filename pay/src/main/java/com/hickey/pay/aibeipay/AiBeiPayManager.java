package com.hickey.pay.aibeipay;

import android.app.Activity;

import com.hickey.pay.PayLog;
import com.iapppay.interfaces.callback.IPayResultCallback;
import com.iapppay.sdk.main.IAppPay;
import com.iapppay.sdk.main.IAppPayOrderUtils;

/**
 * Created by jb on 2016/7/27.
 */
public class AiBeiPayManager {
    private static AiBeiPayManager instances;

    public static AiBeiPayManager getInstance() {
        if (instances == null) {
            synchronized (AiBeiPayManager.class) {
                if (instances == null) {
                    return new AiBeiPayManager();
                }
                return instances;
            }
        }
        return instances;
    }

    public void pay(Activity activity, String transid, final AiBeiResultCallback callback) {
            IAppPay.init(activity, IAppPay.PORTRAIT, PayConfig.appid);//接入时！不要使用Demo中的appid
//        String cporderid = System.currentTimeMillis() + "";
//        String param = getTransdata("userid001", "cpprivateinfo123456", 1, 1, cporderid);

        IAppPay.startPay(activity, transid, new IPayResultCallback() {
            @Override
            public void onPayResult(int resultCode, String signvalue, String resultInfo) {
                {
                    // TODO Auto-generated method stub
                    switch (resultCode) {
                        case IAppPay.PAY_SUCCESS:
                            //调用 IAppPayOrderUtils 的验签方法进行支付结果验证
                            boolean payState = IAppPayOrderUtils.checkPayResult(signvalue, PayConfig.publicKey);
                            if (payState) {
                                PayLog.e(AiBeiPayManager.this, "支付成功");
                                if (callback != null)
                                    callback.onPayResult(1, "支付成功");
                            } else {
                                PayLog.e(AiBeiPayManager.this, "失败");
                                if (callback != null)
                                    callback.onPayResult(2, "支付失败");
                            }
                            break;
                        case IAppPay.PAY_ING:
                            PayLog.e(AiBeiPayManager.this, "成功下单");
                            if (callback != null)
                                callback.onPayResult(3, "成功下单");
                            break;
                        default:
                            PayLog.e(AiBeiPayManager.this, resultInfo);
                            if (callback != null)
                                callback.onPayResult(4, resultInfo);
                            break;
                    }
                    PayLog.d("MainDemoActivity", "requestCode:" + resultCode + ",signvalue:" + signvalue + ",resultInfo:" + resultInfo);
                }

            }
        });
    }

    public interface AiBeiResultCallback {
        void onPayResult(int resultCode, String resultInfo);
    }

    /**
     * //     * 获取收银台参数
     * //
     */
//    private String getTransdata(String appuserid, String cpprivateinfo, int waresid, float price, String cporderid) {
//        //调用 IAppPayOrderUtils getTransdata() 获取支付参数
//        IAppPayOrderUtils orderUtils = new IAppPayOrderUtils();
//        orderUtils.setAppid(PayConfig.appid);
//        orderUtils.setWaresid(waresid);//传入您商户后台创建的商品编号
//        orderUtils.setCporderid(cporderid);
//        orderUtils.setAppuserid(appuserid);
//        orderUtils.setPrice(price);//单位 元
//        orderUtils.setWaresname("自定义名称");//开放价格名称(用户可自定义，如果不传以后台配置为准)
//        orderUtils.setCpprivateinfo(cpprivateinfo);
////        orderUtils.setNotifyurl(PayConfig.notifyurl);
//        return orderUtils.getTransdata(PayConfig.privateKey);
////    }

}
