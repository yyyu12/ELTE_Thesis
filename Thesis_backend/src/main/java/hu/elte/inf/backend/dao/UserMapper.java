package hu.elte.inf.backend.dao;

import hu.elte.inf.backend.sqlEntity.User;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

// 数据访问层负责与数据库进行直接交互
@Mapper
public interface UserMapper {
    Integer insertUser(User user);
    User getUserByUserName(String username);
    User getUserByEmail(String email);
    void updateUserInfo(User user);
}