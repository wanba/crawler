package com.qinziwanba.crawler.service.pipeline;

import com.qinziwanba.commons.WanbaConstants;
import com.qinziwanba.commons.WanbaLogger;
import com.qinziwanba.crawler.dao.AmunionDao;
import com.qinziwanba.crawler.dao.MeituanDao;
import com.qinziwanba.crawler.domain.AmunionPage;
import com.qinziwanba.crawler.domain.MeituanPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;


/**
 * Created by wangzhiguo on 15/6/27.
 */
@Service(value = "amunionPipeline")
public class AmunionPipeline implements Pipeline {

    @Autowired
    private AmunionDao amunionDao;

    public void process(ResultItems resultItems, Task task) {
        WanbaLogger.info("url " + resultItems.getRequest().getUrl() + " result " + resultItems);

        String name = resultItems.get(WanbaConstants.PAGE_NAME);
        String address = resultItems.get(WanbaConstants.PAGE_ADDRESS);
        String square = resultItems.get(WanbaConstants.PAGE_SQUARE);

        // write into mysql
        AmunionPage amunionPage = amunionDao.insert(name, address, square);
        WanbaLogger.info("get amunion page result, page={}", amunionPage);
    }
}
