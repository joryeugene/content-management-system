package com.teamexcalibur.dto;

public class User {
    private int id;
    private String email;
    private String displayName;
    private String authority;
    private String avatarUrl;
    private String password;

    public User() {
    }

    public User(String email, String displayName, String authority, String avatarUrl, String password) {
        this.email = email;
        this.displayName = displayName;
        this.authority = authority;
        this.avatarUrl = avatarUrl;
        this.password = password;
    }

    public User(int id, String email, String displayName, String authority, String avatarUrl, String password) {
        this.id = id;
        this.email = email;
        this.displayName = displayName;
        this.authority = authority;
        this.avatarUrl = avatarUrl;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
