/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamexcalibur.dao;

import com.teamexcalibur.dto.Nav;
import com.teamexcalibur.dto.Page;
import com.teamexcalibur.dto.User;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author apprentice
 */
public class PageDaoTest {

    private PageDao dao;
    private UserDao udao;
    private User u1;
    private User u2;

    public PageDaoTest() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("pageDao", PageDao.class);
        // this must be db impl if postdao uses db -- foreign key constraints
        udao = ctx.getBean("userDao", UserDao.class);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        JdbcTemplate jdbcTemplate
                = ctx.getBean("jdbcTemplate", org.springframework.jdbc.core.JdbcTemplate.class);
        jdbcTemplate.update("Delete from `Nav`");
        jdbcTemplate.update("Delete from `Page`");
        jdbcTemplate.update("Delete from `PostHashtag`");
        jdbcTemplate.update("Delete from `Post`");
        jdbcTemplate.update("Delete from `Category`");
        jdbcTemplate.update("Delete from `User`");
        u1 = udao.addUser(new User(0, "x@u.com", "UserX", "admin", "https://pic0", "userPw1"));
        u2 = udao.addUser(new User(1, "y@u.com", "UserY", "admin", "https://pic1", "userPw2"));
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addPage method, of class PageDao.
     */
    @Test
    public void testAddGetDeletePageNav() {
        System.out.println("testAddGetDeletePageNav");

        Page pg = new Page(u1, "Title 0", "Content 0");
        pg = dao.addPage(pg);
        Nav nv = new Nav(pg.getId(), 1, "menu 0");
        nv = dao.addNav(nv);
        Page pageFromDb = dao.getPageById(pg.getId());
        assertEquals(pg, pageFromDb);
        Nav navFromDb = dao.getNavById(nv.getId());
        assertEquals(nv, navFromDb);
        dao.deleteNav(nv.getId());
        dao.deletePage(pg.getId());
        assertNull(dao.getPageById(pg.getId()));
        assertNull(dao.getNavById(nv.getId()));
    }

    /**
     * Test of updatePage method, of class PageDao.
     */
    @Test
    public void testUpdatePageNav() {
        System.out.println("testUpdatePageNav");

        Page pg = new Page(u1, "Title 0", "Content 0");
        pg = dao.addPage(pg);
        Nav nv = new Nav(pg.getId(), 1, "menu 0");
        nv = dao.addNav(nv);
        pg.setContent("new Content");
        nv.setMenuName("new menu name");
        dao.updateNav(nv);
        dao.updatePage(pg);
        Page pageFromDb = dao.getPageById(pg.getId());
        assertEquals(pg, pageFromDb);
        Nav navFromDb = dao.getNavById(nv.getId());
        assertEquals(nv, navFromDb);
    }

    /**
     * Test of getAllPages method, of class PageDao.
     */
    @Test
    public void testGetAllPagesNavs() {
        System.out.println("getAllPagesNavs");
        Page pg = new Page(u1, "Title 0", "Content 0");
        pg = dao.addPage(pg);
        Nav nv = new Nav(pg.getId(), 0, "menu 0");
        nv = dao.addNav(nv);
        pg = new Page(u1, "Title 1", "Content 1");
        pg = dao.addPage(pg);
        nv = new Nav(pg.getId(), 1, "menu 1");
        nv = dao.addNav(nv);
        pg = new Page(u2, "Title 2", "Content 2");
        pg = dao.addPage(pg);
        nv = new Nav(pg.getId(), 2, "menu 2");
        nv = dao.addNav(nv);  
        
        List<Page> pList = dao.getAllPages();
        assertEquals(3, pList.size());
        List<Nav> nList = dao.getAllNavs();
        assertEquals(3, nList.size());
    }

}
