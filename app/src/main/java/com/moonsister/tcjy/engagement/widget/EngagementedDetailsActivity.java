package com.moonsister.tcjy.engagement.widget;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.hickey.network.ImageServerApi;
import com.hickey.network.bean.EngagementDetailsBean;
import com.hickey.tool.base.BaseActivity;
import com.hickey.tool.constant.EnumConstant;
import com.hickey.tool.lang.StringUtis;
import com.hickey.tool.view.image.ViewUtlis;
import com.hickey.tool.widget.UIUtils;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.engagement.EngagementUtils;
import com.moonsister.tcjy.engagement.presenter.EngagementActionPersenterImpl;
import com.moonsister.tcjy.engagement.presenter.EngagementDetailsPersenter;
import com.moonsister.tcjy.engagement.presenter.EngagementDetailsPersenterImpl;
import com.moonsister.tcjy.engagement.view.EngagementActionView;
import com.moonsister.tcjy.engagement.view.EngagementDetailsView;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.main.widget.RedpacketAcitivity;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.widget.CircularImageView;
import com.moonsister.tcjy.widget.RollingView;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by jb on 2016/11/11.
 */

public class EngagementedDetailsActivity extends BaseActivity implements View.OnClickListener, EngagementDetailsView, EngagementActionView {
    @Bind(R.id.tv_im)
    TextView mTvIm;
    @Bind(R.id.tv_wacth)
    TextView mTvWacth;
    @Bind(R.id.tv_pay)
    TextView mTvPay;
    @Bind(R.id.tv_flower)
    TextView mTvFlower;
    @Bind(R.id.tv_engagement)
    TextView mTvEngagement;
    @Bind(R.id.civ_user_avater)
    CircularImageView mCivUserAvater;
    @Bind(R.id.civ_user_avatered)
    CircularImageView mCivUserAvatered;
    @Bind(R.id.tv_user_name)
    TextView mTvUserName;
    @Bind(R.id.tv_user_nameed)
    TextView mTvUserNameed;
    @Bind(R.id.tv_count_down)
    TextView tv_count_down;


    @Bind(R.id.rl_flower)
    View rl_flower;

    @Bind(R.id.tv_engagement_pay)
    TextView tv_engagement_pay;
    @Bind(R.id.tv_engagement_date)
    TextView tv_engagement_date;
    @Bind(R.id.Rollin_view)
    RollingView Rollin_view;


    private EngagementActionPersenterImpl actionPersenter;
    private EngagementDetailsBean.DataBean bean;
    private EngagementDetailsPersenter persenter;
    private String id;
    private String order_id;
    private String srcorder_id;

    @Override
    protected View setRootContentView() {
        return UIUtils.inflateLayout(R.layout.activity_engagemented_details);
    }

    @Override
    protected void initView() {
        actionPersenter = new EngagementActionPersenterImpl();
        actionPersenter.attachView(this);
        persenter = new EngagementDetailsPersenterImpl();
        persenter.attachView(this);
        srcorder_id = getIntent().getStringExtra("order_id");
        if (StringUtis.isEmpty(srcorder_id)) {
            id = getIntent().getStringExtra("id");
            persenter.loadByIdData2(id);
        } else {
            persenter.loadByOrderIdData2(srcorder_id);
        }

    }


    @Override
    protected String initTitleName() {
        return getString(R.string.publish_success);
    }

    private PopupWindow popupWindow;

    private void showPopUp(View v, List<Integer> integers) {
        if (popupWindow == null) {
            LinearLayout layout = new LinearLayout(this);
            layout.setBackground(getResources().getDrawable(R.drawable.pop_engagement_manager));
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setGravity(Gravity.CENTER);
            addTextViewPopView(layout, integers);
            ViewUtlis.measureView(layout);
            popupWindow = new PopupWindow(layout, layout.getMeasuredWidth(), layout.getMeasuredHeight());
            popupWindow.setFocusable(true);
            popupWindow.setOutsideTouchable(true);
            popupWindow.setBackgroundDrawable(new BitmapDrawable());
            int[] location = new int[2];
            v.getLocationOnScreen(location);
            popupWindow.showAtLocation(v, Gravity.NO_GRAVITY, location[0], location[1] - popupWindow.getHeight());
        } else {
            popupWindow.dismiss();
            popupWindow = null;
        }
    }

    /**
     * @param viewGroup
     * @param
     */
    private void addTextViewPopView(ViewGroup viewGroup, List<Integer> strs) {
        for (int id : strs) {
            TextView view = new TextView(getApplicationContext());
            view.setText(getString(id));
            view.setTextColor(Color.BLACK);
            view.setGravity(Gravity.CENTER);
            view.setPadding(10, 10, 10, 10);
            view.setTag(getString(id));
            view.setOnClickListener(this);
            viewGroup.addView(view);
        }

    }

