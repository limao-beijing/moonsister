package com.moonsister.tcjy.my.widget;

import android.view.View;

import com.hickey.network.bean.CardInfoBean;
import com.hickey.network.bean.GetMoneyBean;
import com.hickey.tool.base.BaseActivity;
import com.hickey.tool.base.BaseRecyclerViewAdapter;
import com.hickey.tool.lang.StringUtis;
import com.hickey.tool.widget.UIUtils;
import com.hickey.tool.widget.XListView;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.adapter.SwitchCardAdatper;
import com.moonsister.tcjy.event.Events;
import com.moonsister.tcjy.event.RxBus;
import com.moonsister.tcjy.my.persenter.SwitchCardActivityPresenter;
import com.moonsister.tcjy.my.persenter.SwitchCardActivityPresenterImpl;
import com.moonsister.tcjy.my.view.SwitchCardActivityView;

import java.util.List;

import butterknife.Bind;

/**
 * Created by jb on 2016/7/4.
 */
public class SwitchCardActivity extends BaseActivity implements SwitchCardActivityView {
    @Bind(R.id.xlv)
    XListView xlv;
    private SwitchCardActivityPresenter presenter;

    @Override
    protected View setRootContentView() {
        presenter = new SwitchCardActivityPresenterImpl();
        presenter.attachView(this);
        return UIUtils.inflateLayout(R.layout.activity_switch_card);
    }

    @Override
    protected String initTitleName() {
        return UIUtils.getStringRes(R.string.withdraw_deposit);
    }

    @Override
    protected void initView() {
        xlv.setLoadingMoreEnabled(false);
        xlv.setPullRefreshEnabled(false);
        xlv.setVerticalLinearLayoutManager();
        presenter.loadCardInfo();
    }

    @Override
    public void showLoading() {
        showProgressDialog();
    }

    @Override
    public void hideLoading() {
        hideProgressDialog();
    }

    @Override
    public void transfePageMsg(String msg) {
        showToast(msg);
    }

    @Override
    public void setCardInfos(List<CardInfoBean.DataBean> datas) {
        String type = getIntent().getStringExtra("type");
        String cardID = getIntent().getStringExtra("cardID");
        for (CardInfoBean.DataBean dataBean : datas) {
            if (StringUtis.equals(dataBean.getId(), cardID) && StringUtis.equals(type, dataBean.getType()))
                dataBean.setIs_default("1");
        }
        SwitchCardAdatper adatper = new SwitchCardAdatper(datas);
        adatper.setOnItemClickListener(new BaseRecyclerViewAdapter.onItemClickListener() {
            @Override
            public void onItemclick(View view, int position) {
                CardInfoBean.DataBean dataBean = datas.get(position);
                GetMoneyBean bean = new GetMoneyBean();

                GetMoneyBean.DataBean data = new GetMoneyBean.DataBean();
                data.setBank_name(dataBean.getBank_name());
                data.setLogo(dataBean.getLogo());
                data.setBank_no(dataBean.getBank_no());
                data.setType(data.getType());
                data.setId(dataBean.getId());
                bean.setData(data);

                Events<GetMoneyBean> events = new Events<>();
                events.what = Events.EventEnum.CLICK_SWITCH_CARD_POSITION;
                events.message = bean;
                RxBus.getInstance().send(events);
                SwitchCardActivity.this.finish();

            }
        });
        xlv.setAdapter(adatper);


    }
}
