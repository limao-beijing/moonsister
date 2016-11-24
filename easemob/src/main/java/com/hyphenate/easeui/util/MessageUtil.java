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
        EMMessage redMeg = null;
        int type = data.getIntExtra("type", -1);
        if (type == 1) {
            redMeg = EMMessage.createTxtSendMessage("[收费视频]", username);
            redMeg.setAttribute(CustomConstant.MESSAGE_TYPE_IS_CHARGE_VIDEO_MESSAGE, true);
        } else if (type == 2) {
            redMeg = EMMessage.createTxtSendMessage("[收费图片]", username);
            redMeg.setAttribute(CustomConstant.MESSAGE_TYPE_IS_CHARGE_IMAGE_MESSAGE, true);
        }
        if (redMeg == null)
            return null;

        redMeg.setAttribute("pic", data.getStringExtra("pic"));
        redMeg.setAttribute("lid", data.getStringExtra("lid"));
        redMeg.setAttribute("msg", data.getStringExtra("msg"));
        redMeg.setAttribute("money", data.getStringExtra("money"));
        redMeg.setAttribute("look", false);
//        redMeg.setAttribute("datas", data.getStringExtra("datas"));
        return redMeg;
    }

//    public static EMMessage createChargeImageMessage(FragmentActivity activity, Intent data, String username) {
//        EMMessage redMeg = EMMessage.createTxtSendMessage("收费图片 : ", username);
//        redMeg.setAttribute(CustomConstant.MESSAGE_TYPE_IS_CHARGE_IMAGE_MESSAGE, true);
//        redMeg.setAttribute("pic", data.getStringExtra("pic"));
//        redMeg.setAttribute("lid", data.getStringExtra("lid"));
//        redMeg.setAttribute("msg", data.getStringExtra("msg"));
//        redMeg.setAttribute("money", data.getStringExtra("money"));
//        redMeg.setAttribute("look", false);
//        redMeg.setAttribute("datas", data.getStringExtra("datas"));
//        return redMeg;
//    }
//
//    public static EMMessage createChargeVideoMessage(FragmentActivity activity, Intent data, String username) {
//        return null;
//    }
}
