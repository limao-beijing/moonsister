package com.hyphenate.easeui.widget.chatrow;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.easemob.easeui.R;
import com.hickey.network.AppointmentServerApi;
import com.hickey.network.LogUtils;
import com.hickey.network.bean.ChargeDataBean;
import com.hickey.tool.activity.pic.ImagePagerActivity;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.exceptions.HyphenateException;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jb on 2016/11/22.
 */
public class EaseChatRowChargeImage extends EaseChatRow implements View.OnClickListener {

    private TextView tv_msg, tv_money;
    private ImageView iv_image;
    private View rl_charge_bg;
    private String lid;

    public EaseChatRowChargeImage(Context context, EMMessage message, int position, BaseAdapter adapter) {
        super(context, message, position, adapter);
    }

    @Override
    protected void onInflateView() {
//
        inflater.inflate(message.direct() == EMMessage.Direct.RECEIVE ? R.layout.ease_row_received_charge_image : R.layout.ease_row_sent_charge_image, this);
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
            tv_money.setText("红包图集,需支付"+message.getStringAttribute("money")+"元拆开查看");
            tv_msg.setText(message.getStringAttribute("msg"));
            boolean look = message.getBooleanAttribute("look");
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
                            Intent intent = new Intent(context, ImagePagerActivity.class);
                            // 图片url,为了演示这里使用常量，一般从数据库中或网络中获取
                            intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_URLS, bean.getData());
                            intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_INDEX, position);
                            activity.startActivity(intent);

                        }
                    }
                });
    }

//}

    public <T extends View> T $(@IdRes int t) {
        return (T) findViewById(t);
    }

    /**
     * 自适应宽度加载图片。保持图片的长宽比例不变，通过修改imageView的高度来完全显示图片。
     */
    public static void loadIntoUseFitWidth(Context context, final String imageUrl, final ImageView imageView) {
        Glide.with(context)
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        if (imageView == null) {
                            return false;
                        }
                        if (imageView.getScaleType() != ImageView.ScaleType.FIT_XY) {
                            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        }
                        ViewGroup.LayoutParams params = imageView.getLayoutParams();
                        int vw = imageView.getWidth() - imageView.getPaddingLeft() - imageView.getPaddingRight();
                        float scale = (float) vw / (float) resource.getIntrinsicWidth();
                        int vh = Math.round(resource.getIntrinsicHeight() * scale);
                        params.height = vh + imageView.getPaddingTop() + imageView.getPaddingBottom();
                        imageView.setLayoutParams(params);
                        return false;
                    }
                })
//                .placeholder(errorImageId)
//                .error(errorImageId)
                .into(imageView);
    }
}
