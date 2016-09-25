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
import com.moonsister.tcjy.widget.RoundImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by jb on 2016/9/24.
 */
public class OnlineFragmentAdapterViewHolder extends BaseRecyclerViewHolder<HomeThreeFragmentBean.DataBean> {
    private ArrayList<String> pics;
    @Bind(R.id.riv_avater)
    RoundImageView riv_avater;
    @Bind(R.id.tv_user_name)
    TextView tv_user_name;
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
    @Bind(R.id.iv_pic_one)
    ImageView mIvPicOne;
    @Bind(R.id.iv_pic_two)
    ImageView mIvPicTwo;
    @Bind(R.id.iv_pic_three)
    ImageView mIvPicThree;
    @Bind(R.id.ll_pic)
    LinearLayout mLlPic;

    public OnlineFragmentAdapterViewHolder(View view) {
        super(view);
    }

    @Override
    public void onBindData(HomeThreeFragmentBean.DataBean bean) {
        ImageServerApi.showURLSamllImage(riv_avater, bean.getFace());
        mTvAge.setText(bean.getAge());
        mTvHeight.setText(bean.getHeight());
        tv_user_name.setText(bean.getNickname());
        mIvAddVip.setVisibility(StringUtis.equals("1", bean.getVip_level()) ? View.VISIBLE : View.GONE);
        List<HomeThreeFragmentBean.DataBean.ShowlistBean> showlist = bean.getShowlist();
        if (showlist != null) {
            for (int i = 0; i < showlist.size(); i++) {
                if (i == 0) {
                    ImageServerApi.showURLImage(mIvPicOne, showlist.get(i).getS());
                }
                if (i == 1) {
                    ImageServerApi.showURLImage(mIvPicTwo, showlist.get(i).getS());
                }
                if (i == 2) {
                    ImageServerApi.showURLImage(mIvPicThree, showlist.get(i).getS());
                }
                if (pics == null) {
                    pics = new ArrayList<>();
                }
                if (i <= 2)
                    pics.add(showlist.get(i).getL());
            }
        }
    }

    @Override
    protected void onItemclick(View view, HomeThreeFragmentBean.DataBean bean, int position) {
        ActivityUtils.startPersonalActivity(bean.getUid());
    }
}
