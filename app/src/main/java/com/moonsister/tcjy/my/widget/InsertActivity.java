package com.moonsister.tcjy.my.widget;

import android.content.Intent;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.adapter.InsertAdapter;
import com.moonsister.tcjy.base.BaseActivity;
import com.moonsister.tcjy.bean.BackInsertBean;
import com.moonsister.tcjy.bean.InsertBaen;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.login.widget.RegActivity;
import com.moonsister.tcjy.my.persenter.InsertActivityPersenter;
import com.moonsister.tcjy.my.persenter.InsertActivityPersenterImpl;
import com.moonsister.tcjy.my.view.InsertActivityView;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.UIUtils;
import com.trello.rxlifecycle.ActivityEvent;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by x on 2016/8/25.
 */
public class InsertActivity extends BaseActivity implements InsertActivityView {
    @Bind(R.id.button_text)//重设
    Button button;
    @Bind(R.id.button1_text)//已选好
    Button button1;
    @Bind(R.id.insertgridview)
    GridView gridView;
    @Bind(R.id.image_back)
    ImageView image_back;
//    String[] images_text=new String[]{"女神","附近异性","奇葩","走心","走肾","夫妻那些事","惊世才艺","网红秀场","笑死人","黑技巧","现场大事件","冷知识"};
    InsertActivityPersenter persenter;
    int tagid;
    String tagname;
    int img;
    InsertAdapter adapter;
    List<InsertBaen.DataBean> data;
    int p;
    String tlist;
    List<Integer> list=new ArrayList<Integer>();
    StringBuffer sbr=new StringBuffer();
    int a;
    String str;
    String uu;
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
//        persenter.sendData(tlist);

    }


    @OnClick({R.id.button_text,R.id.button1_text})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_text://重置
                for (int i = 0; i < data.size(); i++) {

                    InsertBaen.DataBean bean = data.get(i);
                    bean.setIscheck(false);

                }
                adapter.notifyDataSetChanged();
                break;
            case R.id.button1_text://已选好

                    for (int i = 0; i < data.size(); i++) {
                        if (data != null && data.get(i).ischeck()) {//判断为true的选项
//                        String v = String.valueOf(data.get(i).getTagid());
                            String v=Integer.toString(data.get(i).getTagid(), 16);//将int类型转换为string类型
                            if(i!=data.size()-1){
                                sbr.append(v+",");//拼接字符串
                            }

                            sbr.append(v );
                        }

                    }

                    str=sbr.toString();
                    persenter.sendData(str);//发送请求




                break;
            case R.id.image_back:
                    this.finish();


                break;
        }
    }


    @Override
    public void setBasicInfo(InsertBaen getInsertBean) {

         data = getInsertBean.getData();

        adapter=new InsertAdapter(this,data);
        gridView.setAdapter(adapter);
//        View view= LayoutInflater.from(this).inflate(R.layout.insertgridviewitem,null);
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                ImageView insert_image= (ImageView) view.findViewById(R.id.insert_image);
//                TextView text_box= (TextView) view.findViewById(R.id.insert_checkbox);
//                insert_image.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
////                        if(data.get(i).ischeck()) {
//                            text_box.setBackgroundResource(R.mipmap.checkbox);
////                        }
//                    }
//                });
            }
//        });

//    }

    @Override
    public void success() {
        uu=getIntent().getStringExtra("my");
        if(uu==null){
            Intent intent=new Intent(InsertActivity.this,RegActivity.class);
            startActivity(intent);
            this.finish();

        }else{
            this.finish();
        }


    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {
    }

    @Override
    public void transfePageMsg(String msg) {
        showToast(msg);
    }
}
