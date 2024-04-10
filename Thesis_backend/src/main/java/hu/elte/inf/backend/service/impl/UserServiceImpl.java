package hu.elte.inf.backend.service.impl;

import hu.elte.inf.backend.dao.UserMapper;
import hu.elte.inf.backend.sqlEntity.User;
import hu.elte.inf.backend.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;

// 控制类和数据库的桥梁
// 接收控制器层的调用，处理业务逻辑
// 调用数据访问层与数据库交互，并返回结果给控制器层

@org.springframework.stereotype.Service
public class UserServiceImpl implements  Service{

    @Autowired
    private UserMapper userMapper;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    // 注册新用户
    @Override
    public void registerUser(User user) {
        userMapper.insertUser(user);
    }

    // 用户登入
    @Override
    public User login(String username ){
        return userMapper.getUserByUserName(username);
    }

    // 更新用户
    @Override
    public void updateUser(User user){
        userMapper.updateUserInfo(user);
    }
}
