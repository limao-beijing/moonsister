package com.hyphenate.easeui.widget.chatrow;

import android.content.Context;
import android.widget.BaseAdapter;

import com.easemob.easeui.R;
import com.hyphenate.chat.EMMessage;

/**
 * Created by jb on 2016/11/17.
 */

public class ChatRowRedPacket extends EaseChatRow {
    public ChatRowRedPacket(Context context, EMMessage message, int position, BaseAdapter adapter) {
        super(context, message, position, adapter);
    }

    @Override
    protected void onInflateView() {
        inflater.inflate(message.direct() == EMMessage.Direct.RECEIVE ?
                R.layout.ease_row_received_red_packet : R.layout.ease_row_received_red_packet, this);
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
