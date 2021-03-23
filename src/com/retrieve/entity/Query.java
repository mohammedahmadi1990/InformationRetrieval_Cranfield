package com.retrieve.entity;

public class Query {

    private int id;
    private String query;

    public Query() {
    }

    public Query(int id, String query) {
        this.id = id;
        this.query = query;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
