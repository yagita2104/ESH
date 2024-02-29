package com.yagita.esh.model;

public enum Specialize {
    ENGLISH_LANGUAGE("tblVocabEnglishLanguage"),
    INFORMATION_TECHNOLOGY("tblVocabInformationTechnology"),
    ENGLISH_BUSINESS("tblVocabEnglishBusiness");
    private final String tenBang;
    Specialize(String tenBang) {
        this.tenBang = tenBang;
    }

    public String getTenBang() {
        return tenBang;
    }
}
