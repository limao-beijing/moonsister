package com.moonsister.tcjy.my.model;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.BaseBean;
import com.moonsister.tcjy.center.widget.DynamicContentFragment;

import java.util.List;

/**
 * Created by jb on 2016/9/30.
 */
public interface DynamicResAddModel extends BaseIModel {
    void submit(DynamicContentFragment.DynamicType type, List<String> contents, onLoadDateSingleListener<BaseBean> listener);
}
