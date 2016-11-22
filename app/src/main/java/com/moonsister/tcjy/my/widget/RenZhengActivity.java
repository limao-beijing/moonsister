package com.moonsister.tcjy.my.widget;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hickey.network.ImageServerApi;
import com.hickey.network.bean.BackTermsBean;
import com.hickey.tool.url.URIUtils;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.center.widget.DynamicPublishFragment;
import com.moonsister.tcjy.center.widget.VoiceActivity;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.my.persenter.RenZhengAcivityPresenter;
import com.moonsister.tcjy.my.persenter.RenZhengActivityPresenterImpl;
import com.moonsister.tcjy.my.view.RenZhengActivityView;
import com.moonsister.tcjy.utils.SDUtils;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tcjy.utils.VideoUtils;
import com.trello.rxlifecycle.ActivityEvent;

import java.io.File;

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

    @Bind(R.id.input)
    TextView input;
    private RenZhengAcivityPresenter persenter;
    private static final int RECORD_VIDEO = 1;
    //缩略图
    private Bitmap bitmap = null;
    //    String videoasset;
    String facePath;
    String realFilePath;
    private DynamicPublishFragment dyf;
    CharSequence text;

    @Override
    protected View setRootContentView() {
        persenter = new RenZhengActivityPresenterImpl();
        persenter.attachView(this);
        return UIUtils.inflateLayout(R.layout.renzhengactivity);
    }

    String path;

    @Override
    protected void initView() {
        persenter.LoadData();
        RxBus.with(this)
                .setEndEvent(ActivityEvent.DESTROY)
                .setEvent(Events.EventEnum.CERTIFICATION_PAGE_FINISH)
                .onNext(events -> pageFinish())
                .create();

    }

    private void pageFinish() {

        finish();
    }

//        phone.setText(UserInfoManager.getInstance().);

    String uri2;

    /**
     * 录制视频
     **/
    public void startrecord(View view) {


        Intent intent = new Intent();
        intent.setAction("android.media.action.VIDEO_CAPTURE");
        intent.addCategory("android.intent.category.DEFAULT");
        File file = new File(SDUtils.getRootFile(this) + File.separator + System.currentTimeMillis() + ".mp4");
        if (file.exists()) {
            file.delete();
        }
        uri2 = String.valueOf(Uri.fromFile(file));
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri2);
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        intent.putExtra(MediaStore.EXTRA_SIZE_LIMIT, 1024 * 1024 * 50);
        intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 20);
        startActivityForResult(intent, RECORD_VIDEO);
    }

    String voicepat;

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (intent == null)
            return;

        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case RECORD_VIDEO:

                realFilePath = URIUtils.getRealFilePath(getApplicationContext(), intent.getData());
                String videoThumbnail = VideoUtils.getInstance().getVideoThumbnail(realFilePath);
                ImageServerApi.showURLBigImage(video, videoThumbnail);
//                video.setImageBitmap(bitmap);

                break;
//            case RESULT_OK:
//
//               voicepat = intent.getExtras().getString("path");//得到新Activity 关闭后返回的数据
//
//                break;
            case 3:
                voicepat = intent.getStringExtra("path");
                renzheng_yuyin.setImageResource(R.mipmap.a);
                break;
        }
    }

    String fileName = null;

    // 删除在/sdcard/dcim/Camera/默认生成的文件
    private void deleteDefaultFile(Uri uri) {

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
//        xmlpath = String.valueOf(this.getClass().getClassLoader().getResource(fileName));
        // 删除文件
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
            Log.d("delete", "删除成功");
        }
    }

    @Override
    public void showLoading() {
        showProgressDialog();
    }

    @Override
    public void hideLoading() {
        hideProgressDialog();
    }

    @Override
    public void transfePageMsg(String msg) {
        showToast(msg);
    }

    @OnClick({R.id.image_back, R.id.video, R.id.renzheng_yuyin, R.id.input})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_back:
                RenZhengActivity.this.finish();
                break;
            case R.id.video:

                startrecord(view);

                break;
            case R.id.renzheng_yuyin:
                startActivityForResult(new Intent(RenZhengActivity.this, VoiceActivity.class), 3);

                break;
            case R.id.input:
//                presenter1.sendDynamic(type1, txtContent1, dynamicContent1, voicepat);
//                if (StringUtis.isEmpty(realFilePath) || StringUtis.isEmpty(voicepat)) {
//                    showToast("请先录制视频/语音");
//                } else {
                    submit();
//                }

//
                break;
        }
    }

    @Override
    public void success(BackTermsBean backTermsBean) {
        random.setText(backTermsBean.getData().getVoice_info());
        ImageServerApi.showURLSamllImage(riv_avater, backTermsBean.getData().getFace());
        vip_id.setText(backTermsBean.getData().getUid() + "");
        String mobile = backTermsBean.getData().getMobile();
        if(mobile.equals("null")){
            phone.setVisibility(View.INVISIBLE);
        }else{
            phone.setText(mobile+"");
        }
    }

    @Override
    public void finishPage() {
//        ActivityUtils.startRZThidActivity();
//        RxBus.getInstance().send(Events.EventEnum.CERTIFICATION_PAGE_FINISH, null);
        finish();
    }

    String str;
    String order_id;

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

                if (realFilePath != null ) {
                    persenter.submit(realFilePath, voicepat, random.getText().toString());
                }else if(voicepat != null){
                    persenter.submit(realFilePath, voicepat, random.getText().toString());
                }


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

}
