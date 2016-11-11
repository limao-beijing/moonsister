package com.moonsister.tcjy.viewholder;

import android.view.View;
import android.widget.TextView;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseRecyclerViewHolder;
import com.moonsister.tcjy.bean.TiXinrRecordBean;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tool.lang.StringUtis;
import com.moonsister.tool.time.TimeUtils;
import com.moonsister.tcjy.utils.UIUtils;

import butterknife.Bind;

/**
 * Created by jb on 2016/7/2.
 */
public class TixianViewHolder extends BaseRecyclerViewHolder<TiXinrRecordBean.DataBean> {
    @Bind(R.id.tv_money)
    TextView tvMoney;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.tv_flag)
    TextView tv_flag;

    public TixianViewHolder(View view) {
        super(view);
    }

    @Override
    public void onBindData(TiXinrRecordBean.DataBean dataBean) {
        tvMoney.setText(dataBean.getMoney());
        tvTime.setText(TimeUtils.format(dataBean.getAudit_time() * 1000));
        /**
         * status 提现状态 1申请中 2已通过 3不通过
         */
        String status = dataBean.getStatus();
        if (StringUtis.equals("1", status)) {
            tv_flag.setText(UIUtils.getStringRes(R.string.tixining));
        } else if (StringUtis.equals("2", status)) {
            tv_flag.setText(UIUtils.getStringRes(R.string.success) + "    ");
        } else if (StringUtis.equals("3", status)) {
            tv_flag.setText(UIUtils.getStringRes(R.string.not_success));
        }


    }

    @Override
    protected void onItemclick(View view, TiXinrRecordBean.DataBean dataBean, int position) {
        ActivityUtils.startTiXianRecordActivity(dataBean);
    }
}
