package com.moonsister.tcjy.my.widget;

import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.View.OnClickListener;

import com.moonsister.tcjy.ImageServerApi;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.bean.PersonalMessageBean;
import com.moonsister.tcjy.bean.PersonalReviseMessageBean;
import com.moonsister.tcjy.bean.UserInfoChangeBean;
import com.moonsister.tcjy.bean.personalBean;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.login.widget.SelectPicPopupActivity;
import com.moonsister.tcjy.my.persenter.PersonalReviseActivityPersenter;
import com.moonsister.tcjy.my.persenter.PersonalReviseActivityPersenterImpl;
import com.moonsister.tcjy.my.view.PersonalActivityView;
import com.moonsister.tcjy.my.view.PersonalReviseActivityView;
import com.moonsister.tcjy.popwindow.PopWindowDistance;
import com.moonsister.tcjy.popwindow.PopWindowMarital;
import com.moonsister.tcjy.popwindow.PopWindowPremarital;
import com.moonsister.tcjy.popwindow.SelectPicPopupWindow;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.LogUtils;
import com.moonsister.tcjy.utils.UIUtils;
import com.trello.rxlifecycle.ActivityEvent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by x on 2016/9/5.
 */
public class PersonalReviseActivity extends BaseActivity implements PersonalReviseActivityView{
    @Bind(R.id.riv_user_image)//头像
            ImageView riv_user_image;
//    @Bind(R.id.riv_like_image)//背景墙
//            ImageView riv_like_image;
    @Bind(R.id.tv_nike_name)//昵称
            EditText tv_nike_name;
    @Bind(R.id.tv_signature)//个性签名
            EditText tv_signature;
    @Bind(R.id.tv_sex)//性别
            EditText tv_sex;
    @Bind(R.id.tv_birthday)//出生年月
            TextView tv_birthday;
    @Bind(R.id.tv_star_sign)//星座
            TextView tv_star_sign;
    @Bind(R.id.tv_birthplace)//籍贯
            TextView tv_birthplace;
    @Bind(R.id.tv_address)//现居
            TextView tv_address;
    @Bind(R.id.tv_job)//职业
            TextView tv_job;
    @Bind(R.id.tv_hobby)//兴趣爱好
            TextView tv_hobby;
    @Bind(R.id.tv_self_image)//自我印象
            TextView tv_self_image;
    @Bind(R.id.tv_ishouse)//是否有房
            TextView tv_ishouse;
    @Bind(R.id.tv_marital_status)//婚姻状况
            TextView tv_marital_status;
    @Bind(R.id.tv_distance_love)//接受异地恋
            TextView tv_distance_love;
    @Bind(R.id.tv_like_sex)//喜欢的异性
            TextView tv_like_sex;
    @Bind(R.id.tv_premarital_sex)//婚前性行为
            TextView tv_premarital_sex;
    @Bind(R.id.image_back)//返回
            ImageView image_back;
    @Bind(R.id.baocun)//保存
            TextView baocun;
    PersonalReviseActivityPersenter presenter;
//    List<PersonalMessageBean.DataBean.RulesBean> data;
//    PersonalMessageBean.DataBean.BaseinfoBean data1;
//    PersonalMessageBean.DataBean.DlistBean data2;
//    PersonalMessageBean.DataBean.VipinfoBean data3;
    private List<PersonalMessageBean> userBeans; //保存数据的集合
    private JSONObject object; //JSONObject对象，处理一个一个的对象
    private JSONObject object2;
    private JSONArray jsonArray;//JSONObject对象，处理一个一个集合或者数组
    private String jsonString; //保存带集合的json字符串
    private String jsonString2;//不带集合的json字符串
    private String avater;
    private personalBean mPersonalBean;
    String habit;
    //自定义的弹出框类
    SelectPicPopupWindow menuWindow;
    PopWindowMarital mMenuViewmarital;
    PopWindowDistance mMenuViewdistance;
    PopWindowPremarital mMenuViewpremarital;
    String message;//头像路径
    String message2;//背景图路径
    String result;//年龄
    String love;//星座
    JSONObject jsonmobile;//手机
    JSONObject jsonqq;//qq
    JSONObject jsonweixin;//微信
    JSONObject  jsonavater;//头像  背景图
    JSONObject jsonobjname;//昵称
    JSONObject jsonobjsignature;//个性签名
    JSONObject jsonobjsex;//性别
    JSONObject jsonobjbirthday;//出生日期
    JSONObject jsonobjstarsign;//星座
    JSONObject jsonobjbirthplace;//籍贯
    JSONObject jsonobjaddress;//现居
    JSONObject jsonobjjob;//职业
    JSONObject jsonobjhobby;//兴趣爱好
    JSONObject jsonobjself;//自我印象
    JSONObject jsonobjishouse;//是否有房
    JSONObject jsonobjmarital;//婚姻状况
    JSONObject jsonobjdistance;//接受异地恋
    JSONObject jsonobjlike;//喜欢的异性
    JSONObject jsonobjpremarital;//婚前性行为

