package hu.elte.inf.backend.sqlEntity;

import java.sql.Timestamp;
import lombok.Data;

@Data
public class BlindBoxDetail {

    private Long blind_box_id;
    private Timestamp purchase_time;
    private Double blind_box_price;
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
