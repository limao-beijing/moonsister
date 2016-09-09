package com.moonsister.tcjy.main.widget;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.adapter.DynamicAdapter;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.bean.PayRedPacketPicsBean;
import com.moonsister.tcjy.bean.UserInfoDetailBean;
import com.moonsister.tcjy.bean.UserInfoListBean;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.main.presenter.DynamicPresenter;
import com.moonsister.tcjy.main.presenter.DynamicPresenterImpl;
import com.moonsister.tcjy.main.view.DynamicView;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.LogUtils;
import com.moonsister.tcjy.utils.StringUtis;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tcjy.viewholder.DynamicHeadViewHolder;
import com.moonsister.tcjy.widget.XListView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.trello.rxlifecycle.ActivityEvent;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by pc on 2016/6/3.
 */
public class DynamicActivity extends BaseActivity implements DynamicView {

    @Bind(R.id.recyclerview)
    XListView recyclerview;
    @Bind(R.id.text_empty)
    TextView textEmpty;
    @Bind(R.id.tv_reward)
    TextView tvReward;
    @Bind(R.id.tv_send_msg)
    TextView tvSendMsg;
    @Bind(R.id.tv_appointment)
    TextView tvAppointment;
    @Bind(R.id.layout_usee_action)
    LinearLayout layout_usee_action;
    @Bind(R.id.single_send_msg)
    TextView single_send_msg;
    private DynamicPresenter mPresenter;
    private DynamicAdapter mAdapter;
    private boolean isRefresh;
    private DynamicHeadViewHolder headHolder;
    private String userId;
    private String nikeName;

    @Override
    protected void initView() {

        userId = getIntent().getStringExtra(AppConstant.USER_ID);
        recyclerview.setVerticalLinearLayoutManager();
        recyclerview.addHeaderView(initHeadLayout());
//        recyclerview.addOnScrollListener(dynamic_new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                if (layout_usee_action != null && !layout_usee_action.isShown())
//                    layout_usee_action.setVisibility(View.VISIBLE);
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                if (layout_usee_action != null && layout_usee_action.isShown())
//                    layout_usee_action.setVisibility(View.INVISIBLE);
//            }
//        });
        recyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                isRefresh = true;
                mPresenter.loadUserInfodetail(userId);
                mPresenter.loadonRefreshData(userId);
            }

