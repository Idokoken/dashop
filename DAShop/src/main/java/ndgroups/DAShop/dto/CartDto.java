package ndgroups.DAShop.dto;

import lombok.Data;
import ndgroups.DAShop.model.CartItem;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class CartDto {
    private Integer cartId;
    private BigDecimal totalAmount;
    private Set<CartItemDto> cartItems;
}
