/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamexcalibur.dto;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author apprentice
 */
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
    private List<Hashtag> hashtags;
    private boolean queued;

    public Post() {
    }

    public Post(User author, String title, String content, int numViews, String stringStartDate, String stringEndDate, Category category, List<Hashtag> hashtags, boolean queued) {
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

    public Post(int id, User author, String title, String content, int numViews, String stringStartDate, String stringEndDate, Category category, List<Hashtag> hashtags, boolean queued) {
        this(author,title,content, numViews, stringStartDate, stringEndDate, category, hashtags, queued);
        this.id = id;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the author
     */
    public User getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(User author) {
        this.author = author;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the numViews
     */
    public int getNumViews() {
        return numViews;
    }

    /**
     * @param numViews the numViews to set
     */
    public void setNumViews(int numViews) {
        this.numViews = numViews;
    }

    /**
     * @return the startDate
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the stringStartDate
     */
    public String getStringStartDate() {
        return startDate.toString();
    }

    /**
     * @param stringStartDate the stringStartDate to set
     */
    public void setStringStartDate(String stringStartDate) {
        if (stringStartDate.isEmpty()) {
            this.startDate = LocalDate.now();
            return;
        }
        LocalDate newDate;
        newDate = LocalDate.parse(stringStartDate.replaceAll("/", "-"));
        this.startDate = newDate;
    }

    /**
     * @return the stringEndDate
     */
    public String getStringEndDate() {
        return endDate.toString();
    }

    /**
     * @param stringEndDate the stringEndDate to set
     */
    public void setStringEndDate(String stringEndDate) {
        LocalDate newDate;
        newDate = LocalDate.parse(stringEndDate.replaceAll("/", "-"));
        this.endDate = newDate;
    }

    /**
     * @return the hashtags
     */
    public List<Hashtag> getHashtags() {
        return hashtags;
    }

    /**
     * @param hashtags the hashtags to set
     */
    public void setHashtags(List<Hashtag> hashtags) {
        this.hashtags = hashtags;
    }

    /**
     * @return the queued
     */
    public boolean isQueued() {
        return queued;
    }

    /**
     * @param queued the queued to set
     */
    public void setQueued(boolean queued) {
        this.queued = queued;
    }

    /**
     * @return the category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(Category category) {
        this.category = category;
    }
}
