package com.moonsister.tcjy.my.widget;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.moonsister.tcjy.ImageServerApi;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.DefaultDataBean;
import com.moonsister.tcjy.bean.PersonalMessageFenBean;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.main.model.UserActionModelImpl;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.my.persenter.PersonalActivityPersenter;
import com.moonsister.tcjy.my.persenter.PersonalActivityPersenterImpl;
import com.moonsister.tcjy.my.view.PersonalActivityFenView;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.StringUtis;
import com.moonsister.tcjy.utils.UIUtils;
import com.trello.rxlifecycle.ActivityEvent;

import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

import static com.moonsister.tcjy.R.id.qq_massage_look;

/**
 * Created by x on 2016/8/25.
 */
public class PersonalActivity extends BaseActivity implements PersonalActivityFenView {
    //
//    @Bind(R.id.image_back)//返回键
//            ImageView image_back;
    PersonalMessageFenBean.DataBean.BaseinfoBean baseinfo;
    List<PersonalMessageFenBean.DataBean.RulesBean> rules;
    PersonalMessageFenBean.DataBean.VipinfoBean vipinfo;
    PersonalMessageFenBean.DataBean.DlistBean dlist;
    PersonalMessageFenBean.DataBean.AinfoBean ainfo;
    PersonalActivityPersenter persenter;
    @Bind(R.id.iv_user_icon)//头像
            ImageView iv_user_icon;
    @Bind(R.id.image_if_vip)//是否为VIP
            ImageView image_if_vip;
    @Bind(R.id.tv_name_three)//用户名
            TextView tv_name_three;
    @Bind(R.id.tv_age_three)//年龄
            TextView tv_age_three;
    @Bind(R.id.tv_hight_three)//身高
            TextView tv_hight_three;
    @Bind(R.id.tv_wight_three)//体重
            TextView tv_wight_three;
    @Bind(R.id.tv_position)//地址
            TextView tv_position;
    @Bind(R.id.tv_native_place)//籍贯
            TextView tv_native_place;
    @Bind(R.id.tv_hight)//公开资料身高
            TextView tv_hight;
    @Bind(R.id.tv_wight)//公开资料体重
            TextView tv_wight;
    @Bind(R.id.tv_long_distance)//是否接受异地恋
            TextView tv_long_distance;
    @Bind(R.id.tv_love_talk)//爱情宣
            TextView tv_love_talk;
    @Bind(R.id.sv)
    ScrollView mScrollView;

    @Bind(R.id.tv_accept)
    TextView tv_accept;
    String id;
    String get_source;
    @Bind(R.id.pvt_picture)
    ImageView mPvtPicture;
    @Bind(R.id.pvt_picture2)
    ImageView mPvtPicture2;
    @Bind(R.id.pvt_picture3)
    ImageView mPvtPicture3;
    @Bind(R.id.pvt_video_1)
    ImageView mPvtVideo1;
    @Bind(R.id.pvt_video_2)
    ImageView mPvtVideo2;
    @Bind(R.id.pvt_video_3)
    ImageView mPvtVideo3;
    @Bind(R.id.pvt_voice_1)
    ImageView mPvtVoice1;
    @Bind(R.id.pvt_voice_2)
    ImageView mPvtVoice2;
    @Bind(R.id.pvt_voice_3)
    ImageView mPvtVoice3;
    @Bind(R.id.ll_pic)
    LinearLayout mLlPic;
    @Bind(R.id.ll_video)
    LinearLayout mLlVideo;
    @Bind(R.id.ll_vioce)
    LinearLayout mLlVioce;
    @Bind(R.id.tv_weixinhao)
    TextView tv_weixinhao;
    @Bind(R.id.tv_phone_number)
    TextView tv_phone_number;
    @Bind(R.id.tv_sex)
    TextView tv_sex;
    //    @Bind(R.id.massage_look_line)
//    ImageView massage_look_line;
    @Bind(R.id.tv_ifline)
    TextView tv_ifline;
    @Bind(R.id.tv_qq)
    TextView tv_qq;


    @Override
    protected View setRootContentView() {
        return UIUtils.inflateLayout(R.layout.personalactivity);
    }

    @Override
    protected void initView() {
        mScrollView.smoothScrollTo(0, 0);
        id = getIntent().getStringExtra("id");
        get_source = "1";
        persenter = new PersonalActivityPersenterImpl();
        persenter.attachView(this);
        persenter.sendPersonalMessageFen(id, get_source);
        RxBus.with(this)
                .setEndEvent(ActivityEvent.DESTROY)
                .setEvent(Events.EventEnum.BUY_VIP_SUCCESS)
                .onNext(events ->
                        persenter.sendPersonalMessageFen(id, get_source)
                )
                .create();


    }


    @Override
    public void showLoading() {
        showProgressDialog();
    }

    @Override
    public void hideLoading() {
        hideProgressDialog();
    }

