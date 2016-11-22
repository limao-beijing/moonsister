package com.moonsister.tcjy.dialogFragment.widget;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hickey.network.bean.PersonInfoDetail;
import com.hickey.tool.lang.StringUtis;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.login.presenter.RegActivityPresenter;
import com.moonsister.tcjy.login.presenter.RegActivityPresenterImpl;
import com.moonsister.tcjy.login.view.RegThridActivityView;
import com.moonsister.tcjy.login.widget.LoginMainActivity;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.my.widget.BirthdayActivity;
import com.moonsister.tcjy.utils.ConfigUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by jb on 2016/11/2.
 */

public class BindPhoneDialogFragment extends BaseDialogFragment implements RegThridActivityView {
    @Bind(R.id.et_phone_number)//手机号
            EditText etPhoneNumber;
    @Bind(R.id.reg_edit_password)//密码
            EditText reg_edit_password;
    @Bind(R.id.reg_edit_brith)//出生年月日
            TextView reg_edit_brith;
    @Bind(R.id.et_security_code)//输入验证码
            EditText etCode;
    @Bind(R.id.tv_submit)//完成注册
            TextView tvSubmit;
    @Bind(R.id.let_go)//先去逛逛
            TextView let_go;
    @Bind(R.id.tv_security_code)//获取验证码
            TextView tvSecurityCode;
    @Bind(R.id.old_code)
    TextView old_code;
    RegActivityPresenter persenter;
    String pwd;
    String birthday;
    String code;
    private int currCount;
    String mobile;

    @NonNull
    @Override
    protected int initViewId() {
        return R.layout.df_bind_phone;
    }

    @Override
    protected void initData() {
        persenter = new RegActivityPresenterImpl();
        persenter.attachView(this);
    }

    @OnClick({R.id.tv_submit, R.id.tv_security_code, R.id.let_go, R.id.old_code, R.id.reg_edit_brith})
    public void onClick(View view) {

        if (view.getId() == R.id.tv_security_code) {//获取验证码
            String mo = etPhoneNumber.getText().toString().trim();

            if (!mo.isEmpty()) {
                currCount = 0;
                persenter.getSecurityCode(mo);

            }

        } else if (view.getId() == R.id.tv_submit) {//完成注册

            code = etCode.getText().toString().trim();
            mobile = etPhoneNumber.getText().toString().trim();
            birthday = reg_edit_brith.getText().toString().trim();
            if (StringUtis.isEmpty(birthday)) {
                showToast(getString(R.string.birthday) + getString(R.string.not_empty));
                return;
            }
            pwd = reg_edit_password.getText().toString().trim();
            if (StringUtis.isEmpty(pwd)) {
                showToast(getString(R.string.input_password) + getString(R.string.not_empty));
                return;
            }
            if (StringUtis.isEmpty(mobile)) {
                showToast(getString(R.string.input_phone_number) + getString(R.string.not_empty));
                return;
            }
            if (StringUtis.isEmpty(code)) {
                showToast(getString(R.string.input_Security_code) + getString(R.string.not_empty));
                return;
            }
            persenter.getThrid(mobile, pwd, birthday, code);

        } else if (view.getId() == R.id.let_go) {
            dismissDialogFragment();
        } else if (view.getId() == R.id.old_code) {
            Intent intent = new Intent(mActivity, LoginMainActivity.class);
            startActivity(intent);
            dismissDialogFragment();
        } else if (view.getId() == R.id.reg_edit_brith) {
            Intent intent2 = new Intent(mActivity,
                    BirthdayActivity.class);
            startActivityForResult(intent2, 1);
        }

    }


    @Override
    public void LoopMsg() {//验证码60s倒计时
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
    public void finishPage() {
        PersonInfoDetail memoryPersonInfoDetail = UserInfoManager.getInstance().getMemoryPersonInfoDetail();//获得对象
        memoryPersonInfoDetail.setSmobile(etPhoneNumber.getText().toString().trim());//保存值
        UserInfoManager.getInstance().saveMemoryInstance(memoryPersonInfoDetail);
//        Intent intent = new Intent(mActivity, FillOutMessageActivity.class);
//        startActivity(intent);
        dismissDialogFragment();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null)
            return;
        if (requestCode == 1) {
            reg_edit_brith.setText(data.getStringExtra("result"));
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

}
