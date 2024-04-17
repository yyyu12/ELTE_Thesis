package hu.elte.inf.backend.controller.request;

import hu.elte.inf.backend.sqlEntity.Artist;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;

@Data
@Schema(description = "Artwork Insert Request")
public class ArtworkInsertRequest {

    @NotNull(message = "Artist ID cannot be null")
    private Long artistId; // 对应艺术家的ID，与artists表中的id关联

    @NotBlank(message = "Artwork title cannot be empty")
    private String title;

    private String description;

    @Positive(message = "Artwork price must be greater than zero")
    private BigDecimal price;

    @NotBlank(message = "Artwork image Url cannot be empty")
    private String imageUrl;

    @NotBlank(message = "Artwork type cannot be empty")
    private String type;
}
