package com.hickey.network.bean.resposen;

/**
 * Created by jb on 2016/12/1.
 */
public class CallMessageBean extends BaseModel {


    /**
     * uid : 148327
     * type : 1
     * pic : http://mimei.oss-cn-beijing.aliyuncs.com/a/image/06/2016-11-15/582ae5e3b8566.jpg
     */

    private String uid;
    private String type;
    private String pic;
    private String title;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
