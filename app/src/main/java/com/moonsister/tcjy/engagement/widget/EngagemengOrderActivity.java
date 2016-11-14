package com.moonsister.tcjy.engagement.widget;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.moonsister.tcjy.ImageServerApi;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.bean.EngagemengOrderBean;
import com.moonsister.tcjy.bean.EngagementDetailsBean;
import com.moonsister.tcjy.engagement.EngagementUtils;
import com.moonsister.tcjy.engagement.model.EngagemengOrderView;
import com.moonsister.tcjy.engagement.presenter.EngagemengOrderPresenter;
import com.moonsister.tcjy.engagement.presenter.EngagemengOrderPresenterImpl;
import com.moonsister.tcjy.engagement.presenter.EngagementTextPersenter;
import com.moonsister.tcjy.engagement.presenter.EngagementTextPersenterImpl;
import com.moonsister.tcjy.engagement.view.EngagementTextView;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.EnumConstant;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tcjy.widget.CircularImageView;
import com.moonsister.tool.lang.StringUtis;
import com.moonsister.tool.time.TimeUtils;

import butterknife.Bind;
import butterknife.OnClick;
import im.gouyin.com.progressdialog.wheelview.Dateselecter;

/**
 * Created by jb on 2016/9/21.
 */
public class EngagemengOrderActivity extends BaseActivity implements EngagemengOrderView, EngagementTextView {

    @Bind(R.id.tv_engagement)
    TextView mTvEngagement;
    @Bind(R.id.civ_user_avater)
    CircularImageView mCivUserAvater;
    @Bind(R.id.civ_user_avatered)
    CircularImageView mCivUserAvatered;
    @Bind(R.id.tv_user_nameed)
    TextView mTvUserNameed;
    @Bind(R.id.tv_user_name)
    TextView mTvUserName;
    @Bind(R.id.tv_engagement_pay)
    TextView mTvEngagementPay;
    @Bind(R.id.tv_engagement_date)
    TextView mTvEngagementDate;
    @Bind(R.id.tv_engagement_address)
    TextView mTvEngagementAddress;
    @Bind(R.id.et_input_address)
    TextView mEtInputAddress;
    @Bind(R.id.tv_engagement_message)
    TextView mTvEngagementMessage;
    @Bind(R.id.et_engagement_message)
    EditText mEtEngagementMessage;
    @Bind(R.id.et_input_date)
    TextView et_input_date;
    @Bind(R.id.et_input_money)
    EditText et_input_money;
    @Bind(R.id.tv_sumbit)
    TextView mTvSumbit;
    @Bind(R.id.tv_engagement_text)
    TextView tv_engagement_text;
    private EnumConstant.EngegamentType type;
    private String uid;
    private String nike;
    private String face;
    private Dateselecter dateselecter;
    //    private int[] picID = {R.mipmap.engagment_main_meal, R.mipmap.engagment_main_fadai,
//            R.mipmap.engagment_main_movie, R.mipmap.engagment_main_coffee,
//            R.mipmap.engagment_main_shop, R.mipmap.engagment_main_travel, R.mipmap.engagment_main_other, R.mipmap.engagment_main_more};
    private EngagemengOrderPresenter presenter;
    private EngagementTextPersenter textPersenter;

    private int last_count = 0;
    private int dating_money;

    @Override
    protected View setRootContentView() {
        Intent intent = getIntent();
        type = (EnumConstant.EngegamentType) intent.getSerializableExtra("type");
        uid = intent.getStringExtra("id");
        nike = intent.getStringExtra("nike");
        face = intent.getStringExtra("face");
        View view = UIUtils.inflateLayout(R.layout.activity_engagment_order);
        view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        return view;
    }

    @Override
    protected String initTitleName() {
        return getString(R.string.submit_engagment);
    }

