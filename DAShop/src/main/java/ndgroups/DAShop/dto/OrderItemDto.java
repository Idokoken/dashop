package ndgroups.DAShop.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemDto {
    private Integer productId;
    private String productName;
    private String productBrand;
    private Integer quantity;
    private BigDecimal price;

}
