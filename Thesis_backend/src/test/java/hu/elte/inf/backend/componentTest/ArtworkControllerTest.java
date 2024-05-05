package hu.elte.inf.backend.componentTest;

import hu.elte.inf.backend.sqlEntity.Artwork;
import hu.elte.inf.backend.controller.ArtworkController;
import hu.elte.inf.backend.service.impl.ArtworkServiceImpl;
import hu.elte.inf.backend.controller.request.ArtworkInsertRequest;
import hu.elte.inf.backend.controller.request.ArtworkUpdateRequest;
import hu.elte.inf.backend.common.exceptionEnd.ArtworkAlreadyExistsException;
import hu.elte.inf.backend.common.exceptionEnd.ArtworkNotFoundException;

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

import java.math.BigDecimal;
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

@WebMvcTest(controllers  = ArtworkController.class)
@ActiveProfiles(value="test")
@AutoConfigureMybatis
public class ArtworkControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArtworkServiceImpl artworkService;

    @Test
    public void testGetAllArtworks() throws Exception {
        Artwork artwork = new Artwork();
        artwork.setTitle("Still Life");
        artwork.setDescription("A beautiful painting alive.");
        artwork.setPrice(BigDecimal.valueOf(1200));
        artwork.setImage_url("assets/images/artworks/digital/Stil_life.jpeg");
        artwork.setType("digital");
        

        List<Artwork> artworks = Arrays.asList(artwork);
        when(artworkService.getAllArtworks()).thenReturn(artworks);

        mockMvc.perform(get("/artwork/getAllArtworks"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].title").value("Still Life"))
            .andExpect(jsonPath("$[0].description").value("A beautiful painting alive."))
            .andExpect(jsonPath("$[0].price").value(1200))
            .andExpect(jsonPath("$[0].image_url").value("assets/images/artworks/digital/Stil_life.jpeg"))
            .andExpect(jsonPath("$[0].type").value("digital"));
    }


    @Test
    public void testAddArtwork() throws Exception {
        ArtworkInsertRequest request = new ArtworkInsertRequest();
        request.setTitle("Still Life");
        request.setArtist_id(2L);
        request.setDescription("A beautiful painting alive.");
        request.setPrice(BigDecimal.valueOf(1200));
        request.setImage_url("assets/images/artworks/digital/Stil_life.jpeg");
        request.setType("digital");

        when(artworkService.addArtwork(any(Artwork.class))).thenReturn(true);

        mockMvc.perform(post("/artwork/registerArtwork")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.msg").value("Artwork added successfully."));
    }


    @Test
    public void testAddArtwork_Failure() throws Exception {
        ArtworkInsertRequest request = new ArtworkInsertRequest();
        request.setTitle("Still Life");
        request.setArtist_id(2L);
        request.setDescription("A beautiful painting alive.");
        request.setPrice(BigDecimal.valueOf(1200));
        request.setImage_url("assets/images/artworks/digital/Stil_life.jpeg");
        request.setType("digital");

        when(artworkService.addArtwork(any(Artwork.class))).thenReturn(false);

        mockMvc.perform(post("/artwork/registerArtwork")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.msg").value("Artwork already exists."));
    }

    @Test
    public void testDeleteArtwork_Success() throws Exception {
        Long artworkId = 1L;
        when(artworkService.deleteArtworkById(artworkId)).thenReturn(true);

        mockMvc.perform(delete("/artwork/deleteArtwork/{id}", artworkId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.msg").value("Artwork deleted successfully."));
    }

    @Test
    public void testDeleteArtwork_NotFound() throws Exception {
        Long artworkId = 1L;
        when(artworkService.deleteArtworkById(artworkId)).thenReturn(false);

        mockMvc.perform(delete("/artwork/deleteArtwork/{id}", artworkId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.msg").value("Artwork not found or could not be deleted."));
    }

    @Test
    public void testUpdateArtwork_Success() throws Exception {
        Long artworkId = 1L;
        ArtworkUpdateRequest request = new ArtworkUpdateRequest();
        request.setTitle("Still Life");
        request.setDescription("A beautiful painting alive.");
        request.setPrice(BigDecimal.valueOf(1200));
        request.setImage_url("assets/images/artworks/digital/Stil_life.jpeg");
        request.setType("digital");

        when(artworkService.updateArtwork(any(Artwork.class))).thenReturn(true);

        mockMvc.perform(put("/artwork/updateArtwork/{id}", artworkId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.msg").value("Artwork updated successfully."));
    }

    @Test
    public void testUpdateArtwork_NotFound() throws Exception {
        Long artworkId = 1L;
        ArtworkUpdateRequest request = new ArtworkUpdateRequest();
        request.setTitle("Still Life");
        request.setDescription("A beautiful painting alive.");
        request.setPrice(BigDecimal.valueOf(1200));
        request.setImage_url("assets/images/artworks/digital/Stil_life.jpeg");
        request.setType("digital");

        when(artworkService.updateArtwork(any(Artwork.class))).thenReturn(false);

        mockMvc.perform(put("/artwork/updateArtwork/{id}", artworkId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.msg").value("Artwork not found or could not be updated."));
    }
}
