package hu.elte.inf.backend.sqlEntity;

import java.time.LocalDate;
import lombok.Data;

// Use data have already included getter and setter methods
@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private String email;
    private Boolean isAdmin;
    private LocalDate birthdate;
    private String gender;
    private String address;

    // getter and setter
}

