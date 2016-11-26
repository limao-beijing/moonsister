package com.moonsister.tcjy.viewholder.homepage;

import android.view.View;
import android.widget.ImageView;

import com.hickey.network.ImageServerApi;
import com.hickey.network.bean.DynamicItemBean;
import com.hickey.tool.lang.StringUtis;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.adapter.HomePageFragmentAdapter;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.hickey.tool.ConfigUtils;
import com.moonsister.tcjy.widget.speak.VoicePlay;

import butterknife.Bind;

/**
 * Created by jb on 2016/9/1.
 */
public class HomePageVideoViewHolder extends BaseHomePageViewHolder {

    @Bind(R.id.iv_bg)
    ImageView ivBg;
    @Bind(R.id.iv_play)
    ImageView iv_play;

    public HomePageVideoViewHolder(View view) {
        super(view);
    }

    @Override
    public void onBindData(DynamicItemBean dynamicItemBean) {
        super.onBindData(dynamicItemBean);
        if (dynamicItemBean == null)
            return;

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

}
