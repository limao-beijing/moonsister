package com.moonsister.tcjy.main.widget;

import android.view.View;
import android.widget.ImageView;

import com.hickey.network.bean.RecommendMemberFragmentBean;
import com.hickey.tool.base.BaseActivity;
import com.hickey.tool.widget.UIUtils;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.adapter.CardAdapter;
import com.moonsister.tcjy.manager.RecommendMananger;
import com.moonsister.tcjy.widget.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by jb on 2016/8/15.
 */
public class RecommendMemberActivity extends BaseActivity {
    @Bind(R.id.left)
    ImageView left;
    @Bind(R.id.right)
    ImageView right;
    @Bind(R.id.sfav)
    SwipeFlingAdapterView flingContainer;
    CardAdapter adapter;

    @Override
    protected View setRootContentView() {
        return UIUtils.inflateLayout(R.layout.fragment_recommend_member);
    }

    @Override
    protected void initView() {
        ArrayList<RecommendMemberFragmentBean.DataBean> datas = (ArrayList<RecommendMemberFragmentBean.DataBean>) getIntent().getSerializableExtra("datas");
        setData(datas);
    }

    public void setData(ArrayList<RecommendMemberFragmentBean.DataBean> data) {
        adapter = new CardAdapter(this, data);
        flingContainer.setAdapter(adapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                data.remove(0);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                showToast("不喜欢");
                if (dataObject instanceof RecommendMemberFragmentBean.DataBean) {
                    RecommendMemberFragmentBean.DataBean bean = (RecommendMemberFragmentBean.DataBean) dataObject;
                    RecommendMananger.getInstance().recommendMemberStatus(false, bean.getUid());
                }
//
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                showToast("喜欢");
                if (dataObject instanceof RecommendMemberFragmentBean.DataBean) {
                    RecommendMemberFragmentBean.DataBean bean = (RecommendMemberFragmentBean.DataBean) dataObject;
                    RecommendMananger.getInstance().recommendMemberStatus(true, bean.getUid());
                }
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                finish();
            }

            @Override
            public void onScroll(float scrollProgressPercent) {
                View view = flingContainer.getSelectedView();
//                view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
//                view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
            }
        });

    }

    public void right() {
        flingContainer.getTopCardListener().selectRight();
    }

    public void left() {
        flingContainer.getTopCardListener().selectLeft();//调用动画实现不喜欢的效果。
    }

    @OnClick({R.id.left, R.id.right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.left:
                left();
                break;
            case R.id.right:
                right();
                break;
        }
    }

}
