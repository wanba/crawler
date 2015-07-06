package com.qinziwanba.api.controller;

import com.qinziwanba.api.domain.Child;
import com.qinziwanba.api.domain.User;
import com.qinziwanba.api.service.AuthService;
import com.qinziwanba.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangzhiguo on 15/7/5.
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "show.json", method = RequestMethod.GET)
    public User showUser(@RequestParam(value = "uid", required = true) String uid) {

        return userService.getUser(uid);
    }

    @RequestMapping(value = "register/phone.json", method = RequestMethod.POST)
    public User registerByPhone(@RequestParam(value = "name", required = true) String name,
                             @RequestParam(value = "phone", required = true) String phone,
                             @RequestParam(value = "gender", required = true) String gender) {

        return userService.registerUserByPhone(name, phone, gender);
    }

    @RequestMapping(value = "child/add.json", method = RequestMethod.POST)
    public Child addChild(@RequestParam(value = "pid", required = true) String pid,
                          @RequestParam(value = "name", required = true) String name,
                          @RequestParam(value = "age", required = true) int age,
                          @RequestParam(value = "gender", required = false) String gender) {

        return userService.addChild(pid, name, age, gender);
    }

    @RequestMapping(value = "password/reset.json", method = RequestMethod.POST)
    public void resetUserPassword(@RequestParam(value = "uid", required = true) String uid,
                                @RequestParam(value = "password", required = true) String password) {

        userService.resetPassword(uid, password);
    }

    @RequestMapping(value = "password/modify.json", method = RequestMethod.POST)
    public void modifyUserPassword(@RequestParam(value = "uid", required = true) String uid,
                                  @RequestParam(value = "old_password", required = true) String oldPassword,
                                 @RequestParam(value = "new_password", required = true) String newPassword) {

        userService.modifyPassword(uid, oldPassword, newPassword);
    }

    @RequestMapping(value = "login/phone.json", method = RequestMethod.POST)
    public User loginByPhone(@RequestParam(value = "phone", required = true) String phone,
                             @RequestParam(value = "password", required = true) String password) {

        return authService.loginByPhone(phone, password);
    }
}
