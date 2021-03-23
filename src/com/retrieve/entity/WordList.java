package com.retrieve.entity;

import java.util.List;

public class WordList {

    private int docNo;
    public List<String> word;

    public WordList() {
    }

    public WordList(int docNo, List<String> word) {
        this.docNo = docNo;
        this.word = word;
    }

    public int getDocNo() {
        return docNo;
    }

    public void setDocNo(int docNo) {
        this.docNo = docNo;
    }

    public List<String> getWord() {
        return word;
    }

    public void setWord(List<String> word) {
        this.word = word;
    }

    public String[] getWordSeparate(){
        String[] array = word.toArray(new String[0]);
        return array;
    }

    }

