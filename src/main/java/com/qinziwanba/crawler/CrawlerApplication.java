package com.qinziwanba.crawler;

import com.qinziwanba.commons.AppStats;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.*;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangzhiguo on 15/6/27.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
@PropertySources({@PropertySource("classpath:application.properties")
})
public class CrawlerApplication {

    @Value("${server.port}")
    private String port;

    @Bean
    AppStats serviceStatus() {
        AppStats status = new AppStats();
        String startTimeKey = "listen on " + port + ".st";
        Map<String, Object> statusMap = new HashMap<String, Object>();
        statusMap.put(startTimeKey, DateFormat.getDateTimeInstance().format(new Date()));
        status.setStatus(statusMap);
        return status;
    }

    public static void main(String[] args) {
        System.setProperty("java.net.preferIPv4Stack", "true");
        SpringApplication.run(CrawlerApplication.class, args);
    }
}
