package com.moonsister.tcjy.my.widget;

import android.view.View;
import android.widget.TextView;

import com.moonsister.tcjy.ImageServerApi;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.bean.PersonInfoDetail;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.login.widget.SelectPicPopupActivity;
import com.moonsister.tcjy.main.widget.PersonInfoChangeActivity;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.LogUtils;
import com.moonsister.tool.lang.StringUtis;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tcjy.widget.RoundedImageView;
import com.trello.rxlifecycle.ActivityEvent;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by jb on 2016/6/27.
 */
public class RZFirstActivity extends BaseActivity {
    @Bind(R.id.iv_avater)
    RoundedImageView ivAvater;
    @Bind(R.id.tv_nike)
    TextView tvNike;
    @Bind(R.id.tv_sex)
    TextView tvSex;
    @Bind(R.id.tv_address)
    TextView tvAddress;
    @Bind(R.id.tv_height)
    TextView tvHeight;
    @Bind(R.id.rule)
    TextView rule;//规则说明
    private String avaterPath;

    @Override
    protected View setRootContentView() {
        return UIUtils.inflateLayout(R.layout.activity_rzfirst);
    }

    @Override
    protected String initTitleName() {
        return UIUtils.getStringRes(R.string.apply_renzheng);
    }

    @Override
    protected void initView() {
        ImageServerApi.showURLSamllImage(ivAvater, UserInfoManager.getInstance().getAvater());
        PersonInfoDetail info =
                UserInfoManager.getInstance().getMemoryPersonInfoDetail();
        String sex = info.getSex();
        if (!StringUtis.isEmpty(sex)) {
            if (StringUtis.equals("1", sex)) {
                tvSex.setText(UIUtils.getStringRes(R.string.boy));
            } else {
                tvSex.setText(UIUtils.getStringRes(R.string.girls));
            }

        }
        String nickname = info.getNickname();
        if (!StringUtis.isEmpty(nickname))
            tvNike.setText(nickname);
        setRX();
    }


    private void pageFinish() {
        finish();
    }

    private void setRX() {

        RxBus.with(this)
                .setEndEvent(ActivityEvent.DESTROY)
                .setEvent(Events.EventEnum.CERTIFICATION_PAGE_FINISH)
                .onNext(events -> pageFinish())
                .create();
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
                tvNike.setText(message.getArg1());

                break;
            case 4:
                tvAddress.setText(message.getArg1());
                break;
        }
    }


    @OnClick({R.id.layout_nike, R.id.layout_sex, R.id.layout_address, R.id.layout_height, R.id.tv_next, R.id.iv_avater,R.id.rule})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_nike:
                ActivityUtils.startPersonInfoChangeActivity(PersonInfoChangeActivity.ChangeType.NIKE);
                break;
            case R.id.layout_sex:
//                ActivityUtils.startPersonInfoChangeActivity(PersonInfoChangeActivity.ChangeType.SEX);

                break;
            case R.id.layout_address:
                ActivityUtils.startPersonInfoChangeActivity(PersonInfoChangeActivity.ChangeType.ADDRESS);
                break;
            case R.id.layout_height:
                ActivityUtils.startPersonInfoChangeActivity(PersonInfoChangeActivity.ChangeType.HEIGHT);
                break;
            case R.id.tv_next:
                next();

                break;
            case R.id.iv_avater:
                ActivityUtils.startActivity(SelectPicPopupActivity.class);
                RxBus.with(this)
                        .setEvent(Events.EventEnum.GET_PHOTO)
                        .setEndEvent(ActivityEvent.DESTROY)
                        .onNext((events) -> {
                            String message = (String) events.message;
                            LogUtils.e(RZFirstActivity.class, "pic_path : " + message);
                            avaterPath = message;
                            ImageServerApi.showURLSamllImage(ivAvater, message);
                        }).create();

                break;
            case R.id.rule://规则说明监听
                ActivityUtils.startRuleActivity();//调用方法实现跳转

                break;

        }
    }

    private void next() {
        String address = tvAddress.getText().toString().trim();
        String height = tvHeight.getText().toString().trim();
        String sex = tvSex.getText().toString().trim();
        String nike = tvNike.getText().toString().trim();

        ActivityUtils.startRZSecondActivity(address, height, sex, nike, avaterPath);
    }
}
