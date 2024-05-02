package hu.elte.inf.backend.service.impl;

import hu.elte.inf.backend.dao.UserMapper;
import hu.elte.inf.backend.sqlEntity.User;
import hu.elte.inf.backend.service.Service;
import hu.elte.inf.backend.common.exceptionEnd.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

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

    @Override
    public void registerUser(User user) {

        if (userMapper.getUserByUserName(user.getUsername()) != null) {
            throw new UsernameDuplicatedException("The username is already taken.");
        }

        if (userMapper.getUserByEmail(user.getEmail()) != null) {
            throw new EmailDuplicateException("The email is already registered.");
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        System.out.println(encodedPassword);

        Integer insertResult = userMapper.insertUser(user);
        if (insertResult != 1) {
            throw new InsertException("Failed to insert the new user.");
        }

    }

    @Override
    public User login(String username, String password ){
        User user = userMapper.getUserByUserName(username);

        if (user == null) {
            throw new UserNotFoundException("Username Not Found");
        }
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw  new PasswordNotMatchException("Password Does Not Match");
        }

        return user;
    }

    @Override
    public User updateUserInfo(User user){
        userMapper.updateUserInfo(user);
        return userMapper.getUserByUserName(user.getUsername());
    }

    public boolean updatePassword(Long id, String oldPassword, String newPassword) {
        User currentUser = userMapper.getUserById(id);

        if (currentUser == null || !passwordEncoder.matches(oldPassword, currentUser.getPassword())) {
            throw new PasswordNotMatchException("Old password does not match.");
        }

        String encodedNewPassword = passwordEncoder.encode(newPassword);

        int affectedRows = userMapper.updateUserPassword(id, encodedNewPassword);
        return affectedRows == 1;
    }

    @Override
    public boolean deleteUser(Long id){
        User user = userMapper.getUserById(id);
        if(user == null){
            throw new UserNotFoundException("User Not Found");
        }
        int affectedRows = userMapper.deleteUser(id);
        return affectedRows == 1;
    }
}