    String ishouse;
    String marital_status;
    String distance;
    String premarital_sex;
    @Bind(R.id.tv_mobile)//手机
    EditText tv_mobile;
    @Bind(R.id.tv_qq)//手机
            EditText tv_qq;
    @Bind(R.id.tv_weixin)//手机
            EditText tv_weixin;
    List<PersonalReviseMessageBean.DataBean.RulesBean> rules;
    @Bind(R.id.layout_mobile)//手机layout
    RelativeLayout layout_mobile;// ,,R.id.layout_weight,R.id.layout_avater, R.id.layout_nike_name, R.id.layout_signature, R.id.layout_sex, R.id.layout_birthday, R.id.layout_star_sign, R.id.layout_birthplace, R.id.layout_address, R.id.layout_job, R.id.layout_hobby, R.id.layout_self_image, R.id.layout_ishouse, R.id.layout_marital_status, R.id.layout_distance_love, R.id.layout_like_sex, R.id.layout_premarital_sex, R.id.baocun
    @Bind(R.id.layout_qq)//QQlayout
    RelativeLayout layout_qq;
    @Bind(R.id.layout_weixin)//微信layout
    RelativeLayout layout_weixin;
    @Bind(R.id.layout_avater)//头像layout
            RelativeLayout layout_avater;
    @Bind(R.id.layout_nike_name)//昵称layout
            RelativeLayout layout_nike_name;
    @Bind(R.id.layout_signature)//个性签名layout
            RelativeLayout layout_signature;
    @Bind(R.id.layout_sex)//性别layout
            RelativeLayout layout_sex;
    @Bind(R.id.layout_birthday)//出生年月layout
            RelativeLayout layout_birthday;
    @Bind(R.id.layout_star_sign)//星座layout
            RelativeLayout layout_star_sign;
    @Bind(R.id.layout_birthplace)//籍贯layout
            RelativeLayout layout_birthplace;
    @Bind(R.id.layout_address)//现居layout
            RelativeLayout layout_address;
    @Bind(R.id.layout_job)//职业layout
            RelativeLayout layout_job;
    @Bind(R.id.layout_hobby)//兴趣爱好layout
            RelativeLayout layout_hobby;
    @Bind(R.id.layout_self_image)//自我印象layout
            RelativeLayout layout_self_image;
    @Bind(R.id.layout_ishouse)//是否有房layout
            RelativeLayout layout_ishouse;
    @Bind(R.id.layout_marital_status)//婚姻状况layout
            RelativeLayout layout_marital_status;
    @Bind(R.id.layout_distance_love)//接受异地恋layout
            RelativeLayout layout_distance_love;
    @Bind(R.id.layout_like_sex)//喜欢的异性layout
            RelativeLayout layout_like_sex;
    @Bind(R.id.layout_premarital_sex)//婚前性行为layout
            RelativeLayout layout_premarital_sex;

    @Override
    protected View setRootContentView() {

        return UIUtils.inflateLayout(R.layout.personalreviseactivity);
    }

