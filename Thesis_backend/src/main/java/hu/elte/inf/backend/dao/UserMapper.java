package hu.elte.inf.backend.dao;

import hu.elte.inf.backend.sqlEntity.User;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

// 数据访问层负责与数据库进行直接交互
@Mapper
public interface UserMapper {
    /**
     * Insert one line data
     * @param user
     * @return the changing line number
     */
    Integer insertUser(User user);

    /**
     * Get user by username
     * @param username
     * @return User
     */
    User getUserByUserName(String username);

    /**
     * Get user by email
     * @param email
     * @return user
     */
    User getUserByEmail(String email);

    /**
     * Update user information
     * @param user
     */
    void updateUserInfo(User user);
}