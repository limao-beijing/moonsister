package com.moonsister.tcjy.find.widget;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.ServerApi;
import com.moonsister.tcjy.base.BaseFragment;
import com.moonsister.tcjy.bean.DownApkBean;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.ObservableUtils;
import com.moonsister.tool.lang.StringUtis;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tool.url.URIUtils;

import butterknife.OnClick;
import rx.Observable;

/**
 * Created by pc on 2016/5/31.
 */
public class FindFragment extends BaseFragment {
    private DownloadManager dm;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return UIUtils.inflateLayout(R.layout.fragment_find, container);
    }

    @Override
    protected void initData() {
    }

    //
    @OnClick({R.id.relative_video, R.id.relative_rank, R.id.relative_activi, R.id.relative_nearby,})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.relative_video:
                ActivityUtils.starVideoDynamicActivity();
                break;
            case R.id.relative_activi:
                if (!isDownAp)
                    getAPK();
                else
                    showToast("刚已经下载过啦");
                break;
            case R.id.relative_nearby:
                ActivityUtils.startRankActivity();
//                ActivityUtils.startNearbyActivity();
                break;
            case R.id.relative_rank://排行榜

                break;
        }
    }

    private void getAPK() {
        Observable<DownApkBean> observable = ServerApi.getAppAPI().getDownApk(UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<DownApkBean>() {
            @Override
            public void onSuccess(DownApkBean bean) {
                if (StringUtis.equals(bean.getCode(), "1")) {
                    if (!StringUtis.isEmpty(bean.getData().getUrl())) {
                        downApk(bean.getData().getUrl(), bean.getData().getName());
                    }
                    showToast(bean.getData().getMsg());
                }
            }

            @Override
            public void onFailure(String msg) {

            }
        });

    }

    private boolean isDownAp = false;

    private void downApk(String url, String name) {
        dm = (DownloadManager) getContext().getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(
                Uri.parse(url));
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);//只在下载过程中显示通知栏，下载完成后会自动消失
        request.setMimeType("application/vnd.android.package-archive");
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "myApp.apk");
        //设置下载的文件的类型
        request.setMimeType("application/vnd.android.package-archive");
        //设置标题和描述
        request.setTitle(name);
        enqueue = dm.enqueue(request);
        //获取唯一id
        //注册下载完成的广播
        getContext().registerReceiver(receiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        isDownAp = true;
    }

    private long enqueue;
    //下载完成的广播

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
                long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 0);
                if (enqueue == id) {
                    installFile(id);
                }
            }


        }
    };


    //安装应用，指定位置
    private void installFile(long id) {
        Intent install = new Intent(Intent.ACTION_VIEW);
        Uri downloadFileUri = dm.getUriForDownloadedFile(id);
        String path = URIUtils.getRealFilePath(getContext(), downloadFileUri);
        if (!StringUtis.isEmpty(path))
            installApk(path);
//        install.setData(downloadFileUri);
//        install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(install);

//        Intent i = new Intent();
//        i.setAction(Intent.ACTION_VIEW);
//        Uri uri= Uri.fromFile(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS));
//        i.setData(uri);
//        startActivity(i);
    }

    /**
     * 安装APK文件
     */
    private void installApk(String path) {

        // 通过Intent安装APK文件
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setDataAndType(Uri.parse("file://" + path),
                "application/vnd.android.package-archive");
        getContext().startActivity(i);


    }


}