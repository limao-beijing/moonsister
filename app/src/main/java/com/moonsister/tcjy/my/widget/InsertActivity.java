package com.moonsister.tcjy.my.widget;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.bean.InsertBaen;
import com.moonsister.tcjy.my.persenter.InsertActivityPersenter;
import com.moonsister.tcjy.my.persenter.InsertActivityPersenterImpl;
import com.moonsister.tcjy.my.view.InsertActivityView;
import com.moonsister.tcjy.utils.UIUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

/**
 * Created by x on 2016/8/25.
 */
public class InsertActivity extends BaseActivity implements AdapterView.OnItemClickListener, View.OnClickListener, InsertActivityView {
    @Bind(R.id.button_text)//重设
    Button button;
    @Bind(R.id.button1_text)//已选好
    Button button1;
    @Bind(R.id.insertgridview)
    GridView gridView;
//    @Bind(R.id.insert_checkbox)
//    CheckBox checkBox;
    String[] images_text=new String[]{"女神","附近异性","奇葩","走心","走肾","夫妻那些事","惊世才艺","网红秀场","笑死人","黑技巧","现场大事件","冷知识"};
    InsertActivityPersenter persenter;
    int tagid;
    String tagname;
    int img;
    @Override
    protected View setRootContentView() {
//        UIUtils.inflateLayout(R.layout.insertgridviewitem);
        return UIUtils.inflateLayout(R.layout.insertactivity);//加载主页;
    }

    @Override
    protected void initView() {
        persenter=new InsertActivityPersenterImpl();
        persenter.attachView(this);
        persenter.LoadData(tagid,tagname,img);
        List<Map<String,Object>> listItems = new ArrayList<Map<String, Object>>();
        //循环加载数据到gridview中
        for (int i=0;i<images_text.length;i++) {
            Map<String, Object> listItem = new HashMap<String, Object>();
            listItem.put("t", images_text[i]);

            //添加到List数组中
            listItems.add(listItem);
            //设置SimpleAdapter属性
        }
        //利用simpleAdapter适配器适配数据
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems,
                R.layout.insertgridviewitem, new String[] { "t" }, new int[] {
                R.id.insert_text, });
        gridView.setAdapter(simpleAdapter);
        gridView.setOnItemClickListener(this);
        button.setOnClickListener(this);
        button1.setOnClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_text:

                break;
            case R.id.button1_text:

                break;
        }
    }

    @Override
    public void success() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void transfePageMsg(String msg) {
        transfePageMsg(msg);
    }
}
