package ndgroups.DAShop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartItemId;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private Cart cart;
//    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

   public void setTotalPrice() {
       this.totalPrice = this.unitPrice.multiply(new BigDecimal(quantity));
   }
}
