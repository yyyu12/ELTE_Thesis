package hu.elte.inf.backend.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "Username cannot be empty")
    String username;

    @NotBlank(message = "Password cannot be empty")
    String password;
}
