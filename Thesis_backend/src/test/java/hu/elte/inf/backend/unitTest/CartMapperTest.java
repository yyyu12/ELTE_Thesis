package hu.elte.inf.backend.unitTest;

import hu.elte.inf.backend.dao.CartMapper;
import hu.elte.inf.backend.sqlEntity.Cart;
import hu.elte.inf.backend.sqlEntity.CartDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@ActiveProfiles(value = "test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CartMapperTest {
    @Autowired
    CartMapper cartMapper;

    @DirtiesContext
    @Test
    public void testselectCartsByUserId(){
        List<Cart> carts = cartMapper.selectCartsByUserId(2L);
        assertNotNull(carts);
        assertFalse(carts.isEmpty());
    }

    @DirtiesContext
    @Test
    public void testselectCartByUserIdAndArtworkId(){
        Cart cart = cartMapper.selectCartByUserIdAndArtworkId(2L, 1L);
        assertNotNull(cart);
        assertEquals(cart.getUser_id(), 2L);
        assertEquals(cart.getArtwork_id(), 1L);
    }

    @DirtiesContext
    @Test
    public void testSelectCartByCartId(){
        Cart cart = cartMapper.selectCartByCartId(1L);
        assertNotNull(cart);
        assertEquals(cart.getId(), 1L);
    }

    @DirtiesContext
    @Test
    public void testInsertCart(){
        Cart cart = new Cart();
        cart.setUser_id(2L);
        cart.setArtwork_id(2L);

        cartMapper.insertCart(cart);

        Cart cart1 = cartMapper.selectCartByUserIdAndArtworkId(2L, 2L);
        assertNotNull(cart1);
        assertEquals(cart1.getUser_id(), 2L);
        assertEquals(cart1.getArtwork_id(), 2L);
    }

    @DirtiesContext
    @Test
    public void testDeleteCartByCartId(){
        cartMapper.deleteCartByCartId(1L);
        Cart cart = cartMapper.selectCartByCartId(1L);
        assertTrue(cart == null);
    }

    @DirtiesContext
    @Test
    public void testDeleteCartByUserIdAndArtworkId(){
        cartMapper.deleteCartByUserIdAndArtworkId(2L, 1L);
        Cart cart = cartMapper.selectCartByUserIdAndArtworkId(2L, 1L);
        assertTrue(cart == null);
    }

    @DirtiesContext
    @Test
    public void testGetAllCartDetailsByUserId(){
        List<CartDetail> cartDetails = cartMapper.getAllCartDetailsByUserId(2L);
        assertNotNull(cartDetails);
        assertFalse(cartDetails.isEmpty());
    }
}
