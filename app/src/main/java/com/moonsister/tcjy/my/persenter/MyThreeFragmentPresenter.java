package com.moonsister.tcjy.my.persenter;


import com.hickey.tool.base.BaseIPresenter;
import com.moonsister.tcjy.my.view.MyThreeFragmentView;

/**
 * Created by jb on 2016/9/25.
 */
public interface MyThreeFragmentPresenter  extends BaseIPresenter<MyThreeFragmentView> {
    void loadData(String uid, int page, String type);

    void loadHeaderData(String uid);

    void delectRes(String ResId);
}
