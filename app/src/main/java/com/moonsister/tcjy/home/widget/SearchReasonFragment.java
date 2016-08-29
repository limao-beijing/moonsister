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
import com.moonsister.tcjy.bean.DynamicItemBean;
import com.moonsister.tcjy.home.presenetr.SearchReasonFragmentPersenter;
import com.moonsister.tcjy.home.presenetr.SearchReasonFragmentPersenterImpl;
import com.moonsister.tcjy.home.view.SearchReasonFragmentView;
import com.moonsister.tcjy.utils.EnumConstant;
import com.moonsister.tcjy.utils.StringUtis;
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
    private String key;
    private boolean isLoadMore = false;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        persenter = new SearchReasonFragmentPersenterImpl();
        persenter.attachView(this);
        return inflater.inflate(R.layout.fragment_search_reason, container, false);
    }

    @Override
    protected void initData() {
        xlv.setVerticalLinearLayoutManager();
        xlv.setPullRefreshEnabled(false);
        xlv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {
                isLoadMore = true;
                persenter.loadSearchReason(key, isLoadMore, EnumConstant.SearchType.all);
            }
        });
    }

    public static SearchReasonFragment newInstance() {
        return new SearchReasonFragment();
    }

    public void loadSearch(String key) {
        if (StringUtis.isEmpty(key))
            return;
        if (CacheManager.isExist4DataCache(getContext(), CacheManager.CachePath.SEARCH_KEY_HISTPRY)) {
            ArrayList<String> keys = CacheManager.readObject(getContext(), CacheManager.CachePath.SEARCH_KEY_HISTPRY);
            if (keys == null) {
                keys = new ArrayList<String>();
                keys.add(key);
            } else {
                for (int i = 0; i < keys.size(); i++) {
                    if (i > 5) {
                        keys.remove(keys.get(i));
                    } else if (StringUtis.equals(key, keys.get(i))) {
                        keys.remove(keys.get(i));
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
        isLoadMore = false;
        persenter.loadSearchReason(key, isLoadMore, EnumConstant.SearchType.user);
        this.key = key;
    }

    @Override
    public void showLoading() {
        showProgressDialog();
    }

    @Override
    public void hideLoading() {
        hideProgressDialog();
        if (xlv != null) {
            xlv.loadMoreComplete();
            xlv.refreshComplete();
        }
    }

    @Override
    public void transfePageMsg(String msg) {
        showToast(msg);
    }


    @Override
    public void setLoadResult(List<DynamicItemBean> data) {

        if (xlv != null) {
            if (adapter == null) {
                adapter = new SearchReasonFragmentAdapter(data);
                xlv.setAdapter(adapter);
            } else {
                if (!isLoadMore)
                    adapter.clean();
                adapter.addListData(data);
                adapter.onRefresh();
            }

        }


    }

}
