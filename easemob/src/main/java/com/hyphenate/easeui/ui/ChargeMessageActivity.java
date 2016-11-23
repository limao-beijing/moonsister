package com.hyphenate.easeui.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.easemob.easeui.R;
import com.hickey.network.AppointmentServerApi;
import com.hickey.network.bean.ChargeMessageBean;
import com.hickey.tool.activity.pic.PictureSelectorActivity;
import com.hickey.tool.activity.video.ImageGridActivity;
import com.hickey.tool.widget.NoScrollGridView;
import com.hyphenate.util.PathUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by jb on 2016/11/22.
 */

public class ChargeMessageActivity extends AppCompatActivity implements View.OnClickListener {


    private static final int REQUEST_CODE_SELECT_VIDEO = 1;

    private static final int REQUEST_CODE_SELECT_IMAGSES = 2;

    public static final int TYPE_VIDEO = 1;
    public static final int TYPE_IMAGE = 2;
    private ImageView iv_voice;
    private View ll_select_layout;
    private NoScrollGridView gv;
    private EditText et_money;
    private EditText et_msg;
    //image
    private List<String> pics;
    //发送类型
    private int type;
    //video
    private String videoPath;
    private int duration;
    //user
    private String toUid;
    private String authcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ease_activity_charge_message);
        Intent intent = getIntent();
        toUid = intent.getStringExtra("id");
        authcode = intent.getStringExtra("authcode");
        initView();
        initListener();

    }

    private void initView() {

        iv_voice = $(R.id.iv_voice);
        gv = $(R.id.gv);
        ll_select_layout = $(R.id.ll_select_layout);
        et_money = $(R.id.et_money);
        et_msg = $(R.id.et_msg);
    }


    private void initListener() {
        $(R.id.action_back).setOnClickListener(this);
        $(R.id.tv_add_image).setOnClickListener(this);
        $(R.id.tv_add_video).setOnClickListener(this);
        $(R.id.tv_send).setOnClickListener(this);
        $(R.id.tv_reset).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.action_back) {
            finish();
        } else if (i == R.id.tv_add_image) {
            addImage();
        } else if (i == R.id.tv_add_video) {
            Intent intent = new Intent(this, ImageGridActivity.class);
            startActivityForResult(intent, REQUEST_CODE_SELECT_VIDEO);
        } else if (i == R.id.tv_reset) {
            ll_select_layout.setVisibility(View.VISIBLE);
            gv.setVisibility(View.GONE);
            iv_voice.setVisibility(View.GONE);
            et_money.setText("");
            et_msg.setText("");
            if (pics != null) {
                pics.clear();
            }
        } else if (i == R.id.tv_send) {
            String msg = et_msg.getText().toString().trim();
            String money = et_money.getText().toString().trim();
            if (type == 0 || TextUtils.isEmpty(msg) || TextUtils.isEmpty(money)) {
                Toast.makeText(this, "信息不完整", Toast.LENGTH_SHORT).show();
                return;
            }
            if (type == TYPE_VIDEO) {
                if (TextUtils.isEmpty(videoPath)) {
                    Toast.makeText(this, "信息不完整", Toast.LENGTH_SHORT).show();
                    return;
                }
                sendData(money, "", msg, TYPE_VIDEO, toUid, authcode);


            } else if (type == TYPE_IMAGE) {
                if (pics == null || pics.size() == 0) {
                    Toast.makeText(this, "信息不完整", Toast.LENGTH_SHORT).show();
                    return;
                }
                sendData(money, "", msg, TYPE_IMAGE, toUid, authcode);
            } else {
                Toast.makeText(this, "信息不完整", Toast.LENGTH_SHORT).show();
                return;
            }

        }
    }

    private void addImage() {
        Intent intent = new Intent(this, PictureSelectorActivity.class);
        int munber = 0;
        if (pics != null) {
            munber = pics.size();
        }
        intent.putExtra("max", munber);
        startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGSES);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_SELECT_VIDEO:
                    if (data != null) {
                        duration = data.getIntExtra("dur", 0);
                        videoPath = data.getStringExtra("path");
                        File file = new File(PathUtil.getInstance().getImagePath(), "thvideo" + System.currentTimeMillis());
                        try {
                            FileOutputStream fos = new FileOutputStream(file);
                            Bitmap ThumbBitmap = ThumbnailUtils.createVideoThumbnail(videoPath, 3);
                            ThumbBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                            fos.close();
                            Glide.with(this).load(file).into(iv_voice);
//                            sendVideoMessage(videoPath, file.getAbsolutePath(), duration);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    ll_select_layout.setVisibility(View.GONE);
                    gv.setVisibility(View.GONE);
                    iv_voice.setVisibility(View.VISIBLE);
                    type = TYPE_VIDEO;
                    break;

                case REQUEST_CODE_SELECT_IMAGSES:
                    ArrayList listExtra = data.getStringArrayListExtra("android.intent.extra.RETURN_RESULT");
                    if (listExtra == null)
                        return;
                    if (pics == null)
                        pics = new ArrayList();
                    if ((pics.size() + listExtra.size()) > 9) {
                        Toast.makeText(this, "图片最多九张", Toast.LENGTH_SHORT).show();
                    }
                    for (Object obj : listExtra) {
                        if (obj instanceof String) {
                            pics.add((String) obj);
                        } else if (obj instanceof Uri) {
                            pics.add(((Uri) obj).getPath());
                        }
                    }
                    ShowPicAdapter showPicAdapter = new ShowPicAdapter();
                    gv.setAdapter(showPicAdapter);
                    ll_select_layout.setVisibility(View.GONE);
                    gv.setVisibility(View.VISIBLE);
                    iv_voice.setVisibility(View.GONE);
                    type = TYPE_IMAGE;
                    break;
            }
        }
    }


    private class ShowPicAdapter extends BaseAdapter {

        public ShowPicAdapter() {
        }

        @Override
        public int getCount() {
            return pics == null ? 0 : (pics.size() < 9 ? pics.size() + 1 : pics.size());
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }


        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = View.inflate(parent.getContext(), R.layout.item_em_dynamic_publish, null);
            ImageView pic = (ImageView) view.findViewById(R.id.iv_pic);
            View delete = view.findViewById(R.id.iv_delete_pic);
            delete.setVisibility(View.VISIBLE);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pics.remove(position);

//                    if (pics.size() == 0)
//                        ivAddContent.setVisibility(View.VISIBLE);
                    notifyDataSetChanged();
                }
            });
            if (pics.size() < 9) {
                if (position == pics.size()) {
                    Glide.with(parent.getContext()).load(R.drawable.em_add_images).into(pic);

                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            addImage();
                        }
                    });
                    delete.setVisibility(View.GONE);
                } else
                    Glide.with(parent.getContext()).load(pics.get(position)).into(pic);

            } else {
                Glide.with(parent.getContext()).load(pics.get(position)).into(pic);
            }

            return view;
        }
    }

    public void sendData(String money, String content, String desc, int type, String toUid, String authcode) {
        Observable<ChargeMessageBean> observable = AppointmentServerApi.getAppAPI().sendChargeMsg(money, content, desc, type, toUid, authcode);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<ChargeMessageBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(ChargeMessageActivity.this, "发送失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(ChargeMessageBean bean) {
                        Toast.makeText(ChargeMessageActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.putExtra("lid", bean.getData().getSend_id());
                        intent.putExtra("pic", bean.getData().getPic());
                        setResult(Activity.RESULT_OK, intent);
                        finish();
                    }
                });
    }

    public <T extends View> T $(@IdRes int t) {
        return (T) findViewById(t);
    }
}
