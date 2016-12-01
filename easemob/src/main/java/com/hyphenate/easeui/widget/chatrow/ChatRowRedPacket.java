package com.hyphenate.easeui.widget.chatrow;

import android.content.Context;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.easemob.easeui.R;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.CustomConstant;
import com.hyphenate.exceptions.HyphenateException;

/**
 * Created by jb on 2016/11/17.
 */

public class ChatRowRedPacket extends EaseChatRow {
    private TextView tv;

    public ChatRowRedPacket(Context context, EMMessage message, int position, BaseAdapter adapter) {
        super(context, message, position, adapter);
    }

    @Override
    protected void onInflateView() {
        inflater.inflate(R.layout.ease_row_received_red_packet, this);
    }

    @Override
    protected void onFindViewById() {
        tv = (TextView) findViewById(R.id.tv);

    }

    @Override
    protected void onUpdateView() {

    }

    @Override
    protected void onSetUpView() {
        try {
            String money = message.getStringAttribute(CustomConstant.ESSAGE_ATTRIBUTE_RED_PACKET_MONEY);
            if (message.direct() == EMMessage.Direct.SEND) {//消息方向，自己发送的
                money = "您发了一个" + money + "元红包";
            } else {
                money = "您收到一个" + money + "元红包";
            }
            tv.setText(money);
        } catch (HyphenateException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onBubbleClick() {

    }
}
