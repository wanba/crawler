package com.qinziwanba.crawler.dao.impl;

import com.qinziwanba.crawler.dao.MeituanDao;
import com.qinziwanba.crawler.domain.MeituanPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

/**
 * Created by wangzhiguo on 15/6/28.
 */
@Repository
public class MeituanDaoImpl implements MeituanDao {


    private final String SQL_MEITUAN_INSERT = "insert into `meituan_data`(`name`, `address`, `tel`, `category`, `rating`, `consume_count`, `rating_count`, `updated_at`) values(?, ?, ?, ?, ?, ?, ?, ?);";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public MeituanPage insert(String name, String address, String tel, String category, String rating, String consumeCount, String ratingCount) {

        Long cur = System.currentTimeMillis();

        jdbcTemplate.update(SQL_MEITUAN_INSERT, name, address, tel, category, rating, consumeCount, ratingCount, cur);

        MeituanPage page = new MeituanPage(name,address,tel,category,rating,consumeCount,ratingCount);
        page.setUpdatedAt(cur);
        return page;
    }
}
