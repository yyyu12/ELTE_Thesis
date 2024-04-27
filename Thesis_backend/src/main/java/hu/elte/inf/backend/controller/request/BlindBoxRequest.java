package hu.elte.inf.backend.controller.request;

import java.math.BigDecimal;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Add Blind Box Request")
public class BlindBoxRequest {
    @NotNull(message = "User id cannot be null")
    private Long user_id;

    @NotNull(message = "Artwork id cannot be null")
    private Long artwork_id;

    @NotNull(message = "Price cannot be null")
    private BigDecimal price;
}