    @Override
    public void transfePageMsg(String msg) {
        showToast(msg);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void success(PersonalMessageFenBean getPersonalBean) {
        baseinfo = getPersonalBean.getData().getBaseinfo();
        rules = getPersonalBean.getData().getRules();
        vipinfo = getPersonalBean.getData().getVipinfo();
        dlist = getPersonalBean.getData().getDlist();
        ainfo = getPersonalBean.getData().getAinfo();
        ImageServerApi.showURLSamllImage(iv_user_icon, baseinfo.getFace());//展示头像
        String isvip = rules.get(1).getIsvip();//vip的显示与否
        if (StringUtis.equals("0", isvip)) {
            image_if_vip.setVisibility(View.INVISIBLE);
        } else {
            image_if_vip.setVisibility(View.VISIBLE);
        }
        tv_sex.setText(getString(StringUtis.equals(baseinfo.getSex(), "1") ? R.string.boy : R.string.girls));
        tv_name_three.setText(baseinfo.getNickname());//用户名显示
        Calendar c = Calendar.getInstance();//获得系统当前日期
        String birthday = baseinfo.getBirthday();//得到生日
        String spStr[] = birthday.split("-");//截取字符串
        int s = StringUtis.string2Int(spStr[0]);//将得到的年份转为int类型
        int year = c.get(Calendar.YEAR);//得到系统年份
        int i = year - s;//系统年份减去得到的年分
        tv_age_three.setText(i + "岁");//年龄展示
        tv_hight_three.setText(dlist.getHeight() + "cm");//身高显示
        tv_wight_three.setText(dlist.getWeight() + "kg");//体重展示
        tv_position.setText(baseinfo.getBirthplace());//地址
        tv_native_place.setText(dlist.getResidence());//籍贯
        tv_hight.setText(dlist.getHeight() + "cm");//公开资料身高
        tv_wight.setText(dlist.getWeight() + "kg");//公开资料体重
        String value = dlist.getPremarital_sex();//是否接受异地恋
        if (StringUtis.equals(value, "可以接受")) {
            tv_long_distance.setText("是");
        } else if (StringUtis.equals(value, "不能接受")) {
            tv_long_distance.setText("否");
        } else if (StringUtis.equals(value, "看情况")) {
            tv_long_distance.setText("是");
        }
        if (StringUtis.equals(baseinfo.getIsfollow(), "1")) {
            tv_accept.setText("已入宫");
            Drawable drawable = getDrawable(R.mipmap.person_na_hougong);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tv_accept.setCompoundDrawables(drawable, null, null, null);
            tv_accept.setTextColor(getResources().getColor(R.color.color_f790ae));
        } else {
            tv_accept.setText("纳后宫");
            Drawable drawable = getResources().getDrawable(R.mipmap.tv_accept);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tv_accept.setCompoundDrawables(drawable, null, null, null);
            tv_accept.setTextColor(getResources().getColor(R.color.color_3e838a));

        }
        tv_love_talk.setText(baseinfo.getSignature());
        List<PersonalMessageFenBean.DataBean.BaseinfoBean.ImageDataBean> images = baseinfo.getImage_data();
        List<PersonalMessageFenBean.DataBean.BaseinfoBean.ImageDataBean> videos = baseinfo.getImage_video();
        List<PersonalMessageFenBean.DataBean.BaseinfoBean.ImageDataBean> voices = baseinfo.getImage_voice();
        showImage(images, mPvtPicture, mPvtPicture2, mPvtPicture3);
        showImage(videos, mPvtVideo1, mPvtVideo2, mPvtVideo3);
        showImage(voices, mPvtVoice1, mPvtVoice2, mPvtVoice3);
        if (images != null && images.size() != 0) {
            mLlPic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ActivityUtils.startPersonThreeActivity(id, baseinfo.getNickname(), baseinfo.getFace());
                }
            });
        }
        if (videos != null && videos.size() != 0) {
            mLlVideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ActivityUtils.startPersonThreeActivity(id, baseinfo.getNickname(), baseinfo.getFace());
                }
            });
        }
        if (voices != null && voices.size() != 0) {
            mLlPic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ActivityUtils.startPersonThreeActivity(id, baseinfo.getNickname(), baseinfo.getFace());
                }
            });
        }

    }

    @Override
    public void person() {

    }

    private void showImage(List<PersonalMessageFenBean.DataBean.BaseinfoBean.ImageDataBean> pics, ImageView imageView1, ImageView imageView2, ImageView imageView3) {
        if (pics == null || pics.size() == 0) {
            imageView1.setImageResource(R.mipmap.person_no_data);
            imageView2.setVisibility(View.GONE);
            imageView3.setVisibility(View.GONE);
            return;
        }

        for (int i = 0; i < pics.size(); i++) {
            if (i == 0) {
                String pic1 = pics.get(i).getS();
                if (StringUtis.isEmpty(pic1)) {
                    imageView1.setVisibility(View.GONE);
                } else {
                    ImageServerApi.showURLImage(imageView1, pics.get(i).getS());

                }

            }
            if (i == 1) {
                String pic1 = pics.get(i).getS();
                if (StringUtis.isEmpty(pic1)) {
                    imageView2.setVisibility(View.GONE);
                } else {
                    ImageServerApi.showURLImage(imageView2, pics.get(i).getS());

                }
            }
            if (i == 2) {
                String pic1 = pics.get(i).getS();
                if (StringUtis.isEmpty(pic1)) {
                    imageView3.setVisibility(View.GONE);
                } else {
                    ImageServerApi.showURLImage(imageView3, pics.get(i).getS());
                }
            }

        }
    }


    @OnClick({qq_massage_look, R.id.image_back, R.id.tv_sayhello, R.id.tv_accept, R.id.tv_make_an_appointment, R.id.massage_look, R.id.massage_look_phone, R.id.massage_look_line})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_back:
                finish();
                break;
            case R.id.tv_sayhello:
                ActivityUtils.startAppConversationActivity(id, baseinfo.getNickname(), baseinfo.getFace());
                break;
            case R.id.tv_accept:
                waicth();
                break;
            case R.id.tv_make_an_appointment:
                if (baseinfo != null)
                    ActivityUtils.startPersonEngagementTypeActivity(id, baseinfo.getNickname(), baseinfo.getFace());
                break;
            case R.id.massage_look:
                if (baseinfo != null) {
                    if (ainfo.getVip_level() > 0) {
                        tv_weixinhao.setText(vipinfo.getWeixin());
                    } else {
                        showNotLevel();
                    }
                }
                break;
            case R.id.massage_look_phone:
                if (baseinfo != null) {
                    if (ainfo.getVip_level() > 0) {
                        tv_phone_number.setText(vipinfo.getSmobile());
                    } else {
                        showNotLevel();
                    }
                }
                break;
            case R.id.massage_look_line:
                if (baseinfo != null) {
                    if (ainfo.getVip_level() > 0) {
                        tv_ifline.setText(StringUtis.equals(vipinfo.getZaixian(), "1") ? "在线" : "不在线");
                    } else {
                        showNotLevel();
                    }
                }
                break;
            case R.id.qq_massage_look:
                if (baseinfo != null) {
                    if (ainfo.getVip_level() > 0) {
                        tv_qq.setText(vipinfo.getQq());
                    } else {
                        showNotLevel();
                    }
                }
                break;
        }
    }

