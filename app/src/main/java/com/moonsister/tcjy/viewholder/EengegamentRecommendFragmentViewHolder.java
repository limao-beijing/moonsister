package com.moonsister.tcjy.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hickey.network.ImageServerApi;
import com.hickey.network.bean.EngagemengRecommendBean;
import com.hickey.tool.constant.EnumConstant;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseRecyclerViewHolder;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.widget.CircularImageView;

import butterknife.Bind;

/**
 * Created by jb on 2016/9/22.
 */
public class EengegamentRecommendFragmentViewHolder extends BaseRecyclerViewHolder<EngagemengRecommendBean.DataBean> {
    @Bind(R.id.civ_user_avater)
    CircularImageView mCivUserAvater;
    @Bind(R.id.iv_add_v)
    ImageView mIvAddV;
    @Bind(R.id.tv_user_name)
    TextView mTvUserName;
    @Bind(R.id.tv_age)
    TextView mTvAge;
    @Bind(R.id.tv_height)
    TextView mTvHeight;
    @Bind(R.id.tv_weight)
    TextView mTvWeight;
    @Bind(R.id.tv_address)
    TextView mTvAddress;
    @Bind(R.id.iv_engegament)
    ImageView mIvEngegament;
    @Bind(R.id.tv_signature)
    TextView tv_signature;

    public EengegamentRecommendFragmentViewHolder(View view) {
        super(view);
    }

    @Override
    public void onBindData(EngagemengRecommendBean.DataBean bean) {
        ImageServerApi.showURLImage(mCivUserAvater, bean.getFace());

        mIvAddV.setVisibility(bean.getIsauth() == 1 ? View.VISIBLE : View.GONE);
        mTvUserName.setText(bean.getNickname());
        mTvAge.setText(bean.getAge());
        mTvHeight.setText(bean.getHeight());
        mTvAddress.setText(bean.getResidence());
        mTvWeight.setText(bean.getWeight());
        tv_signature.setText(bean.getSignature());

        mIvEngegament.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bean.getEngegamentType() == EnumConstant.EngegamentType.All) {
                    ActivityUtils.startPersonEngagementTypeActivity(bean.getUid(), bean.getNickname(), bean.getFace());
                } else
                    ActivityUtils.startEngagemengOrderActivity(bean.getEngegamentType(), bean.getUid(), bean.getNickname(), bean.getFace());
            }
        });
    }

    @Override
    protected void onItemclick(View view, EngagemengRecommendBean.DataBean bean, int position) {
        ActivityUtils.startPersonalActivity(bean.getUid());
    }
}
