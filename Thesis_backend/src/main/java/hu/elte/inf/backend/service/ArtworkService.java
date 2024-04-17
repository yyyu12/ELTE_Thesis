package hu.elte.inf.backend.service;

import hu.elte.inf.backend.sqlEntity.Artist;
import hu.elte.inf.backend.sqlEntity.Artwork;
import java.util.List;

public interface ArtworkService {
    List<Artwork> getAllArtworks();
    List<Artwork> getArtworksByArtistId(Long artistId);
    boolean addArtwork(Artwork artwork);
    boolean deleteArtworkById(Long id);
    boolean updateArtwork(Artwork artwork);
}
