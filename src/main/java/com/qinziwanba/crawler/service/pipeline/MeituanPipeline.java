package com.qinziwanba.crawler.service.pipeline;

import com.qinziwanba.commons.WanbaLogger;
import com.qinziwanba.crawler.dao.MeituanDao;
import com.qinziwanba.crawler.domain.MeituanPage;
import com.qinziwanba.crawler.service.processor.MeituanPageProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;


/**
 * Created by wangzhiguo on 15/6/27.
 */
@Service(value = "meituanPipeline")
public class MeituanPipeline implements Pipeline {

    @Autowired
    private MeituanDao meituanDao;


    public void process(ResultItems resultItems, Task task) {
        WanbaLogger.info("url " + resultItems.getRequest().getUrl() + " result " + resultItems);

        String name = resultItems.get(MeituanPageProcessor.MEITUAN_NAME);
        String address = resultItems.get(MeituanPageProcessor.MEITUAN_ADDRESS);
        String tel = resultItems.get(MeituanPageProcessor.MEITUAN_TEL);
        String category = resultItems.get(MeituanPageProcessor.MEITUAN_CATEGORY);
        String rating = resultItems.get(MeituanPageProcessor.MEITUAN_RATING);
        String consumeCount = resultItems.get(MeituanPageProcessor.MEITUAN_CONSUME_COUNT);
        String ratingCount = resultItems.get(MeituanPageProcessor.MEITUAN_RATING_COUNT);

        // write into mysql
        MeituanPage meituanPage = meituanDao.insert(name, address, tel, category, rating, consumeCount, ratingCount);
        WanbaLogger.info("get page result, page={}", meituanPage);
    }
}
