package com.moonsister.tcjy.engagement;


import android.support.v4.app.Fragment;

import com.moonsister.tcjy.adapter.EengegamentRecommendFragmentAdpter;
import com.moonsister.tcjy.base.BaseListFragment;
import com.moonsister.tcjy.bean.BaseDataBean;

/**
 * Created by jb on 2016/9/22.
 */
public class EengegamentRecommendFragment extends BaseListFragment<EengegamentRecommendFragmentAdpter, BaseDataBean> {


    @Override
    protected void initChildData() {

    }

    @Override
    public EengegamentRecommendFragmentAdpter setAdapter() {
        return new EengegamentRecommendFragmentAdpter(null);
    }

    @Override
    protected void onLoadMore() {

    }

    @Override
    protected void onRefresh() {
//        ArrayList<EengegamentRecommendBean> been = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            been.add(new EengegamentRecommendBean());
//        }
//        addData(been);
    }

    public static Fragment newInstance() {
        return new EengegamentRecommendFragment();
    }
}
