package com.moonsister.tcjy.viewholder.homepage;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.moonsister.tcjy.ImageServerApi;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.adapter.HomePageFragmentAdapter;
import com.moonsister.tcjy.base.BaseRecyclerViewHolder;
import com.moonsister.tcjy.bean.DynamicItemBean;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.ConfigUtils;
import com.moonsister.tcjy.utils.StringUtis;
import com.moonsister.tcjy.utils.TimeUtils;
import com.moonsister.tcjy.widget.RoundedImageView;
import com.moonsister.tcjy.widget.speak.VoicePlay;

import butterknife.Bind;

/**
 * Created by jb on 2016/9/1.
 */
public class HomePageVideoViewHolder extends BaseRecyclerViewHolder<DynamicItemBean> {
    @Bind(R.id.riv_user_image)
    RoundedImageView rivUserImage;
    @Bind(R.id.tv_user_name)
    TextView tvUserName;
    @Bind(R.id.iv_sex_icon)
    ImageView ivSexIcon;
    @Bind(R.id.tv_sex)
    TextView tvSex;
    @Bind(R.id.tv_user_tag)
    TextView tvUserTag;
    @Bind(R.id.dynamic_content)
    TextView dynamicContent;
    @Bind(R.id.tv_home_page_comment)
    TextView tvHomePageComment;
    @Bind(R.id.tv_home_page_pay)
    TextView tvHomePagePay;
    @Bind(R.id.tv_home_page_control)
    TextView tvHomePageControl;
    @Bind(R.id.tv_user_like)
    TextView tvUserLike;
    @Bind(R.id.iv_bg)
    ImageView ivBg;
    @Bind(R.id.iv_play)
    ImageView iv_play;
    @Bind(R.id.tv_time)
    TextView tv_time;
    @Bind(R.id.iv_add_v)
    ImageView iv_add_v;

    public HomePageVideoViewHolder(View view) {
        super(view);
    }

    @Override
    public void onBindData(DynamicItemBean dynamicItemBean) {
        if (dynamicItemBean == null)
            return;
        ImageServerApi.showURLSamllImage(rivUserImage, dynamicItemBean.getFace());
        tvUserName.setText(dynamicItemBean.getNickname());
        String sex = dynamicItemBean.getSex();
        if (StringUtis.equals(sex, "1")) {
            ivSexIcon.setImageResource(R.mipmap.boy);
        } else
            ivSexIcon.setImageResource(R.mipmap.gril);
        tvSex.setText(dynamicItemBean.getAge());
        String tags = dynamicItemBean.getTags();
        if (!StringUtis.isEmpty(tags)) {
            tags = tags.replace("|||", "  ");
        }
        iv_add_v.setVisibility(StringUtis.equals(dynamicItemBean.getIsauth(), "1") ? View.VISIBLE : View.GONE);
        tv_time.setText(TimeUtils.getDynamicTimeString(dynamicItemBean.getCreate_time()));
        tvUserTag.setText(tags);
        dynamicContent.setText(dynamicItemBean.getTitle());
        tvHomePageComment.setText(dynamicItemBean.getLcomn());
        tvHomePagePay.setText(dynamicItemBean.getMoney());
        tvUserLike.setText(dynamicItemBean.getLupn());
        ImageServerApi.showURLBigImage(ivBg, dynamicItemBean.getVimg());
        int type = dynamicItemBean.getType();
        if (type == HomePageFragmentAdapter.TYPE_CHARGE_VIDEO || type == HomePageFragmentAdapter.TYPE_FREE_VIDEO) {
            iv_play.setImageResource(R.mipmap.home_page_video_play);
        } else {
            iv_play.setImageResource(R.mipmap.home_page_voice);
        }
        iv_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringUtis.equals(dynamicItemBean.getIspay(), "2")) {
                    ActivityUtils.startPayDynamicRedPackketActivity(dynamicItemBean.getMoney(), dynamicItemBean.getLatest_id());
                } else {
                    if (type == HomePageFragmentAdapter.TYPE_CHARGE_VIDEO || type == HomePageFragmentAdapter.TYPE_FREE_VIDEO) {
                        ActivityUtils.startShowShortVideoActivity(dynamicItemBean.getVideo());
                    } else {
                        VoicePlay voicePlay = new VoicePlay();
                        voicePlay.playVoice(ConfigUtils.getInstance().getApplicationContext(), dynamicItemBean.getVideo());
                    }
                }
            }
        });


    }

    @Override
    protected void onItemclick(View view, DynamicItemBean dynamicItemBean, int position) {
        ActivityUtils.startDynamicDatailsActivity(dynamicItemBean.getLatest_id(), dynamicItemBean.getType());
    }


}
