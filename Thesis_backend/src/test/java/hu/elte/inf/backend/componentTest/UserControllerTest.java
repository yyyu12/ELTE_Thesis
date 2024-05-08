package hu.elte.inf.backend.componentTest;

import hu.elte.inf.backend.common.exceptionEnd.PasswordNotMatchException;
import hu.elte.inf.backend.common.exceptionEnd.UserNotFoundException;
import hu.elte.inf.backend.common.exceptionEnd.UsernameDuplicatedException;
import hu.elte.inf.backend.controller.UserController;
import hu.elte.inf.backend.controller.request.LoginRequest;
import hu.elte.inf.backend.controller.request.PasswordUpdateRequest;
import hu.elte.inf.backend.controller.request.UserRegistrationRequest;
import hu.elte.inf.backend.controller.response.UserLoginResponse;
import hu.elte.inf.backend.service.impl.UserServiceImpl;
import hu.elte.inf.backend.sqlEntity.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

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

        mockMvc.perform(get("/users/getAllUsers")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].username").value("johnsmith"))
                .andExpect(jsonPath("$[0].email").value("john@example.com"))
                .andExpect(jsonPath("$[0].isAdmin").value(true))
                .andExpect(jsonPath("$[0].birthdate").value("1990-01-01"))
                .andExpect(jsonPath("$[0].gender").value("Male"))
                .andExpect(jsonPath("$[0].address").value("1234 Oak St"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].username").value("janesmith"))
                .andExpect(jsonPath("$[1].email").value("jane@example.com"))
                .andExpect(jsonPath("$[1].isAdmin").value(false))
                .andExpect(jsonPath("$[1].birthdate").value("1992-02-02"))
                .andExpect(jsonPath("$[1].gender").value("Female"))
                .andExpect(jsonPath("$[1].address").value("5678 Maple St"));
    }

    @Test
    public void testRegisterUser_UsernameExistsException() throws Exception {
        UserRegistrationRequest request = new UserRegistrationRequest();
        request.setUsername("existinguser");
        request.setPassword("password");
        request.setEmail("user@example.com");

        doThrow(new UsernameDuplicatedException("Username already exist")).when(userService).registerUser(any(User.class));

        mockMvc.perform(post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.msg").value("Username already exist"));
    }

    // User Success Login
    @Test
    public void testLogin_Success() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("johnsmith");
        loginRequest.setPassword("password123");

        User user = new User();
        user.setId(1L);
        user.setUsername("johnsmith");
        user.setEmail("john@example.com");

        UserLoginResponse response = new UserLoginResponse();
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());

        when(userService.login(anyString(), anyString())).thenReturn(user);

        mockMvc.perform(post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.info.username").value("johnsmith"))
                .andExpect(jsonPath("$.info.email").value("john@example.com"));
    }

    // User Not Found
    @Test
    public void testLogin_UserNotFound() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("unknown");
        loginRequest.setPassword("password123");

        when(userService.login(anyString(), anyString())).thenThrow(new UserNotFoundException("User Not Found"));

        mockMvc.perform(post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(loginRequest)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.msg").value("User Not Found"));
    }

    @Test
    public void testLogin_PasswordNotMatch() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("johnsmith");
        loginRequest.setPassword("wrongpassword");

        when(userService.login(anyString(), anyString())).thenThrow(new PasswordNotMatchException("Password does not match"));

        mockMvc.perform(post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(loginRequest)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.msg").value("Password does not match"));
    }

    @Test
    public void testUpdateUserInfo() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setUsername("johnsmith");
        user.setPassword("password123");
        user.setEmail("johnsmith123@test.com");

        User updatedUser = new User();

        given(userService.updateUserInfo(any(User.class))).willReturn(updatedUser);

        mockMvc.perform(put("/users/updateInfo/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.msg").value("User info updated successfully"));
    }

    @Test
    public void testUpdateUserInfo_UserNotFound() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setUsername("johnsmith");
        user.setPassword("password123");
        user.setEmail("johnsmith@johnsmith.com");

        when(userService.updateUserInfo(any(User.class))).thenThrow(new UserNotFoundException("User Not Found"));

        mockMvc.perform(put("/users/updateInfo/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.msg").value("User Not Found"));
    }

    @Test
    public void testUpdatePassword_Success() throws Exception {
        Long userId = 1L;
        PasswordUpdateRequest passwordUpdateRequest = new PasswordUpdateRequest();
        passwordUpdateRequest.setOldPassword("oldPassword");
        passwordUpdateRequest.setNewPassword("newPassword");

        when(userService.updatePassword(anyLong(), anyString(), anyString())).thenReturn(true);

        mockMvc.perform(put("/users/updatePassword/{id}", userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(passwordUpdateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.msg").value("Password updated successfully"));
    }

    @Test
    public void testDeleteUser_Success() throws Exception {
        Long userId = 1L;

        when(userService.deleteUser(userId)).thenReturn(true);

        mockMvc.perform(delete("/users/deleteUser/{id}", userId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.msg").value("User deleted successfully"));
    }

    @Test
    public void testDeleteUser_Failure() throws Exception {
        Long userId = 2L;

        when(userService.deleteUser(userId)).thenReturn(false);

        mockMvc.perform(delete("/users/deleteUser/{id}", userId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.msg").value("User delete failed"));
    }
}