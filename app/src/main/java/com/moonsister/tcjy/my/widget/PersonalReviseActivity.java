package com.moonsister.tcjy.my.widget;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.moonsister.tcjy.ImageServerApi;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.bean.PersonalReviseMessageBean;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.login.widget.SelectPicPopupActivity;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.my.persenter.PersonalReviseActivityPersenter;
import com.moonsister.tcjy.my.persenter.PersonalReviseActivityPersenterImpl;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by x on 2016/9/5.
 */
public class PersonalReviseActivity extends BaseActivity implements PersonalReviseActivityView {
    @Bind(R.id.riv_user_image)//头像
            ImageView riv_user_image;
    @Bind(R.id.riv_like_image)//背景墙
            ImageView riv_like_image;
    @Bind(R.id.tv_nike_name)//昵称
            EditText tv_nike_name;
    @Bind(R.id.tv_signature)//个性签名
            EditText tv_signature;
    @Bind(R.id.tv_sex)//性别
            TextView tv_sex;
    @Bind(R.id.tv_birthday)//出生年月
            TextView tv_birthday;
    @Bind(R.id.tv_star_sign)//星座
            TextView tv_star_sign;
    @Bind(R.id.tv_hight)//身高
            TextView tv_hight;
    @Bind(R.id.tv_weight)//体重
            TextView tv_weight;
    @Bind(R.id.tv_money_pay)//月薪
            TextView tv_money_pay;
    @Bind(R.id.tv_educational_background)//学历
            TextView tv_educational_background;
    @Bind(R.id.tv_birthplace)//籍贯
            TextView tv_birthplace;
    @Bind(R.id.tv_birthplace1)
    TextView tv_birthplace1;
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
    @Bind(R.id.tv_take_delivery)//收货地址
            TextView tv_take_delivery;
    @Bind(R.id.tv_zip_code)//邮编
            TextView tv_zip_code;
    @Bind(R.id.image_back)//返回
            ImageView image_back;
    @Bind(R.id.baocun)//保存
            TextView baocun;
    @Bind(R.id.yu_liu)//预留项1
            TextView yu_liu;
    @Bind(R.id.yu_liu1)//预留项2
            TextView yu_liu1;
    @Bind(R.id.yu_liu2)//预留项3
            TextView yu_liu2;
    PersonalReviseActivityPersenter presenter;
    String message;//头像路径
    String like_backgroud;//背景图路径
    String result;//年龄
    String love;//星座
    JSONObject jsonmobile;//手机
    JSONObject jsonqq;//qq
    JSONObject jsonweixin;//微信
    JSONObject jsonavater;//头像  背景图
    JSONObject jsonlike;//背景
    JSONObject jsonobjname;//昵称
    JSONObject jsonobjsignature;//个性签名
    JSONObject jsonobjsex;//性别
    JSONObject jsonobjbirthday;//出生日期
    JSONObject jsonobjstarsign;//星座
    JSONObject jsonobjhight;//身高
    JSONObject jsonobjweight;//体重
    JSONObject jsonobjmoney;//月薪
    JSONObject jsonobjducational;//学历
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
    JSONObject jsonobjtake_delivery;//收货地址
    JSONObject jsonobjZip;//邮编
    String ishouse;String marital_status;String distance;String premarital_sex;String hight;String value;
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
    @Bind(R.id.layout_like)//背景墙
            RelativeLayout layout_like;
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
    @Bind(R.id.layout_hight)//身高layout
            RelativeLayout layout_hight;
    @Bind(R.id.layout_weight)//体重layout
            RelativeLayout layout_weight;
    @Bind(R.id.layout_money_pay)//月薪layout
            RelativeLayout layout_money_pay;
    @Bind(R.id.layout_educational_background)//学历layout
            RelativeLayout layout_educational_background;
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
    @Bind(R.id.layout_take_delivery)//收货地址layout
            RelativeLayout layout_take_delivery;
    @Bind(R.id.layout_zip_code)//邮编
            RelativeLayout layout_zip_code;
    @Bind(R.id.layout_yuliu)//预留项1
            RelativeLayout layout_yuliu;
    @Bind(R.id.layout_yuliu1)//预留项2
            RelativeLayout layout_yuliu1;
    @Bind(R.id.layout_yuliu2)//预留项3
            RelativeLayout layout_yuliu2;
    String isshow;String isshowqq;String isshowweixin;String isshowimage;String isshowlikeimage;String isshowname;String isshowsignature;String isshowsex;
    String isshowbirthday;String isshowstar;String isshowbirthpace;String isshowaddress;String isshowjob;String isshowhobby;String isshowself;
    String isshowmarital;String isshowdistance;String isshowlike;String isshowpremar;String isshowhight;String isshowwight;String isshowmoneypay;
    String isshoweducationalbackground;String isshowttakedelivery;String isshowzipcode;String isshowyuliu;String isshowyuliu1;String isshowyuliu2;
    private String[] province;
    private String[] city, city1;
    private final int[] Tag = {0, 22, 35, 57, 71, 78, 78, 78, 78};//每次记录在arrays.xml中一
    //	个省份开始的第一个城市的位置
    private String provinceName, cityName;
    AlertDialog dlg;
    AlertDialog d;
    StringBuilder sb;

