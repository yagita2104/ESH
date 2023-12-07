package com.yagita.esh.model;

import java.io.Serializable;

public class TestAnswer implements Serializable {
    private int stt;
    private String userAnswer;
    private String answer;

    public TestAnswer(int stt, String userAnswer, String answer) {
        this.stt = stt;
        this.userAnswer = userAnswer;
        this.answer = answer;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
