package com.moonsister.tcjy.center.widget;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hickey.network.ImageServerApi;
import com.hickey.tool.constant.EnumConstant;
import com.hickey.tool.file.PrefUtils;
import com.hickey.tool.lang.StringUtis;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.center.presenter.DynamicPublishPresenter;
import com.moonsister.tcjy.center.presenter.DynamicPublishPresenterImpl;
import com.moonsister.tcjy.center.view.DefaultDynamicView;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.manager.GaodeManager;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.ConfigUtils;
import com.moonsister.tcjy.utils.LogUtils;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tcjy.utils.VideoUtils;
import com.moonsister.tcjy.widget.MySwitch;
import com.hickey.tool.widget.NoScrollGridView;
import com.moonsister.tcjy.widget.RoundedImageView;
import com.trello.rxlifecycle.ActivityEvent;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by jb on 2016/6/23.
 */
public class DefaultDynamicSendActivity extends BaseActivity implements DefaultDynamicView {
    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.et_content)
    EditText etContent;
    @Bind(R.id.gv_pic_list)
    NoScrollGridView gvPicList;
    @Bind(R.id.tv_address)
    TextView tvAddress;
    @Bind(R.id.ms_sm_getmessage)
    MySwitch ivSwitch;
    @Bind(R.id.video_back)
    ImageView video_back;
    private DynamicPublishPresenter presenter;
    private ShowPicAdapter showPicAdapter;
    private List datas;
    private EnumConstant.DynamicType dynamicType;
    private String videoPath;
    private String videoBackPath;

    @Override
    protected View setRootContentView() {


//       type 动态类型 1红包图集，2普通图文，3普通小视频动态
        String type = getIntent().getStringExtra("type");
        switch (type) {
            case "3":
                dynamicType = EnumConstant.DynamicType.FREE_VIDEO;
                ArrayList<String> videos = getIntent().getStringArrayListExtra("data");
                if (videos != null && videos.size() == 1) {
                    videoPath = videos.get(0);

                    videoBackPath = VideoUtils.getInstance().getVideoThumbnail(videoPath);
                }
                if (datas == null)
                    datas = new ArrayList();
                datas.add(videoPath);
                datas.add(videoBackPath);
                break;
            case "2":

                dynamicType = EnumConstant.DynamicType.FREE_PIC;
                if (datas == null)
                    datas = new ArrayList();
                datas.add(R.mipmap.add_dynamic_pic);
                break;
            case "1":
                dynamicType = EnumConstant.DynamicType.CHARGE_PIC;
                ArrayList<String> pics = getIntent().getStringArrayListExtra("data");

                if (datas == null)
                    datas = new ArrayList();
                addData(pics);
                if (pics.size() < 9)
                    datas.add(R.mipmap.add_dynamic_pic);
                break;
        }
        presenter = new DynamicPublishPresenterImpl();
        presenter.attachView(this);
        setRxBus();
        return UIUtils.inflateLayout(R.layout.activity_default_dynamic_send);
    }

    private void addData(ArrayList ls) {
        if (datas == null)
            datas = new ArrayList();

        for (int i = 0; i < ls.size(); i++) {
            datas.add(0, ls.get(i));
        }
    }


    @Override
    protected void initView() {

        if (dynamicType == EnumConstant.DynamicType.FREE_VIDEO) {

            gvPicList.setVisibility(View.GONE);
            ViewGroup.LayoutParams params = video_back.getLayoutParams();
            params.height = 400;
            video_back.setLayoutParams(params);
            ImageServerApi.showURLBigImage(video_back, videoBackPath);
        } else {
            showPicAdapter = new ShowPicAdapter(datas);
            gvPicList.setAdapter(showPicAdapter);
        }

        ivSwitch.setOnSelectChangeListener(new MySwitch.OnSelectChangeListener() {
            @Override
            public void onCheckedChanged(MySwitch mySwitch, boolean isOpen) {
                if (isOpen) {
                    tvAddress.setVisibility(View.VISIBLE);
                } else {
                    tvAddress.setVisibility(View.INVISIBLE);
                }
            }
        });
        ivSwitch.setOpen(true);
        if (ivSwitch.isShown()) {
            String location = PrefUtils.getString(ConfigUtils.getInstance().getApplicationContext(), GaodeManager.class.getName(), "");
            if (StringUtis.isEmpty(location)) {
                tvAddress.setText(UIUtils.getStringRes(R.string.locationing));
            } else {
                try {
                    JSONObject jsonObject = new JSONObject(location);
                    String province = jsonObject.getString("province");
                    String city = jsonObject.getString("city");
                    tvAddress.setText(province + "." + city);
                } catch (JSONException e) {
                    e.printStackTrace();
                    tvAddress.setText(UIUtils.getStringRes(R.string.locationing));
                }

            }
        }
    }


    @OnClick({R.id.tv_send_submit, R.id.tv_address, R.id.ms_sm_getmessage})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_send_submit:
                if (datas == null || datas.size() == 0) {
                    showToast(getResources().getString(R.string.photo) + getResources().getString(R.string.not_empty));
                    break;
                }

                String content = etContent.getText().toString().trim();
                if (StringUtis.isEmpty(content)) {
                    showToast(getResources().getString(R.string.not_empty));
                    break;
                }
                if (content.length() > 150) {
                    showToast(UIUtils.getStringRes(R.string.text_numer_more_150));
                    return;
                }
