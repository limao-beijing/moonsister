package com.moonsister.tcjy.permission;

import com.hickey.network.bean.PermissionBean;
import com.hickey.tool.base.BaseIModel;
import com.hickey.tool.constant.EnumConstant;
import com.hickey.tool.lang.StringUtis;


/**
 * Created by jb on 2016/11/6.
 */

public class UserPermissionManager {

    private volatile static UserPermissionManager instance;

    public static UserPermissionManager getInstance() {
        if (instance == null) {
            synchronized (UserPermissionManager.class) {
                if (instance == null) {
                    instance = new UserPermissionManager();
                }

            }
        }
        return instance;
    }

    public interface PermissionCallback {
        void onStatus(EnumConstant.PermissionReasult reasult, int imCount, String sex);
    }

    public void checkVip(EnumConstant.PermissionType type, PermissionCallback callback) {
        PermissionModel model = new PermissionModelImpl();
        model.checkVip("", type, new BaseIModel.onLoadDateSingleListener<PermissionBean>() {
            @Override
            public void onSuccess(PermissionBean bean, BaseIModel.DataType dataType) {
                PermissionBean.DataBean data = bean.getData();
                String type = data.getAct_type();
                String sex = data.getSex();

                if (callback != null) {
                    if (StringUtis.equals("1", type))
                        callback.onStatus(EnumConstant.PermissionReasult.HAVE_PERSSION, 0, sex);
                    else if (StringUtis.equals("2", type))
                        callback.onStatus(EnumConstant.PermissionReasult.NOT_PERSSION, 0, sex);
                }
            }

            @Override
            public void onFailure(String msg) {
                if (callback != null) {
                    callback.onStatus(EnumConstant.PermissionReasult.NOT_NET, 0, "");
                }
            }
        });
    }

    /**
     * 聊天权限
     *
     * @param callback
     */
    public void checkIMPermission(String toUid, PermissionCallback callback) {
        PermissionModel model = new PermissionModelImpl();
        model.checkVip(toUid, EnumConstant.PermissionType.CHAT_ACT, new BaseIModel.onLoadDateSingleListener<PermissionBean>() {
            @Override
            public void onSuccess(PermissionBean bean, BaseIModel.DataType dataType) {
                PermissionBean.DataBean data = bean.getData();
                String type = data.getAct_type();
                String sex = data.getSex();
                String num = data.getLast_chat_num();
                if (callback != null) {
                    if (StringUtis.equals("1", type))
                        callback.onStatus(EnumConstant.PermissionReasult.HAVE_PERSSION, 0, sex);
                    else if (StringUtis.equals("2", type))
                        callback.onStatus(EnumConstant.PermissionReasult.NOT_PERSSION, StringUtis.string2Int(num), sex);
                }
            }

            @Override
            public void onFailure(String msg) {
                if (callback != null) {
                    callback.onStatus(EnumConstant.PermissionReasult.NOT_NET, 0, "");
                }
            }
        });
    }

}
