package com.moonsister.tcjy.viewholder;

import android.view.View;
import android.widget.TextView;

import com.hickey.network.ImageServerApi;
import com.hickey.network.bean.CommentDataListBean;
import com.hickey.tool.base.BaseRecyclerViewHolder;
import com.hickey.tool.time.TimeUtils;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.widget.RoundedImageView;

import butterknife.Bind;

/**
 * Created by pc on 2016/6/8.
 */
public class DynamicCommentViewHolder extends BaseRecyclerViewHolder<CommentDataListBean.DataBean> {
    @Bind(R.id.riv_item_user_image)
    RoundedImageView rivItemUserImage;
    @Bind(R.id.tv_user_name)
    TextView tvUserName;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.tv_comment_content)
    TextView tvCommentContent;

    public DynamicCommentViewHolder(View view) {
        super(view);
    }

    @Override
    public void onBindData(CommentDataListBean.DataBean bean) {
        if (bean == null)
            return;
        ImageServerApi.showURLSamllImage(rivItemUserImage, bean.getFace());
        tvCommentContent.setText(bean.getTitle());
        tvTime.setText(TimeUtils.getDynamicTimeString(bean.getCreate_time()));
        tvUserName.setText(bean.getNickname());
//        tvUserName.setText(bean.getStatus());
    }

    @Override
    protected void onItemclick(View view, CommentDataListBean.DataBean baseBean, int position) {
        ActivityUtils.startDynamicActivity(baseBean.getUid());
    }
}
