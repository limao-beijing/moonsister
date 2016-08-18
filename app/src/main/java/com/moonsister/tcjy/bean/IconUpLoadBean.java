package com.moonsister.tcjy.bean;

/**
 * Created by jb on 2016/6/16.
 */
public class IconUpLoadBean extends BaseBean {
    public static class Icon {
        private String id;
        private String img_path;
        private String width;
        private String height;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImg_path() {
            return img_path;
        }

        public void setImg_path(String img_path) {
            this.img_path = img_path;
        }

        public String getWidth() {
            return width;
        }

        public void setWidth(String width) {
            this.width = width;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }
    }
}
