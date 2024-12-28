//package ndgroups.DAShop.model;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
//@Entity
//public class Address {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer addressId;
//    private String addressLine;
//    private String city;
//    private String state;
//    private String postalCode;
//    private String country;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    // Getters and Setters
//}
//
