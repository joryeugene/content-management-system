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
        this(author, title, content, numViews, stringStartDate, stringEndDate, category, hashtags, queued);
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Post other = (Post) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.author.getId() != other.author.getId()) {
            return false;
        }
        if (!this.title.equals(other.title)) {
            return false;
        }
        if (!this.content.equals(other.content)) {
            return false;
        }
        if (this.numViews != other.numViews) {
            return false;
        }
        if (!this.getStringStartDate().equals(other.getStringStartDate())) {
            return false;
        }
        if (!this.getStringEndDate().equals(other.getStringEndDate())) {
            return false;
        }
        if (this.category.getId() != other.category.getId()) {
            return false;
        }
        if (this.hashtags.size() != other.hashtags.size()) {
            return false;
        }
        if (this.queued != other.queued) {
            return false;
        }
        return true;
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
        if (startDate == null) {
            this.startDate = LocalDate.now();
        } else {
            this.startDate = startDate;
        }
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        if (endDate == null)
            this.endDate = (LocalDate.now().plusYears(100));
        else
            this.endDate = endDate;
    }

    public String getStringStartDate() {
        return startDate.toString();
    }

    public void setStringStartDate(String stringStartDate) {
        if (stringStartDate == null || stringStartDate.isEmpty()) {
            this.startDate = LocalDate.now();
            return;
        }
        this.startDate = LocalDate.parse(stringStartDate.replaceAll("/", "-"));
    }

    public String getStringEndDate() {
        return endDate.toString();
    }

    public void setStringEndDate(String stringEndDate) {
        if (stringEndDate == null || stringEndDate.isEmpty()) {
            this.endDate = LocalDate.now().plusYears(100);
            return;
        }
        this.endDate = LocalDate.parse(stringEndDate.replaceAll("/", "-"));
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
