package com.moonsister.tcjy.my.widget;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.adapter.PingbiAdapter;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.base.BaseAdapter;
import com.moonsister.tcjy.bean.PingbiBean;
import com.moonsister.tcjy.my.persenter.PingbiActivityPersenter;
import com.moonsister.tcjy.my.persenter.PingbiActivityPersenterImpl;
import com.moonsister.tcjy.my.view.PingbiView;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tcjy.widget.XListView;

import java.util.List;

import butterknife.Bind;

/**
 * Created by x on 2016/9/14.
 */
public class PingbiActivity extends BaseActivity implements PingbiView{
    PingbiActivityPersenter persenter;
    @Bind(R.id.pingbilist)
    XListView pingbilist;
    PingbiAdapter adapter;
    @Bind(R.id.image_back)
    ImageView image_back;
    @Override
    protected View setRootContentView() {
        return UIUtils.inflateLayout(R.layout.pingbiactivity);
    }

    @Override
    protected void initView() {
        String page="1";
        persenter=new PingbiActivityPersenterImpl();
        persenter.attachView(this);
        pingbilist.setVerticalLinearLayoutManager();
        image_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PingbiActivity.this.finish();
            }
        });
        pingbilist.setPullRefreshEnabled(false);
        pingbilist.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {
                persenter.submit(page);
            }
        });
        persenter.submit(page);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void transfePageMsg(String msg) {
        showToast(msg);
    }

    @Override
    public void success(PingbiBean pingbiBean) {
//        List<PingbiBean.DataBean> data = pingbiBean.getData();
        if (pingbiBean == null) {
//            xlv.setNoMore();
            pingbilist.loadMoreComplete();
            pingbilist.refreshComplete();
            return;
        }
        if (pingbiBean.getData() == null || pingbiBean.getData().size() == 0) {
//            xlv.setNoMore();
            pingbilist.loadMoreComplete();
            pingbilist.refreshComplete();
            return;
        }
        if (adapter == null) {
            adapter = new PingbiAdapter(pingbiBean.getData(), this);
//            adapter.setPageType(type);
            if (pingbilist != null)
                pingbilist.setAdapter(adapter);
        } else {
            adapter.addListData(pingbiBean.getData());
            adapter.onRefresh();
        }
        pingbilist.loadMoreComplete();
        pingbilist.refreshComplete();

    }
}
