package ndgroups.DAShop.service.Interface;

import ndgroups.DAShop.model.CartItem;

public interface ICartItemService {
    void addItemToCart(Integer cartId, Integer productId, Integer quantity);

    void removeItemFromCart(Integer cartId, Integer productId);

    void updateItemQuantity(Integer cartId, Integer productId, Integer quantity);

    CartItem getCartItem(Integer cartId, Integer productId);
}
