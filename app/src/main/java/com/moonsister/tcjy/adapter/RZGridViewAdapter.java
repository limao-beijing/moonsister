package com.moonsister.tcjy.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.hickey.network.ImageServerApi;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.utils.UIUtils;

import java.util.ArrayList;


/**
 * Created by Administrator on 2016/6/26.
 */
public class RZGridViewAdapter extends BaseAdapter {

    private ArrayList<String> ls;

    public RZGridViewAdapter(ArrayList<String> pics) {
        this.ls = pics;
    }

    @Override
    public int getCount() {

        return ls == null ? 0 : ls.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = UIUtils.inflateLayout(R.layout.item_dynamic_pics);
        ImageView pic = (ImageView) view.findViewById(R.id.iv_pic);
        ImageServerApi.showURLSamllImage(pic, ls.get(position));
        return view;
    }
}