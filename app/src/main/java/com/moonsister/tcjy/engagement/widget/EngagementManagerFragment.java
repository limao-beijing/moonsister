package com.moonsister.tcjy.engagement.widget;

import android.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;

import com.hickey.network.bean.EngagementManagerBean;
import com.hickey.tool.base.BaseListFragment;
import com.hickey.tool.constant.EnumConstant;
import com.hickey.tool.lang.StringUtis;
import com.hickey.tool.widget.UIUtils;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.adapter.EngagementManagerAdapter;
import com.moonsister.tcjy.dialogFragment.DialogMannager;
import com.hickey.tool.base.BaseDialogFragment;
import com.moonsister.tcjy.dialogFragment.widget.ImPermissionDialog;
import com.moonsister.tcjy.engagement.presenter.EngagementActionPersenterImpl;
import com.moonsister.tcjy.engagement.presenter.EngagementManagerFragmentPresenter;
import com.moonsister.tcjy.engagement.presenter.EngagementManagerFragmentPresenterImpl;
import com.moonsister.tcjy.engagement.view.EngagementActionView;
import com.moonsister.tcjy.engagement.view.EngagementManagerFragmentView;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.utils.ActivityUtils;

import java.util.List;


/**
 * Created by jb on 2016/9/28.
 */
public class EngagementManagerFragment extends BaseListFragment<EngagementManagerAdapter, EngagementManagerBean.DataBean> implements EngagementManagerFragmentView, EngagementActionView {
    private EnumConstant.ManagerType mType;
    private EngagementManagerFragmentPresenter presenter;
    private EngagementActionPersenterImpl actionPersenter;

    public static EngagementManagerFragment newInstance() {

        return new EngagementManagerFragment();
    }


    @Override
    protected void initChildData() {
        presenter = new EngagementManagerFragmentPresenterImpl();
        presenter.attachView(this);
        actionPersenter = new EngagementActionPersenterImpl();
        actionPersenter.attachView(this);
        if (mAdapter != null)
            mAdapter.setBaseView(this);
    }


    private void showNotLevel() {
        AlertDialog myDialog = new AlertDialog.Builder(getActivity()).create();
        myDialog.show();
        View view = UIUtils.inflateLayout(R.layout.dialog_show_notlevel);
        String sex = UserInfoManager.getInstance().getUserSex();
        if (!StringUtis.equals(sex, "1")) {
            ((ImageView) view.findViewById(R.id.iv_bg)).setImageResource(R.mipmap.bg_renzheng);
        }
        myDialog.getWindow().setContentView(view);
        view.findViewById(R.id.iv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        view.findViewById(R.id.view_que)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String sex = UserInfoManager.getInstance().getUserSex();
                        if (StringUtis.equals(sex, "1")) {
                            ActivityUtils.startBuyVipActivity();
                        } else {
                            ActivityUtils.startRenZhengThreeActivity();
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
    public void onRefresh() {
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
            dataBean.setPresenetr(actionPersenter);
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

//    @Override
//    public void submitInviteSuccess(StatusBean mbean) {
//        if (StringUtis.equals(mbean.getCode(), "10")) {
//            showNotLevel();
//        } else if (StringUtis.equals(mbean.getCode(), "11")) {
//            showNotLevel();
//        } else if (StringUtis.equals(mbean.getCode(), "1")) {
//            if (mAdapter != null) {
//                List<EngagementManagerBean.DataBean> datas = mAdapter.getDatas();
//                if (datas != null) {
//                    for (EngagementManagerBean.DataBean bean : datas) {
//                        if (StringUtis.equals(bean.getId(), mbean.getData().getDating_id())) {
//                            bean.setStatus(2);
//                            mAdapter.onRefresh();
//                            break;
//                        }
//                    }
//                }
//            }
//        } else {
//            showToast(mbean.getMsg());
//        }
//
//    }

    public void setType(EnumConstant.ManagerType type) {
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


    @Override
    public void actionSuccess() {
        if (presenter != null) {
            page = 1;
            presenter.loadData(mType, page);
        }
    }

    @Override
    public void showDelectDialog(EngagementManagerBean.DataBean bean, int position) {
        DialogMannager.getInstance().showEngaggementDialogFragment(getFragmentManager(), new ImPermissionDialog.OnCallBack() {
            @Override
            public void onStatus(BaseDialogFragment dialogFragment, EnumConstant.DialogCallBack statusCode) {
                if (statusCode == EnumConstant.DialogCallBack.CONFIRM) {
                    if (actionPersenter != null) {
                        actionPersenter.actionEngagement(bean.getDating_id(), 6);
                        dialogFragment.dismissDialogFragment();
                    }
                } else if (statusCode == EnumConstant.DialogCallBack.CANCEL) {
                    dialogFragment.dismissDialogFragment();
                }
            }
        });
    }


}
