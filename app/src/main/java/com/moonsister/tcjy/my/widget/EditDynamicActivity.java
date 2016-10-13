package com.moonsister.tcjy.my.widget;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseFragmentActivity;
import com.moonsister.tcjy.main.widget.PersonThreeFragment;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.StringUtis;

/**
 * Created by jb on 2016/9/30.
 */

public class EditDynamicActivity extends BaseFragmentActivity {
    @Override
    protected Fragment initFragment() {
        PersonThreeFragment fragment = new PersonThreeFragment();
//        {
//            @Override
//            protected View addHeaderView() {
//                return null;
//            }
//        };
        fragment.isHaseaddHeaderView(false);
        String type = getIntent().getStringExtra("type");
        fragment.setType(type);
        return fragment;
    }

    @Override
    protected String initTitleName() {
        String type = getIntent().getStringExtra("type");
        if (StringUtis.equals(type, PersonThreeFragment.TYPE_PIC)) {
            type = getString(R.string.pic);
        } else if (StringUtis.equals(type, PersonThreeFragment.TYPE_VIDEO)) {
            type = getString(R.string.video);
        } else if (StringUtis.equals(type, PersonThreeFragment.TYPE_VOICE)) {
            type = getString(R.string.voice);
        }
        TextView tv_title_right = (TextView) titleView.findViewById(R.id.tv_title_right);
        tv_title_right.setText(getString(R.string.add_res));
        tv_title_right.setVisibility(View.VISIBLE);
        tv_title_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.startDynamicResAddActivity(getIntent().getStringExtra("type"));
            }
        });
        return type;
    }
}
