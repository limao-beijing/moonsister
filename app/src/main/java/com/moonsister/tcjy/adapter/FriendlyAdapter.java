package com.moonsister.tcjy.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.hickey.network.bean.DefaultDataBean;
import com.hickey.network.bean.FrientBaen;
import com.hickey.tool.base.BaseIModel;
import com.hickey.tool.base.BaseIView;
import com.hickey.tool.base.BaseRecyclerViewAdapter;
import com.hickey.tool.base.BaseRecyclerViewHolder;
import com.hickey.tool.lang.StringUtis;
import com.hickey.tool.widget.UIUtils;
import com.moonsister.tcjy.AppConstant;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.im.widget.FrientFragment;
import com.moonsister.tcjy.main.model.UserActionModelImpl;
import com.moonsister.tcjy.my.widget.ContactsFragment;
import com.moonsister.tcjy.viewholder.FriendlyViewHoler;

import java.util.List;

/**
 * Created by x on 2016/8/25.
 */
public class FriendlyAdapter extends BaseRecyclerViewAdapter<FrientBaen.DataBean> {
    public static final int PAGE_WACTH = 1;
    public static final int PAGE_FEN = 2;
    public static final int PAGE_MAIN = 3;
    public static final int PAGE_FRIEND = 4;//好友状态
    private FriendlyViewHoler viewHoler;
    private BaseIView view;
    private int pageType;
    public FriendlyAdapter(List<FrientBaen.DataBean> list, ContactsFragment contactsFragment) {
        super(list);
        this.view = view;
    }

    @Override
    protected View initRootView(ViewGroup parent, int viewType) {
        View view = UIUtils.inflateLayout(R.layout.contacs);
        viewHoler = new FriendlyViewHoler(view);
        viewHoler.setAdapter(this);
        return viewHoler.getRootView();
    }

    @Override
    protected BaseRecyclerViewHolder getBaseViewHolder(View v, int viewType) {
        return viewHoler;
    }
    @Override
    public void onItemclick(View view, int position) {

    }
    public void setClick(Integer position) {
        if (datas.size() - 1 >= position) {
            FrientBaen.DataBean dataBean = datas.get(position);
            if (dataBean == null)
                return;

            view.showLoading();
            UserActionModelImpl userActionModel = new UserActionModelImpl();
            userActionModel.wacthAction(dataBean.getUid(), pageType == FrientFragment.PAGE_FEN ? "1" : "2", new BaseIModel.onLoadDateSingleListener<DefaultDataBean>() {
                @Override
                public void onSuccess(DefaultDataBean bean, BaseIModel.DataType dataType) {
                    if (bean != null) {
                        if (StringUtis.equals(bean.getCode(), AppConstant.code_request_success)) {
                            if (StringUtis.equals(dataBean.getIsfollow(), "2")) {
                                dataBean.setIsfollow("1");
                            } else
                                datas.remove(dataBean);
                            onRefresh();
//                            Events<FrientBaen.DataBean> events = new Events<>();
//                            events.what = Events.EventEnum.FRIEND_CHANGE;
//                            events.message = dataBean;
                            RxBus.getInstance().send(Events.EventEnum.FRIEND_CHANGE,null);
                        }
                        view.transfePageMsg(bean.getMsg());
                    } else {
                        view.transfePageMsg(UIUtils.getStringRes(R.string.request_failed));
                    }
                    view.hideLoading();
                }

                @Override
                public void onFailure(String msg) {
                    view.hideLoading();
                    view.transfePageMsg(msg);
                }
            });

        }
    }

    public void setPageType(int pageType) {
        this.pageType = pageType;
    }
}
