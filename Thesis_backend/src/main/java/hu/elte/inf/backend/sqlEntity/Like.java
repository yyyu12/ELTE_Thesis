package hu.elte.inf.backend.sqlEntity;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class Like {
    private Long userId;
    private Long artworkId;
    private Timestamp createdAt;

    // Getter and Setter
}
