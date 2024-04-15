package hu.elte.inf.backend.service.impl;

import hu.elte.inf.backend.dao.UserMapper;
import hu.elte.inf.backend.sqlEntity.User;
import hu.elte.inf.backend.service.Service;
import hu.elte.inf.backend.common.exceptionEnd.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
// 控制类和数据库的桥梁
// 接收控制器层的调用，处理业务逻辑
// 调用数据访问层与数据库交互，并返回结果给控制器层

@org.springframework.stereotype.Service
public class UserServiceImpl implements  Service{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return userMapper.findAllUsers();
    }

    // 注册新用户
    @Override
    public void registerUser(User user) {

        // 检查用户名是否已被使用
        if (userMapper.getUserByUserName(user.getUsername()) != null) {
            throw new UsernameDuplicatedException("The username is already taken.");
        }

        // 检查电子邮件是否已被注册
        if (userMapper.getUserByEmail(user.getEmail()) != null) {
            throw new EmailDuplicateException("The email is already registered.");
        }

        // 使用PasswordEncoder加密密码
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        System.out.println(encodedPassword);

        // 尝试插入用户到数据库，并检查操作结果
        Integer insertResult = userMapper.insertUser(user);
        if (insertResult != 1) {
            // 如果影响的行数不是1，认为插入失败，抛出自定义异常
            throw new InsertException("Failed to insert the new user.");
        }

    }

    // 用户登入
    @Override
    public User login(String username, String password ){
        User user = userMapper.getUserByUserName(username);

        // 如果用户不存在，抛出UserNotFoundException
        if (user == null) {
            throw new UserNotFoundException("Username Not Found");
        }
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw  new PasswordNotMatchException("Password Does Not Match");
        }

        return user;
    }

    // 更新用户
    @Override
    public User updateUserInfo(User user){
        userMapper.updateUserInfo(user);
        return userMapper.getUserByUserName(user.getUsername());
    }

    public boolean updatePassword(Long id, String oldPassword, String newPassword) {
        // 获取当前用户信息
        User currentUser = userMapper.getUserById(id);

        // 验证旧密码是否正确
        if (currentUser == null || !passwordEncoder.matches(oldPassword, currentUser.getPassword())) {
            throw new PasswordNotMatchException("Old password does not match.");
        }

        // 加密新密码
        String encodedNewPassword = passwordEncoder.encode(newPassword);

        // 更新密码到数据库
        int affectedRows = userMapper.updateUserPassword(id, encodedNewPassword);
        return affectedRows == 1;
    }
}
