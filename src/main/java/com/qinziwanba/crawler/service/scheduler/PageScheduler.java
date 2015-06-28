package com.qinziwanba.crawler.service.scheduler;

import com.qinziwanba.commons.WanbaLogger;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * Created by wangzhiguo on 15/6/28.
 */
public abstract class PageScheduler {

    protected String URL;
    protected String SPLIT;
    protected PageProcessor pageProcessor;
    protected Pipeline pipeline;

    protected int threadNum = 1;

    public Spider getSpider() {

        List<String> categories = getCategories();
        List<String> directions = getDirections();

        Spider meituanSpider = Spider.create(pageProcessor);
        for (String cate : categories) {
            for (String direct : directions) {
                String url = URL + cate + SPLIT + direct;
                meituanSpider.addUrl(url);
            }
        }
        meituanSpider.addPipeline(pipeline);
        meituanSpider.thread(threadNum);

        WanbaLogger.info("init meituan spider, threadNum={}", threadNum);
        return meituanSpider;

    }

    public abstract List<String> getCategories();

    public abstract List<String> getDirections();
}
