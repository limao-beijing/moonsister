package com.moonsister.tcjy.permission;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.PermissionBean;
import com.moonsister.tcjy.utils.EnumConstant;

/**
 * Created by jb on 2016/11/6.
 */
public interface PermissionModel extends BaseIModel {
    void checkVip(String toUid, EnumConstant.PermissionType type, onLoadDateSingleListener<PermissionBean> listener);
}
