package com.teamexcalibur.dto;

import java.util.Objects;

public class Nav {
    private int id;
    private int pageId;
    private int position;
    private String menuName;

    public Nav() {
    }

    public Nav(int pageId, int position, String menuName) {
        this.pageId = pageId;
        this.position = position;
        this.menuName = menuName;
    }

    public Nav(int id, int pageId, int position, String menuName) {
        this.id = id;
        this.pageId = pageId;
        this.position = position;
        this.menuName = menuName;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Nav other = (Nav) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.pageId != other.pageId) {
            return false;
        }
        if (this.position != other.position) {
            return false;
        }
        if (!Objects.equals(this.menuName, other.menuName)) {
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

    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
}
