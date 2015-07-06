package com.qinziwanba.crawler.dao;

import com.qinziwanba.crawler.domain.DianpingPage;
import org.springframework.stereotype.Repository;

/**
 * Created by wangzhiguo on 15/6/28.
 */
@Repository
public class DianpingDao extends AbstractDao {

    private final String SQL_DIANPING_INSERT = "insert into dianping_data () values ();";

    public DianpingPage insert(String name, String address, String tel, String category, String rating, String ratingCount, String avgPrice, String openingTime) {

        Long cur = System.currentTimeMillis();

        jdbcTemplate.update(SQL_DIANPING_INSERT, name, address, tel, category, rating, ratingCount, avgPrice, openingTime, cur);

        DianpingPage page = new DianpingPage(name, address, tel, category, rating, ratingCount, avgPrice, openingTime);

        return page;
    }
}
