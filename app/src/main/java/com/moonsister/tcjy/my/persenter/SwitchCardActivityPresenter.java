package com.moonsister.tcjy.my.persenter;


import com.hickey.tool.base.BaseIPresenter;
import com.moonsister.tcjy.my.view.SwitchCardActivityView;

/**
 * Created by jb on 2016/7/4.
 */
public interface SwitchCardActivityPresenter  extends BaseIPresenter<SwitchCardActivityView> {
    void loadCardInfo();
}
