package com.hyphenate.easeui.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.support.annotation.IdRes;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.easemob.easeui.R;
import com.hickey.network.bean.resposen.ChargeInitBean;
import com.hickey.network.bean.resposen.ChargeMessageBean;
import com.hickey.tool.activity.pic.PictureSelectorActivity;
import com.hickey.tool.activity.video.ImageGridActivity;
import com.hickey.tool.lang.StringUtis;
import com.hickey.tool.widget.NoScrollGridView;
import com.hyphenate.easeui.CustomConstant;
import com.hyphenate.easeui.mvp.presenter.ChargeMessageActivityPresenter;
import com.hyphenate.easeui.mvp.presenter.ChargeMessageActivityPresenterImpl;
import com.hyphenate.easeui.mvp.view.ChargeMessageActivityView;
import com.hyphenate.util.PathUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by jb on 2016/11/22.
 */

public class ChargeMessageActivity extends com.hickey.tool.base.BaseActivity implements View.OnClickListener, ChargeMessageActivityView {


    private static final int REQUEST_CODE_SELECT_VIDEO = 1;

    private static final int REQUEST_CODE_SELECT_IMAGSES = 2;

    public static final int TYPE_VIDEO = 1;
    public static final int TYPE_IMAGE = 2;
    private ImageView iv_voice;
    private View ll_select_layout;
    private NoScrollGridView gv;
    private EditText et_money;
    private EditText et_msg;
    private TextView tv_show_fengcheng;
    //image
    private List<String> pics;
    //发送类型
    private int type;
    //video
    private String videoPath;
    private long duration;
    private String videoPic;
    //user
    private String toUid;
    private String authcode;
    private ChargeMessageActivityPresenter presenter;
    private int maxMoney;
    private int minMoney;
    private CheckBox checkBox;

    @Override
    protected View setRootContentView() {
        return View.inflate(getApplicationContext(), R.layout.ease_activity_charge_message, null);
    }

    @Override
    protected void initView() {
        iv_voice = $(R.id.iv_voice);
        gv = $(R.id.gv);
        ll_select_layout = $(R.id.ll_select_layout);
        et_money = $(R.id.et_money);
        et_msg = $(R.id.et_msg);
        tv_show_fengcheng = $(R.id.tv_show_fengcheng);
        checkBox = $(R.id.cb_send);
        Intent intent = getIntent();
        toUid = intent.getStringExtra("id");
        authcode = intent.getStringExtra("authcode");
        boolean perimssion = intent.getBooleanExtra("havePerimssion", false);
        checkBox.setVisibility(perimssion ? View.VISIBLE : View.GONE);
        presenter = new ChargeMessageActivityPresenterImpl();
        presenter.attachView(this);
        presenter.loadInitData(authcode);
        initListener();


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
            int i1 = StringUtis.string2Int(money);
            if (i1 < minMoney || i1 > maxMoney) {
                transfePageMsg("输入的金额不正确");
                return;
            }
            if (type == 0 || TextUtils.isEmpty(msg) || TextUtils.isEmpty(money)) {
                Toast.makeText(this, "信息不完整", Toast.LENGTH_SHORT).show();
                return;
            }
            if (type == TYPE_VIDEO) {
                if (TextUtils.isEmpty(videoPath)) {
                    Toast.makeText(this, "信息不完整", Toast.LENGTH_SHORT).show();
                    return;
                }
                ArrayList<String> vs = new ArrayList<>();
                vs.add(videoPath);
                vs.add(videoPic);
                presenter.submitData(checkBox.isChecked(), money, vs, msg, type, toUid, duration, authcode);


            } else if (type == TYPE_IMAGE) {
                if (pics == null || pics.size() == 0) {
                    Toast.makeText(this, "信息不完整", Toast.LENGTH_SHORT).show();
                    return;
                }
                presenter.submitData(checkBox.isChecked(), money, pics, msg, type, toUid, duration, authcode);
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
                        duration = data.getLongExtra("dur", 0);
                        videoPath = data.getStringExtra("path");
                        File file = new File(PathUtil.getInstance().getImagePath(), "thvideo" + System.currentTimeMillis());
                        try {
                            FileOutputStream fos = new FileOutputStream(file);
                            Bitmap ThumbBitmap = ThumbnailUtils.createVideoThumbnail(videoPath, 3);
                            ThumbBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                            fos.close();
                            Glide.with(this).load(file).into(iv_voice);
                            videoPic = file.getAbsolutePath();
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

    @Override
    public void setData(ChargeMessageBean bean) {
        if (bean == null)
            return;
        String msg = et_msg.getText().toString().trim();
        String money = et_money.getText().toString().trim();
        Intent intent = new Intent();
        intent.putExtra("type", type);
        intent.putExtra(CustomConstant.ESSAGE_ATTRIBUTE_LID, bean.getSource_id());
        intent.putExtra(CustomConstant.ESSAGE_ATTRIBUTE_PIC, bean.getPic());
        intent.putExtra(CustomConstant.ESSAGE_ATTRIBUTE_MSG, msg);
        intent.putExtra(CustomConstant.ESSAGE_ATTRIBUTE_MONEY, money);
        intent.putExtra(CustomConstant.ESSAGE_ATTRIBUTE_PIC_NUMBER, pics == null ? 0 : pics.size());
        intent.putExtra(CustomConstant.ESSAGE_ATTRIBUTE_VIDEO_DURATION, duration);
        intent.putExtra(CustomConstant.ESSAGE_ATTRIBUTE_EXPIRE_TIME, (System.currentTimeMillis() / 1000) + (bean.getExpire_time()));
        intent.putExtra(CustomConstant.ESSAGE_ATTRIBUTE_PLAY_TIME, bean.getExpire_sc());
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    @Override
    public void setInitData(ChargeInitBean model) {
        // android:hint=""
        maxMoney = model.getChat_money_max();
        minMoney = model.getChat_money_min();
        et_money.setHint("请输入" + minMoney + "-" + maxMoney + "元范围");
        tv_show_fengcheng.setText(model.getFencheng_msg());
    }

    @Override
    protected String initTitleName() {
        return "收费视频/图片";
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

    public <T extends View> T $(@IdRes int t) {
        return (T) findViewById(t);
    }
}
