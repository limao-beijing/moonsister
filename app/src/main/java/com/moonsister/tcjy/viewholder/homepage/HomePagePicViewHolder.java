package com.moonsister.tcjy.viewholder.homepage;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.moonsister.tcjy.ImageServerApi;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseRecyclerViewHolder;
import com.moonsister.tcjy.bean.DynamicItemBean;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.StringUtis;
import com.moonsister.tcjy.widget.NoScrollGridView;
import com.moonsister.tcjy.widget.RoundedImageView;

import butterknife.Bind;

/**
 * Created by jb on 2016/9/1.
 */
public class HomePagePicViewHolder extends BaseRecyclerViewHolder<DynamicItemBean> {
    @Bind(R.id.riv_user_image)
    RoundedImageView rivUserImage;
    @Bind(R.id.tv_user_name)
    TextView tvUserName;
    @Bind(R.id.iv_sex_icon)
    ImageView ivSexIcon;
    @Bind(R.id.tv_sex)
    TextView tvSex;
    @Bind(R.id.tv_user_tag)
    TextView tvUserTag;
    @Bind(R.id.dynamic_content)
    TextView dynamicContent;
    @Bind(R.id.no_gv)
    NoScrollGridView noGv;
    @Bind(R.id.tv_home_page_comment)
    TextView tvHomePageComment;
    @Bind(R.id.tv_home_page_pay)
    TextView tvHomePagePay;
    @Bind(R.id.tv_home_page_control)
    TextView tvHomePageControl;
    @Bind(R.id.tv_user_like)
    TextView tvUserLike;

    public HomePagePicViewHolder(View view) {
        super(view);
    }

    @Override
    public void onBindData(DynamicItemBean dynamicItemBean) {
        if (dynamicItemBean == null)
            return;
        ImageServerApi.showURLSamllImage(rivUserImage, dynamicItemBean.getFace());
        tvUserName.setText(dynamicItemBean.getNickname());
        String sex = dynamicItemBean.getSex();
        if (StringUtis.equals(sex, "1")) {
            ivSexIcon.setImageResource(R.mipmap.boy);
        } else
            ivSexIcon.setImageResource(R.mipmap.gril);
        tvSex.setText(dynamicItemBean.getAge());
        String tags = dynamicItemBean.getTags();
        if (!StringUtis.isEmpty(tags)) {
            tags = tags.replace("|||", "  ");
        }
        tvUserTag.setText(tags);
        dynamicContent.setText(dynamicItemBean.getTitle());
        tvHomePageComment.setText(dynamicItemBean.getLcomn());
        tvHomePagePay.setText(dynamicItemBean.getMoney());
        tvUserLike.setText(dynamicItemBean.getLupn());
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

    @Override
    protected void onItemclick(View view, DynamicItemBean dynamicItemBean, int position) {
        ActivityUtils.startDynamicDatailsActivity(dynamicItemBean.getLatest_id(), dynamicItemBean.getType());
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
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(width, heigjt);
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
