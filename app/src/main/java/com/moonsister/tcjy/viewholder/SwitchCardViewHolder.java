package com.moonsister.tcjy.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.moonsister.tcjy.ImageServerApi;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseRecyclerViewHolder;
import com.moonsister.tcjy.bean.CardInfoBean;
import com.moonsister.tool.lang.StringUtis;

import butterknife.Bind;

/**
 * Created by jb on 2016/7/4.
 */
public class SwitchCardViewHolder extends BaseRecyclerViewHolder<CardInfoBean.DataBean> {
    @Bind(R.id.iv_bank_logo)
    ImageView ivBankLogo;
    @Bind(R.id.tv_bank_name)
    TextView tvBankName;
    @Bind(R.id.tv_bank_number)
    TextView tvBankNumber;
    @Bind(R.id.iv_selected)
    ImageView ivSelected;

    public SwitchCardViewHolder(View view) {
        super(view);
    }

    @Override
    public void onBindData(CardInfoBean.DataBean dataBean) {
        if (dataBean == null)
            return;
        ImageServerApi.showURLSamllImage(ivBankLogo, dataBean.getLogo());
        tvBankName.setText(dataBean.getBank_name());

        tvBankNumber.setText(dataBean.getBank_no());
        if (StringUtis.equals("1", dataBean.getIs_default())) {
            ivSelected.setVisibility(View.VISIBLE);
        } else {
            ivSelected.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onItemclick(View view, CardInfoBean.DataBean dataBean, int position) {


    }
}
