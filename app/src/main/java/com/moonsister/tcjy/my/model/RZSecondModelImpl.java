package com.moonsister.tcjy.my.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.hickey.network.ServerApi;
import com.hickey.network.bean.DefaultDataBean;
import com.hickey.network.bean.DynamicContent;
import com.hickey.network.bean.PayBean;
import com.hickey.network.bean.PersonInfoDetail;
import com.hickey.pay.aibeipay.AiBeiPayManager;
import com.hickey.tool.lang.StringUtis;
import com.hickey.tool.parse.JsonUtils;
import com.hickey.tool.view.image.ImageUtils;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.center.presenter.DynamicPublishPresenterImpl;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.hickey.network.aliyun.AliyunManager;
import com.moonsister.tcjy.utils.ConfigUtils;
import com.hickey.network.aliyun.FilePathUtlis;
import com.moonsister.tcjy.utils.LogUtils;
import com.moonsister.tcjy.utils.UIUtils;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jb on 2016/6/30.
 */
public class RZSecondModelImpl implements RZSecondModel {

    @Override
    public void submit(String address1, String address2, String height, String sexid, String nike, String avaterpath, ArrayList<String> pics, onLoadDateSingleListener listener) {
        Observable<PayBean> observable = ServerApi.getAppAPI().getCertificationPay(UserInfoManager.getInstance().getAuthcode(),AppConstant.CHANNEL_ID);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<PayBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onFailure(UIUtils.getStringRes(R.string.request_failed));
                    }

                    @Override
                    public void onNext(PayBean bean) {
                        if (bean == null) {
                            listener.onFailure(UIUtils.getStringRes(R.string.request_failed));
                        } else if (StringUtis.equals(bean.getCode(), "1000")) {
                            listener.onFailure(UIUtils.getStringRes(R.string.login_code_timeout));
                            RxBus.getInstance().send(Events.EventEnum.LOGIN_CODE_TIMEOUT, null);
                        } else if (StringUtis.equals("1", bean.getCode())) {
                            AiBeiPayManager.getInstance().pay(ConfigUtils.getInstance().getActivityContext(), bean.getData().getAbcode(), new AiBeiPayManager.AiBeiResultCallback() {
                                @Override
                                public void onPayResult(int resultCode, String resultInfo) {
                                    if (resultCode == 1) {
                                        PersonInfoDetail detail = UserInfoManager.getInstance().getMemoryPersonInfoDetail();
                                        detail.setAttestation(2);
                                        UserInfoManager.getInstance().saveMemoryInstance(detail);
                                        sumbitData(address1, address2, height, sexid, nike, avaterpath, pics, bean.getData().getOrder_id(), listener);
                                    } else
                                        listener.onFailure(resultInfo);

                                }
                            });
                        } else if (StringUtis.equals("2", bean.getCode())) {
                            sumbitData(address1, address2, height, sexid, nike, avaterpath, pics, "", listener);
                        } else
                            listener.onFailure(bean.getMsg());
                    }
                });
    }

    private void sumbitData(String address1, String address2, String height, String sexid, String nike, String avaterpath, ArrayList<String> pics, String orderID, onLoadDateSingleListener listener) {
        LogUtils.e(RZSecondModelImpl.this, "start upload");
        ArrayList<DynamicContent> aliyunPtahs = new ArrayList<DynamicContent>();
        Observable.create(new Observable.OnSubscribe<ArrayList<DynamicContent>>() {
            @Override
            public void call(Subscriber<? super ArrayList<DynamicContent>> subscriber) {

                try {
                    for (int i = 0; i < pics.size(); i++) {
                        DynamicContent image = new DynamicContent();
                        String path = pics.get(i);
                        Bitmap size = ImageUtils.compressImageWithPathSzie(path, 800, 600);
                        Bitmap bitmap = ImageUtils.compressImage(size, 1000);
                        String loadFile = AliyunManager.getInstance(ConfigUtils.getInstance().getApplicationContext()).upLoadFiletFromByteArray(ImageUtils.getBitmapByte(bitmap), FilePathUtlis.FileType.JPG);
                        image.setL(loadFile);
                        aliyunPtahs.add(image);
                    }
                    subscriber.onNext(aliyunPtahs);
                } catch (Exception e) {

                }
            }
        }).observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<ArrayList<DynamicContent>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e(RZSecondModelImpl.this, "onError :　" + e.getMessage());
                        e.printStackTrace();
                        listener.onFailure(e.getMessage());
                    }

                    @Override
                    public void onNext(ArrayList<DynamicContent> s) {
                        String loadFile = "";
                        if (!StringUtis.isEmpty(avaterpath)) {
                            Bitmap avater = ImageUtils.compressImageSzie(BitmapFactory.decodeFile(avaterpath), 120, 120);
                            Bitmap bitmap = ImageUtils.compressImage(avater, 10);
                            loadFile = AliyunManager.getInstance(ConfigUtils.getInstance().getApplicationContext()).upLoadFiletFromByteArray(ImageUtils.getBitmapByte(bitmap), FilePathUtlis.FileType.JPG);
                        }
                        LogUtils.e(DynamicPublishPresenterImpl.class, "  onNext :　" + aliyunPtahs.toString());
                        String serialize = JsonUtils.serialize(s);
                        LogUtils.e(RZSecondModelImpl.this, "JsonUtils : " + serialize);
                        sendAllRz(address1, address2, height, sexid, nike, loadFile, serialize, orderID, listener);
                    }


                });
    }

    private void sendAllRz(String address1, String address2, String height, String sexid, String nike, String loadFile, String serialize, String orderid, onLoadDateSingleListener listener) {

        ServerApi.getAppAPI().sendAllRzInfo(address1, address2, height, sexid, nike, loadFile, serialize, orderid, UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<DefaultDataBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onFailure(e.getMessage());
                    }

                    @Override
                    public void onNext(DefaultDataBean defaultDataBean) {
                        listener.onSuccess(defaultDataBean, DataType.DATA_ZERO);
                    }
                });


    }


}
