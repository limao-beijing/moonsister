package com.moonsister.tcjy.engagement.presenter;


import com.hickey.tool.base.BaseIPresenter;
import com.moonsister.tcjy.engagement.view.EngagementActionView;

/**
 * Created by jb on 2016/11/12.
 */
public interface EngagementActionPersenter extends BaseIPresenter<EngagementActionView> {
    /**
     * 操作类型 1取消订单，2拒绝，3接受，4设置成功，5失败后申请退款
     * @param id
     * @param actionType
     */
    void actionEngagement(String id, int actionType);
}
