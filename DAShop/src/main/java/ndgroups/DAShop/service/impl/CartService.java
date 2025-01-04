package ndgroups.DAShop.service.impl;

import lombok.RequiredArgsConstructor;
import ndgroups.DAShop.exception.ResourceNotFoundException;
import ndgroups.DAShop.model.Cart;
import ndgroups.DAShop.model.CartItem;
import ndgroups.DAShop.repository.CartItemRepository;
import ndgroups.DAShop.repository.CartRepository;
import ndgroups.DAShop.service.Interface.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    private final AtomicInteger cartIdGenerator  = new AtomicInteger(0);

    @Override
    public Cart getCart(Integer cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new ResourceNotFoundException("cart not found"));
        BigDecimal totalAmount = cart.getTotalAmount();
        cart.setTotalAmount(totalAmount);
        return cartRepository.save(cart);
    }
//    @Transactional
    @Override
    public void clearCart(Integer cartId) {
        Cart cart = getCart(cartId);
        cartItemRepository.deleteAllByCartId(cartId);
        cart.getCartItems().clear();
        cartRepository.deleteById(cartId);

    }

    @Override
    public BigDecimal getTotalPrice(Integer id) {
        Cart cart = getCart(id);
        return cart.getCartItems().stream().map(CartItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
//        return cart.getTotalAmount();
    }

    @Override
    public Integer initializeNewCart(){
        Cart newCart = new Cart();
        Integer newCartId = cartIdGenerator.incrementAndGet();
        newCart.setId(newCartId);
        return cartRepository.save(newCart).getId();

    }
    @Override
    public Cart getCartByUserId(Integer userId) {
    return null;
    }
}
