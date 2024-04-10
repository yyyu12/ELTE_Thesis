package hu.elte.inf.backend.controller.response;

import lombok.Data;
import java.time.LocalDate;

@Data
public class UserLoginResponse {
    private Long id;
    private String username;
    private String email;
    private Boolean isAdmin;
    private LocalDate birthdate;
    private String gender;
    private String address;
}
