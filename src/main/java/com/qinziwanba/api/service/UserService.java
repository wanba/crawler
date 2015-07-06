package com.qinziwanba.api.service;

import com.qinziwanba.api.dao.ChildDao;
import com.qinziwanba.api.dao.UserDao;
import com.qinziwanba.api.domain.Child;
import com.qinziwanba.api.domain.User;
import com.qinziwanba.commons.WanbaErrorCode;
import com.qinziwanba.commons.WanbaException;
import com.qinziwanba.commons.WanbaLogger;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangzhiguo on 15/7/5.
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ChildDao childDao;

    /**
     * get user
     * @param uid
     * @return
     */
    public User getUser(String uid) {

        // user
        User user = userDao.getUser(uid);

        // child
        List<Child> childList = childDao.selectChildByPid(uid);
        if (childList!=null && !childList.isEmpty()) {
            user.addChildList(childList);
        }

        return user;
    }

    /**
     * register user
     * @param name
     * @param phone
     * @param gender
     * @return
     */
    public User registerUserByPhone(String name, String phone, String gender) {

        String uid = genUid();

        return userDao.insertUser(uid, name, phone, gender);
    }

    /**
     * add child
     * @param pid
     * @param name
     * @param age
     * @param gender
     * @return
     */
    public Child addChild(String pid, String name, int age, String gender) {

        return childDao.addChild(pid, name, age, gender);
    }

    /**
     *
     * @return
     */
    private String genUid() {
        WanbaLogger.debug("{} genUid", this.getClass());

        return java.util.UUID.randomUUID().toString();
    }

    public void resetPassword(String uid, String password) {
        if (StringUtils.isBlank(password)) {
            throw new WanbaException(WanbaErrorCode.WANBA_PARAM_ISBLACK);
        }

        userDao.resetPassword(uid, password);
    }

    public void modifyPassword(String uid, String oldPassword, String newPassword) {

        if (StringUtils.isBlank(oldPassword) || StringUtils.isBlank(newPassword)) {
            throw new WanbaException(WanbaErrorCode.WANBA_PARAM_ISBLACK);
        }

        userDao.updatePassword(uid, oldPassword, newPassword);
    }
}
