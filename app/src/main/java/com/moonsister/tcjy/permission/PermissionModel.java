package com.moonsister.tcjy.permission;

import com.hickey.network.bean.PermissionBean;
import com.hickey.tool.base.BaseIModel;
import com.hickey.tool.constant.EnumConstant;


/**
 * Created by jb on 2016/11/6.
 */
public interface PermissionModel extends BaseIModel {
    void checkVip(String toUid, EnumConstant.PermissionType type, onLoadDateSingleListener<PermissionBean> listener);
}
