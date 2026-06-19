package ndgroups.DAShop.request;

import lombok.Data;
import ndgroups.DAShop.model.Category;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

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
    private List<MultipartFile> images;
}
