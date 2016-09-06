package com.moonsister.tcjy.my.model;

import android.content.Intent;
import android.graphics.Bitmap;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.moonsister.pay.aibeipay.AiBeiPayManager;
import com.moonsister.pay.tencent.PayBean;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.ServerApi;
import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.BaseBean;
import com.moonsister.tcjy.bean.DynamicContent;
import com.moonsister.tcjy.bean.RechargeBean;
import com.moonsister.tcjy.login.widget.RegActivity;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.manager.aliyun.AliyunManager;
import com.moonsister.tcjy.my.widget.MoneyActivity;
import com.moonsister.tcjy.my.widget.RechargeActivity;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.ConfigUtils;
import com.moonsister.tcjy.utils.FastBlur;
import com.moonsister.tcjy.utils.FilePathUtlis;
import com.moonsister.tcjy.utils.ImageUtils;
import com.moonsister.tcjy.utils.ObservableUtils;
import com.moonsister.tcjy.utils.StringUtis;
import com.moonsister.tcjy.utils.VideoUtils;

import java.io.File;
import java.util.ArrayList;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by x on 2016/9/3.
 */
public class RechargeActivityModelImpl implements RechargeActivityModel {
    @Override
    public void loadData(String money, String pay_type, onLoadDateSingleListener<RechargeBean> listener) {
        Observable<PayBean> observable = ServerApi.getAppAPI().getrechargeBean(money, pay_type, UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID, AppConstant.API_VERSION);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PayBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(PayBean payBean) {
                        if (StringUtis.equals(payBean.getCode(), "1")) {
                            AiBeiPayManager.getInstance().pay(ConfigUtils.getInstance().getActivityContext(), payBean.getData().getAbcode(), new AiBeiPayManager.AiBeiResultCallback() {
                                @Override
                                public void onPayResult(int resultCode, String resultInfo) {
                                    if (StringUtis.equals(payBean.getCode(), "1")) {
                                        ActivityUtils.startRechaargeMoneyActivity();
                                    } else {
                                        listener.onFailure(payBean.getMsg());
                                    }
                                }
                            });
                        } else {
                            listener.onFailure(payBean.getMsg());
                        }
                    }
                });
    }



    public void upLoadVideo(String srcVideoPath, ArrayList< DynamicContent > aliyunPtahs, boolean isCharge) throws ClientException, ServiceException
    {


        //视频
        String vidoPath = AliyunManager.getInstance().upLoadFile(srcVideoPath, FilePathUtlis.FileType.MP4);
        //图片
        String videoThumbnail = VideoUtils.getInstance().getVideoThumbnail(srcVideoPath);
//        String videoThumbnail = VideoUtils.getInstance().getVideoThumbnail(videoPath);
        Bitmap size = ImageUtils.compressImageWithPathSzie(videoThumbnail, 1280, 720);
        //压缩大小
        Bitmap bitmap = ImageUtils.compressImage(size, 100);
        String loadFile = AliyunManager.getInstance().upLoadFiletFromByteArray(ImageUtils.getBitmapByte(bitmap), FilePathUtlis.FileType.JPG);
        DynamicContent content = new DynamicContent();
        content.setV(vidoPath);
        content.setL(loadFile);
        if (isCharge) {
            //压缩质量

            //宽高压缩
//            Bitmap bitmapsize = ImageUtils.compressImageSzie(bitmap, 600, 480);
            //质量压缩
            Bitmap bitmap1 = ImageUtils.compressImage(bitmap, 50);
            //模糊
            Bitmap bitmap2 = FastBlur.doBlur(bitmap1, 20, true);
            byte[] bitmapByte = ImageUtils.getBitmapByte(bitmap2);
            String fuzzyFile = AliyunManager.getInstance().upLoadFiletFromByteArray(bitmapByte, FilePathUtlis.FileType.JPG);

            if (fuzzyFile == null)
                fuzzyFile = "";
            content.setS(fuzzyFile);
        } else {
            content.setS("");
        }
        File file = new File(videoThumbnail);
        if (file.exists() && file.isFile()) {
            file.delete();
        }
        aliyunPtahs.add(content);
    }

    public void upLoadVoice(String videoPath, ArrayList<DynamicContent> aliyunPtahs, boolean isCharge) throws ClientException, ServiceException {
        String s = AliyunManager.getInstance().upLoadFile(videoPath, FilePathUtlis.FileType.AMR);
        DynamicContent content = new DynamicContent();
        content.setV(s);
        content.setL("");
        content.setS("");
        aliyunPtahs.add(content);
    }

}
