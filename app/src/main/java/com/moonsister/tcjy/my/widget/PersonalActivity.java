package com.moonsister.tcjy.my.widget;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.moonsister.tcjy.ImageServerApi;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.adapter.PersonalAdapter;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.bean.GoodSelectBaen;
import com.moonsister.tcjy.bean.PersonInfoDetail;
import com.moonsister.tcjy.bean.PersonalMessageBean;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.my.persenter.PersonalActivityPersenter;
import com.moonsister.tcjy.my.persenter.PersonalActivityPersenterImpl;
import com.moonsister.tcjy.my.view.PersonalActivityView;
import com.moonsister.tcjy.utils.UIUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by x on 2016/8/25.
 */
public class PersonalActivity extends BaseActivity implements PersonalActivityView {
    //    String[] personal=new String[]{"头像","昵称","个性签名","性别","年龄","身高","体重","现居","职业","兴趣爱好"};
    @Bind(R.id.personal_list)//展示用户信息的listview
            ListView personal_list;
    PersonalAdapter adapter;
    @Bind(R.id.image_back)//返回键
            ImageView image_back;
    PersonalActivityPersenter persenter;
    @Bind(R.id.iv_user_icon)//用户头像
            ImageView iv_user_icon;
    @Bind(R.id.if_user_vip)//是否为vip会员
            ImageView if_user_vip;
    @Bind(R.id.iv_user_name)//用户名
            TextView iv_user_name;
    @Bind(R.id.im_user_vip)//vip类型，年会员，月会员
            ImageView im_user_vip;
    @Bind(R.id.iv_user_work)//用户职业
            TextView iv_user_work;
    @Bind(R.id.im_user_sex)//用户性别
            ImageView im_user_sex;
    @Bind(R.id.iv_user_age)//用户年龄
            TextView iv_user_age;
    @Bind(R.id.iv_user_most)//用户的签名
            TextView iv_user_most;
    @Bind(R.id.phone_number)//用户的手机号
            TextView phone_number;
    @Bind(R.id.qq_number)//用户的QQ号
            TextView qq_number;
    @Bind(R.id.weixin_number)//用户的微信号
            TextView weixin_number;
    @Bind(R.id.follow_ta)//关注他
            TextView follow_ta;
    @Bind(R.id.look)//手机号查看
    ImageView look;
    @Bind(R.id.qq_look)//QQ查看
            ImageView qq_look;
    @Bind(R.id.weixin_look)//微信号查看
            ImageView weixin_look;
    List<PersonalMessageBean.DataBean.RulesBean> data;
    PersonalMessageBean.DataBean.BaseinfoBean data1;
    PersonalMessageBean.DataBean.DlistBean data2;
    PersonalMessageBean.DataBean.VipinfoBean data3;
    String uid;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected View setRootContentView() {
        return UIUtils.inflateLayout(R.layout.personalactivity);
    }

    @Override
    protected void initView() {
        persenter = new PersonalActivityPersenterImpl();
        persenter.attachView(this);
        persenter.sendPersonalMessage(145655);
//        iv_user_icon.setImageResource(Integer.parseInt(data2.getFace()));
//        iv_user_name.setText(data1.getNickname());
//        List<Map<String,Object>> listItems = new ArrayList<Map<String, Object>>();
//        for(int i=0;i<personal.length;i++){
//            Map<String, Object> listItem = new HashMap<String, Object>();
//            listItem.put("t", personal[i]);
//            listItems.add(listItem);
//        }
//        SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems,
//                R.layout.personallistviewitem, new String[] { "t" }, new int[] {
//                R.id.text_name, });
//        personal_list.setAdapter(simpleAdapter);
//
////        gridView.setAdapter(simpleAdapter);
////        peradapter=new

    }


    @OnClick(R.id.image_back)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_back:
                PersonalActivity.this.finish();
                break;
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void transfePageMsg(String msg) {
        showToast(msg);
    }

    @Override
    public void success(PersonalMessageBean getPersonalBean) {
        data = getPersonalBean.getData().getRules();
        data1 = getPersonalBean.getData().getBaseinfo();
        data2=getPersonalBean.getData().getDlist();
        data3=getPersonalBean.getData().getVipinfo();

        adapter=new PersonalAdapter(this,data);
        personal_list.setAdapter(adapter);

        iv_user_name.setText(data1.getNickname());//用户昵称

        int i=data1.getSex();//用户性别
        if(i==1){
            im_user_sex.setImageResource(R.mipmap.nan);
        }else{
            im_user_sex.setImageResource(R.mipmap.gril);
        }
        ImageServerApi.showURLBigImage(iv_user_icon, data1.getFace());//用户头像
        iv_user_age.setText(data1.getAge()+"");//用户年龄
        String profess = data1.getProfession();//用户职业
//        String profess=profession.toString();
        if(profess==null){
            iv_user_work.setVisibility(View.INVISIBLE);
        }else{
            iv_user_work.setText(profess);
        }

        iv_user_most.setText(data1.getSignature());//用户签名
        String isvip = data.get(0).getIsvip();
        if(isvip=="0"){
            if_user_vip.setVisibility(View.INVISIBLE);
        }else{
            if_user_vip.setVisibility(View.VISIBLE);
        }
        String Vip_level=data1.getVip_level();//VIP等级
        if(Vip_level=="0"){

            im_user_vip.setVisibility(View.INVISIBLE);
            if_user_vip.setVisibility(View.INVISIBLE);
        }else{

        }
        String smobile = data3.getSmobile();//用户手机号
        if(smobile==null){
            phone_number.setText("xxxxxxxxxxx");
            look.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    phone_number.setText("");
                }
            });
        }else{
            phone_number.setText("xxxxxxxxxxx");
            look.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    phone_number.setText(smobile);
                }
            });
        }
        PersonInfoDetail memoryPersonInfoDetail = UserInfoManager.getInstance().getMemoryPersonInfoDetail();//获得对象
        memoryPersonInfoDetail.setSmobile(smobile);//保存值
        UserInfoManager.getInstance().saveMemoryInstance(memoryPersonInfoDetail);
        String qq = data3.getQq();//用户QQ号
            if(qq==null){
                qq_number.setText("xxxxxxxxxxx");
                qq_look.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        qq_number.setText("");
                    }
                });
            }else{
                qq_number.setText("xxxxxxxxxxx");
                qq_look.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        qq_number.setText(qq);
                    }
                });
        }
        String weixin = data3.getWeixin();//用户微信号
        if(weixin==null){
            weixin_number.setText("xxxxxxxxxxx");
            weixin_look.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    weixin_number.setText("");
                }
            });

        }else{
            weixin_number.setText("xxxxxxxxxxx");
            weixin_look.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    weixin_number.setText(weixin);
                }
            });
        }



    }

    @Override
    public void person() {

    }

}
