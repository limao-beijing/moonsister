package com.moonsister.tcjy.bean;

/**
 * Created by jb on 2016/9/27.
 */
public class EngagemengOrderBean extends BaseBean {

    /**
     * last_count : 0
     * dating_money : 80
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String limit_min;
        private String limit_max;

        public String getLimit_max() {
            return limit_max;
        }

        public void setLimit_max(String limit_max) {
            this.limit_max = limit_max;
        }

        public String getLimit_min() {
            return limit_min;
        }

        public void setLimit_min(String limit_min) {
            this.limit_min = limit_min;
        }
    }
}
