package com.moonsister.tcjy.main.widget;

import android.view.View;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.adapter.MyThreeFragmentAdapter;
import com.moonsister.tcjy.base.BaseListFragment;
import com.moonsister.tcjy.bean.MyThreeFragmentBean;
import com.moonsister.tcjy.bean.UserDetailBean;
import com.moonsister.tcjy.my.persenter.MyThreeFragmentPresenter;
import com.moonsister.tcjy.my.persenter.MyThreeFragmentPresenterImpl;
import com.moonsister.tcjy.my.view.MyThreeFragmentView;
import com.moonsister.tcjy.viewholder.PersonThreeFragmentHeaderViewHoder;

import java.util.List;

/**
 * Created by jb on 2016/9/22.
 */
public class PersonThreeFragment extends BaseListFragment<MyThreeFragmentAdapter, MyThreeFragmentBean.DataBean> implements MyThreeFragmentView {
    private PersonThreeFragmentHeaderViewHoder mHeaderViewHoder;
    private MyThreeFragmentPresenter presenter;
    public static final String TYPE_PIC = "1";
    public static final String TYPE_VIDEO = "2";
    public static final String TYPE_VOICE = "3";
    private String type = TYPE_PIC;
    private String uid;

    @Override
    public MyThreeFragmentAdapter setAdapter() {
        mXListView.setVerticalGridLayoutManager(3);
        return new MyThreeFragmentAdapter(null);
    }

    @Override
    public int getDecorationSize() {
        return (int) getResources().getDimension(R.dimen.x6);
    }

    @Override
    protected void onRefresh() {
        if (mHeaderViewHoder != null)
            presenter.loadHeaderData(uid);
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

    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    protected View addHeaderView() {
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
}
