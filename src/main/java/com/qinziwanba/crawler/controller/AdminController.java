package com.qinziwanba.crawler.controller;

import com.qinziwanba.commons.AppStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangzhiguo on 15/6/27.
 */
@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private AppStats applicationStats;

    @RequestMapping(value = "stats.json", method = RequestMethod.GET)
    public AppStats status(
            @RequestParam(value = "user", required = false) String user,
            @RequestParam(value = "pass", required = false) String paas) {

        return applicationStats;
    }
}
