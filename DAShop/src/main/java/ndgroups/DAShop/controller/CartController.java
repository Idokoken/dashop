package ndgroups.DAShop.controller;

import ndgroups.DAShop.exception.ResourceNotFoundException;
import ndgroups.DAShop.model.Cart;
import ndgroups.DAShop.response.ApiResponse;
import ndgroups.DAShop.service.Interface.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("${api.prefix}/carts")
public class CartController {
    @Autowired
    private ICartService cartService;

    @GetMapping("/cart/{id}")
    public ResponseEntity<ApiResponse> getCart(@PathVariable Integer id) {
        try {
            Cart cart =  cartService.getCart(id);
            return ResponseEntity.ok(new ApiResponse("successful", cart));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }
    @DeleteMapping("/cart/clear/ {id}")
    public ResponseEntity<ApiResponse> clearCart(@PathVariable Integer id) {
        try {
            cartService.clearCart(id);
            return ResponseEntity.ok(new ApiResponse("clear cart successful", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }
    @GetMapping("/cart/total-price/{id}")
    public ResponseEntity<ApiResponse> getTotalPrice(@PathVariable Integer id) {
        try {
            BigDecimal totalPrice =  cartService.getTotalPrice(id);
            return ResponseEntity.ok(new ApiResponse("successful", totalPrice));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

}
