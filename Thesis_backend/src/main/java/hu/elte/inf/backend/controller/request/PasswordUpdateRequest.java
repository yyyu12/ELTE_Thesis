package hu.elte.inf.backend.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "User Update Password Request")
public class PasswordUpdateRequest {
    @NotBlank(message = "Old Password cannot be empty")
    private String oldPassword;

    @NotBlank(message = "New Password cannot be empty")
    private String newPassword;

}
