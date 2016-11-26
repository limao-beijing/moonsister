package com.moonsister.tcjy.my.model;


import com.hickey.tool.base.BaseIModel;

import java.util.ArrayList;

/**
 * Created by jb on 2016/6/30.
 */
public interface RZSecondModel extends BaseIModel {
    void submit(String address1, String address2, String height, String sexid, String nike, String avaterpath, ArrayList<String> pics, onLoadDateSingleListener listener);
}
