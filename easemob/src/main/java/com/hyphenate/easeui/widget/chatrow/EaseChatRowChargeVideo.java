package com.hyphenate.easeui.widget.chatrow;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.easemob.easeui.R;
import com.hickey.network.bean.resposen.ChargeResBean;
import com.hickey.tool.time.TimeUtils;
import com.hickey.tool.widget.UIUtils;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.CustomConstant;
import com.hyphenate.easeui.mvp.presenter.EaseChatRowChargeImagePresenter;
import com.hyphenate.easeui.mvp.presenter.EaseChatRowChargeImagePresenterImpl;
import com.hyphenate.easeui.mvp.view.EaseChatRowChargeImageView;
import com.hyphenate.easeui.ui.ChargeVideoActivity;
import com.hyphenate.exceptions.HyphenateException;

/**
 * Created by jb on 2016/11/24.
 */
public class EaseChatRowChargeVideo extends EaseChatRow implements View.OnClickListener, EaseChatRowChargeImageView {
    private TextView tv_msg, tv_money, tv_video_time;
    private ImageView iv_image;
    private View rl_charge_bg;
    private String lid;
    private boolean look;
    private String money;
    private String mAuthcode;

    private long expireTime;
    private EaseChatRowChargeImagePresenter presenter;

    public EaseChatRowChargeVideo(Context context, String mAuthcode, EMMessage message, int position, BaseAdapter adapter) {
        super(context, message, position, adapter);
        this.mAuthcode = mAuthcode;
    }

    public <T extends View> T $(@IdRes int t) {
        return (T) findViewById(t);
    }

    @Override
    protected void onInflateView() {
        inflater.inflate(message.direct() == EMMessage.Direct.RECEIVE ? R.layout.ease_row_received_charge_video : R.layout.ease_row_sent_charge_video, this);
    }

    @Override
    protected void onFindViewById() {
        tv_msg = $(R.id.tv_msg);
        iv_image = $(R.id.iv_image);
        tv_money = $(R.id.tv_money);
        rl_charge_bg = $(R.id.rl_charge_bg);
        tv_video_time = $(R.id.tv_video_time);

        presenter = new EaseChatRowChargeImagePresenterImpl();
        presenter.attachView(this);
    }

    @Override
    protected void onUpdateView() {

    }

    @Override
    protected void onSetUpView() {
        try {
            Glide.with(this.getContext()).load(message.getStringAttribute("pic")).into(iv_image);
            money = message.getStringAttribute(CustomConstant.ESSAGE_ATTRIBUTE_MONEY, "");
            expireTime = message.getLongAttribute(CustomConstant.ESSAGE_ATTRIBUTE_EXPIRE_TIME, 0);
            long playTime = message.getLongAttribute(CustomConstant.ESSAGE_ATTRIBUTE_PLAY_TIME, 0);
            tv_money.setText("红包视频," + playTime + "秒试看");

            tv_video_time.setText(TimeUtils.stringForTime((int) message.getLongAttribute(CustomConstant.ESSAGE_ATTRIBUTE_VIDEO_DURATION, 0)));
            tv_msg.setText(message.getStringAttribute(CustomConstant.ESSAGE_ATTRIBUTE_MSG));
            look = message.getBooleanAttribute(CustomConstant.ESSAGE_ATTRIBUTE_LOOK);
            lid = message.getStringAttribute(CustomConstant.ESSAGE_ATTRIBUTE_LID);
            if (message.direct() == EMMessage.Direct.RECEIVE) {
                if (look) {
                    rl_charge_bg.setVisibility(View.GONE);
                } else {
                    rl_charge_bg.setVisibility(View.VISIBLE);
                }

            }
            iv_image.setOnClickListener(this);

        } catch (HyphenateException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onBubbleClick() {

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
        UIUtils.showToast(context, msg);
    }

    @Override
    public void setPic(ChargeResBean datas) {
        if (datas.getCont().size() >= 1) {
            Intent intent = new Intent(context, ChargeVideoActivity.class);
            intent.putExtra("path", datas.getCont().get(0));
            intent.putExtra(CustomConstant.ESSAGE_ATTRIBUTE_EMMESSAGE, message);
            intent.putExtra(CustomConstant.ESSAGE_ATTRIBUTE_ACTHCODE, mAuthcode);
            activity.startActivity(intent);
        }

    }

    @Override
    public void onClick(View v) {
        if (expireTime < System.currentTimeMillis() / 1000) {
            transfePageMsg("资源已过期");
            return;
        }
        presenter.getImagePic(lid, mAuthcode);
    }
}
