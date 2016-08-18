package com.moonsister.tcjy.home.model;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.GoodSelectBaen;

import java.util.List;

/**
 * Created by pc on 2016/6/3.
 */
public interface GoodSelectModel {
    void loadGoodSelectDate(int pageType, String type, int page, BaseIModel.onLoadDateSingleListener<List<GoodSelectBaen.Data>> listener);
}
