package hu.elte.inf.backend.dao;

import org.apache.ibatis.annotations.Mapper;
import hu.elte.inf.backend.sqlEntity.Wishlist;
import hu.elte.inf.backend.sqlEntity.WishlistDetail;

import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface WishlistMapper {

    List<WishlistDetail> getAllWishlistDetailsByUserId(@Param("user_id") Long user_id);
    void insertWishlist(Wishlist wishlist);

    Wishlist selectWishlistByWishlistId(Long wishlist_id);

    Wishlist selectWishlistByUserIdAndArtworkId(@Param("user_id") Long user_id, @Param("artwork_id") Long artwork_id);

    List<Wishlist> selectWishlistsByUserId(@Param("user_id") Long user_id);

    void deleteWishlistByWishlistId(Long wishlist_id);
}
