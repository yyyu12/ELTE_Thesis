package hu.elte.inf.backend.sqlEntity;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class Artwork {
    private Long id;
    private Long artistId;
    private String title;
    private String description;
    private BigDecimal price;
    private String imageUrl;
    private String type;

    // Getter and Setter
}
