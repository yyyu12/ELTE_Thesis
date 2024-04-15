package hu.elte.inf.backend.sqlEntity;

import lombok.Data;

@Data
public class Artist {
    private Long id;
    private String name;
    private String bio;

    // Getter and Setter
}
