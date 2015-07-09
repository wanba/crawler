package com.qinziwanba.api.dao;

import com.qinziwanba.api.domain.Child;
import com.qinziwanba.commons.WanbaLogger;
import com.qinziwanba.crawler.dao.AbstractDao;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.CheckedInputStream;

/**
 * Created by wangzhiguo on 15/7/5.
 */
@Repository
public class ChildDao extends AbstractDao {

    private final String SQL_CHILD_INSERT = "insert into child(`pid`, `name`, `age`, `gender`, `updated_at`) value (?, ?, ?, ?, ?)" +
            " on duplicate key update `name` = ?, `age` = ?, `gender` = ?, `updated_at` = ?;";

    private final String SQL_CHILD_SELECT_BY_PARENT = "select `pid`, `name`, `age`, `gender`, `updated_at` as updatedAt from child where pid = ? ;";


    public Child addChild(String pid,String name, int age, String gender)  {
        WanbaLogger.debug("{} add child, pid={} name={} age={} gender={}", this.getClass(), pid, name, age, gender);

        Long cur = System.currentTimeMillis();
        jdbcTemplate.update(SQL_CHILD_INSERT, pid, name, age, gender,cur, name, age, gender, cur);
        Child child = new Child(pid,name,age,gender);
        return child;
    }

    public List<Child> selectChildByPid(String pid) {
        WanbaLogger.debug("{} select child by pid, pid={}", this.getClass(), pid);

        List<Child> children = new ArrayList<Child>();
        List<Map<String,Object>> list = jdbcTemplate.queryForList(SQL_CHILD_SELECT_BY_PARENT, pid);
        for (Map<String,Object> item : list) {
            Child child = new Child(pid,String.valueOf(item.get("name")), (Integer) item.get("age"), String.valueOf(item.get("gender")));
            children.add(child);
        }
        return children;
    }

}
