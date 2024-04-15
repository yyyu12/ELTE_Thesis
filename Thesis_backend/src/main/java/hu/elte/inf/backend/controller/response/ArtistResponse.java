package hu.elte.inf.backend.controller.response;

import lombok.Data;

@Data
public class ArtistResponse {
    private Long id;
    private String name;
    private String bio;
}
