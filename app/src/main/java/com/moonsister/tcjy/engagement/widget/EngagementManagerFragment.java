package com.moonsister.tcjy.engagement.widget;

import android.app.AlertDialog;
import android.view.View;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.adapter.EngagementManagerAdapter;
import com.moonsister.tcjy.base.BaseListFragment;
import com.moonsister.tcjy.bean.EngagementManagerBean;
import com.moonsister.tcjy.bean.PersonInfoDetail;
import com.moonsister.tcjy.bean.StatusBean;
import com.moonsister.tcjy.engagement.presenter.EngagementManagerFragmentPresenter;
import com.moonsister.tcjy.engagement.presenter.EngagementManagerFragmentPresenterImpl;
import com.moonsister.tcjy.engagement.view.EngagementManagerFragmentView;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.StringUtis;
import com.moonsister.tcjy.utils.UIUtils;
import com.trello.rxlifecycle.FragmentEvent;

import java.util.List;

/**
 * Created by jb on 2016/9/28.
 */
public class EngagementManagerFragment extends BaseListFragment<EngagementManagerAdapter, EngagementManagerBean.DataBean> implements EngagementManagerFragmentView {
    private ManagerType mType;
    private EngagementManagerFragmentPresenter presenter;


    public enum ManagerType {
        activity, passivity;
    }

    public static EngagementManagerFragment newInstance() {
        return new EngagementManagerFragment();
    }

    @Override
    protected void initChildData() {
        presenter = new EngagementManagerFragmentPresenterImpl();
        presenter.attachView(this);
        RxBus.with(this)
                .setEndEvent(FragmentEvent.DESTROY)
                .setEvent(Events.EventEnum.CLICK_ENGAGEMENT_SUCCESS)
                .onNext(events -> {
                    Object message = events.message;
                    if (message instanceof String) {
                        presenter.submitSuccess((String) message);
                    }
                })
                .create();
        RxBus.with(this)
                .setEndEvent(FragmentEvent.DESTROY)
                .setEvent(Events.EventEnum.CLICK_ENGAGEMENT_INVITE_2)
                .onNext(events -> {
                    PersonInfoDetail detail = UserInfoManager.getInstance().getMemoryPersonInfoDetail();
                    String sex = detail.getSex();
                    if (StringUtis.equals(sex, "1")) {
                        int attestation = detail.getAttestation();
                        if (attestation != 1) {

                            return;
                        }
                    } else {
                        int status = detail.getVipStatus();
                        if (status != 1) {
                            showNotLevel();
                            return;
                        }
                    }
                    Object message = events.message;
                    if (message instanceof String) {

                        presenter.submitInviteSuccess((String) message, "1");
                    }
                })
                .create();
        RxBus.with(this)
                .setEndEvent(FragmentEvent.DESTROY)
                .setEvent(Events.EventEnum.CLICK_ENGAGEMENT_REFUSE)
                .onNext(events -> {
                    Object message = events.message;
                    if (message instanceof String) {
                        presenter.submitInviteSuccess((String) message, "2");
                    }
                })
                .create();
        RxBus.with(this)
                .setEndEvent(FragmentEvent.DESTROY)
                .setEvent(Events.EventEnum.CLICK_ENGAGEMENT_LEVEL_NOT)
                .onNext(events ->
                        showNotLevel()
                )
                .create();
        if (mAdapter != null)
            mAdapter.setBaseView(this);
    }

    private void showNotLevel() {
        AlertDialog myDialog = new AlertDialog.Builder(getActivity()).create();
        myDialog.show();
        View view = UIUtils.inflateLayout(R.layout.dialog_show_notlevel);
        myDialog.getWindow().setContentView(view);
        view.findViewById(R.id.view_que)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String sex = UserInfoManager.getInstance().getUserSex();
                        if (StringUtis.equals(sex, "1")) {
                            ActivityUtils.startBuyVipActivity();
                        } else {
                            ActivityUtils.startRenzhengThreeActivity();
                        }
                        myDialog.dismiss();
                    }
                });


    }

    @Override
    public EngagementManagerAdapter setAdapter() {
        EngagementManagerAdapter adapter = new EngagementManagerAdapter(null);
        return adapter;
    }

    @Override
    protected void onRefresh() {
        presenter.loadData(mType, page);

    }

    @Override
    protected void onLoadMore() {
        presenter.loadData(mType, page);
    }

    @Override
    public void setData(EngagementManagerBean bean) {
        List<EngagementManagerBean.DataBean> datas = bean.getData();
        for (EngagementManagerBean.DataBean dataBean : datas) {
            dataBean.setManagerType(mType);
        }
        addData(datas);
    }

    /**
     * 约会成功
     *
     * @param id
     */
    @Override
    public void submitSuccess(String id) {
        if (mAdapter != null) {
            List<EngagementManagerBean.DataBean> datas = mAdapter.getDatas();
            if (datas != null) {
                for (EngagementManagerBean.DataBean bean : datas) {
                    if (StringUtis.equals(bean.getId(), id)) {
                        bean.setStatus(5);
                        mAdapter.onRefresh();
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void submitInviteSuccess(StatusBean mbean) {
        if (StringUtis.equals(mbean.getCode(), "10")) {
            showNotLevel();
        } else if (StringUtis.equals(mbean.getCode(), "1")) {
            if (mAdapter != null) {
                List<EngagementManagerBean.DataBean> datas = mAdapter.getDatas();
                if (datas != null) {
                    for (EngagementManagerBean.DataBean bean : datas) {
                        if (StringUtis.equals(bean.getId(), mbean.getId())) {
                            bean.setStatus(2);
                            mAdapter.onRefresh();
                            break;
                        }
                    }
                }
            }
        } else {
            showToast(mbean.getMsg());
        }

    }

    public void setType(ManagerType type) {
        mType = type;
    }

    @Override
    public void showLoading() {
        showProgressDialog();
    }

    @Override
    public void hideLoading() {
        hideProgressDialog();
        colorLoad();
    }

    @Override
    public void transfePageMsg(String msg) {
        showToast(msg);
    }


}
