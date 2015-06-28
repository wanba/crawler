package com.qinziwanba.crawler.service;

import com.qinziwanba.commons.WanbaLogger;
import com.qinziwanba.crawler.service.scheduler.PageScheduler;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangzhiguo on 15/6/27.
 */
@Component
public class SpiderManager {

    private Map<String, Spider> spiderMap = new HashMap<String, Spider>();


    @Resource(name = "meituanPageScheduler")
    private PageScheduler meituanPageScheduler;

    @Resource(name = "dianipingPageScheduler")
    private PageScheduler dianpingPageScheduler;

    @Resource(name = "amunionPageScheduler")
    private PageScheduler amunionPageScheduler;

    @PostConstruct
    public void init() {

        // init meituan
        Spider meituanSpider = meituanPageScheduler.getSpider();
        spiderMap.put("meituan", meituanSpider);

        // init dianping
        Spider dianpingSpider = dianpingPageScheduler.getSpider();
        spiderMap.put("dianping",dianpingSpider);

        // init amunion
        Spider amunionSpider = amunionPageScheduler.getSpider();
        spiderMap.put("amunion", amunionSpider);

        // startAll spider
//        startAll();
    }


    /**
     * 启动所有spider
     */
    public void startAll() {
        WanbaLogger.info("{} startAll", this.getClass());
        for (String splider : spiderMap.keySet()) {
            WanbaLogger.info("start spider " + splider);
            spiderMap.get(splider).start();
        }
    }

    /**
     * 停止所有spider
     */
    public void stopAll() {
        WanbaLogger.info("{} stopAll", this.getClass());
        for (String splider : spiderMap.keySet()) {
            WanbaLogger.info("start spider " + splider);
            spiderMap.get(splider).stop();
        }
    }

}
