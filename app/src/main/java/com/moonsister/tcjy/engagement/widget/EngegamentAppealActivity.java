package com.moonsister.tcjy.engagement.widget;

import android.view.View;
import android.widget.EditText;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.engagement.presenter.EngegamentAppealPresenter;
import com.moonsister.tcjy.engagement.presenter.EngegamentAppealPresenterImpl;
import com.moonsister.tcjy.engagement.view.EngegamentAppealView;
import com.moonsister.tcjy.utils.UIUtils;
import com.hickey.tool.lang.StringUtis;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by jb on 2016/9/29.
 */
public class EngegamentAppealActivity extends BaseActivity implements EngegamentAppealView {
    private EngegamentAppealPresenter presenter;
    @Bind(R.id.et_content)
    EditText mEtContent;
    private String id;

    @Override
    protected View setRootContentView() {
        return UIUtils.inflateLayout(R.layout.activity_engagment_appeal);
    }

    @Override
    protected String initTitleName() {
        return getString(R.string.engagement_appeal);
    }

    @Override
    protected void initView() {
        id = getIntent().getStringExtra("id");
        presenter = new EngegamentAppealPresenterImpl();
        presenter.attachView(this);
    }

    @OnClick(R.id.tv_submit)
    public void onClick() {
        String content = mEtContent.getText().toString();
        if (StringUtis.isEmpty(content)) {
            showToast(getString(R.string.not_empty));
        } else {
            presenter.submitAppeal(id, content);
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
    public void submitSuccess() {
        finish();
    }
}
