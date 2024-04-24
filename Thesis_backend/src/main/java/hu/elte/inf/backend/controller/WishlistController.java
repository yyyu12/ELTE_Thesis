package hu.elte.inf.backend.controller;

import hu.elte.inf.backend.controller.request.WishlistRequest;
import hu.elte.inf.backend.common.Result;
import hu.elte.inf.backend.service.impl.WishlistServiceImpl;
import hu.elte.inf.backend.sqlEntity.Wishlist;
import hu.elte.inf.backend.sqlEntity.WishlistDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


import java.util.List;

@RestController
@RequestMapping(value = "/wishlist")
@CrossOrigin(origins = "*")
public class WishlistController {

    @Autowired
    private WishlistServiceImpl wishlistService;

    @GetMapping("/user/{user_id}/details")
    public ResponseEntity<List<WishlistDetail>> getWishlistDetailsByUserId(@PathVariable Long user_id) {
        List<WishlistDetail> wishlistDetails = wishlistService.getAllWishlistDetailsByUserId(user_id);

        return ResponseEntity.ok(wishlistDetails);
    }

    @GetMapping("/getWishlistsByUserId/{user_id}")
    public ResponseEntity<List<Wishlist>> getWishlistsByUserId(@PathVariable Long user_id) {
        List<Wishlist> wishlists = wishlistService.getWishlistsByUserId(user_id);
        return ResponseEntity.ok(wishlists);
    }

    @PostMapping("/addToWishlist")
    public Result addToWishlist(@Validated @RequestBody WishlistRequest wishlistRequest) {
        long user_id = wishlistRequest.getUser_id();
        long artwork_id = wishlistRequest.getArtwork_id();

        wishlistService.addToWishlist(user_id, artwork_id);
        return Result.ok("Item added to wishlist");
    }

    @DeleteMapping("/deleteWishlistByWishlistId/{wishlist_id}")
    public Result deleteWishlistByWishlistId(@PathVariable Long wishlist_id) {
        wishlistService.deleteWishlistByWishlistId(wishlist_id);
        return Result.ok("Item deleted from wishlist");
    }
}
