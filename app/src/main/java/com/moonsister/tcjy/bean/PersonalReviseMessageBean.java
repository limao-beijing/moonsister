package com.moonsister.tcjy.bean;

import java.util.List;

/**
 * Created by x on 2016/9/10.
 */
public class PersonalReviseMessageBean extends BaseBean{

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * field : smobile
         * name : 手机
         * edit : 2
         * isvip : 1
         * isshow : 1
         * value :
         */

        private List<RulesBean> rules;

        public List<RulesBean> getRules() {
            return rules;
        }

        public void setRules(List<RulesBean> rules) {
            this.rules = rules;
        }

        public static class RulesBean {
            private String field;
            private String name;
            private String edit;
            private String isvip;
            private String isshow;
            private String value;

            public String getField() {
                return field;
            }

            public void setField(String field) {
                this.field = field;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getEdit() {
                return edit;
            }

            public void setEdit(String edit) {
                this.edit = edit;
            }

            public String getIsvip() {
                return isvip;
            }

            public void setIsvip(String isvip) {
                this.isvip = isvip;
            }

            public String getIsshow() {
                return isshow;
            }

            public void setIsshow(String isshow) {
                this.isshow = isshow;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }
    }
}
