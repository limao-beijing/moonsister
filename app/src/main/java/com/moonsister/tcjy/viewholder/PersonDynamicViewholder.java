package com.moonsister.tcjy.viewholder;


import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.moonsister.tcjy.ImageServerApi;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.bean.UserInfoDetailBean;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.StringUtis;
import com.moonsister.tcjy.utils.UIUtils;

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by jb on 2016/6/25.
 */
public class PersonDynamicViewholder extends BaseHolder<UserInfoDetailBean> {
    @Bind(R.id.user_background)
    ImageView userBackground;
    @Bind(R.id.iv_user_icon)
    ImageView ivUserIcon;
    @Bind(R.id.tv_user_name)
    TextView tvUserName;
    @Bind(R.id.tv_fen_number)
    TextView tvFenNumber;
    @Bind(R.id.tv_wacth_number)
    TextView tvWacthNumber;
    @Bind(R.id.tv_dynamic_number)
    TextView tvDynamicNumber;
    @Bind(R.id.tv_flower_number)
    TextView tvFlowerNumber;
    @Bind(R.id.tv_add_v)
    ImageView tv_add_v;
    //我的页面VIP会员
    @Bind(R.id.vip_money)
    ImageView vip_money;
    @Bind(R.id.tv_user_all_income)//总收入
            TextView tvUserAllIncome;
    @Bind(R.id.tv_user_day_income)//今日收入
            TextView tvUserDayIncome;
    @Override
    protected View initView() {
        View headView = UIUtils.inflateLayout(R.layout.head_person_info);
        return headView;
    }

    @Override
    public void refreshView(UserInfoDetailBean data) {
        if (data == null)
            return;
        UserInfoDetailBean.UserInfoDetailDataBean bean = data.getData();
        if (bean == null)
            return;
        UserInfoDetailBean.UserInfoDetailDataBean.Baseinfo baseinfo = bean.getBaseinfo();
        userBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.startCropImageMainActivity();

            }
        });
            if (baseinfo != null) {
            ImageServerApi.showURLBigImage(userBackground, baseinfo.getLike_image());
            ImageServerApi.showURLSamllImage(ivUserIcon, baseinfo.getFace());
            tvUserName.setText(baseinfo.getNickname());

            String userSex = UserInfoManager.getInstance().getUserSex();
            if (StringUtis.equals(userSex, "1")) {
                Drawable nav_up = UIUtils.getResources().getDrawable(R.mipmap.icon_man);
                nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                tvUserName.setCompoundDrawables(null, null, nav_up, null);
            } else if (StringUtis.equals(userSex, "2")) {
                Drawable nav_up = UIUtils.getResources().getDrawable(R.mipmap.icon_woman);
                nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                tvUserName.setCompoundDrawables(null, null, nav_up, null);
                tvUserName.setCompoundDrawablePadding(10);
            }
            int certificationStatus = UserInfoManager.getInstance().getCertificationStatus();
            if (certificationStatus == 1) {
                tv_add_v.setVisibility(View.VISIBLE);
            } else tv_add_v.setVisibility(View.GONE);



        }

        UserInfoDetailBean.UserInfoDetailDataBean.Addons addons = bean.getAddons();
        if (addons != null) {
            tvUserAllIncome.setText(addons.getIncome_all() + UIUtils.getStringRes(R.string.yuan));
            tvUserDayIncome.setText(addons.getIncome_today() + UIUtils.getStringRes(R.string.yuan));
            tvFenNumber.setText(addons.getUfann());
            tvDynamicNumber.setText(addons.getUlatn());
            tvFlowerNumber.setText(addons.getUflon());
            tvWacthNumber.setText(addons.getUfoln());
        }


    }

    @OnClick({R.id.iv_user_icon,})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_user_icon:
//                R.id.layout_wacth, R.id.layout_fen, R.id.rl_dynamic
                ActivityUtils.startUserInfoChangeActivity();
                break;
//            case R.id.layout_wacth:
//
//                ActivityUtils.startWacthRelationActivity(UserInfoManager.getInstance().getUid());
//                break;
//            case R.id.layout_fen:
//                ActivityUtils.startFenRelationActivity(UserInfoManager.getInstance().getUid());
//                break;
//            case R.id.rl_dynamic:
//                ActivityUtils.startDynamicActivity(UserInfoManager.getInstance().getUid());
//                break;
            case R.id.vip_money:
                //我的页面VIP会员
                int m=UserInfoManager.getInstance().getMemoryPersonInfoDetail().getVipStatus();
                if(m == 1){
                    vip_money.setImageResource(R.mipmap.ifvip);
//                    ActivityUtils.startYesActivity();//是会员跳转充值页面
//                    ActivityUtils.startBuyVipActivity();
                }else{
                    vip_money.setVisibility(View.GONE);
//                    ActivityUtils.startNoActivity();//不是会员跳转认证页面
                    ActivityUtils.startBuyVipActivity();
                }
                break;
        }

    }

    public void upImage(String path) {
        if (userBackground != null) {
            ImageServerApi.showURLBigImage(userBackground, path);
        }
    }
}
