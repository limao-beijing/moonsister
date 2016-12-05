package com.moonsister.tcjy.viewholder;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hickey.network.ImageServerApi;
import com.hickey.network.bean.BaseBean;
import com.hickey.network.bean.resposen.IndividualResumeBean;
import com.hickey.tool.base.BaseActivity;
import com.hickey.tool.base.BaseIModel;
import com.hickey.tool.lang.StringUtis;
import com.hickey.tool.widget.UIUtils;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.main.model.UserActionModelImpl;
import com.moonsister.tcjy.main.widget.IndividualResumeActivity;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.widget.RoundedImageView;

import butterknife.Bind;
import butterknife.OnClick;
import im.gouyin.com.progressdialog.AlearDialog;

import static com.moonsister.tcjy.R.id.if_user_vip;
import static com.moonsister.tcjy.R.id.im_user_vip;
import static com.moonsister.tcjy.R.id.iv_user_most;

/**
 * Created by jb on 2016/12/1.
 */
public class IndividualResumeViewHolder extends BaseHolder<IndividualResumeBean> {
    @Bind(R.id.iv_user_icon)
    RoundedImageView iv_user_icon;
    @Bind(if_user_vip)
    ImageView mIfUserVip;
    @Bind(R.id.iv_user_name)
    TextView mIvUserName;
    @Bind(im_user_vip)
    ImageView mImUserVip;
    @Bind(R.id.iv_user_work)
    TextView mIvUserWork;
    @Bind(R.id.im_user_sex)
    ImageView mImUserSex;
    @Bind(R.id.iv_user_age)
    TextView mIvUserAge;
    @Bind(iv_user_most)
    TextView mIvUserMost;
    @Bind(R.id.tv_weixin_show)
    TextView mTvWeixinShow;
    @Bind(R.id.tv_qq_show)
    TextView mTvQqShow;
    @Bind(R.id.tv_phone_show)
    TextView mTvPhoneShow;
    @Bind(R.id.tv_data_info)
    TextView mTvDataInfo;
    @Bind(R.id.tv_data_love)
    TextView mTvDataLove;
    @Bind(R.id.tv_impression_info)
    TextView mTvImpressionInfo;
    @Bind(R.id.tv_wacth)
    TextView tv_wacth;
    private final Context mContext;
    private IndividualResumeBean data;
    private BaseActivity mActivity;

    public IndividualResumeViewHolder(Context context) {
        super();
        this.mContext = context;
        mActivity = (BaseActivity) mContext;
    }

    @Override
    protected View initView() {
        return UIUtils.inflateLayout(R.layout.activity_individual_resume);
    }

    @Override
    public void refreshView(IndividualResumeBean data) {
        this.data = data;
        ImageServerApi.showURLSamllImage(iv_user_icon, data.getFace());
        mIvUserName.setText(data.getNickname());
        mImUserSex.setImageResource(data.getSex() == 1 ? R.mipmap.nan : R.mipmap.gril);
        mIvUserWork.setText(data.getProfession());
        mIvUserMost.setText(data.getSignature());
        mTvDataInfo.setText(data.getAddinfo_a1());
        mTvDataLove.setText(data.getAddinfo_a2());
        mTvImpressionInfo.setText(data.getAddinfo_a3());
        mIvUserAge.setText(data.getAge());

        String follow = data.getFollow();
        boolean equals = StringUtis.equals(follow, "1");
        Drawable drawable = UIUtils.getResources().getDrawable(!equals ? R.mipmap.resume_wacth : R.mipmap.resume_wacthed);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        tv_wacth.setCompoundDrawables(drawable, null, null, null);
        tv_wacth.setText(UIUtils.getStringRes(equals ? R.string.wacth : R.string.not_wacth));
        if (data.getSex() == 1) {
            int level = data.getVip_level();
            if (level == 0) {
                mImUserVip.setVisibility(View.INVISIBLE);
                mImUserVip.setVisibility(View.INVISIBLE);
            } else if (level == 1) {
                mImUserVip.setVisibility(View.VISIBLE);
                mImUserVip.setImageResource(R.mipmap.vipxiao);
            } else if (level == 3) {
                mImUserVip.setVisibility(View.VISIBLE);
                mImUserVip.setImageResource(R.mipmap.vipnext);
            } else if (level == 12) {
                mImUserVip.setVisibility(View.VISIBLE);
                mImUserVip.setImageResource(R.mipmap.vipmost);
            }
        } else {
            if (data.getIsauth() == 1) {
                mImUserVip.setVisibility(View.VISIBLE);
                mImUserVip.setImageResource(R.mipmap.individual_renzheng);
            } else {
                mImUserVip.setVisibility(View.GONE);
            }

        }
    }

