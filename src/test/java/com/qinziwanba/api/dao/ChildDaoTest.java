package com.qinziwanba.api.dao;

import com.qinziwanba.AbstractTest;
import com.qinziwanba.api.domain.Child;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by wangzhiguo on 15/7/9.
 */
public class ChildDaoTest extends AbstractTest {

    @Autowired
    private ChildDao childDao;

    @Before
    public void setUp() throws Exception {
        testAddChild();
    }

    public void testAddChild() throws Exception {
        Child child = childDao.addChild("p1234", "pname", 3, "boy");

        Assert.assertNotNull(child);
        Assert.assertEquals("p1234", child.getPid());

    }

    @Test
    public void testSelectChildByPid() throws Exception {
        List<Child> childList = childDao.selectChildByPid("p1234");
        Assert.assertNotNull(childList);
        Assert.assertFalse(childList.isEmpty());
    }
}