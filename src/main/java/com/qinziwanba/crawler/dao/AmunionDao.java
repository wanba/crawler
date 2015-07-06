package com.qinziwanba.crawler.dao;

import com.qinziwanba.crawler.domain.AmunionPage;

/**
 * Created by wangzhiguo on 15/6/28.
 */
public class AmunionDao extends AbstractDao {

    private final String SQL_AMUNION_INSERT = "insert into `amunion_data` (`name`, `address`, `square`, `updated_at`) " +
            "values (?, ?, ?, ?)";
    
    public AmunionPage insert(String name, String address, String square) {

        Long cur = System.currentTimeMillis();

        jdbcTemplate.update(SQL_AMUNION_INSERT, name, address, square, cur);

        AmunionPage page = new AmunionPage(name, address, square);
        page.setUpdatedAt(cur);
        return page;
    }

}
