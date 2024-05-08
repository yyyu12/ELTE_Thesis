package hu.elte.inf.backend.componentTest;

import hu.elte.inf.backend.sqlEntity.Artist;
import hu.elte.inf.backend.controller.ArtistController;
import hu.elte.inf.backend.service.impl.ArtistServiceImpl;
import hu.elte.inf.backend.controller.request.ArtistInsertRequest;
import hu.elte.inf.backend.controller.request.ArtistUpdateRequest;
import hu.elte.inf.backend.common.exceptionEnd.ArtistExistException;
import hu.elte.inf.backend.common.exceptionEnd.ArtistNotFoundException;

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

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers  = ArtistController.class)
@ActiveProfiles(value="test")
@AutoConfigureMybatis
public class ArtistControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArtistServiceImpl artistService;

    @Test
    public void testGetArtistById_Success() throws Exception {
        Long artistId = 1L;
        Artist artist = new Artist();
        artist.setId(artistId);
        artist.setName("Artist Name");

        when(artistService.getArtistById(artistId)).thenReturn(artist);

        mockMvc.perform(get("/artist/getArtistById/{id}", artistId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(artistId))
                .andExpect(jsonPath("$.name").value("Artist Name"));
    }

    @Test
    public void testGetArtistById_NotFound() throws Exception {
        Long artistId = 1L;
        when(artistService.getArtistById(artistId)).thenReturn(null);

        mockMvc.perform(get("/artist/getArtistById/{id}", artistId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testRegisterArtist_Success() throws Exception {
        Long artistId = 1L;
        Artist artist = new Artist();
        artist.setId(artistId);
        artist.setName("Van Gogh");

        when(artistService.getArtistById(artistId)).thenReturn(artist);

        mockMvc.perform(get("/artist/getArtistById/{id}", artistId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Van Gogh"));

        Long artistId1 = 2L;
        when(artistService.getArtistById(artistId1)).thenReturn(null);
            
        mockMvc.perform(get("/artist/getArtistById/{id}", artistId1))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetAllArtists() throws Exception{
        Artist artist1 = new Artist();
        artist1.setName("Geert Lemmers");
        artist1.setBio("Welcome to my page! I am Geert Lemmers. artist fine photo art, new media and paintings. Have a look at my work. If you have any questions contact me. Almost everything is possible.");

        Artist artist2 = new Artist();
        artist2.setName("Anjana Jain");
        artist2.setBio("I am an artist as well as homemaker. I become an artist at the age of 17 year. I make oil painting,sketches,portrait this is god gift for me .my family encouraged me to continue my painting and I came back professionally.");

        List<Artist> artists = Arrays.asList(artist1, artist2);

        given(artistService.getAllArtists()).willReturn(artists);

        mockMvc.perform(get("/artist/getAllArtists"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name").value("Geert Lemmers"))
            .andExpect(jsonPath("$[1].name").value("Anjana Jain"));
    }

    @Test
    public void testRegisterArtist_ArtistExists() throws Exception {
        ArtistInsertRequest artistInsertRequest = new ArtistInsertRequest();
        artistInsertRequest.setName("Van Gogh");
        artistInsertRequest.setBio("Van Gogh is a famous artist.");

        doThrow(new ArtistExistException("The Artist already exists.")).when(artistService).insertArtist(any(Artist.class));

        mockMvc.perform(post("/artist/registerArtist")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(artistInsertRequest)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.msg").value("The Artist already exists."));
    }

    @Test
    public void testDeleteArtist() throws Exception {
        Long artistId = 1L;
        doNothing().when(artistService).deleteArtist(artistId);

        mockMvc.perform(delete("/artist/deleteArtist/{id}", artistId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.msg").value("Artist deleted successfully"));
    }

    @Test
    public void testDeleteArtist_NotFound() throws Exception {
        Long artistId = 1L;
        doThrow(new ArtistNotFoundException("Artist with id " + artistId + " does not exist")).when(artistService).deleteArtist(artistId);

        mockMvc.perform(delete("/artist/deleteArtist/{id}", artistId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.msg").value("Artist with id " + artistId + " does not exist"));
    }


    @Test
    public void testUpdateArtistInfo() throws Exception {
        Long artistId = 1L;
        ArtistUpdateRequest request = new ArtistUpdateRequest();
        request.setName("Updated Name");

        Artist artist = new Artist();
        artist.setId(artistId);
        artist.setName("Updated Name");

        when(artistService.updateArtist(any(Artist.class))).thenReturn(artist);

        mockMvc.perform(put("/artist/updateArtistInfo/{id}", artistId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.info.name").value("Updated Name"));
    }
}
