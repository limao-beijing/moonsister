package com.moonsister.tcjy.my.view;

import com.moonsister.tcjy.base.BaseIView;
import com.moonsister.tcjy.bean.MyThreeFragmentBean;
import com.moonsister.tcjy.bean.UserDetailBean;

import java.util.List;

/**
 * Created by jb on 2016/9/25.
 */
public interface MyThreeFragmentView extends BaseIView {
    void setData(List<MyThreeFragmentBean.DataBean> data);

    void setHeaderData(UserDetailBean bean);
}
