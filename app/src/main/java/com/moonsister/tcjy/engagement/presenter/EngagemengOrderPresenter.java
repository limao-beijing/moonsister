package com.moonsister.tcjy.engagement.presenter;

import com.moonsister.tcjy.base.BaseIPresenter;
import com.moonsister.tcjy.engagement.model.EngagemengOrderView;
import com.hickey.tool.constant.EnumConstant;

/**
 * Created by jb on 2016/9/27.
 */
public interface EngagemengOrderPresenter extends BaseIPresenter<EngagemengOrderView> {
    void submit(String dating_count, EnumConstant.EngegamentType type, String uid, String money, String date, String message, String address);
    void loadData();
}
