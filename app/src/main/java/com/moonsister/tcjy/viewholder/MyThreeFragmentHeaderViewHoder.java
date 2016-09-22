package com.moonsister.tcjy.viewholder;

import android.view.View;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.bean.MyThreeFragmentHeaderBean;
import com.moonsister.tcjy.utils.UIUtils;

/**
 * Created by jb on 2016/9/22.
 */
public class MyThreeFragmentHeaderViewHoder  extends BaseHolder<MyThreeFragmentHeaderBean>{
    @Override
    protected View initView() {
        return UIUtils.inflateLayout(R.layout.holder_mythree_header);
    }

    @Override
    public void refreshView(MyThreeFragmentHeaderBean data) {

    }
}
