package com.hickey.tool.manager;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.widget.Toast;

import com.hickey.tool.file.SDUtils;
import com.hickey.tool.lang.StringUtis;
import com.hickey.tool.url.URIUtils;


/**
 * Created by jb on 2016/11/16.
 */

public class FileDownManger {


    private boolean isDownAp;
    private long enqueue;
    private DownloadManager dm;

    public void downApk(Context context, String url) {
        Toast.makeText(context, "开始下载....", Toast.LENGTH_LONG).show();
        dm = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(
                Uri.parse(url));
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);//只在下载过程中显示通知栏，下载完成后会自动消失
        request.setMimeType("application/vnd.android.package-archive");
        String name = url.contains("/") ? url.substring(url.lastIndexOf("/") + 1) : System.currentTimeMillis() + "";
        request.setDestinationInExternalPublicDir(SDUtils.getRootFile(context), name);
        //设置下载的文件的类型
        request.setMimeType("application/vnd.android.package-archive");
        //设置标题和描述
        request.setTitle(name);
        enqueue = dm.enqueue(request);
        //获取唯一id
        //注册下载完成的广播
        context.registerReceiver(receiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        isDownAp = true;
    }

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
                long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 0);
                if (enqueue == id) {
                    installFile(context, id);
                }
            }


        }
    };

    //安装应用，指定位置
    private void installFile(Context context, long id) {
        Uri downloadFileUri = dm.getUriForDownloadedFile(id);
        String path = URIUtils.getRealFilePath(context, downloadFileUri);
        if (!StringUtis.isEmpty(path))
            installApk(context, path);
    }

    /**
     * 安装APK文件
     */
    private void installApk(Context context, String path) {

        // 通过Intent安装APK文件
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setDataAndType(Uri.parse("file://" + path),
                "application/vnd.android.package-archive");
        context.startActivity(i);


    }

}
