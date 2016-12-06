package com.teamexcalibur.dto;

public class Config {
    
    private String title; // CrossFit Guild
    private String navColor; // #101010
    private String textColor; // #9d9d9d
    private String image; // squat.jpg

    public Config() {
    }

    public Config(String title, String navColor, String textColor, String image) {
        this.title = title;
        this.navColor = navColor;
        this.textColor = textColor;
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

}
