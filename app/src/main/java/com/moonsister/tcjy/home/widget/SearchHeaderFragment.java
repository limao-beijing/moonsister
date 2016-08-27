package com.moonsister.tcjy.home.widget;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jb on 2016/8/26.
 */
public class SearchHeaderFragment extends BaseFragment implements TextWatcher {
    @Bind(R.id.tv_cancel)
    TextView tvCancel;
    @Bind(R.id.et_content)
    EditText etContent;
    private onSearchHeaderFragmentListener listener;

    public static SearchHeaderFragment newInstance() {
        return new SearchHeaderFragment();
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_head, container, false);
    }

    @Override
    protected void initData() {
        etContent.addTextChangedListener(this);
    }


    @OnClick({R.id.tv_cancel, R.id.iv_search_cancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                getActivity().finish();
                break;
            case R.id.iv_search_cancel:
                etContent.setText("");
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (listener != null) {
            listener.onChange(s.toString());
        }
    }

    /**
     * 文字改变
     */
    public interface onSearchHeaderFragmentListener {
        void onChange(String key);
    }

    public void setSearchHeaderFragmentListener(onSearchHeaderFragmentListener listener) {
        this.listener = listener;
    }
}
