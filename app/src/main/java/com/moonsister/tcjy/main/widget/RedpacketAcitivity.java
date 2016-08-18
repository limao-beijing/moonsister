package com.moonsister.tcjy.main.widget;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.moonsister.tcjy.ImageServerApi;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.main.presenter.RedpacketAcitivityPresenter;
import com.moonsister.tcjy.main.presenter.RedpacketAcitivityPresenterImpl;
import com.moonsister.tcjy.main.view.PlayUserAcitivityView;
import com.moonsister.tcjy.utils.StringUtis;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tcjy.widget.RoundedImageView;
import com.trello.rxlifecycle.ActivityEvent;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jb on 2016/6/24.
 */
public class RedpacketAcitivity extends BaseActivity implements PlayUserAcitivityView, TextWatcher {
    @Bind(R.id.iv_user_icon)
    RoundedImageView ivUserIcon;
    @Bind(R.id.gv_pic_list)
    GridView gvPicList;
    @Bind(R.id.et_input_money)
    EditText editText;
    @Bind(R.id.tv_msg)
    TextView tv_msg;
    private String uid;
    private int type;
    private String[] moneys;
    private int[] flowers = {R.mipmap.flower_1, R.mipmap.flower_2, R.mipmap.flower_3, R.mipmap.flower_4, R.mipmap.flower_5, R.mipmap.flower_6, R.mipmap.flower_7, R.mipmap.flower_8, R.mipmap.flower_9};
    private String[] flowerNames;
    private String[] flowerMoney;

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        boolean b = s.toString().startsWith("0");
        if (b)
            editText.setText("");
    }

    @Override
    public void afterTextChanged(Editable s) {

    }



    public enum RedpacketType {
        TYPE_REDPACKET(1), TYPE_FLOWER(2);
        private final int type;

        private RedpacketType(int type) {
            this.type = type;
        }

        public int getValue() {
            return type;
        }

    }

    private void addData() {
        if (1 == type) {
            moneys = UIUtils.getResources().getStringArray(R.array.moneys);
            tv_msg.setText(UIUtils.getStringRes(R.string.xml_send_red_packet));
        } else if (2 == type) {
            flowerNames = UIUtils.getResources().getStringArray(R.array.flower_name);
            flowerMoney = UIUtils.getResources().getStringArray(R.array.flower_money);
            editText.setCursorVisible(false);
            editText.setCursorVisible(false);
            editText.setFocusable(false);
            editText.setHint(UIUtils.getStringRes(R.string.switch_flower));
            tv_msg.setText(UIUtils.getStringRes(R.string.flower_msg));
        }
    }

    private RedpacketAcitivityPresenter presenter;

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
    protected View setRootContentView() {

        uid = getIntent().getStringExtra("id");
        type = getIntent().getIntExtra("type", -1);

        presenter = new RedpacketAcitivityPresenterImpl();
        presenter.attachView(this);
        return UIUtils.inflateLayout(R.layout.activity_red_packet);
    }

    @Override
    protected void initView() {
        addData();
        editText.addTextChangedListener(this);
        String avater = getIntent().getStringExtra("avater");
        if (!StringUtis.isEmpty(avater))
            ImageServerApi.showURLSamllImage(ivUserIcon, avater);
        gvPicList.setAdapter(new RedpacketAdapter());
    }

    @OnClick({R.id.action_back, R.id.tv_weixin_play, R.id.tv_aliplay_play})
    public void onClick(View view) {
        hideSoftInput();
        presenter.swicthAction(view.getId());

    }

    @Override
    public void swicthAliPlay() {
        if (editText != null) {
            String trim = editText.getText().toString().trim();
            if (StringUtis.isEmpty(trim)) {
                showToast(getString(R.string.input_money) + getResources().getString(R.string.not_empty));
                return;
            }
            if (trim.length() > 8) {
                showToast(getString(R.string.input_money) + getResources().getString(R.string.not_more));
                return;
            }
            if (StringUtis.string2Int(trim) <= 0) {
                showToast(getString(R.string.input_money) + getResources().getString(R.string.not_low_one));
                return;
            }

            presenter.aliPay(type, uid, trim);
        }
    }

    @Override
    public void pageFinish() {
        finish();
    }

    @Override
    public void weixinPay() {
        if (editText != null) {
            String trim = editText.getText().toString().trim();
            if (StringUtis.isEmpty(trim)) {
                showToast(getString(R.string.input_money) + getResources().getString(R.string.not_empty));
                return;
            }
            presenter.weixinPay(type, uid, trim);

            RxBus.with(this)
                    .setEndEvent(ActivityEvent.DESTROY)
                    .setEvent(Events.EventEnum.WEIXIN_PAY_CALLBACK)
                    .onNext(events -> {
                        hideSoftInput();
                        Integer errCode = (Integer) events.message;
                        if (errCode == 0) {
                            transfePageMsg(UIUtils.getStringRes(R.string.pay_success));
                            UIUtils.sendDelayedOneMillis(new Runnable() {
                                @Override
                                public void run() {
                                    finish();
                                }
                            });
                        } else {
                            transfePageMsg(UIUtils.getStringRes(R.string.pay_failure));
                        }
                        hideLoading();

                    })
                    .create();
        }
    }

    private class RedpacketAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 9;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = UIUtils.inflateLayout(R.layout.item_redpacket);
            ViewHolder holder = new ViewHolder(view);
            holder.setData(position);

            return holder.getHolerView();
        }

    }

    class ViewHolder {
        private View view;
        @Bind(R.id.iv)
        ImageView iv;
        @Bind(R.id.tv_money)
        TextView tvMoney;
        @Bind(R.id.tv_name)
        TextView tv_name;

        ViewHolder(View view) {
            this.view = view;
            ButterKnife.bind(this, view);

        }

        public View getHolerView() {
            return view;
        }

        public void setData(int position) {
            switch (type) {
                case 1:
                    ImageServerApi.showResourcesImage(iv, R.mipmap.red_packet);
                    iv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            editText.setText("");
                            String s = moneys[position];
                            editText.getText().insert(editText.getSelectionStart(), s);
                        }
                    });
                    tvMoney.setText(moneys[position]);
                    tv_name.setVisibility(View.GONE);
                    tvMoney.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    ImageServerApi.showResourcesImage(iv, flowers[position]);
                    tvMoney.setVisibility(View.GONE);
                    tv_name.setText(flowerNames[position]);
                    tv_name.setVisibility(View.VISIBLE);
                    tv_name.setTextColor(UIUtils.getResources().getColor(R.color.text_black_color));
                    iv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            editText.setText("");
                            String s = flowerMoney[position];
                            editText.getText().insert(editText.getSelectionStart(), s);
                        }
                    });
                    break;
            }
        }
    }
}
