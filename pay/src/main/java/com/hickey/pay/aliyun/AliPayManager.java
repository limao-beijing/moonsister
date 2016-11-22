package com.hickey.pay.aliyun;

import android.app.Activity;
import android.text.TextUtils;

import com.alipay.sdk.app.PayTask;
import com.hickey.pay.PayLog;

/**
 * Created by jb on 2016/7/23.
 */
public class AliPayManager {
    private static AliPayManager instances;

    public static AliPayManager getInstance() {
        if (instances == null) {
            synchronized (AliPayManager.class) {
                if (instances == null)
                    return new AliPayManager();
                return instances;
            }
        }
        return instances;
    }

    /**
     * 支付支付
     *
     * @param playInfo
     * @return
     */
    public String play(Activity activity, String playInfo) throws Exception {


        // 构造PayTask 对象
        PayTask alipay = new PayTask(activity);
        // 调用支付接口，获取支付结果
        String result = alipay.pay(playInfo, true);
        PayResult payResult = new PayResult((String)result);
        /**
         * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
         * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
         * docType=1) 建议商户依赖异步通知
         */
//        String resultInfo = payResult.getResult();// 同步返回需要验证的信息

        String resultStatus = payResult.getResultStatus();
        // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
        PayLog.e(this, "resultStatus : " + resultStatus);

        if (TextUtils.equals(resultStatus, "9000")) {
            PayLog.e(this, "支付成功");
        } else {
            // 判断resultStatus 为非"9000"则代表可能支付失败
            // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
            if (TextUtils.equals(resultStatus, "8000")) {
                PayLog.e(this, "支付结果确认中");
            } else {
                // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                PayLog.e(this, "支付失败");


            }
        }
        return resultStatus;
    }
}
