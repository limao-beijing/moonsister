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
import com.hickey.network.bean.resposen.ChargeResBean;
import com.hickey.tool.activity.pic.ImagePagerActivity;
import com.hickey.tool.base.BaseDialogFragment;
import com.hickey.tool.constant.EnumConstant;
import com.hickey.tool.widget.UIUtils;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.CustomConstant;
import com.hyphenate.easeui.mvp.presenter.EaseChatRowChargeImagePresenter;
import com.hyphenate.easeui.mvp.presenter.EaseChatRowChargeImagePresenterImpl;
import com.hyphenate.easeui.mvp.view.EaseChatRowChargeImageView;
import com.hyphenate.easeui.ui.ChargeMessageDialog;
import com.hyphenate.exceptions.HyphenateException;

/**
 * Created by jb on 2016/11/22.
 */
public class EaseChatRowChargeImage extends EaseChatRow implements View.OnClickListener, EaseChatRowChargeImageView {

    private TextView tv_msg, tv_money, tv_pic_number;
    private ImageView iv_image;
    private View rl_charge_bg;
    private String lid;
    private boolean look;
    private String money;
    private EaseChatRowChargeImagePresenter presenter;
    private String mActhcode;
    private long expireTime;

    public EaseChatRowChargeImage(Context context, String acthcode, EMMessage message, int position, BaseAdapter adapter) {
        super(context, message, position, adapter);
        this.mActhcode = acthcode;
    }

    @Override
    protected void onInflateView() {
        inflater.inflate(message.direct() == EMMessage.Direct.RECEIVE ? R.layout.ease_row_received_charge_image : R.layout.ease_row_sent_charge_image, this);
    }

    @Override
    protected void onFindViewById() {
        tv_msg = $(R.id.tv_msg);
        iv_image = $(R.id.iv_image);
        tv_money = $(R.id.tv_money);
        rl_charge_bg = $(R.id.rl_charge_bg);
        tv_pic_number = $(R.id.tv_pic_number);
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
            expireTime = message.getLongAttribute(CustomConstant.ESSAGE_ATTRIBUTE_EXPIRE_TIME, 0);
            money = message.getStringAttribute("money");
            tv_money.setText("红包图集,需支付" + money + "元拆开查看");
            int PicNumber = message.getIntAttribute(CustomConstant.ESSAGE_ATTRIBUTE_PIC_NUMBER, 0);
            tv_pic_number.setText(PicNumber + "张");
            tv_msg.setText(message.getStringAttribute("msg"));
            look = message.getBooleanAttribute("look");
            lid = message.getStringAttribute("lid");
            if (message.direct() == EMMessage.Direct.RECEIVE) {
                if (look) {
                    rl_charge_bg.setVisibility(GONE);
                } else {
                    rl_charge_bg.setVisibility(VISIBLE);
                }
            }

        } catch (HyphenateException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        iv_image.setOnClickListener(this);
    }


    @Override
    protected void onBubbleClick() {

    }

    @Override
    public void onClick(View v) {
        if (expireTime < System.currentTimeMillis() / 1000) {
            transfePageMsg("资源已过期");
            return;
        }
        if (!look && message.direct() == EMMessage.Direct.RECEIVE) {
            ChargeMessageDialog dialog = new ChargeMessageDialog();
            Bundle bundle = new Bundle();
            bundle.putString(CustomConstant.ESSAGE_ATTRIBUTE_ACTHCODE, mActhcode);
            bundle.putParcelable(CustomConstant.ESSAGE_ATTRIBUTE_EMMESSAGE, message);
            dialog.setArguments(bundle);
            dialog.setOnCallBack(new BaseDialogFragment.OnCallBack() {
                @Override
                public void onStatus(BaseDialogFragment dialogFragment, EnumConstant.DialogCallBack statusCode) {
                    if (statusCode == EnumConstant.DialogCallBack.CONFIRM) {
                        presenter.getImagePic(lid, mActhcode);
                        dialogFragment.dismissDialogFragment();
                    }
                }
            });
            dialog.showDialogFragment(((AppCompatActivity) context).getSupportFragmentManager());
        } else
            presenter.getImagePic(lid, mActhcode);

    }

//}

    public <T extends View> T $(@IdRes int t) {
        return (T) findViewById(t);
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
        Intent intent = new Intent(context, ImagePagerActivity.class);
        // 图片url,为了演示这里使用常量，一般从数据库中或网络中获取
        intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_URLS, datas.getCont());
        intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_INDEX, 0);
        activity.startActivity(intent);
    }
}