            @Override
            public void onLoadMore() {
                isRefresh = false;
                mPresenter.loadLoadMoreData(userId);
            }
        });
        recyclerview.setRefreshing(true);
        setRxbus();
    }

    private void setRxbus() {
        RxBus.with(this)
                .setEndEvent(ActivityEvent.DESTROY)
                .setEvent(Events.EventEnum.PAY_SUCCESS_GET_DATA)
                .onNext(events -> {
                    LogUtils.e("MyFragment", "PAY_SUCCESS_GET_DATA 数据");
                    if (events != null && mAdapter != null) {
                        Object message = events.message;
                        if (message instanceof PayRedPacketPicsBean) {
                            PayRedPacketPicsBean bean = (PayRedPacketPicsBean) message;
                            mAdapter.updataPayData(bean);

                        }
                    }
                })
                .create();

        RxBus.with(this)
                .setEndEvent(ActivityEvent.DESTROY)
                .setEvent(Events.EventEnum.DYNAMIC_DELETE_ACTION)
                .onNext(events -> {
                    String id = (String) events.message;
                    if (mAdapter != null) {
                        mPresenter.deleteDynamic(id);

                    }
                })
                .create();
        RxBus.with(this)
                .setEndEvent(ActivityEvent.DESTROY)
                .setEvent(Events.EventEnum.DYNAMIC_UP_ACTION)
                .onNext(events -> {
                    String id = (String) events.message;
                    if (mAdapter != null) {
                        mPresenter.upDynamic(id);

                    }
                })
                .create();
        RxBus.with(this)
                .setEndEvent(ActivityEvent.DESTROY)
                .setEvent(Events.EventEnum.DYNAMIC_DEL_UP_ACTION)
                .onNext(events -> {
                    String id = (String) events.message;
                    if (mAdapter != null) {
                        mPresenter.delUpDynamic(id);

                    }
                })
                .create();
        //购买vip
        RxBus.with(this)
                .setEndEvent(ActivityEvent.DESTROY)
                .setEvent(Events.EventEnum.BUY_VIP_SUCCESS)
                .onNext(events -> {
                    if (mPresenter != null) {
                        mPresenter.loadonRefreshData(userId);
                    }
                })
                .create();

    }

    /**
     * 初始化头部局
     *
     * @return
     */
    private View initHeadLayout() {
        View headView = UIUtils.inflateLayout(R.layout.head_user_info);
        headHolder = new DynamicHeadViewHolder(headView);
        headHolder.setUserInfoView(this);
        return headView;
    }

    @Override
    protected View setRootContentView() {
        mPresenter = new DynamicPresenterImpl();
        mPresenter.attachView(this);
        return UIUtils.inflateLayout(R.layout.activity_userinfo_dynamic);
    }

    @OnClick({R.id.single_send_msg, R.id.tv_reward, R.id.tv_send_msg, R.id.tv_appointment, R.id.tv_send_flowers})
    public void onClick(View view) {
        mPresenter.switchNavigation(view.getId());
    }

    @Override
    public void loadUserinfo(List<UserInfoListBean.UserInfoListBeanData.UserInfoListBeanDataList> list) {

        if (mAdapter == null) {
            mAdapter = new DynamicAdapter(list);
            mAdapter.setView(this);
            mAdapter.setActivty(this);
            recyclerview.setAdapter(mAdapter);
        } else {
            if (isRefresh)
                mAdapter.clean();
            mAdapter.addListData(list);
            mAdapter.onRefresh();
        }
        loadMoreComplete();
    }

    private void loadMoreComplete() {
        if (recyclerview == null)
            return;
        recyclerview.loadMoreComplete();
        recyclerview.refreshComplete();

    }

    @Override
    public void switch2RewardActivity() {

        ActivityUtils.startRedpacketActivity(userId, 1, avater);

    }

    @Override
    public void switch2SendMsgActivity() {
        ActivityUtils.startAppConversationActivity(userId, nikeName, avater);
    }

    @Override
    public void switch2AppointmentActivity() {
        showToast(UIUtils.getStringRes(R.string.not_dredge));
//        ActivityUtils.startPayAppointmentActivity();
    }

    @Override
    public void swicth2SendFlowersActivity() {
        ActivityUtils.startRedpacketActivity(userId, 2, avater);
    }

    private void colseload() {
        if (recyclerview == null)
            return;
        if (isRefresh)
            recyclerview.refreshComplete();
        else recyclerview.loadMoreComplete();

    }

    private String avater;

    @Override
    public void setUserInfodetail(UserInfoDetailBean bean) {
        if (bean != null && bean.getData() != null) {
            UserInfoDetailBean.UserInfoDetailDataBean.Baseinfo baseinfo = bean.getData().getBaseinfo();
            avater = baseinfo.getFace();
            nikeName = baseinfo.getNickname();
            bean.getData().setUid(userId);
            if (StringUtis.equals(userId, UserInfoManager.getInstance().getUid())) {
                layout_usee_action.setVisibility(View.GONE);
                single_send_msg.setVisibility(View.GONE);
            } else {
                if (StringUtis.equals(baseinfo.getIsverify(), "1")) {
                    layout_usee_action.setVisibility(View.VISIBLE);
                    single_send_msg.setVisibility(View.GONE);
                } else {
                    layout_usee_action.setVisibility(View.GONE);
                    single_send_msg.setVisibility(View.VISIBLE);
                }
            }

        }
        headHolder.setUserInfodetail(bean);
    }

    @Override
    public void pageFinish() {
        finish();
    }

    @Override
    public void deleteDynamic(String id) {
        if (mAdapter != null) {
            mAdapter.deleteDynamic(id);
        }
    }

    @Override
    public void upLoadDynamic() {
        if (mPresenter != null)
            mPresenter.loadonRefreshData(userId);
    }

    @Override
    public void showLoading() {
        showProgressDialog();
    }

    @Override
    public void hideLoading() {
        hideProgressDialog();
        colseload();
    }

    @Override
    public void transfePageMsg(String msg) {
        showToast(msg);
    }


}
