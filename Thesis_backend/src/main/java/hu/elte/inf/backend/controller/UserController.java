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
@CrossOrigin(origins = "*")
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
    public Result registerUser(@Validated @RequestBody UserRegistrationRequest userRegistrationRequest) {
        User user = new User();
        BeanUtils.copyProperties(userRegistrationRequest, user);
        userService.registerUser(user);
        return Result.ok("User registered successfully");
    }

    /**
     * User login
     * @param loginRequest
     * @return ResponseEntity<Result> -- user login data
     */
    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        User user = userService.login(username, password);
        UserLoginResponse userLoginResponse = new UserLoginResponse();
        BeanUtils.copyProperties(user, userLoginResponse);
        return Result.ok("User login success").put("info", userLoginResponse);
    } 

    /**
     * Get user by id
     * @param id
     * @return
     */
    @PutMapping("/updateInfo/{id}")
    public ResponseEntity<Result> updateUserInfo(@PathVariable Long id, @Validated @RequestBody UpdateRequest updateRequest) {
        User user = new User();
        BeanUtils.copyProperties(updateRequest, user);
        user.setId(id);

        User updatedUser = userService.updateUserInfo(user);

        UserLoginResponse userLoginResponse = new UserLoginResponse();
        BeanUtils.copyProperties(updatedUser, userLoginResponse);

        return ResponseEntity.ok(Result.ok("User info updated successfully").put("info", userLoginResponse));
    }

    /**
     * Update user password
     * @param id
     * @param passwordUpdateRequest
     * @return
     */
    @PutMapping("/updatePassword/{id}")
    public ResponseEntity<Result> updatePassword(@PathVariable Long id, @Validated @RequestBody PasswordUpdateRequest passwordUpdateRequest) {
        boolean updateResult = userService.updatePassword(id, passwordUpdateRequest.getOldPassword(), passwordUpdateRequest.getNewPassword());
        if (updateResult) {
            return ResponseEntity.ok(Result.ok("Password updated successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(Result.error("Password update failed"));
        }
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<Result> deleteUser(@PathVariable Long id) {
        boolean deleteResult = userService.deleteUser(id);
        if (deleteResult) {
            return ResponseEntity.ok(Result.ok("User deleted successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(Result.error("User delete failed"));
        }
    }

}
