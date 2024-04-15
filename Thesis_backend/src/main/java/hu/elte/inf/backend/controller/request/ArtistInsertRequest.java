package hu.elte.inf.backend.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Artist Insert Request")
public class ArtistInsertRequest {
    @NotBlank(message = "Artist name cannot be empty")
    private String name;

    private String bio;
}
