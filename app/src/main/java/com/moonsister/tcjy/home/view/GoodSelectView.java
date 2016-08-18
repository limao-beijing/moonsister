package com.moonsister.tcjy.home.view;

import com.moonsister.tcjy.base.BaseIView;
import com.moonsister.tcjy.bean.GoodSelectBaen;

import java.util.List;

/**
 * Created by pc on 2016/6/3.
 */
public interface GoodSelectView extends BaseIView {
    void addGoodSelectDate(List<GoodSelectBaen.Data> list);

}
