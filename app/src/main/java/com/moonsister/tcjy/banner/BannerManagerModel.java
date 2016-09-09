package com.moonsister.tcjy.banner;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.BannerBean;

/**
 * Created by jb on 2016/9/9.
 */
public interface BannerManagerModel extends BaseIModel {
    void loadBannerData(onLoadDateSingleListener<BannerBean> listener);

}
