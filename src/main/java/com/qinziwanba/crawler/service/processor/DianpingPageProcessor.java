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
@Service(value = "dianpingPageProcessor")
public class DianpingPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(3000);

    public void process(Page page) {

        WanbaLogger.info("process dianping page, url={}", page.getUrl().toString());

        List<String> shops = page.getHtml().links().all();
        for (String shop : shops) {
            if (shop.startsWith("http://www.dianping.com/shop/")) {
                page.addTargetRequest(shop);
            }
        }

        String name = page.getHtml().css("#J_boxDetail > div > div.shop-name > h1", "text").toString();
        if (name == null) {
            //skip this page
            page.setSkip(true);
            WanbaLogger.warn("skip page process, url={}", page.getUrl().toString());
        }
        page.putField(WanbaConstants.PAGE_NAME, name);


        String address = page.getHtml().css("#J_boxDetail > div > div.shop-addr > span", "text").toString();
        page.putField(WanbaConstants.PAGE_ADDRESS, address);

        String tel = page.getHtml().css("#J_boxDetail > div > div.shopinfor > p > span", "text").toString();
        page.putField(WanbaConstants.PAGE_TEL, tel);

        String category = page.getHtml().css("#main-body > div.breadcrumb > b:nth-child(4) > a > span", "text").toString();
        page.putField(WanbaConstants.PAGE_CATEGORY, category);


        String rating = page.getHtml().css("#J_boxDetail > div > div.comment-rst > span > meta", "text").toString();
        page.putField(WanbaConstants.PAGE_RATING, rating);

        String ratingCount = page.getHtml().css("#J_boxDetail > div > div.comment-rst > div > a > span", "text").toString();
        page.putField(WanbaConstants.PAGE_RATING_COUNT, ratingCount);

        String avgPrice = page.getHtml().css("#J_boxDetail > div > div.comment-rst > div > span > em","text").toString();
        page.putField(WanbaConstants.PAGE_AVG_PRICE,avgPrice);

        String openingTime = page.getHtml().css("#J_boxDetail > div > div.more-class > p:nth-child(2) > span","text").toString();
        page.putField(WanbaConstants.PAGE_OPENING_TIME, openingTime);

    }

    public Site getSite() {
        return site;
    }

}
