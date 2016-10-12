package com.moonsister.tcjy.main.widget;

import android.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.adapter.MyThreeFragmentAdapter;
import com.moonsister.tcjy.base.BaseListFragment;
import com.moonsister.tcjy.bean.MyThreeFragmentBean;
import com.moonsister.tcjy.bean.UserDetailBean;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.manager.UserInfoManager;
import com.moonsister.tcjy.my.persenter.MyThreeFragmentPresenter;
import com.moonsister.tcjy.my.persenter.MyThreeFragmentPresenterImpl;
import com.moonsister.tcjy.my.view.MyThreeFragmentView;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.StringUtis;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tcjy.viewholder.PersonThreeFragmentHeaderViewHoder;
import com.trello.rxlifecycle.FragmentEvent;

import java.util.List;

/**
 * Created by jb on 2016/9/22.
 */
public class PersonThreeFragment extends BaseListFragment<MyThreeFragmentAdapter, MyThreeFragmentBean.DataBean> implements MyThreeFragmentView {
    public PersonThreeFragmentHeaderViewHoder mHeaderViewHoder;
    private MyThreeFragmentPresenter presenter;
    public static final String TYPE_PIC = "1";
    public static final String TYPE_VIDEO = "2";
    public static final String TYPE_VOICE = "3";
    private String type = TYPE_PIC;
    private String uid;
    private boolean isHaseAddHeaderView = true;

    @Override
    public MyThreeFragmentAdapter setAdapter() {
        mXListView.setVerticalGridLayoutManager(3);
        RxBus.with(this)
                .setEndEvent(FragmentEvent.DESTROY)
                .setEvent(Events.EventEnum.MyThreeFragment_level)
                .onNext(events -> {
                    showNotLevel();
                })
                .create();
        RxBus.with(this)
                .setEndEvent(FragmentEvent.DESTROY)
                .setEvent(Events.EventEnum.DynamicResAddActivity_up_success)
                .onNext(events -> {
                    if (mXListView != null)
                        mXListView.setRefreshing(true);
                })
                .create();

        return new MyThreeFragmentAdapter(null);
    }

    @Override
    public int getDecorationSize() {
        return (int) getResources().getDimension(R.dimen.x6);
    }

    @Override
    protected void onRefresh() {
        presenter.loadData(uid, page, type);
    }

    @Override
    protected void onLoadMore() {
        presenter.loadData(uid, page, type);
    }

    @Override
    protected void initChildData() {
        presenter = new MyThreeFragmentPresenterImpl();
        presenter.attachView(this);
        uid = getActivity().getIntent().getStringExtra("id");
        if (mHeaderViewHoder != null)
            presenter.loadHeaderData(uid);
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    protected View addHeaderView() {
        if (!isHaseAddHeaderView) {
            return null;
        }
        mHeaderViewHoder = new PersonThreeFragmentHeaderViewHoder() {
            @Override
            public void onClick(View view) {
                super.onClick(view);
                switch (view.getId()) {
                    case R.id.rl_all:
                        type = TYPE_PIC;
                        break;
                    case R.id.rl_user:
                        type = TYPE_VIDEO;
                        break;
                    case R.id.rl_dynamic:
                        type = TYPE_VOICE;
                        break;
                }

                selectColor(view.getId());
                if (mXListView != null) {
                    mXListView.setRefreshing(true);
                }
            }
        };
        mHeaderViewHoder.selectColor(R.id.rl_all);

        return mHeaderViewHoder.getContentView();
    }

    public void isHaseaddHeaderView(boolean isHaseAddHeaderView) {
        this.isHaseAddHeaderView = isHaseAddHeaderView;
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

    @Override
    public void setData(List<MyThreeFragmentBean.DataBean> data) {

        addData(data);
    }

    @Override
    public void setHeaderData(UserDetailBean bean) {

        bean.getData().getBaseinfo().setUid(uid);
        mHeaderViewHoder.refreshView(bean);
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
                            ActivityUtils.startRenzhengThreeActivity();
                        }
                        myDialog.dismiss();
                    }
                });


    }
}
