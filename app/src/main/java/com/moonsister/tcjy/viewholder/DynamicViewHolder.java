package com.moonsister.tcjy.viewholder;

import android.media.MediaPlayer;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

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
import com.moonsister.tcjy.widget.NoScrollGridView;
import com.moonsister.tcjy.widget.RoundedImageView;

import butterknife.Bind;


/**
 * Created by pc on 2016/6/4.
 */
public class DynamicViewHolder extends BaseRecyclerViewHolder<DynamicItemBean> {


    @Bind(R.id.riv_user_image)
    RoundedImageView rivUserImage;
    @Bind(R.id.tv_user_name)
    TextView tvStr;
    @Bind(R.id.layout_content)
    FrameLayout mfragment;
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

    ImageView iv_play;
    NoScrollGridView gvUserPic;
    private int viewType;
    private TextView tvPlay;
    private ImageView playBackground;
    private VideoView play;
    private TextView tv_show_redpacket;
    private boolean isAction = false;

    public DynamicViewHolder(View view, int viewType) {
        super(view);
        this.viewType = viewType;
        initFragment();
    }

    private void initFragment() {
        switch (viewType) {
            case DynamicAdapter.TYPE_FREE_PIC:
            case DynamicAdapter.TYPE_CHARGE_PIC:
                View view = UIUtils.inflateLayout(R.layout.item_user_pic, mfragment);
                mfragment.removeAllViews();
                mfragment.addView(view);
                gvUserPic = (NoScrollGridView) view.findViewById(R.id.gv_user_pic);
                tv_show_redpacket = (TextView) view.findViewById(R.id.tv_show_redpacket);
                break;
            case DynamicAdapter.TYPE_FREE_VIDEO:
            case DynamicAdapter.TYPE_CHARGE_VIDEO:
                mfragment.removeAllViews();
                View palyView = UIUtils.inflateLayout(R.layout.item_video);
                iv_play = (ImageView) palyView.findViewById(R.id.iv_play);
                tvPlay = (TextView) palyView.findViewById(R.id.tv_play_content);
                playBackground = (ImageView) palyView.findViewById(R.id.iv_play_background);
                play = (VideoView) palyView.findViewById(R.id.play);
                mfragment.addView(palyView);
                break;
        }
    }

    @Override
    public void onBindData(DynamicItemBean bean) {
        tvStr.setText(bean.getTitle());
        switch (bean.getType()) {
            case DynamicAdapter.TYPE_CHARGE_PIC:
                setPICData(bean);
                break;
            case DynamicAdapter.TYPE_FREE_PIC:
                setPICData(bean);
                break;
            case DynamicAdapter.TYPE_FREE_VIDEO:
                setTVData(bean);
                break;

        }
    }

    /**
     * 视频信息
     *
     * @param bean
     */
    private void setTVData(DynamicItemBean bean) {
        if (bean == null)
            return;
        ImageServerApi.showURLImage(playBackground, bean.getVimg());
        tvContent.setText(bean.getTitle());
        tvTime.setText(TimeUtils.getDynamicTimeString(bean.getCreate_time()));
        tv_play_number.setText(bean.getLkpicn() + "");
        dynamicAction(bean);
        tv_reply_number.setText(bean.getLcomn() + "");
        tv_wacth_number.setText(bean.getLupn() + "");
        tvStr.setText(bean.getNickname());
        if (StringUtis.equals(bean.getIsauth(), "1"))
            tv_add_v.setVisibility(View.VISIBLE);
        else
            tv_add_v.setVisibility(View.GONE);
        ImageServerApi.showURLImage(rivUserImage, bean.getFace());
        playBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (play != null && bean != null && !StringUtis.isEmpty(bean.getVideo())) {
                    Uri uri = Uri.parse(bean.getVideo());
                    play.setVideoURI(uri);
                    play.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            playBackground.setVisibility(View.GONE);
                            iv_play.setVisibility(View.GONE);
                            play.start();
                        }
                    });

                    play.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.stop();
                            playBackground.setVisibility(View.VISIBLE);
                            iv_play.setVisibility(View.VISIBLE);
                            play.stopPlayback();
                        }
                    });
                }
            }
        });
    }

    /**
     * 图片信息
     *
     * @param bean
     */
    private void setPICData(DynamicItemBean bean) {
        if (bean == null)
            return;
        dynamicAction(bean);
        if (StringUtis.equals(bean.getIsauth(), "1"))
            tv_add_v.setVisibility(View.VISIBLE);
        else
            tv_add_v.setVisibility(View.GONE);
        tvContent.setText(bean.getTitle());
//        tvTime.setText(TimeUtils.format(bean.getCreate_time() * 1000));
        if (StringUtis.equals(bean.getIstop(), "1")) {
            tvTime.setText(UIUtils.getStringRes(R.string.up_dynamic));
            tvTime.setTextColor(UIUtils.getResources().getColor(R.color.home_navigation_text_red));
        } else {
            tvTime.setText(TimeUtils.getDynamicTimeString(bean.getCreate_time()));
            tvTime.setTextColor(UIUtils.getResources().getColor(R.color.gray_color));
        }
        tv_reply_number.setText(bean.getLcomn());
        tv_wacth_number.setText(bean.getLupn());
        tvStr.setText(bean.getNickname());
        ImageServerApi.showURLImage(rivUserImage, bean.getFace());

        if (bean.getType() == DynamicAdapter.TYPE_CHARGE_PIC) {
            if (tv_show_redpacket != null) {
                String format = String.format(UIUtils.getStringRes(R.string.show_wacth_redpacket), bean.getLredn(), bean.getTmoney() == null ? 0 : bean.getTmoney());
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

        gvUserPic.setAdapter(new PicGridView(bean));

        gvUserPic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ActivityUtils.startDynamicDatailsActivity(bean.getLatest_id(),bean.getType());
            }
        });
        gvUserPic.setOnTouchInvalidPositionListener(new NoScrollGridView.OnTouchInvalidPositionListener() {
            @Override
            public boolean onTouchInvalidPosition(int motionEvent) {
                return false; //不终止路由事件让父级控件处理事件
            }
        });
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

    @Override
    protected void onItemclick(View view, DynamicItemBean bean, int position) {
        ActivityUtils.startDynamicDatailsActivity(bean.getLatest_id(),bean.getType());
    }


    private static class PicGridView extends BaseAdapter {
        private DynamicItemBean bean;

        public PicGridView(DynamicItemBean bean) {
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
