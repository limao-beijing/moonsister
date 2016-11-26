package com.moonsister.tcjy.my.widget;

import android.view.View;

import com.hickey.tool.base.BaseActivity;
import com.hickey.tool.widget.UIUtils;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.utils.ActivityUtils;

import butterknife.OnClick;

/**约会
 *
 * Created by jb on 2016/6/27.
 */
public class AppointmentActivity extends BaseActivity {
    @Override
    protected View setRootContentView() {
        return UIUtils.inflateLayout(R.layout.activity_my_yu_yue);
    }

    @Override
    protected void initView() {

    }

    @OnClick(R.id.tv_Refund)
    public void onClick() {
        ActivityUtils.startRefundActivity();
    }
}
