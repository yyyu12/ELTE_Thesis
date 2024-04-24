package hu.elte.inf.backend.sqlEntity;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class CartDetail {
    private Long cart_id;
    private Timestamp added_at;
    private Long artwork_id;
    private String title;
    private String description;
    private Double price;
    private String image_url;
    private String type;
    private Long artist_id;
    private String artist_name;
    private String artist_bio;

    // Getter and Setter
}
