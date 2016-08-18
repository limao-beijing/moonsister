package com.moonsister.tcjy.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.moonsister.tcjy.R;
import com.moonsister.tcjy.base.BaseIView;
import com.moonsister.tcjy.base.BaseRecyclerViewAdapter;
import com.moonsister.tcjy.base.BaseRecyclerViewHolder;
import com.moonsister.tcjy.bean.PayRedPacketPicsBean;
import com.moonsister.tcjy.bean.UserInfoListBean;
import com.moonsister.tcjy.utils.StringUtis;
import com.moonsister.tcjy.utils.UIUtils;
import com.moonsister.tcjy.viewholder.dynamic.PicViewHolder;
import com.moonsister.tcjy.viewholder.dynamic.VideoViewHolder;
import com.moonsister.tcjy.viewholder.dynamic.VoiceViewHolder;

import java.util.List;

/**
 * Created by pc on 2016/6/6.
 */
public class DynamicAdapter extends BaseRecyclerViewAdapter<UserInfoListBean.UserInfoListBeanData.UserInfoListBeanDataList> {
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

    private BaseIView baseIView;
    private BaseRecyclerViewHolder holder;

    public DynamicAdapter(List list) {
        super(list);

    }


    @Override
    protected View initRootView(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_CHARGE_PIC:
                holder = new PicViewHolder(UIUtils.inflateLayout(R.layout.item_dynamic_pic, parent));
                break;
            case TYPE_FREE_PIC:
                holder = new PicViewHolder(UIUtils.inflateLayout(R.layout.item_dynamic_pic, parent));
                break;
            case TYPE_CHARGE_VIDEO:
                holder = new VideoViewHolder(UIUtils.inflateLayout(R.layout.item_dynamic_video, parent));
                break;
            case TYPE_FREE_VIDEO:
                holder = new VideoViewHolder(UIUtils.inflateLayout(R.layout.item_dynamic_video, parent));
                break;
            case TYPE_CHARGE_VOICE:
                holder = new VoiceViewHolder(UIUtils.inflateLayout(R.layout.item_dynamic_voice, parent));
                break;
            case TYPE_FREE_VOICE:
                holder = new VoiceViewHolder(UIUtils.inflateLayout(R.layout.item_dynamic_voice, parent));
                break;
        }
//        View view = UIUtils.inflateLayout(R.layout.item_home_one_menu, parent);
//        holder = new DynamicViewHolder(view, viewType);
        if (baseIView != null)
            holder.setView(baseIView);
        return holder.getRootView();
    }

    @Override
    protected BaseRecyclerViewHolder getBaseViewHolder(View v, int viewType) {

        return holder;
    }

    @Override
    public int getItemViewType(int position) {
        return datas.get(position).getType();
    }

    public void updataPayData(PayRedPacketPicsBean bean) {
        if (bean == null || bean.getData() == null)
            return;
        if (datas != null) {
            for (int i = 0; i < datas.size(); i++) {
                UserInfoListBean.UserInfoListBeanData.UserInfoListBeanDataList ls = datas.get(i);
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

    public void setView(BaseIView view) {
        this.baseIView = view;
    }

    public void deleteDynamic(String id) {
        UserInfoListBean.UserInfoListBeanData.UserInfoListBeanDataList bean = null;
        for (UserInfoListBean.UserInfoListBeanData.UserInfoListBeanDataList data : datas) {
            if (StringUtis.equals(data.getLatest_id(), id)) {
                bean = data;
                break;
            }
        }
        if (bean != null) {
            datas.remove(bean);
            notifyDataSetChanged();

        }
    }
}
