package com.moonsister.tcjy.main.presenter;


import com.hickey.tool.base.BaseIPresenter;
import com.moonsister.tcjy.main.view.FilloutActivityView;

/**
 * Created by x on 2016/9/1.
 */
public interface FillOutActivityPresenter extends BaseIPresenter<FilloutActivityView> {
    void fillout(String face,String nickname );
    void submit(String address1);
}
