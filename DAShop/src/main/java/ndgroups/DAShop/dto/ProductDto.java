package ndgroups.DAShop.dto;

import lombok.Data;
import ndgroups.DAShop.model.Category;
import ndgroups.DAShop.model.Image;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductDto {
    private Integer productId;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stockQuantity;
    private String brand;
    private  String SKU;
    private Category category;
    private List<ImageDto> images;
}
