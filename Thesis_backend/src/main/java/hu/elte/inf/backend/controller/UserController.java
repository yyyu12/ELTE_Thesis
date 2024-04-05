package hu.elte.inf.backend.controller;

import hu.elte.inf.backend.service.impl.UserServiceImpl;
import hu.elte.inf.backend.sqlEntity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(value = "/users") // 将15行以下的东西
public class UserController {
    @Autowired
    private UserServiceImpl UserServiceImpl;

    // 用户注册
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        UserServiceImpl.registerUser(user);
        return ResponseEntity.ok("User registered successfully");
    }

    // 检查是否为管理员（仅示例，实际应用中可能需要更复杂的权限控制逻辑）
    @GetMapping("/{username}/isAdmin")
    public ResponseEntity<Boolean> isAdmin(@PathVariable String username) {
        boolean isAdmin = UserServiceImpl.isAdmin(username);
        return ResponseEntity.ok(isAdmin);
    }
}
