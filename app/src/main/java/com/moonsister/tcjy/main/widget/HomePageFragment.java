package com.moonsister.tcjy.main.widget;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hickey.network.bean.BaseBean;
import com.hickey.network.bean.DynamicItemBean;
import com.hickey.network.bean.PayRedPacketPicsBean;
import com.hickey.network.bean.UserInfoDetailBean;
import com.hickey.tool.base.BaseFragment;
import com.hickey.tool.base.BaseIModel;
import com.hickey.tool.constant.EnumConstant;
import com.hickey.tool.lang.StringUtis;
import com.hickey.tool.widget.UIUtils;
import com.hickey.tool.widget.XListView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.adapter.HomePageFragmentAdapter;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.main.model.UserActionModelImpl;
import com.moonsister.tcjy.main.presenter.HomePageFragmentPresenter;
import com.moonsister.tcjy.main.presenter.HomePageFragmentPresenterImpl;
import com.moonsister.tcjy.main.view.HomePageFragmentView;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.LogUtils;
import com.moonsister.tcjy.viewholder.HomePageHeadHolder;
import com.trello.rxlifecycle.FragmentEvent;

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
    @Bind(R.id.layout_home_content)
    View layout_home_content;

    @Bind(R.id.rl_pay)
    View rl_pay;
    @Bind(R.id.rl_flower)
    View rl_flower;
    @Bind(R.id.rl_engagement)
    RelativeLayout rl_engagement;
    private HomePageFragmentPresenter presenter;
    private HomePageFragmentAdapter adapter;
    private String userId;
    private boolean isRefresh;
    private HomePageHeadHolder headHolder;
    private EnumConstant.SearchType type = EnumConstant.SearchType.all;
    private boolean isAddHead = true;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        userId = getActivity().getIntent().getStringExtra("id");
        presenter = new HomePageFragmentPresenterImpl();
        presenter.attachView(this);
        return inflater.inflate(R.layout.fragment_home_page, container, false);
    }

    public static HomePageFragment newInstance() {
        return new HomePageFragment();
    }

    @Override
    protected void initData() {
        if (StringUtis.equals(userId, UserInfoManager.getInstance().getUid()))
            layout_home_content.setVisibility(View.GONE);

        if (isAddHeaderView()) {
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

            xlv.addHeaderView(headHolder.getContentView());
//            presenter.loadHeader(userId);
//            headHolder.onClick(headHolder.getContentView().findViewById(R.id.rl_all));
        }
        xlv.setVerticalLinearLayoutManager();
        xlv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                isRefresh = true;
                if (isAddHeaderView())
                    presenter.loadHeader(userId);
                presenter.loadRefresh(userId, type);
            }

            @Override
            public void onLoadMore() {
                isRefresh = false;
                presenter.loadMore(userId, type);
            }
        });
        xlv.setRefreshing(true);
//        if (isAddHeaderView()) {
//            presenter.loadHeader(userId);
//        } else {
//
//        }

        setRx();
    }

    private void setRx() {
        RxBus.with(this)
                .setEndEvent(FragmentEvent.DESTROY)
                .setEvent(Events.EventEnum.PAY_SUCCESS_GET_DATA)
                .onNext(events -> {
                    LogUtils.e("MyFragment", "PAY_SUCCESS_GET_DATA 数据");
                    if (events != null && adapter != null) {
                        Object message = events.message;
                        if (message instanceof PayRedPacketPicsBean) {
                            PayRedPacketPicsBean bean = (PayRedPacketPicsBean) message;
                            adapter.updataPayData(bean);

                        }
                    }
                })
                .create();
        //购买vip
        RxBus.with(this)
                .setEndEvent(FragmentEvent.DESTROY)
                .setEvent(Events.EventEnum.BUY_VIP_SUCCESS)
                .onNext(events -> {
                    if (headHolder != null) {
                        headHolder.onClick(headHolder.getContentView().findViewById(R.id.rl_all));
                    }
                })
                .create();

    }

    public void setSearchType(EnumConstant.SearchType type) {
        this.type = type;
    }

    public boolean isAddHeaderView() {
        return isAddHead;
    }

    public void setAddHeadFlage(boolean isAddHead) {
        this.isAddHead = isAddHead;
    }

    @Override
    public void setDynamicData(List<DynamicItemBean> list) {
        if (adapter == null) {
            adapter = new HomePageFragmentAdapter(list);
            adapter.setBaseView(this);
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
        String isverify = userInfo.getData().getBaseinfo().getIsverify();
        if (!StringUtis.equals(isverify, "1")) {
            rl_pay.setVisibility(View.GONE);
            rl_flower.setVisibility(View.GONE);
            rl_engagement.setVisibility(View.GONE);
        }
        String follow = userInfo.getData().getFollow();
        boolean equals = StringUtis.equals(follow, "1");
        Drawable drawable = UIUtils.getResources().getDrawable(!equals ? R.mipmap.home_page_not_wacth : R.mipmap.home_page_wacth);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        tv_wacth.setCompoundDrawables(drawable, null, null, null);
        tv_wacth.setText(UIUtils.getStringRes(equals ? R.string.wacth : R.string.not_wacth));
    }

    @Override
    public void refreshData() {
        if (presenter != null) {
            isRefresh = true;
            presenter.loadRefresh(userId, type);
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


    @OnClick({R.id.rl_reward, R.id.rl_im, R.id.rl_wacth, R.id.rl_pay, R.id.rl_flower, R.id.rl_engagement})
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
            case R.id.rl_engagement:
                ActivityUtils.startPersonEngagementTypeActivity(userId, userInfo.getData().getBaseinfo().getNickname(), userInfo.getData().getBaseinfo().getFace());
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
                    Drawable drawable = UIUtils.getResources().getDrawable(!equals ? R.mipmap.home_page_not_wacth : R.mipmap.home_page_wacth);
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

