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


@RestController
@RequestMapping(value = "/artist")
@CrossOrigin(origins = "*")// 将15行以下的东西
public class ArtistController {
    @Autowired
    private ArtistServiceImpl artistService;

    @GetMapping("/getAllArtists")
    public ResponseEntity<List<Artist>> getAllUsers() {
        List<Artist> artists = artistService.getAllArtists();
        return ResponseEntity.ok(artists);
    }

    @PostMapping("/registerArtist")
    public ResponseEntity<Result> registerArtist(@Validated @RequestBody ArtistInsertRequest artistInsertRequest) {
        try {
            Artist artist = new Artist();
            BeanUtils.copyProperties(artistInsertRequest, artist);
            artistService.insertArtist(artist);

            // 将 Artist 实体转换为响应对象，如果你有一个响应类的话。
            ArtistResponse artistResponse = new ArtistResponse();
            BeanUtils.copyProperties(artist, artistResponse);

            return ResponseEntity.ok(Result.ok("Artist added successfully.").put("info", artistResponse));
        } catch (ArtistExistException e){
            return ResponseEntity.status(HttpStatus.SC_CONFLICT).body(Result.error("Artist already exists"));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(Result.error("Internal server error"));
        }
    }

    @DeleteMapping("deleteArtist/{id}")
    public ResponseEntity<Result> deleteArtist(@PathVariable Long id) {
        try {
            artistService.deleteArtist(id);
            return ResponseEntity.ok(Result.ok("Artist deleted successfully"));
        } catch (ArtistNotFoundException e) {
            return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body(Result.error("Artist Not Found"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(Result.error("Internal server error"));
        }
    }

    @PutMapping("/updateArtistInfo/{id}")
    public ResponseEntity<Result> updateUserInfo(@PathVariable Long id, @Validated @RequestBody ArtistUpdateRequest artistUpdateRequest) {
        try {
            Artist artist = new Artist();
            BeanUtils.copyProperties(artistUpdateRequest, artist);
            artist.setId(id);
            // 这里调用服务层的更新方法，并获取返回的更新后的用户对象
            Artist updatedArtist = artistService.updateArtist(artist);

            // 将User转换为UserLoginResponse的方法或逻辑，就在这里使用它
            ArtistResponse artistResponse = new ArtistResponse();
            BeanUtils.copyProperties(updatedArtist, artistResponse);

            // 返回更新后的用户数据
            return ResponseEntity.ok(Result.ok("Artist info updated successfully").put("info", artistResponse));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(Result.error("Internal server error"));
        }
    }
}
