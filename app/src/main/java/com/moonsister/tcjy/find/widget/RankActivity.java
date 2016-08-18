package com.moonsister.tcjy.find.widget;

import android.view.View;
import android.widget.TextView;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.utils.FragmentUtils;
import com.moonsister.tcjy.utils.UIUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by jb on 2016/8/3.
 */
public class RankActivity extends BaseActivity {
    @Bind(R.id.tv_meili)
    TextView tvMeili;
    @Bind(R.id.tv_gongxian)
    TextView tvGongxian;
    private RankFragment currenFragment, charmFragment, contributionFragment;

    @Override
    protected View setRootContentView() {
        return UIUtils.inflateLayout(R.layout.activity_rank);
    }

    @Override
    protected String initTitleName() {
        return UIUtils.getStringRes(R.string.rank);
    }

    @Override
    protected void initView() {
        onClick(tvMeili);
    }

    @OnClick({R.id.tv_meili, R.id.tv_gongxian})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_meili:
                tvMeili.setSelected(true);
                tvGongxian.setSelected(false);
                if (charmFragment == null) {
                    charmFragment = RankFragment.newInstance();
                    charmFragment.setRankType(1);
                }
                swicth(charmFragment);
                break;
            case R.id.tv_gongxian:
                tvMeili.setSelected(false);
                tvGongxian.setSelected(true);
                if (contributionFragment == null) {
                    contributionFragment = RankFragment.newInstance();
                    contributionFragment.setRankType(2);
                }
                swicth(contributionFragment);
                break;
        }

    }

    private void swicth(RankFragment fragment) {
        FragmentUtils.switchHideFragment(getSupportFragmentManager(), R.id.layout_rank_content, currenFragment, fragment);
        currenFragment = fragment;
    }

}
