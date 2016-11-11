package com.moonsister.tcjy.my.widget;

import android.view.View;
import android.widget.EditText;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.my.persenter.AddCardActivityPersenter;
import com.moonsister.tcjy.my.persenter.AddCardActivityPersenterImpl;
import com.moonsister.tcjy.my.view.AddCardActivityView;
import com.moonsister.tool.lang.StringUtis;
import com.moonsister.tcjy.utils.UIUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by jb on 2016/7/3.
 */
public class AddCardActivity extends BaseActivity implements AddCardActivityView {
    @Bind(R.id.et_input_account_number)
    EditText etInputAccountNumber;
    @Bind(R.id.et_input_name)
    EditText etInputName;
    private AddCardActivityPersenter persenter;

    @Override
    protected View setRootContentView() {
        persenter = new AddCardActivityPersenterImpl();
        persenter.attachView(this);
        return UIUtils.inflateLayout(R.layout.activity_add_card);
    }

    @Override
    protected String initTitleName() {
        return getIntent().getStringExtra("bankname");
    }

    @Override
    protected void initView() {

    }

    @OnClick(R.id.tv_submit)
    public void onClick() {
        String number = etInputAccountNumber.getText().toString().trim();
        if (StringUtis.isEmpty(number)) {
            showToast(UIUtils.getStringRes(R.string.account_number) + UIUtils.getStringRes(R.string.not_empty));
            return;
        }

        String name = etInputName.getText().toString().trim();
        if (StringUtis.isEmpty(name)) {
            showToast(UIUtils.getStringRes(R.string.name) + UIUtils.getStringRes(R.string.not_empty));
            return;
        }
        String type = getIntent().getStringExtra("type");
        String bankname = getIntent().getStringExtra("bankname");
        persenter.submitAccount(number, name, type, bankname);
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
