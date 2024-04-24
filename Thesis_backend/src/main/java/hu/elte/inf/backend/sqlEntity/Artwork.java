package hu.elte.inf.backend.sqlEntity;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class Artwork {
    private Long id;
    private Long artist_id;
    private String title;
    private String description;
    private BigDecimal price;
    private String image_url;
    private String type;
    private Long user_id;
    
    // Getter and Setter
}
