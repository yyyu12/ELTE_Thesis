package hu.elte.inf.backend.controller.request;

import hu.elte.inf.backend.sqlEntity.Artist;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Schema(description = "Artwork Insert Request")
public class ArtworkInsertRequest {
    private Long id;

    @NotBlank(message = "Artwork title cannot be empty")
    private String title;

    private String description;

    @Positive(message = "Artwork price must be greater than zero")
    private Double price;

    @NotBlank(message = "Artwork image Url cannot be empty")
    private String imageUrl;

    @NotBlank(message = "Artwork type cannot be empty")
    private String type;

    private Artist artist;  // 关联的艺术家对象

}
