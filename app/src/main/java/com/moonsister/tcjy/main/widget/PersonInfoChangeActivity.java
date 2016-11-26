package com.moonsister.tcjy.main.widget;

import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hickey.tool.base.BaseActivity;
import com.hickey.tool.lang.StringUtis;
import com.hickey.tool.widget.UIUtils;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.my.widget.info.SelectPlandWindowActivity;
import com.moonsister.tcjy.my.widget.info.SelectSexActivity;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.trello.rxlifecycle.ActivityEvent;

import java.io.Serializable;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by jb on 2016/6/30.
 */
public class PersonInfoChangeActivity extends BaseActivity {
    @Bind(R.id.tv_title_right)
    TextView tvTitleRight;
    @Bind(R.id.et_info)
    EditText etInfo;
    @Bind(R.id.tv_monad)
    TextView tvMonad;
    @Bind(R.id.tv_Left)
    TextView tv_Left;
    private ChangeType type;

    /**
     * 修改的类型
     */
    public enum ChangeType {
        NIKE(1),HEIGHT(3), SEX(2), ADDRESS(4), SIGNATURE(5), INTRODUCTION(6), EDUCATION(7), JOB(8), WEIGHT(9), BIRTHDAY(10);
        int chengeType;

        private ChangeType(int type) {
            chengeType = type;
        }

        public int getValue() {
            return chengeType;
        }
    }

    @Override
    protected View setRootContentView() {
        int i = getIntent().getIntExtra("type", 0);
        if (i == 0)
            finish();

        switch (i) {
            case 1:
                type = ChangeType.NIKE;

                break;
            case 2:
                type = ChangeType.SEX;
                break;
            case 3:
                type = ChangeType.HEIGHT;
                break;
            case 4:
                type = ChangeType.ADDRESS;
                break;
            case 5:
                type = ChangeType.SIGNATURE;
                break;
            case 6:
                type = ChangeType.INTRODUCTION;
                break;
            case 7:
                type = ChangeType.EDUCATION;
                break;
            case 8:
                type = ChangeType.JOB;
                break;
            case 9:
                type = ChangeType.WEIGHT;
                break;

        }
        return UIUtils.inflateLayout(R.layout.activity_person_info_change);
    }

    @Override
    protected String initTitleName() {
        setRx();
        String title = null;
        switch (type.chengeType) {
            case 1:
                title = UIUtils.getStringRes(R.string.nike);
                tvMonad.setVisibility(View.GONE);

                break;
            case 2:
                etInfo.setVisibility(View.INVISIBLE);
                title = UIUtils.getStringRes(R.string.sex);
                tvMonad.setVisibility(View.VISIBLE);
                tvMonad.setTextColor(UIUtils.getResources().getColor(R.color.home_navigation_text_red));
                tv_Left.setVisibility(View.VISIBLE);
                tv_Left.setText(UIUtils.getStringRes(R.string.sex));
                tv_Left.setTextColor(UIUtils.getResources().getColor(R.color.text_black_color));
                ActivityUtils.startActivity(SelectSexActivity.class);
                setRx();
                break;
            case 3:
                title = UIUtils.getStringRes(R.string.height);
                tvMonad.setText(UIUtils.getStringRes(R.string.cm));
                tvMonad.setTextColor(UIUtils.getResources().getColor(R.color.text_black_color));
                etInfo.setHint(title);
                etInfo.setInputType(InputType.TYPE_CLASS_NUMBER);
                break;
            case 4:
                title = UIUtils.getStringRes(R.string.address);
                etInfo.setVisibility(View.INVISIBLE);
                tv_Left.setVisibility(View.VISIBLE);
                tvMonad.setVisibility(View.VISIBLE);
                tvMonad.setTextColor(UIUtils.getResources().getColor(R.color.home_navigation_text_red));
                tv_Left.setText(UIUtils.getStringRes(R.string.Provice) + UIUtils.getResources().getString(R.string.City));
                tv_Left.setTextColor(UIUtils.getResources().getColor(R.color.text_black_color));
                ActivityUtils.startActivity(SelectPlandWindowActivity.class);
                setRx();
                break;
            case 5:
                title = UIUtils.getStringRes(R.string.signature);
                setRx();
                break;
            case 6:
                title = UIUtils.getStringRes(R.string.introduction);
                setRx();
                break;
            case 7:
                title = UIUtils.getStringRes(R.string.education);
                setRx();
                break;
            case 8:
                title = UIUtils.getStringRes(R.string.job);
                setRx();
                break;
            case 9:
                title = UIUtils.getStringRes(R.string.weight);
                etInfo.setInputType(InputType.TYPE_CLASS_NUMBER);
                setRx();
                break;

        }
        if (!StringUtis.isEmpty(title))
            return title;
        return super.initTitleName();
    }

