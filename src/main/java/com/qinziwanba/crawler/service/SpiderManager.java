package com.qinziwanba.crawler.service;

import com.qinziwanba.commons.WanbaLogger;
import us.codecraft.webmagic.Spider;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangzhiguo on 15/6/27.
 */
public class SpiderManager {

    private Map<String,Spider> spliderMap = new HashMap<String, Spider>();

    public void init() {

    }

    public void startAll() {
        for (String splider : spliderMap.keySet()) {
            WanbaLogger.info("start spider " + splider);
            spliderMap.get(splider).start();
        }
    }

    public void stopAll() {
        for (String splider : spliderMap.keySet()) {
            WanbaLogger.info("start spider " + splider);
            spliderMap.get(splider).stop();
        }
    }


}
