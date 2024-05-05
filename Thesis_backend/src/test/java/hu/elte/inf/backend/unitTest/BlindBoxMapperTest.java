package hu.elte.inf.backend.unitTest;

import hu.elte.inf.backend.dao.BlindBoxMapper;
import hu.elte.inf.backend.sqlEntity.BlindBox;
import hu.elte.inf.backend.sqlEntity.BlindBoxDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@ActiveProfiles(value = "test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class BlindBoxMapperTest {

    @Autowired
    BlindBoxMapper blindBoxMapper;

    @DirtiesContext
    @Test
    public void insertBlindBoxTest(){
        BlindBox blindBox = new BlindBox();
        blindBox.setUser_id(2L);
        blindBox.setArtwork_id(1L);
        blindBox.setPrice(BigDecimal.valueOf(125));

        int result = blindBoxMapper.insertBlindBox(blindBox);
        assertEquals(1, result);
        assertNotNull(blindBox.getId()); 
    }

    @DirtiesContext
    @Test
    public void testSelectBlindBoxesByUserId() {
        Long userId = 2L;
        List<BlindBox> blindBoxes = blindBoxMapper.selectBlindBoxesByUserId(userId);
        assertNotNull(blindBoxes);
        assertFalse(blindBoxes.isEmpty());
        assertTrue(blindBoxes.stream().allMatch(bb -> bb.getUser_id().equals(userId)));
    }

    @DirtiesContext
    @Test
    public void testSelectBlindBoxByUserIdAndArtworkId() {
        Long userId = 2L;
        Long artworkId = 1L;
        BlindBox blindBox = blindBoxMapper.selectBlindBoxByUserIdAndArtworkId(userId, artworkId);
        assertNotNull(blindBox);
        assertEquals(userId, blindBox.getUser_id());
        assertEquals(artworkId, blindBox.getArtwork_id());
    }

    @DirtiesContext
    @Test
    public void testSelectBlindBoxByBlindBoxId() {
        BlindBox blindBox = blindBoxMapper.selectBlindBoxByBlindBoxId(1L);
        assertNotNull(blindBox);
        assertEquals(1L, blindBox.getId());
    }

    @DirtiesContext
    @Test
    public void testGetAllBlindBoxDetails() {
        List<BlindBoxDetail> details = blindBoxMapper.getAllBlindBoxDetails();
        assertNotNull(details);
        assertFalse(details.isEmpty());
    }
}
