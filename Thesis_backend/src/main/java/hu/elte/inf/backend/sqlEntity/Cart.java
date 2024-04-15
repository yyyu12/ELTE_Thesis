package hu.elte.inf.backend.sqlEntity;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class Cart {
    private Long id;
    private Long userId;
    private Long artworkId;
    private Timestamp addedAt;

    // Getter and Setter
}
