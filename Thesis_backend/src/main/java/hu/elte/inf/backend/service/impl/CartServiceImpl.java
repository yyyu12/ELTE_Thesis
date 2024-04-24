package hu.elte.inf.backend.service.impl;


import hu.elte.inf.backend.dao.CartMapper;
import hu.elte.inf.backend.dao.ArtworkMapper;
import hu.elte.inf.backend.service.CartService;
import hu.elte.inf.backend.sqlEntity.Cart;
import hu.elte.inf.backend.sqlEntity.CartDetail;
import hu.elte.inf.backend.common.exceptionEnd.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private ArtworkMapper artworkMapper;

    @Override
    public List<CartDetail> getAllCartDetailsByUserId(Long user_id) {
        return cartMapper.getAllCartDetailsByUserId(user_id);
    }

    @Override
    public void addToCart(Long user_id, Long artwork_id) {
        // check if the artwork exists
        if (artworkMapper.getArtworkById(artwork_id) == null) {
            throw new ArtworkNotFoundException("Artwork Not Found. Please check the artwork id.");
        }

        // check if the cart exists
        Cart existingCart = cartMapper.selectCartByUserIdAndArtworkId(user_id, artwork_id);
        if(existingCart != null){
            throw new CartItemAlreadyExists("Cart Item Already Exists.");
        }else{
            Cart cart = new Cart();
            cart.setUser_id(user_id);
            cart.setArtwork_id(artwork_id);
            cartMapper.insertCart(cart);
        }

    }

    @Override
    public List<Cart> getCartsByUserId(Long user_id){
        return cartMapper.selectCartsByUserId(user_id);
    }

    @Override
    public void removeCart(Long user_id, Long artwork_id) {
        // Check if the cart exists
        Cart existingCart = cartMapper.selectCartByUserIdAndArtworkId(user_id, artwork_id);

        if (existingCart == null) {
            throw new CartItemNotFound("Cart Item Not Found.");
        }

        cartMapper.deleteCartByUserIdAndArtworkId(user_id, artwork_id);
    }

    @Override
    public void removeCartByCartId(Long cart_id) {
        // Check if the cart exists
        Cart existingCart = cartMapper.selectCartByCartId(cart_id);

        if (existingCart == null) {
            throw new CartItemNotFound("Cart Item Not Found.");
        }

        cartMapper.deleteCartByCartId(cart_id);
    }

}
