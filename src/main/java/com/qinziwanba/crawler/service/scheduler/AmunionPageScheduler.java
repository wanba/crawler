package com.qinziwanba.crawler.service.scheduler;

import com.qinziwanba.commons.WanbaLogger;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangzhiguo on 15/6/28.
 */
@Component(value = "amunionPageScheduler")
public class AmunionPageScheduler extends PageScheduler {

    protected String URL = "http://www.amunion.com/list/list_distribution.jsp?";
    protected String SPLIT = "/";

    @Resource(name = "amunionPipeline")
    private Pipeline pipeline;

    @Resource(name = "amunionPageProcessor")
    private PageProcessor pageProcessor;

    /**
     *
     */
    public List<String> getCategories() {
        List<String> categraies = new ArrayList<String>();
        for (int area = 10011 ; area <= 10073; area++) {
            categraies.add("area_id=" + area);
        }

        WanbaLogger.info("init amunion categraies={}", categraies);
        return categraies;
    }

    /**
     *
     */
    public List<String> getDirections() {
        List<String> directions = new ArrayList<String>();

        WanbaLogger.info("init amunion directions={}", directions);
        return directions;
    }
}
