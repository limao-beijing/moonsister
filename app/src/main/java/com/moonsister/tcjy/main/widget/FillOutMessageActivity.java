package com.moonsister.tcjy.main.widget;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.moonsister.tcjy.ImageServerApi;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.login.widget.SelectPicPopupActivity;
import com.moonsister.tcjy.main.presenter.FillOutActivityPresenter;
import com.moonsister.tcjy.main.presenter.FillOutActivityPresenterImpl;
import com.moonsister.tcjy.main.view.FilloutActivityView;
import com.moonsister.tcjy.manager.aliyun.AliyunManager;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.ConfigUtils;
import com.moonsister.tcjy.utils.FilePathUtlis;
import com.moonsister.tcjy.utils.LogUtils;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tcjy.widget.image.PhonePicActivity;
import com.trello.rxlifecycle.FragmentEvent;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by x on 2016/8/30.
 */
public class FillOutMessageActivity extends BaseActivity implements FilloutActivityView{
    //调用系统相册-选择图片
    private static final int IMAGE = 1;

    @Bind(R.id.fillout_ok)//完成
    TextView fillout_ok;
    @Bind(R.id.fillout_go)//跳过
    TextView fillout_go;
    @Bind(R.id.fillout_imageview)//上传头像
    ImageView fillout_imageview;
    @Bind(R.id.fillout_message_name)//设置用户昵称
    EditText fillout_message_name;
    FillOutActivityPresenter presenter;
    String imagePath;//条用系统相册后返回的图片路径
    String str;//得到用户输入的昵称
    @Override
    protected View setRootContentView() {
        presenter=new FillOutActivityPresenterImpl();
        presenter.attachView(this);
        return UIUtils.inflateLayout(R.layout.activity_filloutmessage);
    }

    @Override
    protected void initView() {
        str=fillout_message_name.getText().toString().trim();
    }

    @OnClick({R.id.fillout_ok,R.id.fillout_go,R.id.fillout_imageview})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fillout_ok:
                presenter.fillout(imagePath,str);
                Intent intent=new Intent(FillOutMessageActivity.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.fillout_go:
                Intent in=new Intent(FillOutMessageActivity.this,MainActivity.class);
                startActivity(in);
                break;
            case R.id.fillout_imageview:
                Intent intent1 = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent1, IMAGE);

                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获取图片路径
        if (requestCode == IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            imagePath = c.getString(columnIndex);
            showImage(imagePath);
            c.close();
            showLoading();

            new Thread(new Runnable() {
                @Override
                public void run() {
//                    RxBus.with(this)
//                            .setEvent(Events.EventEnum.GET_PHOTO)
//                            .setEndEvent(FragmentEvent.DESTROY)
//                            .onNext((events) -> {
//                                String message = (String) events.message;
////                        LogUtils.e(RegiterDataFragment.class, "pic_path : " + message);
//
//                                ImageServerApi.showURLImage(fillout_imageview, imagePath);
//                                presenter.submit(imagePath);
//                            }).create();
                    try {
                        imagePath = AliyunManager.getInstance().upLoadFile(imagePath, FilePathUtlis.FileType.JPG);
                        UIUtils.onRunMainThred(new Runnable() {
                            @Override
                            public void run() {
                                hideLoading();
                            }
                        });
                    } catch (ClientException e) {
                        e.printStackTrace();
                    } catch (ServiceException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    //加载图片
    private void showImage(String imaePath){
        Bitmap bm = BitmapFactory.decodeFile(imaePath);
        ((ImageView)findViewById(R.id.fillout_imageview)).setImageBitmap(bm);
    }


    @Override
    public void filloutactivity() {
        String message_name = fillout_message_name.getText().toString();
        presenter.fillout(imagePath,message_name);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void transfePageMsg(String msg) {

    }
}
