package com.moonsister.tcjy.viewholder;

import android.support.annotation.IdRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hickey.network.ImageServerApi;
import com.hickey.network.bean.UserInfoDetailBean;
import com.hickey.tool.lang.StringUtis;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tcjy.widget.RoundedImageView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by jb on 2016/9/2.
 */
public class HomePageHeadHolder extends BaseHolder<UserInfoDetailBean> {
    @Bind(R.id.iv_bg)
    ImageView mIvBg;
    @Bind(R.id.iv_back)
    ImageView mIvBack;
    @Bind(R.id.tv_more)
    TextView mTvMore;
    @Bind(R.id.riv_user_image)
    RoundedImageView mRivUserImage;
    @Bind(R.id.iv_add_v)
    ImageView mIvAddV;
    @Bind(R.id.tv_user_name)
    TextView mTvUserName;
    @Bind(R.id.tv_job)
    TextView mTvJob;
    @Bind(R.id.iv_sex)
    ImageView mIvSex;
    @Bind(R.id.tv_age)
    TextView mTvAge;
    @Bind(R.id.tv_tags)
    TextView mTvTags;
    @Bind(R.id.tv_signature)
    TextView mTvSignature;
    @Bind(R.id.tv_wacth_number)
    TextView mTvWacthNumber;
    @Bind(R.id.tv_fen_number)
    TextView mTvFenNumber;
    @Bind(R.id.tv_all)
    TextView mTvAll;
    @Bind(R.id.rl_all)
    RelativeLayout mRlAll;
    @Bind(R.id.tv_type_user)
    TextView mTvTypeUser;
    @Bind(R.id.rl_user)
    RelativeLayout mRlUser;
    @Bind(R.id.tv_type_dynamic)
    TextView mTvTypeDynamic;
    @Bind(R.id.rl_dynamic)
    RelativeLayout mRlDynamic;
    @Bind(R.id.view_all_line)
    View mViewAllLine;
    @Bind(R.id.view_user_line)
    View mViewUserLine;
    @Bind(R.id.view_dynamic_line)
    View mViewDynamicLine;
    @Bind(R.id.center_line)
    View mCenterLine;
    @Bind(R.id.rl_top)
    RelativeLayout mRlTop;
    @Bind(R.id.iv_vip)
    ImageView iv_vip;

    @Override
    protected View initView() {
        return UIUtils.inflateLayout(R.layout.holder_home_page_head);
    }

    @Override
    public void refreshView(UserInfoDetailBean bean) {
        if (bean == null || bean.getData() == null)
            return;
        UserInfoDetailBean.UserInfoDetailDataBean data = bean.getData();
        UserInfoDetailBean.UserInfoDetailDataBean.Addons addons = data.getAddons();
        UserInfoDetailBean.UserInfoDetailDataBean.Baseinfo baseinfo = data.getBaseinfo();
        ImageServerApi.showURLBigImage(mIvBg, baseinfo.getLikeImage());
        ImageServerApi.showURLSamllImage(mRivUserImage, baseinfo.getFace());
        mTvUserName.setText(baseinfo.getNickname());
        mTvFenNumber.setText(addons.getUfann());
        mTvAll.setText("全部 ( " + baseinfo.getLatest_total() + " )");
        mTvTypeUser.setText("开放 ( " + baseinfo.getLatest_free() + " )");
        mTvTypeDynamic.setText("私密 ( " + baseinfo.getLatest_vip() + " )");
        if (StringUtis.isEmpty(baseinfo.getProfession())) {
            mTvJob.setVisibility(View.GONE);
        } else {
            mTvJob.setVisibility(View.VISIBLE);
            mTvJob.setText(baseinfo.getProfession());
        }
        if (baseinfo.getSex() == 1) {
            mIvSex.setImageResource(R.mipmap.boy);
        } else {
            mIvSex.setImageResource(R.mipmap.gril);
        }
        String level = baseinfo.getVip_level();
        if (StringUtis.equals("1", level)) {
            iv_vip.setImageResource(R.mipmap.vipxiao);
        } else if (StringUtis.equals("3", level)) {
            iv_vip.setImageResource(R.mipmap.vipnext);
        } else if (StringUtis.equals("12", level)) {
            iv_vip.setImageResource(R.mipmap.vipmost);
        } else iv_vip.setImageBitmap(null);

        mTvAge.setText(baseinfo.getAge());
        mTvTags.setText(baseinfo.getTags().replace("|||", "   "));
        mTvSignature.setText(baseinfo.getSignature());
//        tvFlowerNumber.setText(addons.getUflon());
        mTvWacthNumber.setText(addons.getUfoln());
//        getwacthStatus(data.getFollow(), data.getUid());
//        mRivUserImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ActivityUtils.startUserinfoActivity(baseinfo.getUid());
//            }
//        });
        mTvWacthNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.startWacthRelationActivity(baseinfo.getUid());
            }
        });
        mTvFenNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.startFenRelationActivity(baseinfo.getUid());
            }
        });
        if (StringUtis.equals(data.getBaseinfo().getIsverify(), "1")) {
            mIvAddV.setVisibility(View.VISIBLE);
        } else mIvAddV.setVisibility(View.GONE);
        selectColor(R.id.rl_all);
        if (StringUtis.equals(UserInfoManager.getInstance().getUid(), baseinfo.getUid())) {
            mTvMore.setText(UIUtils.getStringRes(R.string.setting));
        } else {
            mTvMore.setText(UIUtils.getStringRes(R.string.more));
        }
        mTvMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringUtis.equals(UserInfoManager.getInstance().getUid(), baseinfo.getUid())) {
                    ActivityUtils.startPersonalReviseActivity();
                } else {
                    ActivityUtils.startPersonalActivity(baseinfo.getUid());

                }
            }
        });

    }

    private void selectColor(@IdRes int id) {
        int yellow = UIUtils.getResources().getColor(R.color.yellow_ff8201);
        int transparent = UIUtils.getResources().getColor(R.color.transparent);
        mTvAll.setSelected(id == R.id.rl_all);
        mTvTypeUser.setSelected(id == R.id.rl_user);
        mTvTypeDynamic.setSelected(id == R.id.rl_dynamic);

        mViewAllLine.setBackgroundColor((id == R.id.rl_all) ? yellow : transparent);
        mViewUserLine.setBackgroundColor((id == R.id.rl_user) ? yellow : transparent);
        mViewDynamicLine.setBackgroundColor((id == R.id.rl_dynamic) ? yellow : transparent);


    }

    @OnClick({R.id.rl_all, R.id.rl_user, R.id.rl_dynamic, R.id.iv_back})
    public void onClick(View view) {
        selectColor(view.getId());

        if (mListener != null)
            mListener.onClick(view);
    }

    private OnClickListener mListener;

    public void setOnClickListener(OnClickListener listener) {
        this.mListener = listener;
    }

    public interface OnClickListener {
        void onClick(View view);
    }
}
