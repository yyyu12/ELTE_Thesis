package hu.elte.inf.backend.sqlEntity;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class Cart {
    private Long id;
    private Long user_id;
    private Long artwork_id;
    private Timestamp added_at;

    // Getter and Setter
}

