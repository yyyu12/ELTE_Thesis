package hu.elte.inf.backend.componentTest;

import hu.elte.inf.backend.sqlEntity.Wishlist;
import hu.elte.inf.backend.sqlEntity.WishlistDetail;
import hu.elte.inf.backend.controller.WishlistController;
import hu.elte.inf.backend.service.impl.WishlistServiceImpl;
import hu.elte.inf.backend.controller.request.WishlistRequest;
import hu.elte.inf.backend.common.exceptionEnd.WishlistItemAlreadyExists;
import hu.elte.inf.backend.common.exceptionEnd.WishlistItemNotFound;

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

@WebMvcTest(controllers = WishlistController.class)
@ActiveProfiles(value="test")
@AutoConfigureMybatis
public class WishlistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WishlistServiceImpl wishlistService;

    @Test
    public void testGetWishlistDetailsByUserId() throws Exception {
        Long userId = 1L;
        List<WishlistDetail> details = List.of(new WishlistDetail());

        when(wishlistService.getAllWishlistDetailsByUserId(userId)).thenReturn(details);

        mockMvc.perform(get("/wishlist/user/{user_id}/details", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0]").exists());
    }

    @Test
    public void testAddToWishlist() throws Exception {
        WishlistRequest request = new WishlistRequest();
        request.setUser_id(1L);
        request.setArtwork_id(2L);

        doNothing().when(wishlistService).addToWishlist(request.getUser_id(), request.getArtwork_id());

        mockMvc.perform(post("/wishlist/addToWishlist")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.msg").value("Item added to wishlist"));
    }

    @Test
    public void testDeleteWishlistByWishlistId_Success() throws Exception {
        Long wishlistId = 1L;
        doNothing().when(wishlistService).deleteWishlistByWishlistId(wishlistId);

        mockMvc.perform(delete("/wishlist/deleteWishlistByWishlistId/{wishlist_id}", wishlistId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.msg").value("Item deleted from wishlist"));
    }

    @Test
    public void testDeleteWishlistByWishlistId_NotFound() throws Exception {
        Long wishlistId = 2L;
        doThrow(new WishlistItemNotFound("Wishlist Item Not Found.")).when(wishlistService).deleteWishlistByWishlistId(wishlistId);

        mockMvc.perform(delete("/wishlist/deleteWishlistByWishlistId/{wishlist_id}", wishlistId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.msg").value("Wishlist Item Not Found."));
    }




    
}
