package com.moonsister.tcjy.main.widget;

import android.view.View;
import android.widget.EditText;

import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.adapter.DynamicAdapter;
import com.moonsister.tcjy.adapter.DynamicDetailsAdapter;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.base.BaseRecyclerViewHolder;
import com.moonsister.tcjy.bean.CommentDataListBean;
import com.moonsister.tcjy.bean.PayRedPacketPicsBean;
import com.moonsister.tcjy.bean.UserInfoListBean;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.main.presenter.DynamincDatailsPresenter;
import com.moonsister.tcjy.main.presenter.DynamincDatailsPresenterImpl;
import com.moonsister.tcjy.main.view.DynamicDatailsView;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.LogUtils;
import com.moonsister.tcjy.utils.StringUtis;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tcjy.viewholder.DynamicViewHolder;
import com.moonsister.tcjy.viewholder.dynamic.PicViewHolder;
import com.moonsister.tcjy.viewholder.dynamic.VideoViewHolder;
import com.moonsister.tcjy.viewholder.dynamic.VoiceViewHolder;
import com.moonsister.tcjy.widget.XListView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.trello.rxlifecycle.ActivityEvent;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import im.gouyin.com.progressdialog.AlearDialog;

/**
 * Created by pc on 2016/6/7.
 */
public class DynamicDatailsActivity extends BaseActivity implements DynamicDatailsView {

    @Bind(R.id.lv)
    XListView recyclerView;
    @Bind(R.id.ed_input)
    EditText edInput;
    private DynamicDetailsAdapter mAdapter;
    private DynamincDatailsPresenter presenter;
    private UserInfoListBean.UserInfoListBeanData.UserInfoListBeanDataList userInfo;
    private BaseRecyclerViewHolder<UserInfoListBean.UserInfoListBeanData.UserInfoListBeanDataList> holder;

    @Override

