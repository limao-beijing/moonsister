package com.moonsister.tcjy.manager;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.ServerApi;
import com.moonsister.tcjy.bean.UserInfoBannerBean;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.ObservableUtils;
import com.moonsister.tcjy.utils.StringUtis;
import com.moonsister.tcjy.utils.UIUtils;

import rx.Observable;

/**
 * Created by jb on 2016/9/14.
 */
public class UserInfoBannerManager {
    private volatile static UserInfoBannerManager instance;


    public static UserInfoBannerManager getInstance() {
        if (instance == null) {
            synchronized (UserInfoManager.class) {
                if (instance == null) {
                    instance = new UserInfoBannerManager();
                }

            }
        }
        return instance;
    }

    public void show(Activity activity, ViewGroup viewGroup) {
        if (activity == null || viewGroup == null)
            return;
        Observable<UserInfoBannerBean> observable = ServerApi.getAppAPI().getUserInfoStatus(UserInfoManager.getInstance().getAuthcode(), AppConstant.CHANNEL_ID);
        ObservableUtils.parser(observable, new ObservableUtils.Callback<UserInfoBannerBean>() {
            @Override
            public void onSuccess(UserInfoBannerBean bean) {
                if (bean == null) {
                    viewGroup.setVisibility(View.GONE);
                } else {
                    //状态 100代表用户手机号未绑定，200代表用户资料填写不完整
                    if (StringUtis.equals(bean.getCode(), "1")) {
                        String status = bean.getData().getStatus();
                        if (StringUtis.equals(status, "100")) {
                            addBanner(viewGroup, bean.getData().getMsg(), status);
                        } else if (StringUtis.equals(status, "200")) {
                            addBanner(viewGroup, bean.getData().getMsg(), status);
                        } else
                            viewGroup.setVisibility(View.GONE);

                    } else {

                        viewGroup.setVisibility(View.GONE);
                    }

                }
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }

    private void addBanner(ViewGroup viewGroup, String content, String status) {
        View view = UIUtils.inflateLayout(R.layout.banner_userinfo);
        TextView tv_content = (TextView) view.findViewById(R.id.tv_content);
        tv_content.setText(content);
        view.findViewById(R.id.iv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setVisibility(View.GONE);
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringUtis.equals(status, "100")) {
                    ActivityUtils.startRegActivity();
                } else if (StringUtis.equals(status, "200")) {
                    ActivityUtils.startPersonalReviseActivity();
                }
                viewGroup.setVisibility(View.GONE);
            }
        });
        viewGroup.addView(view);
    }


}
