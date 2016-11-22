package com.moonsister.tcjy.update.model;

import android.os.Looper;
import android.util.Log;

import com.hickey.network.ServerApi;
import com.hickey.network.bean.VersionInfo;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.update.down.DownloadProgressHandler;
import com.moonsister.tcjy.update.down.ProgressHelper;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jb on 2016/7/14.
 */
public class UpdateManagerModelImpl implements UpdateManagerModel {
    @Override
    public void loadVersionInfo(onLoadDateSingleListener listener) {
        ServerApi.getAppAPI().getLoadVersonInfo(AppConstant.CHANNEL_ID)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<VersionInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onFailure(e.getMessage());
                    }

                    @Override
                    public void onNext(VersionInfo versionInfo) {
                        listener.onSuccess(versionInfo, DataType.DATA_ZERO);
                    }
                });
    }

    public void downFile(String url, String savePath, onDownFileleListener listener) {
        ProgressHelper.setProgressHandler(new DownloadProgressHandler() {
            @Override
            protected void onProgress(long bytesRead, long contentLength, boolean done) {
                Log.e("是否在主线程中运行", String.valueOf(Looper.getMainLooper() == Looper.myLooper()));
                Log.e("onProgress", String.format("%d%% done\n", (100 * bytesRead) / contentLength));
                Log.e("done", "--->" + String.valueOf(done));
                listener.onDownFileleProgress(bytesRead, contentLength, done);

            }
        });
        File apkfile = new File(savePath);
        if (apkfile.exists()) {
            apkfile.delete();
        }

        final String finalUrl = url;
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                OkHttpClient.Builder builder = ProgressHelper.addProgress(null);
                Request request = new Request.Builder().url(finalUrl).build();
                okhttp3.Call call = builder.build().newCall(request);
                try {
                    Response response = call.execute();
                    Log.e("enqueue是否在主线程中运行", String.valueOf(Looper.getMainLooper() == Looper.myLooper()));
                    InputStream is = response.body().byteStream();
                    File file = new File(savePath);
                    FileOutputStream fos = new FileOutputStream(file);
                    BufferedInputStream bis = new BufferedInputStream(is);
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = bis.read(buffer)) != -1) {
                        fos.write(buffer, 0, len);
                        fos.flush();
                    }
                    fos.close();
                    bis.close();
                    is.close();
                    Log.e("enqueue是否在主线程中运行", file.length() + "");
                    subscriber.onNext(savePath);

                } catch (IOException e) {
                    subscriber.onError(e);
                    e.printStackTrace();
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onDownFileleFailure(e.getMessage());
                    }

                    @Override
                    public void onNext(String s) {
                        listener.onDownFileleSuccess();
                    }
                });


    }


}
