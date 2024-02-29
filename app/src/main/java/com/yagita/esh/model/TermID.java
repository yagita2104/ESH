package com.yagita.esh.model;

public enum TermID {
    ID_INFORMATION_TECHNOLOGY("English_Information_Technology_"),
    ID_ENGLISH_LANGUAGE_READ("English_English_Language_Read_"),
    ID_ENGLISH_LANGUAGE_WRITING("English_English_Language_Writing_"),
    ID_ENGLISH_LANGUAGE_LISTEN("English_English_Language_Listen_"),
    ID_ENGLISH_LANGUAGE_SPEAK("English_English_Language_Speak_"),
    ID_ENGLISH_LANGUAGE("English_English_Language_"),
    ID_ENGLISH_BUSINESS("English_Business_");
    private final String id;
    TermID(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }
}
