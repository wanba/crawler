package com.qinziwanba.api.service;

import com.qinziwanba.api.dao.UserDao;
import com.qinziwanba.api.domain.User;
import com.qinziwanba.commons.WanbaErrorCode;
import com.qinziwanba.commons.WanbaException;
import com.qinziwanba.commons.WanbaLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangzhiguo on 15/7/6.
 */
@Service
public class AuthService {


    @Autowired
    private UserDao userDao;


    public User loginByPhone(String phone,String password) {

        User user = userDao.getUserByPhone(phone);

        if (!password.equalsIgnoreCase(user.getPassword())) {
            throw new WanbaException(WanbaErrorCode.WANBA_PASSWORD_INVALID);
        }else {
            // TODO :user login session
        }

        WanbaLogger.info("{} login by phone success, phone={}", this.getClass(), phone);

        return user;
    }

    public boolean isLogin(String phone) {
        return false;       // TODO
    }
}
