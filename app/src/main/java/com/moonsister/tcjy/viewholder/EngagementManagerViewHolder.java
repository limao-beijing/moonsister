package com.moonsister.tcjy.viewholder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moonsister.tcjy.ImageServerApi;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseRecyclerViewHolder;
import com.moonsister.tcjy.bean.EngagementManagerBean;
import com.moonsister.tcjy.engagement.widget.EngagementManagerFragment;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.EnumConstant;
import com.moonsister.tcjy.utils.StringUtis;
import com.moonsister.tcjy.widget.RoundImageView;

import butterknife.Bind;

/**
 * Created by jb on 2016/9/28.
 */
public class EngagementManagerViewHolder extends BaseRecyclerViewHolder<EngagementManagerBean.DataBean> {
    @Bind(R.id.riv_avater)
    RoundImageView mRivAvater;
    @Bind(R.id.tv_user_name)
    TextView mTvUserName;
    @Bind(R.id.tv_engagement_money)
    TextView mTvEngagementMoney;
    @Bind(R.id.tv_engagement_type)
    TextView mTvEngagementType;
    @Bind(R.id.tv_engagement_date)
    TextView mTvEngagementDate;
    @Bind(R.id.tv_engagement_address)
    TextView mTvEngagementAddress;
    @Bind(R.id.tv_btn_one)
    TextView mTvBtnOne;
    @Bind(R.id.tv_btn_two)
    TextView mTvBtnTwo;
    @Bind(R.id.tv_btn_three)
    TextView mTvBtnThree;
    @Bind(R.id.ll_user_action)
    LinearLayout mLlUserAction;
    @Bind(R.id.tv_engagement_status)
    TextView tv_engagement_status;
    @Bind(R.id.tv_engagement_text_status)
    TextView mTvEngagementTextStatus;
    int bgRed = R.drawable.shape_background_half_round_red;
    int bgGray = R.drawable.shape_background_half_round_gray;

    public EngagementManagerViewHolder(View view) {
        super(view);
    }

    @Override
    public void onBindData(EngagementManagerBean.DataBean bean) {

        mTvEngagementMoney.setText("约会费用：" + bean.getMoney());
        mTvEngagementAddress.setText(bean.getAddress());
        mTvEngagementDate.setText(bean.getDate());
        if (bean.getManagerType() == EngagementManagerFragment.ManagerType.activity) {
            selectEngegamentStatus(bean);
            ImageServerApi.showURLImage(mRivAvater, bean.getTo_face());
            mTvUserName.setText(bean.getTo_nickname());
        } else {
            selectEngegamentedStatus(bean);
            ImageServerApi.showURLImage(mRivAvater, bean.getFrom_face());
            mTvUserName.setText(bean.getFrom_nickname());
        }
        selectEngegamentedType(bean);
        setClick(bean);
    }

    private void selectEngegamentedType(EngagementManagerBean.DataBean bean) {
        String type = "";
        switch (bean.getType()) {

            case 1:
                type = getString(R.string.engagment_meal);
                break;
            case 2:
                type = getString(R.string.asleep);
                break;
            case 3:
                type = getString(R.string.movie);
                break;
            case 4:
                type = getString(R.string.coffee);
                break;
            case 5:
                type = getString(R.string.shop);
                break;
            case 6:
                type = getString(R.string.travel);
                break;
            case 7:
                type = getString(R.string.more);
                break;
        }
        mTvEngagementType.setText("约见目的：" + type);
    }