    @Override
    protected View setRootContentView() {
        return UIUtils.inflateLayout(R.layout.personalreviseactivity);
    }

    @Override
    protected void initView() {
        presenter = new PersonalReviseActivityPersenterImpl();
        presenter.attachView(this);
        presenter.sendPersonalReviseMessage(UserInfoManager.getInstance().getUid());

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

    @OnClick({R.id.image_back, R.id.layout_like, R.id.layout_mobile, R.id.layout_qq, R.id.layout_weixin, R.id.layout_avater, R.id.layout_nike_name, R.id.layout_signature, R.id.layout_sex, R.id.layout_birthday, R.id.layout_star_sign,
            R.id.layout_birthplace, R.id.layout_address, R.id.layout_job, R.id.layout_hobby, R.id.layout_self_image, R.id.layout_ishouse, R.id.layout_marital_status, R.id.layout_distance_love, R.id.layout_like_sex, R.id.layout_premarital_sex,
            R.id.layout_hight, R.id.layout_weight, R.id.layout_money_pay, R.id.layout_educational_background, R.id.layout_take_delivery, R.id.layout_zip_code, R.id.baocun})
    public void onClick(View view) {
        if (rules == null) {
            return;
        }
        switch (view.getId()) {
            case R.id.image_back:
                this.finish();
                break;
            case R.id.layout_mobile:

                break;
            case R.id.layout_qq:

                break;
            case R.id.layout_weixin:

                break;
            case R.id.layout_avater://头像
                ActivityUtils.startActivity(SelectPicPopupActivity.class);
                RxBus.with(this)
                        .setEvent(Events.EventEnum.GET_PHOTO)
                        .setEndEvent(ActivityEvent.DESTROY)
                        .onNext((events) -> {
                            message = (String) events.message;
                            LogUtils.e(RZFirstActivity.class, "pic_path : " + message);
                            ImageServerApi.showURLSamllImage(riv_user_image, message);
                        }).create();

                break;
            case R.id.layout_like://背景
                ActivityUtils.startActivity(SelectPicPopupActivity.class);
                RxBus.with(this)
                        .setEvent(Events.EventEnum.GET_PHOTO)
                        .setEndEvent(ActivityEvent.DESTROY)
                        .onNext((events) -> {
                            like_backgroud = (String) events.message;
                            LogUtils.e(RZFirstActivity.class, "pic_path : " + message);
                            ImageServerApi.showURLSamllImage(riv_user_image, message);
                        }).create();
                break;
            case R.id.layout_nike_name://昵称
                tv_nike_name.requestFocus();

                break;
            case R.id.layout_signature://个性签名
                tv_signature.requestFocus();

                break;
            case R.id.layout_sex:

                break;
            case R.id.layout_birthday://出生年月
                Calendar c = Calendar.getInstance();
                c.setTimeInMillis(System.currentTimeMillis());
                final int nowYear = c.get(Calendar.YEAR);
                DatePickerDialog dialog = new DatePickerDialog(PersonalReviseActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        c.set(year, monthOfYear, dayOfMonth);
                        tv_birthday.setText(DateFormat.format("yyy-MM-dd", c));
                        if (year > nowYear) {
                            tv_star_sign
                                    .setText("你还未出生,星座:"
                                            + getStarSeat(monthOfYear + 1,
                                            dayOfMonth));

                        } else {
                            tv_star_sign.setText(getStarSeat(monthOfYear + 1, dayOfMonth));
                        }
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();

                break;
            case R.id.layout_star_sign://星座

                break;
            case R.id.layout_hight://身高
                View outerView = LayoutInflater.from(this).inflate(R.layout.wheel_view, null);
                ListView wheel_list = (ListView) outerView.findViewById(R.id.wheel_list);
                ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
                for (int i = 150; i < 195; i++) {
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("wheel_viewnumber", i + "CM");
                    mylist.add(map);
                }
                SimpleAdapter adapter = new SimpleAdapter(this, mylist, R.layout.wheel_viewitem,
                        new String[]{"wheel_viewnumber"}, new int[]{R.id.wheel_viewnumber});
                wheel_list.setAdapter(adapter);
                new AlertDialog.Builder(this)
                        .setTitle("请选择身高")
                        .setView(outerView)
                        .setPositiveButton("OK", null)
                        .show();
                wheel_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        view.setBackgroundResource(R.color.red_eb2616);
                        HashMap<String, String> map = (HashMap<String, String>) adapterView.getItemAtPosition(i);
                        tv_hight.setText(map.get("wheel_viewnumber"));
                    }
                });
                break;
            case R.id.layout_weight://体重
                View outerViewweight = LayoutInflater.from(this).inflate(R.layout.wheel_view, null);
                ListView wheel_listweight = (ListView) outerViewweight.findViewById(R.id.wheel_list);
                ArrayList<HashMap<String, String>> wheel_weight = new ArrayList<HashMap<String, String>>();
                for (int i = 30; i < 100; i++) {
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("wheel_viewnumber", i + "KG");
                    wheel_weight.add(map);
                }
                SimpleAdapter adapterweight = new SimpleAdapter(this, wheel_weight, R.layout.wheel_viewitem,
                        new String[]{"wheel_viewnumber"}, new int[]{R.id.wheel_viewnumber});
                wheel_listweight.setAdapter(adapterweight);
                new AlertDialog.Builder(this)
                        .setTitle("请选择体重")
                        .setView(outerViewweight)
                        .setPositiveButton("OK", null)
                        .show();
                wheel_listweight.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        view.setBackgroundResource(R.color.red_eb2616);
                        HashMap<String, String> map = (HashMap<String, String>) adapterView.getItemAtPosition(i);
                        tv_weight.setText(map.get("wheel_viewnumber"));
                    }
                });
                break;
            case R.id.layout_money_pay://月薪
                String[] money_pay = {"3000以下", "3000-5000", "5000-10000", "10000-15000", "15000-20000", "20000以上"};
                AlertDialog.Builder builder = new AlertDialog.Builder(this);  //先得到构造器
                builder.setTitle("请选择月薪"); //设置标题
                builder.setPositiveButton("OK", null);
                builder.setItems(money_pay, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Toast.makeText(PersonalReviseActivity.this, money_pay[which], Toast.LENGTH_SHORT).show();
                        tv_money_pay.setText(money_pay[which]);

                    }
                });
                builder.create().show();

                break;
            case R.id.layout_educational_background://学历
                String[] educational = {"专科以下", "专科", "本科", "硕士", "博士及以上"};
                AlertDialog.Builder buildeder = new AlertDialog.Builder(this);  //先得到构造器
                buildeder.setTitle("请选择月薪"); //设置标题
                buildeder.setPositiveButton("OK", null);
                buildeder.setItems(educational, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Toast.makeText(PersonalReviseActivity.this, educational[which], Toast.LENGTH_SHORT).show();
                        tv_educational_background.setText(educational[which]);
                    }
                });
                buildeder.create().show();
                break;
            case R.id.layout_birthplace://籍贯
                province = getResources().getStringArray(R.array.Province);
                city = getResources().getStringArray(R.array.City);
                dlg = new AlertDialog.Builder(this)
                        .setTitle("选择省份").setItems(R.array.Province,new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                    /*返回选择城市*/
                                provinceName = province[which];
                                tv_birthplace.setText(provinceName);
                                city1 = getCity(Tag[which], Tag[which + 1]);
                                dlg.dismiss();
                            }
                        }).create();

                dlg.show();

                break;
            case R.id.layout_address://现居
                if (tv_birthplace.getText().toString().equals("选择省份")) {
                    city1 = city;
                }
            /*创建城市对话框*/
                dlg = new AlertDialog.Builder(this)
                        .setTitle("选择城市").setItems(city1, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                    /*返回选择城市*/
                                cityName = city1[which];
                                tv_address.setText(cityName);
                                dlg.dismiss();
                            }

                        }).create();
                dlg.show();
                break;
            case R.id.layout_job://职业
                tv_job.requestFocus();

                break;
            case R.id.layout_hobby://兴趣爱好
                String[] hobbyimge = {"电影", "音乐", "读书", "写作", "摄影", "旅游", "美食", "游戏", "逛街", "购物", "萌宠", "跳舞", "绘画", "运动", "其他"};
                View outerViewhobby = LayoutInflater.from(this).inflate(R.layout.wheel_gridview, null);
                GridView wheel_listhobby = (GridView) outerViewhobby.findViewById(R.id.wheel_grid);
                ArrayList<HashMap<String, String>> mylisthobby = new ArrayList<HashMap<String, String>>();
                for (int i = 0; i < hobbyimge.length; i++) {
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("wheel_viewnumber", hobbyimge[i]);
                    mylisthobby.add(map);
                }
                SimpleAdapter adapterhobyy = new SimpleAdapter(this, mylisthobby, R.layout.wheel_gridviewitem,
                        new String[]{"wheel_viewnumber"}, new int[]{R.id.gridview});
                wheel_listhobby.setAdapter(adapterhobyy);
                new AlertDialog.Builder(this)
                        .setTitle("请选择")
                        .setView(outerViewhobby)
                        .setPositiveButton("OK", null)
                        .show();
                sb = new StringBuilder();
                wheel_listhobby.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            sb.append(hobbyimge[i]+" ");
                        if(sb.length()<16){
                            tv_hobby.setText(sb.toString());
                            view.setBackgroundResource(R.color.red_eb2616);
                        }else{
                            return;
                        }
                    }
                });
                break;
            case R.id.layout_self_image://自我印象
                String[] self = {"男选项", "女选项"};
                String[] selfnan = {"幽默", "稳重", "活泼", "感性", "体贴", "憨厚", "爱宅", "闷骚", "好强", "冷静", "义气", "孝顺", "胆大", "负责任", "随和"};
                String[] selfgril = {"幽默", "活泼", "文静", "鬼马", "好动", "爱宅", "性感", "妩媚", "傲娇", "任性", "温柔", "体贴", "可爱", "胆大", "自来熟", "孝顺", "清纯"};
                View outerViewself = LayoutInflater.from(this).inflate(R.layout.wheel_nanguilgrid, null);
                GridView wheel_listnan = (GridView) outerViewself.findViewById(R.id.grid_nan);
                GridView wheel_listgril = (GridView) outerViewself.findViewById(R.id.grid_gril);
                TextView text_n= (TextView) outerViewself.findViewById(R.id.text_nan);
                TextView text_g= (TextView) outerViewself.findViewById(R.id.text_gril);
                if(value=="1"){
                    ArrayList<HashMap<String, String>> mylistnan = new ArrayList<HashMap<String, String>>();
                    for (int i = 0; i < selfnan.length; i++) {
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put("wheel_viewnumber", selfnan[i]);
                        mylistnan.add(map);
                    }
                    SimpleAdapter adapternan = new SimpleAdapter(this, mylistnan, R.layout.wheel_gridviewitem,
                            new String[]{"wheel_viewnumber"}, new int[]{R.id.gridview});
                    wheel_listnan.setAdapter(adapternan);
                    final StringBuffer stringb=new StringBuffer();
                    wheel_listnan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            stringb.append(selfnan[i]+" ");
                            if(stringb.length()<16){
                                tv_self_image.setText(stringb.toString());
                                view.setBackgroundResource(R.color.red_eb2616);
                            }else{
                                return;
                            }

                        }
                    });
                    wheel_listgril.setVisibility(View.GONE);
                    text_g.setVisibility(View.GONE);
                }else{
                    ArrayList<HashMap<String, String>> mylistgril = new ArrayList<HashMap<String, String>>();
                    for (int i = 0; i < selfgril.length; i++) {
                        HashMap<String, String> p = new HashMap<String, String>();
                        p.put("m", selfgril[i]);
                        mylistgril.add(p);
                    }
                    SimpleAdapter adaptergril = new SimpleAdapter(this, mylistgril, R.layout.wheel_gridviewitem,
                            new String[]{"m"}, new int[]{R.id.gridview});
                    wheel_listgril.setAdapter(adaptergril);
                    new AlertDialog.Builder(this)
                            .setTitle("请选择")
                            .setView(outerViewself)
                            .setPositiveButton("OK", null)
                            .show();

                    final StringBuffer stringBuffer=new StringBuffer();
                    wheel_listgril.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            stringBuffer.append(selfgril[i]+" ");
                            if(stringBuffer.length()<16){
                                tv_self_image.setText(stringBuffer.toString());
                                view.setBackgroundResource(R.color.red_eb2616);
                            }else{
                                return;
                            }

                        }
                    });
                    wheel_listnan.setVisibility(View.GONE);
                    text_n.setVisibility(View.GONE);
                }
                break;
            case R.id.layout_ishouse://是否有房
                String[] house = {"租房", "有房", "宿舍"};
                AlertDialog.Builder builderhouse = new AlertDialog.Builder(this);  //先得到构造器
                builderhouse.setTitle("请选择住房条件"); //设置标题
                builderhouse.setPositiveButton("OK", null);
                builderhouse.setItems(house, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Toast.makeText(PersonalReviseActivity.this, house[which], Toast.LENGTH_SHORT).show();
                        tv_ishouse.setText(house[which]);
                    }
                });
                builderhouse.create().show();
                break;
            case R.id.layout_marital_status://婚姻状况
                String[] marital = {"单身", "恋爱中", "已婚", "离异", "丧偶"};
                AlertDialog.Builder buildermarital = new AlertDialog.Builder(this);  //先得到构造器
                buildermarital.setTitle("请选择婚姻状况"); //设置标题
                buildermarital.setPositiveButton("OK", null);
                buildermarital.setItems(marital, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Toast.makeText(PersonalReviseActivity.this, marital[which], Toast.LENGTH_SHORT).show();
                        tv_marital_status.setText(marital[which]);
                    }
                });
                buildermarital.create().show();
                break;
            case R.id.layout_distance_love://接受异地恋
                String[] distancelove = {"可以接受", "不能接受", "看情况"};
                AlertDialog.Builder builderdistance = new AlertDialog.Builder(this);  //先得到构造器
                builderdistance.setTitle("接受异地恋"); //设置标题
                builderdistance.setPositiveButton("OK", null);
                builderdistance.setItems(distancelove, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Toast.makeText(PersonalReviseActivity.this, distancelove[which], Toast.LENGTH_SHORT).show();
                        tv_distance_love.setText(distancelove[which]);
                    }
                });
                builderdistance.create().show();
                break;
            case R.id.layout_like_sex://喜欢的异性
                String[] like_sexnan = {"性感妩媚", "落落大方", "眉清目秀", "古灵精怪", "成熟魅力", "小鸟依人", "善解人意", "娇小可爱", "温柔体贴", "雍容华贵"};
                String[] like_sexgril = {"成熟稳重", "有责任心", "高大帅气", "眉清目秀", "落落大方", "善解人意", "温柔体贴", "器大活好", "冷静幽默"};
                View outerViewlikesex = LayoutInflater.from(this).inflate(R.layout.wheel_likesex, null);
                GridView wheel_listlikenan = (GridView) outerViewlikesex.findViewById(R.id.grid_nan);
                GridView wheel_listlikegril = (GridView) outerViewlikesex.findViewById(R.id.grid_gril);
                TextView text_gril= (TextView) outerViewlikesex.findViewById(R.id.text_gril);
                TextView text_nan= (TextView) outerViewlikesex.findViewById(R.id.text_nan);
                if(value=="1"){
                    ArrayList<HashMap<String, String>> mylistlikenan = new ArrayList<HashMap<String, String>>();
                    for (int i = 0; i < like_sexnan.length; i++) {
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put("wheel_viewnumber", like_sexnan[i]);
                        mylistlikenan.add(map);
                    }
                    SimpleAdapter adapterlikenan = new SimpleAdapter(this, mylistlikenan, R.layout.wheel_gridviewitem,
                            new String[]{"wheel_viewnumber"}, new int[]{R.id.gridview});
                    wheel_listlikenan.setAdapter(adapterlikenan);
                    final StringBuffer stringbuff=new StringBuffer();
                    wheel_listlikenan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            stringbuff.append(like_sexnan[i]+" ");
                            if(stringbuff.length()<28){
                                tv_like_sex.setText(stringbuff.toString());
                                view.setBackgroundResource(R.color.red_eb2616);
                            }else{
                                return;
                            }

                        }
                    });
                    wheel_listlikegril.setVisibility(View.GONE);
                    text_gril.setVisibility(View.GONE);
                }else{
                    ArrayList<HashMap<String, String>> mylistlikegril = new ArrayList<HashMap<String, String>>();
                    for (int i = 0; i < like_sexgril.length; i++) {
                        HashMap<String, String> p = new HashMap<String, String>();
                        p.put("m", like_sexgril[i]);
                        mylistlikegril.add(p);
                    }
                    SimpleAdapter adapterlikegril = new SimpleAdapter(this, mylistlikegril, R.layout.wheel_gridviewitem,
                            new String[]{"m"}, new int[]{R.id.gridview});
                    wheel_listlikegril.setAdapter(adapterlikegril);
                    new AlertDialog.Builder(this)
                            .setTitle("请选择")
                            .setView(outerViewlikesex)
                            .setPositiveButton("OK", null)
                            .show();

                    final StringBuffer stringbu=new StringBuffer();
                    wheel_listlikegril.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            stringbu.append(like_sexgril[i]+" ");
                            if(stringbu.length()<28){
                                tv_like_sex.setText(stringbu.toString());
                                view.setBackgroundResource(R.color.red_eb2616);
                            }else{
                                return;
                            }
                        }
                    });
                    wheel_listlikenan.setVisibility(View.GONE);
                    text_nan.setVisibility(View.GONE);
                }
                break;
            case R.id.layout_premarital_sex://婚前性行为
