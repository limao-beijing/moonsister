package com.moonsister.tcjy.main.widget;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.hickey.network.bean.CommentDataListBean;
import com.hickey.network.bean.DefaultDataBean;
import com.hickey.network.bean.DynamicDatailsBean;
import com.hickey.network.bean.DynamicItemBean;
import com.hickey.network.bean.PayRedPacketPicsBean;
import com.hickey.tool.base.BaseActivity;
import com.hickey.tool.base.BaseIModel;
import com.hickey.tool.base.BaseRecyclerViewHolder;
import com.hickey.tool.constant.EnumConstant;
import com.hickey.tool.lang.StringUtis;
import com.hickey.tool.widget.UIUtils;
import com.hickey.tool.widget.XListView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.adapter.DynamicAdapter;
import com.moonsister.tcjy.adapter.DynamicDetailsAdapter;
import com.moonsister.tcjy.dialogFragment.DialogMannager;
import com.hickey.tool.base.BaseDialogFragment;
import com.moonsister.tcjy.dialogFragment.widget.ImPermissionDialog;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.main.model.UserActionModelImpl;
import com.moonsister.tcjy.main.presenter.DynamincDatailsPresenter;
import com.moonsister.tcjy.main.presenter.DynamincDatailsPresenterImpl;
import com.moonsister.tcjy.main.view.DynamicDatailsView;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.permission.UserPermissionManager;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.LogUtils;
import com.moonsister.tcjy.viewholder.CommentDynacmicViewHolder;
import com.moonsister.tcjy.viewholder.homepage.HomePagePicViewHolder;
import com.moonsister.tcjy.viewholder.homepage.HomePageVideoViewHolder;
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
    @Bind(R.id.id_layout_input)
    View id_layout_input;
    @Bind(R.id.id_layout_comment)
    View id_layout_comment;
    @Bind(R.id.tv_not_like)
    TextView tv_not_like;
    @Bind(R.id.tv_like)
    TextView tv_like;
    private DynamicDetailsAdapter mAdapter;
    private DynamincDatailsPresenter presenter;
    private String dynamicId;

    private BaseRecyclerViewHolder<DynamicItemBean> holder;
    private CommentDynacmicViewHolder dynacmicViewHolder;

    @Override
    protected View setRootContentView() {
        dynamicId = getIntent().getStringExtra("id");
        int dynamicType = getIntent().getIntExtra("type", -1);
        initHeader(dynamicType);
        if (StringUtis.isEmpty(dynamicId))
            finish();
        presenter = new DynamincDatailsPresenterImpl();
        presenter.attachView(this);
        return UIUtils.inflateLayout(R.layout.activity_dynamic_datails);
    }

    @Override
    protected String initTitleName() {
        return getResources().getString(R.string.activity_name_dynamic_datails);
    }

    @Override
    protected void initView() {
        TextView tv_title_right = (TextView) titleView.findViewById(R.id.tv_title_right);
        Drawable drawable = getResources().getDrawable(R.mipmap.im_userinfo);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        tv_title_right.setCompoundDrawables(drawable, null, null, null);
//        tv_title_right.setText(getString(R.string.home_page));
        tv_title_right.setPadding(20, 20, 20, 20);
//        tv_title_right.setTextColor(getResources().getColor(R.color.white_778998));
        tv_title_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDatailsBean != null)
                    ActivityUtils.startDynamicActivity(mDatailsBean.getData().getUid());
            }
        });
        setRx();
        recyclerView.setVerticalLinearLayoutManager();
        recyclerView.setPullRefreshEnabled(false);
        presenter.loadCommentListData(dynamicId);


        presenter.loadSingeDyamic(dynamicId);
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {

                presenter.loadCommentListData(dynamicId);
            }
        });
        mRootView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (bottom > oldBottom) {
                    LogUtils.d(this, "键盘关闭");
                    if (id_layout_comment != null)
                        id_layout_comment.setVisibility(View.VISIBLE);
                }
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
//                            PayRedPacketPicsBean bean = (PayRedPacketPicsBean) message;
//                            userInfo.setIspay("1");
//                            userInfo.getSimg();
//                            userInfo.getSimg().clear();
//                            userInfo.getSimg().addAll(bean.getData().getSimg());
//                            userInfo.getImg().clear();
//                            userInfo.getImg().addAll(bean.getData().getImg());
//                            holder.onBindData(userInfo);
                            if (presenter != null)
                                presenter.loadSingeDyamic(dynamicId);
                        }
                    }
                })
                .create();
    }


    @Override
    public void loadData(List<CommentDataListBean.DataBean> datas) {
        if (mAdapter == null) {
            mAdapter = new DynamicDetailsAdapter(datas);
            View view = UIUtils.inflateLayout(R.layout.item_home_one_menu);
//            holder = new DynamicViewHolder(view, userInfo.getType());

//            holder.onBindData(userInfo);
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
            case DynamicAdapter.TYPE_FREE_PIC:
                holder = new HomePagePicViewHolder(UIUtils.inflateLayout(R.layout.item_dynamic_home_page_pic));
                break;
            case DynamicAdapter.TYPE_CHARGE_VIDEO:
            case DynamicAdapter.TYPE_FREE_VIDEO:
            case DynamicAdapter.TYPE_CHARGE_VOICE:
            case DynamicAdapter.TYPE_FREE_VOICE:
                holder = new HomePageVideoViewHolder(UIUtils.inflateLayout(R.layout.item_dynamic_home_page_video));
                break;
        }
        if (holder != null && holder.getRootView() != null) {
            View view = holder.getRootView().findViewById(R.id.ll_action);
            if (view != null) {
                view.setVisibility(View.GONE);
            }
            View view1 = holder.getRootView();
            if (view1 instanceof ViewGroup) {
                dynacmicViewHolder = new CommentDynacmicViewHolder();
                ((ViewGroup) view1).addView(dynacmicViewHolder.getContentView());
            }

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
        if (mDatailsBean != null && mDatailsBean.getData() != null) {
            String lupn = mDatailsBean.getData().getComment_count();
            int i = StringUtis.string2Int(lupn) + 1;
            mDatailsBean.getData().setComment_count(i + "");
            setDynamicDatails(mDatailsBean);
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

    private DynamicDatailsBean mDatailsBean;

    @Override
    public void setDynamicDatails(DynamicDatailsBean bean) {
        if (bean != null) {
            this.mDatailsBean = bean;

            holder.onBindData(bean.getData());
            dynacmicViewHolder.refreshView(bean);
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

    private String s;
    private boolean isLikeAction = false;

    @OnClick({R.id.rl_comment_content, R.id.rl_reward, R.id.rl_not_like, R.id.rl_like, R.id.btn_send})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_comment_content:
                id_layout_comment.setVisibility(View.GONE);
                showSoftInput(edInput);
                break;

            case R.id.rl_not_like:
                //type  1顶 2取消顶，3踩，4取消踩
                if (isLikeAction)
                    return;
                isLikeAction = true;
                if (!StringUtis.equals(likeType, "2"))
//                if (StringUtis.isEmpty(likeType) || StringUtis.equals(likeType, "3") || StringUtis.equals(likeType, "4") || StringUtis.equals(likeType, "2"))
                    notLikeAction(StringUtis.isEmpty(likeType) || StringUtis.equals("2", likeType) ? "3" : likeType);
                break;
            case R.id.rl_like:
                //type  1顶 2取消顶，3踩，4取消踩
                if (isLikeAction)
                    return;
                isLikeAction = true;
                if (!StringUtis.equals(likeType, "4"))
//                if (StringUtis.isEmpty(likeType) || StringUtis.equals(likeType, "1") || StringUtis.equals(likeType, "2") || StringUtis.equals(likeType, "4"))
                    LikeAction(StringUtis.isEmpty(likeType) || StringUtis.equals("4", likeType) ? "1" : likeType);
                break;
            case R.id.btn_send:
                checkPermssion();
                break;
            case R.id.rl_reward:
                if (mDatailsBean != null && mDatailsBean.getData() != null) {
                    DynamicItemBean data = mDatailsBean.getData();
                    ActivityUtils.startRedpacketActivity(data.getUid(), RedpacketAcitivity.RedpacketType.TYPE_REDPACKET, data.getFace());
                }
                break;
        }
    }

    private String likeType;

    private void notLikeAction(String type) {
        UserActionModelImpl model = new UserActionModelImpl();
        final String finalType = type;
        showLoading();
        model.likeAction(dynamicId, type, new BaseIModel.onLoadDateSingleListener<DefaultDataBean>() {
                    @Override
                    public void onSuccess(DefaultDataBean bean, BaseIModel.DataType dataType) {
                        if (bean != null || StringUtis.equals(bean.getCode(), "1")) {
                            Drawable drawable = getResources().getDrawable(StringUtis.equals(finalType, "4") ? R.mipmap.not_like_icon : R.mipmap.not_liked_icon);
                            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                            tv_not_like.setCompoundDrawables(drawable, null, null, null);
                            if (mDatailsBean != null && mDatailsBean.getData() != null) {
                                String lupn = mDatailsBean.getData().getLdon();
                                int i = StringUtis.string2Int(lupn);
                                if (StringUtis.equals(type, "4")) {
                                    i = i > 0 ? (i - 1) : 0;
                                } else {
                                    i = i + 1;
                                }
                                mDatailsBean.getData().setLdon(i + "");
                                setDynamicDatails(mDatailsBean);
                            }
                            showToast(bean.getMsg());

                        }

                        likeType = StringUtis.equals(type, "3") ? "4" : "3";
                        hideLoading();
                    }

                    @Override
                    public void onFailure(String msg) {
                        showToast(msg);
                        hideLoading();
                    }
                }

        );
    }

    private void LikeAction(String type) {
        UserActionModelImpl model = new UserActionModelImpl();
        final String finalType = type;
        showLoading();
        model.likeAction(dynamicId, type, new BaseIModel.onLoadDateSingleListener<DefaultDataBean>() {
            @Override
            public void onSuccess(DefaultDataBean bean, BaseIModel.DataType dataType) {
                if (bean != null || StringUtis.equals(bean.getCode(), "1")) {
                    Drawable drawable = getResources().getDrawable(StringUtis.equals(finalType, "2") ? R.mipmap.dynamic_like_icon : R.mipmap.dynamic_liked_icon);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tv_like.setCompoundDrawables(drawable, null, null, null);
                    if (mDatailsBean != null && mDatailsBean.getData() != null) {
                        String lupn = mDatailsBean.getData().getLupn();
                        int i = StringUtis.string2Int(lupn);
                        if (StringUtis.equals(type, "2")) {
                            i = i > 0 ? (i - 1) : 0;
                        } else {
                            i = i + 1;
                        }
                        mDatailsBean.getData().setLupn(i + "");
                        setDynamicDatails(mDatailsBean);
                        showToast(bean.getMsg());
                    }
                }
                likeType = StringUtis.equals(type, "1") ? "2" : "1";
                hideLoading();
            }

            @Override
            public void onFailure(String msg) {
                showToast(msg);
                hideLoading();
            }
        });
    }


    /**
     * 发送评论
     */
    private void checkPermssion() {
        UserPermissionManager.getInstance().checkVip(EnumConstant.PermissionType.COMMENT, new UserPermissionManager.PermissionCallback() {
            @Override
            public void onStatus(EnumConstant.PermissionReasult reasult, int imCount, String sex) {
                if (reasult == EnumConstant.PermissionReasult.HAVE_PERSSION) {
                    sendComment();
                } else if (reasult == EnumConstant.PermissionReasult.NOT_PERSSION) {
                    DialogMannager.getInstance().showImPermission(sex, getSupportFragmentManager(), new ImPermissionDialog.OnCallBack() {
                        @Override
                        public void onStatus(BaseDialogFragment dialogFragment, EnumConstant.DialogCallBack statusCode) {
                            if (statusCode == EnumConstant.DialogCallBack.CONFIRM) {
                                if (StringUtis.equals("1", sex)) {
                                    ActivityUtils.startBuyVipActivity();
                                } else
                                    ActivityUtils.startRenZhengThreeActivity();
                                dialogFragment.dismissDialogFragment();
                            } else if (statusCode == EnumConstant.DialogCallBack.CANCEL) {
                                dialogFragment.dismissDialogFragment();
                            }
                        }
                    });
                }
            }
        });

    }

    private void sendComment() {
        s = edInput.getText().toString();
        if (StringUtis.isEmpty(s)) {
            showToast(UIUtils.getStringRes(R.string.input) + UIUtils.getStringRes(R.string.not_empty));
            return;
        }
        if (s.length() > 100) {
            showToast(UIUtils.getStringRes(R.string.input_text_number_100));
            return;
        }
        presenter.sendComment(dynamicId, s, "");
        edInput.setText("");
    }

    public boolean certificationStatus() {
        boolean iscertificationStatus = false;
        int certificationStatus = UserInfoManager.getInstance().getCertificationStatus();
        if (certificationStatus == 3) {
            iscertificationStatus = true;
            AlearDialog alearDialog = new AlearDialog(AlearDialog.DialogType.Certification_publish_1002, this);
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
