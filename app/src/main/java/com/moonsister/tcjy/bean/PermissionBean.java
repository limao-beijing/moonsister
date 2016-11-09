package com.moonsister.tcjy.bean;

/**
 * Created by jb on 2016/11/6.
 */
public class PermissionBean extends BaseBean {

    /**
     * act_type : 2
     * sex : 2
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String act_type;
        private String sex;
        //剩余条数
        private String last_chat_num;

        public String getAct_type() {
            return act_type;
        }

        public void setAct_type(String act_type) {
            this.act_type = act_type;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getLast_chat_num() {
            return last_chat_num;
        }

        public void setLast_chat_num(String last_chat_num) {
            this.last_chat_num = last_chat_num;
        }
    }
}
