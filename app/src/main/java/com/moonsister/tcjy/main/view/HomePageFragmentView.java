package com.moonsister.tcjy.main.view;


import com.hickey.network.bean.DynamicItemBean;
import com.hickey.network.bean.UserInfoDetailBean;

import java.util.List;

/**
 * Created by jb on 2016/9/1.
 */
public interface HomePageFragmentView  extends BasePageFragmentView{
    void setDynamicData(List<DynamicItemBean> list);

    void setHeaderData(UserInfoDetailBean bean);


}
