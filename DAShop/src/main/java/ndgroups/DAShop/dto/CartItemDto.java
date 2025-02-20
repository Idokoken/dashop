package ndgroups.DAShop.dto;


import lombok.Data;
import ndgroups.DAShop.model.Cart;

import java.math.BigDecimal;

@Data
public class CartItemDto {
    private Integer cartItemId;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private ProductDto product;
}
