package com.moonsister.tcjy.my.widget;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.moonsister.tcjy.ImageServerApi;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.adapter.PersonalAdapter;
import com.moonsister.tcjy.adapter.PersonalReviseAdapter;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.bean.PersonalMessageBean;
import com.moonsister.tcjy.my.persenter.PersonalActivityPersenter;
import com.moonsister.tcjy.my.persenter.PersonalActivityPersenterImpl;
import com.moonsister.tcjy.my.view.PersonalActivityView;
import com.moonsister.tcjy.utils.UIUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by x on 2016/9/5.
 */
public class PersonalReviseActivity extends BaseActivity implements PersonalActivityView {
    @Bind(R.id.riv_user_image)//头像
    ImageView riv_user_image;
    @Bind(R.id.riv_like_image)//背景墙
     ImageView riv_like_image;
    @Bind(R.id.tv_nike_name)//昵称
    TextView tv_nike_name;
    @Bind(R.id.tv_signature)//个性签名
    TextView tv_signature;
    @Bind(R.id.tv_sex)//性别
            TextView tv_sex;
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
    private PersonalActivityPersenter presenter;
    List<PersonalMessageBean.DataBean.RulesBean> data;
    PersonalMessageBean.DataBean.BaseinfoBean data1;
    @Override
    protected View setRootContentView() {
        presenter = new PersonalActivityPersenterImpl();
        presenter.attachView(this);
        return UIUtils.inflateLayout(R.layout.personalreviseactivity);
    }

    @Override
    protected void initView() {

    }

    @Override
    public void success(PersonalMessageBean getPersonalBean) {
        data = getPersonalBean.getData().getRules();
        data1=getPersonalBean.getData().getBaseinfo();

//        for (int i=0;i<data.size();i++){
//            tv_nike_name.setText(data.get(i).getValue().);
//        }
//        tv_nike_name.setText(data.g);
    }

    @Override
    public void person() {
        ImageServerApi.showURLBigImage(riv_user_image, data1.getFace());//用户头像
        ImageServerApi.showURLBigImage(riv_like_image, data1.getLike_image());//用户头像
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
}
