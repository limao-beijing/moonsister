package com.moonsister.tcjy.widget;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.moonsister.tcjy.ImageServerApi;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.bean.BackTermsBean;
import com.moonsister.tcjy.center.presenter.DynamicPublishPresenter;
import com.moonsister.tcjy.center.presenter.DynamicPublishPresenterImpl;
import com.moonsister.tcjy.center.view.DefaultDynamicView;
import com.moonsister.tcjy.center.widget.DynamicContentFragment;
import com.moonsister.tcjy.center.widget.DynamicPublishFragment;
import com.moonsister.tcjy.center.widget.VoiceActivity;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.main.widget.VideoSelectorActivity;
import com.moonsister.tcjy.manager.GaodeManager;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.my.persenter.RZSecondPersenter;
import com.moonsister.tcjy.my.persenter.RZSecondPersenterImpl;
import com.moonsister.tcjy.my.persenter.RenZhengAcivityPresenter;
import com.moonsister.tcjy.my.persenter.RenZhengActivityPresenterImpl;
import com.moonsister.tcjy.my.view.RZSecondView;
import com.moonsister.tcjy.my.view.RenZhengActivityView;
import com.moonsister.tcjy.my.widget.InsertActivity;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.EnumConstant;
import com.moonsister.tcjy.utils.StringUtis;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tcjy.widget.speak.PressToSpeakListenr;
import com.trello.rxlifecycle.ActivityEvent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by x on 2016/8/25.
 */
public class RenZhengActivity extends BaseActivity implements RenZhengActivityView {
    @Bind(R.id.riv_avater)//用户头像
            ImageView riv_avater;
    @Bind(R.id.vip_id)//会员ID
            TextView vip_id;
    @Bind(R.id.phone)//绑定手机号
            TextView phone;
    @Bind(R.id.image_back)
    ImageView image_back;
    @Bind(R.id.video)//上传视频
            ImageView video;
    @Bind(R.id.renzheng_yuyin)//上传语音
            ImageView renzheng_yuyin;
    @Bind(R.id.random)//录制语音视频需要念的文字
            TextView random;
    @Bind(R.id.tv_time)//录制时间
            TextView tv_time;
    @Bind(R.id.input)
    TextView input;
    private RenZhengAcivityPresenter persenter;
    private static final int RECORD_VIDEO = 1;
    //缩略图
    private Bitmap bitmap = null;
//    String videoasset;
    String facePath;
    String voicepat;
    String xmlpath;
    private DynamicPublishFragment dyf;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected View setRootContentView() {
        persenter = new RenZhengActivityPresenterImpl();
        persenter.attachView(this);
        persenter.LoadData();
        return UIUtils.inflateLayout(R.layout.renzhengactivity);
    }

    @Override
    protected void initView() {
        RxBus.with(this)
                .setEndEvent(ActivityEvent.DESTROY)
                .setEvent(Events.EventEnum.CERTIFICATION_PAGE_FINISH)
                .onNext(events -> pageFinish())
                .create();
//        ImageServerApi.showURLSamllImage(riv_avater, UserInfoManager.getInstance().getAvater());
//        vip_id.setText(UserInfoManager.getInstance().getUid());

    }
    private void pageFinish() {

        finish();
    }

//        phone.setText(UserInfoManager.getInstance().);


    /**
     * 录制视频
     **/
    public void startrecord(View view) {
        Intent mIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        mIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0.5);
        startActivityForResult(mIntent, RECORD_VIDEO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case RECORD_VIDEO:
                // 录制视频完成
                try {
                    AssetFileDescriptor videoAsset = getContentResolver()
                            .openAssetFileDescriptor(intent.getData(), "r");
                    FileInputStream fis = videoAsset.createInputStream();
                    File tmpFile = new File(
                            Environment.getExternalStorageDirectory(),
                            "recordvideo.mp4");
                    FileOutputStream fos = new FileOutputStream(tmpFile);

                    byte[] buf = new byte[1024];
                    int len;
                    while ((len = fis.read(buf)) > 0) {
                        fos.write(buf, 0, len);
                    }
                    fis.close();
                    fos.close();
                    // 文件写完之后删除/sdcard/dcim/CAMERA/XXX.MP4
                    deleteDefaultFile(intent.getData());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                video.setImageBitmap(bitmap);
                break;
            case RESULT_OK:

               voicepat = intent.getExtras().getString("path");//得到新Activity 关闭后返回的数据

                break;
        }
    }

    // 删除在/sdcard/dcim/Camera/默认生成的文件
    private void deleteDefaultFile(Uri uri) {
        String fileName = null;
        if (uri != null) {
            // content
            Log.d("Scheme", uri.getScheme().toString());
            if (uri.getScheme().toString().equals("content")) {
                Cursor cursor = this.getContentResolver().query(uri, null,
                        null, null, null);
                if (cursor.moveToNext()) {
                    int columnIndex = cursor
                            .getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
                    fileName = cursor.getString(columnIndex);
                    //获取缩略图id
                    int id = cursor.getInt(cursor
                            .getColumnIndex(MediaStore.Video.VideoColumns._ID));
                    //获取缩略图
                    bitmap = MediaStore.Video.Thumbnails.getThumbnail(
                            getContentResolver(), id, MediaStore.Video.Thumbnails.MICRO_KIND,
                            null);

                    if (!fileName.startsWith("/mnt")) {
                        fileName = "/mnt/" + fileName;
                    }
                    Log.d("fileName", fileName);
                }
            }
        }
        xmlpath = String.valueOf(this.getClass().getClassLoader().getResource(fileName));
        // 删除文件
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
            Log.d("delete", "删除成功");
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void transfePageMsg(String msg) {
        showToast(msg);
    }

    @OnClick({R.id.image_back, R.id.video, R.id.renzheng_yuyin,R.id.input})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_back:
                RenZhengActivity.this.finish();
                break;
            case R.id.video:

                startrecord(view);

                break;
            case R.id.renzheng_yuyin:
//                Intent in=new Intent(RenZhengActivity.this, VoiceActivity.class);
//                startActivity(in);
                startActivityForResult(new Intent(RenZhengActivity.this, VoiceActivity.class), 1);

                break;
            case R.id.input:
//                presenter1.sendDynamic(type1, txtContent1, dynamicContent1, voicepat);
                submit();
//
                break;
        }
    }

    @Override
    public void success(BackTermsBean backTermsBean) {
        random.setText(backTermsBean.getData());
    }

    @Override
    public void finishPage() {
//        ActivityUtils.startRZThidActivity();
//        RxBus.getInstance().send(Events.EventEnum.CERTIFICATION_PAGE_FINISH, null);
        finish();
    }

    private void submit() {
        //点击提交审核后弹出dialog提醒用户是否继续
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String s = getResources().getString(R.string.tv_ren) + getString(R.string.pay_is_go);
        builder.setMessage(s);
        builder.setTitle("提示");
        //dialog确认监听，用户点击确认则提交审核，得到并且判断之前的数据，跳转页面及带参数
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                persenter.submit(xmlpath,voicepat);
            }
        });
        //dialog取消监听
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
//    @Override
//    public void uploadSuccess(String path) {
//        this.facePath = path;
//    }
}
