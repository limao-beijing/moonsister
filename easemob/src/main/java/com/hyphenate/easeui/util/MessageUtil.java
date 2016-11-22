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
        String money = data.getStringExtra(CustomConstant.ESSAGE_ATTRIBUTE_MONEY);
        EMMessage redMeg = EMMessage.createTxtSendMessage("红包 : " + money, username);
        redMeg.setAttribute(CustomConstant.MESSAGE_TYPE_IS_RED_PACKET_MESSAGE, true);
        redMeg.setAttribute(CustomConstant.ESSAGE_ATTRIBUTE_MONEY, money);
        return redMeg;
    }

    public static EMMessage createChargeMessage(FragmentActivity activity, Intent data, String username) {
        EMMessage redMeg = EMMessage.createTxtSendMessage("收费图片 : ", username);
        redMeg.setAttribute(CustomConstant.MESSAGE_TYPE_IS_CHARGE_IMAGE_MESSAGE, true);
        return redMeg;
    }
}
