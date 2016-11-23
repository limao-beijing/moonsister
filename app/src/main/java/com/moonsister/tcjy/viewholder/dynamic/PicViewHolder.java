package com.moonsister.tcjy.viewholder.dynamic;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hickey.network.ImageServerApi;
import com.hickey.network.bean.DefaultDataBean;
import com.hickey.network.bean.DynamicItemBean;
import com.hickey.tool.lang.StringUtis;
import com.hickey.tool.time.TimeUtils;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.adapter.DynamicAdapter;
import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.base.BaseRecyclerViewHolder;
import com.moonsister.tcjy.main.model.UserActionModelImpl;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.UIUtils;
import com.hickey.tool.widget.NoScrollGridView;
import com.moonsister.tcjy.widget.RoundedImageView;

import butterknife.Bind;

/**
 * Created by jb on 2016/8/11.
 */
public class PicViewHolder extends BaseRecyclerViewHolder<DynamicItemBean> {
    @Bind(R.id.riv_user_image)
    RoundedImageView rivUserImage;
    @Bind(R.id.tv_user_name)
    TextView tvName;
    @Bind(R.id.tv_content)
    TextView tvContent;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.tv_wacth_number)
    TextView tv_wacth_number;
    @Bind(R.id.tv_reply_number)
    TextView tv_reply_number;
    @Bind(R.id.tv_play_number)
    TextView tv_play_number;
    @Bind(R.id.tv_add_v)
    ImageView tv_add_v;
    @Bind(R.id.tv_more__number)
    ImageView tv_more__number;
    @Bind(R.id.tv_show_redpacket)
    TextView tv_show_redpacket;
    @Bind(R.id.gv_user_pic)
    NoScrollGridView gv_user_pic;
    private boolean isAction = false;

    public PicViewHolder(View view) {
        super(view);
    }

    @Override
    public void onBindData(DynamicItemBean bean) {
        if (bean == null)
            return;
        dynamicAction(bean);
        if (StringUtis.equals(bean.getIsauth(), "1"))
            tv_add_v.setVisibility(View.VISIBLE);
        else
            tv_add_v.setVisibility(View.GONE);
        tvContent.setText(bean.getTitle());
//        tvTime.setText(TimeUtils.format(bean.getCreate_time() * 1000));
        if (StringUtis.equals(bean.getIstop(),"1")) {
            tvTime.setText(UIUtils.getStringRes(R.string.up_dynamic));
            tvTime.setTextColor(UIUtils.getResources().getColor(R.color.home_navigation_text_red));
        } else {
            tvTime.setText(TimeUtils.getDynamicTimeString(bean.getCreate_time()));
            tvTime.setTextColor(UIUtils.getResources().getColor(R.color.gray_color));
        }
        tv_reply_number.setText(bean.getLcomn());
        tv_wacth_number.setText(bean.getLupn());
        tvName.setText(bean.getNickname());
        ImageServerApi.showURLImage(rivUserImage, bean.getFace());

        if (bean.getType() == DynamicAdapter.TYPE_CHARGE_PIC) {
            if (tv_show_redpacket != null) {
                String format = String.format(UIUtils.getStringRes(R.string.show_wacth_redpacket), bean.getLredn() == null ? 0 : bean.getLredn(), bean.getTmoney() == null ? 0 : bean.getTmoney());
                tv_show_redpacket.setText(format);
                tv_show_redpacket.setVisibility(View.VISIBLE);
            }
            if (tv_play_number != null) {
                tv_play_number.setText(bean.getLredn());
                tv_play_number.setVisibility(View.VISIBLE);
            }
        } else {
            if (tv_show_redpacket != null) {
                tv_show_redpacket.setVisibility(View.GONE);
            }
            if (tv_play_number != null) {
                tv_play_number.setVisibility(View.GONE);
            }
        }

        gv_user_pic.setAdapter(new PicGridViewAdapter(bean));

        gv_user_pic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ActivityUtils.startDynamicDatailsActivity(bean.getLatest_id(),bean.getType());
            }
        });
        gv_user_pic.setOnTouchInvalidPositionListener(new NoScrollGridView.OnTouchInvalidPositionListener() {
            @Override
            public boolean onTouchInvalidPosition(int motionEvent) {
                return false; //不终止路由事件让父级控件处理事件
            }
        });
    }

    @Override
    protected void onItemclick(View view,DynamicItemBean bean, int position) {
        ActivityUtils.startDynamicDatailsActivity(bean.getLatest_id(),bean.getType());
    }

    /**
     * 处理动态行为
     *
     * @param bean
     */
    private void dynamicAction(DynamicItemBean bean) {
        tv_wacth_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAction) {
                    if (baseIView != null)
                        baseIView.transfePageMsg(UIUtils.getStringRes(R.string.already) + UIUtils.getStringRes(R.string.action));
                    return;
                }
                isAction = true;
                if (baseIView != null)
                    baseIView.showLoading();
                UserActionModelImpl userActionModel = new UserActionModelImpl();
                userActionModel.likeAction(bean.getLatest_id(), "1", new BaseIModel.onLoadDateSingleListener<DefaultDataBean>() {
                    @Override
                    public void onSuccess(DefaultDataBean b, BaseIModel.DataType dataType) {
                        if (baseIView != null) {
                            baseIView.hideLoading();
                            baseIView.transfePageMsg(b.getMsg());
                        }
                        String s = tv_wacth_number.getText().toString();
                        int i = StringUtis.string2Int(s) + 1;
                        bean.setLupn(i + "");
                        tv_wacth_number.setText(bean.getLupn());
                    }

                    @Override
                    public void onFailure(String msg) {
                        if (baseIView != null) {
                            baseIView.hideLoading();
                            baseIView.transfePageMsg(msg);
                        }
                    }
                });
            }
        });
        tv_more__number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.startDynamicAtionActivity(bean.getUid(), bean.getLatest_id(), bean.getType(), bean.getIstop());
            }
        });
        rivUserImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.startUserinfoActivity(bean.getUid());
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
            View view = UIUtils.inflateLayout(R.layout.item_dynamic_pics);
            ImageView pic = (ImageView) view.findViewById(R.id.iv_pic);
            ImageServerApi.showURLImage(pic, bean.getSimg().get(position));
            pic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (StringUtis.equals(bean.getIspay(), "2")) {
                        ActivityUtils.startPayDynamicRedPackketActivity(bean.getMoney(), bean.getLatest_id());
                    } else {
                        ActivityUtils.startImagePagerActivity(bean.getImg(), position);
                    }
                }
            });
            return view;
        }
    }
}
