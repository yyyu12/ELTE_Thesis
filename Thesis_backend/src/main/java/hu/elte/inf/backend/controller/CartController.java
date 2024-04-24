package hu.elte.inf.backend.controller;

import hu.elte.inf.backend.controller.request.CartRequest;
import hu.elte.inf.backend.common.Result;
import hu.elte.inf.backend.service.impl.CartServiceImpl;
import hu.elte.inf.backend.sqlEntity.Cart;
import hu.elte.inf.backend.sqlEntity.CartDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


import java.util.List;

@RestController
@RequestMapping(value = "/cart")
@CrossOrigin(origins = "*")
public class CartController {

    @Autowired
    private CartServiceImpl cartService;

    /**
     * Get all cart details by user id
     * @param user_id
     * @return
     */
    @GetMapping("/user/{user_id}/details")
    public ResponseEntity<List<CartDetail>> getCartDetailsByUserId(@PathVariable Long user_id) {
        List<CartDetail> cartDetails = cartService.getAllCartDetailsByUserId(user_id);

        return ResponseEntity.ok(cartDetails);
    }


    /**
     * Add to cart
     * @param user_id
     * @param artwork_id
     * @return
     */
    @PostMapping("/addToCart")
    public Result addToCart(@Validated @RequestBody CartRequest cartRequest) {
        long user_id = cartRequest.getUser_id();
        long artwork_id = cartRequest.getArtwork_id();

        cartService.addToCart(user_id, artwork_id);
        return Result.ok("Item added to cart");
    }

    /**
     * Get all carts by user id
     * @param user_id
     * @return
     */
    @GetMapping("/getCartsByUserId/{user_id}")
    public ResponseEntity<List<Cart>> getCartsByUserId(@PathVariable Long user_id) {
        List<Cart> carts = cartService.getCartsByUserId(user_id);
        return ResponseEntity.ok(carts);
    }

    
    /**
     * Remove from cart by user id and artwork id
     * @param user_id
     * @param artwork_id
     * @return
     */
    @DeleteMapping("/remove")
    public ResponseEntity<Result> removeFromCart(@RequestParam Long user_id, @RequestParam Long artwork_id) {
        cartService.removeCart(user_id, artwork_id);
        return ResponseEntity.ok(Result.ok("Artwork deleted successfully."));
    }

    /**
     * Remove from cart by cart id
     * @param cart_id
     * @return
     */
    @DeleteMapping("/removeByCartId/{cart_id}")
    public ResponseEntity<Result> removeByCartId(@PathVariable Long cart_id) {
        cartService.removeCartByCartId(cart_id);
        return ResponseEntity.ok(Result.ok("Artwork deleted successfully."));
    }
}
