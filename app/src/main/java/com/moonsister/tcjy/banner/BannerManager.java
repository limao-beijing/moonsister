package com.moonsister.tcjy.banner;

import android.app.Activity;
import android.view.ViewGroup;

/**
 * Created by jb on 2016/9/9.
 */
public class BannerManager {
    /**
     * 显示顶部banner
     * @param activity
     * @param viewGroup
     */
    public void showTopBanner(Activity activity, ViewGroup viewGroup) {
        HomeTopBanner homeTopBanner = new HomeTopBanner();
        homeTopBanner.show(activity, viewGroup);
    }

}
