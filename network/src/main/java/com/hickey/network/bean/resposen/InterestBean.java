package com.hickey.network.bean.resposen;

import java.util.List;

/**
 * Created by jb on 2016/11/30.
 */
public class InterestBean extends BaseModel {


    /**
     * uid:144133
     * face:http://mimei.oss-cn-beijing.aliyuncs.com/default/face/15.jpg
     * key:profession
     * question:嗨，我现在是企业职员，你能告诉我你的职业吗？
     * answer:["在校员工","企业职员","私营业主","事业单位工作者","军人","自由职业者","农业劳动者"]
     */

    private String uid;
    private String face;
    private String key;
    private String question;
    private List<String> answer;

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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getAnswer() {
        return answer;
    }

    public void setAnswer(List<String> answer) {
        this.answer = answer;
    }

}
