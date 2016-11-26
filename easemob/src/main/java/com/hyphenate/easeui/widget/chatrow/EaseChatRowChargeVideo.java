package com.hyphenate.easeui.widget.chatrow;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.easemob.easeui.R;
import com.hickey.tool.base.BaseDialogFragment;
import com.hickey.tool.constant.EnumConstant;
import com.hickey.tool.widget.UIUtils;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.CustomConstant;
import com.hyphenate.easeui.mvp.presenter.EaseChatRowChargeImagePresenter;
import com.hyphenate.easeui.mvp.presenter.EaseChatRowChargeImagePresenterImpl;
import com.hyphenate.easeui.mvp.view.EaseChatRowChargeImageView;
import com.hyphenate.easeui.ui.ChargeMessageDialog;
import com.hyphenate.easeui.ui.PlayDirectlyActivity;
import com.hyphenate.exceptions.HyphenateException;

import java.util.ArrayList;

/**
 * Created by jb on 2016/11/24.
 */
public class EaseChatRowChargeVideo extends EaseChatRow implements View.OnClickListener, EaseChatRowChargeImageView {
    private TextView tv_msg, tv_money;
    private ImageView iv_image;
    private View rl_charge_bg;
    private String lid;
    private boolean look;
    private String money;
    private String mAuthcode;
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
            money = message.getStringAttribute(CustomConstant.ESSAGE_ATTRIBUTE_MONEY);
            tv_money.setText("红包视频,10秒试看");
            tv_msg.setText(message.getStringAttribute(CustomConstant.ESSAGE_ATTRIBUTE_MSG));
            look = message.getBooleanAttribute(CustomConstant.ESSAGE_ATTRIBUTE_LOOK);
            lid = message.getStringAttribute(CustomConstant.ESSAGE_ATTRIBUTE_LID);
            if (message.direct() == EMMessage.Direct.RECEIVE) {
                if (look) {
                    rl_charge_bg.setVisibility(GONE);
                } else {
                    rl_charge_bg.setVisibility(VISIBLE);
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
    public void setPic(ArrayList<String> datas) {
        if (datas.size() >= 1) {
            Intent intent = new Intent(context, PlayDirectlyActivity.class);
            intent.putExtra("path", datas.get(0));
            activity.startActivity(intent);
        }

    }

    @Override
    public void onClick(View v) {
        if (!look && message.direct() == EMMessage.Direct.RECEIVE) {
            ChargeMessageDialog dialog = new ChargeMessageDialog();
            Bundle bundle = new Bundle();
            bundle.putString(CustomConstant.ESSAGE_ATTRIBUTE_MONEY, money);
            bundle.putString(CustomConstant.ESSAGE_ATTRIBUTE_ACTHCODE, mAuthcode);
            dialog.setArguments(bundle);
            dialog.setOnCallBack(new BaseDialogFragment.OnCallBack() {
                @Override
                public void onStatus(BaseDialogFragment dialogFragment, EnumConstant.DialogCallBack statusCode) {
                    if (statusCode == EnumConstant.DialogCallBack.CONFIRM) {
                        presenter.getImagePic(lid, mAuthcode);
                        dialogFragment.dismissDialogFragment();
                    }
                }
            });
            dialog.showDialogFragment(((AppCompatActivity) context).getSupportFragmentManager());
        } else
            presenter.getImagePic(lid, mAuthcode);
    }
}
