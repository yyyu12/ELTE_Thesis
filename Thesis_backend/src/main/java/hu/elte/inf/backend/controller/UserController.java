package hu.elte.inf.backend.controller;

import hu.elte.inf.backend.common.Result;
import hu.elte.inf.backend.controller.request.LoginRequest;
import hu.elte.inf.backend.controller.request.UserRegistrationRequest;
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

import java.util.Objects;

// 与前端交互的地方

@RestController
@RequestMapping(value = "/users")
@CrossOrigin(origins = "*")// 将15行以下的东西
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    // 用户注册
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
            return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body(Result.error("User not found"));
        } catch (PasswordNotMatchException ex) {
            return ResponseEntity.status(HttpStatus.SC_UNAUTHORIZED).body(Result.error("Password does not match"));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(Result.error("Internal server error"));
        }
    }
}
