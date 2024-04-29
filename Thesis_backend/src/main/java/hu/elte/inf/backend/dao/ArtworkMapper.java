package hu.elte.inf.backend.dao;

import hu.elte.inf.backend.sqlEntity.Artwork;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ArtworkMapper {
    /**
     * Find all artworks
     * @return List<Artwork>
     */
    List<Artwork> findAllArtworks();

    /**
     * Find artworks by artist id
     * @param artist_id
     * @return List<Artwork>
     */
    List<Artwork> findArtworksByArtistId(Long artist_id);

    /**
     * Find artworks by user id
     * @param user_id
     * @return List<Artwork>
     */
    List<Artwork> findArtworksByUserId(Long user_id);

    /**
     * Find artworks by title and artist id
     * @param params
     * @return List<Artwork>
     */
    List<Artwork> findArtworkByTitleAndArtistId(Map<String, Object> params);
    
    /**
     * Find artwork by id
     * @param id
     * @return Artwork
     */
    Artwork getArtworkById(Long id);

    /**
     * Insert artwork
     * @param artwork
     * @return int
     */
    int insertArtwork(Artwork artwork);

    /**
     * Update artwork
     * @param artwork
     * @return int
     */
    int updateArtwork(Artwork artwork);

    /**
     * Delete artwork
     * @param id
     * @return int
     */
    int deleteArtwork(Long id);

    /**
     * Purchase artwork
     * @param user_id
     * @param artwork_id
     * @return int
     */
    int purchaseArtwork(@Param("user_id") Long user_id, @Param("artwork_id") Long artwork_id);

    /**
     * Find random artwork by price range
     * @param minPrice
     * @param maxPrice
     * @return Artwork
     */
    Artwork findRandomArtworkByPriceRange(@Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);
}
