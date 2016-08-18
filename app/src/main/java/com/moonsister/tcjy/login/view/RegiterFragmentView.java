package com.moonsister.tcjy.login.view;

import com.moonsister.tcjy.base.BaseIView;

/**
 * Created by pc on 2016/6/14.
 */
public interface RegiterFragmentView extends BaseIView {
        void navigationNext(String code);
        void requestFailed(String reason);
        void LoopMsg();
}
