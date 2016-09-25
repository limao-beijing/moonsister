package com.moonsister.tcjy.my.persenter;

import com.moonsister.tcjy.base.BaseIPresenter;
import com.moonsister.tcjy.my.view.MyThreeFragmentView;

/**
 * Created by jb on 2016/9/25.
 */
public interface MyThreeFragmentPresenter  extends BaseIPresenter<MyThreeFragmentView>{
    void loadData(int page, String type);

    void loadHeaderData();
}
