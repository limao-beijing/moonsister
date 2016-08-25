package com.moonsister.tcjy.login.widget;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.login.presenter.FindPasswordActivityPresenterImpl;
import com.moonsister.tcjy.login.presenter.FindPasswordActivityPresenter;
import com.moonsister.tcjy.main.view.FindPasswordActivityView;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.ConfigUtils;
import com.moonsister.tcjy.utils.UIUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by jb on 2016/7/11.
 */
public class FindPasswordActivity extends BaseActivity implements FindPasswordActivityView {
    @Bind(R.id.tv_security_code)
    TextView tvSecurityCode;
    @Bind(R.id.et_phone_number)
    EditText etPhoneNumber;
    @Bind(R.id.et_security_code)
    EditText etSecurityCode;
    @Bind(R.id.tv_submit)
    TextView tvSubmit;
    private FindPasswordActivityPresenter presenter;

    @Override
    protected View setRootContentView() {

        return UIUtils.inflateLayout(R.layout.fragment_regiter);
    }

    @Override
    protected void initView() {
        tvSubmit.setText(UIUtils.getStringRes(R.string.find_pwd));
        presenter = new FindPasswordActivityPresenterImpl();
        presenter.attachView(this);
    }


    @OnClick({R.id.tv_security_code, R.id.tv_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_security_code:
                String phoneNumber = etPhoneNumber.getText().toString().trim();
                if (!phoneNumber.isEmpty()) {
                    currCount = 0;
                    presenter.getSecurityCode(phoneNumber);

                } else
                    showToast(UIUtils.getStringRes(R.string.input_phone_number) + UIUtils.getStringRes(R.string.not_empty));
                break;
            case R.id.tv_submit:
                String code = etSecurityCode.getText().toString().trim();
                String phone = etPhoneNumber.getText().toString().trim();
                if (code == null || code.isEmpty() || phone == null || phone.isEmpty()) {
                    showToast(UIUtils.getStringRes(R.string.input_Security_code) + UIUtils.getStringRes(R.string.input_phone_number) + UIUtils.getStringRes(R.string.not_empty));

                } else {
                    presenter.submitRegiter(phone, code);
                }
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

    private int currCount;

    @Override
    public void LoopMsg() {
        ConfigUtils.getInstance().getMainHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (tvSecurityCode == null)
                    return;
                if (currCount <= 60) {
                    tvSecurityCode.setClickable(false);
                    tvSecurityCode.setFocusable(false);
                    tvSecurityCode.setText((60 - currCount) + "s");
                    LoopMsg();
                    currCount++;
                } else {
                    tvSecurityCode.setText(getResources().getString(R.string.agein_get_code));
                    tvSecurityCode.setClickable(true);
                    tvSecurityCode.setFocusable(true);
                }
            }
        }, 1000);
    }

    @Override
    public void navigationNext(String authcode) {
        finish();
        ActivityUtils.startFindPasswordNextActivity(authcode);
    }

}
