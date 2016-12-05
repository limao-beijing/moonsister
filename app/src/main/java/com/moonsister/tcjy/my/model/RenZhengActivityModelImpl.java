package com.moonsister.tcjy.my.model;

import android.graphics.Bitmap;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.hickey.network.ServerApi;
import com.hickey.network.bean.BackTermsBean;
import com.hickey.network.bean.BaseBean;
import com.hickey.network.bean.DynamicContent;
import com.hickey.network.bean.PayBean;
import com.hickey.pay.aibeipay.AiBeiPayManager;
import com.hickey.tool.lang.StringUtis;
import com.hickey.tool.view.image.FastBlur;
import com.hickey.tool.view.image.ImageUtils;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.hickey.network.aliyun.AliyunManager;
import com.hickey.tool.ConfigUtils;
import com.hickey.network.aliyun.FilePathUtlis;
import com.moonsister.tcjy.utils.LogUtils;
import com.moonsister.tcjy.utils.ObservableUtils;
import com.hickey.tool.widget.UIUtils;
import com.moonsister.tcjy.utils.VideoUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by x on 2016/9/3.
 */
public class RenZhengActivityModelImpl implements RenZhengActivityModel {
    @Override
    public void loadData(onLoadDateSingleListener<BaseBean> listener) {
        Observable<BackTermsBean> observable = ServerApi.getAppAPI().backTermsBean(UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<BackTermsBean>() {
            @Override
            public void onSuccess(BackTermsBean bean) {
                listener.onSuccess(bean, DataType.DATA_ZERO);
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });
    }

    @Override
    public void submit(String address1, String address2, String text, onLoadDateSingleListener listener) {

        ArrayList<DynamicContent> aliyunPtahs = new ArrayList<DynamicContent>();
        Observable<PayBean> observable = ServerApi.getAppAPI().getCertificationPay(UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
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
                        } else {
                            if (StringUtis.equals("1", bean.getCode())) {
                                AiBeiPayManager.getInstance().pay(ConfigUtils.getInstance().getActivityContext(), bean.getData().getAbcode(), new AiBeiPayManager.AiBeiResultCallback() {
                                    @Override
                                    public void onPayResult(int resultCode, String resultInfo) {
                                        if (resultCode == 1) {
                                            String code = bean.getData().getAbcode();

                                            submittt(address1, address2, text, code, listener);

                                        } else {
                                            listener.onFailure(resultInfo);
                                        }

                                    }
                                });
                            } else
                                listener.onFailure(bean.getMsg());
                        }
                    }

                });
    }

    public void submittt(String address1, String address2, String text, String code, onLoadDateSingleListener listener) {
        ArrayList<String> aliyunPtahs = new ArrayList<String>();
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                String s = null;
                String s1 = null;
                try {
                    s = AliyunManager.getInstance(ConfigUtils.getInstance().getApplicationContext()).upLoadFile(address1, FilePathUtlis.FileType.MP4);
                    s1 = AliyunManager.getInstance(ConfigUtils.getInstance().getApplicationContext()).upLoadFile(address2, FilePathUtlis.FileType.AMR);

                } catch (ClientException e) {
                    e.printStackTrace();
                } catch (ServiceException e) {
                    e.printStackTrace();
                }
                JSONArray jsonarry = new JSONArray();
                JSONObject jsonObj = new JSONObject();
                JSONObject jsonObj1 = new JSONObject();
                JSONObject jsonObj2 = new JSONObject();
                try {
                    jsonObj.put("video", s);
                    jsonObj1.put("voice", s1);
                    jsonObj2.put("info", text);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //向json数组里面添加pet对象
                jsonarry.put(jsonObj);
                jsonarry.put(jsonObj1);
                jsonarry.put(jsonObj2);
//               upLoadVideo(address1,aliyunPtahs, true);
//              upLoadVoice(address1,aliyunPtahs, true);
                String s2 = jsonarry.toString();
                subscriber.onNext(s2);
            }
        }).observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e(RenZhengActivityModelImpl.this, "onError :　" + e.getMessage());
                        e.printStackTrace();
                        listener.onFailure(e.getMessage());
                    }

                    @Override
                    public void onNext(String s2) {
                        submitdata(s2, code, listener);
                    }


                });
    }

    @Override
    public void submitdata(String str, String order_id, onLoadDateSingleListener listener) {
        Observable<BaseBean> observable = ServerApi.getAppAPI().getupto(str, order_id, UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<BaseBean>() {
            @Override
            public void onSuccess(BaseBean bean) {
                listener.onSuccess(bean, DataType.DATA_ONE);

            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });
    }


    public void upLoadVideo(String srcVideoPath, ArrayList<DynamicContent> aliyunPtahs, boolean isCharge) throws ClientException, ServiceException {


        //视频
        String vidoPath = AliyunManager.getInstance(ConfigUtils.getInstance().getApplicationContext()).upLoadFile(srcVideoPath, FilePathUtlis.FileType.MP4);
        //图片
        String videoThumbnail = VideoUtils.getInstance().getVideoThumbnail(srcVideoPath);
//        String videoThumbnail = VideoUtils.getInstance().getVideoThumbnail(videoPath);
        Bitmap size = ImageUtils.compressImageWithPathSzie(videoThumbnail, 1280, 720);
        //压缩大小
        Bitmap bitmap = ImageUtils.compressImage(size, 100);
//        String loadFile = AliyunManager.getInstance().upLoadFiletFromByteArray(ImageUtils.getBitmapByte(bitmap), FilePathUtlis.FileType.JPG);
        DynamicContent content = new DynamicContent();
        content.setV(vidoPath);
//        content.setL(loadFile);
        if (isCharge) {
            //压缩质量

            //宽高压缩
//            Bitmap bitmapsize = ImageUtils.compressImageSzie(bitmap, 600, 480);
            //质量压缩
            Bitmap bitmap1 = ImageUtils.compressImage(bitmap, 50);
            //模糊
            Bitmap bitmap2 = FastBlur.doBlur(bitmap1, 20, true);
            byte[] bitmapByte = ImageUtils.getBitmapByte(bitmap2);
            String fuzzyFile = AliyunManager.getInstance(ConfigUtils.getInstance().getApplicationContext()).upLoadFiletFromByteArray(bitmapByte, FilePathUtlis.FileType.JPG);

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

    //    @Override
//    public void sendDynamicPics(EnumConstant.DynamicType dynamicType, String content, List<String> srcdatas, String address, onLoadDateSingleListener defaultDynamicPresenter) {
//        LogUtils.e(RenZhengActivityModelImpl.this, "showTopBanner upload");
////        ArrayList<DynamicContent> aliyunPtahs = new ArrayList<DynamicContent>();
//
//        Observable.create(new Observable.OnSubscribe<ArrayList<DynamicContent>>() {
//            @Override
//            public void call(Subscriber<? super ArrayList<DynamicContent>> subscriber) {
//                upLoadFile(dynamicType, srcdatas, subscriber);
//            }
//        }).observeOn(Schedulers.io())
//                .subscribeOn(Schedulers.io())
//                .subscribe(new Subscriber<ArrayList<DynamicContent>>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        LogUtils.e(RenZhengActivityModelImpl.this, "onError :　" + e.getMessage());
//                        e.printStackTrace();
//                        defaultDynamicPresenter.onFailure(e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(ArrayList<DynamicContent> s) {
//                        LogUtils.e(RenZhengActivityPresenterImpl.class, "  onNext :　" + s.toString());
//                        String serialize = JsonUtils.serialize(s);
//                        LogUtils.e(RenZhengActivityModelImpl.this, "JsonUtils : " + serialize);
//                        sendAllDynamic(dynamicType, content, serialize, address, defaultDynamicPresenter);
//                    }
//                });
//    }
//    private void sendAllDynamic(EnumConstant.DynamicType dynamicType, String content, String json, String address, onLoadDateSingleListener listener) {
//        String authcode = UserInfoManager.getInstance().getAuthcode();
//        Observable<BaseBean> baseBeanObservable = ServerApi.getAppAPI().sendAllDefaultDynamic(dynamicType.getValue(), content, json, address, authcode, AppConstant.CHANNEL_ID);
//
//        ObservableUtils.parser(baseBeanObservable, new ObservableUtils.Callback<BaseBean>() {
//            @Override
//            public void onSuccess(BaseBean bean) {
//                listener.onSuccess(bean, DataType.DATA_ZERO);
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                listener.onFailure(msg);
//            }
//        });
//
//    }
//
//    private void upLoadFile(EnumConstant.DynamicType dynamicType, List<String> datas, Subscriber<? super ArrayList<DynamicContent>> subscriber) {
//        try {
//            ArrayList<DynamicContent> aliyunPtahs = new ArrayList<DynamicContent>();
//            switch (dynamicType) {
//                case FREE_PIC:
//                    upPic(datas, aliyunPtahs, false);
//                    break;
//                case CHARGE_PIC:
//                    upPic(datas, aliyunPtahs, true);
//                    break;
//                case FREE_VIDEO:
//                    upLoadVideo(datas.get(0), aliyunPtahs, false);
//                    break;
//                case CHARGE_VIDEO:
//                    upLoadVideo(datas.get(0), aliyunPtahs, true);
//                    break;
//                case FREE_VOICE:
//                    upLoadVoice(datas.get(0), aliyunPtahs, false);
//                    break;
//                case CHARGE_VOICE:
//                    upLoadVoice(datas.get(0), aliyunPtahs, true);
//                    break;
//
//            }
//            subscriber.onNext(aliyunPtahs);
//        } catch (ServiceException e) {
//            e.printStackTrace();
//            subscriber.onError(e);
//        } catch (ClientException e) {
//            e.printStackTrace();
//            subscriber.onError(e);
//        }
//
//
//    }
//
//    /**
//     * 上传图片
//     *
//     * @param pics
//     * @param aliyunPtahs
//     * @param isCharge
//     */
//    private void upPic(List<String> pics, ArrayList<DynamicContent> aliyunPtahs, boolean isCharge) {
//        for (int i = 0; i < pics.size(); i++) {
//            DynamicContent content = new DynamicContent();
//            String path = pics.get(i);
//            Bitmap size = ImageUtils.compressImageWithPathSzie(path, 800, 600);
//            Bitmap bitmap = ImageUtils.compressImage(size, 1000);
////                        String loadFile = AliyunManager.getInstance().upLoadFile(path, FilePathUtlis.FileType.JPG);
//            String loadFile = AliyunManager.getInstance().upLoadFiletFromByteArray(ImageUtils.getBitmapByte(bitmap), FilePathUtlis.FileType.JPG);
//            if (isCharge) {
//                //压缩质量
//
//                //宽高压缩
//                Bitmap bitmapsize = ImageUtils.compressImageSzie(bitmap, 200, 200);
//                //质量压缩
//                Bitmap bitmap1 = ImageUtils.compressImage(bitmapsize, 50);
//                //模糊
//                Bitmap bitmap2 = FastBlur.doBlur(bitmap1, 20, true);
//                byte[] bitmapByte = ImageUtils.getBitmapByte(bitmap2);
//                String fuzzyFile = AliyunManager.getInstance().upLoadFiletFromByteArray(bitmapByte, FilePathUtlis.FileType.JPG);
//                if (fuzzyFile == null)
//                    fuzzyFile = "";
//                content.setS(fuzzyFile);
//            } else {
//                content.setS("");
//            }
//            content.setL(loadFile);
//            aliyunPtahs.add(content);
//        }
//    }
//
//    public void upLoadVideo(String srcVideoPath, ArrayList<DynamicContent> aliyunPtahs, boolean isCharge) throws ClientException, ServiceException {
//
//
//        //视频
//        String vidoPath = AliyunManager.getInstance().upLoadFile(srcVideoPath, FilePathUtlis.FileType.MP4);
//        //图片
//        String videoThumbnail = VideoUtils.getInstance().getVideoThumbnail(srcVideoPath);
////        String videoThumbnail = VideoUtils.getInstance().getVideoThumbnail(videoPath);
//        Bitmap size = ImageUtils.compressImageWithPathSzie(videoThumbnail, 1280, 720);
//        //压缩大小
//        Bitmap bitmap = ImageUtils.compressImage(size, 100);
//        String loadFile = AliyunManager.getInstance().upLoadFiletFromByteArray(ImageUtils.getBitmapByte(bitmap), FilePathUtlis.FileType.JPG);
//        DynamicContent content = new DynamicContent();
//        content.setV(vidoPath);
//        content.setL(loadFile);
//        if (isCharge) {
//            //压缩质量
//
//            //宽高压缩
////            Bitmap bitmapsize = ImageUtils.compressImageSzie(bitmap, 600, 480);
//            //质量压缩
//            Bitmap bitmap1 = ImageUtils.compressImage(bitmap, 50);
//            //模糊
//            Bitmap bitmap2 = FastBlur.doBlur(bitmap1, 20, true);
//            byte[] bitmapByte = ImageUtils.getBitmapByte(bitmap2);
//            String fuzzyFile = AliyunManager.getInstance().upLoadFiletFromByteArray(bitmapByte, FilePathUtlis.FileType.JPG);
//
//            if (fuzzyFile == null)
//                fuzzyFile = "";
//            content.setS(fuzzyFile);
//        } else {
//            content.setS("");
//        }
//        File file = new File(videoThumbnail);
//        if (file.exists() && file.isFile()) {
//            file.delete();
//        }
//        aliyunPtahs.add(content);
//    }
//
    public void upLoadVoice(String videoPath, ArrayList<DynamicContent> aliyunPtahs, boolean isCharge) throws ClientException, ServiceException {
        String s = AliyunManager.getInstance(ConfigUtils.getInstance().getApplicationContext()).upLoadFile(videoPath, FilePathUtlis.FileType.AMR);
        DynamicContent content = new DynamicContent();
        content.setV(s);
        content.setL("");
        content.setS("");
        aliyunPtahs.add(content);
    }
}


//    @Override
//    public void upLoadIcon(String iconPath, onLoadDateSingleListener listener) {
//        Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                try {
//                    String host = AliyunManager.getInstance().upLoadFile(iconPath, FilePathUtlis.FileType.JPG);
//                    subscriber.onNext(host);
//                } catch (ClientException e) {
//                    subscriber.onError(e);
//                    e.printStackTrace();
//                } catch (ServiceException e) {
//                    e.printStackTrace();
//                    subscriber.onError(e);
//                }
//                subscriber.onCompleted();
//            }
//        }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<String>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        listener.onFailure(ConfigUtils.getInstance().getResources().getString(R.string.upload_failure));
//
//                    }
//
//                    @Override
//                    public void onNext(String host) {
//                        listener.onSuccess(host, DataType.DATA_ONE);
//                    }
//                });
//    }

