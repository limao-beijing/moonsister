package com.moonsister.tcjy.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.moonsister.tcjy.ImageServerApi;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseRecyclerViewHolder;
import com.moonsister.tcjy.bean.DynamicItemBean;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.widget.RoundedImageView;

import butterknife.Bind;

/**
 * Created by jb on 2016/8/29.
 */
public class UserViewHolder extends BaseRecyclerViewHolder<DynamicItemBean> {
    @Bind(R.id.riv_friend_image)
    RoundedImageView rivFriendImage;
    @Bind(R.id.tv_user_name)
    TextView tvUserName;
    @Bind(R.id.textview_work)
    TextView textviewWork;
    @Bind(R.id.tv_friend_fen)
    TextView tvFriendFen;
    @Bind(R.id.delete_follow)
    ImageView deleteFollow;

    public UserViewHolder(View view) {
        super(view);
    }

    @Override
    public void onBindData(DynamicItemBean dynamicItemBean) {
        if (dynamicItemBean == null)
            return;
        ImageServerApi.showURLSamllImage(rivFriendImage, dynamicItemBean.getFace());
        tvUserName.setText(dynamicItemBean.getNickname());
//        textviewWork.setText(dynamicItemBean.);
        tvFriendFen.setText(dynamicItemBean.getFansnum());

    }

    @Override
    protected void onItemclick(View view, DynamicItemBean dynamicItemBean, int position) {
        ActivityUtils.startDynamicActivity(dynamicItemBean.getUid());
    }
}
