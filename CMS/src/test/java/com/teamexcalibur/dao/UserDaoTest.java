/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamexcalibur.dao;

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

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class UserDaoTest {
    
    private UserDao dao;
    
    public UserDaoTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-application-context.xml");
        dao = ctx.getBean("userDao", UserDaoMemoryImpl.class);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
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
      
    }

    /**
     * Test of deleteUser method, of class UserDao.
     */
    @Test
    public void testDeleteUser() {
        System.out.println("deleteUser");
        int id = 0;
        UserDao instance = new UserDaoImpl();
        instance.deleteUser(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateUser method, of class UserDao.
     */
    @Test
    public void testUpdateUser() {
        System.out.println("updateUser");
        User user = null;
        UserDao instance = new UserDaoImpl();
        instance.updateUser(user);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserById method, of class UserDao.
     */
    @Test
    public void testGetUserById() {
        System.out.println("getUserById");
        int id = 0;
        UserDao instance = new UserDaoImpl();
        User expResult = null;
        User result = instance.getUserById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllUsers method, of class UserDao.
     */
    @Test
    public void testGetAllUsers() {
        System.out.println("getAllUsers");
        UserDao instance = new UserDaoImpl();
        List<User> expResult = null;
        List<User> result = instance.getAllUsers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class UserDaoImpl implements UserDao {

        public User addUser(User user) {
            return null;
        }

        public void deleteUser(int id) {
        }

        public void updateUser(User user) {
        }

        public User getUserById(int id) {
            return null;
        }

        public List<User> getAllUsers() {
            return null;
        }
    }
    
}
