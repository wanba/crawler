package com.qinziwanba.crawler.service;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * Created by wangzhiguo on 15/6/27.
 */
public class MeituanPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(3000);

    public void process(Page page) {

        List<String> shops = page.getHtml().links().all();
        for (String shop : shops) {
            if (shop.startsWith("http://www.meituan.com/shop/")) {
                page.addTargetRequest(shop);
            }
        }

        String name = page.getHtml().css("#bd > div.summary.biz-box.fs-section.cf > div.fs-section__left > h2 > span","text").toString();
        page.putField("name", name);
        if (page.getResultItems().get("name")==null){
            //skip this page
            page.setSkip(true);
        }

        String address = page.getHtml().css("#bd > div.summary.biz-box.fs-section.cf > div.fs-section__left > p:nth-child(2) > span.geo","text").toString();
        page.putField("address", address);

        String tel = page.getHtml().css("#bd > div.summary.biz-box.fs-section.cf > div.fs-section__left > p:nth-child(3)","text").toString();
        page.putField("tel",tel);

        String catagery = page.getHtml().css("#bd > div.summary.biz-box.fs-section.cf > div.fs-section__right > div.info > div:nth-child(2) > a","text").toString();
        page.putField("catagery",catagery);

        String rating = page.getHtml().css("#bd > div.summary.biz-box.fs-section.cf > div.fs-section__right > div.info > div:nth-child(1) > span.biz-level > strong","text").toString();
        page.putField("rating",rating);

        String consumeCount = page.getHtml().css("#bd > div.summary.biz-box.fs-section.cf > div.fs-section__right > div.counts > div:nth-child(1) > span","text").toString();
        page.putField("consume_count",consumeCount);

        String ratingCount = page.getHtml().css("#bd > div.summary.biz-box.fs-section.cf > div.fs-section__right > div.counts > div:nth-child(2) > a", "text").toString();
        page.putField("rating_count",ratingCount);


    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new MeituanPageProcessor()).addUrl("http://bj.meituan.com/category/shuishijie/wangjing").thread(1).run();
    }
}
