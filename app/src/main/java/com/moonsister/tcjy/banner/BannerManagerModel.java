package com.moonsister.tcjy.banner;

import com.hickey.network.bean.TopBannerBean;
import com.hickey.tool.base.BaseIModel;


/**
 * Created by jb on 2016/9/9.
 */
public interface BannerManagerModel extends BaseIModel {
    void loadBannerData(onLoadDateSingleListener<TopBannerBean> listener);

}
