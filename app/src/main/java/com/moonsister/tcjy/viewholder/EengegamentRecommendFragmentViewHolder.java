package com.moonsister.tcjy.viewholder;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hickey.network.ImageServerApi;
import com.hickey.network.bean.EngagemengRecommendBean;
import com.hickey.tool.base.BaseRecyclerViewHolder;
import com.hickey.tool.constant.EnumConstant;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.widget.RoundedImageView;

import butterknife.Bind;

/**
 * Created by jb on 2016/9/22.
 */
public class EengegamentRecommendFragmentViewHolder extends BaseRecyclerViewHolder<EngagemengRecommendBean.DataBean> {
    @Bind(R.id.civ_user_avater)
    RoundedImageView mCivUserAvater;
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
    @Bind(R.id.ll_user_info)
    LinearLayout ll_user_info;

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
        String type = bean.getHomeType();
        if (TextUtils.equals("3", type)) {
            mIvEngegament.setVisibility(View.GONE);
            tv_signature.setVisibility(View.GONE);
            RelativeLayout.LayoutParams llParams = (RelativeLayout.LayoutParams) ll_user_info.getLayoutParams();
            llParams.addRule(RelativeLayout.BELOW, mTvUserName.getId());
            ll_user_info.setLayoutParams(llParams);
            int dimension = (int) mCivUserAvater.getContext().getResources().getDimension(R.dimen.x96);
            ViewGroup.LayoutParams params = mCivUserAvater.getLayoutParams();
            params.height = dimension;
            params.width = dimension;
            mCivUserAvater.setLayoutParams(params);

            return;
        }
        mIvEngegament.setVisibility(View.VISIBLE);
        mIvEngegament.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bean.getEngegamentType() == EnumConstant.EngegamentType.All || TextUtils.equals(type, "2")) {
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
