package com.erent.service;

import com.erent.pojo.User;
import org.apache.ibatis.session.SqlSession;

public interface UserService {

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    User login(String username, String password);

    /**
     * 根据id返回信息
     * @param id
     * @return
     */
    User selectById(int id);

    /**
     * 注册：即添加用户
     * @param user
     * @return
     */
    boolean register(User user);

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    User selectByUsername(String username);
}
