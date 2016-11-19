/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamexcalibur.dao;

import com.teamexcalibur.dto.Category;
import com.teamexcalibur.dto.Post;
import com.teamexcalibur.dto.User;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

/**
 *
 * @author apprentice
 */
public class PostDaoTest {

    private PostDao dao;
    private UserDao udao;
    private User u1;
    private User u2;

    public PostDaoTest() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("postDao", PostDao.class);
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

    @Test
    public void addGetDeletes() {
        List<String> oneTag = new ArrayList<>();
        oneTag.add("#YoMama");
        Category cat = dao.addCategory(new Category("Monday"));

        Post nc = new Post(u1, "Title 0", "Content 0", 0,
                null, null, cat, oneTag, false);
        dao.addPost(nc);
        Post fromDb = dao.getPostById(nc.getId());
        assertEquals(nc, fromDb);
        dao.deletePost(nc.getId());
        assertNull(dao.getPostById(nc.getId()));
    }

    @Test
    public void addUpdatePost() {
        List<String> oneTag = new ArrayList<>();
        oneTag.add("#YoMama");
        Category cat = dao.addCategory(new Category("Monday"));

        Post nc = new Post(u1, "Title 0", "Content 0", 0,
                null, null, cat, oneTag, false);
        dao.addPost(nc);
        nc.setContent("Joan Wiggums");
        dao.updatePost(nc);
        Post fromDb = dao.getPostById(nc.getId());
        assertEquals(nc, fromDb);
    }

    @Test
    public void getAllUsed() {
        List<String> oneTag = new ArrayList<>();
        oneTag.add("#YoMama");
        List<String> twoTags = new ArrayList<>();
        twoTags.add("#YoPapa");
        twoTags.add("#YoJory");

        Category c1 = dao.addCategory(new Category("Monday"));
        Category c2 = dao.addCategory(new Category("Tuesday"));
        Category c3 = dao.addCategory(new Category("Wednesday"));
        Category c4 = dao.addCategory(new Category("Thursday"));
        dao.addCategory(new Category("Friday"));

        Post nc = new Post(u1, "Title 0", "Content 0", 0,
                null, null, c1, oneTag, false);
        dao.addPost(nc);
        dao.addPost(new Post(u2, "Title 1", "Content 1", 0,
                null, null, c2, twoTags, false));
        dao.addPost(new Post(u1, "Title 2", "Content 2", 0,
                null, null, c3, oneTag, false));
        dao.addPost(new Post(u2, "Title 3", "Content 3", 0,
                null, null, c2, twoTags, false));
        dao.addPost(new Post(u1, "Title 4", "Content 4", 0,
                null, null, c3, oneTag, false));
        List<Category> cList = dao.getAllCategories();
        assertEquals(5, cList.size());
        List<String> hList = dao.getAllHashtags();
        assertEquals(3, hList.size());
        List<Post> pList = dao.getAllPosts();
        assertEquals(5, pList.size());
        cList = dao.getUsedCategories();
        assertEquals(3, cList.size());
        hList = dao.getUsedHashtags();
        assertEquals(3, hList.size());
        pList = dao.getPostsByCategoryId(c2.getId());
        assertEquals(2, pList.size());
        pList = dao.getPostsByHashtag("#YoPaPa");
        assertEquals(2, pList.size());
        dao.deleteCategory(c4.getId());
        cList = dao.getAllCategories();
        assertEquals(4, cList.size());
        
    }
}