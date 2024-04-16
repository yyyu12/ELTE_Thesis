package hu.elte.inf.backend.controller;

import hu.elte.inf.backend.common.Result;
import hu.elte.inf.backend.controller.request.ArtworkInsertRequest;
import hu.elte.inf.backend.controller.request.ArtistUpdateRequest;
import hu.elte.inf.backend.common.exceptionEnd.*;
import hu.elte.inf.backend.controller.response.ArtworkResponse;
import hu.elte.inf.backend.service.impl.ArtworkServiceImpl;
import hu.elte.inf.backend.sqlEntity.Artwork;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.apache.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping(value = "/artwork")
@CrossOrigin(origins = "*")// 将15行以下的东西
public class ArtworkController {
    @Autowired
    private ArtworkServiceImpl artworkService;

    @PostMapping("/registerArtwork")
    public ResponseEntity<Result> registerArtwork(@Validated @RequestBody ArtworkInsertRequest artistInsertRequest) {
        try {
            Artwork artwork = new Artwork();
            BeanUtils.copyProperties(artistInsertRequest, artwork);

            // Service call to insert the artwork, which might throw an exception if artwork already exists
            artworkService.insertArtwork(artwork);

            // Create response object and copy properties
            ArtworkResponse response = new ArtworkResponse();
            BeanUtils.copyProperties(artwork, response);

            return ResponseEntity.ok(Result.ok("Artwork added successfully.").put("artworkInfo", artistInsertRequest));
        } catch (ArtworkAlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.SC_CONFLICT).body(Result.error("Artist already exists"));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(Result.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(Result.error("Internal server error"));
        }
    }
}
