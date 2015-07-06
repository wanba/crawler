package com.qinziwanba.crawler.dao;

import com.qinziwanba.crawler.domain.MeituanPage;
import org.springframework.stereotype.Repository;

/**
 * Created by wangzhiguo on 15/6/28.
 */
@Repository
public class MeituanDao extends AbstractDao {


    private final String SQL_MEITUAN_INSERT = "insert into `meituan_data`(`name`, `address`, `tel`, `category`," +
            " `rating`, `consume_count`, `rating_count`, `updated_at`) values(?, ?, ?, ?, ?, ?, ?, ?);";

    public MeituanPage insert(String name, String address, String tel, String category, String rating, String consumeCount, String ratingCount) {

        Long cur = System.currentTimeMillis();

        jdbcTemplate.update(SQL_MEITUAN_INSERT, name, address, tel, category, rating, consumeCount, ratingCount, cur);

        MeituanPage page = new MeituanPage(name, address, tel, category, rating, consumeCount, ratingCount);
        page.setUpdatedAt(cur);
        return page;
    }
}
