package ndgroups.DAShop.controller;

import ndgroups.DAShop.dto.ProductRequest;
import ndgroups.DAShop.response.StripeResponse;
import ndgroups.DAShop.service.impl.StripeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductCheckoutController {

    private StripeService stripeService;

    public ProductCheckoutController(StripeService stripeService) {
        this.stripeService = stripeService;
    }

    @PostMapping("/checkout")
    public ResponseEntity<StripeResponse>checkoutProduct(@RequestBody ProductRequest productRequest){
       StripeResponse stripeResponse = stripeService.checkoutProducts(productRequest);
       return ResponseEntity.status(HttpStatus.OK).body(stripeResponse);

    }
}
