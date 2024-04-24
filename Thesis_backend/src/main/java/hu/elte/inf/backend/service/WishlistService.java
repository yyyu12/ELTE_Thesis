package hu.elte.inf.backend.service;

import hu.elte.inf.backend.sqlEntity.Wishlist;
import hu.elte.inf.backend.sqlEntity.WishlistDetail;
import java.util.List;

public interface WishlistService {
    List<WishlistDetail> getAllWishlistDetailsByUserId(Long user_id);
    void addToWishlist(Long user_id, Long artwork_id);
    
    List<Wishlist> getWishlistsByUserId(Long user_id);

    void deleteWishlistByWishlistId(Long wishlist_id);
}
