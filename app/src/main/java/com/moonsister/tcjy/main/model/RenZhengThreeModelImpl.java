package com.moonsister.tcjy.main.model;

import com.hickey.network.ServerApi;
import com.hickey.network.bean.BaseBean;
import com.hickey.tool.parse.JsonUtils;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.center.widget.DynamicContentFragment;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.manager.aliyun.AliyunManager;
import com.moonsister.tcjy.utils.FilePathUtlis;
import com.moonsister.tcjy.utils.ObservableUtils;
import com.moonsister.tcjy.utils.UIUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;


/**
 * Created by jb on 2016/10/10.
 */
public class RenZhengThreeModelImpl implements RenZhengThreeModel {
    @Override
    public void submit(List<String> content, DynamicContentFragment.DynamicType type, BaseIModel.onLoadDateSingleListener listener) {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    JSONObject object = new JSONObject();
                    switch (type) {
                        case VIDEO:

                            String video = AliyunManager.getInstance().upLoadFile(content.get(0), FilePathUtlis.FileType.MP4);
                            object.put("video", video);
                            object.put("voice", "");
                            object.put("image", "");

                            break;
                        case PIC:
                            ArrayList<String> strings = new ArrayList<>();
                            for (String path : content) {
                                String pic = AliyunManager.getInstance().upLoadFile(path, FilePathUtlis.FileType.JPG);
                                strings.add(pic);
                            }
                            object.put("video", "");
                            object.put("voice", "");
                            object.put("image", JsonUtils.serialize(strings));
                            break;
                        case VOICE:
                            String voice = AliyunManager.getInstance().upLoadFile(content.get(0), FilePathUtlis.FileType.AMR);
                            object.put("video", "");
                            object.put("voice", voice);
                            object.put("image", "");
                            break;
                    }
                    subscriber.onNext(object.toString());
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }

            }
        }).observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        UIUtils.onRunMainThred(new Runnable() {
                            @Override
                            public void run() {
                                listener.onFailure(UIUtils.getStringRes(R.string.request_failed));
                            }
                        });

                    }

                    @Override
                    public void onNext(String s) {
                        upService(s, listener);
                    }
                });
    }

    private void upService(String content, BaseIModel.onLoadDateSingleListener listener) {
        Observable<BaseBean> observable = ServerApi.getAppAPI().getRenzhengThree(content, UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<BaseBean>() {
            @Override
            public void onSuccess(BaseBean o) {
                listener.onSuccess(o, BaseIModel.DataType.DATA_ZERO);
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });
    }


}
