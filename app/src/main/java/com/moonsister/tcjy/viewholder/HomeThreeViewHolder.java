package com.moonsister.tcjy.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moonsister.tcjy.ImageServerApi;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseRecyclerViewHolder;
import com.moonsister.tcjy.bean.HomeThreeFragmentBean;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.StringUtis;
import com.moonsister.tcjy.widget.RoundedImageView;

import butterknife.Bind;

/**
 * Created by jb on 2016/9/24.
 */
public class HomeThreeViewHolder extends BaseRecyclerViewHolder<HomeThreeFragmentBean.DataBean> {
    @Bind(R.id.riv_avater)
    RoundedImageView mRivAvater;
    @Bind(R.id.tv_user_name)
    TextView mTvUserName;
    @Bind(R.id.iv_add_vip)
    ImageView mIvAddVip;
    @Bind(R.id.tv_age)
    TextView mTvAge;
    @Bind(R.id.tv_height)
    TextView mTvHeight;
    @Bind(R.id.tv_address)
    TextView mTvAddress;
    @Bind(R.id.ll_user_info)
    LinearLayout mLlUserInfo;
    @Bind(R.id.tv_signatuer)
    TextView mTvSignatuer;
    @Bind(R.id.iv_phone)
    ImageView mIvPhone;
    @Bind(R.id.iv_qq)
    ImageView mIvQq;
    @Bind(R.id.iv_winxin)
    ImageView mIvWinxin;
    @Bind(R.id.ll_user_bind)
    LinearLayout ll_user_bind;

    public HomeThreeViewHolder(View view) {
        super(view);
    }

    @Override
    public void onBindData(HomeThreeFragmentBean.DataBean bean) {
        ImageServerApi.showURLImage(mRivAvater, bean.getFace());
        mTvUserName.setText(bean.getNickname());
        mTvAge.setText(bean.getAge());
        mTvHeight.setText(bean.getHeight());
        mTvSignatuer.setText(bean.getSignature());
        isViewShow(mIvQq, "1", bean.getQq());
        isViewShow(mIvPhone, "1", bean.getSmobile());
        isViewShow(mIvWinxin, "1", bean.getWeixin());

        if (!mIvQq.isShown() && !mIvPhone.isShown() && !mIvWinxin.isShown()) {
            ll_user_bind.setVisibility(View.GONE);
        } else
            ll_user_bind.setVisibility(View.VISIBLE);
        isViewShow(mIvAddVip, "1", bean.getVip_level());

    }

    @Override
    protected void onItemclick(View view, HomeThreeFragmentBean.DataBean bean, int position) {
        ActivityUtils.startPersonalActivity(bean.getUid());

    }

    private void isViewShow(View view, String tag, String value) {
        if (StringUtis.equals(tag, value)) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }

    }

}
