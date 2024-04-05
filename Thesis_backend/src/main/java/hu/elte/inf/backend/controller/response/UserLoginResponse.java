package hu.elte.inf.backend.controller.response;

import lombok.Data;

@Data
public class UserLoginResponse {
    private Long id;
    private String username;
    private String email;
    private Boolean isAdmin;
}
