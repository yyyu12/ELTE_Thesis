package hu.elte.inf.backend.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(description = "User Information Request")
public class UpdateRequest {

    private String username;

    @Email(message = "Email should be valid")
    private String email;

    private String gender; // 可以为空
    private LocalDate birthdate; // 可以为空
    private String address; // 可以为空
}
