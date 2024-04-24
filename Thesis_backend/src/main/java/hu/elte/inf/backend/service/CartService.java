package hu.elte.inf.backend.service;

import hu.elte.inf.backend.sqlEntity.Cart;
import hu.elte.inf.backend.sqlEntity.CartDetail;
import java.util.List;

public interface CartService {
    List<CartDetail> getAllCartDetailsByUserId(Long user_id);
    void addToCart(Long user_id, Long artwork_id);
    List<Cart> getCartsByUserId(Long user_id);
    void removeCart(Long user_id, Long artwork_id);

    void removeCartByCartId(Long cart_id);
}
