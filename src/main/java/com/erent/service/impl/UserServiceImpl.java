package com.erent.service.impl;

import com.erent.mapper.UserMapper;
import com.erent.pojo.User;
import com.erent.service.UserService;
import com.erent.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class UserServiceImpl implements UserService {
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();
    @Override
    public User login(String username, String password) {
        SqlSession sqlSession = factory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User select = mapper.select(username, password);

        // 释放资源
        sqlSession.close();

        return select;
    }

    @Override
    public User selectById(int id) {
        SqlSession sqlSession = factory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User user = mapper.selectById(id);

        sqlSession.close();

        return user;
    }

    @Override
    public boolean register(User user) {
        SqlSession sqlSession = factory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User u = mapper.selectByUsername(user.getUsername());

        if (u == null) {
            // 可以注册
            mapper.add(user);
            sqlSession.commit();
        }
        sqlSession.close();

        return u == null;
    }

    @Override
    public User selectByUsername(String username) {
        SqlSession sqlSession = factory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User user = mapper.selectByUsername(username);

        sqlSession.close();

        return user;
    }

}
