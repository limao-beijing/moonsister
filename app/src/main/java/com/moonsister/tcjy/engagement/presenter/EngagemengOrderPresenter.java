package com.moonsister.tcjy.engagement.presenter;


import com.hickey.tool.base.BaseIPresenter;
import com.hickey.tool.constant.EnumConstant;
import com.moonsister.tcjy.engagement.model.EngagemengOrderView;

/**
 * Created by jb on 2016/9/27.
 */
public interface EngagemengOrderPresenter extends BaseIPresenter<EngagemengOrderView> {
    void submit(String dating_count, EnumConstant.EngegamentType type, String uid, String money, String date, String message, String address);
    void loadData();
}
