package com.moonsister.tcjy.widget.image;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.utils.LogUtils;
import com.moonsister.tcjy.utils.UIUtils;
import com.soundcloud.android.crop.Crop;

import java.io.File;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by jb on 2016/7/12.
 */
public class CropImageMainActivity extends BaseActivity {
    @Bind(R.id.result_image)
    ImageView resultImage;
    private Uri uri;

    @Override
    protected View setRootContentView() {
        return UIUtils.inflateLayout(R.layout.activity_crop_main_image);
    }

    @Override
    protected void initView() {

    }


    @OnClick({R.id.tv_select_pic, R.id.tv_finish})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_select_pic:
                resultImage.setImageDrawable(null);
                Crop.pickImage(this);
                break;
            case R.id.tv_finish:
                if (uri == null) {
                    showToast(UIUtils.getStringRes(R.string.select_pic));
                    return;
                }
                String filePath = getRealFilePath(this, uri);
                if (filePath == null) {
                    showToast(UIUtils.getStringRes(R.string.select_pic));
                    return;
                }
                LogUtils.d(this, "裁剪的路径 ： " + filePath);
                Events<String> events = new Events<String>();
                events.what = Events.EventEnum.CROP_IMAGE_PATH;
                events.message = filePath;
                RxBus.getInstance().send(events);
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent result) {
        if (requestCode == Crop.REQUEST_PICK && resultCode == RESULT_OK) {
            beginCrop(result.getData());
        } else if (requestCode == Crop.REQUEST_CROP) {
            handleCrop(resultCode, result);
        }
    }

    private void beginCrop(Uri source) {
        Uri destination = Uri.fromFile(new File(getCacheDir(), "cropped" + System.currentTimeMillis() + ".jpg"));
        Crop.of(source, destination).withAspect(720, 460).start(this);
    }

    private void handleCrop(int resultCode, Intent result) {
        if (resultCode == RESULT_OK) {
            uri = Crop.getOutput(result);
            resultImage.setImageURI(uri);
        } else if (resultCode == Crop.RESULT_ERROR) {
            Toast.makeText(this, Crop.getError(result).getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 转换Uri
     *
     * @param context
     * @param uri
     * @return
     */
    public static String getRealFilePath(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

}
