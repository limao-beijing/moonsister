package com.moonsister.tcjy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.hickey.network.bean.PersonalMessageBean;
import com.moonsister.tcjy.R;

import java.util.List;

/**
 * Created by x on 2016/9/5.
 */
public class PersonalReviseAdapter extends BaseAdapter {
    private Context mContext;
    private List<PersonalMessageBean.DataBean.RulesBean> data;
    String s;
    public PersonalReviseAdapter(Context mContext, List<PersonalMessageBean.DataBean.RulesBean> data) {
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
            convertView = inflater.inflate(R.layout.personalreviseactivitylistview, parent, false);
            holder = new ViewHolder();
            holder.text_name = (TextView) convertView.findViewById(R.id.text_name);
            holder.text_number = (EditText) convertView.findViewById(R.id.text_number);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String edit = data.get(position).getEdit();
//        if(edit=="1"){
//            final ViewHolder finalHolder = holder;
//            holder.text_number.addTextChangedListener(new TextWatcher() {
//                @Override
//                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                    s = charSequence.toString();
//                }
//
//                @Override
//                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//                }
//
//                @Override
//                public void afterTextChanged(Editable editable) {
//                    finalHolder.text_number.setText(s);
//                }
//            });
//        }else{
//            holder.text_number.setInputType(InputType.TYPE_NULL);
//        }

        if(edit=="1"){
            holder.text_number.setFocusable(true);
            holder.text_number.setClickable(true);
        }else{
            holder.text_number.setFocusable(false);
            holder.text_number.setClickable(false);
        }
//        holder.text_number.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//            }
//        });

        holder.text_name.setText(data.get(position).getName());
        holder.text_number.setText(data.get(position).getValue());

        return convertView;
    }
    class ViewHolder{
        TextView text_name;
        EditText text_number;
    }
}
