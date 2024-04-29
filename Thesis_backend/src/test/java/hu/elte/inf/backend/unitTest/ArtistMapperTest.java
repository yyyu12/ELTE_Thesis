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
        assertEquals(artistList.size(),2);
    }

    @DirtiesContext
    @Test
    public void getArtistByIdTest(){
        Artist artist = artistMapper.getArtistById(1L);
        assertNotNull(artist);
        assertEquals(artist.getName(), "Anjana Jain");
    }

    @DirtiesContext
    @Test
    public void getArtistByNameTest(){
        Artist artist = artistMapper.getArtistByName("Anjana Jain");
        assertNotNull(artist);
        assertEquals(artist.getId(), 1L);
    }

    @DirtiesContext
    @Test
    public void insertArtistTest(){
        Artist artist = new Artist();
        artist.setName("Geert Lemmers");
        artist.setBio("Welcome to my page! I am Geert Lemmers. artist fine photo art, new media and paintings. Have a look at my work. If you have any questions contact me. Almost everything is possible.");

        int affectedRows = artistMapper.insertArtist(artist);
        assertEquals(affectedRows, 1);
    }

    @DirtiesContext
    @Test
    public void deleteArtistTest(){
        int affectedRows = artistMapper.deleteArtist(2L);
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
