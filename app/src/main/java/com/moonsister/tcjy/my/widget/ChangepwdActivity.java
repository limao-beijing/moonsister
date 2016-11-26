package com.moonsister.tcjy.my.widget;

import android.view.View;
import android.widget.EditText;

import com.hickey.tool.base.BaseActivity;
import com.hickey.tool.lang.StringUtis;
import com.hickey.tool.widget.UIUtils;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.main.presenter.ChangepwdActivityPresenter;
import com.moonsister.tcjy.main.presenter.ChangepwdActivityPresenterImpl;
import com.moonsister.tcjy.main.view.ChangepwdActivityView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by jb on 2016/6/27.
 */
public class ChangepwdActivity extends BaseActivity implements ChangepwdActivityView {
    @Bind(R.id.old_password)
    EditText oldPassword;
    @Bind(R.id.new_password)
    EditText newPassword;
    @Bind(R.id.new_input_password)
    EditText newInputPassword;
    private ChangepwdActivityPresenter presenter;

    @Override
    protected View setRootContentView() {
        return UIUtils.inflateLayout(R.layout.activity_change_pwd);
    }

    @Override
    protected String initTitleName() {
        return UIUtils.getStringRes(R.string.change_password);
    }

    @Override
    protected void initView() {
        presenter = new ChangepwdActivityPresenterImpl();
        presenter.attachView(this);
    }

    @OnClick({R.id.tv_submit, R.id.tv_reset})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_submit:
                String oldpwd = oldPassword.getText().toString();
                String newpwd = newPassword.getText().toString();
                String newInputpwd = newInputPassword.getText().toString();
                if (StringUtis.isEmpty(oldpwd)) {
                    showToast(UIUtils.getStringRes(R.string.old_password) + UIUtils.getStringRes(R.string.not_empty));
                    return;
                }
                if (StringUtis.isEmpty(newpwd) || StringUtis.isEmpty(newInputpwd)) {
                    showToast(UIUtils.getStringRes(R.string.new_password) + UIUtils.getStringRes(R.string.again_input) + UIUtils.getStringRes(R.string.not_empty));
                    return;
                }
                if (!StringUtis.equals(newpwd, newInputpwd)) {
                    showToast(UIUtils.getStringRes(R.string.new_password) + UIUtils.getStringRes(R.string.again_input) + UIUtils.getStringRes(R.string.not_same));
                    return;
                }
                presenter.submit(oldpwd, newpwd);
                break;
            case R.id.tv_reset:
                oldPassword.setText("");
                newPassword.setText("");
                newInputPassword.setText("");
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
    public void pageFinish() {
        finish();
    }
}
