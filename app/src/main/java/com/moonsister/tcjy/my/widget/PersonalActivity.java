package com.moonsister.tcjy.my.widget;

import android.view.View;
import android.widget.Adapter;
import android.widget.ListView;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.adapter.PersonalAdapter;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.utils.UIUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

/**
 * Created by x on 2016/8/25.
 */
public class PersonalActivity extends BaseActivity {
    String[] personal=new String[]{"头像","昵称","个性签名","性别","年龄","身高","体重","现居","职业","兴趣爱好"};
    @Bind(R.id.personal_list)
    ListView personal_list;
    PersonalAdapter peradapter;
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
//        peradapter=new

    }
}
