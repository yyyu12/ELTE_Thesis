package hu.elte.inf.backend.service.impl;

import hu.elte.inf.backend.dao.UserMapper;
import hu.elte.inf.backend.sqlEntity.User;
import hu.elte.inf.backend.service.Service;
import org.springframework.beans.factory.annotation.Autowired;

// 实现rest业务逻辑 -- 前端和后端交互的地方
@org.springframework.stereotype.Service
public class UserServiceImpl implements  Service{

    @Autowired
    private UserMapper userMapper;

    // 注册新用户
    @Override
    public void registerUser(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public User login(String username ){
        return userMapper.getUserByUserName(username);
    }
    // 查找用户
}
