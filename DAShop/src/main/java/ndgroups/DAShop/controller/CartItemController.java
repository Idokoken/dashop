package ndgroups.DAShop.controller;

import io.jsonwebtoken.JwtException;
import ndgroups.DAShop.exception.ResourceNotFoundException;
import ndgroups.DAShop.model.Cart;
import ndgroups.DAShop.model.User;
import ndgroups.DAShop.response.ApiResponse;
import ndgroups.DAShop.service.Interface.ICartItemService;
import ndgroups.DAShop.service.Interface.ICartService;
import ndgroups.DAShop.service.Interface.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("${api.prefix}/cartitems")
public class CartItemController {
    @Autowired
    private ICartItemService cartItemService;
    @Autowired
    private ICartService cartService;
    @Autowired
    private IUserService userService;


    @PostMapping("/item/add")
    public ResponseEntity<ApiResponse>addItemToCart(@RequestParam Integer productId,
                                                    @RequestParam Integer quantity) {
        try {
            User user = userService.getAuthenticatedUser();
            Cart cart = cartService.initializeNewCart(user);

            cartItemService.addItemToCart(cart.getId(), productId, quantity);
            return ResponseEntity.ok(new ApiResponse("successful", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }catch (JwtException e){
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                   .body(new ApiResponse(e.getMessage(), null));
        }
    }
    @DeleteMapping("/item/remove/{cartId}/{productId}")
    public ResponseEntity<ApiResponse>removeItemFromCart(@PathVariable Integer cartId, @PathVariable Integer productId) {
        try {
            cartItemService.removeItemFromCart(cartId, productId);
            return ResponseEntity.ok(new ApiResponse("successfully removed", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/item/update/{cartId}/{productId}")
    public ResponseEntity<ApiResponse>updateItemQuantity(@PathVariable Integer cartId,@PathVariable Integer productId,
                                                         @RequestParam Integer quantity) {
        try {
            cartItemService.updateItemQuantity(cartId, productId, quantity);
            return ResponseEntity.ok(new ApiResponse("successfully updated", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }
}
