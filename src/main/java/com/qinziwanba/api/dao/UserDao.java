package com.qinziwanba.api.dao;

import com.qinziwanba.api.domain.User;
import com.qinziwanba.commons.WanbaErrorCode;
import com.qinziwanba.commons.WanbaException;
import com.qinziwanba.commons.WanbaLogger;
import com.qinziwanba.crawler.dao.AbstractDao;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

/**
 * Created by wangzhiguo on 15/7/5.
 */
@Repository
public class UserDao extends AbstractDao {

    private final String SQL_USER_INSERT = "insert into user (`uid`, `name`, `phone`, `gender`, `updated_at`) values (?,?,?,?,?)" +
            " on duplicate key update `name` = ?, `phone` = ?, `gender` = ?, `updated_at` = ?;";

    private final String SQL_USER_SELECT = "select `uid`, `name`, `password`, `email`, `phone`, `city`, `gender`, `updated_at` as updatedAt from user where uid=? ;";

    private final String SQL_USER_SELECT_BY_PHONE = "select `uid`, `name`, `password`, `email`, `phone`, `city`, `gender`, `updated_at` as updatedAt from user where phone=? ;";

    private final String SQL_USER_UPDATE_PASSWORD = "update user set password = ? where uid = ? ";

    /**
     * get user
     * @param uid
     * @return
     */
    public User getUser(String uid) {
        WanbaLogger.debug("{} getUser , uid={}",this.getClass(), uid);

        RowMapper<User> rm = ParameterizedBeanPropertyRowMapper.newInstance(User.class);
        return jdbcTemplate.queryForObject(SQL_USER_SELECT, new Object[] {uid}, rm);
    }

    public User getUserByPhone(String phone) {
        WanbaLogger.debug("{} get user by phone, phone={}", this.getClass(), phone);

        RowMapper<User> rm = ParameterizedBeanPropertyRowMapper.newInstance(User.class);
        return (User) jdbcTemplate.queryForObject(SQL_USER_SELECT_BY_PHONE, new Object[] {phone}, rm);
    }

    /**
     * insert user
     * @param uid
     * @param name
     * @param phone
     * @param gender
     * @return
     */
    public User insertUser(String uid,String name,String phone,String gender) {
        WanbaLogger.debug("{} insertUser, uid={} name={} phone={} city={} gender={}",
                this.getClass(), uid, name, phone, gender);

        Long cur = System.currentTimeMillis();
        jdbcTemplate.update(SQL_USER_INSERT, uid, name, phone, gender,cur, name, phone, gender, cur);

        User user = new User(uid,name,phone,gender);
        user.setUpdatedAt(cur);

        return user;
    }

    public void resetPassword(String uid, String password) {
        WanbaLogger.debug("{} update password, uid={} password={}", this.getClass(),uid, password);

        jdbcTemplate.update(SQL_USER_UPDATE_PASSWORD, new Object[]{password, uid});
    }

    public void updatePassword(String uid, String oldPassword, String newPassword) {
        WanbaLogger.debug("{} update password, uid={} oldPassword={} newPassword={}", this.getClass(), uid, oldPassword, newPassword);

        User user = getUser(uid);
        if (oldPassword.equalsIgnoreCase(user.getPassword())) {
            jdbcTemplate.update(SQL_USER_UPDATE_PASSWORD, new Object[]{newPassword, uid });
        }else {
            throw new WanbaException(WanbaErrorCode.WANBA_OLD_PASSWORD_INVALID);
        }
    }
}