    @Override
    protected void initView() {

        mTvEngagement.setText(EngagementUtils.getEngagementStr(type.getType()));
        presenter = new EngagemengOrderPresenterImpl();
        presenter.attachView(this);

        textPersenter = new EngagementTextPersenterImpl();
        textPersenter.attachView(this);

        textPersenter.loadText("", EnumConstant.EngegamentTextType.ENGEGAMENT_PUBLISH);
        ImageServerApi.showURLImage(mCivUserAvater, UserInfoManager.getInstance().getAvater());
        ImageServerApi.showURLImage(mCivUserAvatered, face);
        mTvUserName.setText(UserInfoManager.getInstance().getNickeName());
        mTvUserNameed.setText(nike);
//        int position = type.getType() - 1;
//        if (position >= 0) {
//            Drawable drawable = getResources().getDrawable(picID[position]);
//            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
//            mTvEngagement.setCompoundDrawables(null, drawable, null, null);
//            mTvEngagement.setText(getResources().getStringArray(R.array.engagement_type)[position]);
//        }
        dateselecter = new Dateselecter();
        dateselecter.startSelect(this, et_input_date);
        dateselecter.setDateselecterFinishListenter(new Dateselecter.onDateselecterFinishListenter() {
            @Override
            public void onFinish(String date) {
                et_input_date.setText(date);
            }
        });
        presenter.loadData();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        hideVirtualButtons();
        return super.onTouchEvent(event);
    }

    @OnClick({R.id.tv_sumbit, R.id.et_input_date})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.et_input_date:

                dateselecter.showPop();

                break;

            case R.id.tv_sumbit:
                String money = et_input_money.getText().toString();

                String date = et_input_date.getText().toString();
                String address = mEtInputAddress.getText().toString();
                String message = mEtEngagementMessage.getText().toString();
                if (StringUtis.isEmpty(money)
                        || StringUtis.isEmpty(date) || StringUtis.isEmpty(address)
                        || StringUtis.isEmpty(message)) {
                    showToast(getString(R.string.input_date_not_empty));
                    return;
                }
                if (dating_money > StringUtis.string2Int(money)) {
                    showToast("金额不能低于" + dating_money);
                    return;
                }
                long l = TimeUtils.formatTimestamp(date + ":00");
                if ((l - System.currentTimeMillis()) < (1000 * 60 * 60 * 2)) {
                    showToast("约会时间不能少于2个小时");
                    return;
                }
                presenter.submit(last_count + "", type, uid, money, (TimeUtils.formatTimestamp(date + ":00") / 1000) + "", message, address);
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
    public void submitSuccess() {
        EngagementDetailsBean.DataBean bean = new EngagementDetailsBean.DataBean();
        bean.setAddress(mEtInputAddress.getText().toString());
        bean.setStatus(1);
        bean.setMsgX(mEtEngagementMessage.getText().toString());
        bean.setMoney(et_input_money.getText().toString());
        long l = TimeUtils.formatTimestamp(et_input_date.getText().toString() + ":00");
        if ((l - System.currentTimeMillis()) > (48 * 3600000)) {
            bean.setDaojishi(48 * 3600);
        } else {
            bean.setDaojishi((l - System.currentTimeMillis()) / 1000);
        }
        bean.setDate((TimeUtils.formatTimestamp(et_input_date.getText().toString() + ":00") / 1000) + "");
        bean.setF_face(UserInfoManager.getInstance().getAvater());
        bean.setF_nickname(UserInfoManager.getInstance().getNickeName());
        bean.setF_uid(UserInfoManager.getInstance().getUid());

        bean.setT_face(face);
        bean.setT_nickname(nike);
        bean.setT_uid(uid);
        bean.setType(type.getType());
        Intent intent = new Intent(this, EngagementDetailsActivity.class);
        intent.putExtra("data", bean);
        startActivity(intent);
        finish();
    }

    @Override
    public void notLevel() {
        showNotLevel();
    }

    @Override
    public void setData(EngagemengOrderBean bean) {
        if (bean != null && bean.getData() != null) {
            EngagemengOrderBean.DataBean data = bean.getData();
            dating_money = StringUtis.string2Int(data.getLimit_min());
            et_input_money.setHint("请输入约见赏金，赏金越高，约见成功率越高");
        }
    }

    private void showNotLevel() {
        AlertDialog myDialog = new AlertDialog.Builder(this).create();
        myDialog.show();
        View view = UIUtils.inflateLayout(R.layout.dialog_show_notlevel);
        myDialog.getWindow().setContentView(view);
        view.findViewById(R.id.view_que)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityUtils.startBuyVipActivity();
                        myDialog.dismiss();
                    }
                });


    }

    @Override
    public void setEngagementText(String info) {
        tv_engagement_text.setText(info);
    }

    @SuppressLint("NewApi")
    private void hideVirtualButtons() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_IMMERSIVE);
        }
    }

}
