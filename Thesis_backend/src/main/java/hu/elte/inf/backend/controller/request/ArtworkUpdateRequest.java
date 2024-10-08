package hu.elte.inf.backend.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ArtworkUpdateRequest {

    @NotNull(message = "Artist ID cannot be null")
    private Long artist_id; // 对应艺术家的ID，与artists表中的id关联

    @NotBlank(message = "Artwork title cannot be empty")
    private String title;

    private String description;

    @Positive(message = "Artwork price must be greater than zero")
    private BigDecimal price;

    @NotBlank(message = "Artwork image Url cannot be empty")
    private String image_url;

    @NotBlank(message = "Artwork type cannot be empty")
    private String type;
}
