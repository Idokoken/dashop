package ndgroups.DAShop.request;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String fistName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
}
