package com.moonsister.tcjy.main.widget;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.adapter.HomePageFragmentAdapter;
import com.moonsister.tcjy.base.BaseFragment;
import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.BaseBean;
import com.moonsister.tcjy.bean.DynamicItemBean;
import com.moonsister.tcjy.bean.UserInfoDetailBean;
import com.moonsister.tcjy.main.model.UserActionModelImpl;
import com.moonsister.tcjy.main.presenter.HomePageFragmentPresenter;
import com.moonsister.tcjy.main.presenter.HomePageFragmentPresenterImpl;
import com.moonsister.tcjy.main.view.HomePageFragmentView;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.EnumConstant;
import com.moonsister.tcjy.utils.StringUtis;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tcjy.viewholder.HomePageHeadHolder;
import com.moonsister.tcjy.widget.XListView;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by jb on 2016/9/1.
 */
public class HomePageFragment extends BaseFragment implements HomePageFragmentView {
    @Bind(R.id.xlv)
    XListView xlv;

    @Bind(R.id.tv_wacth)
    TextView tv_wacth;
    private HomePageFragmentPresenter presenter;
    private HomePageFragmentAdapter adapter;
    private String userId;
    private boolean isRefresh;
    private HomePageHeadHolder headHolder;
    private EnumConstant.SearchType type = EnumConstant.SearchType.all;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        userId = getActivity().getIntent().getStringExtra("id");
        presenter = new HomePageFragmentPresenterImpl();
        presenter.attachView(this);
        return inflater.inflate(R.layout.fragment_home_page, container, false);
    }

    public static Fragment newInstance() {
        return new HomePageFragment();
    }

    @Override
    protected void initData() {
        headHolder = new HomePageHeadHolder();
        headHolder.setOnClickListener(new HomePageHeadHolder.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (presenter == null)
                    return;
                switch (view.getId()) {
                    case R.id.rl_all:
                        type = EnumConstant.SearchType.all;

                        break;
                    case R.id.rl_user:
                        type = EnumConstant.SearchType.user;
                        break;
                    case R.id.rl_dynamic:
                        type = EnumConstant.SearchType.dynamic;
                        break;
                    case R.id.iv_back:
                        getActivity().finish();
                        break;
                }
                isRefresh = true;
                presenter.loadRefresh(userId, type);
            }
        });
        xlv.setVerticalLinearLayoutManager();
        xlv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                isRefresh = true;
                presenter.loadHeader(userId);
                presenter.loadRefresh(userId, type);
            }

            @Override
            public void onLoadMore() {
                isRefresh = false;
                presenter.loadMore(userId, type);
            }
        });
        xlv.addHeaderView(headHolder.getContentView());
        xlv.setRefreshing(true);
    }

    @Override
    public void setDynamicData(List<DynamicItemBean> list) {
        if (adapter == null) {
            adapter = new HomePageFragmentAdapter(list);
            xlv.setAdapter(adapter);
        } else {
            if (isRefresh)
                adapter.clean();
            adapter.addListData(list);
            adapter.onRefresh();
        }
        if (xlv != null) {
            xlv.refreshComplete();
            xlv.loadMoreComplete();
        }
    }

    private UserInfoDetailBean userInfo;

    @Override
    public void setHeaderData(UserInfoDetailBean bean) {
        this.userInfo = bean;
        if (headHolder != null) {
            headHolder.refreshView(bean);
        }
        if (userInfo == null || userInfo.getData() == null)
            return;
        String follow = userInfo.getData().getFollow();
        boolean equals = StringUtis.equals(follow, "1");
        Drawable drawable = UIUtils.getResources().getDrawable(equals ? R.mipmap.home_page_not_wacth : R.mipmap.home_page_wacth);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        tv_wacth.setCompoundDrawables(drawable, null, null, null);
        tv_wacth.setText(UIUtils.getStringRes(equals ? R.string.wacth : R.string.not_wacth));
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


    @OnClick({R.id.rl_reward, R.id.rl_im, R.id.rl_wacth, R.id.rl_pay, R.id.rl_flower})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_reward:

                break;
            case R.id.rl_im:
                if (userInfo != null && userInfo.getData() != null)
                    ActivityUtils.startAppConversationActivity(userId, userInfo.getData().getBaseinfo().getNickname(), userInfo.getData().getBaseinfo().getFace());
                break;
            case R.id.rl_wacth:
                wacthUser();
                break;
            case R.id.rl_pay:
                ActivityUtils.startRedpacketActivity(userId, RedpacketAcitivity.RedpacketType.TYPE_REDPACKET, userInfo.getData().getBaseinfo().getFace());
                break;
            case R.id.rl_flower:
                ActivityUtils.startRedpacketActivity(userId, RedpacketAcitivity.RedpacketType.TYPE_FLOWER, userInfo.getData().getBaseinfo().getFace());
                break;
        }
    }

    private void wacthUser() {
        if (StringUtis.isEmpty(userId) || userInfo == null || userInfo.getData() == null)
            return;
        UserActionModelImpl model = new UserActionModelImpl();
        UserInfoDetailBean.UserInfoDetailDataBean data = userInfo.getData();
        String follow = StringUtis.equals(data.getFollow(), "1") ? "2" : "1";
        showLoading();
        model.wacthAction(userId, follow, new BaseIModel.onLoadDateSingleListener<BaseBean>() {
            @Override
            public void onSuccess(BaseBean bean, BaseIModel.DataType dataType) {
                boolean equals = StringUtis.equals(follow, "1");
                if (bean != null && StringUtis.equals(bean.getCode(), "1")) {
                    Drawable drawable = UIUtils.getResources().getDrawable(equals ? R.mipmap.home_page_not_wacth : R.mipmap.home_page_wacth);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tv_wacth.setCompoundDrawables(drawable, null, null, null);
                    tv_wacth.setText(UIUtils.getStringRes(equals ? R.string.wacth : R.string.not_wacth));
                    showToast(bean.getMsg());
                    data.setFollow(follow);
                }
                hideLoading();
            }

            @Override
            public void onFailure(String msg) {
                showToast(msg);
                hideLoading();
            }
        });

    }
}

