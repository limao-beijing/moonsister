package com.moonsister.tcjy.viewholder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hickey.network.ImageServerApi;
import com.hickey.tool.constant.EnumConstant;
import com.hickey.tool.lang.StringUtis;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseRecyclerViewHolder;
import com.hickey.network.bean.EngagementManagerBean;
import com.moonsister.tcjy.engagement.EngagementUtils;
import com.moonsister.tcjy.engagement.presenter.EngagementActionPersenter;
import com.moonsister.tcjy.engagement.view.EngagementManagerFragmentView;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ActivityUtils;
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
        if (bean.getManagerType() == EnumConstant.ManagerType.activity) {
            selectEngegamentStatus(bean);
            ImageServerApi.showURLImage(mRivAvater, bean.getTo_face());
            mTvUserName.setText(bean.getTo_nickname());
            mRivAvater.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ActivityUtils.startPersonalActivity(bean.getTo_uid());
                }
            });
        } else {
            selectEngegamentedStatus(bean);
            ImageServerApi.showURLImage(mRivAvater, bean.getFrom_face());
            mTvUserName.setText(bean.getFrom_nickname());
            mRivAvater.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ActivityUtils.startPersonalActivity(bean.getFrom_uid());
                }
            });
        }
        mTvEngagementType.setText("约见目的：" + EngagementUtils.getEngagementStr(bean.getStatus()));
        setClick(bean);
    }


    private void selectEngegamentedStatus(EngagementManagerBean.DataBean bean) {
        /**
         * 1 等待应答
         2 约会中
         3 申诉中
         4 已成功
         5 已失败
         */
        switch (bean.getStatus()) {
            case 1://1 等待应答
                mTvBtnOne.setText(getString(R.string.engagement_invite));
                mTvBtnOne.setBackground(getDrawable(bgRed));
                mTvBtnTwo.setText(getString(R.string.engagement_refuse));
                mTvBtnThree.setVisibility(View.INVISIBLE);
                tv_engagement_status.setText(bean.getTimeinfo());
                mTvEngagementTextStatus.setText(getString(R.string.engagement_status_text_await));
                break;
            case 2:// 2 约会中
                mTvBtnOne.setText(getString(R.string.engagement_appointments));
                mTvBtnOne.setBackground(getDrawable(bgGray));
                mTvBtnThree.setVisibility(View.INVISIBLE);
                mTvBtnTwo.setText(getString(R.string.engagement_refuse));
                tv_engagement_status.setText(getString(R.string.engagement_status_in));
                mTvEngagementTextStatus.setText(getString(R.string.engagement_status_text_in));

                break;
            case 3://3 申诉中
                mTvBtnOne.setVisibility(View.GONE);
                mTvBtnTwo.setVisibility(View.GONE);
                mTvBtnThree.setVisibility(View.GONE);
                tv_engagement_status.setText(getString(R.string.engagement_status_applying));
                mTvEngagementTextStatus.setText(getString(R.string.engagement_status_msg_order_lock));
                break;
            case 4://4 已成功

                mTvBtnOne.setText(getString(R.string.engagement_agree));
                mTvBtnOne.setBackground(getDrawable(bgGray));
                mTvBtnThree.setVisibility(View.INVISIBLE);
                mTvBtnTwo.setText(getString(R.string.engagement_refuse));
                tv_engagement_status.setText(getString(R.string.engagement_status_success));
                mTvEngagementTextStatus.setText(getString(R.string.engagement_status_text_success));
                break;
            case 5://5 已失败
                mTvBtnOne.setText(getString(R.string.engagement_agree));
                mTvBtnOne.setBackground(getDrawable(bgGray));
                mTvBtnThree.setVisibility(View.INVISIBLE);
                mTvBtnTwo.setText(getString(R.string.engagement_refuse));
                tv_engagement_status.setText(getString(R.string.engagement_status_failure));
                mTvEngagementTextStatus.setText(getString(R.string.engagement_status_text_failure));
                break;

        }
    }

    private void setClick(EngagementManagerBean.DataBean bean) {
        /**
         *  1 等待应答
         2 约会中
         3 申诉中
         4 已成功
         5 已失败
         */
        mTvBtnThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bean.getAppeal_status() == 3) {
                    if (baseRecyclerViewAdapter.getBaseIView() != null)
                        baseRecyclerViewAdapter.getBaseIView().transfePageMsg(getString(R.string.order_lock));
                    return;
                }
                if (bean.getStatus() == 5 || bean.getStatus() == 4) {
                    if (baseRecyclerViewAdapter.getBaseIView() != null)
                        baseRecyclerViewAdapter.getBaseIView().transfePageMsg(getString(R.string.order_finish));
                    return;
                }
                Object tag = v.getTag(R.id.tag_first);
                if (tag instanceof Boolean) {
                    if ((Boolean) tag) {
//                        Events<String> events = new Events<>();
//                        events.message = bean.getId();
//                        events.what = Events.EventEnum.CLICK_ENGAGEMENT_SUCCESS;
//                        RxBus.getInstance().send(events);
                        EngagementActionPersenter presenetr = (EngagementActionPersenter) bean.getPresenetr();
                        if (presenetr != null) {
                            presenetr.actionEngagement(bean.getId(), 4);
                        }
                    }
                }
            }
        });
        mTvBtnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bean.getAppeal_status() == 3) {
                    if (baseRecyclerViewAdapter.getBaseIView() != null)
                        baseRecyclerViewAdapter.getBaseIView().transfePageMsg(getString(R.string.order_lock));
                    return;
                }
                if (bean.getStatus() == 5) {
                    if (baseRecyclerViewAdapter.getBaseIView() != null)
                        baseRecyclerViewAdapter.getBaseIView().transfePageMsg(getString(R.string.order_finish));
                    return;
                }

                if (bean.getManagerType() == EnumConstant.ManagerType.activity) {
                    ActivityUtils.startEengegamentAppealActivity(bean.getId());
                } else if (bean.getStatus() == 1) {
//                    Events<String> events = new Events<>();
//                    events.message = bean.getId();
//                    events.what = Events.EventEnum.CLICK_ENGAGEMENT_REFUSE;
//                    RxBus.getInstance().send(events);
                    EngagementActionPersenter presenetr = (EngagementActionPersenter) bean.getPresenetr();
                    if (presenetr != null) {
                        presenetr.actionEngagement(bean.getId(), 2);
                    }
                }

            }
        });
        mTvBtnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bean.getStatus() == 3) {
                    if (baseRecyclerViewAdapter.getBaseIView() != null)
                        baseRecyclerViewAdapter.getBaseIView().transfePageMsg(getString(R.string.order_lock));
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
                    ActivityUtils.startEngagemengOrderActivity(type, bean.getTo_uid(), bean.getTo_nickname(), bean.getTo_face());
                } else if (StringUtis.equals(content, getString(R.string.engagement_invite))) {
//                    Events<String> events = new Events<>();
//                    events.message = bean.getId();
//                    events.what = Events.EventEnum.EngagementManagerFragment_CLICK_ENGAGEMENT_INVITE;
//                    RxBus.getInstance().send(events);
                    EngagementActionPersenter presenetr = (EngagementActionPersenter) bean.getPresenetr();
                    if (presenetr != null) {
                        presenetr.actionEngagement(bean.getId(), 3);
                    }
                }

            }
        });
    }

    @Override
    protected void onItemclick(View view, EngagementManagerBean.DataBean bean, int position) {
        if (!StringUtis.equals(UserInfoManager.getInstance().getUid(), bean.getTo_uid())) {
            ActivityUtils.startEngagementedDetailsActivity(bean.getId());
        } else
            ActivityUtils.startEngagementDetailsActivity(bean.getId());

    }

    private void selectEngegamentStatus(EngagementManagerBean.DataBean bean) {
        /**
         * 1 等待应答
         2 约会中
         3 申诉中
         4 已成功
         5 已失败
         */
        switch (bean.getStatus()) {
            case 1://1 等待应答
                mTvBtnTwo.setVisibility(View.GONE);
                mTvBtnOne.setText(getString(R.string.engagement_await));
                mTvBtnOne.setBackground(getDrawable(bgGray));
                mTvBtnThree.setVisibility(View.INVISIBLE);
                tv_engagement_status.setText(bean.getTimeinfo());
                mTvEngagementTextStatus.setText(getString(R.string.engagement_status_text_await));
                break;
            case 2:// 2 约会中
                mTvBtnTwo.setVisibility(View.GONE);
                mTvBtnOne.setText(getString(R.string.engagement_agree));
                mTvBtnOne.setBackground(getDrawable(bgGray));
                mTvBtnThree.setVisibility(View.VISIBLE);
                mTvBtnThree.setText(getString(R.string.engagement_status_success));
                mTvBtnThree.setBackground(getDrawable(bgRed));
                mTvBtnThree.setTag(R.id.tag_first, true);
                tv_engagement_status.setText(getString(R.string.engagement_status_in));
                mTvEngagementTextStatus.setText(getString(R.string.engagement_status_text_in));

                break;
            case 3:// 3 申诉中
                mTvBtnOne.setVisibility(View.GONE);
                mTvBtnTwo.setVisibility(View.GONE);
                mTvBtnThree.setVisibility(View.GONE);
                tv_engagement_status.setText(getString(R.string.engagement_status_applying));
                mTvEngagementTextStatus.setText(getString(R.string.engagement_status_msg_order_lock));
                break;
            case 4:// 4 已成功
                mTvBtnTwo.setVisibility(View.GONE);
                mTvBtnOne.setText(getString(R.string.engagement_agree));
                mTvBtnOne.setBackground(getDrawable(bgGray));
                mTvBtnThree.setVisibility(View.VISIBLE);
                mTvBtnThree.setText(getString(R.string.engagement_status_success));
                mTvBtnThree.setTag(R.id.tag_first, false);
                mTvBtnThree.setBackground(getDrawable(bgGray));
                tv_engagement_status.setText(getString(R.string.engagement_status_success));
                mTvEngagementTextStatus.setText(getString(R.string.engagement_status_text_success));

                break;
            case 5://  5 已失败
                mTvBtnTwo.setVisibility(View.GONE);
                mTvBtnOne.setText(getString(R.string.engagement_again));
                mTvBtnOne.setBackground(getDrawable(bgRed));
                mTvBtnThree.setVisibility(View.INVISIBLE);
                tv_engagement_status.setText(getString(R.string.engagement_status_failure));
                mTvEngagementTextStatus.setText(getString(R.string.engagement_status_text_failure));
                break;

        }
    }

    @Override
    protected boolean onLongClick(View view, EngagementManagerBean.DataBean bean, int position) {

        if (baseIView != null && baseIView instanceof EngagementManagerFragmentView)
            ((EngagementManagerFragmentView) baseIView).showDelectDialog(bean, position);
        return true;
    }
}
