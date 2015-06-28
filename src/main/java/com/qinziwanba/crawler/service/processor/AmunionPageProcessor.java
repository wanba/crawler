package com.qinziwanba.crawler.service.processor;

import com.qinziwanba.commons.WanbaConstants;
import com.qinziwanba.commons.WanbaLogger;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * Created by wangzhiguo on 15/6/27.
 */
@Service(value = "amunionPageProcessor")
public class AmunionPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(3000);

    public void process(Page page) {

        WanbaLogger.info("process meituan page, url={}", page.getUrl().toString());

        List<String> shops = page.getHtml().links().all();
        for (String shop : shops) {
            if (shop.startsWith("http://www.amunion.com/detail/detail_distribution.jsp?factory_id=")) {
                page.addTargetRequest(shop);
            }
        }

        String name = page.getHtml().css("body > div.content.width > div.cdfb_list_left > div.fb_c_t > div:nth-child(2) > h1", "text").toString();
        if (name == null) {
            //skip this page
            page.setSkip(true);
            WanbaLogger.warn("skip page process, url={}", page.getUrl().toString());
        }
        page.putField(WanbaConstants.PAGE_NAME, name);

        String address = page.getHtml().xpath("/html/body/div[4]/div[3]/div[1]/div[2]/span/text()[1]").toString();
        page.putField(WanbaConstants.PAGE_ADDRESS, address);

        String square = page.getHtml().xpath("/html/body/div[4]/div[3]/div[1]/div[2]/span/text()[2]").toString();
        page.putField(WanbaConstants.PAGE_SQUARE, square);


    }

    public Site getSite() {
        return site;
    }

}
