package com.moonsister.tcjy.my.widget;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;

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
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

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

    }

    @Override
    public void success(PersonalMessageBean getPersonalBean){
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


        JSONObject json = new JSONObject();

    }

    @Override
    public void person() {

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

    @OnClick({R.id.image_back,R.id.layout_avater,R.id.layout_like,R.id.layout_nike_name,R.id.layout_signature,R.id.layout_birthday,R.id.layout_star_sign,R.id.layout_birthplace,R.id.layout_address,R.id.layout_job,R.id.layout_hobby,R.id.layout_self_image,R.id.layout_ishouse,R.id.layout_marital_status,R.id.layout_distance_love,R.id.layout_like_sex,R.id.layout_premarital_sex,R.id.baocun})
    public void onClick(View view) throws JSONException {
        switch (view.getId()){
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
                JSONObject jsonobj=new JSONObject();
                jsonobj.put("nickname", data1.getNickname());
                jsonobj.put("sex",data1.getSex());
                jsonobj.put("face", data.get(1).getEdit());
                jsonobj.put("isvip",data.get(1).getIsvip());
                jsonobj.put("value",message);

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
                PersonalMessageBean userJsonBean1 = new PersonalMessageBean();
                userJsonBean1.setUserId(2);
                userJsonBean1.setUserName("message2");
                userBeans.add(userJsonBean1);
                JSONObject jsonobj1=new JSONObject();
                jsonobj1.put("field", data.get(2).getField());
                jsonobj1.put("name",data.get(2).getName());
                jsonobj1.put("edit", data.get(2).getEdit());
                jsonobj1.put("isvip",data.get(2).getIsvip());
                jsonobj1.put("value",message2);
                break;
            case R.id.layout_nike_name://昵称

               tv_nike_name.requestFocus();
                String str=tv_nike_name.getText().toString();
                PersonalMessageBean userJsonBean3 = new PersonalMessageBean();
                userJsonBean3.setUserId(3);
                userJsonBean3.setUserName("str");
                userBeans.add(userJsonBean3);

                break;
            case R.id.layout_signature://个性签名
                tv_signature.requestFocus();
                String signature=tv_signature.getText().toString();
                PersonalMessageBean userJsonBean4 = new PersonalMessageBean();
                userJsonBean4.setUserId(4);
                userJsonBean4.setUserName("signature");
                userBeans.add(userJsonBean4);
                break;

            case R.id.layout_birthday://出生年月
                ActivityUtils.startBirthdayActivity("s");



                //同样，在读取SharedPreferences数据前要实例化出一个SharedPreferences对象

//                DatePickerDialog  dateDlg = new DatePickerDialog(PersonalReviseActivity.this,
//                        d,
//                        dateAndTime.get(Calendar.YEAR),
//                        dateAndTime.get(Calendar.MONTH),
//                        dateAndTime.get(Calendar.DAY_OF_MONTH));
//
//                dateDlg.show();
//
//                Log.d(TAG,"Date show");

                break;
            case R.id.layout_star_sign://星座
//                ActivityUtils.startBirthdayActivity("s");
                SharedPreferences sharedPreferences= getSharedPreferences("my",
                        Activity.MODE_PRIVATE);
// 使用getString方法获得value，注意第2个参数是value的默认值
                String name =sharedPreferences.getString("bir", "");
                habit =sharedPreferences.getString("con", "");
                tv_birthday.setText(name);
                tv_star_sign.setText(habit+"");
//                tv_star_sign.requestFocus();
//                String star_sign=tv_star_sign.getText().toString();
//                PersonalMessageBean userJsonBean6 = new PersonalMessageBean();
//                userJsonBean6.setUserId(6);
//                userJsonBean6.setUserName("star_sign");
//                userBeans.add(userJsonBean6);
                break;
            case R.id.layout_birthplace://籍贯
                tv_birthplace.requestFocus();
                String birthplace=tv_birthplace.getText().toString();
                PersonalMessageBean userJsonBean7 = new PersonalMessageBean();
                userJsonBean7.setUserId(7);
                userJsonBean7.setUserName("birthplace");
                userBeans.add(userJsonBean7);
                break;
            case R.id.layout_address://现居
                tv_address.requestFocus();
                String address=tv_address.getText().toString();
                PersonalMessageBean userJsonBean8 = new PersonalMessageBean();
                userJsonBean8.setUserId(8);
                userJsonBean8.setUserName("address");
                userBeans.add(userJsonBean8);
                break;
            case R.id.layout_job://职业
                tv_job.requestFocus();
                String job=tv_job.getText().toString();
                PersonalMessageBean userJsonBean9 = new PersonalMessageBean();
                userJsonBean9.setUserId(9);
                userJsonBean9.setUserName("job");
                userBeans.add(userJsonBean9);
                break;
            case R.id.layout_hobby://兴趣爱好
                tv_hobby.requestFocus();
                String hobby=tv_hobby.getText().toString();
                PersonalMessageBean userJsonBean10 = new PersonalMessageBean();
                userJsonBean10.setUserId(10);
                userJsonBean10.setUserName("hobby");
                userBeans.add(userJsonBean10);
                break;
            case R.id.layout_self_image://自我印象
                tv_self_image.requestFocus();
                String self_image=tv_self_image.getText().toString();
                PersonalMessageBean userJsonBean11 = new PersonalMessageBean();
                userJsonBean11.setUserId(11);
                userJsonBean11.setUserName("tv_self_image");
                userBeans.add(userJsonBean11);
                break;
            case R.id.layout_ishouse://是否有房
                menuWindow = new SelectPicPopupWindow(PersonalReviseActivity.this, itemsOnClick);
                menuWindow.showAtLocation(PersonalReviseActivity.this.findViewById(R.id.layout_ishouse), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置

                break;
            case R.id.layout_marital_status://婚姻状况
                mMenuViewmarital = new PopWindowMarital(PersonalReviseActivity.this, itemsOnClickMarital);
                mMenuViewmarital.showAtLocation(PersonalReviseActivity.this.findViewById(R.id.tv_marital_status), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
//                tv_marital_status.requestFocus();
//                String marital_status=tv_marital_status.getText().toString();
//                PersonalMessageBean userJsonBean13 = new PersonalMessageBean();
//                userJsonBean13.setUserId(13);
//                userJsonBean13.setUserName("marital_status");
//                userBeans.add(userJsonBean13);
                break;
            case R.id.layout_distance_love://接受异地恋
                mMenuViewdistance = new PopWindowDistance(PersonalReviseActivity.this, itemsOnClickDistance);
                mMenuViewdistance.showAtLocation(PersonalReviseActivity.this.findViewById(R.id.tv_distance_love), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置

                break;

            case R.id.layout_like_sex://喜欢的异性
                tv_like_sex.requestFocus();
                String like_sex=tv_like_sex.getText().toString();
                PersonalMessageBean userJsonBean15 = new PersonalMessageBean();
                userJsonBean15.setUserId(15);
                userJsonBean15.setUserName("like_sex");
                userBeans.add(userJsonBean15);
                break;
            case R.id.layout_premarital_sex://婚前性行为
                mMenuViewpremarital = new PopWindowPremarital(PersonalReviseActivity.this, itemsOnClickPrematital);
                mMenuViewpremarital.showAtLocation(PersonalReviseActivity.this.findViewById(R.id.tv_premarital_sex), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置

                break;
            case R.id.baocun:

                break;
        }
    }
    DateFormat fmtDate = new java.text.SimpleDateFormat("yyyy-MM-dd");
    Calendar dateAndTime = Calendar.getInstance(Locale.CHINA);
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener()
    {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            //修改日历控件的年，月，日
            //这里的year,monthOfYear,dayOfMonth的值与DatePickerDialog控件设置的最新值一致
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            //将页面TextView的显示更新为最新时间
            upDateDate();

        }
    };
    private void upDateDate() {
        tv_birthday.setText(fmtDate.format(dateAndTime.getTime()));

    }
    //为婚前性行为弹出窗口实现监听类
    private OnClickListener  itemsOnClickPrematital = new OnClickListener(){

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
    private OnClickListener  itemsOnClickDistance = new OnClickListener(){

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
    private OnClickListener  itemsOnClick = new OnClickListener(){

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
    private OnClickListener  itemsOnClickMarital = new OnClickListener(){
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

    private void changeArrayDateToJson() { //把一个集合转换成json格式的字符串
        jsonArray=null;
        object=null;
        jsonArray = new JSONArray();
        object=new JSONObject();
        for (int i = 0; i < userBeans.size(); i++) { //遍历上面初始化的集合数据，把数据加入JSONObject里面
            object2 = new JSONObject();//一个user对象，使用一个JSONObject对象来装
            try {
                object2.put("userId", userBeans.get(i).getUserId()); //从集合取出数据，放入JSONObject里面 JSONObject对象和map差不多用法,以键和值形式存储数据
                object2.put("userName", userBeans.get(i).getUserName());
                jsonArray.put(object2); //把JSONObject对象装入jsonArray数组里面
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        try {
            object.put("userDate", jsonArray); //再把JSONArray数据加入JSONObject对象里面(数组也是对象)
//object.put("time", "2013-11-14"); //这里还可以加入数据，这样json型字符串，就既有集合，又有普通数据
        } catch (JSONException e) {
            e.printStackTrace();
        }
        jsonString=null;
        jsonString = object.toString(); //把JSONObject转换成json格式的字符串
//        textView.setText(jsonString);
        Log.i("hck", "转换成json字符串: " + jsonString);

    }
}
