package hu.elte.inf.backend.sqlEntity;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class Wishlist {
    private Long id;
    private Long user_id;
    private Long artwork_id;
    private Timestamp added_at;

    // Getter and Setter
}
