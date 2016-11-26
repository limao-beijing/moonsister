package com.moonsister.tcjy.home.widget;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.hickey.tool.base.BaseFragment;
import com.hickey.tool.lang.StringUtis;
import com.moonsister.tcjy.R;

import butterknife.Bind;
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
                if (StringUtis.equals(tvCancel.getText().toString(), getString(R.string.cancel)))
                    getActivity().finish();
                else {
                    if (listener != null) {
                        listener.search(etContent.getText().toString());
                    }
                }
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
        if (StringUtis.isEmpty(s.toString())) {
            tvCancel.setText(getText(R.string.cancel));
        } else {
            tvCancel.setText(getString(R.string.search));
        }
        if (listener != null) {
            listener.onChange(s.toString());
        }
        etContent.setSelection(s.length());
    }

    public void setEditTextText(String key) {
        if (etContent != null)
            etContent.setText(key);
    }

    /**
     * 文字改变
     */
    public interface onSearchHeaderFragmentListener {
        void onChange(String key);

        void search(String key);
    }

    public void setSearchHeaderFragmentListener(onSearchHeaderFragmentListener listener) {
        this.listener = listener;
    }
}
