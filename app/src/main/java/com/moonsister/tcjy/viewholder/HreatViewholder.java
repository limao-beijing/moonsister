package com.moonsister.tcjy.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.bean.UserInfoDetailBean;
import com.moonsister.tcjy.utils.UIUtils;

import butterknife.Bind;

/**
 * Created by x on 2016/8/22.
 */
public class HreatViewholder extends BaseHolder<UserInfoDetailBean> {
    @Bind(R.id.tv_user_all_income)//总收入
            TextView tvUserAllIncome;
    @Bind(R.id.tv_user_day_income)//今日收入
            TextView tvUserDayIncome;
    @Bind(R.id.tv_user_name)//用户名
            TextView tvUserName;
    @Bind(R.id.tv_work)//用户职业
            TextView tv_work;
    @Bind(R.id.tv_time)//用户时间
            TextView tv_time;
    @Bind(R.id.tv_age)//用户年龄
            TextView tv_age;
    @Bind(R.id.tv_address)//用户地址
            TextView tv_address;
    @Bind(R.id.image_gril)//用户男女图片
            ImageView image_gril;
    @Bind(R.id.tv_look_people)//查看个人主页
            TextView tv_look_people;


    @Override
    protected View initView() {
        View hreadView = UIUtils.inflateLayout(R.layout.my_zhuye);
        return hreadView;
    }

    @Override
    public void refreshView(UserInfoDetailBean data) {
    }
}
