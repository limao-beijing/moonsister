package com.hickey.network.bean;

import java.util.List;

/**
 * Created by jb on 2016/8/29.
 */
public class DynamicBean extends BaseBean {


    /**
     * uid : 146318
     * isfollow : 2
     * nickname : 别说后来
     * face : http://mimei.oss-cn-beijing.aliyuncs.com/image/20160821/02/14717178997550358.jpg
     * signature :
     * sex : 2
     * fansnum : 10
     * type : 100
     */

    private List<DynamicItemBean> data;

    public List<DynamicItemBean> getData() {
        return data;
    }

    public void setData(List<DynamicItemBean> data) {
        this.data = data;
    }

}
