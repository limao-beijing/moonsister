package com.moonsister.tcjy.my.widget;

import android.media.Image;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.adapter.PersonalAdapter;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.utils.UIUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by x on 2016/8/25.
 */
public class PersonalActivity extends BaseActivity {
    String[] personal=new String[]{"头像","昵称","个性签名","性别","年龄","身高","体重","现居","职业","兴趣爱好"};
    @Bind(R.id.personal_list)
    ListView personal_list;
    PersonalAdapter peradapter;
    @Bind(R.id.image_back)
    ImageView image_back;
    @Override
    protected View setRootContentView() {
        return UIUtils.inflateLayout(R.layout.personalactivity);
    }

    @Override
    protected void initView() {
        List<Map<String,Object>> listItems = new ArrayList<Map<String, Object>>();
        for(int i=0;i<personal.length;i++){
            Map<String, Object> listItem = new HashMap<String, Object>();
            listItem.put("t", personal[i]);
            listItems.add(listItem);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems,
                R.layout.personallistviewitem, new String[] { "t" }, new int[] {
                R.id.text_name, });
        personal_list.setAdapter(simpleAdapter);

//        gridView.setAdapter(simpleAdapter);
//        peradapter=new

    }


    @OnClick(R.id.image_back)
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.image_back:
                PersonalActivity.this.finish();
                break;
        }
    }
}
