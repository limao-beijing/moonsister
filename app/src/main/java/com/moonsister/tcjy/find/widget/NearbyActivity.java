package com.moonsister.tcjy.find.widget;

import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hickey.network.bean.NearbyBean;
import com.hickey.tool.base.BaseActivity;
import com.hickey.tool.widget.UIUtils;
import com.hickey.tool.widget.XListView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.adapter.NearbyAdapter;
import com.moonsister.tcjy.find.presenter.NearbyActivityPresenter;
import com.moonsister.tcjy.find.presenter.NearbyActivityPresenterImpl;
import com.moonsister.tcjy.find.view.NearbyActivityView;

import java.util.List;

import butterknife.Bind;

/**
 * Created by jb on 2016/8/4.
 */
public class NearbyActivity extends BaseActivity implements NearbyActivityView, View.OnClickListener {
    @Bind(R.id.app_title_bar)
    RelativeLayout appTitleBar;
    @Bind(R.id.tv_title_right)
    TextView tvTitleRight;
    private NearbyActivityPresenter presenter;
    private NearbyAdapter nearbyAdapter;
    private boolean isRefresh;
    private String sex = "0";
    @Bind(R.id.xlv)
    XListView xlv;
    private View popupViewState;
    private PopupWindow popupWindowState;

    @Override
    protected View setRootContentView() {
        presenter = new NearbyActivityPresenterImpl();
        presenter.attachView(this);
        return UIUtils.inflateLayout(R.layout.activity_nearby);
    }

    @Override
    protected String initTitleName() {
        return UIUtils.getStringRes(R.string.nearby);
    }

    @Override
    protected void initView() {
        tvTitleRight.setText(UIUtils.getStringRes(R.string.filter));
        tvTitleRight.setVisibility(View.VISIBLE);
        tvTitleRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showStateWindow();
            }
        });
        xlv.setVerticalGridLayoutManager(3);
        xlv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                isRefresh = true;
                presenter.refresh(sex);
            }

            @Override
            public void onLoadMore() {
                isRefresh = false;
                presenter.loadMore(sex);
            }
        });
        xlv.setRefreshing(true);
    }

    @Override
    public void showLoading() {
        showProgressDialog();
    }

    @Override
    public void hideLoading() {
        hideProgressDialog();
        if (xlv != null) {
            xlv.refreshComplete();
            xlv.loadMoreComplete();
        }
    }

    @Override
    public void transfePageMsg(String msg) {
        showToast(msg);
    }

    @Override
    public void setData(List<NearbyBean.DataBean> data) {
        if (nearbyAdapter == null) {
            nearbyAdapter = new NearbyAdapter(data);
            xlv.setAdapter(nearbyAdapter);
        } else {
            if (isRefresh)
                nearbyAdapter.clean();
            nearbyAdapter.addListData(data);
            nearbyAdapter.onRefresh();
        }
    }

    private void showStateWindow() {
        if (popupViewState == null) {
            popupViewState = LayoutInflater.from(this).inflate(R.layout.popup_nearby, null);
            popupWindowState = new PopupWindow(popupViewState, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            popupWindowState.setFocusable(true);
            popupWindowState.setOutsideTouchable(true);
            popupWindowState.setBackgroundDrawable(new BitmapDrawable());
            RelativeLayout window = (RelativeLayout) popupViewState.findViewById(R.id.linear);
            popupViewState.findViewById(R.id.wacth_girl).setOnClickListener(this);
            popupViewState.findViewById(R.id.wacth_boy).setOnClickListener(this);
            popupViewState.findViewById(R.id.nearby_all).setOnClickListener(this);
        }
        if (popupWindowState.isShowing()) {
            popupWindowState.dismiss();
        } else {
            popupWindowState.showAsDropDown(appTitleBar);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.wacth_boy:
                showStateWindow();
                sex = "1";
                if (presenter != null)
                    presenter.refresh(sex);
                break;
            case R.id.wacth_girl:
                showStateWindow();
                sex = "2";
                if (presenter != null)
                    presenter.refresh(sex);
                break;
            case R.id.nearby_all:
                showStateWindow();
                sex = "0";
                if (presenter != null)
                    presenter.refresh(sex);
                break;
        }
    }
}
