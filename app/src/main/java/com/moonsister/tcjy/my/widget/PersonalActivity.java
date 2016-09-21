package com.moonsister.tcjy.my.widget;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.moonsister.tcjy.ImageServerApi;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.bean.PersonalMessageBean;
import com.moonsister.tcjy.bean.PersonalMessageFenBean;
import com.moonsister.tcjy.my.persenter.PersonalActivityPersenter;
import com.moonsister.tcjy.my.persenter.PersonalActivityPersenterImpl;
import com.moonsister.tcjy.my.view.PersonalActivityFenView;
import com.moonsister.tcjy.utils.UIUtils;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.List;

import butterknife.Bind;

/**
 * Created by x on 2016/8/25.
 */
public class PersonalActivity extends BaseActivity implements PersonalActivityFenView {

    //    @Bind(R.id.image_back)//返回键
//            ImageView image_back;
    PersonalMessageFenBean.DataBean.BaseinfoBean baseinfo;
    List<PersonalMessageFenBean.DataBean.RulesBean> rules;
    PersonalMessageFenBean.DataBean.VipinfoBean vipinfo;
    PersonalMessageFenBean.DataBean.DlistBean dlist;
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
    @Bind(R.id.tv_love_talk)//爱情宣言
    TextView tv_love_talk;
    String id;
    String get_source;

    @Override
    protected View setRootContentView() {
        return UIUtils.inflateLayout(R.layout.personalactivity);
    }

    @Override
    protected void initView() {
        id = getIntent().getStringExtra("id");
        get_source = "1";
        persenter = new PersonalActivityPersenterImpl();
        persenter.attachView(this);
        persenter.sendPersonalMessageFen(id, get_source);

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

    @Override
    public void success(PersonalMessageFenBean getPersonalBean) {
        baseinfo = getPersonalBean.getData().getBaseinfo();
        rules = getPersonalBean.getData().getRules();
        vipinfo = getPersonalBean.getData().getVipinfo();
        dlist = getPersonalBean.getData().getDlist();
        ImageServerApi.showURLSamllImage(iv_user_icon, baseinfo.getFace());//展示头像
        String isvip = rules.get(1).getIsvip();//vip的显示与否
        if (isvip.equals("0")) {
            image_if_vip.setVisibility(View.INVISIBLE);
        } else {
            image_if_vip.setVisibility(View.VISIBLE);
        }
        tv_name_three.setText(baseinfo.getNickname());//用户名显示
        Calendar c = Calendar.getInstance();//获得系统当前日期
        String birthday = baseinfo.getBirthday();//得到生日
        String spStr[] = birthday.split("-");//截取字符串
        int s = Integer.parseInt(spStr[0]);//将得到的年份转为int类型
        int year =c.get(Calendar.YEAR);//得到系统年份
        int i = year-s;//系统年份减去得到的年分
        tv_age_three.setText(i + "岁");//年龄展示
        tv_hight_three.setText(dlist.getHeight()+"cm");//身高显示
        tv_wight_three.setText(dlist.getWeight()+"kg");//体重展示
        tv_position.setText(dlist.getBirthplace());//地址
        tv_native_place.setText(dlist.getResidence());//籍贯
        tv_hight.setText(dlist.getHeight()+"cm");//公开资料身高
        tv_wight.setText(dlist.getWeight()+"kg");//公开资料体重
        String value = dlist.getPremarital_sex();//是否接受异地恋
        if(value.equals("可以接受")){
            tv_long_distance.setText("是");
        }else if(value.equals("不能接受")){
            tv_long_distance.setText("否");
        }else if(value.equals("看情况")){
            tv_long_distance.setText("是");
        }
        tv_love_talk.setText(baseinfo.getSignature());
    }

    @Override
    public void person() {

    }
}
