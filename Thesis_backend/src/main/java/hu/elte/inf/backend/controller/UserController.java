package hu.elte.inf.backend.controller;

import hu.elte.inf.backend.common.Result;
import hu.elte.inf.backend.service.impl.UserServiceImpl;
import hu.elte.inf.backend.sqlEntity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping ("/login")
    public Result login(String username ,String password){
        if(username == null||password == null){
            return Result.error("Username or password can not be null");
        }
        User user = userService.login(username);
        if(user == null){
            return Result.error("User couldn't find");
        } else if (Objects.equals(user.getPassword(), password)) {
            return Result.ok("User login success").put("User info",user);
        } else{
            return Result.error(("User password is wrong"));
        }

    }

}
