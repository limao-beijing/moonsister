package com.moonsister.tcjy.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hickey.network.ImageServerApi;
import com.hickey.network.bean.BalanceBean;
import com.hickey.tool.base.BaseRecyclerViewHolder;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.adapter.MoneyAdapter;
import com.moonsister.tcjy.utils.ActivityUtils;

import butterknife.Bind;

/**
 * Created by x on 2016/9/9.
 */
public class MoneyHolder extends BaseRecyclerViewHolder<BalanceBean.DataBean> {
    @Bind(R.id.onfragment_im)//头像
    ImageView onfragment_imge;
    @Bind(R.id.text_time)//时间
        TextView text_time;
//    @Bind(R.id.text_time1)
//    TextView text_time1;
    @Bind(R.id.text_money)//钱
        TextView text_money;
    @Bind(R.id.text_tc)
        TextView text_tc;
    private MoneyAdapter adapter;
    public MoneyHolder(View view) {
        super(view);
    }

    @Override
    public void onBindData(BalanceBean.DataBean dataBean) {

    }
    @Override
    public void onBindData(BalanceBean.DataBean dataBean,int position) {
//        String time = dataBean.getTime().toString();
//        String[] splitAddress=time.split("\\|||");
//        text_time.setText(splitAddress[0]);
//        text_time1.setText(splitAddress[1]);
        ImageServerApi.showURLSamllImage(onfragment_imge, dataBean.getPic());
        text_time.setText(dataBean.getTime());
        text_money.setText(dataBean.getMoney());
        text_tc.setText(dataBean.getDesc());
    }
    @Override
    protected void onItemclick(View view, BalanceBean.DataBean dataBean, int position) {
        String uid = String.valueOf(dataBean.getAct_uid());
        ActivityUtils.startHomePageActivity(uid);

    }

    public void setAdapter(MoneyAdapter adapter) {
        this.adapter = adapter;
    }
}
