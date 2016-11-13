package com.moonsister.tcjy.engagement;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tool.lang.StringUtis;

import java.util.ArrayList;
import java.util.List;

import static com.moonsister.tcjy.utils.UIUtils.getStringRes;

/**
 * Created by jb on 2016/11/12.
 */

public class EngagementUtils {
    public static String getEngagementStr(int status) {
        String str = "";
        switch (status) {

            case 1:
                str = UIUtils.getResources().getString(R.string.engagment_meal);
                break;
            case 2:
                str = UIUtils.getResources().getString(R.string.asleep);
                break;
            case 3:
                str = UIUtils.getResources().getString(R.string.movie);
                break;
            case 4:
                str = UIUtils.getResources().getString(R.string.coffee);
                break;
            case 5:
                str = UIUtils.getResources().getString(R.string.shop);
                break;
            case 6:
                str = UIUtils.getResources().getString(R.string.travel);
                break;
            case 7:
                str = UIUtils.getResources().getString(R.string.other);
                break;
            case 8:
                str = UIUtils.getResources().getString(R.string.more);
                break;
        }
        return str;
    }

    public static List<Integer> popTextStr(int status, int failurecode, String failureMsg, boolean isEngagement) {
        ArrayList<Integer> integers = new ArrayList<>();
        switch (status) {
            case 1://1等待应答
                if (isEngagement)
                    integers.add(R.string.engagement_action_apply_canle);
                else {
                    integers.add(R.string.engagement_action_me_engagement);
                    integers.add(R.string.engagement_action_reimburse_ta);
                }

                break;
            case 2://2已接受
                if (isEngagement) {
                    integers.add(R.string.engagement_action_me_apply);
                    integers.add(R.string.engagement_action_affirm_enagement);
                }

                break;
            case 3://3 申诉中

                break;
            case 4://4已成功

                break;
            case 5://5已失败
                if (isEngagement) {
                    switch (failurecode) {
                        case 5001:
                            integers.add(R.string.engagement_action_apply_reimburse);
                            integers.add(R.string.engagement_action_again);
                            break;
                        case 5003:
                            integers.add(R.string.engagement_action_apply_reimburse);
                            break;
                    }

                }

                break;
            case 6://6申诉失败
                break;
            case 7://，7已完成
                integers.add(R.string.engagement_action_again);
                break;
        }
        return integers;
    }

    public static String getStatusText(int status, int failurecode, String failureMsg, boolean isEngagement) {
        String string = "";
        switch (status) {
            case 1://1等待应答
                if (isEngagement)
                    string = getStringRes(R.string.engagement_status_await);
                else
                    string = getStringRes(R.string.engagement_status_await_me);
                break;
            case 2://2已接受
                if (isEngagement)
                    string = getStringRes(R.string.engagement_status_peer_engagement);
                else
                    string = getStringRes(R.string.engagement_status_me_engagement);
                break;
            case 3://3 申诉中
                string = getStringRes(R.string.engagement_status_me_reimburse);
                break;
            case 4://4已成功
                string = getStringRes(R.string.engagement_status_success);
                break;
            case 5://5已失败
                string = failureMsg;
                break;

        }
        return string;
    }

    public static int getClickCode(String tag) {
        //操作类型 1取消订单，2拒绝，3接受，4设置成功，5失败后申请退款
        int code = 0;
        if (StringUtis.equals(tag, getStringRes(R.string.engagement_action_apply_canle))) {
            code = 1;
        } else if (StringUtis.equals(tag, getStringRes(R.string.engagement_action_reimburse_ta))) {
            code = 2;
        } else if (StringUtis.equals(tag, getStringRes(R.string.engagement_action_me_engagement))) {
            code = 3;
        } else if (StringUtis.equals(tag, getStringRes(R.string.engagement_action_affirm_enagement))) {
            code = 4;
        } else if (StringUtis.equals(tag, getStringRes(R.string.engagement_action_apply_reimburse))) {
            code = 5;
        }
        return code;
    }


}