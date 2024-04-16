package hu.elte.inf.backend.service;

import hu.elte.inf.backend.sqlEntity.Artist;
import hu.elte.inf.backend.sqlEntity.Artwork;
import java.util.List;

public interface ArtworkService {
    List<Artwork> getAllArtworks();
    void insertArtwork(Artwork artwork);
    void deleteArtwork(Long id);
    Artwork updateArtwork(Artwork artwork);
}
