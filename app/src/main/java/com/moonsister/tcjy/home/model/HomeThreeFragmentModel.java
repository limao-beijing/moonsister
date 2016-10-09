package com.moonsister.tcjy.home.model;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.BannerBean;
import com.moonsister.tcjy.bean.BaseBean;
import com.moonsister.tcjy.bean.HomeParams;

/**
 * Created by jb on 2016/9/25.
 */
public interface HomeThreeFragmentModel extends BaseIModel {
    void loadDate(String type, HomeParams params, int page, int flag, onLoadDateSingleListener<BaseBean> listener);

    void loadBannerData(onLoadDateSingleListener<BannerBean> listener);

}
