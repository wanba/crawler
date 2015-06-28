package com.qinziwanba.crawler.service.pipeline;

import com.qinziwanba.commons.WanbaConstants;
import com.qinziwanba.commons.WanbaLogger;
import com.qinziwanba.crawler.dao.DianpingDao;
import com.qinziwanba.crawler.domain.DianpingPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;


/**
 * Created by wangzhiguo on 15/6/27.
 */
@Service(value = "dianpingPipeline")
public class DianpingPipeline implements Pipeline {

    @Autowired
    private DianpingDao dianpingDao;


    public void process(ResultItems resultItems, Task task) {
        WanbaLogger.info("url " + resultItems.getRequest().getUrl() + " result " + resultItems);

        String name = resultItems.get(WanbaConstants.PAGE_NAME);
        String address = resultItems.get(WanbaConstants.PAGE_ADDRESS);
        String tel = resultItems.get(WanbaConstants.PAGE_TEL);
        String category = resultItems.get(WanbaConstants.PAGE_CATEGORY);
        String rating = resultItems.get(WanbaConstants.PAGE_RATING);
        String ratingCount = resultItems.get(WanbaConstants.PAGE_RATING_COUNT);
        String avgPrice = resultItems.get(WanbaConstants.PAGE_AVG_PRICE);
        String openingTime = resultItems.get(WanbaConstants.PAGE_OPENING_TIME);

        // write into mysql
        DianpingPage dianpingPage = dianpingDao.insert(name, address, tel, category, rating, ratingCount, avgPrice, openingTime);
        WanbaLogger.info("get dianping page result, page={}", dianpingPage);
    }
}
