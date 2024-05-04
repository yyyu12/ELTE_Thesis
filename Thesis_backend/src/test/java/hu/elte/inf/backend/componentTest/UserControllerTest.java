package hu.elte.inf.backend.componentTest;

import hu.elte.inf.backend.controller.UserController;
import hu.elte.inf.backend.service.impl.UserServiceImpl;
import hu.elte.inf.backend.sqlEntity.User;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;

@WebMvcTest(controllers  = UserController.class)
@ActiveProfiles(value="test")
@AutoConfigureMybatis
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userService;

    @Test
    public void testGetAllUsers() throws Exception {
        User user1 = new User();
        user1.setId(1L);
        user1.setUsername("johnsmith");
        user1.setPassword("password123");
        user1.setEmail("john@example.com");
        user1.setIsAdmin(true);
        user1.setBirthdate(LocalDate.of(1990, 1, 1));
        user1.setGender("Male");
        user1.setAddress("1234 Oak St");

        User user2 = new User();
        user2.setId(2L);
        user2.setUsername("janesmith");
        user2.setPassword("password456");
        user2.setEmail("jane@example.com");
        user2.setIsAdmin(false);
        user2.setBirthdate(LocalDate.of(1992, 2, 2));
        user2.setGender("Female");
        user2.setAddress("5678 Maple St");

        List<User> users = Arrays.asList(user1, user2);

        given(userService.getAllUsers()).willReturn(users);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/getAllUsers")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].username").value("johnsmith"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("john@example.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].isAdmin").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].birthdate").value("1990-01-01"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].gender").value("Male"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].address").value("1234 Oak St"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].username").value("janesmith"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].email").value("jane@example.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].isAdmin").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].birthdate").value("1992-02-02"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].gender").value("Female"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].address").value("5678 Maple St"));
    }
}