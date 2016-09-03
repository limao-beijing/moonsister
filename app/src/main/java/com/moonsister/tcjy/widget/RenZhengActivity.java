package com.moonsister.tcjy.widget;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.moonsister.tcjy.ImageServerApi;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.bean.BackTermsBean;
import com.moonsister.tcjy.center.widget.VoiceActivity;
import com.moonsister.tcjy.main.widget.VideoSelectorActivity;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.my.persenter.RZSecondPersenter;
import com.moonsister.tcjy.my.persenter.RZSecondPersenterImpl;
import com.moonsister.tcjy.my.persenter.RenZhengAcivityPresenter;
import com.moonsister.tcjy.my.persenter.RenZhengActivityPresenterImpl;
import com.moonsister.tcjy.my.view.RZSecondView;
import com.moonsister.tcjy.my.view.RenZhengActivityView;
import com.moonsister.tcjy.my.widget.InsertActivity;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tcjy.widget.speak.PressToSpeakListenr;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by x on 2016/8/25.
 */
public class RenZhengActivity extends BaseActivity implements RenZhengActivityView{
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
    private RenZhengAcivityPresenter persenter;
    private static final int RECORD_VIDEO = 1;
    //缩略图
    private Bitmap bitmap = null;
    String videoasset;
    String facePath;
    @Override
    protected View setRootContentView() {
        persenter = new RenZhengActivityPresenterImpl();
        persenter.attachView(this);
        persenter.LoadData();
        return UIUtils.inflateLayout(R.layout.renzhengactivity);
    }

    @Override
    protected void initView() {
        ImageServerApi.showURLSamllImage(riv_avater, UserInfoManager.getInstance().getAvater());
        vip_id.setText(UserInfoManager.getInstance().getUid());

    }

//        phone.setText(UserInfoManager.getInstance().);


    /**录制视频**/
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
                    videoasset=videoAsset.toString();
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
                String result = intent.getExtras().getString("result");//得到新Activity 关闭后返回的数据

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

    @OnClick({R.id.image_back,R.id.video,R.id.renzheng_yuyin})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.image_back:
                RenZhengActivity.this.finish();
                break;
            case R.id.video:
                startrecord(view);
//                Intent in=new Intent(RenZhengActivity.this, VideoSelectorActivity.class);
//                startActivity(in);
//                persenter.upLoadIcon(videoasset);
                break;
            case R.id.renzheng_yuyin:
//                Intent in=new Intent(RenZhengActivity.this, VoiceActivity.class);
//                startActivity(in);
                startActivityForResult(new Intent(RenZhengActivity.this, VoiceActivity.class), 1);
                break;
        }
    }

    @Override
    public void success(BackTermsBean backTermsBean) {
        random.setText(backTermsBean.getData());
    }


//    @Override
//    public void uploadSuccess(String path) {
//        this.facePath = path;
//    }
}
