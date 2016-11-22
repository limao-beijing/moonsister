package com.moonsister.tcjy.home.widget;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.moonsister.tcjy.CacheManager;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseAdapter;
import com.moonsister.tcjy.base.BaseFragment;
import com.moonsister.tcjy.home.presenetr.SearchContentFragmentPersenter;
import com.moonsister.tcjy.home.presenetr.SearchContentFragmentPersenterImpl;
import com.moonsister.tcjy.home.view.SearchContentFragmentView;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tcjy.widget.FlowLayout;

import java.util.List;

import butterknife.Bind;

/**
 * Created by jb on 2016/8/26.
 */
public class SearchContentFragment extends BaseFragment implements SearchContentFragmentView {
    @Bind(R.id.lv)
    ListView lv;
    @Bind(R.id.fl_hot_key)
    FlowLayout flHotKey;
    private SearchContentFragmentPersenter persenter;
    private SearchContentFragmentListtener listtener;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        persenter = new SearchContentFragmentPersenterImpl();
        persenter.attachView(this);
        return inflater.inflate(R.layout.fragment_search_content, container, false);
    }

    @Override
    protected void initData() {
        persenter.loadChooseKey();
    }

    @Override
    public void onStart() {
        super.onStart();
        initHostorySearch();
    }

    private void initHostorySearch() {
        if (CacheManager.isExist4DataCache(getContext(), CacheManager.CachePath.SEARCH_KEY_HISTPRY)) {
            List<String> keys = CacheManager.readObject(getContext(), CacheManager.CachePath.SEARCH_KEY_HISTPRY);
            if (lv != null) {
                KeyMateAdapter keyMateAdapter = new KeyMateAdapter();
                keyMateAdapter.addCollection(keys);
                lv.setAdapter(keyMateAdapter);
            }
        }

    }

    public static SearchContentFragment newInstance() {
        return new SearchContentFragment();
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
    public void setChooseKeys(List<String> datas) {
        if (datas == null || datas.size() == 0)
            return;
        for (String s : datas) {
            TextView tv = (TextView) UIUtils.inflateLayout(
                    R.layout.bg_search_key, flHotKey);
            tv.setText(s);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    ActivityUtils.startSearchReasonActivity(tv.getText().toString());
                    if (listtener != null) {
                        listtener.onClickKey(tv.getText().toString());
                    }
                }
            });
            flHotKey.addView(tv);
        }

    }


    public class KeyMateAdapter extends BaseAdapter<String> {


        @Override
        protected View newView(Context context, int position, ViewGroup parent) {
            TextView view = new TextView(context);
//            view.setTextSize(context.getResources().getDimension(R.dimen.text_size_13));
            view.setTextColor(context.getResources().getColor(R.color.text_gray_778998));
            view.setPadding(30, 15, 30, 15);
            return view;
        }

        @Override
        protected void bindView(View view, int position, String s) {
            ((TextView) view).setText(s);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listtener != null)
                        listtener.onClickKey(s);
                }
            });
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }
    }

    public interface SearchContentFragmentListtener {
        void onClickKey(String key);
    }

    public void setSearchContentFragmentListtener(SearchContentFragmentListtener listtener) {
        this.listtener = listtener;
    }
}
