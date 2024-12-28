package ndgroups.DAShop.request;

import lombok.Data;
import ndgroups.DAShop.model.Category;

import java.math.BigDecimal;

@Data
public class AddProductRequest {
    private Integer productId;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stockQuantity;
    private String brand;
    private  String SKU;
    private Category category;
}
