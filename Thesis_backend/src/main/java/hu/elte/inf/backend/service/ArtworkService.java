package hu.elte.inf.backend.service;

import hu.elte.inf.backend.sqlEntity.Artwork;
import java.util.List;

public interface ArtworkService {
    void insertArtwork(Artwork artwork);
    void deleteArtwork(Long id);
    Artwork updateArtwork(Artwork artwork);
}
