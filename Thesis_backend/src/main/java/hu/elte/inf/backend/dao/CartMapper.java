package hu.elte.inf.backend.dao;

import hu.elte.inf.backend.sqlEntity.Cart;
import hu.elte.inf.backend.sqlEntity.CartDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

// 数据访问层负责与数据库进行直接交互
@Mapper
public interface CartMapper {
    /**
     * Get all cart details by user id
     * @param user_id
     * @return
     */
    List<CartDetail> getAllCartDetailsByUserId(@Param("user_id") Long user_id);

    /**
     * Insert cart
     * @param cart
     */
    void insertCart(Cart cart);

    /**
     * Select cart by user id and artwork id
     * @param user_id
     * @param artwork_id
     * @return
     */
    Cart selectCartByUserIdAndArtworkId(@Param("user_id") Long user_id, @Param("artwork_id") Long artwork_id);

    /**
     * Select cart by cart id
     * @param cart_id
     * @return
     */
    Cart selectCartByCartId(Long cart_id);

    /**
     * Get all carts by user id
     * @param user_id
     * @return
     */
    List<Cart> selectCartsByUserId(Long user_id);

    /**
     * Delete cart by cart id
     * @param cart_id
     */
    void deleteCartByCartId(Long cart_id);

    /**
     * Delete cart by user id and artwork id
     * @param user_id
     * @param artwork_id
     */
    void deleteCartByUserIdAndArtworkId(@Param("user_id") Long user_id, @Param("artwork_id") Long artwork_id);
    
}
