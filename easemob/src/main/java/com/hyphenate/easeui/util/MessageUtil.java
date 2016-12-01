package com.hyphenate.easeui.util;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.CustomConstant;

/**
 * Created by jb on 2016/11/21.
 */
public class MessageUtil {
    public static EMMessage createRPMessage(FragmentActivity activity, Intent data, String username) {
        String money = data.getStringExtra(CustomConstant.ESSAGE_ATTRIBUTE_RED_PACKET_MONEY);
        EMMessage redMeg = EMMessage.createTxtSendMessage("[红包]", username);
        redMeg.setAttribute(CustomConstant.MESSAGE_TYPE_IS_RED_PACKET_MESSAGE, true);
        redMeg.setAttribute(CustomConstant.ESSAGE_ATTRIBUTE_RED_PACKET_MONEY, money);
        return redMeg;
    }

    public static EMMessage createChargeMessage(FragmentActivity activity, Intent data, String username) {
        EMMessage redMeg = null;
        int type = data.getIntExtra("type", -1);
        if (type == 1) {
            redMeg = EMMessage.createTxtSendMessage("[视频]", username);
            redMeg.setAttribute(CustomConstant.MESSAGE_TYPE_IS_CHARGE_VIDEO_MESSAGE, true);
            redMeg.setAttribute(CustomConstant.ESSAGE_ATTRIBUTE_PLAY_TIME, data.getLongExtra(CustomConstant.ESSAGE_ATTRIBUTE_PLAY_TIME, 0));
            redMeg.setAttribute(CustomConstant.ESSAGE_ATTRIBUTE_VIDEO_DURATION, data.getLongExtra(CustomConstant.ESSAGE_ATTRIBUTE_VIDEO_DURATION, 0));
        } else if (type == 2) {
            redMeg = EMMessage.createTxtSendMessage("[图片]", username);
            redMeg.setAttribute(CustomConstant.MESSAGE_TYPE_IS_CHARGE_IMAGE_MESSAGE, true);
            redMeg.setAttribute(CustomConstant.ESSAGE_ATTRIBUTE_PIC_NUMBER, data.getIntExtra(CustomConstant.ESSAGE_ATTRIBUTE_PIC_NUMBER, 0));
        }
        if (redMeg == null)
            return null;
        redMeg.setAttribute(CustomConstant.ESSAGE_ATTRIBUTE_EXPIRE_TIME, data.getLongExtra(CustomConstant.ESSAGE_ATTRIBUTE_EXPIRE_TIME, 0));
        redMeg.setAttribute(CustomConstant.ESSAGE_ATTRIBUTE_PIC, data.getStringExtra(CustomConstant.ESSAGE_ATTRIBUTE_PIC));
        redMeg.setAttribute(CustomConstant.ESSAGE_ATTRIBUTE_LID, data.getStringExtra(CustomConstant.ESSAGE_ATTRIBUTE_LID));
        redMeg.setAttribute(CustomConstant.ESSAGE_ATTRIBUTE_MSG, data.getStringExtra(CustomConstant.ESSAGE_ATTRIBUTE_MSG));
        redMeg.setAttribute(CustomConstant.ESSAGE_ATTRIBUTE_MONEY, data.getStringExtra(CustomConstant.ESSAGE_ATTRIBUTE_MONEY));
        redMeg.setAttribute(CustomConstant.ESSAGE_ATTRIBUTE_LOOK, false);
        return redMeg;
    }

}
