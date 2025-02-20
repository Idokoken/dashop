package ndgroups.DAShop.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private Integer id;
    private String fistName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private List<OrderDto>orders;
    private CartDto cart;

}
