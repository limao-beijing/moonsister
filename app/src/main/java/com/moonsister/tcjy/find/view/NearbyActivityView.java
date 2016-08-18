package com.moonsister.tcjy.find.view;

import com.moonsister.tcjy.base.BaseIView;
import com.moonsister.tcjy.bean.NearbyBean;

import java.util.List;

/**
 * Created by jb on 2016/8/4.
 */
public interface NearbyActivityView extends BaseIView {
    void setData(List<NearbyBean.DataBean> data);
}
