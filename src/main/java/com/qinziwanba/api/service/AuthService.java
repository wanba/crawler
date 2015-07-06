package com.qinziwanba.api.service;

import com.qinziwanba.api.dao.UserDao;
import com.qinziwanba.api.domain.User;
import com.qinziwanba.commons.WanbaErrorCode;
import com.qinziwanba.commons.WanbaException;
import com.qinziwanba.commons.WanbaLogger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by wangzhiguo on 15/7/6.
 */
public class AuthService {


    @Autowired
    private UserDao userDao;


    public User loginByPhone(String phone,String password) {

        User user = userDao.getUserByPhone(phone);

        if (!password.equalsIgnoreCase(user.getPassword())) {
            throw new WanbaException(WanbaErrorCode.WANBA_PASSWORD_INVALID);
        }else {

        }

        WanbaLogger.info("{} login by phone success, phone={}", this.getClass(), phone);

        return user;
    }

}
