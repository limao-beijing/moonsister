package com.moonsister.tcjy.my.widget;

import android.view.View;

import com.hickey.tool.base.BaseActivity;
import com.hickey.tool.widget.UIUtils;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;

import butterknife.OnClick;

/**
 * Created by jb on 2016/6/27.
 */
public class RZThidActivity extends BaseActivity {
    @Override
    protected View setRootContentView() {
        return UIUtils.inflateLayout(R.layout.activity_rzthid);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected String initTitleName() {
        return UIUtils.getStringRes(R.string.apply_renzheng);
    }

    @OnClick(R.id.tv_success)
    public void onClick() {
        RxBus.getInstance().send(Events.EventEnum.CERTIFICATION_PAGE_FINISH, null);
        finish();
    }
}
