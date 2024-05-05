package hu.elte.inf.backend.unitTest;

import hu.elte.inf.backend.dao.ArtistMapper;
import hu.elte.inf.backend.sqlEntity.Artist;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@ActiveProfiles(value="test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ArtistMapperTest {
    @Autowired
    ArtistMapper artistMapper;

    @DirtiesContext
    @Test
    public void findAllArtistsTest(){
        List<Artist> artistList = artistMapper.findAllArtists();
        assertNotNull(artistList);
        assertEquals(artistList.size(),6);
    }

    @DirtiesContext
    @Test
    public void getArtistByIdTest(){
        Artist artist = artistMapper.getArtistById(1L);
        assertNotNull(artist);
        assertEquals(artist.getName(), "Geert Lemmers");
    }

    @DirtiesContext
    @Test
    public void getArtistByNameTest(){
        Artist artist = artistMapper.getArtistByName("Geert Lemmers");
        assertNotNull(artist);
        assertEquals(artist.getId(), 1L);
    }

    @DirtiesContext
    @Test
    public void insertArtistTest(){
        Artist artist = new Artist();
        artist.setName("Test Artist");
        artist.setBio("Test Bio");

        int affectedRows = artistMapper.insertArtist(artist);
        assertEquals(affectedRows, 1);
    }

    @DirtiesContext
    @Test
    public void deleteArtistTest(){
        Artist artist = new Artist();
        artist.setName("Test Artist");
        artist.setBio("Test Bio");
        artistMapper.insertArtist(artist);

        int affectedRows = artistMapper.deleteArtist(artist.getId());
        assertEquals(affectedRows, 1);
    }

    @DirtiesContext
    @Test
    public void updateArtistTest(){
        Artist artist = artistMapper.getArtistById(1L);
        artist.setBio("Updated bio");

        int affectedRows = artistMapper.updateArtist(artist);
        assertEquals(affectedRows, 1);
        assertEquals(artist.getBio(), "Updated bio");
    }
}
