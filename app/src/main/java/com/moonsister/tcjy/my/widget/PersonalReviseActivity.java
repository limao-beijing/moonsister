package com.moonsister.tcjy.my.widget;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.moonsister.tcjy.ImageServerApi;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.bean.PersonalMessageBean;
import com.moonsister.tcjy.bean.UserInfoChangeBean;
import com.moonsister.tcjy.bean.personalBean;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.login.widget.SelectPicPopupActivity;
import com.moonsister.tcjy.my.persenter.PersonalActivityPersenter;
import com.moonsister.tcjy.my.persenter.PersonalActivityPersenterImpl;
import com.moonsister.tcjy.my.view.PersonalActivityView;
import com.moonsister.tcjy.my.view.UserInfoChangeActivityView;
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
import io.rong.imkit.MsgContent;
import rx.android.plugins.RxAndroidSchedulersHook;

/**
 * Created by x on 2016/9/5.
 */
public class PersonalReviseActivity extends BaseActivity implements PersonalActivityView{
    @Bind(R.id.riv_user_image)//头像
            ImageView riv_user_image;
    @Bind(R.id.riv_like_image)//背景墙
            ImageView riv_like_image;
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
    private PersonalActivityPersenter presenter;
    List<PersonalMessageBean.DataBean.RulesBean> data;
    PersonalMessageBean.DataBean.BaseinfoBean data1;
    PersonalMessageBean.DataBean.DlistBean data2;
    PersonalMessageBean.DataBean.VipinfoBean data3;
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
    JSONObject jsonobj;//头像  背景图
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

    @Override
    protected View setRootContentView() {

        return UIUtils.inflateLayout(R.layout.personalreviseactivity);
    }

    @Override
    protected void initView() {
        presenter = new PersonalActivityPersenterImpl();
        presenter.attachView(this);
        userBeans = new ArrayList<PersonalMessageBean>();
        presenter.sendPersonalMessage(145655);
//        presenter.sendPersonalResviceMessage(145655);

    }

    @Override
    public void success(PersonalMessageBean getPersonalBean) {
        data = getPersonalBean.getData().getRules();
        data1 = getPersonalBean.getData().getBaseinfo();
        data2 = getPersonalBean.getData().getDlist();
        data3 = getPersonalBean.getData().getVipinfo();

        ImageServerApi.showURLBigImage(riv_user_image, data1.getFace());//用户头像
        ImageServerApi.showURLBigImage(riv_like_image, data1.getLike_image());//用户头像
        tv_nike_name.setText(data1.getNickname());//用户昵称
        tv_signature.setText(data.get(1).getValue());
        String value = data.get(2).getValue();
        if (value == "1") {
            tv_sex.setText("男");
        } else {
            tv_sex.setText("女");
        }
        tv_birthday.setText(data.get(3).getValue());
        tv_star_sign.setText(data.get(4).getValue());
        tv_birthplace.setText(data.get(5).getValue());
        tv_address.setText(data.get(6).getValue());
        tv_job.setText(data.get(7).getValue());
        tv_hobby.setText(data.get(8).getValue());
        tv_self_image.setText(data.get(9).getValue());
        tv_ishouse.setText(data.get(10).getValue());
        tv_marital_status.setText(data.get(11).getValue());
        tv_distance_love.setText(data.get(12).getValue());
        tv_like_sex.setText(data.get(13).getValue());
        tv_premarital_sex.setText(data.get(14).getValue());

    }

