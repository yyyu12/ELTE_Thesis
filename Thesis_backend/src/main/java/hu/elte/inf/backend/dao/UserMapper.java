package hu.elte.inf.backend.dao;

import hu.elte.inf.backend.sqlEntity.User;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
@Mapper
public interface UserMapper {
    void insertUser(User user);
    User getUserByUserName(String username);

}
