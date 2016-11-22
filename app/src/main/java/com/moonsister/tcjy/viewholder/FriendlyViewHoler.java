package com.moonsister.tcjy.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hickey.network.ImageServerApi;
import com.hickey.network.bean.DefaultDataBean;
import com.hickey.network.bean.FrientBaen;
import com.hickey.tool.lang.StringUtis;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.adapter.FriendlyAdapter;
import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.base.BaseRecyclerViewHolder;
import com.moonsister.tcjy.main.model.UserActionModelImpl;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tcjy.widget.RoundedImageView;

import butterknife.Bind;

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
        String uid = dataBean.getUid();
        UserActionModelImpl model = new UserActionModelImpl();
        if (baseIView != null)
            baseIView.showLoading();
        ImageServerApi.showURLSamllImage(rivUserImage, dataBean.getFace());
        tvContent.setText(dataBean.getSignature());
        tvUserName.setText(dataBean.getNickname());
        String vip_level = dataBean.getVip_level();
        if(vip_level.equals("0")){
            if_vip.setVisibility(View.INVISIBLE);
        }

        String isshield = dataBean.getIsshield();
        if(isshield.equals("1")){
            tvSubmit.setTextColor(UIUtils.getResources().getColor(R.color.text_huang));

            xin.setImageResource(R.mipmap.zuixin);
            tvSubmit.setText("取消屏蔽");
            tvSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String type="2";
                    model.uppingbi(type,uid, new BaseIModel.onLoadDateSingleListener<DefaultDataBean>() {
                        @Override
                        public void onSuccess(DefaultDataBean bean, BaseIModel.DataType dataType) {

                            if (baseIView != null) {
                                baseIView.hideLoading();
                            }
                            if (StringUtis.equals(bean.getCode(), "1")) {
                                if (baseRecyclerViewAdapter != null) {
                                    baseRecyclerViewAdapter.delectSingleItme(position);
                                }
                            }
                            if (baseIView != null) {
                                baseIView.transfePageMsg(bean.getMsg());
                            }


                        }

                        @Override
                        public void onFailure(String msg) {
                            if (baseIView != null) {
                                baseIView.transfePageMsg(msg);
                            }
                        }

                    });
                }
            });
        }else{
            tvSubmit.setTextColor(UIUtils.getResources().getColor(R.color.home_navigation_text_gred));
            xin.setImageResource(R.mipmap.onexin);
            tvSubmit.setText("屏蔽TA");
            String type="1";
            tvSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    model.uppingbi(type,uid, new BaseIModel.onLoadDateSingleListener<DefaultDataBean>() {
                        @Override
                        public void onSuccess(DefaultDataBean bean, BaseIModel.DataType dataType) {
                            tvSubmit.setTextColor(UIUtils.getResources().getColor(R.color.text_huang));

                            xin.setImageResource(R.mipmap.zuixin);
                            tvSubmit.setText("取消屏蔽");
                            if (baseIView != null) {
                                baseIView.hideLoading();
                            }
                            if (StringUtis.equals(bean.getCode(), "1")) {
                                if (baseRecyclerViewAdapter != null) {
                                    baseRecyclerViewAdapter.delectSingleItme(position);
                                }
                            }
                            if (baseIView != null) {
                                baseIView.transfePageMsg(bean.getMsg());
                            }

                        }

                        @Override
                        public void onFailure(String msg) {
                            if (baseIView != null) {
                                baseIView.transfePageMsg(msg);
                            }
                        }
                    });

                }
            });
        }
        tvSubmit.setTag(position);
    }

//        tvSubmit.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    tvSubmit.setTextColor(UIUtils.getResources().getColor(R.color.text_huang));
//
//                    xin.setImageResource(R.mipmap.zuixin);
//                    tvSubmit.setText("取消屏蔽");
//                    str= (String) tvSubmit.getText();
//
////                    adapter.setClick(position);
//                }
//            });
//        if(str=="取消屏蔽"){
//            tvSubmit.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    tvSubmit.setTextColor(UIUtils.getResources().getColor(R.color.home_navigation_text_gred));
//                    xin.setImageResource(R.mipmap.onexin);
//                    tvSubmit.setText("屏蔽TA");
//                }
//            });
//        }

        /// 这一步必须要做,否则不会显示.


//        rivUserImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ActivityUtils.startUserinfoActivity(dataBean.getUid());
//            }
//        });


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
