package ndgroups.DAShop.controller;

import ndgroups.DAShop.exception.ResourceNotFoundException;
import ndgroups.DAShop.response.ApiResponse;
import ndgroups.DAShop.service.Interface.ICartItemService;
import ndgroups.DAShop.service.Interface.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("${api.prefix}/cartitems")
public class CartItemController {
    @Autowired
    private ICartItemService cartItemService;
    @Autowired
    private ICartService cartService;


    @PostMapping("/item/add")
    public ResponseEntity<ApiResponse>addItemToCart(@RequestParam(required = false) Integer cartId,@RequestParam Integer productId,
                                                    @RequestParam Integer quantity) {
        try {
            if (cartId == null) {
                cartId = cartService.initializeNewCart();
            }

            cartItemService.addItemToCart(cartId, productId, quantity);
            return ResponseEntity.ok(new ApiResponse("successful", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
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
