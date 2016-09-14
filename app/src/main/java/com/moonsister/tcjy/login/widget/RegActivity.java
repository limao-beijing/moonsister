package com.moonsister.tcjy.login.widget;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.bean.PersonInfoDetail;
import com.moonsister.tcjy.login.presenter.RegActivityPresenter;
import com.moonsister.tcjy.login.presenter.RegActivityPresenterImpl;
import com.moonsister.tcjy.login.view.RegThridActivityView;
import com.moonsister.tcjy.main.widget.FillOutMessageActivity;
import com.moonsister.tcjy.main.widget.MainActivity;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ConfigUtils;
import com.moonsister.tcjy.utils.UIUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by x on 2016/8/30.
 */
public class RegActivity extends BaseActivity implements RegThridActivityView {
    @Bind(R.id.et_phone_number)//手机号
            EditText etPhoneNumber;
    @Bind(R.id.reg_edit_password)//密码
            EditText reg_edit_password;
//    @Bind(R.id.reg_edit_brith)//出生年月日
//            EditText reg_edit_brith;
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

    @Override
    protected View setRootContentView() {
        return UIUtils.inflateLayout(R.layout.activity_reg);
    }

    @Override
    protected String initTitleName() {
        return UIUtils.getStringRes(R.string.phone_binding);
    }

    @Override
    protected void initView() {//绑定
        persenter = new RegActivityPresenterImpl();
        persenter.attachView(this);
    }

    @OnClick({R.id.tv_submit, R.id.tv_security_code, R.id.let_go,R.id.old_code})
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
//            birthday = reg_edit_brith.getText().toString().trim();
            pwd = reg_edit_password.getText().toString().trim();
            if (mobile == null || mobile.isEmpty() || pwd == null || pwd.isEmpty() || code == null || code.isEmpty()) {
//                showToast(resources.getString(R.string.input_Security_code) + resources.getString(R.string.input_phone_number) + resources.getString(R.string.not_empty));

            } else {
                persenter.getThrid(mobile, pwd, birthday, code);
            }

        } else if (view.getId() == R.id.let_go) {
            Intent intent = new Intent(RegActivity.this, MainActivity.class);
            startActivity(intent);
        }else if(view.getId() == R.id.old_code){
            Intent intent = new Intent(RegActivity.this, LoginMainActivity.class);
            startActivity(intent);
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
        memoryPersonInfoDetail.setSmobile(mobile);//保存值
        UserInfoManager.getInstance().saveMemoryInstance(memoryPersonInfoDetail);
        Intent intent = new Intent(RegActivity.this, FillOutMessageActivity.class);
        startActivity(intent);
        this.finish();
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
