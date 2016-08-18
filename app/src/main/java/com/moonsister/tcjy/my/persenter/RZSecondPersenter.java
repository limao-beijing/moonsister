package com.moonsister.tcjy.my.persenter;

import com.moonsister.tcjy.base.BaseIPresenter;
import com.moonsister.tcjy.my.view.RZSecondView;

import java.util.ArrayList;

/**
 * Created by jb on 2016/6/30.
 */
public interface RZSecondPersenter extends BaseIPresenter<RZSecondView> {
    void submit(String address1, String address2, String height, String sexid, String nike, String avaterpath, ArrayList<String> pics);

}
