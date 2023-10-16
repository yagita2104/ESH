package com.yagita.esh.model;

public class Vocabulary {
    private String word;
    private String spelling;
    private int illustration;
    private String wordTranslate;
    private String sentences;

    public Vocabulary(String word, String spelling, int illustration, String wordTranslate, String sentences) {
        this.word = word;
        this.spelling = spelling;
        this.illustration = illustration;
        this.wordTranslate = wordTranslate;
        this.sentences = sentences;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getSpelling() {
        return spelling;
    }

    public void setSpelling(String spelling) {
        this.spelling = spelling;
    }

    public int getIllustration() {
        return illustration;
    }

    public void setIllustration(int illustration) {
        this.illustration = illustration;
    }

    public String getWordTranslate() {
        return wordTranslate;
    }

    public void setWordTranslate(String wordTranslate) {
        this.wordTranslate = wordTranslate;
    }

    public String getSentences() {
        return sentences;
    }

    public void setSentences(String sentences) {
        this.sentences = sentences;
    }
}