    private void selectEngegamentedStatus(EngagementManagerBean.DataBean bean) {
        //status 约会的状态（0未付款，1已付款，2已接受，3已拒绝，4，已失败，5，已成功）
        switch (bean.getStatus()) {
            case 1:
                mTvBtnOne.setText(getString(R.string.engagement_invite));
                mTvBtnOne.setBackground(getDrawable(bgRed));
                mTvBtnTwo.setText(getString(R.string.engagement_refuse));
                mTvBtnThree.setVisibility(View.INVISIBLE);
                tv_engagement_status.setText(bean.getTimeinfo());
                mTvEngagementTextStatus.setText(getString(R.string.engagement_status_text_await));
                break;
            case 2:
                mTvBtnOne.setText(getString(R.string.engagement_appointments));
                mTvBtnOne.setBackground(getDrawable(bgGray));
                mTvBtnThree.setVisibility(View.INVISIBLE);
                mTvBtnTwo.setText(getString(R.string.engagement_refuse));
                tv_engagement_status.setText(getString(R.string.engagement_status_in));
                mTvEngagementTextStatus.setText(getString(R.string.engagement_status_text_in));

                break;
            case 3:
                mTvBtnOne.setText(getString(R.string.engagement_refuse_appointments));
                mTvBtnOne.setBackground(getDrawable(bgGray));
                mTvBtnThree.setVisibility(View.INVISIBLE);
                mTvBtnTwo.setText(getString(R.string.engagement_refuse));
                tv_engagement_status.setText(getString(R.string.engagement_status_failure));
                mTvEngagementTextStatus.setText(getString(R.string.engagement_status_text_failure));
                break;
            case 4:
                mTvBtnOne.setText(getString(R.string.engagement_again));
                mTvBtnOne.setBackground(getDrawable(bgRed));
                mTvBtnThree.setVisibility(View.INVISIBLE);
                mTvBtnTwo.setText(getString(R.string.engagement_refuse));
                tv_engagement_status.setText(getString(R.string.engagement_status_failure));
                mTvEngagementTextStatus.setText(getString(R.string.engagement_status_text_failure));
                break;
            case 5:
                mTvBtnOne.setText(getString(R.string.engagement_agree));
                mTvBtnOne.setBackground(getDrawable(bgGray));
                mTvBtnThree.setVisibility(View.INVISIBLE);
                mTvBtnTwo.setText(getString(R.string.engagement_refuse));
                tv_engagement_status.setText(getString(R.string.engagement_status_success));
                mTvEngagementTextStatus.setText(getString(R.string.engagement_status_text_success));
                break;

        }
    }

    private void setClick(EngagementManagerBean.DataBean bean) {
        mTvBtnThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bean.getAppeal_status() == 1) {
                    if (baseRecyclerViewAdapter.getBaseIView() != null)
                        baseRecyclerViewAdapter.getBaseIView().transfePageMsg(getString(R.string.order_lock));
                    return;
                }
                if (bean.getStatus() == 5) {
                    if (baseRecyclerViewAdapter.getBaseIView() != null)
                        baseRecyclerViewAdapter.getBaseIView().transfePageMsg(getString(R.string.order_finish));
                    return;
                }
                Object tag = v.getTag(R.id.tag_first);
                if (tag instanceof Boolean) {
                    if ((Boolean) tag) {
                        Events<String> events = new Events<>();
                        events.message = bean.getId();
                        events.what = Events.EventEnum.CLICK_ENGAGEMENT_SUCCESS;
                        RxBus.getInstance().send(events);
                    }
                }
            }
        });
        mTvBtnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bean.getAppeal_status() == 1) {
                    if (baseRecyclerViewAdapter.getBaseIView() != null)
                        baseRecyclerViewAdapter.getBaseIView().transfePageMsg(getString(R.string.order_lock));
                    return;
                }
                if (bean.getStatus() == 5) {
                    if (baseRecyclerViewAdapter.getBaseIView() != null)
                        baseRecyclerViewAdapter.getBaseIView().transfePageMsg(getString(R.string.order_finish));
                    return;
                }

                if (bean.getManagerType() == EngagementManagerFragment.ManagerType.activity) {
                    ActivityUtils.startEengegamentAppealActivity(bean.getId());
                } else if (bean.getStatus() == 1) {
                    Events<String> events = new Events<>();
                    events.message = bean.getId();
                    events.what = Events.EventEnum.CLICK_ENGAGEMENT_REFUSE;
                    RxBus.getInstance().send(events);
                }

            }
        });
        mTvBtnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bean.getAppeal_status() == 1) {
                    if (baseRecyclerViewAdapter.getBaseIView() != null)
                        baseRecyclerViewAdapter.getBaseIView().transfePageMsg(getString(R.string.order_lock));
                    return;
                }
                if (bean.getStatus() == 5) {
                    if (baseRecyclerViewAdapter.getBaseIView() != null)
                        baseRecyclerViewAdapter.getBaseIView().transfePageMsg(getString(R.string.order_finish));
                    return;
                }
