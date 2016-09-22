package com.moonsister.tcjy.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseRecyclerViewHolder;
import com.moonsister.tcjy.bean.EengegamentRecommendBean;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.widget.CircularImageView;

import butterknife.Bind;

/**
 * Created by jb on 2016/9/22.
 */
public class EengegamentRecommendFragmentViewHolder extends BaseRecyclerViewHolder<EengegamentRecommendBean> {
    @Bind(R.id.civ_user_avater)
    CircularImageView mCivUserAvater;
    @Bind(R.id.iv_add_v)
    ImageView mIvAddV;
    @Bind(R.id.iv_engegament)
    ImageView mIvEngegament;
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

    public EengegamentRecommendFragmentViewHolder(View view) {
        super(view);
    }

    @Override
    public void onBindData(EengegamentRecommendBean bean) {
        mIvEngegament.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.startEngagemengOrderActivity();
            }
        });
    }

    @Override
    protected void onItemclick(View view, EengegamentRecommendBean bean, int position) {
        ActivityUtils.startPersonalActivity(UserInfoManager.getInstance().getUid());
    }
}
