package com.moonsister.tcjy.adapter;

import android.content.Context;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.bean.GoodSelectBaen;
import com.moonsister.tcjy.bean.PersonalMessageBean;
import com.moonsister.tcjy.my.widget.PersonalActivity;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by x on 2016/8/27.
 */
public class PersonalAdapter extends BaseAdapter {
    private Context mContext;
    private List<PersonalMessageBean.DataBean.RulesBean> data;
    public PersonalAdapter(Context mContext, List<PersonalMessageBean.DataBean.RulesBean> data) {
        this.mContext = mContext;
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
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.personallistviewitem, parent, false);
            holder = new ViewHolder();
            holder.text_name = (TextView) convertView.findViewById(R.id.text_name);
            holder.text_number = (TextView) convertView.findViewById(R.id.text_number);
            ;
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.text_name.setText(data.get(position).getName());
        return convertView;
    }
    class ViewHolder{
        TextView text_name;
        TextView text_number;
    }
}
