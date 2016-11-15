/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamexcalibur.dto;

/**
 *
 * @author apprentice
 */
public class User {
    private int id;
    private String email;
    private String displayName;
    private String authority;
    private String avatarUrl;

    public User() {
    }

    public User(String email, String displayName, String authority, String avatarUrl) {
        this.email = email;
        this.displayName = displayName;
        this.authority = authority;
        this.avatarUrl = avatarUrl;
    }

    public User(int id, String email, String displayName, String authority, String avatarUrl) {
        this.id = id;
        this.email = email;
        this.displayName = displayName;
        this.authority = authority;
        this.avatarUrl = avatarUrl;
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
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the displayName
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * @param displayName the displayName to set
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * @return the authority
     */
    public String getAuthority() {
        return authority;
    }

    /**
     * @param authority the authority to set
     */
    public void setAuthority(String authority) {
        this.authority = authority;
    }

    /**
     * @return the avatarUrl
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * @param avatarUrl the avatarUrl to set
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
