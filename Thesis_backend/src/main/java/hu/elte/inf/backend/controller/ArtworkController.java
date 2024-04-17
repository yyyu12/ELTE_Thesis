package hu.elte.inf.backend.controller;

import hu.elte.inf.backend.common.Result;
import hu.elte.inf.backend.controller.request.ArtworkInsertRequest;
import hu.elte.inf.backend.controller.request.ArtistUpdateRequest;
import hu.elte.inf.backend.common.exceptionEnd.*;
import hu.elte.inf.backend.controller.request.ArtworkUpdateRequest;
import hu.elte.inf.backend.controller.response.ArtistResponse;
import hu.elte.inf.backend.controller.response.ArtworkResponse;
import hu.elte.inf.backend.service.impl.ArtworkServiceImpl;
import hu.elte.inf.backend.sqlEntity.Artist;
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

    @GetMapping("/getAllArtworks")
    public ResponseEntity<List<Artwork>> getAllArtworks() {
        List<Artwork> artworks = artworkService.getAllArtworks();
        return ResponseEntity.ok(artworks);
    }

    @GetMapping("/getArtworksByArtistId/{artistId}")
    public ResponseEntity<List<Artwork>> getArtworksByArtistId(@PathVariable Long artistId) {
        List<Artwork> artworks = artworkService.getArtworksByArtistId(artistId);
        return ResponseEntity.ok(artworks);
    }

    @PostMapping("/registerArtwork")
    public ResponseEntity<Result> addArtwork(@Validated @RequestBody  ArtworkInsertRequest artworkInsertRequest) {
        Artwork artwork = new Artwork();
        BeanUtils.copyProperties(artworkInsertRequest, artwork);

        boolean added = artworkService.addArtwork(artwork);  // 调用服务层的添加方法
        if (added) {
            ArtworkResponse artworkResponse = new ArtworkResponse();
            BeanUtils.copyProperties(artwork, artworkResponse);
            return  ResponseEntity.ok(Result.ok("Artwork added successfully.").put("artworkInfo", artworkResponse));
        } else {
            return  ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(Result.error("Artwork already exists."));
        }
    }

    @DeleteMapping("deleteArtwork/{id}")
    public ResponseEntity<Result> deleteArtwork(@PathVariable Long id) {
        boolean deleted = artworkService.deleteArtworkById(id);
        if (deleted) {
            return ResponseEntity.ok(Result.ok("Artwork deleted successfully."));
        } else {
            return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body(Result.error("Artwork not found or could not be deleted."));
        }
    }

    @PutMapping("updateArtwork/{id}")
    public ResponseEntity<Result> updateArtwork(@PathVariable Long id, @RequestBody ArtworkUpdateRequest artworkUpdateRequest) {
        Artwork artwork = new Artwork();
        BeanUtils.copyProperties(artworkUpdateRequest, artwork);
        artwork.setId(id);

        boolean updated = artworkService.updateArtwork(artwork);
        if (updated) {
            return ResponseEntity.ok(Result.ok("Artwork updated successfully."));
        } else {
            return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body(Result.error("Artwork not found or could not be updated."));
        }
    }
}
