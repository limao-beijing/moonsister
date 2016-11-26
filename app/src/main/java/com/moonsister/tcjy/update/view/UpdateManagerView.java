package com.moonsister.tcjy.update.view;


import com.hickey.network.bean.VersionInfo;
import com.hickey.tool.base.BaseIView;

/**
 * Created by jb on 2016/7/14.
 */
public interface UpdateManagerView extends BaseIView {
    void onProgress(long progress, long total, boolean done);

    void downApkSuccess(VersionInfo versionInfo);
}
