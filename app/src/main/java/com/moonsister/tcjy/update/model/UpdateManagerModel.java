package com.moonsister.tcjy.update.model;


import com.hickey.tool.base.BaseIModel;

/**
 * Created by jb on 2016/7/14.
 */
public interface UpdateManagerModel extends BaseIModel {
    void loadVersionInfo(onLoadDateSingleListener listener);

    void downFile(String url, String savePath, onDownFileleListener listener);
}
