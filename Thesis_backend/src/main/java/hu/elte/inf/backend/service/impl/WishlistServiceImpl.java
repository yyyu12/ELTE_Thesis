package hu.elte.inf.backend.service.impl;

import hu.elte.inf.backend.service.WishlistService;
import hu.elte.inf.backend.dao.WishlistMapper;
import hu.elte.inf.backend.dao.ArtworkMapper;
import hu.elte.inf.backend.sqlEntity.Wishlist;
import org.springframework.beans.factory.annotation.Autowired;
import hu.elte.inf.backend.common.exceptionEnd.*;
import hu.elte.inf.backend.sqlEntity.WishlistDetail;

import java.util.List;


@org.springframework.stereotype.Service
public class WishlistServiceImpl implements WishlistService {

    @Autowired
    private WishlistMapper wishlistMapper;

    @Autowired
    private ArtworkMapper artworkMapper;

    @Override
    public List<WishlistDetail> getAllWishlistDetailsByUserId(Long user_id) {
        return wishlistMapper.getAllWishlistDetailsByUserId(user_id);
    }

    @Override
    public void addToWishlist(Long user_id, Long artwork_id) {
        // check if the artwork exists
        if (artworkMapper.getArtworkById(artwork_id) == null) {
            throw new ArtworkNotFoundException("Artwork Not Found. Please check the artwork id.");
        }

        // check if the wishlist exists
        Wishlist existingWishlist = wishlistMapper.selectWishlistByUserIdAndArtworkId(user_id, artwork_id);
        if(existingWishlist != null){
            throw new WishlistItemAlreadyExists("Wishlist Item Already Exists.");
        }else{
            Wishlist wishlist = new Wishlist();
            wishlist.setUser_id(user_id);
            wishlist.setArtwork_id(artwork_id);
            wishlistMapper.insertWishlist(wishlist);
        }

    }

    @Override
    public List<Wishlist> getWishlistsByUserId(Long user_id){
        return wishlistMapper.selectWishlistsByUserId(user_id);
    }

    @Override
    public void deleteWishlistByWishlistId(Long wishlist_id) {
        // Check if the wishlist exists
        Wishlist existingWishlist = wishlistMapper.selectWishlistByWishlistId(wishlist_id);

        if (existingWishlist == null) {
            throw new WishlistItemNotFound("Wishlist Item Not Found.");
        }

        wishlistMapper.deleteWishlistByWishlistId(wishlist_id);
    }
}
