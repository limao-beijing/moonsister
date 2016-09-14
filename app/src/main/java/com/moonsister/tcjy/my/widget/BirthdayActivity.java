package com.moonsister.tcjy.my.widget;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.main.widget.PersonInfoChangeActivity;
import com.moonsister.tcjy.utils.CaculationUtils;
import com.moonsister.tcjy.utils.StringUtis;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tcjy.widget.wheelview.NumericWheelAdapter;
import com.moonsister.tcjy.widget.wheelview.OnWheelScrollListener;
import com.moonsister.tcjy.widget.wheelview.WheelView;

import java.util.Calendar;



/*
 * author 院彩华
 * 生日的选项页面
 * */

public class BirthdayActivity extends BaseActivity implements OnClickListener {
    private WheelView year;// 年
    private WheelView month;// 月
    private WheelView day;// 日
    private int mYear = 1996;
    private int mMonth = 0;
    private int mDay = 1;
    private static String birthday;// 生日
    private TextView tv_date_birthday;// 显示生日
    private TextView tv_title_right;
    private TextView tv_birthday_constellation;// 显示星座
    String brigth;

    @Override
    protected View setRootContentView() {
        return UIUtils.inflateLayout(R.layout.activity_birthday);
    }

    @Override
    protected String initTitleName() {
        return UIUtils.getStringRes(R.string.birthday);
    }

    @Override
    protected void initView() {


        tv_title_right = (TextView) findViewById(R.id.tv_title_right);
        tv_title_right.setVisibility(View.VISIBLE);
        tv_title_right.setText("保存");
        tv_birthday_constellation = (TextView) findViewById(R.id.tv_birthday_constellation);
        tv_date_birthday = (TextView) findViewById(R.id.tv_date_birthday);
        String editdata = getIntent().getStringExtra("editdata");

        if (StringUtis.isEmpty(editdata)) {
            birthday = "";
        } else {
            if (editdata.contains("-")) {
                String[] split = editdata.split("-");
                tv_date_birthday.setText(CaculationUtils
                        .calculateDatePoor(birthday));
                tv_birthday_constellation.setText(getConstellation(
                        Integer.parseInt(split[1]),
                        Integer.parseInt(split[2]))
                        + "");
            } else
                birthday = "";
        }

//        if (edit_birthday.getStringExtra("editdata") == null
//                || "".equals(edit_birthday.getStringExtra("editdata"))) {
//
//        } else {
//            birthday = edit_birthday.getExtras().get("editdata") + "";
//            String[] dataArry = birthday.split("-");
//            tv_date_birthday.setText(CaculationUtils
//                    .calculateDatePoor(birthday));
//
//    }

        tv_title_right.setOnClickListener(this);

        getDataPick();

    }


    private final static int[] dayArr = new int[]{20, 19, 21, 20, 21, 22, 23,
            23, 23, 24, 23, 22};
    private final static String[] constellationArr = new String[]{"摩羯座",
            "水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座",
            "天蝎座", "射手座", "摩羯座"};

    public static String getConstellation(int month, int day) {
        return day < dayArr[month - 1] ? constellationArr[month - 1]
                : constellationArr[month];
    }

