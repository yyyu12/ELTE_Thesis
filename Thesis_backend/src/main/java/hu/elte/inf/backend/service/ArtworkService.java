package hu.elte.inf.backend.service;

import hu.elte.inf.backend.sqlEntity.Artwork;
import java.util.List;

public interface ArtworkService {
    /**
     * Get artwork by id
     * @param id
     * @return Artwork
     */
    Artwork getArtworkById(Long id);

    /**
     * Get all artworks
     * @return List<Artwork>
     */
    List<Artwork> getAllArtworks();

    /**
     * Get artworks by artist id
     * @param artist_id
     * @return List<Artwork>
     */
    List<Artwork> getArtworksByArtistId(Long artist_id);

    /**
     * Get artworks by user id
     * @param userId
     * @return List<Artwork>
     */
    List<Artwork> getArtworksByUserId(Long userId);

    /**
     * Add artwork
     * @param artwork
     * @return boolean
     */
    boolean addArtwork(Artwork artwork);

    /**
     * Delete artwork by id
     * @param id
     * @return boolean
     */
    boolean deleteArtworkById(Long id);

    /**
     * Update artwork
     * @param artwork
     * @return boolean
     */
    boolean updateArtwork(Artwork artwork);

    /**
     * Purchase artwork
     * @param user_id
     * @param artwork_id
     * @return
     */
    boolean purchaseArtwork(Long user_id, Long artwork_id);

    /**
     * Get random artwork by price range
     * @param minPrice
     * @param maxPrice
     * @return
     */
    Artwork getRandomArtworkByPriceRange(double minPrice, double maxPrice);
}
