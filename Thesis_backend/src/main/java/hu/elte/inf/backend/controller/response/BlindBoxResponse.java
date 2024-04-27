package hu.elte.inf.backend.controller.response;

import java.math.BigDecimal;
import java.sql.Timestamp;
import lombok.Data;

@Data
public class BlindBoxResponse {
    private Long id;
    private Long user_id;
    private Long artwork_id;
    private BigDecimal price;
}
