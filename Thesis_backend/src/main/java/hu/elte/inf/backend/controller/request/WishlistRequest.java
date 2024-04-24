package hu.elte.inf.backend.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Add Artwork to Wishlist Request")
public class WishlistRequest {
    @NotNull(message = "User id cannot be null")
    private Long user_id;

    @NotNull(message = "Artwork id cannot be null")
    private Long artwork_id;
}