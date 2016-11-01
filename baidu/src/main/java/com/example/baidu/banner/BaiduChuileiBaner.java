package com.example.baidu.banner;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import com.baidu.mobad.chuilei.BaiduChuilei;
import com.baidu.mobad.chuilei.BaiduChuileiErrorCode;
import com.baidu.mobad.chuilei.BaiduChuileiRequestParameters;
import com.baidu.mobad.chuilei.BaiduChuileiResponse;
import com.moonsister.baidu.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jb on 2016/9/26.
 */

public class BaiduChuileiBaner {
    private List<BaiduChuileiResponse> responses;
    private GridView gridview;
    private static String AD_PLACE_ID = "";

    public void showBaner(final Activity activity, ViewGroup viewGroup) {
        /**
         * Step 1. 创建BaiduNative对象，参数分别为： 上下文context，广告位ID, BaiduNativeNetworkListener监听（监听广告请求的成功与失败）
         * 注意：请将YOUR_AD_PALCE_ID替换为自己的广告位ID
         */
        BaiduChuilei baidu = new BaiduChuilei(activity, AD_PLACE_ID, new BaiduChuilei.BaiduChuileiNetworkListener() {

            @Override
            public void onChuileiFail(BaiduChuileiErrorCode arg0) {
                Log.w("ChuileiActivity", "onNativeFail reason:" + arg0.name());
            }

            @Override
            public void onChuileiLoad(List<BaiduChuileiResponse> arg0) {
                if (arg0.size() > 0) {
                    updateView(arg0, activity);
                    responses = arg0;
                }
            }
        });
        baidu.setAppSid(activity, "e866cfb0");

        /**
         * Step 2. 创建requestParameters对象，并将其传给baidu.makeRequest来请求广告
         */
        BaiduChuileiRequestParameters requestParameters =
                new BaiduChuileiRequestParameters();
        baidu.makeRequest(requestParameters);
    }

    public void updateView(final List<BaiduChuileiResponse> nativeResponses, Activity activity) {
        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> map = null;

        for (int i = 0; i < nativeResponses.size(); i++) {
            BaiduChuileiResponse response = nativeResponses.get(i);
            map = new HashMap<String, Object>();
            map.put("appicon", getBitmap(response.getImageUrl()));
            map.put("appname", response.getTitle());
            list.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(activity, list,
                R.layout.gridview_item_layout, new String[]{"appicon",
                "appname"}, new int[]{R.id.appicon, R.id.appname});
        adapter.setViewBinder(new SimpleAdapter.ViewBinder() {

            public boolean setViewValue(View view, Object data,
                                        String textRepresentation) {
                // 判断是否为我们要处理的对象
                if (view instanceof ImageView && data instanceof Bitmap) {
                    ImageView iv = (ImageView) view;
                    iv.setImageBitmap((Bitmap) data);
                    return true;
                } else {
                    return false;
                }
            }
        });

        gridview.setAdapter(adapter);
        // 发送展现日志
        for (int i = 0; i < nativeResponses.size(); i++) {
            BaiduChuileiResponse response = nativeResponses.get(i);
            response.recordImpression(gridview);
        }

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position,
                                    long arg3) {
                // 点击动作
                responses.get(position).handleClick(view);
            }
        });
    }

    public Bitmap getBitmap(String imageUrl) {
        Bitmap mBitmap = null;
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            InputStream is = conn.getInputStream();
            mBitmap = BitmapFactory.decodeStream(is);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mBitmap;
    }
}
