package com.qinziwanba.api.dao;

import com.qinziwanba.api.domain.Child;
import com.qinziwanba.commons.WanbaLogger;
import com.qinziwanba.crawler.dao.AbstractDao;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.List;

/**
 * Created by wangzhiguo on 15/7/5.
 */
@Repository
public class ChildDao extends AbstractDao {

    private final String SQL_CHILD_INSERT = "insert into child(`pid`, `name`, `age`, `gender`, `updated_at`) value (?, ?, ?, ?, ?)";

    private final String SQL_CHILD_SELECT_BY_PARENT = "select `pid`, `name`, `age`, `gender`, `updated_at` as updatedAt from child where parent = ? ;";


    public Child addChild(String pid,String name, int age, String gender)  {
        WanbaLogger.debug("{} add child, pid={} name={} age={} gender={}", this.getClass(), pid, name, age, gender);

        jdbcTemplate.update(SQL_CHILD_INSERT, pid, name, age, gender);
        Child child = new Child(pid,name,age,gender);
        return child;
    }

    public List<Child> selectChildByPid(String pid) {
        WanbaLogger.debug("{} select child by pid, pid={}", this.getClass(), pid);

        return jdbcTemplate.queryForList(SQL_CHILD_SELECT_BY_PARENT, new Object[]{pid}, Child.class);
    }

}
