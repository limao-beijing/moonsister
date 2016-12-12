package com.moonsister.tcjy.viewholder;


import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baiiu.filter.DropDownMenu;
import com.baiiu.filter.adapter.DropMenuAdapter;
import com.baiiu.filter.interfaces.OnFilterDoneListener;
import com.hickey.network.bean.BannerBean;
import com.hickey.network.bean.HomeParams;
import com.hickey.tool.lang.StringUtis;
import com.hickey.tool.widget.UIUtils;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.utils.ActivityUtils;

import butterknife.Bind;
import butterknife.OnClick;

import static com.moonsister.tcjy.R.id.ed_input;


/**
 * Created by jb on 2016/9/22.
 */
public class HomeThreeHeaderViewHolder extends BaseHolder<String> implements OnFilterDoneListener {
    @Bind(R.id.iv_banner_content)
    TextView mIvBannerContent;
    @Bind(R.id.dropDownMenu)
    DropDownMenu dropDownMenu;
    @Bind(R.id.rl_search)
    RelativeLayout rl_search;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.rl_transparent)
    RelativeLayout mRlTransparent;
    @Bind(R.id.iv_search)
    ImageView mIvSearch;
    @Bind(R.id.rl_title)
    RelativeLayout mRlTitle;
    @Bind(R.id.home_three_banner_icon)
    ImageView mHomeThreeBannerIcon;
    @Bind(R.id.tv_search)
    TextView mTvSearch;
    @Bind(ed_input)
    EditText mEdInput;
    @Bind(R.id.rl_search_view)
    RelativeLayout mRlSearchView;
    private Activity mActivity;
    private HomeParams params;
    private onFilterDoneListenter listenter;
    private String pageType = "1";

    @Override
    protected View initView() {
        params = new HomeParams();
        return UIUtils.inflateLayout(R.layout.view_holder_home_three_header);
    }

    public void setPageType(String pageType) {
        this.pageType = pageType;
    }

    public void setTvTitle(String title) {
        if (!StringUtis.isEmpty(title))
            mTvTitle.setText(title);
    }

    public void showBanner(boolean showbanner) {
        mRlTransparent.setVisibility(showbanner ? View.VISIBLE : View.GONE);
    }

    private void initFilterDropDownView() {
        String[] titleList = new String[]{"性别", "年龄", "省份", "更多"};
        dropDownMenu.setMenuAdapter(new DropMenuAdapter(mActivity, titleList, this));
    }

    public void setbanerDate(BannerBean banerDate) {
        mIvBannerContent.setText(banerDate.getData().getData());
        mIvBannerContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.startH5Activity(banerDate.getData().getJump_url());
            }
        });
    }

    public interface onFilterDoneListenter {
        void onFilterDone(HomeParams params);
    }

    public void setFilterDoneListenter(onFilterDoneListenter listenter) {
        this.listenter = listenter;
    }

    public void setSearchViewShow(boolean isShow) {
        if (rl_search != null)
            rl_search.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onFilterDone(int listPosition, String positionTitle, String urlValue, int itemPosition) {
        switch (listPosition) {
            case 0:
                params.setSex((itemPosition) + "");
                dropDownMenu.close();
                if (listenter != null)
                    listenter.onFilterDone(params);
                break;
            case 1:
                params.setAgetype((itemPosition) + "");
                dropDownMenu.close();
                if (listenter != null)
                    listenter.onFilterDone(params);
                break;
            case 2:
                params.setProvince(itemPosition + "");
                dropDownMenu.close();
                if (listenter != null)
                    listenter.onFilterDone(params);
                break;
            case 3:
                switch (positionTitle) {
                    case "身高":
                        params.setHeighttype((itemPosition) + "");
                        break;
                    case "体重":
                        params.setWeighttype((itemPosition) + "");
                        break;
                    case "是否绑定微信":
                        params.setWeixin((itemPosition) + "");
                        break;
                    case "是否绑定手机":
                        params.setMobile((itemPosition) + "");
                        break;
                    case "是否在线":
                        params.setZaixian((itemPosition) + "");
                        break;
                    case "是否接受异地恋":
                        params.setYidi((itemPosition) + "");
                        break;


                }
                break;

        }

    }

    @Override
    public void onActionDone(int action) {
        switch (action) {
            case 1:
                params = new HomeParams();
                initFilterDropDownView();
                break;
            case 2:
                dropDownMenu.close();
                if (listenter != null)
                    listenter.onFilterDone(params);
                break;
        }
    }


    @Override
    public void refreshView(String data) {
        initFilterDropDownView();
    }

    @OnClick({R.id.iv_search, R.id.iv_back, R.id.tv_search})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_search:
//                rl_search.setVisibility(View.VISIBLE);
                ActivityUtils.startHomeSearchActivity(pageType);

                break;
            case R.id.iv_back:
                if (mActivity != null)
                    mActivity.finish();
                break;
            case R.id.tv_search:
                String content = mEdInput.getText().toString();
                if (!StringUtis.isEmpty(content)) {
                    params.setKeys(content);
                    if (listenter != null)
                        listenter.onFilterDone(params);
                }
                break;
        }
    }

    public void setActivity(FragmentActivity activity) {
        mActivity = activity;
    }

}
