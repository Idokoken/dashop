//package ndgroups.DAShop.model;
//
//import jakarta.persistence.*;
//
//import java.util.Date;
//import java.util.List;
//
//@Entity
//public class Order {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long orderId;
//    private String orderStatus;
//    private double totalPrice;
//    private Date createdAt;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    @ManyToOne
//    @JoinColumn(name = "address_id")
//    private Address shippingAddress;
//
//    @OneToMany(mappedBy = "order")
//    private List<OrderItem> orderItems;
//
//    // Getters and Setters
//}
//
