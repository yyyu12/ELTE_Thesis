package hu.elte.inf.backend.unitTest;

import hu.elte.inf.backend.dao.ArtworkMapper;
import hu.elte.inf.backend.sqlEntity.Artwork;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@ActiveProfiles(value = "test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ArtworkMapperTest {

    @Autowired
    ArtworkMapper artworkMapper;

    @DirtiesContext
    @Test
    public void findAllArtworksTest() {
        List<Artwork> artworkList = artworkMapper.findAllArtworks();
        assertNotNull(artworkList);
        assertEquals(artworkList.size(), 11);
    }

    @DirtiesContext
    @Test
    public void getArtworkByIdTest() {
        Artwork artwork = artworkMapper.getArtworkById(1L);
        assertNotNull(artwork);
        assertEquals(artwork.getTitle(), "Still Life");
    }

    @DirtiesContext
    @Test
    public void findArtworksByArtistIdTest() {
        List<Artwork> artworkList = artworkMapper.findArtworksByArtistId(1L);
        assertNotNull(artworkList);
        assertEquals(artworkList.size(), 2);
    }

    @DirtiesContext
    @Test
    public void findArtworksByUserIdTest() {
        List<Artwork> artworkList = artworkMapper.findArtworksByUserId(2L);
        assertNotNull(artworkList);
        assertEquals(artworkList.size(), 1);
    }

    @DirtiesContext
    @Test
    public void findArtworkByTitleAndArtistIdTest() {
        Map<String, Object> params = new HashMap<>();
        params.put("title", "Still Life");
        params.put("artist_id", 1L);

        List<Artwork> artworks = artworkMapper.findArtworkByTitleAndArtistId(params);
        assertNotNull(artworks);
        assertFalse(artworks.isEmpty());
    }

    @DirtiesContext
    @Test
    public void insertArtworkTest() {
        Artwork artwork = new Artwork();
        artwork.setArtist_id(2L);
        artwork.setTitle("Monochromatic Sunshine");
        artwork.setDescription(
                "The artwork radiates joy, depicting the sun as a luminous orb casting intricate shadows.");
        artwork.setPrice(BigDecimal.valueOf(850));
        artwork.setImage_url("assets/images/artworks/NFT/Monochromatic_Sunshine.jpeg");
        artwork.setType("nft");

        int result = artworkMapper.insertArtwork(artwork);
        assertEquals(result, 1);
    }

    @DirtiesContext
    @Test
    public void deleteArtworkTest() {
        int result = artworkMapper.deleteArtwork(11L);
        assertEquals(1, result);
    }

    @DirtiesContext
    @Test
    public void updateArtworkTest() {
        Artwork artwork = artworkMapper.getArtworkById(1L);
        assertNotNull(artwork);

        artwork.setArtist_id(1L);
        artwork.setTitle("Still Life with Flowers");
        artwork.setDescription("The artwork depicts a vase of flowers, with a variety of flowers in full bloom.");
        artwork.setPrice(BigDecimal.valueOf(500));
        artwork.setImage_url("assets/images/artworks/Still_Life_with_Flowers.jpeg");
        artwork.setType("painting");

        int result = artworkMapper.updateArtwork(artwork);
        assertEquals(1, result);

        Artwork updatedArtwork = artworkMapper.getArtworkById(1L);
        assertNotNull(updatedArtwork);
        assertEquals("Still Life with Flowers", updatedArtwork.getTitle());
        assertEquals("The artwork depicts a vase of flowers, with a variety of flowers in full bloom.",
                updatedArtwork.getDescription());
        assertEquals(0, BigDecimal.valueOf(500).compareTo(updatedArtwork.getPrice()));
        assertEquals("assets/images/artworks/Still_Life_with_Flowers.jpeg", updatedArtwork.getImage_url());
        assertEquals("painting", updatedArtwork.getType());
    }
}
