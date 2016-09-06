package com.moonsister.tcjy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.moonsister.tcjy.ImageServerApi;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.bean.BalanceBean;

import java.util.List;

/**
 * Created by x on 2016/9/2.
 */
public class MoneyAdapter extends BaseAdapter{
    Context context;
    List<BalanceBean.DataBean> data;
    public MoneyAdapter(Context context, List<BalanceBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.onefragmentitem, null);
            holder = new ViewHolder();
            holder.onfragment_imge = (ImageView) convertView.findViewById(R.id.onfragment_im);
            holder.text_time = (TextView) convertView.findViewById(R.id.text_time);
            holder.text_money = (TextView) convertView.findViewById(R.id.text_money);
            holder.text_tc = (TextView) convertView.findViewById(R.id.text_tc);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ImageServerApi.showURLBigImage(holder.onfragment_imge, data.get(position).getPic());
        holder.text_time.setText(data.get(position).getTime()+"");
        holder.text_money.setText(data.get(position).getMoney());
        holder.text_tc.setText(data.get(position).getDesc());
        return convertView;
    }
    class ViewHolder{
        ImageView onfragment_imge;
        TextView text_time;
        TextView text_money;
        TextView text_tc;
    }
}
