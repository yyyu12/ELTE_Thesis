package hu.elte.inf.backend.controller;

import hu.elte.inf.backend.common.Result;
import hu.elte.inf.backend.controller.request.LoginRequest;
import hu.elte.inf.backend.controller.response.UserLoginResponse;
import hu.elte.inf.backend.service.impl.UserServiceImpl;
import hu.elte.inf.backend.sqlEntity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@RestController
@RequestMapping(value = "/users")
@CrossOrigin(origins = "*")// 将15行以下的东西
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    // 用户注册
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        userService.registerUser(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping ("/login")
    public Result login(@Validated @RequestBody LoginRequest loginRequest){
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        if(username == null||password == null){
            return Result.error("Username or password can not be null");
        }
        User user = userService.login(username);
        if(user == null){
            return Result.error("User couldn't find");
        } else if (Objects.equals(user.getPassword(), password)) {
            UserLoginResponse userLoginResponse = new UserLoginResponse();
            BeanUtils.copyProperties(user,userLoginResponse);
            return Result.ok("User login success").put("User info",userLoginResponse);
        } else{
            return Result.error(("User password is wrong"));
        }

    }

}
