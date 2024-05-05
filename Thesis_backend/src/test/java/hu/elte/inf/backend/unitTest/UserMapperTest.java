package hu.elte.inf.backend.unitTest;


import hu.elte.inf.backend.dao.UserMapper;
import hu.elte.inf.backend.sqlEntity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@ActiveProfiles(value="test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @DirtiesContext
    @Test
    public void findAllUsersTest(){
        List<User> userList = userMapper.findAllUsers();
        assertNotNull(userList);
        assertEquals(userList.size(),6);
    }

    @DirtiesContext
    @Test
    public void getUserByIdTest(){
        User user = userMapper.getUserById(1L);
        assertNotNull(user);
        assertEquals(user.getUsername(), "Chen Siyu");
    }

    @DirtiesContext
    @Test
    public void getUserByUserNameTest(){
        User user = userMapper.getUserByUserName("Fancy");
        assertNotNull(user);
        assertEquals(user.getEmail(), "fancy123@gmail.com");
    }

    @DirtiesContext
    @Test
    public void getUserByEmailTest(){
        User user = userMapper.getUserByEmail("fancy123@gmail.com");
        assertNotNull(user);
        assertEquals(user.getUsername(), "Fancy");
    }

    @DirtiesContext
    @Test
    public void insertUserTest(){
        User user = new User();
        user.setUsername("Test");
        user.setPassword("Test.123");
        user.setEmail("test123@test.com");
        
        int result = userMapper.insertUser(user);
        assertEquals(result, 1);

        User newUser = userMapper.getUserByUserName("Test");
        assertNotNull(newUser);
        assertEquals(newUser.getEmail(), "test123@test.com");
    }


    @DirtiesContext
    @Test
    public void updateUserInfoTest(){
        User user = userMapper.getUserByUserName("Fancy");
        user.setEmail("Fancy123@test123.com");
        userMapper.updateUserInfo(user);

        User newUser = userMapper.getUserByUserName("Fancy");
        assertNotNull(newUser);
        assertEquals(newUser.getEmail(), "Fancy123@test123.com");
    }

    @DirtiesContext
    @Test
    public void updateUserPasswordTest(){
        int result = userMapper.updateUserPassword(2L, "Fancy123.123");
        assertEquals(result, 1);

        User user = userMapper.getUserById(2L);
        assertNotNull(user);
        assertEquals(user.getPassword(), "Fancy123.123");
    }

    @DirtiesContext
    @Test
    public void deleteUserTest(){
        User user = new User();
        user.setUsername("Test");
        user.setPassword("Test.123");
        user.setEmail("Test123@test.com");
        userMapper.insertUser(user);

        User newUser = userMapper.getUserByUserName("Test");
        assertNotNull(newUser);

        int result = userMapper.deleteUser(newUser.getId());
        assertEquals(result, 1);

        User deletedUser = userMapper.getUserByUserName("Test");
        assertEquals(deletedUser, null);
    }
}