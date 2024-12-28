package ndgroups.DAShop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    @NotEmpty(message="name should not be empty")
    private String name;
    @Column(columnDefinition = "longtext")
    private String description;
    private BigDecimal price;
    private Integer stockQuantity;
    private String brand;
    private  String SKU;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "image_id")
    private List<Image> images;


    public Product(String name, String description, BigDecimal price, Integer stockQuantity, String brand, String SKU, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.brand = brand;
        this.SKU = SKU;
        this.category = category;
    }
}
