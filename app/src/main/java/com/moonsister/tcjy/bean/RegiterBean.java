package com.moonsister.tcjy.bean;

import java.io.Serializable;

/**
 * Created by jb on 2016/6/15.
 */
public class RegiterBean extends BaseBean {
    //        private RegiterCode data;
    private RegiterCode data;

    public static class RegiterCode implements Serializable {
        private String authcode;

        public String getAuthcode() {
            return authcode;
        }

        public void setAuthcode(String authcode) {
            this.authcode = authcode;
        }

        @Override
        public String toString() {
            return "RegiterCode{" +
                    "authcode='" + authcode + '\'' +
                    '}';
        }
    }

    public RegiterCode getData() {
        return data;
    }

    public void setData(RegiterCode data) {
        this.data = data;
    }
}
