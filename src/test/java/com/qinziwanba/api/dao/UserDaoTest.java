package com.qinziwanba.api.dao;

import com.qinziwanba.AbstractTest;
import com.qinziwanba.api.domain.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by wangzhiguo on 15/7/9.
 */
public class UserDaoTest extends AbstractTest {

    @Autowired
    private UserDao userDao;

    @Before
    public void setUp() throws Exception {
        testInsertUser();
    }

    public void testInsertUser() throws Exception {
        User user = userDao.insertUser("u1234","uname","12345678","man");
        Assert.assertNotNull(user);
    }

    @Test
    public void testGetUser() throws Exception {
        User user = userDao.getUser("u1234");
        Assert.assertNotNull(user);
    }

    @Test
    public void testGetUserByPhone() throws Exception {
        User user = userDao.getUserByPhone("12345678");
        Assert.assertNotNull(user);
    }

    @Test
    public void testResetPassword() throws Exception {
        userDao.resetPassword("u1234","123456");

        User user = userDao.getUser("u1234");
        Assert.assertEquals("123456", user.getPassword());
    }

    @Test
    public void testUpdatePassword() throws Exception {
        userDao.updatePassword("u1234", "123456", "654321");

        User user = userDao.getUser("u1234");
        Assert.assertEquals("654321", user.getPassword());
    }
}