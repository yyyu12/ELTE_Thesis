package hu.elte.inf.backend.componentTest;

import hu.elte.inf.backend.sqlEntity.Cart;
import hu.elte.inf.backend.sqlEntity.CartDetail;
import hu.elte.inf.backend.controller.CartController;
import hu.elte.inf.backend.service.impl.CartServiceImpl;
import hu.elte.inf.backend.controller.request.CartRequest;
import hu.elte.inf.backend.common.exceptionEnd.CartItemAlreadyExists;
import hu.elte.inf.backend.common.exceptionEnd.CartItemNotFound;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = CartController.class)
@ActiveProfiles(value="test")
@AutoConfigureMybatis
public class CartControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartServiceImpl cartService;

    @Test
    public void testGetCartDetailsByUserId() throws Exception {
        CartDetail cartDetail = new CartDetail();
        cartDetail.setCart_id(1L);
        cartDetail.setAdded_at(new java.sql.Timestamp(System.currentTimeMillis()));
        cartDetail.setArtwork_id(1L);
        cartDetail.setTitle("Title");
        cartDetail.setDescription("Description");
        cartDetail.setPrice(1.0);
        cartDetail.setImage_url("Image URL");
        cartDetail.setType("Type");
        cartDetail.setArtist_id(1L);
        cartDetail.setArtist_name("Artist Name");
        cartDetail.setArtist_bio("Artist Bio");

        List<CartDetail> cartDetails = Arrays.asList(cartDetail);
        when(cartService.getAllCartDetailsByUserId(anyLong())).thenReturn(cartDetails);
    
        mockMvc.perform(get("/cart/user/{user_id}/details", 1L))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$[0].title").exists());
    }

    @Test
    public void testAddToCart() throws Exception {
        CartRequest cartRequest = new CartRequest();
        cartRequest.setUser_id(1L);
        cartRequest.setArtwork_id(2L);

        doNothing().when(cartService).addToCart(cartRequest.getUser_id(), cartRequest.getArtwork_id());

        mockMvc.perform(post("/cart/addToCart")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(cartRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.msg").value("Item added to cart"));
    }

    @Test
    public void testRemoveByCartId_Success() throws Exception {
        Long cartId = 1L;
        doNothing().when(cartService).removeCartByCartId(cartId);
    
        mockMvc.perform(delete("/cart/removeByCartId/{cart_id}", cartId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.msg").value("Artwork deleted successfully."));
    }
}
