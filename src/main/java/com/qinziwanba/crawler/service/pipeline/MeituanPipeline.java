package com.qinziwanba.crawler.service.pipeline;

import com.qinziwanba.commons.WanbaConstants;
import com.qinziwanba.commons.WanbaLogger;
import com.qinziwanba.crawler.dao.MeituanDao;
import com.qinziwanba.crawler.domain.MeituanPage;
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

        String name = resultItems.get(WanbaConstants.PAGE_NAME);
        String address = resultItems.get(WanbaConstants.PAGE_ADDRESS);
        String tel = resultItems.get(WanbaConstants.PAGE_TEL);
        String category = resultItems.get(WanbaConstants.PAGE_CATEGORY);
        String rating = resultItems.get(WanbaConstants.PAGE_RATING);
        String consumeCount = resultItems.get(WanbaConstants.PAGE_CONSUME_COUNT);
        String ratingCount = resultItems.get(WanbaConstants.PAGE_RATING_COUNT);

        // write into mysql
        MeituanPage meituanPage = meituanDao.insert(name, address, tel, category, rating, consumeCount, ratingCount);
        WanbaLogger.info("get meituan page result, page={}", meituanPage);
    }
}
