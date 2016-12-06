package com.teamexcalibur.dto;

public class Config {
    
    private String title; // CrossFit Guild
    private String subtitle; // Most Recent Posts
    private String navColor; // #101010
    private String textColor; // #9d9d9d
    private String highlightColor; // #337ab7
    private String image; // squat.jpg

    public Config() {
    }

    public Config(String title, String subtitle, String navColor, String textColor, String highlightColor, String image) {
        this.title = title;
        this.subtitle = subtitle;
        this.navColor = navColor;
        this.textColor = textColor;
        this.highlightColor = highlightColor;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNavColor() {
        return navColor;
    }

    public void setNavColor(String navColor) {
        this.navColor = navColor;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getHighlightColor() {
        return highlightColor;
    }

    public void setHighlightColor(String highlightColor) {
        this.highlightColor = highlightColor;
    }

}
