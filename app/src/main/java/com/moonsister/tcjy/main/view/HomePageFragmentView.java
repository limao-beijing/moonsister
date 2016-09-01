package com.moonsister.tcjy.main.view;

import com.moonsister.tcjy.base.BaseIView;
import com.moonsister.tcjy.bean.DynamicItemBean;
import com.moonsister.tcjy.bean.UserInfoDetailBean;

import java.util.List;

/**
 * Created by jb on 2016/9/1.
 */
public interface HomePageFragmentView  extends BaseIView{
    void setDynamicData(List<DynamicItemBean> list);

    void setHeaderData(UserInfoDetailBean bean);
}
