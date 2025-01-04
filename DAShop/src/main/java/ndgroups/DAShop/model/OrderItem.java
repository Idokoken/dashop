package ndgroups.DAShop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderItemId;
    private Integer quantity;
    private BigDecimal pricePerUnit;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public OrderItem(Integer quantity, BigDecimal pricePerUnit, Order order, Product product) {
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.order = order;
        this.product = product;
    }
}
