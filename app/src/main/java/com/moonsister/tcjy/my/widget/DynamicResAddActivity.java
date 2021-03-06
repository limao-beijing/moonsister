package com.moonsister.tcjy.my.widget;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseFragmentActivity;
import com.moonsister.tcjy.center.widget.DynamicContentFragment;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.main.widget.PersonThreeFragment;
import com.moonsister.tcjy.my.persenter.DynamicResAddPersenter;
import com.moonsister.tcjy.my.persenter.DynamicResAddPersenterImpl;
import com.moonsister.tcjy.utils.StringUtis;
import com.moonsister.tcjy.utils.UIUtils;

import java.util.List;

/**
 * Created by jb on 2016/9/30.
 */
public class DynamicResAddActivity extends BaseFragmentActivity implements DynamicResAddView {
    private DynamicContentFragment fragment;
    private DynamicResAddPersenter persenter;

    @Override
    protected Fragment initFragment() {
        persenter = new DynamicResAddPersenterImpl();
        persenter.attachView(this);
        fragment = new DynamicContentFragment();

        return fragment;
    }

    @Override
    protected void initData() {
        UIUtils.sendDelayedOneMillis(new Runnable() {
            @Override
            public void run() {
                String type = getIntent().getStringExtra("type");
                if (StringUtis.equals(type, PersonThreeFragment.TYPE_PIC)) {
                    fragment.doClick("1");
                } else if (StringUtis.equals(type, PersonThreeFragment.TYPE_VIDEO)) {
                    fragment.doClick("2");
                } else if (StringUtis.equals(type, PersonThreeFragment.TYPE_VOICE)) {
                    fragment.doClick("3");
                }
            }
        });


    }


    @Override
    protected String initTitleName() {
        TextView tv_title_right = (TextView) titleView.findViewById(R.id.tv_title_right);
        tv_title_right.setText(getString(R.string.finish));

        tv_title_right.setVisibility(View.VISIBLE);
        tv_title_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> contents = fragment.getDynamicContent();
                if (contents == null || contents.size() == 0) {
                    showToast(getString(R.string.not_empty));
                    return;
                }
                persenter.submit(fragment.getDynamicType(), contents);
            }
        });
        return getString(R.string.add_res);
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
    public void finishPage() {
        RxBus.getInstance().send(Events.EventEnum.DynamicResAddActivity_up_success, null);
        finish();
    }
}
