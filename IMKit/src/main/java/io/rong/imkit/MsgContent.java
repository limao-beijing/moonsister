package io.rong.imkit;

/**
 * Created by jb on 2016/7/29.
 */
public class MsgContent {

    /**
     * content : hello
     * user : {"id":"4242","name":"Robin","icon":"http://www.demo.com/p1.png"}
     * extra : helloExtra
     */

    private String content;
    /**
     * id : 4242
     * name : Robin
     * icon : http://www.demo.com/p1.png
     */

    private UserBean user;
    private String extra;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public static class UserBean {
        private String id;
        private String name;
        private String icon;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }
}
