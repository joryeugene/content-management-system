package com.teamexcalibur.dto;

import java.time.LocalDate;
import java.util.List;

public class Post {

    private int id;
    private User author;
    private String title;
    private String content;
    private int numViews;
    private LocalDate startDate;
    private LocalDate endDate;
    private String stringStartDate;
    private String stringEndDate;
    private Category category;
    private List<String> hashtags;
    private boolean queued;

    public Post() {
    }

    public Post(User author, String title, String content, int numViews, String stringStartDate, String stringEndDate, Category category, List<String> hashtags, boolean queued) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.numViews = numViews;
        this.setStringEndDate(stringEndDate);
        this.setStringStartDate(stringStartDate);
        this.category = category;
        this.hashtags = hashtags;
        this.queued = queued;
    }

    public Post(int id, User author, String title, String content, int numViews, String stringStartDate, String stringEndDate, Category category, List<String> hashtags, boolean queued) {
        this(author,title,content, numViews, stringStartDate, stringEndDate, category, hashtags, queued);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
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

    public int getNumViews() {
        return numViews;
    }

    public void setNumViews(int numViews) {
        this.numViews = numViews;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

     public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getStringStartDate() {
        return startDate.toString();
    }

    public void setStringStartDate(String stringStartDate) {
        if (stringStartDate.isEmpty()) {
            this.startDate = LocalDate.now();
            return;
        }
        LocalDate newDate;
        newDate = LocalDate.parse(stringStartDate.replaceAll("/", "-"));
        this.startDate = newDate;
    }

    public String getStringEndDate() {
        if (endDate == null) {
            return "Not set";
        }
        return endDate.toString();
    }

    public void setStringEndDate(String stringEndDate) {
        if (stringEndDate.isEmpty() || stringEndDate.equals("Not set")) {
            return;
        }
        LocalDate newDate;
        newDate = LocalDate.parse(stringEndDate.replaceAll("/", "-"));
        this.endDate = newDate;
    }

      public List<String> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<String> hashtags) {
        this.hashtags = hashtags;
    }

     public boolean isQueued() {
        return queued;
    }

     public void setQueued(boolean queued) {
        this.queued = queued;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
