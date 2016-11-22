package com.moonsister.tcjy.banner;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.hickey.network.ImageServerApi;
import com.hickey.network.bean.BannerBean;
import com.hickey.tool.lang.StringUtis;
import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.LogUtils;

/**
 * Created by jb on 2016/9/9.
 */
public class BannerManager {

    public void start(Activity activity, ViewGroup viewGroup) {
        if (viewGroup == null || activity == null) {
            if (LogUtils.getDeBugState()) {
                throw new RuntimeException("ViewGroup or Activity  not is null");
            } else {
                return;
            }
        }
        if (!(viewGroup instanceof FrameLayout)) {
            if (LogUtils.getDeBugState()) {
                throw new RuntimeException("ViewGroup  not instanceof FrameLayout");
            } else {
                return;
            }
        }
        BannerManagerModel model = new BannerManagerModelImpl();
        model.loadBannerData(new BaseIModel.onLoadDateSingleListener<BannerBean>() {
            @Override
            public void onSuccess(BannerBean bean, BaseIModel.DataType dataType) {
                if (bean != null) {
                    String code = bean.getCode();

                    if (StringUtis.equals(code, "1")) {
                        BannerBean.DataBean data = bean.getData();
                        if (data != null)
                            selelctBanner(data, viewGroup, activity);
                        else {
                            viewGroup.setVisibility(View.GONE);
                        }

                    } else {
                        viewGroup.setVisibility(View.GONE);
                    }

                } else {
                    viewGroup.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(String msg) {
                viewGroup.setVisibility(View.GONE);
            }
        });
    }

    public void selelctBanner(BannerBean.DataBean data, ViewGroup viewGroup, Activity activity) {
        if (viewGroup == null || activity == null)
            return;
        if (StringUtis.equals(data.getType(), "-1")) {
            viewGroup.setVisibility(View.GONE);
            return;
        }
        addImageView(viewGroup, data.getImg(), data.getWidth(), data.getHeight());
        viewGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!UserInfoManager.getInstance().isLogin()) {
                    ActivityUtils.startLoginMainActivity();
                    return;
                }
                switch (data.getType()) {
                    // 1认证，2 vip，3 h5的url
                    case "1":
                        ActivityUtils.startCertificationActivity();
                        break;
                    case "2":
                        ActivityUtils.startBuyVipActivity();
                        break;
                    case "3":
                        String param = data.getParam();
                        if (!StringUtis.isEmpty(param)) {
                            ActivityUtils.startH5Activity(param);
                        }
                        break;
                }
            }
        });


    }

    private void addImageView(ViewGroup viewGroup, String url, int width, int height) {
        if (viewGroup == null || StringUtis.isEmpty(url))
            return;

        ImageView imageView = new ImageView(viewGroup.getContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        viewGroup.addView(imageView);
        if (viewGroup instanceof FrameLayout) {
            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) imageView.getLayoutParams();
            params.gravity = Gravity.CENTER_HORIZONTAL;
            params.height = height;
            params.width = width;
            imageView.setLayoutParams(params);
        }
        ImageServerApi.showURLBigImage(imageView, url);
        viewGroup.setVisibility(View.VISIBLE);
    }
}
