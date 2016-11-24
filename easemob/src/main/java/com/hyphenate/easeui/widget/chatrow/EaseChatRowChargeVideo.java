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
import com.hickey.network.AppointmentServerApi;
import com.hickey.network.LogUtils;
import com.hickey.network.bean.ChargeDataBean;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.ui.PlayDirectlyActivity;
import com.hyphenate.exceptions.HyphenateException;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jb on 2016/11/24.
 */
public class EaseChatRowChargeVideo extends EaseChatRow implements View.OnClickListener {
    private TextView tv_msg, tv_money;
    private ImageView iv_image;
    private View rl_charge_bg;
    private String lid;

    public EaseChatRowChargeVideo(Context context, EMMessage message, int position, BaseAdapter adapter) {
        super(context, message, position, adapter);
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
    }

    @Override
    protected void onUpdateView() {

    }

    @Override
    protected void onSetUpView() {
        try {
            Glide.with(this.getContext()).load(message.getStringAttribute("pic")).into(iv_image);
            tv_money.setText("红包视频,10秒试看");
            tv_msg.setText(message.getStringAttribute("msg"));
            lid = message.getStringAttribute("lid");
            boolean look = message.getBooleanAttribute("look");
            if (message.direct() == EMMessage.Direct.RECEIVE) {
                if (look) {
                    rl_charge_bg.setVisibility(GONE);
                } else {
                    rl_charge_bg.setVisibility(VISIBLE);
                }
            }

        } catch (HyphenateException e) {
            e.printStackTrace();
        }
        iv_image.setOnClickListener(this);
    }

    @Override
    protected void onBubbleClick() {

    }

    @Override
    public void onClick(View v) {

        Observable<ChargeDataBean> observable = AppointmentServerApi.getAppAPI().getChargeMessage(lid);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<ChargeDataBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        LogUtils.e(this, e.getMessage());
                    }

                    @Override
                    public void onNext(ChargeDataBean bean) {
                        if (bean != null && bean.getData() != null && bean.getData().size() != 0) {
                            String localPath = bean.getData().get(0);
                            Intent intent = new Intent(context, PlayDirectlyActivity.class);
                            intent.putExtra("path", localPath);
                            activity.startActivity(intent);
                        }
                    }
                });
    }
}
