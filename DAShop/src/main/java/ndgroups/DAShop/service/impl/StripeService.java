package ndgroups.DAShop.service.impl;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import ndgroups.DAShop.dto.ProductRequest;
import ndgroups.DAShop.response.StripeResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StripeService{

    @Value("${stripe.secretKey}")
    private String secretKey;
    @Value("${stripe.publishableKey}")
    private String publishableKey;
    @Value("${app.frontend.url}")
    private String url;

    public StripeResponse checkoutProducts(ProductRequest productRequest){
        Stripe.apiKey = secretKey;
        SessionCreateParams.LineItem.PriceData.ProductData productData = SessionCreateParams.LineItem
                .PriceData.ProductData.builder()
                .setName(productRequest.getName())
                .build();
        SessionCreateParams.LineItem.PriceData priceData = SessionCreateParams.LineItem.PriceData.builder()
                .setCurrency(productRequest.getCurrency() == null ? "USD" : productRequest.getCurrency())
                .setUnitAmount(productRequest.getAmount())
                .setProductData(productData)
                .build();
        SessionCreateParams.LineItem lineItem = SessionCreateParams.LineItem
                .builder().setQuantity(productRequest.getQuantity())
                .setPriceData(priceData)
                .build();

        SessionCreateParams params = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl(url + "/success")
                .setCancelUrl(url + "/cancel")
                .addLineItem(lineItem)
                .build();

        Session session = null;

        try {
            session = Session.create(params);
        } catch (StripeException e) {
            System.out.println(e.getMessage());;
        }

        return StripeResponse.builder()
                .status("success")
                .message("payment session created")
                .sessionId(session.getId())
                .sessionUrl(session.getUrl())
                .build();

    }

}
