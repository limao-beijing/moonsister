package com.moonsister.appointment.engagement.widget;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.moonsister.tcjy.ImageServerApi;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.bean.EngagemengOrderBean;
import com.moonsister.tcjy.engagement.model.EngagemengOrderView;
import com.moonsister.tcjy.engagement.presenter.EngagemengOrderPresenter;
import com.moonsister.tcjy.engagement.presenter.EngagemengOrderPresenterImpl;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.EnumConstant;
import com.moonsister.tcjy.utils.StringUtis;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tcjy.widget.CircularImageView;

import butterknife.Bind;
import butterknife.OnClick;
import im.gouyin.com.progressdialog.wheelview.Dateselecter;

/**
 * Created by jb on 2016/9/21.
 */
public class EngagemengOrderActivity extends BaseActivity implements EngagemengOrderView {

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
    @Bind(R.id.tv_center_txt)
    TextView mTvCenterTxt;
    @Bind(R.id.et_input_date)
    TextView et_input_date;
    @Bind(R.id.et_input_money)
    EditText et_input_money;
    @Bind(R.id.tv_sumbit)
    TextView mTvSumbit;
    private EnumConstant.EngegamentType type;
    private String uid;
    private String nike;
    private String face;
    private Dateselecter dateselecter;
    private int[] picID = {R.mipmap.engagment_main_meal, R.mipmap.engagment_main_fadai,
            R.mipmap.engagment_main_movie, R.mipmap.engagment_main_coffee,
            R.mipmap.engagment_main_shop, R.mipmap.engagment_main_travel, R.mipmap.engagment_main_other, R.mipmap.engagment_main_more};
    private EngagemengOrderPresenter presenter;


    private int last_count = 0;
    private int dating_money;

    @Override
    protected View setRootContentView() {
        Intent intent = getIntent();
        type = (EnumConstant.EngegamentType) intent.getSerializableExtra("type");
        uid = intent.getStringExtra("id");
        nike = intent.getStringExtra("nike");
        face = intent.getStringExtra("face");
        return UIUtils.inflateLayout(R.layout.activity_engagment_order);
    }

    @Override
    protected String initTitleName() {
        return getString(R.string.submit_engagment);
    }

    @Override
    protected void initView() {
        presenter = new EngagemengOrderPresenterImpl();
        presenter.attachView(this);
        ImageServerApi.showURLImage(mCivUserAvater, UserInfoManager.getInstance().getAvater());
        ImageServerApi.showURLImage(mCivUserAvatered, face);
        mTvUserName.setText(UserInfoManager.getInstance().getNickeName());
        mTvUserNameed.setText(nike);
        int position = type.getType() - 1;
        if (position >= 0) {
            Drawable drawable = getResources().getDrawable(picID[position]);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            mTvEngagement.setCompoundDrawables(null, drawable, null, null);
            mTvEngagement.setText(getResources().getStringArray(R.array.engagement_type)[position]);
        }
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
                    showToast("担保金额不能低于" + dating_money);
                    return;
                }
                presenter.submit(last_count + "", type, uid, money, date, message, address);
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
            dating_money = StringUtis.string2Int(data.getDating_money());
            last_count = StringUtis.string2Int(data.getLast_count());
            if (last_count > 0) {
                mTvSumbit.setText("还有" + last_count + "次免费约见");
                et_input_money.setText(dating_money+"");
                et_input_money.setFocusable(false);
                et_input_money.setClickable(false);
            }
            et_input_money.setHint("担保金额不能低于" + dating_money);
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


}
