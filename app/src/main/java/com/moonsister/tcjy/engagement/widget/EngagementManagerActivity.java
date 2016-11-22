package com.moonsister.tcjy.engagement.widget;

import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.hickey.tool.constant.EnumConstant;
import com.moonsister.tcjy.utils.FragmentUtils;
import com.moonsister.tcjy.utils.UIUtils;
import com.trello.rxlifecycle.ActivityEvent;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by jb on 2016/9/28.
 */
public class EngagementManagerActivity extends BaseActivity {

    @Bind(R.id.tv_engagement)
    TextView mTvEngagement;
    @Bind(R.id.tv_engagemented)
    TextView mTvEngagemented;
    @Bind(R.id.fl_content)
    FrameLayout mFlContent;
    private Fragment currFragment;
    private EngagementManagerFragment mEngagementManagerFragment, mEngagementedManagerFragment;

    @Override
    protected View setRootContentView() {
        return UIUtils.inflateLayout(R.layout.activity_engagment_manager);
    }

    @Override
    protected void initView() {
        onClick(mTvEngagement);
        RxBus.with(this)
                .setEndEvent(ActivityEvent.DESTROY)
                .setEvent(Events.EventEnum.ENGAGEMENT_ACTION_SUCCESS)
                .onNext(events ->
                {
                    if (mEngagementManagerFragment != null)
                        mEngagementManagerFragment.actionSuccess();
                    if (mEngagementedManagerFragment != null)
                        mEngagementedManagerFragment.actionSuccess();
                })
                .create();
    }

    @OnClick({R.id.tv_engagement, R.id.tv_engagemented, R.id.iv_back})
    public void onClick(View view) {
        if (view.getId() == R.id.iv_back) {
            finish();
            return;
        }
        Fragment fragment = null;
        switch (view.getId()) {
            case R.id.tv_engagement:
                if (mEngagementManagerFragment == null) {
                    mEngagementManagerFragment = EngagementManagerFragment.newInstance();
                    mEngagementManagerFragment.setType(EnumConstant.ManagerType.activity);
                }
                fragment = mEngagementManagerFragment;
                break;
            case R.id.tv_engagemented:
                if (mEngagementedManagerFragment == null) {
                    mEngagementedManagerFragment = EngagementManagerFragment.newInstance();
                    mEngagementedManagerFragment.setType(EnumConstant.ManagerType.passivity);
                }
                fragment = mEngagementedManagerFragment;
                break;
        }
        if (fragment != null) {
            FragmentUtils.switchHideFragment(getSupportFragmentManager(), R.id.fl_content, currFragment, fragment);
            currFragment = fragment;
            selectColor(view);
        }
    }

    private void selectColor(View view) {
        Drawable drawable = getResources().getDrawable(R.drawable.shape_background_half_round_blue_dark_151c22);
        mTvEngagemented.setBackground(view == mTvEngagemented ? drawable : null);
        mTvEngagement.setBackground(view == mTvEngagement ? drawable : null);

    }
}
