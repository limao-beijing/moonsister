package com.moonsister.tcjy.main.view;

import com.moonsister.tcjy.base.BaseIView;

/**
 * Created by jb on 2016/7/11.
 */
public interface FindPasswordActivityView extends BaseIView {
    void LoopMsg();

    void navigationNext(String authcode);
}
