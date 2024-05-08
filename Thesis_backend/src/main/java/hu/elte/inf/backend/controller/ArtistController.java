package hu.elte.inf.backend.controller;

import hu.elte.inf.backend.common.Result;
import hu.elte.inf.backend.controller.request.ArtistInsertRequest;
import hu.elte.inf.backend.controller.request.ArtistUpdateRequest;
import hu.elte.inf.backend.common.exceptionEnd.*;
import hu.elte.inf.backend.controller.response.ArtistResponse;
import hu.elte.inf.backend.service.impl.ArtistServiceImpl;
import hu.elte.inf.backend.sqlEntity.Artist;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.apache.http.HttpStatus;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping(value = "/artist")
@CrossOrigin(origins = "*")
public class ArtistController {
    @Autowired
    private ArtistServiceImpl artistService;

    /**
     * Get artist by id
     * @param id
     * @return artist
     */
    @GetMapping("/getArtistById/{id}")
    public ResponseEntity<Artist> getArtistById(@PathVariable Long id) {
        Artist artist = artistService.getArtistById(id);
        if (artist != null) {
            return ResponseEntity.ok(artist);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Get all artists
     * @return artists
     */
    @GetMapping("/getAllArtists")
    public ResponseEntity<List<Artist>> getAllArtists() {
        List<Artist> artists = artistService.getAllArtists();
        return ResponseEntity.ok(artists);
    }

    /**
     * Register artist
     * @param artistInsertRequest
     * @return
     */
    @PostMapping("/registerArtist")
    public ResponseEntity<Result> registerArtist(@Validated @RequestBody ArtistInsertRequest artistInsertRequest) {

        Artist artist = new Artist();
        BeanUtils.copyProperties(artistInsertRequest, artist);
        artistService.insertArtist(artist);

        ArtistResponse artistResponse = new ArtistResponse();
        BeanUtils.copyProperties(artist, artistResponse);

        return ResponseEntity.ok(Result.ok("Artist added successfully.").put("info", artistResponse));
    }

    /**
     * Delete artist by id
     * @param id
     * @return Result
     */
    @DeleteMapping("deleteArtist/{id}")
    public ResponseEntity<Result> deleteArtist(@PathVariable Long id) {
        artistService.deleteArtist(id);
        return ResponseEntity.ok(Result.ok("Artist deleted successfully"));
    }

    /**
     * Update artist info
     * @param id
     * @param artistUpdateRequest
     * @return
     */
    @PutMapping("/updateArtistInfo/{id}")
    public ResponseEntity<Result> updateArtistInfo(@PathVariable Long id, @Validated @RequestBody ArtistUpdateRequest artistUpdateRequest) {
        Artist artist = new Artist();
        BeanUtils.copyProperties(artistUpdateRequest, artist);
        artist.setId(id);

        Artist updatedArtist = artistService.updateArtist(artist);

        ArtistResponse artistResponse = new ArtistResponse();
        BeanUtils.copyProperties(updatedArtist, artistResponse);
        return ResponseEntity.ok(Result.ok("Artist info updated successfully").put("info", artistResponse));
    }

}
