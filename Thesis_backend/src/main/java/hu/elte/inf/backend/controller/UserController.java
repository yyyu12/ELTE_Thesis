package hu.elte.inf.backend.controller;

import hu.elte.inf.backend.common.Result;
import hu.elte.inf.backend.controller.request.LoginRequest;
import hu.elte.inf.backend.controller.request.UpdateRequest;
import hu.elte.inf.backend.controller.request.UserRegistrationRequest;
import hu.elte.inf.backend.controller.request.PasswordUpdateRequest;
import hu.elte.inf.backend.common.exceptionEnd.*;
import hu.elte.inf.backend.controller.response.UserLoginResponse;
import hu.elte.inf.backend.service.impl.UserServiceImpl;
import hu.elte.inf.backend.sqlEntity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.apache.http.HttpStatus;
import java.util.List;


@RestController
@RequestMapping(value = "/users")
@CrossOrigin(origins = "*")// 将15行以下的东西
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    /**
     * Get all users information
     * @return List<User>
     */
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * Get user by id
     * @param userRegistrationRequest
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<Result> registerUser(@Validated @RequestBody UserRegistrationRequest userRegistrationRequest) {
        try{
            User user = new User();
            BeanUtils.copyProperties(userRegistrationRequest, user);
            userService.registerUser(user);
            return ResponseEntity.ok(Result.ok("User registered successfully."));
        }catch (UsernameDuplicatedException e) {
            return ResponseEntity.status(HttpStatus.SC_CONFLICT).body(Result.error("Username already used."));
        } catch (EmailDuplicateException e) {
            return ResponseEntity.status(HttpStatus.SC_CONFLICT).body(Result.error("Email already used."));
        } catch (InsertException e) {
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(Result.error("Internal server error"));
        }
    }

    /**
     * User login
     * @param loginRequest
     * @return ResponseEntity<Result> -- user login data
     */
    @PostMapping("/login")
    public ResponseEntity<Result> login(@Validated @RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        try {
            User user = userService.login(username, password);
            UserLoginResponse userLoginResponse = new UserLoginResponse();
            BeanUtils.copyProperties(user, userLoginResponse);
            return ResponseEntity.ok(Result.ok("User login success").put("info", userLoginResponse));
        } catch (UserNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body(Result.error("User Not Found"));
        } catch (PasswordNotMatchException ex) {
            return ResponseEntity.status(HttpStatus.SC_UNAUTHORIZED).body(Result.error("Password does not match"));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(Result.error("Internal server error"));
        }
    } 

    /**
     * Get user by id
     * @param id
     * @return
     */
    @PutMapping("/updateInfo/{id}")
    public ResponseEntity<Result> updateUserInfo(@PathVariable Long id, @Validated @RequestBody UpdateRequest updateRequest) {
        try {
            User user = new User();
            BeanUtils.copyProperties(updateRequest, user);
            user.setId(id);
            // 这里调用服务层的更新方法，并获取返回的更新后的用户对象
            User updatedUser = userService.updateUserInfo(user);

            // 将User转换为UserLoginResponse的方法或逻辑，就在这里使用它
            UserLoginResponse userLoginResponse = new UserLoginResponse();
            BeanUtils.copyProperties(updatedUser, userLoginResponse);

            // 返回更新后的用户数据
            return ResponseEntity.ok(Result.ok("User info updated successfully").put("info", userLoginResponse));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(Result.error("Internal server error"));
        }
    }

    /**
     * Update user password
     * @param id
     * @param passwordUpdateRequest
     * @return
     */
    @PutMapping("/updatePassword/{id}")
    public ResponseEntity<Result> updatePassword(@PathVariable Long id, @Validated @RequestBody PasswordUpdateRequest passwordUpdateRequest) {
        try {
            boolean updateResult = userService.updatePassword(id, passwordUpdateRequest.getOldPassword(), passwordUpdateRequest.getNewPassword());
            if (updateResult) {
                return ResponseEntity.ok(Result.ok("Password updated successfully"));
            } else {
                return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(Result.error("Password update failed"));
            }
        } catch (PasswordNotMatchException e) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(Result.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(Result.error("Internal server error"));
        }
    }

}
