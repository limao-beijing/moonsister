package com.moonsister.tcjy.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.moonsister.tcjy.ImageServerApi;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseRecyclerViewHolder;
import com.moonsister.tcjy.bean.HomeTopItemBean;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.ConfigUtils;
import com.moonsister.tcjy.utils.StringUtis;
import com.moonsister.tcjy.widget.RoundedImageView;

import java.util.List;

import butterknife.Bind;

/**
 * Created by jb on 2016/8/24.
 */
public class HomeTopItemFragmentViewHolder extends BaseRecyclerViewHolder<HomeTopItemBean.DataBean> {
    @Bind(R.id.iv_dynamic_bg_left)
    ImageView ivDynamicBgLeft;
    @Bind(R.id.tv_wacth_number_left)
    TextView tvWacthNumberLeft;
    @Bind(R.id.tv_content_left)
    TextView tvContentLeft;
    @Bind(R.id.tv_wacth_number_right)
    TextView tvWacthNumberRight;
    @Bind(R.id.iv_dynamic_bg_right)
    ImageView ivDynamicBgRight;
    @Bind(R.id.tv_content_right)
    TextView tvContentRight;
    @Bind(R.id.rl_dynamic)
    RelativeLayout rlDynamic;
    @Bind(R.id.riv_user_image)
    RoundedImageView rivUserImage;
    @Bind(R.id.tv_job)
    TextView tvJob;
    @Bind(R.id.iv_sex)
    ImageView ivSex;
    @Bind(R.id.tv_age)
    TextView tvAge;
    @Bind(R.id.iv_dynamic_type_right)
    ImageView iv_dynamic_type_right;
    @Bind(R.id.tv_user_name)
    TextView tv_user_name;
    @Bind(R.id.ll_tag_content)
    LinearLayout llTagContent;

    public HomeTopItemFragmentViewHolder(View view) {
        super(view);
    }

    @Override
    public void onBindData(HomeTopItemBean.DataBean dataBean) {
        if (dataBean == null)
            return;
        List<HomeTopItemBean.DataBean.ListBean> list = dataBean.getList();
        if (list != null && list.size() >= 2) {
            //left
            HomeTopItemBean.DataBean.ListBean listletfBean = list.get(0);
            HomeTopItemBean.DataBean.ListBean listrightBean = list.get(1);
            ImageServerApi.showURLBigImage(ivDynamicBgLeft, listletfBean.getPic());
            tvContentLeft.setText(listletfBean.getTitle());
            tvWacthNumberLeft.setText(listletfBean.getViews());
            //right
            ImageServerApi.showURLBigImage(ivDynamicBgRight, listrightBean.getPic());
            tvContentRight.setText(listrightBean.getTitle());
            tvWacthNumberRight.setText(listrightBean.getViews());
        }
        HomeTopItemBean.DataBean.UinfoBean uinfo = dataBean.getUinfo();
        if (uinfo != null) {
            ImageServerApi.showURLSamllImage(rivUserImage, uinfo.getFace());
            tv_user_name.setText(uinfo.getNickname());
            if (StringUtis.equals(uinfo.getSex(), "1")) {
                ivSex.setImageResource(R.mipmap.boy);
            } else {
                ivSex.setImageResource(R.mipmap.gril);
            }
            tvJob.setText(uinfo.getProfession());
            tvAge.setText(uinfo.getAge());
            setUserTag(uinfo.getUsertags());
        }

    }

    @Override
    protected void onItemclick(View view, HomeTopItemBean.DataBean dataBean, int position) {
//        ActivityUtils.startDynamicActivity(dataBean.getId());
    }

    public void setUserTag(String userTag) {
        if (llTagContent == null)
            return;
        if (!StringUtis.isEmpty(userTag) && userTag.contains("|||")) {
            String[] split = userTag.split("|||");
            if (split != null) {
                for (int i = 0; i < split.length; i++) {
                    TextView view = new TextView(ConfigUtils.getInstance().getApplicationContext());
                    view.setText(split[i]);
                    llTagContent.addView(view);
                }
            }
        }
    }
}
