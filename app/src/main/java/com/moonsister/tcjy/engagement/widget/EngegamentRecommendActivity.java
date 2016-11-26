package com.moonsister.tcjy.engagement.widget;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.hickey.tool.activity.FragmentUtils;
import com.hickey.tool.base.BaseActivity;
import com.hickey.tool.widget.UIUtils;
import com.moonsister.tcjy.R;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by jb on 2016/9/22.
 */
public class EngegamentRecommendActivity extends BaseActivity {

    @Bind(R.id.tv_engagement)
    TextView mTvEngagement;
    @Bind(R.id.tv_engagemented)
    TextView mTvEngagemented;
    @Bind(R.id.fl_content)
    FrameLayout mFlContent;

    private Fragment currFragment;
    private Fragment mEngagementManagerFragment, mEngagementedManagerFragment;

    @Override
    protected View setRootContentView() {
        return UIUtils.inflateLayout(R.layout.activity_engagment_manager);
    }

    @Override
    protected void initView() {
        mTvEngagement.setText(getString(R.string.engagement_wacth));
        mTvEngagemented.setText(getString(R.string.tv_recommend));
        onClick(mTvEngagement);
    }

    @OnClick({R.id.tv_engagement, R.id.tv_engagemented, R.id.iv_back})
    public void onClick(View view) {
        Fragment fragment = null;
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_engagement:
                if (mEngagementManagerFragment == null) {
                    mEngagementManagerFragment = EngegamentRecommendFragment.newInstance();
                    Bundle bundle = new Bundle();
                    bundle.putString("userType", "1");
                    mEngagementManagerFragment.setArguments(bundle);
                }
                fragment = mEngagementManagerFragment;
                break;
            case R.id.tv_engagemented:
                if (mEngagementedManagerFragment == null) {
                    mEngagementedManagerFragment = EngegamentRecommendFragment.newInstance();
                    Bundle bundle = new Bundle();
                    bundle.putString("userType", "2");
                    mEngagementedManagerFragment.setArguments(bundle);
//                    mEngagementedManagerFragment.setType(EnumConstant.ManagerType.passivity);
                }
                fragment = mEngagementedManagerFragment;
                break;
        }
        if (fragment != null) {
            FragmentUtils.swichReplaceFramgent(getSupportFragmentManager(), mFlContent.getId(), fragment);
            currFragment = fragment;
            selectColor(view);
        }
    }

    private void selectColor(View view) {
//        Drawable drawable = getResources().getDrawable(R.drawable.shape_background_half_round_blue_dark_151c22);
        Drawable drawableed = getResources().getDrawable(R.drawable.shape_background_half_round_blue_dark_1d262e);
        mTvEngagemented.setBackground(view == mTvEngagemented ? drawableed : null);
        mTvEngagement.setBackground(view == mTvEngagement ? drawableed : null);

    }
}
