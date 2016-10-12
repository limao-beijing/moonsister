package com.moonsister.tcjy.bean;

/**
 * Created by jb on 2016/9/28.
 */

public class StatusBean extends BaseBean {
    private String status;
    private String id;
    /**
     * dating_id : 44
     */

    private DataBean data;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {

        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String dating_id;

        public String getDating_id() {
            return dating_id;
        }

        public void setDating_id(String dating_id) {
            this.dating_id = dating_id;
        }
    }
}