    @Override
    protected void initView() {
        presenter = new PersonalReviseActivityPersenterImpl();
        presenter.attachView(this);
        presenter.sendPersonalReviseMessage(145655);

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

    @OnClick({R.id.image_back, R.id.layout_mobile,R.id.layout_qq,R.id.layout_weixin,R.id.layout_avater, R.id.layout_nike_name, R.id.layout_signature, R.id.layout_sex, R.id.layout_birthday, R.id.layout_star_sign, R.id.layout_birthplace, R.id.layout_address, R.id.layout_job, R.id.layout_hobby, R.id.layout_self_image, R.id.layout_ishouse, R.id.layout_marital_status, R.id.layout_distance_love, R.id.layout_like_sex, R.id.layout_premarital_sex, R.id.baocun})
    public void onClick(View view) {
        if(rules==null){
            return;
        }
        switch (view.getId()) {
            case R.id.image_back:
                this.finish();
//                changeArrayDateToJson();
//                presenter.sendUserJson(jsonString);
                break;
            case R.id.layout_mobile:
                String mobile = tv_mobile.getText().toString();
                jsonmobile=new JSONObject();
                PersonalReviseMessageBean.DataBean.RulesBean rulesBean = rules.get(0);
                if(rulesBean==null){
                }
                    try {
                        jsonmobile.put("field",rules.get(0).getField());
                        jsonmobile.put("name",rules.get(0).getName());
                        jsonmobile.put("edit",rules.get(0).getEdit());
                        jsonmobile.put("isvip",rules.get(0).getIsvip());
                        jsonmobile.put("isshow",rules.get(0).getIsshow());
                        jsonmobile.put("value",mobile);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                break;
            case R.id.layout_qq:
                String qq = tv_mobile.getText().toString();
                jsonqq=new JSONObject();
                PersonalReviseMessageBean.DataBean.RulesBean rulesQQ = rules.get(1);
                if(rulesQQ==null){
                }
                try {
                    jsonqq.put("field",rules.get(1).getField());
                    jsonqq.put("name",rules.get(1).getName());
                    jsonqq.put("edit",rules.get(1).getEdit());
                    jsonqq.put("isvip",rules.get(1).getIsvip());
                    jsonqq.put("isshow",rules.get(1).getIsshow());
                    jsonqq.put("value",qq);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.layout_weixin:
                String weixin = tv_mobile.getText().toString();
                jsonweixin=new JSONObject();
                PersonalReviseMessageBean.DataBean.RulesBean rulesweixin = rules.get(2);
                if(rulesweixin==null){
                }
                try {
                    jsonweixin.put("field",rules.get(2).getField());
                    jsonweixin.put("name",rules.get(2).getName());
                    jsonweixin.put("edit",rules.get(2).getEdit());
                    jsonweixin.put("isvip",rules.get(2).getIsvip());
                    jsonweixin.put("isshow",rules.get(2).getIsshow());
                    jsonweixin.put("value",weixin);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.layout_avater://头像
                ActivityUtils.startActivity(SelectPicPopupActivity.class);
                RxBus.with(this)
                        .setEvent(Events.EventEnum.GET_PHOTO)
                        .setEndEvent(ActivityEvent.DESTROY)
                        .onNext((events) -> {
                            message = (String) events.message;
                            LogUtils.e(RZFirstActivity.class, "pic_path : " + message);
                            avater = message;
                            ImageServerApi.showURLSamllImage(riv_user_image, message);
                        }).create();
                PersonalReviseMessageBean.DataBean.RulesBean rulesavater = rules.get(3);
                if(rulesavater==null){
                }
                jsonavater=new JSONObject();
                try {
                    jsonavater.put("field",rules.get(3).getField());
                    jsonavater.put("name",rules.get(3).getName());
                    jsonavater.put("edit",rules.get(3).getEdit());
                    jsonavater.put("isvip",rules.get(3).getIsvip());
                    jsonavater.put("isshow",rules.get(3).getIsshow());
                    jsonavater.put("value",message);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                break;

            case R.id.layout_nike_name://昵称

                tv_nike_name.requestFocus();
                String str = tv_nike_name.getText().toString();
                PersonalReviseMessageBean.DataBean.RulesBean rulesnike = rules.get(4);
                if(rulesnike==null){
                }
                jsonobjname = new JSONObject();
                try {
                    jsonobjname.put("field", rules.get(4).getField());
                    jsonobjname.put("name", rules.get(4).getName());
                    jsonobjname.put("edit", rules.get(4).getEdit());
                    jsonobjname.put("isvip", rules.get(4).getIsvip());
                    jsonobjname.put("isshow",rules.get(4).getIsshow());
                    jsonobjname.put("value", str);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.layout_signature://个性签名
                tv_signature.requestFocus();
                String signature = tv_signature.getText().toString();
                PersonalReviseMessageBean.DataBean.RulesBean rulessignature = rules.get(5);
                if(rulessignature==null){
                }
                jsonobjsignature = new JSONObject();
                try {
                    jsonobjsignature.put("field", rules.get(5).getField());
                    jsonobjsignature.put("name", rules.get(5).getName());
                    jsonobjsignature.put("edit",rules.get(5).getEdit());
                    jsonobjsignature.put("isvip", rules.get(5).getIsvip());
                    jsonobjsignature.put("isshow",rules.get(5).getIsshow());
                    jsonobjsignature.put("value", signature);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.layout_sex:
                String sex = tv_sex.getText().toString();
                PersonalReviseMessageBean.DataBean.RulesBean rulessex = rules.get(6);
                if(rulessex==null){
                }
                jsonobjsex = new JSONObject();
                try {
                    jsonobjsex.put("field", rules.get(6).getField());
                    jsonobjsex.put("name", rules.get(6).getName());
                    jsonobjsex.put("edit", rules.get(6).getEdit());
                    jsonobjsex.put("isvip", rules.get(6).getIsvip());
                    jsonobjsex.put("isshow",rules.get(6).getIsshow());
                    jsonobjsex.put("value", sex);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.layout_birthday://出生年月
                startActivityForResult(new Intent(PersonalReviseActivity.this, BirthdayActivity.class), 1);
                PersonalReviseMessageBean.DataBean.RulesBean rulesbir = rules.get(7);
                if(rulesbir==null){
                }
                jsonobjbirthday = new JSONObject();
                try {
                    jsonobjbirthday.put("field", rules.get(7).getField());
                    jsonobjbirthday.put("name", rules.get(7).getName());
                    jsonobjbirthday.put("edit", rules.get(7).getEdit());
                    jsonobjbirthday.put("isvip", rules.get(7).getIsvip());
                    jsonobjbirthday.put("isshow",rules.get(7).getIsshow());
                    jsonobjbirthday.put("value", result);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.layout_star_sign://星座
                PersonalReviseMessageBean.DataBean.RulesBean rulesstar = rules.get(8);
                if(rulesstar==null){
                }
                jsonobjstarsign = new JSONObject();
                try {
                    jsonobjstarsign.put("field", rules.get(8).getField());
                    jsonobjstarsign.put("name", rules.get(8).getName());
                    jsonobjstarsign.put("edit", rules.get(8).getEdit());
                    jsonobjstarsign.put("isvip", rules.get(8).getIsvip());
                    jsonobjstarsign.put("isshow",rules.get(8).getIsshow());
                    jsonobjstarsign.put("value", love);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.layout_birthplace://籍贯
                tv_birthplace.requestFocus();
                String birthplace = tv_birthplace.getText().toString();
                PersonalReviseMessageBean.DataBean.RulesBean rulesbirthplace = rules.get(9);
                if(rulesbirthplace==null){
                }
                jsonobjbirthplace = new JSONObject();
                try {
                    jsonobjbirthplace.put("field", rules.get(9).getField());
                    jsonobjbirthplace.put("name", rules.get(9).getName());
                    jsonobjbirthplace.put("edit", rules.get(9).getEdit());
                    jsonobjbirthplace.put("isvip", rules.get(9).getIsvip());
                    jsonobjbirthplace.put("isshow",rules.get(9).getIsshow());
                    jsonobjbirthplace.put("value", birthplace);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.layout_address://现居
                tv_address.requestFocus();
                String address = tv_address.getText().toString();
                PersonalReviseMessageBean.DataBean.RulesBean rulesaddress = rules.get(10);
                if(rulesaddress==null){
                }
                jsonobjaddress = new JSONObject();
                try {
                    jsonobjaddress.put("field", rules.get(10).getField());
                    jsonobjaddress.put("name", rules.get(10).getName());
                    jsonobjaddress.put("edit", rules.get(10).getEdit());
                    jsonobjaddress.put("isvip", rules.get(10).getIsvip());
                    jsonobjaddress.put("isshow",rules.get(10).getIsshow());
                    jsonobjaddress.put("value", address);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.layout_job://职业
                tv_job.requestFocus();
                String job = tv_job.getText().toString();
                PersonalReviseMessageBean.DataBean.RulesBean rulesjob = rules.get(11);
                if(rulesjob==null){
                }
                jsonobjjob = new JSONObject();
                try {
                    jsonobjjob.put("field", rules.get(11).getField());
                    jsonobjjob.put("name", rules.get(11).getName());
                    jsonobjjob.put("edit", rules.get(11).getEdit());
                    jsonobjjob.put("isvip", rules.get(11).getIsvip());
                    jsonobjjob.put("isshow",rules.get(11).getIsshow());
                    jsonobjjob.put("value", job);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.layout_hobby://兴趣爱好
                tv_hobby.requestFocus();
                String hobby = tv_hobby.getText().toString();
                PersonalReviseMessageBean.DataBean.RulesBean ruleshobby = rules.get(12);
                if(ruleshobby==null){
                }
                jsonobjhobby = new JSONObject();
                try {
                    jsonobjhobby.put("field", rules.get(12).getField());
                    jsonobjhobby.put("name", rules.get(12).getName());
                    jsonobjhobby.put("edit", rules.get(12).getEdit());
                    jsonobjhobby.put("isvip", rules.get(12).getIsvip());
                    jsonobjhobby.put("isshow",rules.get(12).getIsshow());
                    jsonobjhobby.put("value", hobby);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.layout_self_image://自我印象
                tv_self_image.requestFocus();
                String self_image = tv_self_image.getText().toString();
                PersonalReviseMessageBean.DataBean.RulesBean rulesself = rules.get(13);
                if(rulesself==null){
                }
                jsonobjself = new JSONObject();
                try {
                    jsonobjself.put("field", rules.get(13).getField());
                    jsonobjself.put("name", rules.get(13).getName());
                    jsonobjself.put("edit", rules.get(13).getEdit());
                    jsonobjself.put("isvip", rules.get(13).getIsvip());
                    jsonobjself.put("isshow",rules.get(13).getIsshow());
                    jsonobjself.put("value", self_image);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.layout_ishouse://是否有房
                menuWindow = new SelectPicPopupWindow(PersonalReviseActivity.this, itemsOnClick);
                menuWindow.showAtLocation(PersonalReviseActivity.this.findViewById(R.id.layout_ishouse), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
                ishouse = tv_ishouse.getText().toString();
                PersonalReviseMessageBean.DataBean.RulesBean rulesishouse = rules.get(14);
                if(rulesishouse==null){
                }
                jsonobjishouse = new JSONObject();
                try {
                    jsonobjishouse.put("fileld", rules.get(14).getField());
                    jsonobjishouse.put("name", rules.get(14).getName());
                    jsonobjishouse.put("edit", rules.get(14).getEdit());
                    jsonobjishouse.put("isvip", rules.get(14).getIsvip());
                    jsonobjishouse.put("isshow",rules.get(14).getIsshow());
                    jsonobjishouse.put("value", ishouse);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.layout_marital_status://婚姻状况
                mMenuViewmarital = new PopWindowMarital(PersonalReviseActivity.this, itemsOnClickMarital);
                mMenuViewmarital.showAtLocation(PersonalReviseActivity.this.findViewById(R.id.tv_marital_status), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
                marital_status = tv_marital_status.getText().toString();
                PersonalReviseMessageBean.DataBean.RulesBean rulesmarital = rules.get(15);
                if(rulesmarital==null){
                }
                jsonobjmarital = new JSONObject();
                try {
                    jsonobjmarital.put("field", rules.get(15).getField());
                    jsonobjmarital.put("name", rules.get(15).getName());
                    jsonobjmarital.put("edit", rules.get(15).getEdit());
                    jsonobjmarital.put("isvip", rules.get(15).getIsvip());
                    jsonobjmarital.put("isshow",rules.get(15).getIsshow());
                    jsonobjmarital.put("value", marital_status);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.layout_distance_love://接受异地恋
                mMenuViewdistance = new PopWindowDistance(PersonalReviseActivity.this, itemsOnClickDistance);
                mMenuViewdistance.showAtLocation(PersonalReviseActivity.this.findViewById(R.id.tv_distance_love), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
                distance = tv_distance_love.getText().toString();
                PersonalReviseMessageBean.DataBean.RulesBean rulesdistance = rules.get(16);
                if(rulesdistance==null){
                }
                jsonobjdistance = new JSONObject();
                try {
                    jsonobjdistance.put("field", rules.get(16).getField());
                    jsonobjdistance.put("name", rules.get(16).getName());
                    jsonobjdistance.put("edit", rules.get(16).getEdit());
                    jsonobjdistance.put("isvip", rules.get(16).getIsvip());
                    jsonobjdistance.put("isshow",rules.get(16).getIsshow());
                    jsonobjdistance.put("value", distance);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.layout_like_sex://喜欢的异性
                tv_like_sex.requestFocus();
                String like_sex = tv_like_sex.getText().toString();
                PersonalReviseMessageBean.DataBean.RulesBean ruleslike = rules.get(17);
                if(ruleslike==null){
                }
                jsonobjlike = new JSONObject();
                try {
                    jsonobjlike.put("field", rules.get(17).getField());
                    jsonobjlike.put("name", rules.get(17).getName());
                    jsonobjlike.put("edit", rules.get(17).getEdit());
                    jsonobjlike.put("isvip", rules.get(17).getIsvip());
                    jsonobjlike.put("isshow",rules.get(17).getIsshow());
                    jsonobjlike.put("value", like_sex);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.layout_premarital_sex://婚前性行为
                mMenuViewpremarital = new PopWindowPremarital(PersonalReviseActivity.this, itemsOnClickPrematital);
                mMenuViewpremarital.showAtLocation(PersonalReviseActivity.this.findViewById(R.id.tv_premarital_sex), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
                premarital_sex = tv_premarital_sex.getText().toString();
                PersonalReviseMessageBean.DataBean.RulesBean rulespremarital = rules.get(18);
                if(rulespremarital==null){
                }
                jsonobjpremarital = new JSONObject();
                try {
                    jsonobjpremarital.put("field", rules.get(18).getField());
                    jsonobjpremarital.put("name", rules.get(18).getName());
                    jsonobjpremarital.put("edit", rules.get(18).getEdit());
                    jsonobjpremarital.put("isvip", rules.get(18).getIsvip());
                    jsonobjpremarital.put("isshow",rules.get(18).getIsshow());
                    jsonobjpremarital.put("value", premarital_sex);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.baocun:

                JSONArray jsonarry = new JSONArray();
                jsonarry.put(jsonmobile);
                jsonarry.put(jsonqq);
                jsonarry.put(jsonweixin);
                jsonarry.put(jsonavater);
                jsonarry.put(jsonobjname);
                jsonarry.put(jsonobjsignature);
                jsonarry.put(jsonobjsex);
                jsonarry.put(jsonobjbirthday);
                jsonarry.put(jsonobjstarsign);
                jsonarry.put(jsonobjbirthplace);
                jsonarry.put(jsonobjaddress);
                jsonarry.put(jsonobjjob);
                jsonarry.put(jsonobjhobby);
                jsonarry.put(jsonobjself);
                jsonarry.put(jsonobjishouse);
                jsonarry.put(jsonobjmarital);
                jsonarry.put(jsonobjdistance);
                jsonarry.put(jsonobjlike);
                jsonarry.put(jsonobjpremarital);
                String ss = jsonarry.toString();
                presenter.sendUserJson(ss);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        result = data.getExtras().getString("result");//得到新Activity 关闭后返回的数据
        love = data.getExtras().getString("love");
        tv_birthday.setText(result);
        tv_star_sign.setText(love);
        Log.i(TAG, result);
    }


    //为婚前性行为弹出窗口实现监听类
    private OnClickListener itemsOnClickPrematital = new OnClickListener() {

        public void onClick(View v) {
            mMenuViewpremarital.dismiss();
            switch (v.getId()) {
                case R.id.can_can:
                    tv_premarital_sex.setText("可以接受");
                    break;
                case R.id.nono_no:
                    tv_premarital_sex.setText("不能接受");
                    break;

                case R.id.can_no:
                    tv_premarital_sex.setText("必须得有");
                    break;
                default:
                    break;
            }
        }
    };
    //为接受异地恋弹出窗口实现监听类
    private OnClickListener itemsOnClickDistance = new OnClickListener() {

        public void onClick(View v) {
            mMenuViewdistance.dismiss();
            switch (v.getId()) {
                case R.id.can:
                    tv_distance_love.setText("可以接受");
                    break;
                case R.id.nono:
                    tv_distance_love.setText("不能接受");
                    break;

                case R.id.look_can:
                    tv_distance_love.setText("看情况");
                    break;
                default:
                    break;
            }
        }
    };
    //为弹出窗口实现监听类
    private OnClickListener itemsOnClick = new OnClickListener() {

        public void onClick(View v) {
            menuWindow.dismiss();
            switch (v.getId()) {
                case R.id.btn_take_photo:
                    tv_ishouse.setText("租房");

                    break;
                case R.id.btn_pick_photo:
                    tv_ishouse.setText("有房");

                    break;

                case R.id.btn_pick_photo1:
                    tv_ishouse.setText("宿舍");
                    break;
                default:
                    break;
            }
        }
    };
    //为婚姻状况弹出窗口实现监听类
    private OnClickListener itemsOnClickMarital = new OnClickListener() {
        public void onClick(View v) {
            mMenuViewmarital.dismiss();
            switch (v.getId()) {
                case R.id.yi_marital:
                    tv_marital_status.setText("已婚");
                    break;
                case R.id.wei_marital:
                    tv_marital_status.setText("未婚");
                    break;
                case R.id.li_marital:
                    tv_marital_status.setText("离异");
                    break;
                case R.id.si_marital:
                    tv_marital_status.setText("丧偶");
                    break;

                default:
                    break;
            }


        }

    };

    @Override
    public void success(PersonalReviseMessageBean personalReviseMessageBean) {
        rules = personalReviseMessageBean.getData().getRules();
//        tv_mobile.setText(rules.get(0).getValue());//手机  QQ 微信
//        tv_qq.setText(rules.get(1).getValue());
//        tv_weixin.setText(rules.get(2).getValue());
//        ImageServerApi.showURLBigImage(riv_user_image, rules.get(3).getValue());//用户头像
//        tv_nike_name.setText(rules.get(4).getValue());//用户昵称
//        tv_signature.setText(rules.get(5).getValue());//个性签名
//        String value = rules.get(6).getValue();//性别
//            if (value == "1") {
//                tv_sex.setText("男");
//            } else {
//                tv_sex.setText("女");
//            }
//        tv_birthday.setText(rules.get(7).getValue());//出生年月
//        tv_star_sign.setText(rules.get(8).getValue());//星座
//        tv_birthplace.setText(rules.get(9).getValue());//籍贯
//        tv_address.setText(rules.get(10).getValue());//现居
//        tv_job.setText(rules.get(11).getValue());//职业
//        tv_hobby.setText(rules.get(12).getValue());//兴趣爱好
//        tv_self_image.setText(rules.get(13).getValue());//自我印象
//        tv_ishouse.setText(rules.get(14).getValue());//是否有房
//        tv_marital_status.setText(rules.get(15).getValue());//婚姻状况
//        tv_distance_love.setText(rules.get(16).getValue());//接受异地恋
//        tv_like_sex.setText(rules.get(17).getValue());//喜欢的异性
//        tv_premarital_sex.setText(rules.get(18).getValue());//婚前性行为
        String isshow = rules.get(0).getIsshow().toString();
        if(isshow.equals("1")){
            tv_mobile.setText(rules.get(0).getValue()+"");//手机  QQ 微信
        }else{
            layout_mobile.setVisibility(View.GONE);
        }

        String isshowqq = rules.get(1).getIsshow();
        if(isshowqq.equals("1")){
            tv_qq.setText(rules.get(1).getValue()+"");
        }else{
            layout_qq.setVisibility(View.GONE);
        }
        String isshowweixin = rules.get(2).getIsshow();
        if(isshowweixin.equals("1")){
            tv_weixin.setText(rules.get(2).getValue()+"");
        }else{
            layout_weixin.setVisibility(View.GONE);
        }
        String isshowimage = rules.get(3).getIsshow();
        if(isshowimage.equals("1")){
            ImageServerApi.showURLBigImage(riv_user_image, rules.get(3).getValue());//用户头像
        }else{
            layout_avater.setVisibility(View.GONE);
        }
        String isshowname = rules.get(4).getIsshow();
        if(isshowname.equals("1")){
            tv_nike_name.setText(rules.get(4).getValue());//用户昵称
        }else{
            layout_nike_name.setVisibility(View.GONE);
        }
        String isshowsignature = rules.get(5).getIsshow();
        if(isshowsignature.equals("1")){
            tv_signature.setText(rules.get(5).getValue());//个性签名
        }else{
            layout_signature.setVisibility(View.GONE);
        }

        String isshowsex = rules.get(6).getIsshow();
        String value = rules.get(6).getValue();//性别
        if(isshowsex.equals("1")){
            if (value == "1") {
                tv_sex.setText("男");
            } else {
                tv_sex.setText("女");
            }
        }else{
            layout_sex.setVisibility(View.GONE);
        }
        String isshowbirthday = rules.get(7).getIsshow();
        if(isshowbirthday.equals("1")){
            tv_birthday.setText(rules.get(7).getValue());//出生年月
        }else{
            layout_birthday.setVisibility(View.GONE);
        }
        String isshowstar = rules.get(8).getIsshow();
        if(isshowstar.equals("1")){
            tv_star_sign.setText(rules.get(8).getValue());//星座
        }else{
            layout_star_sign.setVisibility(View.GONE);
        }
        String isshowbirthpace = rules.get(9).getIsshow();
        if(isshowbirthpace.equals("1")){
            tv_birthplace.setText(rules.get(9).getValue());//籍贯
        }else{
            layout_birthplace.setVisibility(View.GONE);
        }
        String isshowaddress = rules.get(10).getIsshow();
        if(isshowaddress.equals("1")){
            tv_address.setText(rules.get(10).getValue());//现居
        }else{
            layout_address.setVisibility(View.GONE);
        }
        String isshowjob = rules.get(11).getIsshow();
        if(isshowjob.equals("1")){
            tv_job.setText(rules.get(11).getValue());//职业
        }else{
            layout_job.setVisibility(View.GONE);
        }
        String isshowhobby = rules.get(12).getIsshow();
        if(isshowhobby.equals("1")){
            tv_hobby.setText(rules.get(12).getValue());//兴趣爱好
        }else{
            layout_hobby.setVisibility(View.GONE);
        }
        String isshowself = rules.get(13).getIsshow();
        if(isshowself.equals("1")){
            tv_self_image.setText(rules.get(13).getValue());//自我印象
        }else{
            layout_self_image.setVisibility(View.GONE);
        }
        String isshowhouse = rules.get(14).getIsshow();
        if(isshowhouse.equals("1")){
            tv_ishouse.setText(rules.get(14).getValue());//是否有房
        }else{
            layout_ishouse.setVisibility(View.GONE);
        }
        String isshowmarital = rules.get(15).getIsshow();
        if(isshowmarital.equals("1")){
            tv_marital_status.setText(rules.get(15).getValue());//婚姻状况
        }else{
            layout_marital_status.setVisibility(View.GONE);
        }
        String isshowdistance = rules.get(16).getIsshow();
        if(isshowdistance.equals("1")){
            tv_distance_love.setText(rules.get(16).getValue());//接受异地恋
        }else{
            layout_distance_love.setVisibility(View.GONE);
        }
        String isshowlike = rules.get(17).getIsshow();
        if(isshowlike.equals("1")){
            tv_like_sex.setText(rules.get(17).getValue());//喜欢的异性
        }else{
            layout_like_sex.setVisibility(View.GONE);
        }
        String isshowpremar = rules.get(18).getIsshow();
        if(isshowpremar.equals("1")){
            tv_premarital_sex.setText(rules.get(18).getValue());//婚前性行为
        }else{
            layout_premarital_sex.setVisibility(View.GONE);
        }
//        ImageServerApi.showURLBigImage(riv_like_image, data1.getLike_image());//用户头像


    }
}
