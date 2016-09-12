package com.moonsister.tcjy.viewholder;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.moonsister.tcjy.ImageServerApi;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.ServerApi;
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
import butterknife.OnClick;

/**
 * Created by x on 2016/8/25.
 */
public class FriendlyViewHoler extends BaseRecyclerViewHolder<FrientBaen.DataBean>  {
    @Bind(R.id.riv_user_image)//头像
    RoundedImageView rivUserImage;
    @Bind(R.id.tv_user_name)
    TextView tvUserName;//用户名
    @Bind(R.id.tv_content)
    TextView tvContent;
    @Bind(R.id.delete_textview)//屏蔽
    TextView tvSubmit;
    @Bind(R.id.if_vip)
    ImageView if_vip;
    @Bind(R.id.xin)//屏蔽图片
    ImageView xin;
    String str;
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
        String vip_level = dataBean.getVip_level();
        if(vip_level.equals("0")){
            if_vip.setVisibility(View.INVISIBLE);
        }
        tvSubmit.setTag(position);

            tvSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvSubmit.setTextColor(UIUtils.getResources().getColor(R.color.text_huang));

                    xin.setImageResource(R.mipmap.zuixin);
                    tvSubmit.setText("取消屏蔽");
                    str= (String) tvSubmit.getText();
                    if(str=="取消屏蔽"){
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                tvSubmit.setTextColor(UIUtils.getResources().getColor(R.color.home_navigation_text_gred));
                                xin.setImageResource(R.mipmap.onexin);
                                tvSubmit.setText("屏蔽TA");
                            }
                        });
                    }
//                    adapter.setClick(position);
                }
            });


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

//    @OnClick(R.id.delete_textview)
//    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.delete_textview:
//
//                break;
//        }
//    }
}
