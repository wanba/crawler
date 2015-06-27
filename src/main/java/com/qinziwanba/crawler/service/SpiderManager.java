package com.qinziwanba.crawler.service;

import com.qinziwanba.commons.WanbaLogger;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangzhiguo on 15/6/27.
 */
@Component
public class SpiderManager {

    private Map<String,Spider> spiderMap = new HashMap<String, Spider>();

    private List<String> meituanCategraies = new ArrayList<String>();
    private List<String> meituanDirections = new ArrayList<String>();

    private final String MEITUAN_URL = "http://bj.meituan.com/category/";
    private int threadNum = 1;

    @Resource(name = "meituanPipeline")
    private Pipeline meituanPipeline;

    @Resource(name = "meituanPageProcessor")
    private PageProcessor meituanPageProcessor;

    @PostConstruct
    public void init() {

        Spider meituanSpider = initMeituanSpider();

        // add to map
        spiderMap.put("meituan",meituanSpider);

        // startAll spider
        startAll();
    }

    private Spider initMeituanSpider() {

        initMeituanCategraies();
        initMeituanDirections();

        Spider meituanSpider = Spider.create(meituanPageProcessor);
        for (String cate : meituanCategraies) {
            for (String direct : meituanDirections) {
                String url = MEITUAN_URL + cate + "/" + direct;
                meituanSpider.addUrl(url);
            }
        }
        meituanSpider.addPipeline(meituanPipeline);
        meituanSpider.thread(threadNum);

        WanbaLogger.info("init meituan spider, threadNum={}", threadNum);
        return meituanSpider;

    }


    /**
     * 足疗按摩
     水上世界
     亲子游玩
     温泉
     洗浴/汗蒸
     滑雪
     游泳/水上乐园
     运动健身
     咖啡/酒吧
     桌游/电玩
     景点郊游
     主题公园/游乐园
     儿童乐园
     采摘/农家乐
     演出/赛事/展览
     DIY手工
     真人CS
     私人影院
     4D/5D电影
     密室逃脱
     其他娱乐
     */
    private void initMeituanCategraies() {
        meituanCategraies.add("shuishijie");
//        meituanCategraies.add("qinziyouwan");
//        meituanCategraies.add("zuliaoxiuxian");
//        meituanCategraies.add("wenquan");
//        meituanCategraies.add("xiyuhanzheng");
//        meituanCategraies.add("huaxue");
//        meituanCategraies.add("youyong");
//        meituanCategraies.add("jianshen");
//        meituanCategraies.add("jiubakafei");
//        meituanCategraies.add("zhuoyou");
//        meituanCategraies.add("youle");
//        meituanCategraies.add("zhutigongyuan");
//        meituanCategraies.add("ertongleyuan");
//        meituanCategraies.add("caizhai");
//        meituanCategraies.add("yanchu");
//        meituanCategraies.add("diy");
//        meituanCategraies.add("zhenrencs");
//        meituanCategraies.add("sirenyingyuan");
//        meituanCategraies.add("4d5d");
//        meituanCategraies.add("mishitaotuo");
//        meituanCategraies.add("qitaxiuxian");

        WanbaLogger.info("init meituan categraies={}",meituanCategraies);
    }

    /**
     * 朝阳区
     海淀区
     丰台区
     西城区
     东城区
     昌平区
     石景山区
     通州区
     大兴区
     顺义区
     房山区
     密云县
     怀柔区
     延庆县
     平谷区
     门头沟
     */
    private void initMeituanDirections() {
        meituanDirections.add("chaoyangqu");
//        meituanDirections.add("haidianqu");
//        meituanDirections.add("fengtaiqu");
//        meituanDirections.add("xichengqu");
//        meituanDirections.add("dongchengqu");
//        meituanDirections.add("changpingqu");
//        meituanDirections.add("shijingshanqu");
//        meituanDirections.add("tongzhouqu");
//        meituanDirections.add("daxingqu");
//        meituanDirections.add("shunyiqu");
//        meituanDirections.add("fangshanqu");
//        meituanDirections.add("miyunqu");
//        meituanDirections.add("hairouqu");
//        meituanDirections.add("yanqingqu");
//        meituanDirections.add("pingguqu");
//        meituanDirections.add("mentougouqu");

        WanbaLogger.info("init meituan directions={}", meituanDirections);
    }

    /**
     * 启动所有spider
     */
    public void startAll() {
        WanbaLogger.info("{} startAll",this.getClass());
        for (String splider : spiderMap.keySet()) {
            WanbaLogger.info("start spider " + splider);
            spiderMap.get(splider).start();
        }
    }

    /**
     * 停止所有spider
     */
    public void stopAll() {
        WanbaLogger.info("{} stopAll",this.getClass());
        for (String splider : spiderMap.keySet()) {
            WanbaLogger.info("start spider " + splider);
            spiderMap.get(splider).stop();
        }
    }

}
