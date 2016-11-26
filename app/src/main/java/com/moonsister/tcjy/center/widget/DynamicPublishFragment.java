package com.moonsister.tcjy.center.widget;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.hickey.tool.base.BaseFragment;
import com.hickey.tool.widget.UIUtils;
import com.moonsister.tcjy.R;
import com.moonsister.tcjy.manager.GaodeManager;
import com.moonsister.tcjy.widget.MySwitch;

import java.util.List;

import butterknife.Bind;

/**
 * Created by jb on 2016/8/8.
 */
public class DynamicPublishFragment extends BaseFragment {
    @Bind(R.id.et_content)
    EditText etContent;
    @Bind(R.id.fl_upload_content)
    FrameLayout flUploadContent;
    @Bind(R.id.ms_dynamic_charge)
    MySwitch msDynamicCharge;
    @Bind(R.id.tv_show_adress)
    TextView tvShowAdress;
    @Bind(R.id.ms_adress_show)
    MySwitch msAdressShow;
    @Bind(R.id.fl_label_content)
    FrameLayout flLabelContent;
    private LableFragment lableFragment;
    private DynamicContentFragment contentFragment;

    public static DynamicPublishFragment newInstance() {
        return new DynamicPublishFragment();
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dynamic_publish, container, false);
    }

    @Override
    protected void initData() {
        initLableLayout();
        String adress = GaodeManager.getInstance().getStringAdress();
        tvShowAdress.setText(UIUtils.getStringRes(R.string.locationing));
        GaodeManager.getInstance().getLocLocation();
        GaodeManager.getInstance().setLocationFinishListenter(new GaodeManager.onLocationFinishListenter() {
            @Override
            public void onLocationListenter(AMapLocation aMapLocation) {
                tvShowAdress.setText(aMapLocation.getProvince() + " - " + aMapLocation.getCity());
//                getLables();
            }
        });
        msAdressShow.setOnSelectChangeListener(new MySwitch.OnSelectChangeListener() {
            @Override
            public void onCheckedChanged(MySwitch mySwitch, boolean isOpen) {
                if (isOpen) {
                    tvShowAdress.setVisibility(View.VISIBLE);
                } else
                    tvShowAdress.setVisibility(View.GONE);
            }
        });
        msAdressShow.setOpen(true);

    }

    private void initLableLayout() {
        lableFragment = LableFragment.newInstance();
        contentFragment = DynamicContentFragment.newInsatance();
        replaceFramgent(lableFragment, flLabelContent.getId());
        replaceFramgent(contentFragment, flUploadContent.getId());
    }

    /**
     * 获取选择标签
     *
     * @return
     */
    public List<String> getLables() {
        if (lableFragment == null) {
            return null;
        }
        return lableFragment.getSelectLables();

    }

    /**
     * 获取媒体类容
     *
     * @return
     */
    public List<String> getDynamicContent() {
        return contentFragment.getDynamicContent();
    }

    /**
     * 获取输入文本类容
     *
     * @return
     */
    public String getTXTContent() {
        return etContent.getText().toString().trim();
    }

    /**
     * 是否收费
     *
     * @return
     */
    public boolean isCharge() {
        return msDynamicCharge.isOpen();
    }

    /**
     * 是否显示地址
     *
     * @return
     */
    public boolean isShowAdress() {
        return msAdressShow.isOpen();
    }

    /**
     * 获取动态类型
     *
     * @return
     */
    public DynamicContentFragment.DynamicType getDynamicType() {
        return contentFragment.getDynamicType();
    }
}
