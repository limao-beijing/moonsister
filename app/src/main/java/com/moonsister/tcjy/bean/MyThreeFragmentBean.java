package com.moonsister.tcjy.bean;

import java.util.List;

/**
 * Created by jb on 2016/9/22.
 */
public class MyThreeFragmentBean extends BaseBean {

    /**
     * create_time : 1474729944
     * uid : 144093
     * contents : {"s":"http://mimei.img-cn-beijing.aliyuncs.com/a/image/02/2016-07-13/5785e5905b122.jpg@!336_246","v":"","l":"http://mimei.oss-cn-beijing.aliyuncs.com/a/image/02/2016-07-13/5785e5905b122.jpg"}
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean extends BaseDataBean {
        private String create_time;
        private String uid;
        private String source_type;
        private boolean isUpPIC = false;

        public boolean isUpPIC() {
            return isUpPIC;
        }

        public void setUpPIC(boolean upPIC) {
            isUpPIC = upPIC;
        }

        /**
         * s : http://mimei.img-cn-beijing.aliyuncs.com/a/image/02/2016-07-13/5785e5905b122.jpg@!336_246
         * v :
         * l : http://mimei.oss-cn-beijing.aliyuncs.com/a/image/02/2016-07-13/5785e5905b122.jpg
         */

        private ContentsBean contents;

        public String getSource_type() {
            return source_type;
        }

        public void setSource_type(String source_type) {
            this.source_type = source_type;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public ContentsBean getContents() {
            return contents;
        }

        public void setContents(ContentsBean contents) {
            this.contents = contents;
        }

        public static class ContentsBean {
            private String s;
            private String v;
            private String l;

            public String getS() {
                return s;
            }

            public void setS(String s) {
                this.s = s;
            }

            public String getV() {
                return v;
            }

            public void setV(String v) {
                this.v = v;
            }

            public String getL() {
                return l;
            }

            public void setL(String l) {
                this.l = l;
            }
        }
    }
}
