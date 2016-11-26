package com.moonsister.tcjy.home.view;

import com.hickey.network.bean.GoodSelectBaen;
import com.hickey.tool.base.BaseIView;

import java.util.List;

/**
 * Created by pc on 2016/6/3.
 */
public interface GoodSelectView extends BaseIView {
    void addGoodSelectDate(List<GoodSelectBaen.Data> list);

}
