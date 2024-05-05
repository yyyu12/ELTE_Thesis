package hu.elte.inf.backend.unitTest;

import hu.elte.inf.backend.dao.WishlistMapper;
import hu.elte.inf.backend.sqlEntity.Wishlist;
import hu.elte.inf.backend.sqlEntity.WishlistDetail;
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
public class WishlistMapperTest {

    @Autowired
    WishlistMapper wishlistMapper;

    @DirtiesContext
    @Test
    public void testSelectWishlistsByUserId(){
        List<Wishlist> wishlists = wishlistMapper.selectWishlistsByUserId(2L);
        assertNotNull(wishlists);
        assertFalse(wishlists.isEmpty());
    }

    @DirtiesContext
    @Test
    public void testSelectWishlistByUserIdAndArtworkId(){
        Wishlist wishlist = wishlistMapper.selectWishlistByUserIdAndArtworkId(2L, 2L);
        assertNotNull(wishlist);
        assertEquals(wishlist.getUser_id(), 2L);
        assertEquals(wishlist.getArtwork_id(), 2L);
    }

    @DirtiesContext
    @Test
    public void testSelectWishlistByWishlistId(){
        Wishlist wishlist = wishlistMapper.selectWishlistByWishlistId(1L);
        assertNotNull(wishlist);
        assertEquals(wishlist.getId(), 1L);
    }

    @DirtiesContext
    @Test
    public void testInsertWishlist(){
        Wishlist wishlist = new Wishlist();
        wishlist.setUser_id(4L);
        wishlist.setArtwork_id(5L);

        wishlistMapper.insertWishlist(wishlist);
    }

    @DirtiesContext
    @Test
    public void testDeleteWishlistByWishlistId(){
        wishlistMapper.deleteWishlistByWishlistId(1L);
        Wishlist wishlist = wishlistMapper.selectWishlistByWishlistId(1L);
        assertTrue(wishlist == null);
    }

    @DirtiesContext
    @Test
    public void testGetAllWishlistDetailsByUserId(){
        List<WishlistDetail> wishlistDetails = wishlistMapper.getAllWishlistDetailsByUserId(2L);
        assertNotNull(wishlistDetails);
        assertFalse(wishlistDetails.isEmpty());
    }
}
