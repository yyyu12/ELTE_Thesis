package hu.elte.inf.backend.sqlEntity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import lombok.Data;

@Data
public class BlindBox {

    private Long id;
    private Long user_id;
    private Long artwork_id;
    private Timestamp purchase_time;
    private BigDecimal price;

    // Getter and Setter
}
