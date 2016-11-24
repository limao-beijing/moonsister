package com.hickey.network.aliyun;


import android.content.Context;
import android.util.Log;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.hickey.network.LogUtils;

import java.io.File;

/**
 * Created by jb on 2016/6/16.
 */
public class AliyunManager {
    private OSS oss;

    // 运行sample前需要配置以下字段为有效的值
    private static final String endpoint = "http://oss-cn-beijing.aliyuncs.com";
    private static final String accessKeyId = "qfNP5fs6r3MYzYy7";
    private static final String accessKeySecret = "yrvj6Yv9Vm6ZcZFEgxaxOsPCcYy4Jy";


    private static final String bucket = "mimei";

    private static final String downloadObject = "sampleObject";
    private static AliyunManager instances;
    private static Context mContext;

    private AliyunManager() {
        OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider(accessKeyId, accessKeySecret);

        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
        conf.setMaxConcurrentRequest(5); // 最大并发请求书，默认5个
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
        OSSLog.enableLog();
        oss = new OSSClient(mContext, endpoint, credentialProvider, conf);
    }

    public static AliyunManager getInstance(Context context) {
        if (instances == null) {
            synchronized (AliyunManager.class) {
                if (instances == null) {
                    mContext = context;
                    return new AliyunManager();
                }
                return instances;
            }
        }
        return instances;
    }

    public String upLoadFile(String uploadFilePath, FilePathUtlis.FileType fileType) throws ClientException, ServiceException {
        LogUtils.e(this, "uploadFilePath : " + uploadFilePath);
        // 构造上传请求
        String path = fileType.getFileFormat() + File.separator + FilePathUtlis.getUpAliyunFilePath(fileType);
        PutObjectRequest put = new PutObjectRequest(bucket, path, uploadFilePath);
//        try {

        PutObjectResult putResult = oss.putObject(put);

        LogUtils.d("PutObject", "UploadSuccess");
        String host = endpoint.substring(0, endpoint.indexOf("//") + 2) + put.getBucketName() + "." + endpoint.substring(endpoint.indexOf("//") + 2) + File.separator + put.getObjectKey();

        LogUtils.e(this, "host : " + host);
        LogUtils.d("ETag", "ETag : " + putResult.getETag());
        LogUtils.d("RequestId", "  : " + putResult.getRequestId());

        return host;
//        } catch (ClientException e) {
//            // 本地异常如网络异常等
//
//            e.printStackTrace();
//
//        } catch (ServiceException e) {
//            // 服务异常
//            LogUtils.e("RequestId", e.getRequestId());
//            LogUtils.e("ErrorCode", e.getErrorCode());
//            LogUtils.e("HostId", e.getHostId());
//            LogUtils.e("RawMessage", e.getRawMessage());
//
//        }


    }

    // 直接上传二进制数据，使用阻塞的同步接口
    public String upLoadFiletFromByteArray(byte[] uploadData, FilePathUtlis.FileType fileType) {
        String path = fileType.getFileFormat() + File.separator + FilePathUtlis.getUpAliyunFilePath(fileType);
        // 构造上传请求
        PutObjectRequest put = new PutObjectRequest(bucket, path, uploadData);

        try {
            PutObjectResult putResult = oss.putObject(put);
            String host = endpoint.substring(0, endpoint.indexOf("//") + 2) + put.getBucketName() + "." + endpoint.substring(endpoint.indexOf("//") + 2) + File.separator + put.getObjectKey();
            LogUtils.d("PutObject", "UploadSuccess");
            LogUtils.e(this, "host : " + host);
            LogUtils.d("ETag", putResult.getETag());
            LogUtils.d("RequestId", putResult.getRequestId());
            return host;
        } catch (ClientException e) {
            // 本地异常如网络异常等
            e.printStackTrace();
            return null;
        } catch (ServiceException e) {
            // 服务异常
            Log.e("RequestId", e.getRequestId());
            Log.e("ErrorCode", e.getErrorCode());
            Log.e("HostId", e.getHostId());
            Log.e("RawMessage", e.getRawMessage());
            return null;
        }
    }


//    /**
//     * 支付支付
//     *
//     * @param playInfo
//     * @return
//     */
//    public String play(String playInfo) throws Exception {
//
//
//        // 构造PayTask 对象
//        PayTask alipay = new PayTask(ConfigUtils.getInstance().getActivityContext());
//        // 调用支付接口，获取支付结果
//        String result = alipay.pay(playInfo, true);
//        PayResult payResult = new PayResult((String) result);
//        /**
//         * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
//         * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
//         * docType=1) 建议商户依赖异步通知
//         */
////        String resultInfo = payResult.getResult();// 同步返回需要验证的信息
//
//        String resultStatus = payResult.getResultStatus();
//        // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
//        LogUtils.e(AliyunManager.this, "resultStatus : " + resultStatus);
//
//        if (TextUtils.equals(resultStatus, "9000")) {
//            LogUtils.e(AliyunManager.this, "支付成功");
//        } else {
//            // 判断resultStatus 为非"9000"则代表可能支付失败
//            // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
//            if (TextUtils.equals(resultStatus, "8000")) {
//                LogUtils.e(AliyunManager.this, "支付结果确认中");
//            } else {
//                // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
//                LogUtils.e(AliyunManager.this, "支付失败");
//
//
//            }
//        }
//        return resultStatus;
//    }


    public interface onAliyunListener<T> {
        void onResuit(boolean isSucced, T t);
    }

    public static class AliyunCallBack {
        private String eTag;
        private String RequestId;
        private String serverFilePath;
    }
}
