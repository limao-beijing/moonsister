package com.moonsister.tcjy.my.widget;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.adapter.DynamicAdapter;
import com.moonsister.tcjy.base.BaseFragment;
import com.moonsister.tcjy.bean.PayRedPacketPicsBean;
import com.moonsister.tcjy.bean.UserInfoDetailBean;
import com.moonsister.tcjy.bean.UserInfoListBean;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.my.persenter.MyFragmentPresenter;
import com.moonsister.tcjy.my.persenter.MyFragmentPresenterImpl;
import com.moonsister.tcjy.my.view.MyFragmentView;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.StringUtis;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tcjy.viewholder.PersonDynamicViewholder;
import com.moonsister.tcjy.widget.XListView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.trello.rxlifecycle.FragmentEvent;

import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by pc on 2016/5/31.
 */
public class MyFragment extends BaseFragment implements MyFragmentView {

    @Bind(R.id.tv_certification)
    TextView tv_certification;
    @Bind(R.id.recyclerview)
    XListView recyclerview;
    private MyFragmentPresenter mPresenter;
    private DynamicAdapter mAdapter;
    private boolean isRefresh;

    private PersonDynamicViewholder personDynamicViewholder;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mPresenter = new MyFragmentPresenterImpl();
        mPresenter.attachView(this);

