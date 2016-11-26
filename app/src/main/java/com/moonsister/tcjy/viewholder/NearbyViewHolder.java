package com.moonsister.tcjy.viewholder;

import android.view.View;
import android.widget.TextView;

import com.hickey.network.ImageServerApi;
import com.hickey.network.bean.NearbyBean;
import com.hickey.tool.ConfigUtils;
import com.hickey.tool.base.BaseRecyclerViewHolder;
import com.hickey.tool.file.PrefUtils;
import com.hickey.tool.lang.StringUtis;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.manager.GaodeManager;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.utils.LocationUtils;
import com.moonsister.tcjy.widget.RoundedImageView;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;

/**
 * Created by jb on 2016/8/4.
 */
public class NearbyViewHolder extends BaseRecyclerViewHolder<NearbyBean.DataBean> {
    @Bind(R.id.riv_user_image)
    RoundedImageView rivUserImage;
    @Bind(R.id.tv_location)
    TextView tvLocation;

    public NearbyViewHolder(View view) {
        super(view);
    }

    @Override
    public void onBindData(NearbyBean.DataBean dataBean) {
        if (dataBean == null)
            return;
        String location = PrefUtils.getString(ConfigUtils.getInstance().getApplicationContext(), GaodeManager.class.getName(), "");
        double latitude = 0;
        double longitude = 0;
        if (!StringUtis.isEmpty(location)) {
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(location);
                longitude = jsonObject.getDouble("longitude");
                latitude = jsonObject.getDouble("latitude");

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        ImageServerApi.showURLSamllImage(rivUserImage, dataBean.getFace());
        int v = (int) LocationUtils.calculateLineDistance(dataBean.getLng(), dataBean.getLat(), longitude, latitude);
        tvLocation.setText(v + "米");

    }

    @Override
    protected void onItemclick(View view, NearbyBean.DataBean dataBean, int position) {
        if (dataBean == null)
            return;
        ActivityUtils.startDynamicActivity(dataBean.getUid());


    }
}
