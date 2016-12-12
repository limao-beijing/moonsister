package com.moonsister.tcjy.home.model;


import com.hickey.network.bean.BannerBean;
import com.hickey.network.bean.BaseBean;
import com.hickey.network.bean.HomeParams;
import com.hickey.tool.base.BaseIModel;

/**
 * Created by jb on 2016/9/25.
 */
public interface HomeThreeFragmentModel extends BaseIModel {
    void loadDate(String type, HomeParams params, int page, int flag, onLoadDateSingleListener<BaseBean> listener);

    void loadBannerData(onLoadDateSingleListener<BannerBean> listener);

}
