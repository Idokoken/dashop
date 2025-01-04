package ndgroups.DAShop.service.impl;

import lombok.RequiredArgsConstructor;
import ndgroups.DAShop.enums.OrderStatus;
import ndgroups.DAShop.exception.ResourceNotFoundException;
import ndgroups.DAShop.model.Cart;
import ndgroups.DAShop.model.Order;
import ndgroups.DAShop.model.OrderItem;
import ndgroups.DAShop.model.Product;
import ndgroups.DAShop.repository.OrderRepository;
import ndgroups.DAShop.repository.ProductRepository;
import ndgroups.DAShop.service.Interface.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartService cartService;


    @Override
    public Order placeOrder(Integer userId) {
        Cart cart = cartService.getCartByUserId(userId);
        Order order = createOrder(cart);
        List<OrderItem> orderItemList = createOrderItems(order, cart);
        order.setOrderItems(new HashSet<>(orderItemList));
        order.setTotalAmount(calculateTotalAmount(orderItemList));
        Order savedOrder = orderRepository.save(order);
        cartService.clearCart(cart.getId());
        return savedOrder;
    }

    @Override
    public Order getOrder(Integer orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }
    @Override
    public List<Order>getUserOrders(Integer userId){
        return null;
    }

    private Order createOrder(Cart cart){
        Order order = new Order();
        // set the user
        order.setOrderStatus(OrderStatus.PENDING);
        order.setOrderDate(LocalDate.now());
        return order;
    }

    private List<OrderItem> createOrderItems(Order order, Cart cart){
        return cart.getCartItems().stream().map(cartItem -> {
            Product product  = cartItem.getProduct();
            product.setStockQuantity(product.getStockQuantity() - cartItem.getQuantity());
            productRepository.save(product);
            return new OrderItem(cartItem.getQuantity(), cartItem.getUnitPrice(), order, product);
        }).toList();
    }

    private BigDecimal calculateTotalAmount(List<OrderItem> orderItemList){
       return orderItemList.stream()
               .map(item -> item.getPricePerUnit().multiply(new BigDecimal(item.getQuantity())))
               .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
