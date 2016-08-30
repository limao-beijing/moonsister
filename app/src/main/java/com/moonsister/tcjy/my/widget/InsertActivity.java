package com.moonsister.tcjy.my.widget;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.adapter.InsertAdapter;
import com.moonsister.tcjy.base.BaseActivity;
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

import java.util.List;

import butterknife.Bind;

/**
 * Created by x on 2016/8/25.
 */
public class InsertActivity extends BaseActivity implements View.OnClickListener, InsertActivityView {
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
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                p=data.get(i).getTagid();

            }
        });
        button.setOnClickListener(this);
        button1.setOnClickListener(this);
//        List<Integer> list=new ArrayList<Integer>();
//        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                int p=data
//            }
//        });
        }
        //利用simpleAdapter适配器适配数据
//        SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems,
//                R.layout.insertgridviewitem, new String[] { "t" }, new int[] {
//                R.id.insert_text, });

//        gridView.setAdapter(simpleAdapter);
//

//    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_text:

                break;
            case R.id.button1_text:
                Intent intent=new Intent(InsertActivity.this,RegActivity.class);
                startActivity(intent);
                break;
            case R.id.image_back:
                InsertActivity.this.finish();
                break;
        }
    }

    private void setSwicthCardRxbus() {
        RxBus.with(this)
                .setEndEvent(ActivityEvent.DESTROY)
                .setEvent(Events.EventEnum.CLICK_SWITCH_CARD_POSITION)
                .onNext(events -> {
                    if (events != null && events.message instanceof InsertBaen) {
                        setBasicInfo((InsertBaen) events.message);
                    }
                })
                .create();

    }
    @Override
    public void setBasicInfo(InsertBaen getInsertBean) {

         data = getInsertBean.getData();

        adapter=new InsertAdapter(this,data);
        gridView.setAdapter(adapter);

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

    }
}
