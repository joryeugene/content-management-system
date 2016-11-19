package com.teamexcalibur.dao;

import com.teamexcalibur.dto.Nav;
import com.teamexcalibur.dto.Page;
import com.teamexcalibur.dto.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PageDaoInMemImpl implements PageDao {

    private final Map<Integer, Page> pageMap = new HashMap<>();
    private static int idCounter = 0;
    
    private final Map<Integer, Nav> navMap = new HashMap<>();
    private static int idCounterNav = 0;

    public PageDaoInMemImpl() {
        addPage(new Page(new User("user1@example.com", "User1", "ROLE_ADMIN", "/img/avatar.png", "password"),
                "About Us", "Content of the about me page here"));
        addNav(new Nav(pageMap.get(0).getId(), 2, "About Us"));
        addPage(new Page(new User("user2@example.com", "User2", "ROLE_WRITER", "/img/avatar.png", "password"),
                "Contact", "Content of the about me page here"));
        addNav(new Nav(pageMap.get(1).getId(), 4, "Contact"));
        
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
        Comparator<Nav> byPosition = (nav1, nav2) -> Integer.compare(
            nav1.getPosition(), nav2.getPosition());
        List<Nav> c = navMap.values().stream().sorted(byPosition).collect(Collectors.toList());
        return new ArrayList(c);
    }

    @Override
    public Nav addNav(Nav nav) {
        nav.setId(idCounterNav);
        idCounterNav++;
        navMap.put(nav.getId(), nav);
        return nav;
    }

    @Override
    public void deleteNav(int id) {
        navMap.remove(id);
    }

    @Override
    public void updateNav(Nav nav) {
        navMap.put(nav.getId(), nav);
    }

    @Override
    public Nav getNavById(int id) {
        return navMap.get(id);
    }

}
