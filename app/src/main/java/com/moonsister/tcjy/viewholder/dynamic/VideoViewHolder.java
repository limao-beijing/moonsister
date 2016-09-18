package com.moonsister.tcjy.viewholder.dynamic;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.ImageServerApi;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.adapter.DynamicAdapter;
import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.base.BaseRecyclerViewHolder;
import com.moonsister.tcjy.bean.DefaultDataBean;
import com.moonsister.tcjy.bean.UserInfoListBean;
import com.moonsister.tcjy.main.model.UserActionModelImpl;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.StringUtis;
import com.moonsister.tcjy.utils.TimeUtils;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tcjy.widget.RoundedImageView;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import im.gouyin.com.progressdialog.AlearDialog;

/**
 * Created by jb on 2016/8/11.
 */
public class VideoViewHolder extends BaseRecyclerViewHolder<UserInfoListBean.UserInfoListBeanData.UserInfoListBeanDataList> {
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
    @Bind(R.id.iv_play)
    ImageView iv_play;
    @Bind(R.id.play)
    VideoView play;
    @Bind(R.id.iv_play_background)
    ImageView iv_play_background;
    @Bind(R.id.tv_show_redpacket)
    TextView tv_show_redpacket;
    private boolean isAction = false;

    public VideoViewHolder(View view) {
        super(view);
    }

    @Override
    public void onBindData(UserInfoListBean.UserInfoListBeanData.UserInfoListBeanDataList bean) {
        if (bean == null)
            return;
        ImageServerApi.showURLBigImage(iv_play_background, bean.getVimg());
        tvContent.setText(bean.getTitle());
        tvTime.setText(TimeUtils.getDynamicTimeString(bean.getCreate_time()));
        tv_play_number.setText(bean.getLkpicn() + "");
        dynamicAction(bean);
        tv_reply_number.setText(bean.getLcomn() + "");
        tv_wacth_number.setText(bean.getLupn() + "");
        tvName.setText(bean.getNickname());
        if (StringUtis.equals(bean.getIstop(), "1")) {
            tvTime.setText(UIUtils.getStringRes(R.string.up_dynamic));
            tvTime.setTextColor(UIUtils.getResources().getColor(R.color.home_navigation_text_red));
        } else {
            tvTime.setText(TimeUtils.getDynamicTimeString(bean.getCreate_time()));
            tvTime.setTextColor(UIUtils.getResources().getColor(R.color.gray_color));
        }
        if (bean.getType() == DynamicAdapter.TYPE_CHARGE_VIDEO) {
            if (tv_show_redpacket != null) {
                String format = String.format(UIUtils.getStringRes(R.string.show_wacth_video), bean.getLredn() == null ? 0 : bean.getLredn(), bean.getTmoney() == null ? 0 : bean.getTmoney());
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
        if (StringUtis.equals(bean.getIsauth(), "1"))
            tv_add_v.setVisibility(View.VISIBLE);
        else
            tv_add_v.setVisibility(View.GONE);
        ImageServerApi.showURLImage(rivUserImage, bean.getFace());


        iv_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] array = UIUtils.getResources().getStringArray(R.array.dynamic_channel_1002);
                List<String> strings = Arrays.asList(array);
                if (StringUtis.equals(bean.getIspay(), "2")) {
                    if (strings.contains(AppConstant.CHANNEL_ID)) {
                        alear();
                    } else
                        ActivityUtils.startPayDynamicRedPackketActivity(bean.getMoney(), bean.getLatest_id());
                } else {
                    ActivityUtils.startShowShortVideoActivity(bean.getVideo());
                }
            }
        });
    }

    private void alear() {
        if (mActivity == null)
            return;
        AlearDialog dialog = new AlearDialog(AlearDialog.DialogType.Certification_dynamic, mActivity);
        dialog.setListenter(new AlearDialog.onClickListenter() {
            @Override
            public void clickType(AlearDialog.clickType type) {
                if (type == AlearDialog.clickType.confirm_vip) {
                    ActivityUtils.startBuyVipActivity();
                    dialog.dismiss();
                }

            }
        });

    }

//    private void playVideo(UserInfoListBean.UserInfoListBeanData.UserInfoListBeanDataList bean) {
//
//        if (play != null && bean != null && !StringUtis.isEmpty(bean.getVideo())) {
//            Uri uri = Uri.parse(bean.getVideo());
//            play.setVideoURI(uri);
//            play.setOnPreparedListener(dynamic_new MediaPlayer.OnPreparedListener() {
//                @Override
//                public void onPrepared(MediaPlayer mp) {
//                    iv_play_background.setVisibility(View.GONE);
//                    iv_play.setVisibility(View.GONE);
//                    play.start();
//                }
//            });
//
//            play.setOnCompletionListener(dynamic_new MediaPlayer.OnCompletionListener() {
//                @Override
//                public void onCompletion(MediaPlayer mp) {
//                    mp.stop();
//                    iv_play_background.setVisibility(View.VISIBLE);
//                    iv_play.setVisibility(View.VISIBLE);
//                    play.stopPlayback();
//                }
//            });
//        }
//    }

    @Override
    protected void onItemclick(View view, UserInfoListBean.UserInfoListBeanData.UserInfoListBeanDataList bean, int position) {
        ActivityUtils.startDynamicDatailsActivity(bean);
    }

    /**
     * 处理动态行为
     *
     * @param bean
     */
    private void dynamicAction(UserInfoListBean.UserInfoListBeanData.UserInfoListBeanDataList bean) {
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

}
