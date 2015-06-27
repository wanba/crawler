package com.qinziwanba.crawler.service.processor;

import com.qinziwanba.commons.WanbaLogger;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * Created by wangzhiguo on 15/6/27.
 */
@Service(value = "meituanPageProcessor")
public class MeituanPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(3000);

    static public final String MEITUAN_NAME = "name";
    static public final String MEITUAN_ADDRESS = "address";
    static public final String MEITUAN_TEL = "tel";
    static public final String MEITUAN_CATEGORY = "category";
    static public final String MEITUAN_RATING = "rating";
    static public final String MEITUAN_CONSUME_COUNT = "consume_count";
    static public final String MEITUAN_RATING_COUNT = "rating_count";

    public void process(Page page) {

        WanbaLogger.info("process page, url={}", page.getUrl().toString());

        List<String> shops = page.getHtml().links().all();
        for (String shop : shops) {
            if (shop.startsWith("http://www.meituan.com/shop/")) {
                page.addTargetRequest(shop);
            }
        }

        String name = page.getHtml().css("#bd > div.summary.biz-box.fs-section.cf > div.fs-section__left > h2 > span", "text").toString();
        if (name == null) {
            //skip this page
            page.setSkip(true);
            WanbaLogger.warn("skip page process, url={}", page.getUrl().toString());
        }
        page.putField(MEITUAN_NAME, name);

        String address = page.getHtml().css("#bd > div.summary.biz-box.fs-section.cf > div.fs-section__left > p:nth-child(2) > span.geo", "text").toString();
        page.putField(MEITUAN_ADDRESS, address);

        String tel = page.getHtml().css("#bd > div.summary.biz-box.fs-section.cf > div.fs-section__left > p:nth-child(3)", "text").toString();
        page.putField(MEITUAN_TEL, tel);

        String category = page.getHtml().css("#bd > div.summary.biz-box.fs-section.cf > div.fs-section__right > div.info > div:nth-child(2) > a", "text").toString();
        page.putField(MEITUAN_CATEGORY, category);

        String rating = page.getHtml().css("#bd > div.summary.biz-box.fs-section.cf > div.fs-section__right > div.info > div:nth-child(1) > span.biz-level > strong", "text").toString();
        page.putField(MEITUAN_RATING, rating);

        String consumeCount = page.getHtml().css("#bd > div.summary.biz-box.fs-section.cf > div.fs-section__right > div.counts > div:nth-child(1) > span", "text").toString();
        page.putField(MEITUAN_CONSUME_COUNT, consumeCount);

        String ratingCount = page.getHtml().css("#bd > div.summary.biz-box.fs-section.cf > div.fs-section__right > div.counts > div:nth-child(2) > a", "text").toString();
        page.putField(MEITUAN_RATING_COUNT, ratingCount);

    }

    public Site getSite() {
        return site;
    }

}
