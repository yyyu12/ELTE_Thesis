package hu.elte.inf.backend.dao;

import hu.elte.inf.backend.sqlEntity.User;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.apache.ibatis.annotations.Param;

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
     * Get user by id
     * @param id
     * @return User
     */
    User getUserById(Long id);

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

    /**
     * Update user's password.
     * @param userId the ID of the user whose password is to be updated.
     * @param newPassword the new password for the user.
     * @return the number of rows affected.
     */
    int updateUserPassword(@Param("id") Long id, @Param("newPassword") String newPassword);
}