package com.hyphenate.easeui.mvp.model;

import android.content.Context;

import com.hickey.network.ModuleServerApi;
import com.hickey.network.aliyun.AliyunManager;
import com.hickey.network.aliyun.FilePathUtlis;
import com.hickey.network.bean.resposen.ChargeMessageBean;
import com.hickey.tool.base.BaseResponse;
import com.hyphenate.easeui.ui.ChargeMessageActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;


/**
 * Created by jb on 2016/11/26.
 */
public class ChargeMessageActivityModelImpl implements ChargeMessageActivityModel {
    @Override
    public void submitData(Context context, final String money, final List<String> contents, final String desc, final int type, final String uid, final long duration, final String authcode, onLoadDateSingleListener<ChargeMessageBean> listenter) {
        Observable<BaseResponse<ChargeMessageBean>> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                uploadData(null, contents, type, duration, subscriber);
            }
        }).flatMap(new Func1<String, Observable<BaseResponse<ChargeMessageBean>>>() {
            @Override
            public Observable<BaseResponse<ChargeMessageBean>> call(String s) {
                return ModuleServerApi.getAppAPI().sendChargeMsg(money, s, desc, type, uid, authcode);
            }
        });
        ObservableUtis.$(observable, DataType.DATA_ZERO, listenter);
    }

    private void uploadData(Context context, List<String> contents, int type, long duration, Subscriber<? super String> subscriber) {

        try {
            if (type == ChargeMessageActivity.TYPE_VIDEO) {
                String videoPtah = AliyunManager.getInstance(context).upLoadFile(contents.get(0), FilePathUtlis.FileType.MP4);
                String videoPic = AliyunManager.getInstance(context).upLoadFile(contents.get(1), FilePathUtlis.FileType.JPG);
                JSONObject videoJson = new JSONObject();
                videoJson.put("v", videoPtah);
                videoJson.put("l", videoPic);
                videoJson.put("s", "");
                videoJson.put("sc", duration);
                videoJson.put("size", "");
                subscriber.onNext(videoJson.toString());
            } else {
                JSONObject picjson = new JSONObject();
                JSONArray picJsons = new JSONArray();
                for (String s : contents) {
                    JSONObject data = new JSONObject();
                    String s1 = AliyunManager.getInstance(context).upLoadFile(s, FilePathUtlis.FileType.JPG);
                    data.put("l", s1);
                    picJsons.put(data);
                }
                picjson.put("cont", picJsons);
                subscriber.onNext(picjson.toString());
            }
        } catch (Exception e) {
            subscriber.onError(e);
        }
    }
}
