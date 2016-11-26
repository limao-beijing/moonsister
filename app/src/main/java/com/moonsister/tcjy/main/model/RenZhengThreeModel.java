package com.moonsister.tcjy.main.model;


import com.hickey.tool.base.BaseIModel;
import com.moonsister.tcjy.center.widget.DynamicContentFragment;

import java.util.List;

/**
 * Created by jb on 2016/10/10.
 */
public interface RenZhengThreeModel  extends BaseIModel {
    void submit(List<String> content, DynamicContentFragment.DynamicType type, onLoadDateSingleListener listener);
}
