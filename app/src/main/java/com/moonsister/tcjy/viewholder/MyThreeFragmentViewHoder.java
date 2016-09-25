package com.moonsister.tcjy.viewholder;

import android.view.View;
import android.widget.ImageView;

import com.moonsister.tcjy.ImageServerApi;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseRecyclerViewHolder;
import com.moonsister.tcjy.bean.MyThreeFragmentBean;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.StringUtis;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by jb on 2016/9/22.
 */
public class MyThreeFragmentViewHoder extends BaseRecyclerViewHolder<MyThreeFragmentBean.DataBean> {
    @Bind(R.id.iv_show_bg)
    ImageView mIvShowBg;
    @Bind(R.id.iv_show)
    ImageView mIvShow;

    public MyThreeFragmentViewHoder(View view) {
        super(view);
    }

    @Override
    public void onBindData(MyThreeFragmentBean.DataBean bean) {
        ImageServerApi.showURLImage(mIvShowBg, bean.getContents().getS());
    }

    @Override
    protected void onItemclick(View view, MyThreeFragmentBean.DataBean bean, int position) {
        String l = bean.getContents().getL();
        if (!StringUtis.isEmpty(l)) {
            ArrayList<String> strings = new ArrayList<>();
            strings.add(l);
            ActivityUtils.startImagePagerActivity(strings, 0);
        }
    }
}
