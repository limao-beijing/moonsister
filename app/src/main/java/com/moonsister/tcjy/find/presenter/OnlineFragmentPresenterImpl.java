package com.moonsister.tcjy.find.presenter;

import com.moonsister.tcjy.find.model.OnlineFragmentModel;
import com.moonsister.tcjy.find.model.OnlineFragmentModelImpl;
import com.moonsister.tcjy.find.view.OnlineFragmentView;

/**
 * Created by jb on 2016/9/25.
 */
public class OnlineFragmentPresenterImpl implements OnlineFragmentPresenter {
    private OnlineFragmentView view;
    private  OnlineFragmentModel model;
    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(OnlineFragmentView view) {
        this.view = view;
        model = new OnlineFragmentModelImpl();

    }
}
