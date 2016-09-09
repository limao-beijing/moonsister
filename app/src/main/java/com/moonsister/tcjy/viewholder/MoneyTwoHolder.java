package com.moonsister.tcjy.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.moonsister.tcjy.ImageServerApi;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.adapter.MoneyAdapter;
import com.moonsister.tcjy.adapter.MoneyTwoAdapter;
import com.moonsister.tcjy.base.BaseRecyclerViewHolder;
import com.moonsister.tcjy.bean.BalanceBean;

import butterknife.Bind;

/**
 * Created by x on 2016/9/9.
 */
public class MoneyTwoHolder extends BaseRecyclerViewHolder<BalanceBean.DataBean> {
    @Bind(R.id.onfragment_im)//头像
            ImageView onfragment_imge;
    @Bind(R.id.text_time)//时间
            TextView text_time;
    @Bind(R.id.text_money)//钱
            TextView text_money;
    @Bind(R.id.text_tc)
    TextView text_tc;
    private MoneyTwoAdapter adapter;
    public MoneyTwoHolder(View view) {
        super(view);
    }

    @Override
    public void onBindData(BalanceBean.DataBean dataBean) {

    }
    @Override
    public void onBindData(BalanceBean.DataBean dataBean,int position) {
        ImageServerApi.showURLSamllImage(onfragment_imge, dataBean.getPic());
        text_time.setText(dataBean.getTime()+"");
        text_money.setText(dataBean.getMoney()+"");
        text_tc.setText(dataBean.getDesc()+"");
    }
    @Override
    protected void onItemclick(View view, BalanceBean.DataBean dataBean, int position) {

    }

    public void setAdapter(MoneyTwoAdapter adapter) {
        this.adapter = adapter;
    }
}
