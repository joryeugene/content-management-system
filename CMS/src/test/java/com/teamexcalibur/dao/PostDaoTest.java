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
    private 

    public PostDaoTest() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("postDao", PostDao.class);
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
        jdbcTemplate.update("Delete from `Category`");
        jdbcTemplate.update("Delete from `Post`");
        udao.addUser(new User(0, "x@u.com", "UserX", "admin", "https://pic0", "userPw1"));
        udao.addUser(new User(1, "y@u.com", "UserY", "admin", "https://pic1", "userPw2"));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void addGetDeletes() {
        List<String> oneTag = new ArrayList<>();
        oneTag.add("#YoMama");
//        List<String> twoTags = new ArrayList<>();
//        twoTags.add("#YoPapa");
//        twoTags.add("#YoJory");

        dao.addCategory(new Category("Monday"));
//        dao.addCategory(new Category("Tuesday"));
//        dao.addCategory(new Category("Wednesday"));
//        dao.addCategory(new Category("Thursday"));
//        dao.addCategory(new Category("Friday"));

        Post nc = new Post(udao.getUserById(0), "Title 0", "Content 0", 0,
                null, null, dao.getCategoryById(0), oneTag, false);
        dao.addPost(nc);
//        dao.addPost(new Post(udao.getUserById(1), "Title 1", "Content 1", 0,
//                null, null, dao.getCategoryById(1), twoTags, false));
//        dao.addPost(new Post(udao.getUserById(0), "Title 2", "Content 2", 0,
//                null, null, dao.getCategoryById(2), oneTag, false));
//        dao.addPost(new Post(udao.getUserById(1), "Title 3", "Content 3", 0,
//                null, null, dao.getCategoryById(1), twoTags, false));
//        dao.addPost(new Post(udao.getUserById(0), "Title 4", "Content 4", 0,
//                null, null, dao.getCategoryById(2), oneTag, false));
        Post fromDb = dao.getPostById(nc.getId());
        assertEquals(fromDb, nc);
        dao.deletePost(nc.getId());
        assertNull(dao.getPostById(nc.getId()));
    }

    @Test
    public void addUpdatePost() {
        List<String> oneTag = new ArrayList<>();
        oneTag.add("#YoMama");
        dao.addCategory(new Category("Monday"));

        Post nc = new Post(udao.getUserById(0), "Title 0", "Content 0", 0,
                null, null, dao.getCategoryById(0), oneTag, false);
        dao.addPost(nc);
        nc.setContent("Joan Wiggums");
        dao.updatePost(nc);
        Post fromDb = dao.getPostById(nc.getId());
        assertEquals(fromDb, nc);
    }

    @Test
    public void getAllUsed() {
        List<String> oneTag = new ArrayList<>();
        oneTag.add("#YoMama");
        List<String> twoTags = new ArrayList<>();
        twoTags.add("#YoPapa");
        twoTags.add("#YoJory");

        dao.addCategory(new Category("Monday"));
        dao.addCategory(new Category("Tuesday"));
        dao.addCategory(new Category("Wednesday"));
        dao.addCategory(new Category("Thursday"));
        dao.addCategory(new Category("Friday"));

        Post nc = new Post(udao.getUserById(0), "Title 0", "Content 0", 0,
                null, null, dao.getCategoryById(0), oneTag, false);
        dao.addPost(nc);
        dao.addPost(new Post(udao.getUserById(1), "Title 1", "Content 1", 0,
                null, null, dao.getCategoryById(1), twoTags, false));
        dao.addPost(new Post(udao.getUserById(0), "Title 2", "Content 2", 0,
                null, null, dao.getCategoryById(2), oneTag, false));
        dao.addPost(new Post(udao.getUserById(1), "Title 3", "Content 3", 0,
                null, null, dao.getCategoryById(1), twoTags, false));
        dao.addPost(new Post(udao.getUserById(0), "Title 4", "Content 4", 0,
                null, null, dao.getCategoryById(2), oneTag, false));
        List<Category> cList = dao.getAllCategories();
        assertEquals(cList.size(), 5);
        List<String> hList = dao.getAllHashtags();
        assertEquals(hList.size(), 3);
        List<Post> pList = dao.getAllPosts();
        assertEquals(pList.size(), 5);
        cList = dao.getUsedCategories();
        assertEquals(cList.size(), 3);
        hList = dao.getUsedHashtags();
        assertEquals(hList.size(), 3);
        pList = dao.getPostsByCategoryId(2);
        assertEquals(pList.size(), 2);
        pList = dao.getPostsByHashtag("#YoPaPa");
        assertEquals(pList.size(), 2);
        dao.deleteCategory(1);
        cList = dao.getAllCategories();
        assertEquals(cList.size(), 4);
        
    }
}
