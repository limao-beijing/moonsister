package com.moonsister.tcjy.bean;

import java.util.List;

/**
 * Created by jb on 2016/8/3.
 */
public class RankBean extends BaseBean {


    /**
     * code : 1
     * msg : 拉取成功
     * data : [{"nickname":"Sally","uid":144030,"face":"http://mimei.oss-cn-beijing.aliyuncs.com/image/20160803/10/39311470191971679.jpg","num":3968,"isfollow":"1"},{"nickname":"大家","uid":144020,"face":"http://mimei.oss-cn-beijing.aliyuncs.com/image/20160706/11/14677758558769932.jpg","num":2756,"isfollow":"1"},{"nickname":"John\\'s","uid":144133,"face":"http://mimei.oss-cn-beijing.aliyuncs.com/image/20160722/18/14691825252521227.jpg","num":1815,"isfollow":"2"},{"nickname":"Snow","uid":144134,"face":"http://mimei.oss-cn-beijing.aliyuncs.com/image/20160729/17/0831146978331151.jpg","num":1641,"isfollow":"2"},{"nickname":"小丽","uid":144025,"face":"http://mimei.oss-cn-beijing.aliyuncs.com/image/20160629/12/32531467174773149.jpg","num":1634,"isfollow":"2"},{"nickname":"灵萧萧","uid":104703,"face":"http://mimei.oss-cn-beijing.aliyuncs.com/a/image/09/2016-07-07/577e5ff205679.png","num":1077,"isfollow":"2"},{"nickname":"井小西","uid":104699,"face":"http://mimei.oss-cn-beijing.aliyuncs.com/a/image/09/2016-07-07/577e5a317d027.jpg","num":956,"isfollow":"2"},{"nickname":"144021","uid":144021,"face":"http://mimei.oss-cn-beijing.aliyuncs.com/public/face/1.png","num":936,"isfollow":"2"},{"nickname":"霜霜","uid":104717,"face":"http://mimei.oss-cn-beijing.aliyuncs.com/a/image/01/2016-07-09/57808c910883e.png","num":616,"isfollow":"2"},{"nickname":"魔豆豆","uid":104707,"face":"http://mimei.oss-cn-beijing.aliyuncs.com/a/image/10/2016-07-07/577e65cf70761.png","num":555,"isfollow":"2"},{"nickname":"范峻3","uid":104722,"face":"http://mimei.oss-cn-beijing.aliyuncs.com/default/face/2957.jpg","num":479,"isfollow":"2"},{"nickname":"菜阿菜","uid":144011,"face":"http://mimei.oss-cn-beijing.aliyuncs.com/image/20160706/12/14677781248850764.jpg","num":408,"isfollow":"2"},{"nickname":"mm1467889412696","uid":144031,"face":"http://mimei.oss-cn-beijing.aliyuncs.com/image/20160707/19/14678894020308066.jpg","num":402,"isfollow":"2"},{"nickname":"江小影","uid":104697,"face":"http://mimei.oss-cn-beijing.aliyuncs.com/a/image/09/2016-07-07/577e59354a509.png","num":400,"isfollow":"2"},{"nickname":"潘多拉","uid":104713,"face":"http://mimei.oss-cn-beijing.aliyuncs.com/a/image/10/2016-07-07/577e693635651.png","num":381,"isfollow":"2"},{"nickname":"秦淮淮","uid":104715,"face":"http://mimei.oss-cn-beijing.aliyuncs.com/a/image/12/2016-07-09/57807fbfdcdd3.png","num":374,"isfollow":"2"},{"nickname":"mm1468392359569","uid":144135,"face":"http://mimei.oss-cn-beijing.aliyuncs.com/image/20160713/23/14684236009196512.jpg","num":357,"isfollow":"2"},{"nickname":"安琪儿","uid":104733,"face":"http://mimei.oss-cn-beijing.aliyuncs.com/a/image/10/2016-07-07/577e6c63a19a2.png","num":339,"isfollow":"2"},{"nickname":"暖暖","uid":104709,"face":"http://mimei.oss-cn-beijing.aliyuncs.com/a/image/10/2016-07-07/577e6761e5549.png","num":318,"isfollow":"2"},{"nickname":"哈尼宝宝","uid":144027,"face":"http://mimei.oss-cn-beijing.aliyuncs.com/image/20160720/22/14690253242128453.jpg","num":285,"isfollow":"2"}]
     */

    /**
     * nickname : Sally
     * uid : 144030
     * face : http://mimei.oss-cn-beijing.aliyuncs.com/image/20160803/10/39311470191971679.jpg
     * num : 3968
     * isfollow : 1
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean extends BaseDataBean {
        private String nickname;
        private String uid;
        private String face;
        private String num;
        private String isfollow;

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getFace() {
            return face;
        }

        public void setFace(String face) {
            this.face = face;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getIsfollow() {
            return isfollow;
        }

        public void setIsfollow(String isfollow) {
            this.isfollow = isfollow;
        }
    }
}
