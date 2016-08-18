package io.rong.imkit.provider;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.rong.imkit.R;
import io.rong.imkit.model.ProviderTag;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.widget.provider.IContainerItemProvider;
import io.rong.imlib.model.Message;

/**
 * Created by jb on 2016/7/5.
 */
@ProviderTag(showProgress = false, showPortrait = false, centerInHorizontal = true, messageContent = RedPacketMessage.class)
public class RedPacketMessageItemProvider extends IContainerItemProvider.MessageProvider<RedPacketMessage> {
    @Override
    public void bindView(View view, int i, RedPacketMessage redPacketMessage, UIMessage uiMessage) {
        ViewHolder holder = (ViewHolder) view.getTag();
        String money = redPacketMessage.getContent();
        if (uiMessage.getMessageDirection() == Message.MessageDirection.SEND) {//消息方向，自己发送的
            money = "您发了一个" + money + "元红包";
        } else {
            money = "您收到一个" + money + "元红包";
        }
        holder.message.setText(money);
    }

    @Override
    public Spannable getContentSummary(RedPacketMessage redPacketMessage) {
        return new SpannableString("[ 红包 ]");
    }

    @Override
    public void onItemClick(View view, int i, RedPacketMessage redPacketMessage, UIMessage uiMessage) {

    }

    @Override
    public void onItemLongClick(View view, int i, RedPacketMessage redPacketMessage, UIMessage uiMessage) {

    }

    @Override
    public View newView(Context context, ViewGroup viewGroup) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_customize_message, null);
        ViewHolder holder = new ViewHolder();
        holder.message = (TextView) view.findViewById(R.id.tv);
        view.setTag(holder);
        return view;
    }

    static class ViewHolder {
        TextView message;
    }
}