//                String address = tvAddress.getText().toString().trim();
                if (dynamicType != EnumConstant.DynamicType.FREE_VIDEO) {
                    if (datas.size() < 9) {
                        datas.remove(datas.size() - 1);
                    }
                }
                if (datas.size() == 9) {
                    Object o = datas.get(datas.size() - 1);
                    if (o instanceof Integer) {
                        datas.remove(datas.size() - 1);
                    }
                }
                String location = "";
                if (ivSwitch.isOpen()) {
                    location = PrefUtils.getString(ConfigUtils.getInstance().getApplicationContext(), GaodeManager.class.getName(), "");
                }
                presenter.sendDynamic(dynamicType, content, datas, "", location);
                break;
            case R.id.tv_address:
                break;
            case R.id.ms_sm_getmessage:
                break;
        }
    }

    private void setRxBus() {
        RxBus.with(this).setEndEvent(ActivityEvent.DESTROY)
                .setEvent(Events.EventEnum.GET_PHOTO_LIST)
                .onNext(events ->
                        {

                            if (events != null) {
                                Object message = events.getMessage();
                                if (message != null && message instanceof List) {
                                    ArrayList pics = (ArrayList) message;
                                    LogUtils.e(DefaultDynamicSendActivity.class, "pics : " + pics.toString());
                                    if (datas == null) {
                                        datas = new ArrayList<String>();
                                        datas.clear();
                                    }
                                    if ((datas.size() + pics.size()) > 10) {
                                        showToast(UIUtils.getStringRes(R.string.pic_more_nine));

                                    } else {
                                        if ((datas.size() + pics.size()) == 10) {
                                            datas.remove(datas.size() - 1);
                                        }
                                        addData(pics);
//                                    datas.addAll(pics);
                                        showPicAdapter = new ShowPicAdapter(datas);
                                        gvPicList.setAdapter(showPicAdapter);
                                    }
                                }
                            }
                        }
                ).create();
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
    public void finishPage() {
        UIUtils.sendDelayedOneMillis(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        });

    }

    private class ShowPicAdapter extends BaseAdapter {
        private List adatperdatas;

        public ShowPicAdapter(List datas) {
            this.adatperdatas = datas;
        }

        @Override
        public int getCount() {
            return adatperdatas == null ? 0 : adatperdatas.size();
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
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = UIUtils.inflateLayout(R.layout.item_pics);
            RoundedImageView pic = (RoundedImageView) view.findViewById(R.id.iv_pic);
            Object o = datas.get(position);
            if (o != null) {
                if (o instanceof String) {
                    ImageServerApi.showURLSamllImage(pic, (String) o);

                } else if (o instanceof Integer) {
                    ImageServerApi.showResourcesImage(pic, (Integer) o);
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ActivityUtils.startPhonePicActivity();

                        }
                    });
                }
            }
            return view;
        }
    }
}
