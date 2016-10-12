package com.moonsister.tcjy.main.widget;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.center.widget.DynamicContentFragment;
import com.moonsister.tcjy.main.presenter.RenZhengThreePresenter;
import com.moonsister.tcjy.main.presenter.RenZhengThreePresenterImpl;
import com.moonsister.tcjy.main.view.RenZhengThreeView;
import com.moonsister.tcjy.utils.FragmentUtils;
import com.moonsister.tcjy.utils.UIUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by jb on 2016/10/10.
 */

public class RenZhengThreeActivity extends BaseActivity implements RenZhengThreeView {
    @Bind(R.id.tv_submit)
    TextView mTvSubmit;
    private DynamicContentFragment contentFragment;
    @Bind(R.id.fl_content)
    FrameLayout mFlContent;
    @Bind(R.id.rl)
    RelativeLayout rl;
    private RenZhengThreePresenter presenter;

    @Override
    protected View setRootContentView() {
        presenter = new RenZhengThreePresenterImpl();
        presenter.attachView(this);
        return UIUtils.inflateLayout(R.layout.activity_renzheng_three);
    }

    @Override
    protected String initTitleName() {
        return "资料认证页面";
    }

    @Override
    protected void initView() {


        contentFragment = new DynamicContentFragment();
        FragmentUtils.swichReplaceFramgent(getSupportFragmentManager(), R.id.fl_content, contentFragment);

    }



    @OnClick({R.id.rl, R.id.tv_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl:
                contentFragment.onClick(contentFragment.ivAddContent);
                break;
            case R.id.tv_submit:
                if (contentFragment != null) {
                    presenter.submit(contentFragment.getDynamicContent(), contentFragment.getDynamicType());
                }
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (contentFragment != null) {
            List<String> contents = contentFragment.getDynamicContent();
            if (contents != null && contents.size() != 0) {
                mFlContent.setVisibility(View.VISIBLE);
                rl.setVisibility(View.GONE);
                mTvSubmit.setBackground(getResources().getDrawable(R.drawable.shape_background_red));
                mTvSubmit.setClickable(true);
                mTvSubmit.setFocusable(true);
            } else {
                mFlContent.setVisibility(View.GONE);
                rl.setVisibility(View.VISIBLE);
                mTvSubmit.setBackground(getResources().getDrawable(R.drawable.shape_background_gray));
                mTvSubmit.setClickable(false);
                mTvSubmit.setFocusable(false);
            }
        }
    }

    @Override
    public void showLoading() {
        showProgressDialog();
    }

    @Override
    public void hideLoading() {
        hideProgressDialog();
    }

    @Override
    public void transfePageMsg(String msg) {
        showToast(msg);
    }

    @Override
    public void pageFinish() {
        finish();
    }
}
