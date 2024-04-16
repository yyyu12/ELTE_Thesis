package hu.elte.inf.backend.sqlEntity;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class Artwork {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String imageUrl;
    private String type;
    private Artist artist;  // 关联的艺术家对象

    // Getter and Setter
}
