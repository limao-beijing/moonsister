package com.moonsister.tcjy.viewholder;

import android.support.annotation.IdRes;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.moonsister.tcjy.ImageServerApi;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.bean.UserDetailBean;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tcjy.widget.CircularImageView;

import butterknife.Bind;
import butterknife.OnClick;

import static com.moonsister.tcjy.utils.UIUtils.getResources;
import static com.moonsister.tcjy.utils.UIUtils.getStringRes;

/**
 * Created by jb on 2016/9/22.
 */
public class PersonThreeFragmentHeaderViewHoder extends BaseHolder<UserDetailBean> {
    @Bind(R.id.civ_user_avater)
    CircularImageView mCivUserAvater;
    @Bind(R.id.tv_age)
    TextView mTvAge;
    @Bind(R.id.tv_height)
    TextView mTvHeight;
    @Bind(R.id.tv_address)
    TextView mTvAddress;
    @Bind(R.id.ll_user_info)
    LinearLayout mLlUserInfo;
    @Bind(R.id.tv_signature)
    TextView mTvSignature;
    @Bind(R.id.view_center_line)
    View mViewCenterLine;
    @Bind(R.id.iv_add_vip)
    TextView mIvAddVip;
    @Bind(R.id.iv_dynamic_manager)
    TextView mIvDynamicManager;
    @Bind(R.id.iv_engegament_manager)
    TextView mIvEngegamentManager;
    @Bind(R.id.iv_finance_manager)
    TextView mIvFinanceManager;
    @Bind(R.id.ll_user_action)
    LinearLayout mLlUserAction;
    @Bind(R.id.view_line)
    View mViewLine;
    @Bind(R.id.tv_all)
    public TextView mTvAll;
    @Bind(R.id.view_all_line)
    View mViewAllLine;
    @Bind(R.id.rl_all)
    RelativeLayout mRlAll;
    @Bind(R.id.tv_type_user)
    TextView mTvTypeUser;
    @Bind(R.id.view_user_line)
    View mViewUserLine;
    @Bind(R.id.rl_user)
    RelativeLayout mRlUser;
    @Bind(R.id.tv_type_dynamic)
    TextView mTvTypeDynamic;
    @Bind(R.id.view_dynamic_line)
    View mViewDynamicLine;
    @Bind(R.id.rl_dynamic)
    RelativeLayout mRlDynamic;
    @Bind(R.id.tv_engagement)
    TextView tv_engagement;

    @Override
    protected View initView() {

        return UIUtils.inflateLayout(R.layout.holder_personthree_header);
    }

    @Override
    public void refreshView(UserDetailBean data) {
        UserDetailBean.DataBean bean = data.getData();
        if (bean == null)
            return;
        ImageServerApi.showURLImage(mCivUserAvater, bean.getBaseinfo().getFace());
        mTvSignature.setText(bean.getBaseinfo().getSignature());
        mTvAge.setText(bean.getBaseinfo().getAge());
        mTvHeight.setText(bean.getBaseinfo().getHeight());
        mTvAll.setText(String.format(getStringRes(R.string.my_three_pic), bean.getBaseinfo().getImage_count()));
        mTvTypeDynamic.setText(String.format(getStringRes(R.string.my_three_voice), bean.getBaseinfo().getVoice_count()));
        mTvTypeUser.setText(String.format(getStringRes(R.string.my_three_video), bean.getBaseinfo().getVideo_count()));
        mIvAddVip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.startBuyVipActivity();
            }
        });
        mIvDynamicManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.startPersonalReviseActivity();
            }
        });
        mIvEngegamentManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.startEngagementManagerActivity();
            }
        });
        mIvFinanceManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.startMoneyActivity(UserInfoManager.getInstance().getUid());
            }
        });
        tv_engagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.startPersonEngagementTypeActivity(bean.getBaseinfo().getUid(), bean.getBaseinfo().getNickname(), bean.getBaseinfo().getFace());
            }
        });

    }

    @OnClick({R.id.rl_all, R.id.rl_user, R.id.rl_dynamic})
    public void onClick(View view) {

    }

    public void selectColor(@IdRes int id) {
        int yellow = getResources().getColor(R.color.blue_4285f4);
        int transparent = getResources().getColor(R.color.transparent);
        mTvAll.setSelected(id == R.id.rl_all);
        mTvTypeUser.setSelected(id == R.id.rl_user);
        mTvTypeDynamic.setSelected(id == R.id.rl_dynamic);

        mViewAllLine.setBackgroundColor((id == R.id.rl_all) ? yellow : transparent);
        mViewUserLine.setBackgroundColor((id == R.id.rl_user) ? yellow : transparent);
        mViewDynamicLine.setBackgroundColor((id == R.id.rl_dynamic) ? yellow : transparent);


    }
}
