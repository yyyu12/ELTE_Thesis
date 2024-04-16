package hu.elte.inf.backend.controller.response;

import hu.elte.inf.backend.sqlEntity.Artist;
import lombok.Data;

@Data
public class ArtworkResponse {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String imageUrl;
    private String type;
    private Artist artist;  // 关联的艺术家对象
}
