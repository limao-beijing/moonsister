package com.moonsister.tcjy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hickey.network.ImageServerApi;
import com.hickey.network.bean.RecommendMemberFragmentBean;
import com.moonsister.tcjy.R;

import java.util.List;

/**
 * Created by Shall on 2015-06-23.
 */
public class CardAdapter extends BaseAdapter {
    private Context mContext;
    private List<RecommendMemberFragmentBean.DataBean> mCardList;

    public CardAdapter(Context mContext, List<RecommendMemberFragmentBean.DataBean> mCardList) {
        this.mContext = mContext;
        this.mCardList = mCardList;
    }

    @Override
    public int getCount() {
        return mCardList.size();
    }

    @Override
    public Object getItem(int position) {
        return mCardList.get(position);
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
            convertView = inflater.inflate(R.layout.item_recommend, parent, false);
            holder = new ViewHolder();
            holder.mCardImageView = (ImageView) convertView.findViewById(R.id.helloText);
            holder.mCardName = (TextView) convertView.findViewById(R.id.card_name);
            holder.tv_age = (TextView) convertView.findViewById(R.id.tv_age);
            holder.mCardYear = (TextView) convertView.findViewById(R.id.card_year);
            holder.age_image = (ImageView) convertView.findViewById(R.id.yy);
            holder.tv_talk = (TextView) convertView.findViewById(R.id.tv_talk);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String sex=mCardList.get(position).getSex();
        if(sex=="1"){
            holder.age_image.setImageResource(R.drawable.man);
        }else{
            holder.age_image.setImageResource(R.drawable.gril);
        }
        ImageServerApi.showURLBigImage(holder.mCardImageView, mCardList.get(position).getFace());
        holder.mCardName.setText(mCardList.get(position).getNickname());
        holder.tv_age.setText((mCardList.get(position).getBirthday() == null) ? "0" : mCardList.get(position).getBirthday());
        holder.mCardYear.setText((mCardList.get(position).getProfession() == null) ? "" : mCardList.get(position).getProfession());
        holder.tv_talk.setText(mCardList.get(position).getSignature());
        return convertView;
    }

    class ViewHolder {
        TextView mCardTitle;
        TextView mCardName;
        TextView mCardYear;
        TextView tv_age;
        ImageView mCardImageView;
        ImageView age_image;
        TextView tv_talk;
    }
}
