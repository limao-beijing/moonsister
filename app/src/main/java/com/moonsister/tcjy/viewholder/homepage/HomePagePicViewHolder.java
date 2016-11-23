package com.moonsister.tcjy.viewholder.homepage;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.hickey.network.ImageServerApi;
import com.hickey.network.bean.DynamicItemBean;
import com.hickey.tool.lang.StringUtis;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.hickey.tool.widget.NoScrollGridView;

import butterknife.Bind;

/**
 * Created by jb on 2016/9/1.
 */
public class HomePagePicViewHolder extends BaseHomePageViewHolder {

    @Bind(R.id.fl_home_page_control)
    FrameLayout fl_home_page_control;

    @Bind(R.id.no_gv)
    NoScrollGridView noGv;

    public HomePagePicViewHolder(View view) {
        super(view);
    }

    @Override
    public void onBindData(DynamicItemBean dynamicItemBean) {
        super.onBindData(dynamicItemBean);
        noGv.setAdapter(new PicGridViewAdapter(dynamicItemBean));
        noGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ActivityUtils.startDynamicDatailsActivity(dynamicItemBean.getLatest_id(), dynamicItemBean.getType());
            }
        });
        noGv.setOnTouchInvalidPositionListener(new NoScrollGridView.OnTouchInvalidPositionListener() {
            @Override
            public boolean onTouchInvalidPosition(int motionEvent) {
                return false; //不终止路由事件让父级控件处理事件
            }
        });


    }


    private static class PicGridViewAdapter extends BaseAdapter {
        private DynamicItemBean bean;

        public PicGridViewAdapter(DynamicItemBean bean) {
            this.bean = bean;
        }

        @Override
        public int getCount() {
            return bean.getSimg() == null ? 0 : bean.getSimg().size();
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
            Context context = parent.getContext();
            ImageView imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            int heigjt = (int) context.getResources().getDimension(R.dimen.x160);
            int width = (int) context.getResources().getDimension(R.dimen.x218);
            AbsListView.LayoutParams params = new AbsListView.LayoutParams(width, heigjt);
            imageView.setLayoutParams(params);
            ImageServerApi.showURLImage(imageView, bean.getSimg().get(position));
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (StringUtis.equals(bean.getIspay(), "2")) {
                        ActivityUtils.startPayDynamicRedPackketActivity(bean.getMoney(), bean.getLatest_id());
                    } else {
                        ActivityUtils.startImagePagerActivity(bean.getImg(), position);
                    }
                }
            });
            return imageView;
        }
    }


}
