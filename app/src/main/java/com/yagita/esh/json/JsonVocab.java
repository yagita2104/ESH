package com.yagita.esh.json;

import java.util.List;

public class JsonVocab {
    private String ID;
    private String E;
    private String SE;
    private String EX;
    private List<String> TA;
    private int S;

    public JsonVocab(String ID, String e, String SE, String EX, List<String> TA, int s) {
        this.ID = ID;
        E = e;
        this.SE = SE;
        this.EX = EX;
        this.TA = TA;
        S = s;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getE() {
        return E;
    }

    public void setE(String e) {
        E = e;
    }

    public String getSE() {
        return SE;
    }

    public void setSE(String SE) {
        this.SE = SE;
    }

    public String getEX() {
        return EX;
    }

    public void setEX(String EX) {
        this.EX = EX;
    }

    public List<String> getTA() {
        return TA;
    }

    public void setTA(List<String> TA) {
        this.TA = TA;
    }

    public int getS() {
        return S;
    }

    public void setS(int s) {
        S = s;
    }
}