//                if (StringUtis.string2Int(bean.getVip_level()) <= 0 && bean.getStatus() == 1) {
//                    RxBus.getInstance().send(Events.EventEnum.CLICK_ENGAGEMENT_LEVEL_NOT, null);
//                    return;
//                }
                String content = mTvBtnOne.getText().toString();
                if (StringUtis.equals(content, getString(R.string.engagement_again))) {
                    EnumConstant.EngegamentType type = EnumConstant.EngegamentType.other;
                    EnumConstant.EngegamentType[] values = EnumConstant.EngegamentType.values();
                    for (int i = 0; i < values.length; i++) {
                        if (values[i].getType() == bean.getType()) {
                            type = values[i];
                            break;
                        }
                    }
                    ActivityUtils.startEngagemengOrderActivity(type, bean.getTo_uid(), bean.getFrom_nickname(), bean.getFrom_face());
                } else if (StringUtis.equals(content, getString(R.string.engagement_invite))) {
                    Events<String> events = new Events<>();
                    events.message = bean.getId();
                    events.what = Events.EventEnum.CLICK_ENGAGEMENT_INVITE_2;
                    RxBus.getInstance().send(events);
                }

            }
        });
    }

    @Override
    protected void onItemclick(View view, EngagementManagerBean.DataBean bean, int position) {

    }

    private void selectEngegamentStatus(EngagementManagerBean.DataBean bean) {
        //status 约会的状态（0未付款，1已付款，2已接受，3已拒绝，4，已失败，5，已成功）
        switch (bean.getStatus()) {
            case 1:
                mTvBtnOne.setText(getString(R.string.engagement_await));
                mTvBtnOne.setBackground(getDrawable(bgGray));
                mTvBtnThree.setVisibility(View.INVISIBLE);
                tv_engagement_status.setText(bean.getTimeinfo());
                mTvEngagementTextStatus.setText(getString(R.string.engagement_status_text_await));
                break;
            case 2:
                mTvBtnOne.setText(getString(R.string.engagement_agree));
                mTvBtnOne.setBackground(getDrawable(bgGray));
                mTvBtnThree.setVisibility(View.VISIBLE);
                mTvBtnThree.setText(getString(R.string.engagement_status_success));
                mTvBtnThree.setBackground(getDrawable(bgRed));
                mTvBtnThree.setTag(R.id.tag_first, true);
                tv_engagement_status.setText(getString(R.string.engagement_status_in));
                mTvEngagementTextStatus.setText(getString(R.string.engagement_status_text_in));

                break;
            case 3:
                mTvBtnOne.setText(getString(R.string.engagement_again));
                mTvBtnOne.setBackground(getDrawable(bgRed));
                mTvBtnThree.setVisibility(View.INVISIBLE);
                tv_engagement_status.setText(getString(R.string.engagement_status_failure));
                mTvEngagementTextStatus.setText(getString(R.string.engagement_status_text_failure));
                break;
            case 4:
                mTvBtnOne.setText(getString(R.string.engagement_again));
                mTvBtnOne.setBackground(getDrawable(bgRed));
                mTvBtnThree.setVisibility(View.INVISIBLE);
                tv_engagement_status.setText(getString(R.string.engagement_status_failure));
                mTvEngagementTextStatus.setText(getString(R.string.engagement_status_text_failure));
                break;
            case 5:
                mTvBtnOne.setText(getString(R.string.engagement_agree));
                mTvBtnOne.setBackground(getDrawable(bgGray));
                mTvBtnThree.setVisibility(View.VISIBLE);
                mTvBtnThree.setText(getString(R.string.engagement_status_success));
                mTvBtnThree.setTag(R.id.tag_first, false);
                mTvBtnThree.setBackground(getDrawable(bgGray));
                tv_engagement_status.setText(getString(R.string.engagement_status_success));
                mTvEngagementTextStatus.setText(getString(R.string.engagement_status_text_success));
                break;

        }
    }

}
