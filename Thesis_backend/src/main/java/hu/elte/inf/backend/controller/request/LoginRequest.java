package hu.elte.inf.backend.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "User Login Request")
public class LoginRequest {
    @NotBlank(message = "Username cannot be empty")
    String username;

    @NotBlank(message = "Password cannot be empty")
    String password;
}
