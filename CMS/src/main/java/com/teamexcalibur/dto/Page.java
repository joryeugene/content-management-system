package com.teamexcalibur.dto;

public class Page {
    private int id;
    private User user;
    private String title;
    private String content;

    public Page() {
    }

    public Page(User user, String title, String content) {
        this.user = user;
        this.title = title;
        this.content = content;
    }

    public Page(int id, User user, String title, String content) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
