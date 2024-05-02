package hu.elte.inf.backend.controller;

import hu.elte.inf.backend.common.Result;
import hu.elte.inf.backend.controller.request.ArtworkInsertRequest;
import hu.elte.inf.backend.controller.request.ArtworkUpdateRequest;
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
@CrossOrigin(origins = "*")
public class ArtworkController {
    @Autowired
    private ArtworkServiceImpl artworkService;

    /**
     * Get all artworks
     * @return List<Artwork>
     */
    @GetMapping("/getAllArtworks")
    public ResponseEntity<List<Artwork>> getAllArtworks() {
        List<Artwork> artworks = artworkService.getAllArtworks();
        return ResponseEntity.ok(artworks);
    }

    /**
     * Get artworks by artist id
     * @param artist_id
     * @return List<Artwork>
     */
    @GetMapping("/getArtworksByArtistId/{artist_id}")
    public ResponseEntity<List<Artwork>> getArtworksByArtistId(@PathVariable Long artist_id) {
        List<Artwork> artworks = artworkService.getArtworksByArtistId(artist_id);
        return ResponseEntity.ok(artworks);
    }

    /**
     * Get artworks by user id
     * @param userId
     * @return List<Artwork>
     */
    @GetMapping("/getUserArtworks/{userId}")
    public ResponseEntity<List<Artwork>> getArtworksByUserId(@PathVariable Long userId) {
        List<Artwork> artworks = artworkService.getArtworksByUserId(userId);
        return ResponseEntity.ok(artworks);
    }

    /**
     * Get artwork by id
     * @param id
     * @return Artwork
     */
    @GetMapping("/getArtworkById/{id}")
    public ResponseEntity<Artwork> getArtistById(@PathVariable Long id) {
        Artwork artwork = artworkService.getArtworkById(id);
        if(artwork != null){
            return ResponseEntity.ok(artwork);
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    /**
     * Get random artwork by price range
     * @param minPrice
     * @param maxPrice
     * @return Artwork
     */
    @GetMapping("/getRandomArtwork")
    public ResponseEntity<Artwork> getRandomArtwork(@RequestParam double minPrice, @RequestParam double maxPrice) {
        Artwork artwork = artworkService.getRandomArtworkByPriceRange(minPrice, maxPrice);
        if (artwork != null) {
            return ResponseEntity.ok(artwork);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Add artwork
     * @param artworkInsertRequest
     * @return Result
     */
    @PostMapping("/registerArtwork")
    public ResponseEntity<Result> addArtwork(@Validated @RequestBody  ArtworkInsertRequest artworkInsertRequest) {
        Artwork artwork = new Artwork();
        BeanUtils.copyProperties(artworkInsertRequest, artwork);

        boolean added = artworkService.addArtwork(artwork);
        if (added) {
            ArtworkResponse artworkResponse = new ArtworkResponse();
            BeanUtils.copyProperties(artwork, artworkResponse);
            return  ResponseEntity.ok(Result.ok("Artwork added successfully.").put("info", artworkResponse));
        } else {
            return  ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(Result.error("Artwork already exists."));
        }
    }

    /**
     * Delete artwork by id
     * @param id
     * @return Result
     */
    @DeleteMapping("deleteArtwork/{id}")
    public ResponseEntity<Result> deleteArtwork(@PathVariable Long id) {
        boolean deleted = artworkService.deleteArtworkById(id);
        if (deleted) {
            return ResponseEntity.ok(Result.ok("Artwork deleted successfully."));
        } else {
            return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body(Result.error("Artwork not found or could not be deleted."));
        }
    }

    /**
     * Update artwork by id
     * @param id
     * @param artworkUpdateRequest
     * @return Result
     */
    @PutMapping("updateArtwork/{id}")
    public ResponseEntity<Result> updateArtwork(@PathVariable Long id, @RequestBody ArtworkUpdateRequest artworkUpdateRequest) {
        Artwork artwork = new Artwork();
        BeanUtils.copyProperties(artworkUpdateRequest, artwork);
        artwork.setId(id);

        boolean updated = artworkService.updateArtwork(artwork);
        if (updated) {
            ArtworkResponse artworkResponse = new ArtworkResponse();
            BeanUtils.copyProperties(artwork, artworkResponse);

            return ResponseEntity.ok(Result.ok("Artwork updated successfully.").put("info", artworkResponse));
        } else {
            return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body(Result.error("Artwork not found or could not be updated."));
        }
    }

    @PostMapping("/purchase/{artworkId}")
    public Result purchaseArtwork(@PathVariable Long artworkId, @RequestParam Long userId) {
        boolean success = artworkService.purchaseArtwork(userId, artworkId);
        if (success) {
            return Result.ok("Artwork purchased successfully.");
        } else {
            return Result.error("Artwork not found or could not be purchased.");
        }
    }

}
