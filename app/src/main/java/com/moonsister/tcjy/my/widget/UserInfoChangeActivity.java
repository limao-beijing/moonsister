package com.moonsister.tcjy.my.widget;

import android.view.View;
import android.widget.TextView;

import com.moonsister.tcjy.ImageServerApi;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.bean.UserInfoChangeBean;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.login.widget.SelectPicPopupActivity;
import com.moonsister.tcjy.main.widget.PersonInfoChangeActivity;
import com.moonsister.tcjy.my.persenter.UserInfoChangeActivityPresenter;
import com.moonsister.tcjy.my.persenter.UserInfoChangeActivityPresenterImpl;
import com.moonsister.tcjy.my.view.UserInfoChangeActivityView;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.LogUtils;
import com.moonsister.tool.lang.StringUtis;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tcjy.widget.RoundedImageView;
import com.trello.rxlifecycle.ActivityEvent;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by jb on 2016/7/7.
 */
public class UserInfoChangeActivity extends BaseActivity implements UserInfoChangeActivityView {
    @Bind(R.id.riv_user_image)
    RoundedImageView rivUserImage;
    @Bind(R.id.tv_sex)
    TextView tvSex;
    @Bind(R.id.tv_nike_name)
    TextView tvnike;
    @Bind(R.id.tv_birthday)
    TextView tvBirthday;
    @Bind(R.id.tv_address)
    TextView tvAddress;
    @Bind(R.id.tv_signature)
    TextView tvSignature;
    @Bind(R.id.tv_introduction)
    TextView tvIntroduction;
    @Bind(R.id.tv_education)
    TextView tvEducation;
    @Bind(R.id.tv_height)
    TextView tvHeight;
    @Bind(R.id.tv_weight)
    TextView tvWeight;
    @Bind(R.id.tv_job)
    TextView tvJob;
    private UserInfoChangeActivityPresenter presenter;
    private String avater;

    @Override
    protected View setRootContentView() {
        return UIUtils.inflateLayout(R.layout.activity_userinfo_change);
    }

    @Override
    protected String initTitleName() {
        TextView tv_title_right = (TextView) findViewById(R.id.tv_title_right);
        tv_title_right.setText(UIUtils.getStringRes(R.string.finish));
        tv_title_right.setVisibility(View.VISIBLE);
        tv_title_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }


        });
        return UIUtils.getStringRes(R.string.change_user_info);
    }

    private void submit() {
        UserInfoChangeBean.DataBean dataBean = new UserInfoChangeBean.DataBean();
        dataBean.setFace(avater);
        dataBean.setNickname(tvnike.getText().toString());
        dataBean.setSex(StringUtis.equals(tvSex.getText().toString(), "1") ? 1 : 2);
        dataBean.setBirthday(tvBirthday.getText().toString());
        dataBean.setResidence(tvAddress.getText().toString());
        dataBean.setSignature(tvSignature.getText().toString());
        dataBean.setSelf_intro(tvIntroduction.getText().toString());
        dataBean.setDegree(tvEducation.getText().toString());
        dataBean.setHeight(tvHeight.getText().toString());

        dataBean.setWeight(tvWeight.getText().toString());
        dataBean.setProfession(tvJob.getText().toString());
        presenter.submit(dataBean);
    }

    @Override
    protected void initView() {
        presenter = new UserInfoChangeActivityPresenterImpl();
        presenter.attachView(this);
        presenter.loadbasicData();
        setRx();
    }

    private void setRx() {
        RxBus.with(this)
                .setEndEvent(ActivityEvent.DESTROY)
                .setEvent(Events.EventEnum.PERSON_INFO_CHANGE)
                .onNext(events -> {
                    action((Events<PersonInfoChangeActivity.PersonInfoChangeData>) events);
                })
                .create();
    }

    private void action(Events<PersonInfoChangeActivity.PersonInfoChangeData> events) {
        if (events == null)
            return;
        PersonInfoChangeActivity.PersonInfoChangeData message = events.message;

        switch (message.getType().getValue()) {
            case 1:
                tvHeight.setText(message.getArg1());
                break;
            case 2:
                tvSex.setText(message.getArg1());
                break;
            case 3:
                tvnike.setText(message.getArg1());
                break;
            case 4:
                tvAddress.setText(message.getArg1());
                break;
            case 5:
                tvSignature.setText(message.getArg1());
                break;
            case 6:
                tvIntroduction.setText(message.getArg1());
                break;
            case 7:
                tvEducation.setText(message.getArg1());
                break;
            case 8:
                tvJob.setText(message.getArg1());
                break;
            case 9:
                tvWeight.setText(message.getArg1());
                break;
            case 10:
                tvBirthday.setText(message.getArg1());
                break;
        }
    }


    @OnClick({R.id.layout_weight, R.id.layout_nike_name, R.id.layout_job, R.id.layout_avater, R.id.layout_sex, R.id.layout_birthday, R.id.layout_address, R.id.layout_signature, R.id.layout_introduction, R.id.layout_education, R.id.layout_height})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_avater:
                ActivityUtils.startActivity(SelectPicPopupActivity.class);
                RxBus.with(this)
                        .setEvent(Events.EventEnum.GET_PHOTO)
                        .setEndEvent(ActivityEvent.DESTROY)
                        .onNext((events) -> {
                            String message = (String) events.message;
                            LogUtils.e(RZFirstActivity.class, "pic_path : " + message);
                            avater = message;
                            ImageServerApi.showURLSamllImage(rivUserImage, message);
                        }).create();
                break;
            case R.id.layout_nike_name:
                ActivityUtils.startPersonInfoChangeActivity(PersonInfoChangeActivity.ChangeType.NIKE);
                break;
            case R.id.layout_sex:
                ActivityUtils.startPersonInfoChangeActivity(PersonInfoChangeActivity.ChangeType.SEX);
                break;
            case R.id.layout_birthday:
                String s = tvBirthday.getText().toString();
                ActivityUtils.startBirthdayActivity(s);
