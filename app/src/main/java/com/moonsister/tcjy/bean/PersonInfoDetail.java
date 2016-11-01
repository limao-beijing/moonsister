package com.moonsister.tcjy.bean;

import java.util.List;

/**
 * Created by jb on 2016/6/26.
 */
public class PersonInfoDetail extends BaseDataBean {


    private String authcode;
    private String face;
    private String rongyunkey;
    private String nickname;
    private boolean isLogin;
    private String sex;
    private String mobile;//手机号
    /**
     * 认证状态 1 已认证  2 认证中  3 未认证
     */

    private int attestation;
    /**
     * vip 1为vip ，其他为否。
     */
    private int vipStatus;
    /**
     * 好友列表
     */
    private List<String> userFriendList;

    public String getHxPwd() {
        return hxPwd;
    }

    public void setHxPwd(String hxPwd) {
        this.hxPwd = hxPwd;
    }

    private String hxPwd;

    /**
     * 1男 2 女
     *
     * @return
     */
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getRongyunkey() {
        return rongyunkey;
    }

    public void setRongyunkey(String rongyunkey) {
        this.rongyunkey = rongyunkey;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAuthcode() {
        return authcode;
    }

    public void setAuthcode(String authcode) {
        this.authcode = authcode;
    }

    public void setAttestation(int attestation) {
        this.attestation = attestation;
    }

    public int getAttestation() {
        return attestation;
    }

    public void setVipStatus(int vipStatus) {
        this.vipStatus = vipStatus;
    }

    public void setUserFriendList(List<String> userFriendList) {
        this.userFriendList = userFriendList;
    }

    public List<String> getUserFriendList() {
        return userFriendList;
    }

    public int getVipStatus() {
        return vipStatus;
    }
}
