package com.moonsister.tcjy.my.persenter;

import com.moonsister.tcjy.base.BaseIPresenter;
import com.moonsister.tcjy.center.widget.DynamicContentFragment;
import com.moonsister.tcjy.my.widget.DynamicResAddView;

import java.util.List;

/**
 * Created by jb on 2016/9/30.
 */
public interface DynamicResAddPersenter extends BaseIPresenter<DynamicResAddView> {
    void submit(DynamicContentFragment.DynamicType type, List<String> contents);
}
