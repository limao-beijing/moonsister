package com.moonsister.tcjy.viewholder;

import android.view.View;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.utils.UIUtils;

/**
 * Created by jb on 2016/9/6.
 */
public class CommentDynacmicViewHolder extends BaseHolder {
    @Override
    protected View initView() {
        return UIUtils.inflateLayout(R.layout.view_holder_comment_dynamic);
    }

    @Override
    public void refreshView(Object data) {

    }
}
