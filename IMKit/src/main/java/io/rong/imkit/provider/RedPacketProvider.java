package io.rong.imkit.provider;

import android.content.Context;
import android.graphics.PixelXorXfermode;
import android.graphics.drawable.Drawable;
import android.view.View;

import io.rong.imkit.R;
import io.rong.imkit.RongContext;
import io.rong.imkit.userInfoCache.RongUserInfoManager;
import io.rong.imkit.widget.provider.InputProvider;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;

/**
 * Created by jb on 2016/7/5.
 */
public class RedPacketProvider extends InputProvider.ExtendProvider {
    public RedPacketProvider(RongContext context) {
        super(context);
    }

    @Override
    public Drawable obtainPluginDrawable(Context context) {
        return context.getResources().getDrawable(R.drawable.red_packet);
    }

    @Override
    public CharSequence obtainPluginTitle(Context context) {
        return context.getResources().getString(R.string.red_packet);
    }

    @Override
    public void onPluginClick(View view) {

        Conversation currentConversation = this.getCurrentConversation();
        String targetId = currentConversation.getTargetId();
        UserInfo userInfo = RongUserInfoManager.getInstance().getUserInfo(targetId);
        if (listenter != null)
            listenter.onPluginClick(targetId, userInfo == null ? "" : userInfo.getName(), userInfo == null ? "" : userInfo.getPortraitUri().toString());
    }

    public onPluginClickListenter listenter;

    public void setOnPluginClickListenter(onPluginClickListenter listenter) {
        this.listenter = listenter;
    }

    public interface onPluginClickListenter {

        void onPluginClick(String userId, String name, String path);
    }
}
