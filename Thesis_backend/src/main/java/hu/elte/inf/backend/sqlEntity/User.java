package hu.elte.inf.backend.sqlEntity;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private String email;
    private Boolean isAdmin;
    // getter和setter方法
}

