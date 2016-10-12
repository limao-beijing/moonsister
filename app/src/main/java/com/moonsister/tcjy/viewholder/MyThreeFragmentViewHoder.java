package com.moonsister.tcjy.viewholder;

import android.view.View;
import android.widget.ImageView;

import com.moonsister.tcjy.ImageServerApi;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseRecyclerViewHolder;
import com.moonsister.tcjy.bean.MyThreeFragmentBean;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.main.widget.PersonThreeFragment;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.my.widget.MyThreeFragment;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.StringUtis;
import com.moonsister.tcjy.widget.speak.VoicePlay;

import java.util.ArrayList;

import butterknife.Bind;

import static com.moonsister.tcjy.R.id.iv_show;

/**
 * Created by jb on 2016/9/22.
 */
public class MyThreeFragmentViewHoder extends BaseRecyclerViewHolder<MyThreeFragmentBean.DataBean> {
    @Bind(R.id.iv_show_bg)
    ImageView mIvShowBg;
    @Bind(iv_show)
    ImageView mIvShow;
    @Bind(R.id.tv_mohu)
    ImageView tv_mohu;

    public MyThreeFragmentViewHoder(View view) {
        super(view);
    }

    @Override
    public void onBindData(MyThreeFragmentBean.DataBean bean) {

    }

    @Override
    public void onBindData(MyThreeFragmentBean.DataBean bean, int position) {
        if (bean.isUpPIC()) {
            mIvShowBg.setBackgroundResource(R.mipmap.add_dynamic_res);
            mIvShowBg.setImageDrawable(null);
            tv_mohu.setVisibility(View.GONE);
            mIvShow.setImageDrawable(null);
            return;
        }


        String uid = UserInfoManager.getInstance().getUid();
        if (!StringUtis.equals(uid, bean.getUid())) {
            if (position != 0) {
                String sex = UserInfoManager.getInstance().getUserSex();
                if (StringUtis.equals(sex, "1")) {
                    int status = UserInfoManager.getInstance().getMemoryPersonInfoDetail().getVipStatus();
                    if (status == 1) {
                        tv_mohu.setVisibility(View.GONE);
                    } else
                        tv_mohu.setVisibility(View.VISIBLE);
                } else {
                    int status = UserInfoManager.getInstance().getMemoryPersonInfoDetail().getAttestation();
                    if (status == 1) {
                        tv_mohu.setVisibility(View.GONE);
                    } else
                        tv_mohu.setVisibility(View.VISIBLE);
                }
            } else
                tv_mohu.setVisibility(View.GONE);
        } else {
            tv_mohu.setVisibility(View.GONE);
        }
        ImageServerApi.showURLImage(mIvShowBg, bean.getContents().getS());
        String type = bean.getSource_type();
        if (type == MyThreeFragment.TYPE_PIC) {
            mIvShow.setImageDrawable(null);
        } else if (type == MyThreeFragment.TYPE_VIDEO) {
            mIvShow.setImageResource(R.mipmap.my_three_video);

        } else if (type == MyThreeFragment.TYPE_VOICE) {
            mIvShow.setImageResource(R.mipmap.my_three_voice);
        }
    }

    @Override
    protected void onItemclick(View view, MyThreeFragmentBean.DataBean bean, int position) {
        if (bean.isUpPIC()) {
            String type = bean.getSource_type();
            if (type == MyThreeFragment.TYPE_PIC) {
                ActivityUtils.startEditDynamicActivity(PersonThreeFragment.TYPE_PIC, bean.getUid(), "", "");
            } else if (type == MyThreeFragment.TYPE_VIDEO) {
                ActivityUtils.startEditDynamicActivity(PersonThreeFragment.TYPE_VIDEO, bean.getUid(), "", "");
            } else if (type == MyThreeFragment.TYPE_VOICE) {
                ActivityUtils.startEditDynamicActivity(PersonThreeFragment.TYPE_VOICE, bean.getUid(), "", "");
            }

            return;
        }


        String uid = UserInfoManager.getInstance().getUid();
        if (!StringUtis.equals(uid, bean.getUid())) {
            if (position != 0) {
                String sex = UserInfoManager.getInstance().getUserSex();
                if (StringUtis.equals(sex, "1")) {
                    int status = UserInfoManager.getInstance().getMemoryPersonInfoDetail().getVipStatus();
                    if (status != 1) {
                        RxBus.getInstance().send(Events.EventEnum.MyThreeFragment_level, null);
                        return;
                    }
                } else {
                    int status = UserInfoManager.getInstance().getMemoryPersonInfoDetail().getAttestation();
                    if (status != 1) {
                        RxBus.getInstance().send(Events.EventEnum.MyThreeFragment_level, null);
                        return;
                    }
                }
            }
        }

        String type = bean.getSource_type();
        if (type == MyThreeFragment.TYPE_PIC) {
            String l = bean.getContents().getL();
            if (!StringUtis.isEmpty(l)) {
                ArrayList<String> strings = new ArrayList<>();
                strings.add(l);
                ActivityUtils.startImagePagerActivity(strings, 0);
            }
        } else if (type == MyThreeFragment.TYPE_VIDEO) {
            ActivityUtils.startShowShortVideoActivity(bean.getContents().getV());

        } else if (type == MyThreeFragment.TYPE_VOICE) {
            new VoicePlay().playVoice(view.getContext(), bean.getContents().getV());
        }

    }

}
