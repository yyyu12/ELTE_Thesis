package hu.elte.inf.backend.service;

import hu.elte.inf.backend.sqlEntity.Artist;
import hu.elte.inf.backend.sqlEntity.Artwork;
import java.util.List;

public interface ArtworkService {
    Artwork getArtworkById(Long id);
    List<Artwork> getAllArtworks();
    List<Artwork> getArtworksByArtistId(Long artist_id);
    List<Artwork> getArtworksByUserId(Long userId);
    boolean addArtwork(Artwork artwork);
    boolean deleteArtworkById(Long id);
    boolean updateArtwork(Artwork artwork);
    boolean purchaseArtwork(Long user_id, Long artwork_id);
    Artwork getRandomArtworkByPriceRange(double minPrice, double maxPrice);
}
