package hu.elte.inf.backend.componentTest;

import hu.elte.inf.backend.sqlEntity.BlindBox;
import hu.elte.inf.backend.sqlEntity.BlindBoxDetail;
import hu.elte.inf.backend.controller.BlindBoxController;
import hu.elte.inf.backend.service.impl.BlindBoxServiceImpl;
import hu.elte.inf.backend.controller.request.BlindBoxRequest;

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

@WebMvcTest(controllers = BlindBoxController.class)
@ActiveProfiles(value="test")
@AutoConfigureMybatis
public class BlindBoxControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BlindBoxServiceImpl blindBoxService;

    @Test
    public void testGetAllBlindBoxDetails() throws Exception {
        BlindBoxDetail blindBoxDetail = new BlindBoxDetail();
        blindBoxDetail.setBlind_box_id(1L);
        blindBoxDetail.setPurchase_time(new java.sql.Timestamp(System.currentTimeMillis()));
        blindBoxDetail.setArtwork_id(1L);
        blindBoxDetail.setTitle("Title");
        blindBoxDetail.setDescription("Description");
        blindBoxDetail.setPrice(1200.0);
        blindBoxDetail.setImage_url("Image URL");
        blindBoxDetail.setType("Type");
        blindBoxDetail.setArtist_name("Artist Name");
        blindBoxDetail.setArtist_id(1L);
        blindBoxDetail.setArtist_bio("Artist Bio");

        List<BlindBoxDetail> blindBoxDetails = List.of(blindBoxDetail);

        when(blindBoxService.getAllBlindBoxDetails()).thenReturn(blindBoxDetails);

        mockMvc.perform(get("/blindBox/details"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].title").exists());
    }

    @Test
    public void testAddBlindBox() throws Exception {
        BlindBoxRequest request = new BlindBoxRequest();
        request.setUser_id(1L);
        request.setArtwork_id(2L);
        request.setPrice(new BigDecimal("150.00"));

        when(blindBoxService.addBlindBox(any(BlindBox.class))).thenReturn(true);

        mockMvc.perform(post("/blindBox/addBlindBox")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.msg").value("Blind box added successfully"));
    }

    @Test
    public void testAddBlindBox_Failure() throws Exception {
        BlindBoxRequest request = new BlindBoxRequest();
        request.setUser_id(1L);
        request.setArtwork_id(2L);
        request.setPrice(new BigDecimal("150.00"));

        when(blindBoxService.addBlindBox(any(BlindBox.class))).thenReturn(false);

        mockMvc.perform(post("/blindBox/addBlindBox")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.msg").value("Blind box add failed"));
    }

    
}
