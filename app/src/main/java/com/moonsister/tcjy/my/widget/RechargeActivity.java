package com.moonsister.tcjy.my.widget;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.main.widget.MainActivity;
import com.moonsister.tcjy.my.persenter.RechargeActivityPersenter;
import com.moonsister.tcjy.my.persenter.RechargeActivityPersenterImpl;
import com.moonsister.tcjy.my.view.RechargeActivityView;
import com.moonsister.tcjy.utils.EnumConstant;
import com.moonsister.tcjy.utils.UIUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by x on 2016/8/26.
 */
public class RechargeActivity extends BaseActivity implements RechargeActivityView {
    @Bind(R.id.recharge_edit)//输入充值金额的框框
    EditText recharge_edit;
    @Bind(R.id.kending)//确定按钮
    TextView kending;
    String pay_type;
    RechargeActivityPersenter persenter;
    String recharge;
    @Override
    protected View setRootContentView() {

        return UIUtils.inflateLayout(R.layout.activity_recharge);
    }

    @Override
    protected void initView() {

        persenter=new RechargeActivityPersenterImpl();
        persenter.attachView(this);



    }

    @Override
    public void success() {
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
        RechargeActivity.this.finish();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void transfePageMsg(String msg) {
        showToast(msg);
    }

    @OnClick(R.id.kending)
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.kending:
                recharge=recharge_edit.getText().toString();
                if(recharge.equals("")){
                    return;
                }
                persenter.LoadData(recharge, EnumConstant.PayType.IAPP_PAY.getType());
                break;
        }
    }
}
