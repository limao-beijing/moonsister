package com.moonsister.tcjy.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hickey.network.ImageServerApi;
import com.hickey.network.bean.DefaultDataBean;
import com.hickey.network.bean.PingbiBean;
import com.hickey.tool.base.BaseIModel;
import com.hickey.tool.base.BaseRecyclerViewHolder;
import com.hickey.tool.lang.StringUtis;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.adapter.PingbiAdapter;
import com.moonsister.tcjy.main.model.UserActionModelImpl;

import butterknife.Bind;

/**
 * Created by x on 2016/9/14.
 */
public class PingbiViewHolder extends BaseRecyclerViewHolder<PingbiBean.DataBean> {
    private PingbiAdapter adapter;
    @Bind(R.id.riv_user_image)//头像
    ImageView riv_user_image;
    @Bind(R.id.tv_user_name)//用户名
    TextView tv_user_name;
    @Bind(R.id.imageView)//用户职业
    TextView imageView;
    @Bind(R.id.tv_content)//签名
    TextView tv_content;
    @Bind(R.id.delete_textview)//取消关注
    TextView delete_textview;

    public PingbiViewHolder(View view) {
        super(view);
    }

    @Override
    public void onBindData(PingbiBean.DataBean dataBean) {


    }
    @Override
    public void onBindData(PingbiBean.DataBean dataBean, int position) {
        String uid = String.valueOf(dataBean.getUid());
        UserActionModelImpl model = new UserActionModelImpl();
        if (baseIView != null)
            baseIView.showLoading();
        String type="2";

        ImageServerApi.showURLSamllImage(riv_user_image, dataBean.getFace());
        tv_content.setText(dataBean.getSignature());
        tv_user_name.setText(dataBean.getNickname());
        delete_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.uppingbi(type,uid,new BaseIModel.onLoadDateSingleListener<DefaultDataBean>() {
                    @Override
                    public void onSuccess(DefaultDataBean bean, BaseIModel.DataType dataType) {
                        if (baseIView != null) {
                            baseIView.hideLoading();
                        }
                        if (StringUtis.equals(bean.getCode(), "1")) {
                            if (baseRecyclerViewAdapter != null) {
                                baseRecyclerViewAdapter.delectSingleItme(position);
                            }
                        }
                        if (baseIView != null) {
                            baseIView.transfePageMsg(bean.getMsg());
                        }

                    }

                    @Override
                    public void onFailure(String msg) {
                        if (baseIView != null) {
                            baseIView.transfePageMsg(msg);
                        }
                    }
                });
            }
        });
    }

    @Override
    protected void onItemclick(View view, PingbiBean.DataBean dataBean, int position) {

    }
    public void setAdapter(PingbiAdapter adapter) {
        this.adapter = adapter;
    }
}