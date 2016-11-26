package com.moonsister.tcjy.my.persenter;


import com.hickey.tool.base.BaseIPresenter;
import com.moonsister.tcjy.my.view.MyFragmentView;

/**
 * Created by jb on 2016/6/27.
 */
public interface MyFragmentPresenter extends BaseIPresenter<MyFragmentView> {

    void swicth2Page(int id);

    void loadPersonHeader();

    void loadonRefreshData();

    void loadLoadMoreData();

    void uploadBackground(String path);

    void deleteDynamic(String id);

    void upDynamic(String id);

    void delUpDynamic(String id);
}


