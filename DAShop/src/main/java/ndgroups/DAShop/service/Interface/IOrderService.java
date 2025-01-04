package ndgroups.DAShop.service.Interface;

import ndgroups.DAShop.model.Order;

import java.util.List;

public interface IOrderService {
    Order placeOrder(Integer userId);
    Order getOrder(Integer orderId);

    List<Order> getUserOrders(Integer userId);
}
