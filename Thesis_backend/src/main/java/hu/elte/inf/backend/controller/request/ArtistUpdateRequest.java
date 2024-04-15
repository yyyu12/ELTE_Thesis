package hu.elte.inf.backend.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Artist Information Request")
public class ArtistUpdateRequest {
    private String name;

    private String bio;
}