    private void setRx() {
        RxBus.with(this)
                .setEndEvent(ActivityEvent.DESTROY)
                .setEvent(Events.EventEnum.SELECT_PLAND_DATA)
                .onNext(events -> {
                    tvMonad.setText((String) events.message);
                })
                .create();
    }

    @Override
    protected void initView() {
        tvTitleRight.setVisibility(View.VISIBLE);
        tvTitleRight.setText(UIUtils.getStringRes(R.string.finish));
    }


    @OnClick({R.id.tv_title_right, R.id.layout_info})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.tv_title_right:
                submit();
                break;
            case R.id.layout_info:
                layout();
                break;
        }
    }

    private void layout() {
        switch (type.getValue()) {
            case 2:
                ActivityUtils.startActivity(SelectSexActivity.class);
                break;
            case 4:
                ActivityUtils.startActivity(SelectPlandWindowActivity.class);
                break;
        }
    }

    /***
     * 完成
     */
    private void submit() {
        switch (type.getValue()) {
            case 1:
                String heigth = etInfo.getText().toString().trim();
                if (StringUtis.isEmpty(heigth) || heigth.length() == 0) {
                    showToast(getResources().getString(R.string.not_empty));
                    return;
                }
                String cm = tvMonad.getText().toString().trim();
                sendRx(heigth, cm);
                finish();
                break;
            case 2:
                String sex = tvMonad.getText().toString().toString();
                if (StringUtis.isEmpty(sex) || sex.length() == 0) {
                    showToast(getResources().getString(R.string.not_empty));
                    return;
                }
                sendRx(sex, null);
                finish();
                break;
            case 3:
                String str = etInfo.getText().toString().trim();
                if (StringUtis.isEmpty(str) || str.length() == 0) {
                    showToast(getResources().getString(R.string.not_empty));
                    return;
                }
                sendRx(str, null);
                finish();
                break;

            case 4:
                String address = tvMonad.getText().toString().toString();
                if (StringUtis.isEmpty(address) || address.length() == 0) {
                    showToast(getResources().getString(R.string.not_empty));
                    return;
                }
                sendRx(address, null);
                finish();
                break;
            case 5:
                String signature = etInfo.getText().toString().trim();
                if (StringUtis.isEmpty(signature) || signature.length() == 0) {
                    showToast(getResources().getString(R.string.not_empty));
                    return;
                }
                sendRx(signature, null);
                finish();
                break;
            case 6:
                String introduction = etInfo.getText().toString().trim();
                if (StringUtis.isEmpty(introduction) || introduction.length() == 0) {
                    showToast(getResources().getString(R.string.not_empty));
                    return;
                }
                sendRx(introduction, null);
                finish();
                break;
            case 7:
                String education = etInfo.getText().toString().trim();
                if (StringUtis.isEmpty(education) || education.length() == 0) {
                    showToast(getResources().getString(R.string.not_empty));
                    return;
                }
                sendRx(education, null);
                finish();
                break;
            case 8:
                String job = etInfo.getText().toString().trim();
                if (StringUtis.isEmpty(job) || job.length() == 0) {
                    showToast(getResources().getString(R.string.not_empty));
                    return;
                }
                sendRx(job, null);
                finish();
                break;
            case 9:
                String weight = etInfo.getText().toString().trim();
                if (StringUtis.isEmpty(weight) || weight.length() == 0) {
                    showToast(getResources().getString(R.string.not_empty));
                    return;
                }
                sendRx(weight, null);
                finish();
                break;
        }
    }

    private void sendRx(String arg1, String arg2) {
        Events<PersonInfoChangeData> events = new Events<PersonInfoChangeData>();
        events.what = Events.EventEnum.PERSON_INFO_CHANGE;
        PersonInfoChangeData personInfoChangeData = new PersonInfoChangeData();
        personInfoChangeData.setArg1(arg1);
        personInfoChangeData.setArg2(arg2);
        personInfoChangeData.setType(type);
        events.message = personInfoChangeData;
        RxBus.getInstance().send(events);
        finish();
    }

    public static class PersonInfoChangeData implements Serializable {
        private String arg1;
        private String arg2;
        private ChangeType type;

        public String getArg1() {
            return arg1;
        }

        public void setArg1(String arg1) {
            this.arg1 = arg1;
        }

        public String getArg2() {
            return arg2;
        }

        public void setArg2(String arg2) {
            this.arg2 = arg2;
        }

        public ChangeType getType() {
            return type;
        }

        public void setType(ChangeType type) {
            this.type = type;
        }
    }

}
