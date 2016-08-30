package com.moonsister.tcjy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.moonsister.tcjy.ImageServerApi;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.ServerApi;
import com.moonsister.tcjy.bean.InsertBaen;
import com.moonsister.tcjy.my.widget.InsertActivity;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Map;

/**
 * Created by x on 2016/8/29.
 */
public class InsertAdapter extends BaseAdapter{
    Context context;
    List<InsertBaen.DataBean> data;
    public InsertAdapter(Context context, List<InsertBaen.DataBean> data) {
        this.context=context;
        this.data=data;
    }

    @Override
    public int getCount() {
        return data==null?0:data.size();
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
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.insertgridviewitem, viewGroup, false);
            holder = new ViewHolder();
            holder.insert_image = (ImageView) convertView.findViewById(R.id.insert_image);
            holder.insert_text = (TextView) convertView.findViewById(R.id.insert_text);
            holder.check = (CheckBox) convertView.findViewById(R.id.insert_checkbox);
            convertView.setTag(holder);
        } else {
            holder= (ViewHolder) convertView.getTag();
        }
        ImageServerApi.showURLBigImage(holder.insert_image, data.get(position).getImg());
        holder.insert_text.setText(data.get(position).getTagname());
        final ViewHolder finalHolder = holder;
        final ViewHolder finalHolder1 = holder;
        holder.check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                final boolean checked = finalHolder1.check.isChecked();
                int k=data.get(position).getTagid();
                
            }
        });
        return convertView;
    }
    class ViewHolder{
        ImageView insert_image;
        CheckBox check;
        TextView insert_text;

    }
}
