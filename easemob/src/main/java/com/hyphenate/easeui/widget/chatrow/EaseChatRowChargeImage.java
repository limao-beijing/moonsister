package com.hyphenate.easeui.widget.chatrow;

import android.content.Context;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.easemob.easeui.R;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.exceptions.HyphenateException;

/**
 * Created by jb on 2016/11/22.
 */
public class EaseChatRowChargeImage extends EaseChatRow {
    public EaseChatRowChargeImage(Context context, EMMessage message, int position, BaseAdapter adapter) {
        super(context, message, position, adapter);
    }

    @Override
    protected void onInflateView() {
        inflater.inflate(R.layout.ease_row_received_charge_image, this);
    }

    @Override
    protected void onFindViewById() {
        ImageView tv_chatcontent = (ImageView) findViewById(R.id.tv_chatcontent);

        try {
            Glide.with(this.getContext()).load(message.getStringAttribute("pic")).into(tv_chatcontent);
        } catch (HyphenateException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onUpdateView() {

    }

    @Override
    protected void onSetUpView() {

    }

    @Override
    protected void onBubbleClick() {

    }
}
