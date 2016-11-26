package com.moonsister.tcjy.home.model;

import com.hickey.network.bean.GoodSelectBaen;
import com.hickey.tool.base.BaseIModel;

import java.util.List;

/**
 * Created by pc on 2016/6/3.
 */
public interface GoodSelectModel {
    void loadGoodSelectDate(int pageType, String type, int page, BaseIModel.onLoadDateSingleListener<List<GoodSelectBaen.Data>> listener);
}
