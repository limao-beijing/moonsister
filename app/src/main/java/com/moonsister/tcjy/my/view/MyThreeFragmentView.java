package com.moonsister.tcjy.my.view;


import com.hickey.network.bean.MyThreeFragmentBean;
import com.hickey.network.bean.UserDetailBean;
import com.hickey.tool.base.BaseIView;

import java.util.List;

/**
 * Created by jb on 2016/9/25.
 */
public interface MyThreeFragmentView extends BaseIView {
    void setData(List<MyThreeFragmentBean.DataBean> data);

    void setHeaderData(UserDetailBean bean);

    void deleteSuccess(String id);

}
