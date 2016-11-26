package com.moonsister.tcjy.viewholder;

import android.view.View;
import android.widget.TextView;

import com.hickey.network.ImageServerApi;
import com.hickey.network.bean.GoodSelectBaen;
import com.hickey.tool.base.BaseRecyclerViewHolder;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.utils.ActivityUtils;
import com.moonsister.tcjy.widget.RoundedImageView;

import butterknife.Bind;


/**
 * Created by pc on 2016/6/4.
 */
public class GoodSelectViewHolder extends BaseRecyclerViewHolder<GoodSelectBaen.Data> {
    @Bind(R.id.tv_user_name)
    TextView tvUserName;
    @Bind(R.id.tv_address)
    TextView tvAddress;
    @Bind(R.id.tv_wacth_number)
    TextView tvWacthNumber;
    @Bind(R.id.tv_fen_number)
    TextView tvFenNumber;
    @Bind(R.id.rv_user_pic)
    RoundedImageView rvUserPic;

    public GoodSelectViewHolder(View view) {
        super(view);
    }

    @Override
    public void onBindData(GoodSelectBaen.Data goodSelectBaen) {
        if (goodSelectBaen == null)
            return;
        tvUserName.setText(goodSelectBaen.getNickname());
        tvAddress.setText(goodSelectBaen.getPlace());
        tvFenNumber.setText(goodSelectBaen.getFansnum());
        tvWacthNumber.setText(goodSelectBaen.getAnum());
        ImageServerApi.showURLImage(rvUserPic, goodSelectBaen.getFace());

    }

    @Override
    protected void onItemclick(View view, GoodSelectBaen.Data baen, int position) {
        ActivityUtils.startDynamicActivity(baen.getUserid());

    }

}
