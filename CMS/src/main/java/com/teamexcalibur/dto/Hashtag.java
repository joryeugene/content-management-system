package com.teamexcalibur.dto;

public class Hashtag {
    private int id;
    private String hashtag;

    public Hashtag() {
    }

    public Hashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public Hashtag(int id, String hashtag) {
        this(hashtag);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }
}
