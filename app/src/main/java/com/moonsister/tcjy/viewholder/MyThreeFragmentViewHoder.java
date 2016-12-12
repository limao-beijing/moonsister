package com.moonsister.tcjy.viewholder;

import android.view.View;
import android.widget.ImageView;

import com.hickey.network.ImageServerApi;
import com.hickey.network.bean.MyThreeFragmentBean;
import com.hickey.tool.base.BaseRecyclerViewHolder;
import com.hickey.tool.lang.StringUtis;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.my.widget.MyThreeFragment;
import com.moonsister.tcjy.my.widget.PersonThreeFragment;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.widget.speak.VoicePlay;

import java.util.ArrayList;

import butterknife.Bind;

import static com.moonsister.tcjy.event.Events.EventEnum.PersonThreeFragment_delete_pic;


/**
 * Created by jb on 2016/9/22.
 */
public class MyThreeFragmentViewHoder extends BaseRecyclerViewHolder<MyThreeFragmentBean.DataBean> {
    @Bind(R.id.iv_show_bg)
    ImageView mIvShowBg;
    @Bind(R.id.iv_show_icon)
    ImageView mIvShow;
    @Bind(R.id.tv_mohu)
    ImageView tv_mohu;
    @Bind(R.id.iv_delete_pic)
    ImageView iv_delete_pic;

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
        if (StringUtis.equals(type, MyThreeFragment.TYPE_PIC)) {
            mIvShow.setImageBitmap(null);
        } else if (StringUtis.equals(type, MyThreeFragment.TYPE_VIDEO)) {
            mIvShow.setImageResource(R.mipmap.my_three_video);

        } else if (StringUtis.equals(type, MyThreeFragment.TYPE_VOICE)) {
            mIvShow.setImageResource(R.mipmap.my_three_voice);
        }
        if (bean.isDelect() && StringUtis.equals(uid, bean.getUid())) {
            iv_delete_pic.setVisibility(View.VISIBLE);
            iv_delete_pic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Events<String> events = new Events<>();
                    events.message = bean.getId();
                    events.what = PersonThreeFragment_delete_pic;
                    RxBus.getInstance().send(events);
                }
            });
        } else
            iv_delete_pic.setVisibility(View.GONE);
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
                ActivityUtils.startImagePagerActivity(bean.getId(), strings, 0);
            }
        } else if (type == MyThreeFragment.TYPE_VIDEO) {
            ActivityUtils.startShowShortVideoActivity(bean.getContents().getV());

        } else if (type == MyThreeFragment.TYPE_VOICE) {
            new VoicePlay().playVoice(view.getContext(), bean.getContents().getV());
        }

    }

}
