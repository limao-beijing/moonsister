package com.moonsister.tcjy.viewholder;

import android.view.View;
import android.widget.ImageView;

import com.moonsister.tcjy.ImageServerApi;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseRecyclerViewHolder;
import com.moonsister.tcjy.bean.MyThreeFragmentBean;
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

    public MyThreeFragmentViewHoder(View view) {
        super(view);
    }

    @Override
    public void onBindData(MyThreeFragmentBean.DataBean bean) {
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
