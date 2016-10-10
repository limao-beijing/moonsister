package com.moonsister.tcjy.main.presenter;

import com.moonsister.tcjy.base.BaseIPresenter;
import com.moonsister.tcjy.center.widget.DynamicContentFragment;
import com.moonsister.tcjy.main.view.RenZhengThreeView;

import java.util.List;

/**
 * Created by jb on 2016/10/10.
 */
public interface RenZhengThreePresenter  extends BaseIPresenter<RenZhengThreeView>{
    void submit(List<String> content, DynamicContentFragment.DynamicType type);
}
