package com.moonsister.tcjy.viewholder;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.moonsister.tcjy.ImageServerApi;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.adapter.FriendAdapter;
import com.moonsister.tcjy.adapter.FriendlyAdapter;
import com.moonsister.tcjy.base.BaseRecyclerViewHolder;
import com.moonsister.tcjy.bean.FrientBaen;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.ConfigUtils;
import com.moonsister.tcjy.utils.StringUtis;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tcjy.widget.RoundedImageView;

import butterknife.Bind;

/**
 * Created by x on 2016/8/25.
 */
public class FriendlyViewHoler extends BaseRecyclerViewHolder<FrientBaen.DataBean> {
    @Bind(R.id.riv_user_image)
    RoundedImageView rivUserImage;
    @Bind(R.id.tv_user_name)
    TextView tvUserName;
    @Bind(R.id.tv_content)
    TextView tvContent;
    @Bind(R.id.delete_textview)
    TextView tvSubmit;
    @Bind(R.id.xin)
    ImageView xin;
    //    @Bind(R.id.imageView)
//    ImageView mImageView;//提示用户是否为网红的图标
    private FriendlyAdapter adapter;
    public FriendlyViewHoler(View view) {
        super(view);
    }

    @Override
    public void onBindData(FrientBaen.DataBean dataBean) {

    }

    @Override
    public void onBindData(FrientBaen.DataBean dataBean,int position) {

        ImageServerApi.showURLSamllImage(rivUserImage, dataBean.getFace());
        tvContent.setText(dataBean.getSignature());
        tvUserName.setText(dataBean.getNickname());
        tvSubmit.setTag(position);
        //判断是否是新好友，1为新好友，2则不是
//        if(StringUtis.equals(dataBean.getIsnew(),"1")){
//            mImageView.setVisibility(View.VISIBLE);//是新好友则显示新好友图标，提醒用户
//        }else{
//            mImageView.setVisibility(View.INVISIBLE);//不是新好友则隐藏提示
//        }
        if (StringUtis.equals(dataBean.getIsfollow(), "1")) {//关注则1，未关注则2
//            Drawable drawable = UIUtils.getResources().getDrawable(R.mipmap.delect_wacth);
//            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
//            tvSubmit.setCompoundDrawables(null, null, null, null);
//            tvSubmit.setText(UIUtils.getStringRes(R.string.already_wacth));
//            tvSubmit.setText(UIUtils.getStringRes(R.string.delete_wacth));
//            tvSubmit.setText(UIUtils.getStringRes(R.string.pingbi));
//            tvSubmit.setTextColor(UIUtils.getResources().getColor(R.color.home_navigation_text_gred));

            tvSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIUtils.showToast(ConfigUtils.getInstance().getActivityContext(), UIUtils.getStringRes(R.string.already) + UIUtils.getStringRes(R.string.together) + UIUtils.getStringRes(R.string.wacth));
                    tvSubmit.setTextColor(UIUtils.getResources().getColor(R.color.home_navigation_text_gred));
                    xin.setImageResource(R.mipmap.zuixin);
                    tvSubmit.setText("取消屏蔽");
                }
            });

        } else if (StringUtis.equals(dataBean.getIsfollow(), "2")) {
//            Drawable drawable = UIUtils.getResources().getDrawable(R.mipmap.add_wacth_icon);
//            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
//            tvSubmit.setCompoundDrawables(drawable, null, null, null);
            tvSubmit.setText(UIUtils.getStringRes(R.string.pingbi));
//            tvSubmit.setTextColor(UIUtils.getResources().getColor(R.color.home_navigation_text_red));
            tvSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvSubmit.setTextColor(UIUtils.getResources().getColor(R.color.text_huang));
                    xin.setImageResource(R.mipmap.zuixin);
                    tvSubmit.setText("取消屏蔽");
//                    adapter.setClick(position);
                }
            });
        } else {
            tvSubmit.setTextColor(UIUtils.getResources().getColor(R.color.home_navigation_text_gred));
            tvSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvSubmit.setText("取消屏蔽");
                    tvSubmit.setTextColor(UIUtils.getResources().getColor(R.color.home_navigation_text_gred));
                    xin.setImageResource(R.mipmap.zuixin);

//                    adapter.setClick(position);
                }
            });
        }

        /// 这一步必须要做,否则不会显示.


//        rivUserImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ActivityUtils.startUserinfoActivity(dataBean.getUid());
//            }
//        });
    }

    @Override
    protected void onItemclick(View view, FrientBaen.DataBean dataBean, int position) {
        ActivityUtils.startDynamicActivity(dataBean.getUid());
    }

    public void setAdapter(FriendlyAdapter adapter) {
        this.adapter = adapter;
    }
}
