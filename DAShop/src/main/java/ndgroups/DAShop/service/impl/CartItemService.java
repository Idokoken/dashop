package ndgroups.DAShop.service.impl;

import ndgroups.DAShop.exception.ResourceNotFoundException;
import ndgroups.DAShop.model.Cart;
import ndgroups.DAShop.model.CartItem;
import ndgroups.DAShop.model.Product;
import ndgroups.DAShop.repository.CartItemRepository;
import ndgroups.DAShop.repository.CartRepository;
import ndgroups.DAShop.service.Interface.ICartItemService;
import ndgroups.DAShop.service.Interface.ICartService;
import ndgroups.DAShop.service.Interface.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CartItemService implements ICartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private IProductService productService;
    @Autowired
    private ICartService cartService;

    @Override
    public void addItemToCart(Integer cartId, Integer productId, Integer quantity) {
        Cart cart = cartService.getCart(cartId);
        Product product =productService.getProductById(productId);
        CartItem cartItem = cart.getCartItems().stream()
                .filter(item-> item.getProduct().getProductId().equals(productId))
                .findFirst().orElse(new CartItem());
        if(cartItem.getCart() == null){
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setUnitPrice(product.getPrice());
        }else {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        }
        cartItem.setTotalPrice();
        cart.addCartItem(cartItem);
        cartItemRepository.save(cartItem);
        cartRepository.save(cart);

    }

    @Override
    public void removeItemFromCart(Integer cartId, Integer productId) {
        Cart cart = cartService.getCart(cartId);
        CartItem itemToRemove = getCartItem(cartId, productId);
        cart.removeItem(itemToRemove);
        cartRepository.save(cart);

    }

    @Override
    public void updateItemQuantity(Integer cartId, Integer productId, Integer quantity) {
        Cart cart = cartService.getCart(cartId);
        Product product = productService.getProductById(productId);
        cart.getCartItems()
                .stream()
                .filter(item-> item.getProduct().getProductId().equals(productId))
                .findFirst()
                .ifPresent(item -> {
                    item.setQuantity(quantity);
                    item.setUnitPrice(item.getProduct().getPrice());
                    item.setTotalPrice();
                });
        BigDecimal totalAmount = cart.getCartItems().stream().map(CartItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        cart.setTotalAmount(totalAmount);
        cartRepository.save(cart);

    }

    @Override
    public  CartItem getCartItem(Integer cartId, Integer productId){
        Cart cart = cartService.getCart(cartId);
        return cart.getCartItems().stream()
                .filter(item-> item.getProduct().getProductId().equals(productId))
                .findFirst().orElseThrow(() -> new ResourceNotFoundException("Item not found"));
    }
}
