/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamexcalibur.dao;

import com.teamexcalibur.dto.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class UserDaoTest {

    private UserDao dao;
    private Map<Integer, User> testUserMap = new HashMap<>();
    //private List<User> userList = new ArrayList<>();

    public UserDaoTest() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("userDao", UserDao.class);
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
        jdbcTemplate.update("Delete from `User`");
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addUser method, of class UserDao.
     */
    @Test
    public void testAddUser() {
        System.out.println("addUser");
        //Create a new user
        User newUser = new User();
        newUser.setAuthority("ROLE_ADMIN");
        newUser.setDisplayName("Test User");
        newUser.setAvatarUrl("img/avatar.png");
        newUser.setEmail("hacker@hacker.com");
        newUser.setPassword("password");

        newUser = dao.addUser(newUser);
        assertEquals(newUser, dao.getUserById(newUser.getId()));

    }

    /**
     * Test of deleteUser method, of class UserDao.
     */
    @Test
    public void testDeleteUser() {
        System.out.println("deleteUser");
        //Create a new user
        User newUser = new User();
        newUser.setId(2);
        newUser.setAuthority("ROLE_ADMIN");
        newUser.setDisplayName("Test User");
        newUser.setAvatarUrl("img/avatar.png");
        newUser.setEmail("hacker@hacker.com");
        newUser.setPassword("password");

        newUser = dao.addUser(newUser);
        // make sure it gets into the dao
        assertEquals(newUser, dao.getUserById(newUser.getId()));

        dao.deleteUser(newUser.getId());
        assertNull(dao.getUserById(newUser.getId()));
    }

    /**
     * Test of updateUser method, of class UserDao.
     */
    @Test
    public void testUpdateUser() {
        System.out.println("updateUser");
        User newUser = new User();
        newUser.setId(7);
        newUser.setAuthority("ROLE_ADMIN");
        newUser.setDisplayName("Test User");
        newUser.setAvatarUrl("img/avatar.png");
        newUser.setEmail("hacker@hacker.com");
        newUser.setPassword("password");

        newUser = dao.addUser(newUser);
        newUser.setDisplayName("Updated name");
        dao.updateUser(newUser);

        assertTrue(dao.getUserById(newUser.getId()).getDisplayName().equals("Updated name"));

    }

    /**
     * Test of getUserById method, of class UserDao.
     */
    @Test
    public void testGetUserById() {
        System.out.println("getUserById");
        User newUser = new User();
        newUser.setId(7);
        newUser.setAuthority("ROLE_ADMIN");
        newUser.setDisplayName("Test User");
        newUser.setAvatarUrl("img/avatar.png");
        newUser.setEmail("hacker@hacker.com");
        newUser.setPassword("password");
        newUser = dao.addUser(newUser);
        User testUser = dao.getUserById(newUser.getId());

        assertTrue(testUser.getDisplayName().equals("Test User"));
    }

    /**
     * Test of getAllUsers method, of class UserDao.
     */
    @Test
    public void testGetAllUsers() {
        System.out.println("getAllUsers");
        User newUser = new User();
        newUser.setId(2);
        newUser.setAuthority("ROLE_ADMIN");
        newUser.setDisplayName("Test User");
        newUser.setAvatarUrl("img/avatar.png");
        newUser.setEmail("hacker@hacker.com");
        newUser.setPassword("password");
        dao.addUser(newUser);
        newUser = new User();
        newUser.setId(2);
        newUser.setAuthority("ROLE_ADMIN");
        newUser.setDisplayName("Test User1");
        newUser.setAvatarUrl("img/avatar.png");
        newUser.setEmail("hacker@hacker.com");
        newUser.setPassword("password");
        dao.addUser(newUser);

        newUser = new User();
        newUser.setId(2);
        newUser.setAuthority("ROLE_ADMIN");
        newUser.setDisplayName("Test User2");
        newUser.setAvatarUrl("img/avatar.png");
        newUser.setEmail("hacker@hacker.com");
        newUser.setPassword("password");
        dao.addUser(newUser);

        List<User> getAllTestList = dao.getAllUsers();

        assertEquals(3, getAllTestList.size());

    }

}