    protected void initView() {
        setRx();
        recyclerView.setVerticalLinearLayoutManager();
        recyclerView.setPullRefreshEnabled(false);
        if (userInfo != null) {
            presenter.loadCommentListData(userInfo.getLatest_id());
        }
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {
                presenter.loadCommentListData(userInfo.getLatest_id());
            }
        });


    }

    private void setRx() {

        RxBus.with(this)
                .setEndEvent(ActivityEvent.DESTROY)
                .setEvent(Events.EventEnum.DYNAMIC_DELETE_ACTION)
                .onNext(events -> {
                    String id = (String) events.message;
                    if (mAdapter != null) {
                        presenter.deleteDynamic(id);

                    }
                })
                .create();
        RxBus.with(this)
                .setEndEvent(ActivityEvent.DESTROY)
                .setEvent(Events.EventEnum.PAY_SUCCESS_GET_DATA)
                .onNext(events -> {
                    LogUtils.e("MyFragment", "PAY_SUCCESS_GET_DATA 数据");
                    if (events != null && mAdapter != null) {
                        Object message = events.message;
                        if (message instanceof PayRedPacketPicsBean) {
                            PayRedPacketPicsBean bean = (PayRedPacketPicsBean) message;
                            userInfo.setIspay("1");
                            userInfo.getSimg();
                            userInfo.getSimg().clear();
                            userInfo.getSimg().addAll(bean.getData().getSimg());
                            userInfo.getImg().clear();
                            userInfo.getImg().addAll(bean.getData().getImg());
                            holder.onBindData(userInfo);
                        }
                    }
                })
                .create();
    }

    @Override
    protected View setRootContentView() {
        userInfo = (UserInfoListBean.UserInfoListBeanData.UserInfoListBeanDataList) getIntent().getSerializableExtra(AppConstant.DYNAMIC_DATAILS);
        if (userInfo == null) {
            finish();
            return null;
        }
        presenter = new DynamincDatailsPresenterImpl();
        presenter.attachView(this);
        return UIUtils.inflateLayout(R.layout.activity_dynamic_datails);
    }

    @Override
    protected String initTitleName() {
        return getResources().getString(R.string.activity_name_dynamic_datails);
    }

    @Override
    public void loadData(List<CommentDataListBean.DataBean> datas) {
        if (mAdapter == null) {
            mAdapter = new DynamicDetailsAdapter(datas);
            View view = UIUtils.inflateLayout(R.layout.item_home_one_menu);
//            holder = new DynamicViewHolder(view, userInfo.getType());
            initHeader(userInfo.getType());
            holder.onBindData(userInfo);
            holder.setView(this);
            recyclerView.addHeaderView(holder.getRootView());
            recyclerView.setAdapter(mAdapter);
        } else {

            mAdapter.addListData(datas);
            if (datas != null && datas.size() == 0) {
                recyclerView.setNoMore();
            }
        }
        if (recyclerView != null) {
            recyclerView.loadMoreComplete();
        }
    }

    private void initHeader(int type) {
        switch (type) {
            case DynamicAdapter.TYPE_CHARGE_PIC:
                holder = new PicViewHolder(UIUtils.inflateLayout(R.layout.item_dynamic_pic));
                break;
            case DynamicAdapter.TYPE_FREE_PIC:
                holder = new PicViewHolder(UIUtils.inflateLayout(R.layout.item_dynamic_pic));
                break;
            case DynamicAdapter.TYPE_CHARGE_VIDEO:
                holder = new VideoViewHolder(UIUtils.inflateLayout(R.layout.item_dynamic_video));
                break;
            case DynamicAdapter.TYPE_FREE_VIDEO:
                holder = new VideoViewHolder(UIUtils.inflateLayout(R.layout.item_dynamic_video));
                break;
            case DynamicAdapter.TYPE_CHARGE_VOICE:
                holder = new VoiceViewHolder(UIUtils.inflateLayout(R.layout.item_dynamic_voice));
                break;
            case DynamicAdapter.TYPE_FREE_VOICE:
                holder = new VoiceViewHolder(UIUtils.inflateLayout(R.layout.item_dynamic_voice));
                break;
        }
    }

    @Override
    public void CommentSuccess() {
        CommentDataListBean.DataBean dataBean = new CommentDataListBean.DataBean();

        dataBean.setFace(UserInfoManager.getInstance().getAvater());
        dataBean.setNickname(UserInfoManager.getInstance().getNickeName());
        dataBean.setCreate_time(System.currentTimeMillis() / 1000);
        dataBean.setTitle(s);
        if (mAdapter != null) {

            mAdapter.addSingeData(0, dataBean);
            mAdapter.onRefresh();
        }
    }

    @Override
    public void deleteDynamic(String id) {
        UIUtils.sendDelayedOneMillis(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        });
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

    }

    private String s;

    @OnClick(R.id.btn_send)
    public void onClick() {
        if (certificationStatus())
            return;
        s = edInput.getText().toString();
        if (StringUtis.isEmpty(s)) {
            showToast(UIUtils.getStringRes(R.string.input) + UIUtils.getStringRes(R.string.not_empty));
            return;
        }
        if (s.length() > 100) {
            showToast(UIUtils.getStringRes(R.string.input_text_number_100));
            return;
        }
        presenter.sendComment(userInfo.getLatest_id(), s, "");
        edInput.setText("");
    }

    public boolean certificationStatus() {
        boolean iscertificationStatus = false;
        int certificationStatus = UserInfoManager.getInstance().getCertificationStatus();
        if (certificationStatus == 3) {
            iscertificationStatus = true;
            AlearDialog alearDialog = new AlearDialog(AlearDialog.DialogType.Certification_comment, this);
            alearDialog.setListenter(new AlearDialog.onClickListenter() {
                @Override
                public void clickType(AlearDialog.clickType type) {
                    switch (type) {
                        case cancel:
                            alearDialog.dismiss();
                            break;
                        case confirm_vip:
                            ActivityUtils.startBuyVipActivity();
                            break;
                        case confirm:
                            ActivityUtils.startCertificationActivity();
                            break;

                    }
                    alearDialog.dismiss();
                }
            });
        }
        return iscertificationStatus;
    }
}
