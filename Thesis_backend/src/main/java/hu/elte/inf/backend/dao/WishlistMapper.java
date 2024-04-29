package hu.elte.inf.backend.dao;

import org.apache.ibatis.annotations.Mapper;
import hu.elte.inf.backend.sqlEntity.Wishlist;
import hu.elte.inf.backend.sqlEntity.WishlistDetail;

import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface WishlistMapper {
    /**
     * Get all wishlist details by user id
     * @param user_id
     * @return
     */
    List<WishlistDetail> getAllWishlistDetailsByUserId(@Param("user_id") Long user_id);
    
    /**
     * Insert a new wishlist
     * @param wishlist
     */
    void insertWishlist(Wishlist wishlist);

    /**
     * Select a wishlist by wishlist id
     * @param wishlist_id
     * @return
     */
    Wishlist selectWishlistByWishlistId(Long wishlist_id);

    /**
     * Select a wishlist by user id and artwork id
     * @param user_id
     * @param artwork_id
     * @return
     */
    Wishlist selectWishlistByUserIdAndArtworkId(@Param("user_id") Long user_id, @Param("artwork_id") Long artwork_id);

    /**
     * Select wishlists by user id
     * @param user_id
     * @return
     */
    List<Wishlist> selectWishlistsByUserId(@Param("user_id") Long user_id);

    /**
     * Delete a wishlist by wishlist id
     * @param wishlist_id
     */
    void deleteWishlistByWishlistId(Long wishlist_id);
}