//                ActivityUtils.startPersonInfoChangeActivity(PersonInfoChangeActivity.ChangeType.);
                break;
            case R.id.layout_address:
                ActivityUtils.startPersonInfoChangeActivity(PersonInfoChangeActivity.ChangeType.ADDRESS);
                break;
            case R.id.layout_signature:
                ActivityUtils.startPersonInfoChangeActivity(PersonInfoChangeActivity.ChangeType.SIGNATURE);
                break;
            case R.id.layout_introduction:
                ActivityUtils.startPersonInfoChangeActivity(PersonInfoChangeActivity.ChangeType.INTRODUCTION);
                break;
            case R.id.layout_education:
                ActivityUtils.startPersonInfoChangeActivity(PersonInfoChangeActivity.ChangeType.EDUCATION);
                break;
            case R.id.layout_height:
                ActivityUtils.startPersonInfoChangeActivity(PersonInfoChangeActivity.ChangeType.HEIGHT);
                break;
            case R.id.layout_job:
                ActivityUtils.startPersonInfoChangeActivity(PersonInfoChangeActivity.ChangeType.JOB);
                break;
            case R.id.layout_weight:
                ActivityUtils.startPersonInfoChangeActivity(PersonInfoChangeActivity.ChangeType.WEIGHT);
                break;
        }
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
    public void setUserBasic(UserInfoChangeBean userInfoChangeBean) {
        if (userInfoChangeBean == null)
            return;
        UserInfoChangeBean.DataBean data = userInfoChangeBean.getData();
        if (data == null)
            return;
        ImageServerApi.showURLSamllImage(rivUserImage, data.getFace());
        tvnike.setText(data.getNickname());
        tvSex.setText(data.getSex() == 1 ? "男" : "女");
        tvBirthday.setText(data.getBirthday());
        tvAddress.setText(data.getResidence());
        tvSignature.setText(data.getSignature());
        tvIntroduction.setText(data.getSelf_intro());
        tvEducation.setText(data.getDegree());
        tvHeight.setText(data.getHeight());
        tvWeight.setText(data.getWeight());
        tvJob.setText(data.getProfession());
    }

    @Override
    public void pageFinish() {
        RxBus.getInstance().send(Events.EventEnum.USERINFO_CHANGE,null);
        finish();
    }


}
