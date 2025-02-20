package ndgroups.DAShop.service.Interface;

import ndgroups.DAShop.model.Cart;
import ndgroups.DAShop.model.User;

import java.math.BigDecimal;

public interface ICartService {
    Cart getCart(Integer cartId);
    void clearCart(Integer cartId);
    BigDecimal getTotalPrice(Integer id);

    Cart initializeNewCart(User user);

    Cart getCartByUserId(Integer userId);
}
