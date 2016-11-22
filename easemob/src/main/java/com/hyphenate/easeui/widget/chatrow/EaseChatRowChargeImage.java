package com.hyphenate.easeui.widget.chatrow;

import android.content.Context;
import android.widget.BaseAdapter;

import com.easemob.easeui.R;
import com.hyphenate.chat.EMMessage;

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
