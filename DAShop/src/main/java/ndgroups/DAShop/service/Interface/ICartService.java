package ndgroups.DAShop.service.Interface;

import ndgroups.DAShop.model.Cart;

import java.math.BigDecimal;

public interface ICartService {
    Cart getCart(Integer cartId);
    void clearCart(Integer cartId);
    BigDecimal getTotalPrice(Integer id);

    Integer initializeNewCart();

    Cart getCartByUserId(Integer userId);
}
