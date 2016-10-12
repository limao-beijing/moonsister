package com.moonsister.tcjy.my.widget;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.login.widget.RegActivity;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.DataCleanUtils;
import com.moonsister.tcjy.utils.PackageUtils;
import com.moonsister.tcjy.utils.UIUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by jb on 2016/6/27.
 */
public class SettingActivity extends BaseActivity {
    @Bind(R.id.timeout)
    TextView timeout;
    @Bind(R.id.tv_cache_size)
    TextView tv_cache_size;
    @Bind(R.id.layout_delete_cache)
    RelativeLayout layout_delete_cache;
    @Bind(R.id.tv_versonName)
    TextView tvVersonName;
    @Bind(R.id.tv_verson_status)
    TextView tvVersonStatus;


    @Override
    protected View setRootContentView() {
        return UIUtils.inflateLayout(R.layout.activity_other_setting);
    }

    @Override
    protected void initView() {
        getCache();
        tvVersonName.setText(UIUtils.getStringRes(R.string.show_verson_name) + PackageUtils.getVersionName(getApplicationContext()));
    }

    @Override
    protected String initTitleName() {
        return UIUtils.getStringRes(R.string.setting);
    }

    @OnClick({R.id.tv_bind_phone, R.id.tv_changepwd, R.id.timeout, R.id.layout_delete_cache, R.id.layout_verson_name})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.timeout:
//                String smobile = UserInfoManager.getInstance().getMemoryPersonInfoDetail().getSmobile();
//                if (StringUtis.isEmpty(smobile)) {
//                    AlearDialog dialog = new AlearDialog(AlearDialog.DialogType.app_logout, this);
//                    dialog.setListenter(new AlearDialog.onClickListenter() {
//                        @Override
//                        public void clickType(AlearDialog.clickType type) {
//                            if (type == AlearDialog.clickType.confirm)
//                                ActivityUtils.startRegActivity();
//                            dialog.dismiss();
//                        }
//                    });
//                } else {
                RxBus.getInstance().send(Events.EventEnum.LOGIN_CODE_TIMEOUT, null);
                finish();
//                }
                break;
            case R.id.tv_changepwd:
                ActivityUtils.startChangepwdActivity();
                break;
            case R.id.layout_delete_cache:
                DataCleanUtils.clearAllCache(getApplicationContext());
                getCache();
                break;
            case R.id.tv_bind_phone:
                Intent intent = new Intent(this, RegActivity.class);
                startActivity(intent);
                break;
        }


    }

    private void getCache() {
        String totalCacheSize;
        try {
            totalCacheSize = DataCleanUtils.getTotalCacheSize(getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
            totalCacheSize = "";
        }
        tv_cache_size.setText(totalCacheSize);
    }


}