//                mMenuViewpremarital = new PopWindowPremarital(PersonalReviseActivity.this, itemsOnClickPrematital);
//                mMenuViewpremarital.showAtLocation(PersonalReviseActivity.this.findViewById(R.id.tv_premarital_sex), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
                String[] premarital = {"可以接受", "不能接受", "必须得有"};
                AlertDialog.Builder builderpremarital = new AlertDialog.Builder(this);  //先得到构造器
                builderpremarital.setTitle("婚前性行为"); //设置标题
                builderpremarital.setPositiveButton("OK", null);
                builderpremarital.setItems(premarital, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Toast.makeText(PersonalReviseActivity.this, premarital[which], Toast.LENGTH_SHORT).show();
                        tv_premarital_sex.setText(premarital[which]);
                    }
                });
                builderpremarital.create().show();
                break;
            case R.id.baocun:
                String mobile = tv_mobile.getText().toString();
                jsonmobile = new JSONObject();
                PersonalReviseMessageBean.DataBean.RulesBean rulesBean = rules.get(0);
                if (rulesBean == null) {
                }
                try {
                    jsonmobile.put(rules.get(0).getField(), mobile);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String qq = tv_qq.getText().toString();
                jsonqq = new JSONObject();
                PersonalReviseMessageBean.DataBean.RulesBean rulesQQ = rules.get(1);
                if (rulesQQ == null) {
                }
                try {
                    jsonqq.put(rules.get(1).getField(), qq);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String weixin = tv_weixin.getText().toString();
                jsonweixin = new JSONObject();
                PersonalReviseMessageBean.DataBean.RulesBean rulesweixin = rules.get(2);
                if (rulesweixin == null) {
                }
                try {
                    jsonweixin.put(rules.get(2).getField(), weixin);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                PersonalReviseMessageBean.DataBean.RulesBean rulesavater = rules.get(3);
                if (rulesavater == null) {
                }
                jsonavater = new JSONObject();
                try {
                    jsonavater.put("face", message);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                PersonalReviseMessageBean.DataBean.RulesBean ruleslikeimage = rules.get(4);
                if (ruleslikeimage == null) {
                }
                jsonlike = new JSONObject();
                try {
                    jsonlike.put("face",like_backgroud);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String str = tv_nike_name.getText().toString();
                PersonalReviseMessageBean.DataBean.RulesBean rulesnike = rules.get(5);
                if (rulesnike == null) {
                }
                jsonobjname = new JSONObject();
                try {
                    jsonobjname.put(rules.get(5).getField(), str);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String signature = tv_signature.getText().toString();
                PersonalReviseMessageBean.DataBean.RulesBean rulessignature = rules.get(6);
                if (rulessignature == null) {
                }
                jsonobjsignature = new JSONObject();
                try {
                    jsonobjsignature.put(rules.get(6).getField(), signature);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String sex = tv_sex.getText().toString();
                PersonalReviseMessageBean.DataBean.RulesBean rulessex = rules.get(7);
                if (rulessex == null) {
                }
                jsonobjsex = new JSONObject();
                try {
                    jsonobjsex.put(rules.get(7).getField(), sex);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                PersonalReviseMessageBean.DataBean.RulesBean rulesbir = rules.get(8);
                if (rulesbir == null) {
                }
                jsonobjbirthday = new JSONObject();
                try {
                    jsonobjbirthday.put(rules.get(8).getField(), result);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                PersonalReviseMessageBean.DataBean.RulesBean rulesstar = rules.get(9);
                if (rulesstar == null) {
                }
                jsonobjstarsign = new JSONObject();
                try {
                    jsonobjstarsign.put(rules.get(9).getField(), love);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                hight = tv_hight.getText().toString();
                PersonalReviseMessageBean.DataBean.RulesBean ruleshight = rules.get(10);
                if (ruleshight == null) {
                }
                jsonobjhight = new JSONObject();
                try {
                    jsonobjhight.put(rules.get(10).getField(), hight);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String weight = tv_weight.getText().toString();
                PersonalReviseMessageBean.DataBean.RulesBean rulesweight = rules.get(11);
                if (rulesweight == null) {
                }
                jsonobjweight = new JSONObject();
                try {
                    jsonobjweight.put(rules.get(11).getField(), weight);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String money_p = tv_money_pay.getText().toString();
                PersonalReviseMessageBean.DataBean.RulesBean ruleswmoney = rules.get(12);
                if (ruleswmoney == null) {
                }
                jsonobjmoney = new JSONObject();
                try {
                    jsonobjmoney.put(rules.get(12).getField(), money_p);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String educational_background = tv_educational_background.getText().toString();
                PersonalReviseMessageBean.DataBean.RulesBean ruleseducational = rules.get(13);
                if (ruleseducational == null) {
                }
                jsonobjducational = new JSONObject();
                try {
                    jsonobjducational.put(rules.get(13).getField(), educational_background);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String birthplace = tv_birthplace.getText().toString();
                PersonalReviseMessageBean.DataBean.RulesBean rulesbirthplace = rules.get(14);
                if (rulesbirthplace == null) {
                }
                jsonobjbirthplace = new JSONObject();
                try {
                    jsonobjbirthplace.put(rules.get(14).getField(), birthplace);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String address = tv_address.getText().toString();
                PersonalReviseMessageBean.DataBean.RulesBean rulesaddress = rules.get(15);
                if (rulesaddress == null) {
                }
                jsonobjaddress = new JSONObject();
                try {
                    jsonobjaddress.put(rules.get(15).getField(), address);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String job = tv_job.getText().toString();
                PersonalReviseMessageBean.DataBean.RulesBean rulesjob = rules.get(16);
                if (rulesjob == null) {
                }
                jsonobjjob = new JSONObject();
                try {
                    jsonobjjob.put(rules.get(16).getField(), job);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String hobby = tv_hobby.getText().toString();
                PersonalReviseMessageBean.DataBean.RulesBean ruleshobby = rules.get(17);
                if (ruleshobby == null) {
                }
                jsonobjhobby = new JSONObject();
                try {
                    jsonobjhobby.put(rules.get(17).getField(), hobby);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String self_image = tv_self_image.getText().toString();
                PersonalReviseMessageBean.DataBean.RulesBean rulesself = rules.get(18);
                if (rulesself == null) {
                }
                jsonobjself = new JSONObject();
                try {
                    jsonobjself.put(rules.get(18).getField(), self_image);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                ishouse = tv_ishouse.getText().toString();
                PersonalReviseMessageBean.DataBean.RulesBean rulesishouse = rules.get(19);
                if (rulesishouse == null) {
                }
                jsonobjishouse = new JSONObject();
                try {
                    jsonobjishouse.put(rules.get(19).getField(), ishouse);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                marital_status = tv_marital_status.getText().toString();
                PersonalReviseMessageBean.DataBean.RulesBean rulesmarital = rules.get(20);
                if (rulesmarital == null) {
                }
                jsonobjmarital = new JSONObject();
                try {
                    jsonobjmarital.put(rules.get(20).getField(), marital_status);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                distance = tv_distance_love.getText().toString();
                PersonalReviseMessageBean.DataBean.RulesBean rulesdistance = rules.get(21);
                if (rulesdistance == null) {
                }
                jsonobjdistance = new JSONObject();
                try {
                    jsonobjdistance.put(rules.get(21).getField(), distance);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String like_sex = tv_like_sex.getText().toString();
                PersonalReviseMessageBean.DataBean.RulesBean ruleslike = rules.get(22);
                if (ruleslike == null) {
                }
                jsonobjlike = new JSONObject();
                try {
                    jsonobjlike.put(rules.get(22).getField(), like_sex);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                premarital_sex = tv_premarital_sex.getText().toString();
                PersonalReviseMessageBean.DataBean.RulesBean rulespremarital = rules.get(23);
                if (rulespremarital == null) {
                }
                jsonobjpremarital = new JSONObject();
                try {
                    jsonobjpremarital.put(rules.get(23).getField(), premarital_sex);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String ake_delivery = tv_take_delivery.getText().toString();
                PersonalReviseMessageBean.DataBean.RulesBean rulestake_delivery = rules.get(24);
                if (rulestake_delivery == null) {
                }
                jsonobjtake_delivery = new JSONObject();
                try {
                    jsonobjtake_delivery.put(rules.get(24).getField(), ake_delivery);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String zip_code = tv_zip_code.getText().toString();
                PersonalReviseMessageBean.DataBean.RulesBean ruleszip_code = rules.get(25);
                if (ruleszip_code == null) {
                }
                jsonobjZip = new JSONObject();
                try {
                    jsonobjZip.put(rules.get(25).getField(), zip_code);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JSONArray jsonarry = new JSONArray();
                jsonarry.put(jsonmobile);
                jsonarry.put(jsonqq);
                jsonarry.put(jsonweixin);
                jsonarry.put(jsonavater);
                jsonarry.put(jsonlike);
                jsonarry.put(jsonobjname);
                jsonarry.put(jsonobjsignature);
                jsonarry.put(jsonobjsex);
                jsonarry.put(jsonobjbirthday);
                jsonarry.put(jsonobjstarsign);
                jsonarry.put(jsonobjhight);
                jsonarry.put(jsonobjweight);
                jsonarry.put(jsonobjmoney);
                jsonarry.put(jsonobjducational);
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
                jsonarry.put(jsonobjpremarital);
                jsonarry.put(jsonobjtake_delivery);
                jsonarry.put(jsonobjZip);

                String ss = jsonarry.toString();
                presenter.sendUserJson(ss);
                break;
        }
    }

    @Override
    public void success(PersonalReviseMessageBean personalReviseMessageBean) {
        rules = personalReviseMessageBean.getData().getRules();
        isshow = rules.get(0).getIsshow().toString();
        if (isshow.equals("1")) {
            tv_mobile.setText(rules.get(0).getValue() + "");//手机  QQ 微信
        } else {
            layout_mobile.setVisibility(View.GONE);
        }

        isshowqq = rules.get(1).getIsshow();
        if (isshowqq.equals("1")) {
            tv_qq.setText(rules.get(1).getValue() + "");
        } else {
            layout_qq.setVisibility(View.GONE);
        }
        isshowweixin = rules.get(2).getIsshow();
        if (isshowweixin.equals("1")) {
            tv_weixin.setText(rules.get(2).getValue() + "");
        } else {
            layout_weixin.setVisibility(View.GONE);
        }
        isshowimage = rules.get(3).getIsshow();
        if (isshowimage.equals("1")) {
            ImageServerApi.showURLBigImage(riv_user_image, rules.get(3).getValue());//用户头像
        } else {
            layout_avater.setVisibility(View.GONE);
        }
        isshowlikeimage = rules.get(4).getIsshow();
        if (isshowlikeimage.equals("1")) {
            ImageServerApi.showURLBigImage(riv_like_image, rules.get(4).getValue());//背景头像
        } else {
            layout_like.setVisibility(View.GONE);
        }

        isshowname = rules.get(5).getIsshow();
        if (isshowname.equals("1")) {
            tv_nike_name.setText(rules.get(5).getValue());//用户昵称
        } else {
            layout_nike_name.setVisibility(View.GONE);
        }
        isshowsignature = rules.get(6).getIsshow();
        if (isshowsignature.equals("1")) {
            tv_signature.setText(rules.get(6).getValue());//个性签名
        } else {
            layout_signature.setVisibility(View.GONE);
        }

        isshowsex = rules.get(7).getIsshow();
        value = rules.get(7).getValue();//性别
        if (isshowsex.equals("1")) {
            if (value == "1") {
                tv_sex.setText("男");
            } else {
                tv_sex.setText("女");
            }
        } else {
            layout_sex.setVisibility(View.GONE);
        }
        isshowbirthday = rules.get(8).getIsshow();
        if (isshowbirthday.equals("1")) {
            tv_birthday.setText(rules.get(8).getValue());//出生年月
        } else {
            layout_birthday.setVisibility(View.GONE);
        }

        isshowstar = rules.get(9).getIsshow();
        if (isshowstar.equals("1")) {
            tv_star_sign.setText(rules.get(9).getValue());//星座
        } else {
            layout_star_sign.setVisibility(View.GONE);
        }
        isshowhight = rules.get(10).getIsshow();
        if (isshowhight.equals("1")) {
            tv_hight.setText(rules.get(10).getValue());//身高
        } else {
            layout_hight.setVisibility(View.GONE);
        }
        isshowwight = rules.get(11).getIsshow();//体重
        if (isshowwight.equals("1")) {
            tv_weight.setText(rules.get(11).getValue());
        } else {
            layout_weight.setVisibility(View.GONE);
        }
        isshowmoneypay = rules.get(12).getIsshow();//月薪
        if (isshowmoneypay.equals("1")) {
            tv_money_pay.setText(rules.get(12).getValue());
        } else {
            layout_money_pay.setVisibility(View.GONE);
        }
        isshoweducationalbackground = rules.get(13).getIsshow();//学历
        if (isshoweducationalbackground.equals("1")) {
            tv_educational_background.setText(rules.get(13).getValue());
        } else {
            layout_educational_background.setVisibility(View.GONE);
        }

        isshowbirthpace = rules.get(14).getIsshow();
        if(isshowbirthpace.equals("1")){
        tv_birthplace.setText(rules.get(14).getValue());//籍贯
        }else{
            layout_birthplace.setVisibility(View.GONE);
        }
        isshowaddress = rules.get(15).getIsshow();
        if(isshowaddress.equals("1")){
        tv_address.setText(rules.get(15).getValue());//现居
        }else{
            layout_address.setVisibility(View.GONE);
        }
        isshowjob = rules.get(16).getIsshow();
        if (isshowjob.equals("1")) {
            tv_job.setText(rules.get(16).getValue());//职业
        } else {
            layout_job.setVisibility(View.GONE);
        }
        isshowhobby = rules.get(17).getIsshow();
        if (isshowhobby.equals("1")) {
            tv_hobby.setText(rules.get(17).getValue());//兴趣爱好
        } else {
            layout_hobby.setVisibility(View.GONE);
        }
        isshowself = rules.get(18).getIsshow();
        if (isshowself.equals("1")) {
            tv_self_image.setText(rules.get(18).getValue());//自我印象
        } else {
            layout_self_image.setVisibility(View.GONE);
        }

//        isshowhouse = rules.get(19).getIsshow();
//        if(isshowhouse.equals("1")){
        tv_ishouse.setText(rules.get(19).getValue());//是否有房
//        }else{
//            layout_ishouse.setVisibility(View.GONE);
//        }
        isshowmarital = rules.get(20).getIsshow();
        if (isshowmarital.equals("1")) {
            tv_marital_status.setText(rules.get(20).getValue());//婚姻状况
        } else {
            layout_marital_status.setVisibility(View.GONE);
        }
        isshowdistance = rules.get(21).getIsshow();
        if (isshowdistance.equals("1")) {
            tv_distance_love.setText(rules.get(21).getValue());//接受异地恋
        } else {
            layout_distance_love.setVisibility(View.GONE);
        }
        isshowlike = rules.get(22).getIsshow();
        if (isshowlike.equals("1")) {
            tv_like_sex.setText(rules.get(22).getValue());//喜欢的异性
        } else {
            layout_like_sex.setVisibility(View.GONE);
        }
        isshowpremar = rules.get(23).getIsshow();
        if (isshowpremar.equals("1")) {
            tv_premarital_sex.setText(rules.get(23).getValue());//婚前性行为
        } else {
            layout_premarital_sex.setVisibility(View.GONE);
        }
        isshowttakedelivery = rules.get(24).getIsshow();//收货地址
        if (isshowttakedelivery.equals("1")) {
            tv_take_delivery.setText(rules.get(24).getValue());
        } else {
            layout_take_delivery.setVisibility(View.GONE);
        }
        isshowzipcode = rules.get(25).getIsshow();//邮编
        if (isshowzipcode.equals("1")) {
            tv_zip_code.setText(rules.get(25).getValue());
        } else {
            layout_zip_code.setVisibility(View.GONE);
        }
        isshowyuliu = rules.get(26).getIsshow();//预留项1
        if (isshowyuliu.equals("1")) {
            yu_liu.setText(rules.get(26).getValue());
        } else {
            layout_yuliu.setVisibility(View.GONE);
        }
        isshowyuliu1 = rules.get(27).getIsshow();//预留项1
        if (isshowyuliu1.equals("1")) {
            yu_liu1.setText(rules.get(27).getValue());
        } else {
            layout_yuliu1.setVisibility(View.GONE);
        }
        isshowyuliu2 = rules.get(28).getIsshow();//预留项1
        if (isshowyuliu2.equals("1")) {
            yu_liu2.setText(rules.get(28).getValue());
        } else {
            layout_yuliu2.setVisibility(View.GONE);
        }

    }

    /**
     * 通过日期来确定星座
     *
     * @param mouth
     * @param day
     * @return
     */
    public static String getStarSeat(int mouth, int day) {
        String starSeat = null;

        if ((mouth == 3 && day >= 21) || (mouth == 4 && day <= 19)) {
            starSeat = "白羊座";
        } else if ((mouth == 4 && day >= 20) || (mouth == 5 && day <= 20)) {
            starSeat = "金牛座";
        } else if ((mouth == 5 && day >= 21) || (mouth == 6 && day <= 21)) {
            starSeat = "双子座";
        } else if ((mouth == 6 && day >= 22) || (mouth == 7 && day <= 22)) {
            starSeat = "巨蟹座";
        } else if ((mouth == 7 && day >= 23) || (mouth == 8 && day <= 22)) {
            starSeat = "狮子座";
        } else if ((mouth == 8 && day >= 23) || (mouth == 9 && day <= 22)) {
            starSeat = "处女座";
        } else if ((mouth == 9 && day >= 23) || (mouth == 10 && day <= 23)) {
            starSeat = "天秤座";
        } else if ((mouth == 10 && day >= 24) || (mouth == 11 && day <= 22)) {
            starSeat = "天蝎座";
        } else if ((mouth == 11 && day >= 23) || (mouth == 12 && day <= 21)) {
            starSeat = "射手座";
        } else if ((mouth == 12 && day >= 22) || (mouth == 1 && day <= 19)) {
            starSeat = "摩羯座";
        } else if ((mouth == 1 && day >= 20) || (mouth == 2 && day <= 18)) {
            starSeat = "水瓶座";
        } else {
            starSeat = "双鱼座";
        }
        return starSeat;
    }

    public String[] getCity(int start, int end) {
        String[] City = new String[end - start + 1];
        for (int i = start; i <= end; i++) {
            City[i - start] = city[i];
        }
        return City;
    }
}
