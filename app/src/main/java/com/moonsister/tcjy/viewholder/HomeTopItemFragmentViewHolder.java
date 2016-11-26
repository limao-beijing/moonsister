package com.moonsister.tcjy.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hickey.network.ImageServerApi;
import com.hickey.network.bean.HomeTopItemBean;
import com.hickey.tool.base.BaseRecyclerViewHolder;
import com.hickey.tool.constant.EnumConstant;
import com.hickey.tool.lang.StringUtis;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.utils.ActivityUtils;
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
    @Bind(R.id.iv_dynamic_type_left)
    ImageView iv_dynamic_type_left;
    @Bind(R.id.tv_user_name)
    TextView tv_user_name;
    @Bind(R.id.ll_tag_content)
    LinearLayout llTagContent;
    @Bind(R.id.tv_tags)
    TextView tvTags;
    @Bind(R.id.tv_add_v)
    ImageView tv_add_v;
    @Bind(R.id.tv_fen_number)
    TextView tv_fen_number;
    @Bind(R.id.tv_dynamic_number)
    TextView tv_dynamic_number;
    @Bind(R.id.iv_vip)
    ImageView iv_vip;

    public HomeTopItemFragmentViewHolder(View view) {
        super(view);
    }

    @Override
    public void onBindData(HomeTopItemBean.DataBean dataBean) {
        if (dataBean == null)
            return;
        HomeTopItemBean.DataBean.UinfoBean uinfo1 = dataBean.getUinfo();
//        if (uinfo1.getIsauth() == 1) {
//            tv_add_v.setVisibility(View.VISIBLE);
//        } else {
//            tv_add_v.setVisibility(View.GONE);
//        }
        String level = uinfo1.getVip_level();
        if (StringUtis.equals("1", level)) {
            iv_vip.setImageResource(R.mipmap.vipxiao);
        } else if (StringUtis.equals("3", level)) {
            iv_vip.setImageResource(R.mipmap.vipnext);
        } else if (StringUtis.equals("12", level)) {
            iv_vip.setImageResource(R.mipmap.vipmost);
        } else iv_vip.setImageBitmap(null);

        tv_fen_number.setText(uinfo1.getFansnum());
        tv_dynamic_number.setText(uinfo1.getLatest_total());
        List<HomeTopItemBean.DataBean.ListBean> list = dataBean.getList();
        if (list != null && list.size() >= 2) {
            //left
            HomeTopItemBean.DataBean.ListBean listletfBean = list.get(0);
            HomeTopItemBean.DataBean.ListBean listrightBean = list.get(1);
            ImageServerApi.showURLBigImage(ivDynamicBgLeft, listletfBean.getPic());
            tvContentLeft.setText(listletfBean.getTitle());
            tvWacthNumberLeft.setText(listletfBean.getViews());
//            setClickTODymic(ivDynamicBgLeft, listletfBean.getLid(), listletfBean.getType());
            showDynamicType(iv_dynamic_type_left, listletfBean.getType());
            //right
            ImageServerApi.showURLBigImage(ivDynamicBgRight, listrightBean.getPic());
            tvContentRight.setText(listrightBean.getTitle());
            tvWacthNumberRight.setText(listrightBean.getViews());
//            setClickTODymic(ivDynamicBgRight, listrightBean.getLid(), listrightBean.getType());
            showDynamicType(iv_dynamic_type_right, listrightBean.getType());
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
            String usertags = uinfo.getUsertags();
            if (!StringUtis.isEmpty(usertags) && usertags.contains("|||")) {
                usertags = usertags.replace("|||", "   ");
            }
            tvTags.setText(usertags);
        }


    }

    /**
     * 点击去动态
     *
     * @param view
     * @param dynamicId
     */
    private void setClickTODymic(View view, String dynamicId, int type) {
        if (view == null || StringUtis.isEmpty(dynamicId))
            return;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.startDynamicDatailsActivity(dynamicId, type);
            }
        });

    }

    /**
     * 显示收费标示
     *
     * @param view
     * @param type
     */
    private void showDynamicType(ImageView view, int type) {
        if (type == 0 || view == null)
            return;
        if (type == EnumConstant.DynamicType.FREE_VIDEO.getValue()
                || type == EnumConstant.DynamicType.FREE_VOICE.getValue()
                || type == EnumConstant.DynamicType.FREE_PIC.getValue()) {
            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.VISIBLE);
            if (type == EnumConstant.DynamicType.CHARGE_PIC.getValue()) {
                view.setImageResource(R.mipmap.home_top_pic);
            } else if (type == EnumConstant.DynamicType.CHARGE_VOICE.getValue()) {
                view.setImageResource(R.mipmap.home_top_voice);
            } else {
                view.setImageResource(R.mipmap.home_top_video);
            }
        }

    }

    @Override
    protected void onItemclick(View view, HomeTopItemBean.DataBean dataBean, int position) {
        HomeTopItemBean.DataBean.UinfoBean uinfo = dataBean.getUinfo();
        if (uinfo == null)
            return;
        ActivityUtils.startDynamicActivity(uinfo.getUid());
    }


}
