package com.moonsister.tcjy.home.widget;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hickey.network.bean.DynamicItemBean;
import com.hickey.tool.base.BaseFragment;
import com.hickey.tool.constant.EnumConstant;
import com.hickey.tool.lang.StringUtis;
import com.hickey.tool.widget.XListView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.moonsister.tcjy.CacheManager;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.adapter.SearchReasonFragmentAdapter;
import com.moonsister.tcjy.home.presenetr.SearchReasonFragmentPersenter;
import com.moonsister.tcjy.home.presenetr.SearchReasonFragmentPersenterImpl;
import com.moonsister.tcjy.home.view.SearchReasonFragmentView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by jb on 2016/8/28.
 */
public class SearchReasonFragment extends BaseFragment implements SearchReasonFragmentView {
    @Bind(R.id.xlv)
    XListView xlv;
    @Bind(R.id.tv_all)
    TextView tvAll;
    @Bind(R.id.view_all_line)
    View viewAllLine;
    @Bind(R.id.tv_type_user)
    TextView tvTypeUser;
    @Bind(R.id.view_user_line)
    View viewUserLine;
    @Bind(R.id.tv_type_dynamic)
    TextView tvTypeDynamic;
    @Bind(R.id.view_dynamic_line)
    View viewDynamicLine;
    private SearchReasonFragmentPersenter persenter;
    private SearchReasonFragmentAdapter adapter;
    private String key;
    private boolean isLoadMore = false;
    private EnumConstant.SearchType searchType = EnumConstant.SearchType.all;

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
                persenter.loadSearchReason(key, isLoadMore, searchType);
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
        searchType = EnumConstant.SearchType.all;
        isLoadMore = false;

        persenter.loadSearchReason(key, isLoadMore, searchType);
        this.key = key;
        selectColor(R.id.rl_all);
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

    @OnClick({R.id.rl_all, R.id.rl_user, R.id.rl_dynamic})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_all:
                searchType = EnumConstant.SearchType.all;
                isLoadMore=false;
                persenter.loadSearchReason(key, isLoadMore, searchType);
                break;
            case R.id.rl_user:
                isLoadMore=false;
                searchType = EnumConstant.SearchType.user;
                persenter.loadSearchReason(key, isLoadMore, searchType);
                break;
            case R.id.rl_dynamic:
                isLoadMore=false;
                searchType = EnumConstant.SearchType.dynamic;
                persenter.loadSearchReason(key, isLoadMore, searchType);
                break;
        }
        selectColor(view.getId());
    }

    private void selectColor(@IdRes int id) {
        int yellow = getResources().getColor(R.color.yellow_ff8201);
        int transparent = getResources().getColor(R.color.transparent);
        tvAll.setSelected(id == R.id.rl_all);
        tvTypeUser.setSelected(id == R.id.rl_user);
        tvTypeDynamic.setSelected(id == R.id.rl_dynamic);

        viewAllLine.setBackgroundColor((id == R.id.rl_all) ? yellow : transparent);
        viewUserLine.setBackgroundColor((id == R.id.rl_user) ? yellow : transparent);
        viewDynamicLine.setBackgroundColor((id == R.id.rl_dynamic) ? yellow : transparent);


    }
}