//    private void showNotLevel() {
//        AlertDialog myDialog = new AlertDialog.Builder(this).create();
//        myDialog.show();
//        View view = UIUtils.inflateLayout(R.layout.dialog_show_notlevel);
//        myDialog.getWindow().setContentView(view);
//        view.findViewById(R.id.iv_cancel).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                myDialog.dismiss();
//            }
//        });
//        view.findViewById(R.id.view_que)
//                .setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        ActivityUtils.startBuyVipActivity();
//                        myDialog.dismiss();
//                    }
//                });
//
//
//    }

    private void showNotLevel() {
        AlertDialog myDialog = new AlertDialog.Builder(this).create();
        myDialog.show();
        View view = UIUtils.inflateLayout(R.layout.dialog_show_notlevel);
        String sex = UserInfoManager.getInstance().getUserSex();
        if (!StringUtis.equals(sex, "1")) {
            ((ImageView) view.findViewById(R.id.iv_bg)).setImageResource(R.mipmap.bg_renzheng);
        }
        myDialog.getWindow().setContentView(view);
        view.findViewById(R.id.iv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        view.findViewById(R.id.view_que)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String sex = UserInfoManager.getInstance().getUserSex();
                        if (StringUtis.equals(sex, "1")) {
                            ActivityUtils.startBuyVipActivity();
                        } else {
                            ActivityUtils.startRenzhengThreeActivity();
                        }
                        myDialog.dismiss();
                    }
                });


    }

    private void waicth() {
        showLoading();
        UserActionModelImpl model = new UserActionModelImpl();
        model.wacthAction(id, StringUtis.equals(baseinfo.getIsfollow(), "1") ? "2" : "1", new BaseIModel.onLoadDateSingleListener<DefaultDataBean>() {
            @Override
            public void onSuccess(DefaultDataBean bean, BaseIModel.DataType dataType) {
                if (StringUtis.equals(bean.getCode(), "1")) {
                    baseinfo.setIsfollow(StringUtis.equals(baseinfo.getIsfollow(), "1") ? "2" : "1");
                    if (StringUtis.equals(baseinfo.getIsfollow(), "1")) {
                        tv_accept.setText("已入宫");
                        Drawable drawable = getResources().getDrawable(R.mipmap.person_na_hougong);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        tv_accept.setCompoundDrawables(drawable, null, null, null);
                        tv_accept.setTextColor(getResources().getColor(R.color.color_f790ae));
                    } else {
                        tv_accept.setText("纳后宫");
                        Drawable drawable = getResources().getDrawable(R.mipmap.tv_accept);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        tv_accept.setCompoundDrawables(drawable, null, null, null);
                        tv_accept.setTextColor(getResources().getColor(R.color.color_3e838a));

                    }
                }
                showToast(bean.getMsg());
                hideLoading();
            }

            @Override
            public void onFailure(String msg) {
                showToast(msg);
                hideLoading();
            }
        });
    }

}
