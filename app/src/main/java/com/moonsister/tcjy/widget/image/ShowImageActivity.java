package com.moonsister.tcjy.widget.image;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.TextView;

import com.hickey.tool.base.BaseActivity;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by jb on 2016/6/23.
 */
public class ShowImageActivity extends BaseActivity {

    @Bind(R.id.tv_title_right)
    TextView tvTitleRight;
    private GridView mGridView;
    private List<String> list;
    private ArrayList<String> select_list;
    private ChildAdapter adapter;
    private CheckBox mCheckBox;
    private Intent showPicIntent;

    @Override
    protected String initTitleName() {
        return getResources().getString(R.string.photo_album);
    }

    @Override
    protected View setRootContentView() {
        View view = View.inflate(ShowImageActivity.this,
                R.layout.show_image_activity, null);

        showPicIntent = getIntent();
        select_list = new ArrayList<String>();
        return view;
    }

    @Override
    protected void initView() {
        tvTitleRight.setText(getResources().getString(R.string.finish));
        mGridView = (GridView) findViewById(R.id.child_grid);
        list = showPicIntent.getStringArrayListExtra("data");
        adapter = new ChildAdapter(this, list, mGridView);
        mGridView.setAdapter(adapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                mCheckBox = (CheckBox) view.findViewById(R.id.child_checkbox);
                mCheckBox.setChecked(!mCheckBox.isChecked());
            }
        });
    }


    @OnClick(R.id.tv_title_right)
    public void onClick() {
        for (int i = 0; i < adapter.getSelectItems().size(); i++) {
            select_list.add(list.get(adapter.getSelectItems().get(i)));
        }
        if (select_list.size() > 9) {
            showToast("一次最多只能选择9张图片");
            select_list.clear();
        } else {
            tvTitleRight.setClickable(false);
            tvTitleRight.setFocusable(false);
            RxBus.getInstance().send(Events.EventEnum.PIC_DESTROY, null);
            Events<List> event = new Events<List>();
            event.what = Events.EventEnum.GET_PHOTO_LIST;
            event.message = select_list;
            RxBus.getInstance().send(event);
            finish();
        }
    }

}