    @OnClick({R.id.iv_look_weixin, R.id.iv_look_qq, R.id.iv_look_phone, R.id.rl_reward, R.id.rl_im, R.id.rl_wacth, R.id.rl_pay, R.id.rl_flower, R.id.rl_engagement})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.iv_look_weixin:
                if (data != null && checkPermission())
                    mTvWeixinShow.setText(data.getWeixin());
                break;
            case R.id.iv_look_qq:
                if (data != null && checkPermission())
                    mTvQqShow.setText(data.getQq());
                break;
            case R.id.iv_look_phone:
                if (data != null && checkPermission())
                    mTvPhoneShow.setText(data.getSmobile());
                break;

            case R.id.rl_reward:

                break;
            case R.id.rl_im:
                ActivityUtils.startHomePageActivity(data.getUid());
                break;
            case R.id.rl_wacth:
                wacthUser();
                break;
            case R.id.rl_pay:
                if (data != null)
                    ActivityUtils.startAppConversationActivity(data.getUid(), data.getNickname(), data.getFace());
//                ActivityUtils.startRedpacketActivity(data.getUid(), RedpacketAcitivity.RedpacketType.TYPE_REDPACKET, userInfo.getData().getBaseinfo().getFace());
                break;
            case R.id.rl_flower:
                mActivity.showToast("举报成功");
                break;
            case R.id.rl_engagement:
                ActivityUtils.startPersonEngagementTypeActivity(data.getUid(), data.getNickname(), data.getFace());
                break;
        }

    }

    private boolean checkPermission() {
        boolean isPermission = false;
        if (mContext != null && mContext instanceof IndividualResumeActivity) {
            isPermission = isVip_level();
        }
        return isPermission;
    }

    private void wacthUser() {
        if (StringUtis.isEmpty(data.getUid()) || data == null)
            return;
        UserActionModelImpl model = new UserActionModelImpl();
        String follow = StringUtis.equals(data.getFollow(), "1") ? "2" : "1";

        model.wacthAction(data.getUid(), follow, new BaseIModel.onLoadDateSingleListener<BaseBean>() {
            @Override
            public void onSuccess(BaseBean bean, BaseIModel.DataType dataType) {
                boolean equals = StringUtis.equals(follow, "1");
                if (bean != null && StringUtis.equals(bean.getCode(), "1")) {
                    Drawable drawable = UIUtils.getResources().getDrawable(!equals ? R.mipmap.resume_wacth : R.mipmap.resume_wacthed);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tv_wacth.setCompoundDrawables(drawable, null, null, null);
                    tv_wacth.setText(UIUtils.getStringRes(equals ? R.string.wacth : R.string.not_wacth));
                    mActivity.showToast(bean.getMsg());
                    data.setFollow(follow);
                }
            }

            @Override
            public void onFailure(String msg) {
                mActivity.showToast(msg);
                mActivity.hideLoading();
            }
        });

    }

    /**
     * @return
     */
    public boolean isVip_level() {
        if (data == null || data.getVip_level() != 1) {
            AlearDialog dialog = new AlearDialog(AlearDialog.DialogType.Certification_vip, mActivity);
            dialog.setListenter(new AlearDialog.onClickListenter() {
                @Override
                public void clickType(AlearDialog.clickType type) {
                    if (type == AlearDialog.clickType.confirm)
                        ActivityUtils.startBuyVipActivity();
                    dialog.dismiss();
                }
            });
            return false;
        }
        return true;
    }
}
