package com.moonsister.tcjy.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.moonsister.tcjy.ImageServerApi;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseRecyclerViewHolder;
import com.moonsister.tcjy.bean.DynamicItemBean;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.StringUtis;
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
    @Bind(R.id.tv_wacth)
    TextView tvWacth;
    @Bind(R.id.iv_sex)
    ImageView iv_sex;
    @Bind(R.id.tv_age)
    TextView tvAge;
    @Bind(R.id.tv_signature)
    TextView tv_signature;
    @Bind(R.id.iv_add_v)
    ImageView iv_add_v;

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
        tvAge.setText(dynamicItemBean.getAge());
        tv_signature.setText(dynamicItemBean.getSignature());
        String sex = dynamicItemBean.getSex();
        if (StringUtis.equals(sex, "1")) {
            iv_sex.setImageResource(R.mipmap.boy);
        } else {
            iv_sex.setImageResource(R.mipmap.gril);
        }
        if (StringUtis.equals(dynamicItemBean.getIsauth(), "1")) {
            iv_add_v.setVisibility(View.VISIBLE);
        } else {
            iv_add_v.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onItemclick(View view, DynamicItemBean dynamicItemBean, int position) {
        ActivityUtils.startDynamicActivity(dynamicItemBean.getUid());
    }
}
