package com.moonsister.tcjy.home.widget;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.moonsister.tcjy.CacheManager;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tool.lang.StringUtis;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tcjy.widget.FlowLayout;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by jb on 2016/7/10.
 */
public class SearchActivity extends BaseActivity implements TextWatcher {
    @Bind(R.id.et_channel_find)
    EditText etChannelFind;
    @Bind(R.id.btn_search_pager_cancel)
    Button btnSearchPagerCancel;
    @Bind(R.id.tv_del_all)
    TextView tvDelAll;
    @Bind(R.id.fl_erach_cache)
    FlowLayout flErachCache;

    @Override
    protected View setRootContentView() {
        return UIUtils.inflateLayout(R.layout.activity_search);
    }

    @Override
    protected void initView() {

        etChannelFind.addTextChangedListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initHistory();
    }

    /**
     * 初始化历史记录
     */
    private void initHistory() {
        boolean exist4DataCache = CacheManager.isExist4DataCache(this, CacheManager.CachePath.SEARCH_HISTPRY);
        if (!exist4DataCache) {
            return;
        }
        ArrayList<String> arrays = CacheManager.readObject(this, CacheManager.CachePath.SEARCH_HISTPRY);
        flErachCache.removeAllViews();
        if (arrays == null) {
            return;
        }
        for (String s : arrays) {
            TextView tv = (TextView) UIUtils.inflateLayout(
                    R.layout.activity_search_tv, flErachCache);
            tv.setText(s);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ActivityUtils.startSearchReasonActivity(tv.getText().toString());
                }
            });
            flErachCache.addView(tv);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String enter_key = etChannelFind.getText().toString();
        if (enter_key.length() == 0) {
            btnSearchPagerCancel.setText(UIUtils.getStringRes(R.string.cancel));
        } else {
            btnSearchPagerCancel.setText(UIUtils.getStringRes(R.string.search));
        }
    }

    @OnClick({R.id.btn_search_pager_cancel, R.id.tv_del_all})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_search_pager_cancel:
                String s1 = btnSearchPagerCancel.getText().toString();
                if (StringUtis.equals(s1, UIUtils.getStringRes(R.string.cancel))) {
                    finish();
                    return;
                }
                String s = etChannelFind.getText().toString();
                ActivityUtils.startSearchReasonActivity(s);
                break;
            case R.id.tv_del_all:
                CacheManager.saveObject(this, null, CacheManager.CachePath.SEARCH_HISTPRY);
                initHistory();
                break;
        }
    }
}
