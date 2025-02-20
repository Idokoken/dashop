package ndgroups.DAShop.service.Interface;

import ndgroups.DAShop.dto.OrderDto;
import ndgroups.DAShop.model.Order;

import java.util.List;

public interface IOrderService {
    Order placeOrder(Integer userId);
    OrderDto getOrder(Integer orderId);

    List<OrderDto> getUserOrders(Integer userId);

    OrderDto convertToDto(Order order);
}
