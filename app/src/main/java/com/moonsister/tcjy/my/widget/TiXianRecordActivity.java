package com.moonsister.tcjy.my.widget;

import android.view.View;
import android.widget.TextView;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.bean.TiXinrRecordBean;
import com.moonsister.tool.lang.StringUtis;
import com.moonsister.tool.time.TimeUtils;
import com.moonsister.tcjy.utils.UIUtils;

import butterknife.Bind;

public class TiXianRecordActivity extends BaseActivity {
    @Bind(R.id.system_time)
    TextView system_time;
    @Bind(R.id.tv_tixian_time1)
    TextView tv_tixian_time1;
    @Bind(R.id.tv_tixian_time2)
    TextView tv_tixian_time2;
    @Bind(R.id.tv_money__number)
    TextView tv_money_number;
    @Bind(R.id.tv_reason)
    TextView tv_reason;
    @Bind(R.id.bank_name)
    TextView bank_name;

    @Override
    protected View setRootContentView() {

        return UIUtils.inflateLayout(R.layout.activity_ti_xian_record);
    }

    @Override
    public void initView() {
        TiXinrRecordBean.DataBean data = (TiXinrRecordBean.DataBean) getIntent().getSerializableExtra("data");
        if (data != null) {
            tv_money_number.setText(UIUtils.getStringRes(R.string.money) + " : " + data.getMoney());
            tv_tixian_time1.setText(TimeUtils.format(data.getCreate_time()));
            tv_tixian_time2.setText(TimeUtils.format(data.getCreate_time()));
            system_time.setText(TimeUtils.format(data.getAudit_time()));
            bank_name.setText("转款到" + data.getAccount_bank_name() + "  " + data.getAccount_bank_no());

            if (StringUtis.equals("3", data.getStatus())) {
                tv_reason.setVisibility(View.VISIBLE);
                tv_reason.setText(data.getRemarks());
            }
        }
    }

}