    @Override
    public void person() {

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

    @OnClick({R.id.image_back, R.id.layout_avater, R.id.layout_like, R.id.layout_nike_name, R.id.layout_signature, R.id.layout_sex, R.id.layout_birthday, R.id.layout_star_sign, R.id.layout_birthplace, R.id.layout_address, R.id.layout_job, R.id.layout_hobby, R.id.layout_self_image, R.id.layout_ishouse, R.id.layout_marital_status, R.id.layout_distance_love, R.id.layout_like_sex, R.id.layout_premarital_sex, R.id.baocun})
    public void onClick(View view)  {
        switch (view.getId()) {
            case R.id.image_back:
                this.finish();
//                changeArrayDateToJson();
//                presenter.sendUserJson(jsonString);
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


                break;
            case R.id.layout_like://背景墙
                ActivityUtils.startActivity(SelectPicPopupActivity.class);
                RxBus.with(this)
                        .setEvent(Events.EventEnum.GET_PHOTO)
                        .setEndEvent(ActivityEvent.DESTROY)
                        .onNext((events) -> {
                            message2 = (String) events.message;
                            LogUtils.e(RZFirstActivity.class, "pic_path : " + message2);
                            avater = message2;
                            ImageServerApi.showURLSamllImage(riv_like_image, message2);
                        }).create();

                jsonobj = new JSONObject();
                try {
                    jsonobj.put("nickname", data1.getNickname());
                    jsonobj.put("sex", data1.getSex());
                    jsonobj.put("face", message);
                    jsonobj.put("like_image", message2);
                    jsonobj.put("birthday", data1.getBirthday());
                    jsonobj.put("signature", data1.getSignature());
                    jsonobj.put("age", data1.getAge());
                    jsonobj.put("vip_level", data1.getVip_level());
                    jsonobj.put("isauth", data1.getIsauth());
                    jsonobj.put("isfollow", data1.getIsfollow());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.layout_nike_name://昵称

                tv_nike_name.requestFocus();
                String str = tv_nike_name.getText().toString();

                jsonobjname = new JSONObject();
                try {
                    jsonobjname.put("field", data.get(1).getField());
                    jsonobjname.put("name", data.get(1).getName());
                    jsonobjname.put("edit", data.get(1).getEdit());
                    jsonobjname.put("isvip", data.get(1).getIsvip());
                    jsonobjname.put("value", str);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                break;
            case R.id.layout_signature://个性签名
                tv_signature.requestFocus();
                String signature = tv_signature.getText().toString();
                jsonobjsignature = new JSONObject();
                try {
                    jsonobjsignature.put("field", data.get(2).getField());
                    jsonobjsignature.put("name", data.get(2).getName());
                    jsonobjsignature.put("edit", data.get(2).getEdit());
                    jsonobjsignature.put("isvip", data.get(2).getIsvip());
                    jsonobjsignature.put("value", signature);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.layout_sex:
                jsonobjsex = new JSONObject();
                try {
                    jsonobjsex.put("field", data.get(3).getField());
                    jsonobjsex.put("name", data.get(3).getName());
                    jsonobjsex.put("edit", data.get(3).getEdit());
                    jsonobjsex.put("isvip", data.get(3).getIsvip());
                    jsonobjsex.put("value", data.get(3).getValue());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.layout_birthday://出生年月
                startActivityForResult(new Intent(PersonalReviseActivity.this, BirthdayActivity.class), 1);

                jsonobjbirthday = new JSONObject();
                try {
                    jsonobjbirthday.put("field", data.get(4).getField());
                    jsonobjbirthday.put("name", data.get(4).getName());
                    jsonobjbirthday.put("edit", data.get(4).getEdit());
                    jsonobjbirthday.put("isvip", data.get(4).getIsvip());
                    jsonobjbirthday.put("value", result);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.layout_star_sign://星座
                jsonobjstarsign = new JSONObject();
                try {
                    jsonobjstarsign.put("field", data.get(5).getField());
                    jsonobjstarsign.put("name", data.get(5).getName());
                    jsonobjstarsign.put("edit", data.get(5).getEdit());
                    jsonobjstarsign.put("isvip", data.get(5).getIsvip());
                    jsonobjstarsign.put("value", love);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.layout_birthplace://籍贯
                tv_birthplace.requestFocus();
                String birthplace = tv_birthplace.getText().toString();
                jsonobjbirthplace = new JSONObject();
                try {
                    jsonobjbirthplace.put("field", data.get(6).getField());
                    jsonobjbirthplace.put("name", data.get(6).getName());
                    jsonobjbirthplace.put("edit", data.get(6).getEdit());
                    jsonobjbirthplace.put("isvip", data.get(6).getIsvip());
                    jsonobjbirthplace.put("value", birthplace);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.layout_address://现居
                tv_address.requestFocus();
                String address = tv_address.getText().toString();
                jsonobjaddress = new JSONObject();
                try {
                    jsonobjaddress.put("field", data.get(7).getField());
                    jsonobjaddress.put("name", data.get(7).getName());
                    jsonobjaddress.put("edit", data.get(7).getEdit());
                    jsonobjaddress.put("isvip", data.get(7).getIsvip());
                    jsonobjaddress.put("value", address);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.layout_job://职业
                tv_job.requestFocus();
                String job = tv_job.getText().toString();
                jsonobjjob = new JSONObject();
                try {
                    jsonobjjob.put("field", data.get(8).getField());
                    jsonobjjob.put("name", data.get(8).getName());
                    jsonobjjob.put("edit", data.get(8).getEdit());
                    jsonobjjob.put("isvip", data.get(8).getIsvip());
                    jsonobjjob.put("value", job);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.layout_hobby://兴趣爱好
                tv_hobby.requestFocus();
                String hobby = tv_hobby.getText().toString();
                jsonobjhobby = new JSONObject();
                try {
                    jsonobjhobby.put("field", data.get(9).getField());
                    jsonobjhobby.put("name", data.get(9).getName());
                    jsonobjhobby.put("edit", data.get(9).getEdit());
                    jsonobjhobby.put("isvip", data.get(9).getIsvip());
                    jsonobjhobby.put("value", hobby);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.layout_self_image://自我印象
                tv_self_image.requestFocus();
                String self_image = tv_self_image.getText().toString();
                jsonobjself = new JSONObject();
                try {
                    jsonobjself.put("field", data.get(10).getField());
                    jsonobjself.put("name", data.get(10).getName());
                    jsonobjself.put("edit", data.get(10).getEdit());
                    jsonobjself.put("isvip", data.get(10).getIsvip());
                    jsonobjself.put("value", self_image);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.layout_ishouse://是否有房
                menuWindow = new SelectPicPopupWindow(PersonalReviseActivity.this, itemsOnClick);
                menuWindow.showAtLocation(PersonalReviseActivity.this.findViewById(R.id.layout_ishouse), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
                ishouse = tv_ishouse.getText().toString();
                jsonobjishouse = new JSONObject();
                try {
                    jsonobjishouse.put("fileld", data.get(11).getField());
                    jsonobjishouse.put("name", data.get(11).getName());
                    jsonobjishouse.put("edit", data.get(11).getEdit());
                    jsonobjishouse.put("isvip", data.get(11).getIsvip());
                    jsonobjishouse.put("value", ishouse);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.layout_marital_status://婚姻状况
                mMenuViewmarital = new PopWindowMarital(PersonalReviseActivity.this, itemsOnClickMarital);
                mMenuViewmarital.showAtLocation(PersonalReviseActivity.this.findViewById(R.id.tv_marital_status), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
                marital_status = tv_marital_status.getText().toString();
                jsonobjmarital = new JSONObject();
                try {
                    jsonobjmarital.put("field", data.get(12).getField());
                    jsonobjmarital.put("name", data.get(12).getName());
                    jsonobjmarital.put("edit", data.get(12).getEdit());
                    jsonobjmarital.put("isvip", data.get(12).getIsvip());
                    jsonobjmarital.put("value", marital_status);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.layout_distance_love://接受异地恋
                mMenuViewdistance = new PopWindowDistance(PersonalReviseActivity.this, itemsOnClickDistance);
                mMenuViewdistance.showAtLocation(PersonalReviseActivity.this.findViewById(R.id.tv_distance_love), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
                distance = tv_distance_love.getText().toString();
                jsonobjdistance = new JSONObject();
                try {
                    jsonobjdistance.put("field", data.get(13).getField());
                    jsonobjdistance.put("name", data.get(13).getName());
                    jsonobjdistance.put("edit", data.get(13).getEdit());
                    jsonobjdistance.put("isvip", data.get(13).getIsvip());
                    jsonobjdistance.put("value", distance);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.layout_like_sex://喜欢的异性
                tv_like_sex.requestFocus();
                String like_sex = tv_like_sex.getText().toString();
                jsonobjlike = new JSONObject();
                try {
                    jsonobjlike.put("field", data.get(14).getField());
                    jsonobjlike.put("name", data.get(14).getName());
                    jsonobjlike.put("edit", data.get(14).getEdit());
                    jsonobjlike.put("isvip", data.get(14).getIsvip());
                    jsonobjlike.put("value", like_sex);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.layout_premarital_sex://婚前性行为
                mMenuViewpremarital = new PopWindowPremarital(PersonalReviseActivity.this, itemsOnClickPrematital);
                mMenuViewpremarital.showAtLocation(PersonalReviseActivity.this.findViewById(R.id.tv_premarital_sex), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
                premarital_sex = tv_premarital_sex.getText().toString();
                jsonobjpremarital = new JSONObject();
                try {
                    jsonobjpremarital.put("field", data.get(15).getField());
                    jsonobjpremarital.put("name", data.get(15).getName());
                    jsonobjpremarital.put("edit", data.get(15).getEdit());
                    jsonobjpremarital.put("isvip", data.get(15).getIsvip());
                    jsonobjpremarital.put("value", premarital_sex);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.baocun:

                JSONArray jsonarry = new JSONArray();
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
                JSONArray jsonarry2 = new JSONArray();
                jsonarry2.put(jsonarry);
                jsonarry2.put(jsonobj);
                String ss = jsonarry2.toString();
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

}