    /**
     * @param year
     * @param month
     * @return
     */
    private int getDay(int year, int month) {
        int day = 30;
        boolean flag = false;
        switch (year % 4) {
            case 0:
                flag = true;
                break;
            default:
                flag = false;
                break;
        }
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                day = 31;
                break;
            case 2:
                day = flag ? 29 : 28;
                break;
            default:
                day = 30;
                break;
        }
        return day;
    }

    /**
     */
    private void initDay(int arg1, int arg2) {
        day.setAdapter(new NumericWheelAdapter(1, getDay(arg1, arg2), "%02d"));
    }

    OnWheelScrollListener scrollListener = new OnWheelScrollListener() {

        @Override
        public void onScrollingStarted(WheelView wheel) {

        }

        @Override
        public void onScrollingFinished(WheelView wheel) {
            int n_year = year.getCurrentItem() + 1950;//
            int n_month = month.getCurrentItem() + 1;//
            initDay(n_year, n_month);
            birthday = new StringBuilder()
                    .append((year.getCurrentItem() + 1950))
                    .append("-")
                    .append((month.getCurrentItem() + 1) < 10 ? "0"
                            + (month.getCurrentItem() + 1) : (month
                            .getCurrentItem() + 1))
                    .append("-")
                    .append(((day.getCurrentItem() + 1) < 10) ? "0"
                            + (day.getCurrentItem() + 1) : (day
                            .getCurrentItem() + 1)).toString();
            String year = CaculationUtils.calculateDatePoor(birthday);
            tv_birthday_constellation.setText(""
                    + getConstellation(n_month, day.getCurrentItem()));
            tv_date_birthday.setText(year + "");


        }
    };

    private void getDataPick() {
        Calendar c = Calendar.getInstance();
        int norYear = c.get(Calendar.YEAR);

        int curYear = mYear;
        int curMonth = mMonth + 1;
        // int curDate = mDay;

        year = (WheelView) findViewById(R.id.year);
        year.setAdapter(new NumericWheelAdapter(1950, norYear));
        year.setLabel("年");
        year.setCyclic(true);
        year.addScrollingListener(scrollListener);

        month = (WheelView) findViewById(R.id.month);
        month.setAdapter(new NumericWheelAdapter(1, 12, "%02d"));
        month.setLabel("月");
        month.setCyclic(true);
        month.addScrollingListener(scrollListener);

        day = (WheelView) findViewById(R.id.day);
        initDay(curYear, curMonth);
        day.setLabel("日");
        day.setCyclic(true);
        day.addScrollingListener(scrollListener);
        if (birthday != null && !"".equals(birthday)) {
            String[] date = birthday.trim().split("-");
            year.setCurrentItem(Integer.parseInt(date[0]) - 1950);
            month.setCurrentItem(Integer.parseInt(date[1]) - 1);
            day.setCurrentItem(Integer.parseInt(date[2]) - 1);

        } else {
            year.setCurrentItem(40);
            month.setCurrentItem(0);
            day.setCurrentItem(0);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_title_right:
                if (StringUtis.isEmpty(birthday)) {
                    showToast(UIUtils.getStringRes(R.string.birthday) + UIUtils.getStringRes(R.string.not_empty));
                    return;
                }
                Events<PersonInfoChangeActivity.PersonInfoChangeData> events = new Events<>();
                PersonInfoChangeActivity.PersonInfoChangeData data = new PersonInfoChangeActivity.PersonInfoChangeData();
                data.setType(PersonInfoChangeActivity.ChangeType.BIRTHDAY);
                data.setArg1(birthday);
                events.what = Events.EventEnum.PERSON_INFO_CHANGE;
                events.message = data;
                RxBus.getInstance().send(events);

//                String s = tv_date_birthday.getText().toString();
                String s1 = tv_birthday_constellation.getText().toString();
                //数据是使用Intent返回
                Intent intent = new Intent();
                //把返回数据存入Intent
                intent.putExtra("result", brigth);
                intent.putExtra("love", s1);
                //设置返回数据
                BirthdayActivity.this.setResult(RESULT_OK, intent);
                //关闭Activity
                BirthdayActivity.this.finish();


//                SharedPreferences mySharedPreferences= getSharedPreferences("my",
//                        Activity.MODE_PRIVATE);
//                //实例化SharedPreferences.Editor对象（第二步）
//                SharedPreferences.Editor editor = mySharedPreferences.edit();
//                //用putString的方法保存数据
//                editor.putString("bir", s);
//                editor.putString("con", s1);
////                提交当前数据
//                editor.commit();

                break;
        }

    }
}