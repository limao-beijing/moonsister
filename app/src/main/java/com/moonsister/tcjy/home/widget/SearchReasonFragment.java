package com.moonsister.tcjy.home.widget;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.moonsister.tcjy.CacheManager;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.adapter.SearchReasonFragmentAdapter;
import com.moonsister.tcjy.base.BaseFragment;
import com.moonsister.tcjy.bean.DynamicBean;
import com.moonsister.tcjy.home.presenetr.SearchReasonFragmentPersenter;
import com.moonsister.tcjy.home.presenetr.SearchReasonFragmentPersenterImpl;
import com.moonsister.tcjy.home.view.SearchReasonFragmentView;
import com.moonsister.tcjy.utils.EnumConstant;
import com.moonsister.tcjy.widget.XListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by jb on 2016/8/28.
 */
public class SearchReasonFragment extends BaseFragment implements SearchReasonFragmentView {
    @Bind(R.id.xlv)
    XListView xlv;
    private SearchReasonFragmentPersenter persenter;
    private SearchReasonFragmentAdapter adapter;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        persenter = new SearchReasonFragmentPersenterImpl();
        persenter.attachView(this);
        return inflater.inflate(R.layout.fragment_search_reason, container, false);
    }

    @Override
    protected void initData() {
        xlv.setVerticalLinearLayoutManager();
        xlv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {

            }
        });
    }

    public static SearchReasonFragment newInstance() {
        return new SearchReasonFragment();
    }

    public void loadSearch(String key) {
        if (CacheManager.isExist4DataCache(getContext(), CacheManager.CachePath.SEARCH_KEY_HISTPRY)) {
            ArrayList<String> keys = CacheManager.readObject(getContext(), CacheManager.CachePath.SEARCH_KEY_HISTPRY);
            if (keys == null) {
                keys = new ArrayList<String>();
                keys.add(key);
            } else {
                if (keys.contains(key)) {
                    keys.remove(key);
                    keys.add(0, key);
                } else {
                    if (keys.size() >= 6) {
                        for (int i = keys.size() - 1; i >= 5; i--) {
                            keys.remove(i);
                        }
                    }
                }
                keys.add(0, key);
            }
            CacheManager.saveObject(getContext(), keys, CacheManager.CachePath.SEARCH_KEY_HISTPRY);
        } else {
            ArrayList<String> keys = new ArrayList<>();
            keys.add(key);
            CacheManager.saveObject(getContext(), keys, CacheManager.CachePath.SEARCH_KEY_HISTPRY);
        }
        persenter.loadSearchReason(key, EnumConstant.SearchType.user);

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
        transfePageMsg(msg);
    }

    @Override
    public void setLoadResult(List<DynamicBean.DataBean.ListBean> data) {

    }


   /* @Override
    public void setLoadResult(List<DynamicBean.DataBean.ListBean> data) {

        if (xlv != null) {
            if (adapter == null) {
                adapter = new SearchReasonFragmentAdapter(data);
                xlv.setAdapter(adapter);
            } else {
                adapter.clean();
                adapter.addListData(data);
                adapter.onRefresh();
            }
        }

    }*/

}
