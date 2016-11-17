package com.teamexcalibur.dao;

import com.teamexcalibur.dto.Nav;
import com.teamexcalibur.dto.Page;
import com.teamexcalibur.dto.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageDaoInMemImpl implements PageDao {

    private final Map<Integer, Page> pageMap = new HashMap<>();
    private static int idCounter = 0;

    public PageDaoInMemImpl() {
        addPage(new Page(new User("user1@example.com", "User1", "ROLE_ADMIN", "/img/avatar.png", "password"),
                "About Us", "Content of the about me page here"));
       addPage(new Page(new User("user2@example.com", "User2", "ROLE_WRITER", "/img/avatar.png", "password"),
                "About Us", "Content of the about me page here"));
    }

    @Override
    public Page addPage(Page page) {
        page.setId(idCounter);
        idCounter++;
        pageMap.put(page.getId(), page);
        return page;
    }

    @Override
    public void deletePage(int id) {
        pageMap.remove(id);
    }

    @Override
    public void updatePage(Page page) {
        pageMap.put(page.getId(), page);
    }

    @Override
    public Page getPageById(int id) {
        return pageMap.get(id);
    }

    @Override
    public List<Page> getAllPages() {
        Collection<Page> c = pageMap.values();
        return new ArrayList(c);
    }

    @Override
    public List<Nav> getAllNavs() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
