package com.retrieve.entity;

public class CranFieldDocument {

    private int docNo;
    private String title;
    private String author;
    private String bibliography;
    private String text;

    public CranFieldDocument() {
    }

    public CranFieldDocument(int docNo, String title, String author, String bibliography, String text) {
        this.docNo = docNo;
        this.title = title;
        this.author = author;
        this.bibliography = bibliography;
        this.text = text;
    }

    public int getDocNo() {
        return docNo;
    }

    public void setDocNo(int docNo) {
        this.docNo = docNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBibliography() {
        return bibliography;
    }

    public void setBibliography(String bibliography) {
        this.bibliography = bibliography;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
