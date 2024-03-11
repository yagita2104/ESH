package com.yagita.esh.model;

import java.io.Serializable;
import java.util.List;

public class Vocabulary implements Serializable {
    private String ID;
    private String English;
    private String Sub_English;
    private String Example;
    private List<String> English_Vocabulary_Test;
    private String Fill_Blank;
    private List<String> Mistakes;
    private int Status;

    public Vocabulary(String ID, String english, String sub_English, String example, List<String> english_Vocabulary_Test, String fill_Blank, List<String> mistake, int status) {
        this.ID = ID;
        English = english;
        Sub_English = sub_English;
        Example = example;
        English_Vocabulary_Test = english_Vocabulary_Test;
        Fill_Blank = fill_Blank;
        Mistakes = mistake;
        Status = status;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getEnglish() {
        return English;
    }

    public void setEnglish(String english) {
        English = english;
    }

    public String getSub_English() {
        return Sub_English;
    }

    public void setSub_English(String sub_English) {
        Sub_English = sub_English;
    }

    public String getExample() {
        return Example;
    }

    public void setExample(String example) {
        Example = example;
    }

    public List<String> getEnglish_Vocabulary_Test() {
        return English_Vocabulary_Test;
    }

    public void setEnglish_Vocabulary_Test(List<String> english_Vocabulary_Test) {
        English_Vocabulary_Test = english_Vocabulary_Test;
    }

    public String getFill_Blank() {
        return Fill_Blank;
    }

    public void setFill_Blank(String fill_Blank) {
        Fill_Blank = fill_Blank;
    }

    public List<String> getMistake() {
        return Mistakes;
    }

    public void setMistake(List<String> mistake) {
        Mistakes = mistake;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }
}
