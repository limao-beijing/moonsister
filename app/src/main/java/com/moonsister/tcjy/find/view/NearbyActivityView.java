package com.moonsister.tcjy.find.view;

import com.hickey.network.bean.NearbyBean;
import com.hickey.tool.base.BaseIView;

import java.util.List;

/**
 * Created by jb on 2016/8/4.
 */
public interface NearbyActivityView extends BaseIView {
    void setData(List<NearbyBean.DataBean> data);
}
