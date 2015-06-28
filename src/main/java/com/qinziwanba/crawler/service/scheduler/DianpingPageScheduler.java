package com.qinziwanba.crawler.service.scheduler;

import com.qinziwanba.commons.WanbaLogger;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangzhiguo on 15/6/28.
 */
@Component(value = "dianpingPageScheduler")
public class DianpingPageScheduler extends PageScheduler{

    protected String URL = "http://www.dianping.com/search/category/2/70/";
    protected String SPLIT = "";

    @Resource(name = "dianpingPipeline")
    private Pipeline dianpingPipeline;

    @Resource(name = "dianpingPageProcessor")
    private PageProcessor dianpingPageProcessor;


    public List<String> getCategories() {
        List<String> categories = new ArrayList<String>();

        categories.add("g27760");
        categories.add("g27767");

        WanbaLogger.info("get dianping categories, categories={}", categories);
        return categories;
    }

    public List<String> getDirections() {
        List<String> directions = new ArrayList<String>();
        directions.add("r14");
        directions.add("r15");
        directions.add("r16");
        directions.add("r17");
        directions.add("r18");
        directions.add("r20");
        directions.add("r328");
        directions.add("r9158");

        WanbaLogger.info("get dianping directions, directions={}", directions);
        return directions;
    }
}
