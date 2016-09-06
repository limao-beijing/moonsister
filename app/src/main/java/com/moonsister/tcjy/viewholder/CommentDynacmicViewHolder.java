package com.moonsister.tcjy.viewholder;

import android.view.View;
import android.widget.TextView;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.bean.DynamicDatailsBean;
import com.moonsister.tcjy.bean.DynamicItemBean;
import com.moonsister.tcjy.utils.UIUtils;

import butterknife.Bind;

/**
 * Created by jb on 2016/9/6.
 */
public class CommentDynacmicViewHolder extends BaseHolder<DynamicDatailsBean> {
    @Bind(R.id.tv_comment_number)
    TextView mTvCommentNumber;
    @Bind(R.id.tv_wacth)
    TextView mTvWacth;
    @Bind(R.id.tv_not_like)
    TextView mTvNotLike;
    @Bind(R.id.tv_like)
    TextView mTvLike;

    @Override
    protected View initView() {
        return UIUtils.inflateLayout(R.layout.view_holder_comment_dynamic);
    }

    @Override
    public void refreshView(DynamicDatailsBean bean) {
        if (bean == null || bean.getData() == null)
            return;
        DynamicItemBean data = bean.getData();
        mTvCommentNumber.setText(UIUtils.getStringRes(R.string.comment) + "  " + data.getComment_count());

        mTvWacth.setText("浏览 " + data.getView_num());
        mTvNotLike.setText("踩 " + data.getLdon());
        mTvLike.setText("赞 " + data.getLupn());
    }
}
