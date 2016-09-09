package com.moonsister.tcjy.my.widget;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseFragment;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tcjy.viewholder.HreatViewholder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

/**
 * Created by x on 2016/8/22.
 */
public class HreatFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private HreatViewholder hreatViewholder;
    @Bind(R.id.gridView)//我页面中gridview
    GridView gridView;
    //gridview中的item数据与图片
    String[] images_text=new String[]{"我关注的","关注我的","动态管理","VIP充值","申请认证","兴趣修改","悬赏管理","约见管理","修改资料","财务中心","屏蔽手机联系人","   设置"};
    int[] images=new int[]{R.mipmap.mysee,R.mipmap.seemy,R.mipmap.makemessage,R.mipmap.vipmoney,R.mipmap.viprenzheng,R.mipmap.insert,R.mipmap.xuanshang,R.mipmap.yousee,R.mipmap.make,R.mipmap.money,R.mipmap.phone,R.mipmap.domake};
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return UIUtils.inflateLayout(R.layout.my_zhuye);//加载主页
    }

    @Override
    protected void initData() {
        List<Map<String,Object>> listItems = new ArrayList<Map<String, Object>>();
        //循环加载数据到gridview中
        for (int i=0;i<images_text.length;i++) {
            Map<String, Object> listItem = new HashMap<String, Object>();
            listItem.put("t", images[i]);
            //添加到List数组中
            listItem.put("m",images_text[i]);
            listItems.add(listItem);
            //设置SimpleAdapter属性
        }
        //利用simpleAdapter适配器适配数据
        SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(), listItems,
                R.layout.gridviewitem, new String[] { "t", "m" }, new int[] {
                R.id.im, R.id.textview});
            gridView.setAdapter(simpleAdapter);
        gridView.setOnItemClickListener(this);

//            gridView.setOnItemClickListener(dynamic_new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                }
//            });
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (images[i]){
            case R.mipmap.mysee://我关注的
                ActivityUtils.startFollowActivity();
                break;
            case R.mipmap.seemy://关注我的
                ActivityUtils.startFollowActivity();
                break;
            case R.mipmap.makemessage:
                ActivityUtils.startMakeMessageActivity();
                break;
            case R.mipmap.vipmoney://VIP充值
                ActivityUtils.startBuyVipActivity();
                break;
        }

    }
}
