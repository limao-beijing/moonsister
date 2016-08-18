package com.moonsister.tcjy.update.presenter;

import com.moonsister.tcjy.base.BaseIPresenter;
import com.moonsister.tcjy.update.view.UpdateManagerView;

/**
 * Created by jb on 2016/7/14.
 */
public interface UpdateManagerPresenter  extends BaseIPresenter<UpdateManagerView>{
    void loadVersionInfo(String apkPath);
}
