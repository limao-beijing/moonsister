package com.moonsister.tcjy.engagement.widget;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.hickey.network.ImageServerApi;
import com.hickey.network.bean.EngagementDetailsBean;
import com.hickey.tool.constant.EnumConstant;
import com.hickey.tool.lang.StringUtis;
import com.hickey.tool.time.TimeUtils;
import com.hickey.tool.view.image.ViewUtlis;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.engagement.EngagementUtils;
import com.moonsister.tcjy.engagement.presenter.EngagementActionPersenter;
import com.moonsister.tcjy.engagement.presenter.EngagementActionPersenterImpl;
import com.moonsister.tcjy.engagement.presenter.EngagementDetailsPersenter;
import com.moonsister.tcjy.engagement.presenter.EngagementDetailsPersenterImpl;
import com.moonsister.tcjy.engagement.presenter.EngagementTextPersenter;
import com.moonsister.tcjy.engagement.presenter.EngagementTextPersenterImpl;
import com.moonsister.tcjy.engagement.view.EngagementActionView;
import com.moonsister.tcjy.engagement.view.EngagementDetailsView;
import com.moonsister.tcjy.engagement.view.EngagementTextView;
import com.moonsister.tcjy.main.widget.RedpacketAcitivity;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tcjy.widget.CircularImageView;

import java.io.Serializable;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by jb on 2016/11/11.
 */

public class EngagementDetailsActivity extends BaseActivity implements EngagementDetailsView, EngagementTextView, View.OnClickListener, EngagementActionView {
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
    @Bind(R.id.et_input_money)
    TextView mEtInputMoney;
    @Bind(R.id.et_input_date)
    TextView mEtInputDate;
    @Bind(R.id.et_input_address)
    TextView mEtInputAddress;
    @Bind(R.id.et_engagement_message)
    TextView mEtEngagementMessage;
    @Bind(R.id.tv_engagement_text)
    TextView mTvEngagementText;
    @Bind(R.id.rl_flower)
    View rl_flower;
    @Bind(R.id.tv_count_down)
    TextView tv_count_down;
    @Bind(R.id.tv_sumbit)
    View tv_sumbit;
    @Bind(R.id.inc_content)
    View inc_content;

    private EngagementDetailsPersenter persenter;
    private EngagementTextPersenter textPersenter;
    private String id;
    private EngagementDetailsBean.DataBean bean;
    private EngagementActionPersenter actionPersenter;

    @Override
    protected String initTitleName() {
        return getString(R.string.publish_success);
    }

    @Override
    protected View setRootContentView() {
        return UIUtils.inflateLayout(R.layout.activity_engagement_details);
    }

    @Override
    public void actionSuccess() {
        if (persenter != null) {
            persenter.loadByIdData(id);
        }
        if (textPersenter != null)
            textPersenter.loadText(id, EnumConstant.EngegamentTextType.ENGEGAMENT_SUCCESS);
    }


    @Override
    protected void initView() {
        persenter = new EngagementDetailsPersenterImpl();
        persenter.attachView(this);

        Serializable data = getIntent().getSerializableExtra("data");
        if (data == null) {
            id = getIntent().getStringExtra("id");
            persenter.loadByIdData(id);
            tv_sumbit.setVisibility(View.GONE);
            inc_content.setVisibility(View.VISIBLE);
        } else {
            if (data instanceof EngagementDetailsBean.DataBean)
                setData((EngagementDetailsBean.DataBean) data);
            tv_sumbit.setVisibility(View.VISIBLE);
            inc_content.setVisibility(View.GONE);
        }

        actionPersenter = new EngagementActionPersenterImpl();
        actionPersenter.attachView(this);
        textPersenter = new EngagementTextPersenterImpl();
        textPersenter.attachView(this);
        textPersenter.loadText(id, EnumConstant.EngegamentTextType.ENGEGAMENT_SUCCESS);

    }

    @OnClick({R.id.rl_im, R.id.rl_wacth, R.id.rl_pay, R.id.rl_flower, R.id.tv_sumbit})
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
                List<Integer> integers = EngagementUtils.popTextStr(bean.getStatus(), bean.getDating_status_add(), bean.getDating_status_add_msg(), StringUtis.equals(UserInfoManager.getInstance().getUid(), bean.getF_uid()));
                if (integers != null && integers.size() != 0) {
                    showPopUp(rl_flower, integers);
                }
                break;
            case R.id.tv_sumbit:
                ActivityUtils.startEngagementManagerActivity();
                finish();
                break;
        }
        Object tag = view.getTag();
        if (tag != null && tag instanceof String) {
            int code = EngagementUtils.getClickCode((String) tag);
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
        this.bean = bean;
        //发起者
        ImageServerApi.showURLBigImage(mCivUserAvater, bean.getF_face());
        mTvUserName.setText(bean.getF_nickname());
        //约会对象
        ImageServerApi.showURLBigImage(mCivUserAvatered, bean.getT_face());
        mTvUserNameed.setText(bean.getT_nickname());

        mEtInputMoney.setText(bean.getMoney());
        mEtInputAddress.setText(bean.getAddress());
        mEtEngagementMessage.setText(bean.getMsgX());
        String format = TimeUtils.format(StringUtis.string2long(bean.getDate()) * 1000);
        mEtInputDate.setText(format == null ? "" : format.substring(0, format.length() - 3));
        mTvEngagement.setText(EngagementUtils.getEngagementStr(bean.getType()));
        mTvFlower.setText(EngagementUtils.getStatusText(bean.getStatus(), bean.getAppeal_status(), bean.getDating_status_add_msg(), StringUtis.equals(UserInfoManager.getInstance().getUid(), bean.getF_uid())));
        long daojishi = bean.getDaojishi();
        if (daojishi > 0) {
            tv_count_down.setText(daojishi / 3600 + " 时 " + (daojishi % 3600) / 60 + " 分");
        }
    }

    @Override
    public void setEngagementText(String info) {
        mTvEngagementText.setText(info);
    }


}
