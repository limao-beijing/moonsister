package com.moonsister.tcjy.bean;

import java.util.List;

/**
 * Created by jb on 2016/8/24.
 */
public class HomeTopItemBean extends BaseBean {

    /**
     * uinfo : {"nickname":"丽莎","face":"http://mimei.oss-cn-beijing.aliyuncs.com/a/image/05/2016-08-05/57a45b131ef85.jpg","birthday":"","sex":2,"isauth":1,"usertags":"美女|||帅哥|||测试","age":23}
     * list : [{"title":"害怕被嘲笑，害怕自取其辱，所以我装作开心装作不在乎。","type":2,"pic":"http://mimei.img-cn-beijing.aliyuncs.com/mimages/002/104034/1003/1 (13).jpg@!336_246","views":"100","lid":"1608103457aaa3798f94f5313"},{"title":"要一起起床吗？","type":2,"pic":"http://mimei.img-cn-beijing.aliyuncs.com/mimages/002/104034/1004/1 (14).jpg@!336_246","views":"100","lid":"1608073457a66bf171b321538"}]
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean extends BaseDataBean {
        /**
         * nickname : 丽莎
         * face : http://mimei.oss-cn-beijing.aliyuncs.com/a/image/05/2016-08-05/57a45b131ef85.jpg
         * birthday :
         * sex : 2
         * isauth : 1
         * usertags : 美女|||帅哥|||测试
         * age : 23
         */

        private UinfoBean uinfo;
        /**
         * title : 害怕被嘲笑，害怕自取其辱，所以我装作开心装作不在乎。
         * type : 2
         * pic : http://mimei.img-cn-beijing.aliyuncs.com/mimages/002/104034/1003/1 (13).jpg@!336_246
         * views : 100
         * lid : 1608103457aaa3798f94f5313
         */
        private int type;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        private List<ListBean> list;

        public UinfoBean getUinfo() {
            return uinfo;
        }

        public void setUinfo(UinfoBean uinfo) {
            this.uinfo = uinfo;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class UinfoBean {
            private String nickname;
            private String face;
            private String birthday;
            private String sex;
            private int isauth;
            private String usertags;
            private String age;
            private String profession;
            private String uid;

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getProfession() {
                return profession;
            }

            public void setProfession(String profession) {
                this.profession = profession;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getFace() {
                return face;
            }

            public void setFace(String face) {
                this.face = face;
            }

            public String getBirthday() {
                return birthday;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public int getIsauth() {
                return isauth;
            }

            public void setIsauth(int isauth) {
                this.isauth = isauth;
            }

            public String getUsertags() {
                return usertags;
            }

            public void setUsertags(String usertags) {
                this.usertags = usertags;
            }

            public String getAge() {
                return age;
            }

            public void setAge(String age) {
                this.age = age;
            }
        }

        public static class ListBean {
            private String title;
            private int type;
            private String pic;
            private String views;
            private String lid;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getViews() {
                return views;
            }

            public void setViews(String views) {
                this.views = views;
            }

            public String getLid() {
                return lid;
            }

            public void setLid(String lid) {
                this.lid = lid;
            }
        }
    }
}
