package com.qinziwanba.crawler.dao;

import com.qinziwanba.crawler.domain.MeituanPage;

/**
 * Created by wangzhiguo on 15/6/28.
 */
public interface MeituanDao {

    public MeituanPage insert(String name, String address, String tel, String category, String rating,
                              String consumeCount, String ratingCount);
}
