//package ndgroups.DAShop.model;
//
//import jakarta.persistence.*;
//
//import java.util.Date;
//
//@Entity
//public class Review {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long reviewId;
//    private int rating;
//    private String reviewText;
//    private Date reviewDate;
//
//    @ManyToOne
//    @JoinColumn(name = "product_id")
//    private Product product;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    // Getters and Setters
//}
