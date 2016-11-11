package com.moonsister.tcjy.engagement.presenter;

import com.moonsister.tcjy.base.BaseIModel;
import com.moonsister.tcjy.bean.EngagementTextBane;
import com.moonsister.tcjy.engagement.model.EngegamentTextModel;
import com.moonsister.tcjy.engagement.model.EngegamentTextModelImpl;
import com.moonsister.tcjy.engagement.view.EngagementTextView;
import com.moonsister.tcjy.utils.EnumConstant;
import com.moonsister.tool.lang.StringUtis;

/**
 * Created by jb on 2016/11/11.
 */
public class EngagementTextPersenterImpl implements EngagementTextPersenter, BaseIModel.onLoadDateSingleListener<EngagementTextBane> {
    private EngagementTextView view;
    private EngegamentTextModel textModel;

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(EngagementTextView view) {
        this.view = view;
        textModel = new EngegamentTextModelImpl();
    }


    @Override
    public void loadText(String datingID, EnumConstant.EngegamentTextType type) {

        textModel.loadText(datingID, type, this);
    }

    @Override
    public void onSuccess(EngagementTextBane bane, BaseIModel.DataType dataType) {
        if (StringUtis.equals(bane.getCode(), "1")) {
            view.setEngagementText(bane.getData().getInfo());
        }
//
    }

    @Override
    public void onFailure(String msg) {

    }
}
