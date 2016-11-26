package com.moonsister.tcjy.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.hickey.network.bean.DynamicItemBean;
import com.hickey.network.bean.PayRedPacketPicsBean;
import com.hickey.tool.base.BaseRecyclerViewAdapter;
import com.hickey.tool.base.BaseRecyclerViewHolder;
import com.hickey.tool.lang.StringUtis;
import com.hickey.tool.widget.UIUtils;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.main.view.BasePageFragmentView;
import com.moonsister.tcjy.viewholder.homepage.HomePagePicViewHolder;
import com.moonsister.tcjy.viewholder.homepage.HomePageVideoViewHolder;

import java.util.List;

/**
 * Created by jb on 2016/9/1.
 */
public class HomePageFragmentAdapter extends BaseRecyclerViewAdapter<DynamicItemBean> {
    /**
     * type 动态类型 1红包图集，2普通图文，3普通小视频动态，4免费语音，5付费语音，6付费视频
     */
    //1 红包图集
    public static final int TYPE_CHARGE_PIC = 1;
    //2 普通图片
    public static final int TYPE_FREE_PIC = 2;
    //3 视频
    public static final int TYPE_FREE_VIDEO = 3;

    public static final int TYPE_FREE_VOICE = 4;

    public static final int TYPE_CHARGE_VOICE = 5;

    public static final int TYPE_CHARGE_VIDEO = 6;


    private BaseRecyclerViewHolder holder;
    private BasePageFragmentView mBaseView;

    public HomePageFragmentAdapter(List<DynamicItemBean> list) {
        super(list);
    }

    @Override
    public int getItemViewType(int position) {
        return datas.get(position).getType();
    }

    @Override
    protected View initRootView(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_CHARGE_PIC:
            case TYPE_FREE_PIC:
                holder = new HomePagePicViewHolder(UIUtils.inflateLayout(R.layout.item_dynamic_home_page_pic, parent));
                break;
            case TYPE_CHARGE_VOICE:
            case TYPE_FREE_VOICE:
            case TYPE_CHARGE_VIDEO:
            case TYPE_FREE_VIDEO:
                holder = new HomePageVideoViewHolder(UIUtils.inflateLayout(R.layout.item_dynamic_home_page_video, parent));
                break;
//            case TYPE_CHARGE_VOICE:
//            case TYPE_FREE_VOICE:
//                holder = new HomePageVideoViewHolder(UIUtils.inflateLayout(R.layout.item_dynamic_home_page_video, parent));
//                break;
        }
        holder.setView(mBaseView);
        return holder.getRootView();
    }

    @Override
    protected BaseRecyclerViewHolder getBaseViewHolder(View v, int viewType) {
        return holder;
    }

    public void setBaseView(BasePageFragmentView baseView) {
        mBaseView = baseView;
    }

    /**
     * 置顶
     *
     * @param position
     */
    public void stickDynamicItme(int position) {
        if (datas != null && datas.size() >= position) {
            for (DynamicItemBean bean : datas) {
                bean.setIstop("2");
            }
            DynamicItemBean itemBean = datas.get(position);
            itemBean.setIstop("1");
            stickItme(position);
        }
    }

    public void onDynamicItmeRefresh() {
        if (mBaseView!=null)
            mBaseView.refreshData();
    }

    public void updataPayData(PayRedPacketPicsBean bean) {
        if (bean == null || bean.getData() == null)
            return;
        if (datas != null) {
            for (int i = 0; i < datas.size(); i++) {
                DynamicItemBean ls = datas.get(i);
                if (ls != null && StringUtis.equals(ls.getLatest_id(), bean.getData().getLatest_id())) {
                    switch (ls.getType()) {
                        case TYPE_CHARGE_PIC:
                            ls.setIspay("1");
                            ls.getSimg();
                            ls.getSimg().clear();
                            ls.getSimg().addAll(bean.getData().getSimg());
                            ls.getImg().clear();
                            ls.getImg().addAll(bean.getData().getImg());
                            break;
                        case TYPE_FREE_PIC:
                            ls.setIspay("1");
                            ls.getSimg();
                            ls.getSimg().clear();
                            ls.getSimg().addAll(bean.getData().getSimg());
                            ls.getImg().clear();
                            ls.getImg().addAll(bean.getData().getImg());
                            break;
                        case TYPE_CHARGE_VIDEO:
                            List<String> simg = bean.getData().getSimg();
                            if (simg.size() > 0) {
                                ls.setIspay("1");
                                String s = simg.get(0);
                                ls.setVimg(s);
                                ls.setVideo(bean.getData().getV().get(0));
                            }

                            break;
                        case TYPE_FREE_VIDEO:
                            List<String> freeimg = bean.getData().getSimg();
                            if (freeimg.size() > 0) {
                                ls.setIspay("1");
                                String s = freeimg.get(0);
                                ls.setVimg(s);
                                ls.setVideo(bean.getData().getV().get(0));
                            }

                            break;
                        case TYPE_CHARGE_VOICE:
                            List<String> voive = bean.getData().getSimg();
                            if (voive.size() > 0) {
                                ls.setIspay("1");
                                String s = voive.get(0);
                                ls.setVimg(s);
                                ls.setVideo(bean.getData().getV().get(0));
                            }

                            break;
                        case TYPE_FREE_VOICE:
                            List<String> freevoice = bean.getData().getSimg();
                            if (freevoice.size() > 0) {
                                ls.setIspay("1");
                                String s = freevoice.get(0);
                                ls.setVimg(s);
                                ls.setVideo(bean.getData().getV().get(0));
                            }

                            break;
                    }


                    notifyDataSetChanged();
                    break;
                }
            }
        }
    }

}
