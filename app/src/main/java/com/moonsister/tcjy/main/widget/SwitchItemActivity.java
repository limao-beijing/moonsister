package com.moonsister.tcjy.main.widget;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by jb on 2016/8/9.
 */
public class SwitchItemActivity extends Activity implements View.OnClickListener {
    @Bind(R.id.ll_content)
    LinearLayout llContent;
    private HashMap<String, String> map;
    private String tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_item);

        ButterKnife.bind(this);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        map = (HashMap<String, String>) intent.getSerializableExtra("map");
        tag = intent.getStringExtra("tag");
        if (map == null || map.size() == 0) {
            finish();
            return;
        }
        initData();
    }


    private void initData() {
        int i = 0;
        for (String s : map.keySet()) {
            i++;
            TextView tv = new TextView(this);
            tv.setTextColor(getResources().getColor(R.color.color_ffd305));
            tv.setTextSize(getResources().getDimension(R.dimen.text_size_9));
            tv.setText(map.get(s));
            tv.setTag(s);
            llContent.addView(tv);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) tv.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            layoutParams.gravity = Gravity.CENTER;
            int dimension = (int) getResources().getDimension(R.dimen.y20);
            layoutParams.topMargin = dimension;
            layoutParams.bottomMargin = dimension;
            tv.setLayoutParams(layoutParams);
            tv.setOnClickListener(this);
            if (i != map.size()) {
                ImageView imageView = new ImageView(this);
                imageView.setBackgroundColor(getResources().getColor(R.color.color_2a343e));
                llContent.addView(imageView);
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) imageView.getLayoutParams();
                params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                params.height = (int) getResources().getDimension(R.dimen.x1);
            }

        }
    }

    @Override
    public void onClick(View v) {
        Events<Bundle> events = new Events<Bundle>();
        events.what = Events.EventEnum.SWITCH_ITEM;
        Bundle bundle = new Bundle();
        bundle.putString("tag", tag);
        bundle.putString("id", (String) v.getTag());
        bundle.putString("name", ((TextView) v).getText().toString());
        events.message = bundle;
        RxBus.getInstance().send(events);
        finish();
    }
}