        return UIUtils.inflateLayout(R.layout.activity_person_dynamic);
    }

    public void updataPayData(PayRedPacketPicsBean bean) {
        if (mAdapter != null) {
            mAdapter.updataPayData(bean);
        }
    }

    @Override
    protected void initData() {
        int certification = UserInfoManager.getInstance().getCertificationStatus();
        /**
         * 认证状态 1 已认证  2 认证中  3 未认证
         */
        if (certification == 1) {
            tv_certification.setText(UIUtils.getStringRes(R.string.already_Certification));
        } else if (certification == 2) {
            tv_certification.setText(UIUtils.getStringRes(R.string.Certificationing));
        } else
            tv_certification.setText(UIUtils.getStringRes(R.string.not_Certificationing));

        recyclerview.setVerticalLinearLayoutManager();
        recyclerview.addHeaderView(initHeadLayout());
        recyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                isRefresh = true;
                mPresenter.loadPersonHeader();
                mPresenter.loadonRefreshData();
            }

            @Override
            public void onLoadMore() {
                isRefresh = false;
                mPresenter.loadLoadMoreData();
            }
        });
        mPresenter.loadPersonHeader();
        recyclerview.setRefreshing(true);
        setRx();

    }

    private void setRx() {
        //背景图
        RxBus.with(this)
                .setEndEvent(FragmentEvent.DESTROY)
                .setEvent(Events.EventEnum.CROP_IMAGE_PATH)
                .onNext(events -> {
                    if (events != null && personDynamicViewholder != null) {
                        String path = (String) events.message;
                        mPresenter.uploadBackground(path);
                    }
                })
                .create();
        // 删除动态
        RxBus.with(this)
                .setEndEvent(FragmentEvent.DESTROY)
                .setEvent(Events.EventEnum.DYNAMIC_DELETE_ACTION)
                .onNext(events -> {
                    String id = (String) events.message;
                    if (mAdapter != null) {
                        mPresenter.deleteDynamic(id);

                    }
                }).create();
        // 置顶
        RxBus.with(this)
                .setEndEvent(FragmentEvent.DESTROY)
                .setEvent(Events.EventEnum.DYNAMIC_UP_ACTION)
                .onNext(events -> {
                    String id = (String) events.message;
                    if (mAdapter != null) {
                        mPresenter.upDynamic(id);

                    }
                })
                .create();
        // 删除置顶
        RxBus.with(this)
                .setEndEvent(FragmentEvent.DESTROY)
                .setEvent(Events.EventEnum.DYNAMIC_DEL_UP_ACTION)
                .onNext(events -> {
                    String id = (String) events.message;
                    if (mAdapter != null) {
                        mPresenter.delUpDynamic(id);

                    }
                })
                .create();
        //更新用户信息
        RxBus.with(this)
                .setEndEvent(FragmentEvent.DESTROY)
                .setEvent(Events.EventEnum.USERINFO_CHANGE)
                .onNext(events -> {
                    if (mPresenter != null) {
                        mPresenter.loadPersonHeader();
                        if (recyclerview != null) {
                            mPresenter.loadPersonHeader();
                            recyclerview.setRefreshing(true);
                        }
                    }
                })
                .create();
        //购买vip
        RxBus.with(this)
                .setEndEvent(FragmentEvent.DESTROY)
                .setEvent(Events.EventEnum.BUY_VIP_SUCCESS)
                .onNext(events -> {
                    if (mPresenter != null) {
                        mPresenter.loadonRefreshData();
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

        personDynamicViewholder = new PersonDynamicViewholder();
        return personDynamicViewholder.getContentView();
    }

    @Override
    public void setUserBackground(String path) {
        if (personDynamicViewholder != null)
            personDynamicViewholder.upImage(path);
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
            mPresenter.loadonRefreshData();
    }

    @OnClick({R.id.tv_certification, R.id.tv_withdraw_deposit, R.id.tv_appointment, R.id.tv_person_order, R.id.tv_person_setting})
    public void onClick(View view) {
        mPresenter.swicth2Page(view.getId());

    }


    private void loadMoreComplete() {
        if (recyclerview == null)
            return;
        recyclerview.loadMoreComplete();
        recyclerview.refreshComplete();

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

    /**
     * 认证
     */
    @Override
    public void swich2PersonRed() {
        int certification = UserInfoManager.getInstance().getCertificationStatus();
        /**
         * 认证状态 1 已认证  2 认证中  3 未认证
         */
        if (certification == 1) {
            String stringRes = UIUtils.getStringRes(R.string.already_Certification);
            String certificationStatus = tv_certification.getText().toString().trim();
            if (!StringUtis.equals(certificationStatus, stringRes))
                tv_certification.setText(stringRes);
            showToast(stringRes);
        } else if (certification == 2) {
            String str = UIUtils.getStringRes(R.string.Certificationing);
            String certificationStatus = tv_certification.getText().toString().trim();
            if (!StringUtis.equals(certificationStatus, str))
                tv_certification.setText(str);
            ActivityUtils.startRZThidActivity();
        } else

            ActivityUtils.startCertificationActivity();


    }

    /**
     * 提现
     */
    @Override
    public void swich2WithdRawDeposit() {
        ActivityUtils.startWithdRawDepositActivity();
    }

    /**
     * 约会
     */
    @Override
    public void swich2Appointment() {
        showToast(UIUtils.getStringRes(R.string.not_dredge));
//        ActivityUtils.startAppointmentActivity();
    }

    /**
     * 订单
     */
    @Override
    public void swich2PersonOrder() {
        showToast(UIUtils.getStringRes(R.string.not_dredge));
//        ActivityUtils.startMyOrderActivity();
    }

    /**
     * 设置
     */
    @Override
    public void swich2PersonSetting() {
        ActivityUtils.startSettingActivity();
    }

    @Override
    public void setListData(List<UserInfoListBean.UserInfoListBeanData.UserInfoListBeanDataList> list) {
        if (mAdapter == null) {
            mAdapter = new DynamicAdapter(list);
            mAdapter.setView(this);
            recyclerview.setAdapter(mAdapter);
        } else {
            if (isRefresh)
                mAdapter.clean();
            mAdapter.addListData(list);
            mAdapter.onRefresh();
        }
        loadMoreComplete();
    }

    @Override
    public void setUserInfo(UserInfoDetailBean bean) {
        if (personDynamicViewholder != null && bean != null)
            personDynamicViewholder.refreshView(bean);
    }


}
