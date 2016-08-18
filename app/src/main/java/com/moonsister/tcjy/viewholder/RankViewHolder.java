package com.moonsister.tcjy.viewholder;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.moonsister.tcjy.ImageServerApi;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.base.BaseRecyclerViewHolder;
import com.moonsister.tcjy.bean.DefaultDataBean;
import com.moonsister.tcjy.bean.RankBean;
import com.moonsister.tcjy.find.view.RankFragmentView;
import com.moonsister.tcjy.find.widget.RankFragment;
import com.moonsister.tcjy.main.model.UserActionModelImpl;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.StringUtis;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tcjy.widget.RoundedImageView;

import butterknife.Bind;

/**
 * Created by jb on 2016/8/3.
 */
public class RankViewHolder extends BaseRecyclerViewHolder<RankBean.DataBean> {

    @Bind(R.id.tv_rank)
    TextView tvRank;
    @Bind(R.id.iv_head)
    RoundedImageView ivHead;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_content)
    TextView tvContent;
    @Bind(R.id.tv_focus)
    TextView tvFocus;
    @Bind(R.id.bg_rank)
    ImageView bg_rank;
    private RankFragmentView rankFragmentView;

    public RankViewHolder(View view) {
        super(view);
    }

    @Override
    public void onBindData(RankBean.DataBean dataBean) {

    }

    @Override
    public void onBindData(RankBean.DataBean dataBean, int position) {
        if (dataBean == null)
            return;
        if (position == 0) {
            bg_rank.setImageResource(R.mipmap.icon_medal1);
            tvRank.setText("");
        } else if (position == 1) {
            bg_rank.setImageResource(R.mipmap.icon_medal2);
            tvRank.setText("");
        } else if (position == 2) {
            bg_rank.setImageResource(R.mipmap.icon_medal3);
            tvRank.setText("");
        } else {
            bg_rank.setImageBitmap(null);
            tvRank.setText((position + 1) + "");
        }
        ImageServerApi.showURLImage(ivHead, dataBean.getFace());
        tvName.setText(dataBean.getNickname());
        tvContent.setText(dataBean.getNum() == null ? "0" : dataBean.getNum() + "  " + UIUtils.getStringRes(R.string.experience_gift));
        if (StringUtis.equals(dataBean.getIsfollow(), "1")) {
            tvFocus.setCompoundDrawables(null, null, null, null);
            tvFocus.setText(UIUtils.getStringRes(R.string.already_wacth));
            tvFocus.setTextColor(UIUtils.getResources().getColor(R.color.home_navigation_text_gred));
        } else {
            Drawable drawable = UIUtils.getResources().getDrawable(R.mipmap.add_wacth_icon);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tvFocus.setCompoundDrawables(drawable, null, null, null);
            tvFocus.setText(UIUtils.getStringRes(R.string.wacth));
            tvFocus.setTextColor(UIUtils.getResources().getColor(R.color.home_navigation_text_red));
        }
        tvFocus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rankFragmentView != null)
                    rankFragmentView.showLoading();
                String type = (StringUtis.equals(dataBean.getIsfollow(), "1")) ? "2" : "1";
                UserActionModelImpl userActionModel = new UserActionModelImpl();
                userActionModel.wacthAction(dataBean.getUid(), type, new BaseIModel.onLoadDateSingleListener<DefaultDataBean>() {
                    @Override
                    public void onSuccess(DefaultDataBean bean, BaseIModel.DataType dataType) {
                        if (bean != null) {
                            if (StringUtis.equals(bean.getCode(), "1")) {
                                dataBean.setIsfollow(type);
                                baseRecyclerViewAdapter.onRefresh();
                            } else {
                                if (rankFragmentView != null)
                                    rankFragmentView.transfePageMsg(bean.getMsg());
                            }

                        } else {

                        }

                        if (rankFragmentView != null)
                            rankFragmentView.hideLoading();
                    }

                    @Override
                    public void onFailure(String msg) {
                        if (rankFragmentView != null) {
                            rankFragmentView.hideLoading();
                            rankFragmentView.transfePageMsg(msg);
                        }
                    }
                });

            }
        });

    }

    @Override
    protected void onItemclick(View view, RankBean.DataBean dataBean, int position) {
        if (dataBean == null)
            return;
        ActivityUtils.startDynamicActivity(dataBean.getUid());
    }

    public void setRankFragmentView(RankFragmentView rankFragmentView) {
        this.rankFragmentView = rankFragmentView;
    }
}