    @OnClick({R.id.rl_im, R.id.rl_wacth, R.id.rl_pay, R.id.rl_flower})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_im:
                if (bean != null)
                    ActivityUtils.startRedpacketActivity(StringUtis.equals(UserInfoManager.getInstance().getUid(), bean.getF_uid()) ? bean.getT_uid() : bean.getT_uid(),
                            RedpacketAcitivity.RedpacketType.TYPE_REDPACKET, StringUtis.equals(UserInfoManager.getInstance().getUid(), bean.getF_uid()) ? bean.getT_face() : bean.getT_face());
                break;
            case R.id.rl_wacth:
                if (bean != null)
                    ActivityUtils.startRedpacketActivity(StringUtis.equals(UserInfoManager.getInstance().getUid(), bean.getF_uid()) ? bean.getT_uid() : bean.getT_uid(),
                            RedpacketAcitivity.RedpacketType.TYPE_FLOWER, StringUtis.equals(UserInfoManager.getInstance().getUid(), bean.getF_uid()) ? bean.getT_face() : bean.getT_face());
                break;
            case R.id.rl_pay:
                ActivityUtils.startHomePageActivity(StringUtis.equals(UserInfoManager.getInstance().getUid(), bean.getF_uid()) ? bean.getT_uid() : bean.getF_uid());
                break;
            case R.id.rl_flower:
                if (persenter == null)
                    return;
                if (StringUtis.isEmpty(id) || StringUtis.equals("0", id)) {
                    showToast("订单生成中,请稍后再试");
                    if (persenter != null)
                        persenter.loadByOrderIdData2(srcorder_id);

                    return;
                }
                List<Integer> integers = EngagementUtils.popTextStr(bean.getStatus(), bean.getDating_status_add(), bean.getDating_status_add_msg(), StringUtis.equals(UserInfoManager.getInstance().getUid(), bean.getF_uid()));
                if (integers != null && integers.size() != 0) {
                    showPopUp(rl_flower, integers);
                }
                break;
        }
        Object tag = view.getTag();
        if (tag != null && tag instanceof String) {
            int code = EngagementUtils.getClickCode((String) tag);
            actionCode = code;
            if (code != 0) {
                if (code == 7) {
                    if (bean != null)
                        ActivityUtils.startEengegamentAppealActivity(id);
                } else if (code == 6) {
                    if (bean != null)
                        for (EnumConstant.EngegamentType type : EnumConstant.EngegamentType.values()) {
                            if (type.getType() == bean.getType()) {
                                ActivityUtils.startEngagemengOrderActivity(type, StringUtis.equals(UserInfoManager.getInstance().getUid(), bean.getF_uid()) ? bean.getT_uid() : bean.getT_uid(),
                                        StringUtis.equals(UserInfoManager.getInstance().getUid(), bean.getF_uid()) ? bean.getT_nickname() : bean.getT_nickname(), StringUtis.equals(UserInfoManager.getInstance().getUid(), bean.getF_uid()) ? bean.getT_face() : bean.getT_face());
                                break;
                            }

                        }

                } else {
                    actionPersenter.actionEngagement(id, code);
                    showPopUp(null, null);
                }
            }
        }
    }

    private int actionCode;

    @Override
    public void showLoading() {
        showProgressDialog();
    }

    @Override
    public void hideLoading() {
        hideProgressDialog();
    }

    @Override
    public void transfePageMsg(String msg) {
        showToast(msg);
    }

    @Override
    public void setData(EngagementDetailsBean.DataBean bean) {
        if (bean == null)
            return;
        this.bean = bean;
        order_id = bean.getOrder_id();
        id = bean.getDating_id();
        //发起者
        ImageServerApi.showURLBigImage(mCivUserAvater, bean.getF_face());
        mTvUserName.setText(bean.getF_nickname());
        //约会对象
        ImageServerApi.showURLBigImage(mCivUserAvatered, bean.getT_face());
        mTvUserNameed.setText(bean.getT_nickname());
        mTvEngagement.setText(EngagementUtils.getEngagementStr(bean.getType()));
        if (StringUtis.equals("0", order_id)) {
            mTvFlower.setText("订单生成中");
        } else {
            mTvFlower.setText(EngagementUtils.getStatusText(bean.getStatus(), bean.getAppeal_status(), bean.getDating_status_add_msg(), StringUtis.equals(UserInfoManager.getInstance().getUid(), bean.getF_uid())));
        }
        tv_engagement_pay.setText(bean.getInfo1());
        if (bean.getInfo2() != null) {
            tv_engagement_date.setText(Html.fromHtml(bean.getInfo2()));
        } else {
            tv_engagement_date.setText("");
        }
        rollingView(bean.getInfo3());
        long daojishi = bean.getDaojishi();


        if (daojishi > 0) {
            tv_count_down.setText(daojishi / 3600 + " 时 " + (daojishi % 3600) / 60 + " 分");
        }

    }

    private void rollingView(List<String> info3) {
// 绑定数据
        Rollin_view.setPageSize(3);
//        Rollin_view.setClickColor(0xff888888);
//        mRollingView.setLeftDrawable(R.drawable.drawable_red_dot);
        Rollin_view.setRollingText(info3);
//        Rollin_view.setOnItemClickListener(this);
        Rollin_view.setTextColor(R.color.color_ff8201);
        Rollin_view.pause();
        Rollin_view.resume();

    }

    @Override
    public void actionSuccess() {
        if (actionCode == 1) {
            showToast("约见取消成功");
            UIUtils.sendDelayedOneMillis(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            });
            return;
        }
        if (persenter != null) {
            persenter.loadByIdData2(id);
        }
        RxBus.getInstance().send(Events.EventEnum.ENGAGEMENT_ACTION_SUCCESS, null);
    }


}
