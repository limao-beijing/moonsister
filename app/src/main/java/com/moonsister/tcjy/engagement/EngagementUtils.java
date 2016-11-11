package com.moonsister.tcjy.engagement;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

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
                str = UIUtils.getResources().getString(R.string.more);
                break;
        }
        return str;
    }

    public static List<Integer> popTextStr(int status) {
        ArrayList<Integer> integers = new ArrayList<>();
        switch (status) {
            case 1://1已付款
                integers.add(R.string.engagement_action_apply_canle);

                break;
            case 2://2已接受
                integers.add(R.string.engagement_action_me_apply);
                integers.add(R.string.engagement_action_affirm_enagement);
                break;
            case 3://3已拒绝
                integers.add(R.string.engagement_action_apply_reimburse);
                integers.add(R.string.engagement_action_again);
                break;
            case 4://4申诉中

                break;
            case 5://5申诉成功

                break;
            case 6://6申诉失败
                break;
            case 7://，7已完成
                integers.add(R.string.engagement_action_again);
                break;
        }
        return integers;
    }

    public static String getStatusText(int status, boolean isEngagement) {
        String string = "";
        switch (status) {
            case 1://1已付款
                if (isEngagement)
                    string = UIUtils.getStringRes(R.string.engagement_status_await);

                else
                    string = UIUtils.getStringRes(R.string.engagement_status_await_me);


                break;
            case 2://2已接受
                string = UIUtils.getStringRes(R.string.engagement_status_me_engagement);
                break;
            case 3://3已拒绝
                string = UIUtils.getStringRes(R.string.engagement_status_me_reimburse);
                break;
            case 4://4申诉中

                break;
            case 5://5申诉成功

                break;
            case 6://6申诉失败
                break;
            case 7://，7已完成
                string = UIUtils.getStringRes(R.string.engagement_status_success);
                break;
        }
        return string;
    }
}