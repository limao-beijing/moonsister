package com.moonsister.tcjy.viewholder;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import com.baiiu.filter.DropDownMenu;
import com.baiiu.filter.adapter.DropMenuAdapter;
import com.baiiu.filter.entity.FilterUrl;
import com.baiiu.filter.interfaces.OnFilterDoneListener;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.utils.UIUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by jb on 2016/9/22.
 */
public class HomeThreeHeaderViewHolder extends BaseHolder<String> implements OnFilterDoneListener {
    @Bind(R.id.iv_banner_content)
    TextView mIvBannerContent;
    @Bind(R.id.dropDownMenu)
    DropDownMenu dropDownMenu;
    @Bind(R.id.mFilterContentView)
    TextView mFilterContentView;
    private Activity mActivity;

    @Override
    protected View initView() {

        return UIUtils.inflateLayout(R.layout.view_holder_home_three_header);
    }

    private void initFilterDropDownView() {
        String[] titleList = new String[]{"性别", "年龄", "城市", "更多"};
        dropDownMenu.setMenuAdapter(new DropMenuAdapter(mActivity, titleList, this));
    }

    @Override
    public void onFilterDone(int position, String positionTitle, String urlValue) {
        if (position != 3) {
            dropDownMenu.setPositionIndicatorText(FilterUrl.instance().position, FilterUrl.instance().positionTitle);
        }

        dropDownMenu.close();
        mFilterContentView.setText(FilterUrl.instance().toString());
    }


    @Override
    public void refreshView(String data) {
        initFilterDropDownView();
    }

    @OnClick(R.id.iv_search)
    public void onClick() {

    }

    public void setActivity(FragmentActivity activity) {
        mActivity = activity;
    }
}
